package com.wannaone.elice.youtubeapi.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wannaone.elice.youtubeapi.R;
import com.wannaone.elice.youtubeapi.adpater.YoutubeViewAdapter;
import com.wannaone.elice.youtubeapi.data.YoutubeListData;
import com.wannaone.elice.youtubeapi.presenter.YoutubePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubeFragment extends BaseFragment implements YoutubeViewAdapter.OnLoadMoreListener {

    RecyclerView videoRecyclerView;
    SwipeRefreshLayout refreshLayout;
    Handler mHandler;
    YoutubePresenter presenter;
    //    ArrayList<YoutubeListData.Item> dataList;
//    ArrayList<YoutubeListData.Item> baseList;
    YoutubeViewAdapter adapter;
    String token;

    public static YoutubeFragment newInstance() {
        YoutubeFragment youtubeFragment = new YoutubeFragment();
        return youtubeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_youtube, container, false);
//        dataList = new ArrayList<>();
//        baseList = new ArrayList<>();
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.youtubeRefresh);
        adapter = new YoutubeViewAdapter(getContext(), this);
        videoRecyclerView = (RecyclerView) v.findViewById(R.id.videoView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        videoRecyclerView.setLayoutManager(mLayoutManager);
        videoRecyclerView.setAdapter(adapter);
        adapter.setLinearLayoutManager(mLayoutManager);
        mHandler = new Handler(Looper.getMainLooper());
        adapter.setRecyclerView(videoRecyclerView);
        setupView();
        updateList();
        return v;
    }

    private void setupView() {
        presenter = new YoutubePresenter();
        presenter.setView(this);
        presenter.loadVideoList();
    }

    public void onComplete(YoutubeListData body) {
        token = body.nextPageToken;
//        adapter.addItemMore(body.items);
        adapter.addAll(body.items);
        adapter.notifyDataSetChanged();
    }

    private void updateList() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                    }
                }, 2000);
            }
        });
    }

    //새로 동영상 리스트를 가져와 업데이트 해주는 기능
    private void refreshData() {
        presenter.loadVideoList();
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {
        if (token != null) {
            showLoading();
            presenter.loadMore(token);
        }
    }

    public void onSuccessMore(YoutubeListData body) {
        hideLoading();
        token = body.nextPageToken;
        adapter.addItemMore(body.items);
        adapter.setIsMoreLoading(false);
        //recyclerview의 addOnscrollLister 작동하도록 함 (스크롤 내리면 계속 로딩되도록)
//        adapter.setMoreLoading(false);
//        adapter.setProgressMore(false);
    }
}

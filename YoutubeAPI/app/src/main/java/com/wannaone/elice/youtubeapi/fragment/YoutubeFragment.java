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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubeFragment extends Fragment {

    RecyclerView videoRecyclerView;
    SwipeRefreshLayout refreshLayout;
    Handler mHandler;
    YoutubePresenter presenter;
    ArrayList<YoutubeListData.Item> dataList;
    ArrayList<YoutubeListData.Item> baseList;
    YoutubeViewAdapter adapter;

    public static YoutubeFragment newInstance() {
        YoutubeFragment youtubeFragment = new YoutubeFragment();
        return youtubeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_youtube, container, false);
        dataList = new ArrayList<>();
        baseList = new ArrayList<>();
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.youtubeRefresh);
        adapter = new YoutubeViewAdapter(getContext(), baseList);
        videoRecyclerView = (RecyclerView) v.findViewById(R.id.videoView);
        videoRecyclerView.setAdapter(adapter);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mHandler = new Handler(Looper.getMainLooper());
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
        dataList = (ArrayList<YoutubeListData.Item>) body.items;
        for (YoutubeListData.Item item : dataList) {
            baseList.add(item);
        }
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
        refreshLayout.setRefreshing(false);
    }

}

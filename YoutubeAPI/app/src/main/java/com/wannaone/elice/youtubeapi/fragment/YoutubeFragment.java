package com.wannaone.elice.youtubeapi.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wannaone.elice.youtubeapi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubeFragment extends Fragment {

    RecyclerView videoRecyclerView;
    SwipeRefreshLayout refreshLayout;
    Handler mHandler;

    public static YoutubeFragment newInstance() {
        YoutubeFragment youtubeFragment = new YoutubeFragment();
        return youtubeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_youtube, container, false);
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.youtubeRefresh);
        mHandler = new Handler(Looper.getMainLooper());
        updateList();

        return v;
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

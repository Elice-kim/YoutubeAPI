package com.wannaone.elice.youtubeapi.fragment;

import android.support.v4.app.Fragment;

import com.wannaone.elice.youtubeapi.activity.BaseActivity;

/**
 * Created by elice.kim on 2017. 7. 8..
 */

public class BaseFragment extends Fragment {

    protected void showLoading() {
        if (getContext() instanceof BaseActivity) {
            BaseActivity rootActivity = (BaseActivity) getContext();
            rootActivity.showLoading();
        }
    }

    protected void hideLoading() {
        if (getContext() instanceof BaseActivity) {
            BaseActivity rootActivity = (BaseActivity) getContext();
            rootActivity.hideLoading();
        }
    }
}

package com.wannaone.elice.youtubeapi;

import android.support.v4.app.Fragment;

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

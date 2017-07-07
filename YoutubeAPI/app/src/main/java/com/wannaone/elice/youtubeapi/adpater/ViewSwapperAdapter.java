package com.wannaone.elice.youtubeapi.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wannaone.elice.youtubeapi.bottomnavigation.FragmentStateAdapter;
import com.wannaone.elice.youtubeapi.fragment.ChattingFragment;
import com.wannaone.elice.youtubeapi.fragment.SettingFragment;
import com.wannaone.elice.youtubeapi.fragment.YoutubeFragment;

/**
 * Created by elice.kim on 2017. 7. 7..
 */

public class ViewSwapperAdapter extends FragmentStateAdapter {

    private static final int INDEX_CHAT = 0;
    private static final int INDEX_VIDEO = 1;
    private static final int INDEX_SETTING = 2;

    public ViewSwapperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case INDEX_CHAT:
                return new ChattingFragment();
            case INDEX_VIDEO:
                return new YoutubeFragment();
            case INDEX_SETTING:
                return new SettingFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}

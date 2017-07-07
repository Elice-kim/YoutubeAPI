package com.wannaone.elice.youtubeapi.adpater;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by elice.kim on 2017. 7. 7..
 */

public class YoutubeViewAdapter extends RecyclerView.Adapter<YoutubeViewAdapter.YoutubeViewHolder>{

    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class YoutubeViewHolder extends RecyclerView.ViewHolder{

        public YoutubeViewHolder(View itemView) {
            super(itemView);
        }
    }
}

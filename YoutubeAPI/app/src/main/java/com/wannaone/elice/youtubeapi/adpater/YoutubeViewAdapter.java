package com.wannaone.elice.youtubeapi.adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wannaone.elice.youtubeapi.R;
import com.wannaone.elice.youtubeapi.data.YoutubeListData;

import java.util.ArrayList;

/**
 * Created by elice.kim on 2017. 7. 7..
 */

public class YoutubeViewAdapter extends RecyclerView.Adapter<YoutubeViewAdapter.YoutubeViewHolder>{

    private Context context;
    private ArrayList<YoutubeListData.Item> videoArray;

    public YoutubeViewAdapter(Context context, ArrayList<YoutubeListData.Item> videoArray) {
        this.context = context;
        this.videoArray = videoArray;
    }

    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.view_youtube, parent, false);
        return new YoutubeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, int position) {
        final YoutubeListData.Item data = videoArray.get(position);

        Glide.with(context).load(data.snippet.thumbnails.high.url).into(holder.imageView);
        holder.title.setText(data.snippet.title);
        holder.channel.setText(data.snippet.channelTitle);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playerIntent
                        = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" +data.id.videoId));
                context.startActivity(playerIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoArray.size();
    }

    public class YoutubeViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title, channel;
        private LinearLayout layout;

        public YoutubeViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.thumbnails);
            title = (TextView) itemView.findViewById(R.id.titleTxt);
            channel = (TextView) itemView.findViewById(R.id.channelTxt);
            layout = (LinearLayout) itemView.findViewById(R.id.videoLayout);
        }
    }
}

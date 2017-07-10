package com.wannaone.elice.youtubeapi.adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
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
import java.util.List;

/**
 * Created by elice.kim on 2017. 7. 7..
 */

public class YoutubeViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<YoutubeListData.Item> videoArray;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager mLinearLayoutManager;

    private boolean isMoreLoading = false;
    private int visibleThreshold = 1;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public YoutubeViewAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
        this.context = context;
        this.videoArray = new ArrayList<>();
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    public void setRecyclerView(RecyclerView mView) {
        mView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLinearLayoutManager.getItemCount();
                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
                if (!isMoreLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isMoreLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return videoArray.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new YoutubeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_youtube, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof YoutubeViewHolder) {
            final YoutubeListData.Item data = videoArray.get(position);

            Glide.with(context).load(data.snippet.thumbnails.high.url).into(((YoutubeViewHolder) holder).imageView);
            ((YoutubeViewHolder) holder).title.setText(data.snippet.title);
            ((YoutubeViewHolder) holder).channel.setText(data.snippet.channelTitle);
            ((YoutubeViewHolder) holder).layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent playerIntent
                            = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + data.id.videoId));
                    context.startActivity(playerIntent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return videoArray.size();
    }

    public void addAll(List<YoutubeListData.Item> lst) {
        videoArray.clear();
        videoArray.addAll(lst);
        notifyDataSetChanged();
    }

    public void addItemMore(List<YoutubeListData.Item> lst) {
        videoArray.addAll(lst);
        notifyDataSetChanged();
    }

    public void setIsMoreLoading(boolean isMoreLoading) {
        this.isMoreLoading = isMoreLoading;
    }

    public class YoutubeViewHolder extends RecyclerView.ViewHolder {

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

package com.wannaone.elice.youtubeapi.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elice.kim on 2017. 7. 7..
 */

public class YoutubeListData {

    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("etag")
    @Expose
    public String etag;
    @SerializedName("nextPageToken")
    @Expose
    public String nextPageToken;
    @SerializedName("regionCode")
    @Expose
    public String regionCode;
    @SerializedName("pageInfo")
    @Expose
    public PageInfo pageInfo;
    @SerializedName("items")
    @Expose
    public List<Item> items = null;

    public class PageInfo {

        @SerializedName("totalResults")
        @Expose
        public Integer totalResults;
        @SerializedName("resultsPerPage")
        @Expose
        public Integer resultsPerPage;
    }

    public class Item {

        @SerializedName("kind")
        @Expose
        public String kind;
        @SerializedName("etag")
        @Expose
        public String etag;
        @SerializedName("id")
        @Expose
        public Id id;
        @SerializedName("snippet")
        @Expose
        public Snippet snippet;

        public class Id {

            @SerializedName("kind")
            @Expose
            public String kind;
            @SerializedName("videoId")
            @Expose
            public String videoId;

        }

        public class Snippet {

            @SerializedName("publishedAt")
            @Expose
            public String publishedAt;
            @SerializedName("channelId")
            @Expose
            public String channelId;
            @SerializedName("title")
            @Expose
            public String title;
            @SerializedName("description")
            @Expose
            public String description;
            @SerializedName("thumbnails")
            @Expose
            public Thumbnails thumbnails;
            @SerializedName("channelTitle")
            @Expose
            public String channelTitle;
            @SerializedName("liveBroadcastContent")
            @Expose
            public String liveBroadcastContent;

            public class Thumbnails {

                @SerializedName("default")
                @Expose
                public Default _default;
                @SerializedName("medium")
                @Expose
                public Medium medium;
                @SerializedName("high")
                @Expose
                public High high;

            }

            public class Default {

                @SerializedName("url")
                @Expose
                public String url;
                @SerializedName("width")
                @Expose
                public Integer width;
                @SerializedName("height")
                @Expose
                public Integer height;

            }

            public class Medium {

                @SerializedName("url")
                @Expose
                public String url;
                @SerializedName("width")
                @Expose
                public Integer width;
                @SerializedName("height")
                @Expose
                public Integer height;

            }

            public class High {

                @SerializedName("url")
                @Expose
                public String url;
                @SerializedName("width")
                @Expose
                public Integer width;
                @SerializedName("height")
                @Expose
                public Integer height;

            }

        }

    }


}

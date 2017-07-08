package com.wannaone.elice.youtubeapi.restapi;

import com.wannaone.elice.youtubeapi.data.YoutubeListData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by elice.kim on 2017. 7. 7..
 */

public interface YoutubeService {

    @GET("search?key=AIzaSyCQCuFaFfOYy4Gp0YQ91PBL_DAoxR8bex4&part=snippet&maxResults=10")
    Call<YoutubeListData> groupList(@Query("q") String name);

    @GET("search?key=AIzaSyCQCuFaFfOYy4Gp0YQ91PBL_DAoxR8bex4&part=snippet&maxResults=10")
    Call<YoutubeListData> loadMore(@Query("pageToken") String token, @Query("q") String name);
}

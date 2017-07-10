package com.wannaone.elice.youtubeapi.presenter;

import com.wannaone.elice.youtubeapi.data.YoutubeListData;
import com.wannaone.elice.youtubeapi.fragment.YoutubeFragment;
import com.wannaone.elice.youtubeapi.module.DefaultRestClient;
import com.wannaone.elice.youtubeapi.restapi.YoutubeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elice.kim on 2017. 7. 7..
 */

public class YoutubePresenter extends BasePresenter<YoutubeFragment> {

    public void loadVideoList(){
        DefaultRestClient<YoutubeService> client = new DefaultRestClient();
        YoutubeService service = client.getService(YoutubeService.class, view.getContext());
        Call<YoutubeListData> call = service.groupList("워너원");
        call.enqueue(new Callback<YoutubeListData>() {
            @Override
            public void onResponse(Call<YoutubeListData> call, Response<YoutubeListData> response) {
                if(response.isSuccessful())
                {
                    view.onComplete(response.body());
                }
            }

            @Override
            public void onFailure(Call<YoutubeListData> call, Throwable t) {

            }
        });

    }

    public void loadMore(String token){
        DefaultRestClient<YoutubeService> client = new DefaultRestClient<>();
        YoutubeService service = client.getService(YoutubeService.class, view.getContext());
        Call<YoutubeListData> call = service.loadMore(token, "워너원");
        call.enqueue(new Callback<YoutubeListData>() {
            @Override
            public void onResponse(Call<YoutubeListData> call, Response<YoutubeListData> response) {
                if(response.isSuccessful()){
                    view.onSuccessMore(response.body());
                }
            }

            @Override
            public void onFailure(Call<YoutubeListData> call, Throwable t) {

            }
        });

    }
}

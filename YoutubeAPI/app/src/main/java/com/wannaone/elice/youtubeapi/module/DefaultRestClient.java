package com.wannaone.elice.youtubeapi.module;

import android.content.Context;

import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by elice.kim on 2017. 7. 7..
 */

public class DefaultRestClient<T> {

    private T service;
    public String baseUrl = "https://www.googleapis.com/youtube/v3/";

    public T getService(Class<? extends T> type, Context context) {

        if (service == null) {


            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            OkHttpClient okHttpClient
                    = new OkHttpClient
                    .Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(new ChuckInterceptor(context))
                    .addInterceptor(new Interceptor() {

                        @Override
                        public Response intercept(Chain chain) throws IOException {

                            Request original = chain.request();

                            Request request = original.newBuilder()
                                    .method(original.method(), original.body())
                                    .header("content-type", "header")
                                    .build();

                            return chain.proceed(request);
                        }
                    }).build();


            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = client.create(type);
        }
        return service;
    }
}

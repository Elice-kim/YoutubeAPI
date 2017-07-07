package com.wannaone.elice.youtubeapi;

/**
 * Created by elice.kim on 2017. 7. 7..
 */

public class BasePresenter<T> {

    public T view;

    public void setView(T view){
        this.view = view;
    }
}

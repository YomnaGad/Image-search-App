package com.example.android.searchimg.ui.base;

/**
 * Created by Yomna on 11/21/2016.
 */
public interface Presenter<V extends BaseView> {

    void attachView(V baseView);

    void detachView();
}

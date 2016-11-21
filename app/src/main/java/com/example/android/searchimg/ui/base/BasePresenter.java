package com.example.android.searchimg.ui.base;

/**
 * Created by Yomna on 11/21/2016.
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {
    private T mBaseView;

    @Override
    public void attachView(T baseView) {
        mBaseView = baseView;
    }

    @Override
    public void detachView() {
        mBaseView = null;
    }

    public boolean isViewAttached(){ return mBaseView != null;}

    public T getBaseView() {return mBaseView;}

    public void checkViewAttached(){
        if(!isViewAttached()) throw new BaseViewNotAttachedException();
    }
    public static class BaseViewNotAttachedException extends RuntimeException{
        public BaseViewNotAttachedException(){
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}

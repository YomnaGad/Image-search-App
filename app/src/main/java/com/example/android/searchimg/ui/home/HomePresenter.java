package com.example.android.searchimg.ui.home;

import android.util.Log;

import com.example.android.searchimg.data.DataManager;
import com.example.android.searchimg.data.model.Response;
import com.example.android.searchimg.utils.GlobalEntities;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Yomna on 11/30/2016.
 */
public class HomePresenter {
    private HomeBaseView view;
    private DataManager dataManager;
    private Subscription mSubscription;

    ArrayList<String> list ;
    public HomePresenter(HomeBaseView view, DataManager dataManager){
        this.view = view;
        this.dataManager = dataManager;
    }



    public void retrieveImageBySearch(String token, String search){
        mSubscription = dataManager.retrieveImages(token,search)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response>() {


                /*    @Override
                    public void onNext(Result<Response> responseResult) {
                        Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:"+responseResult.isError());
                        if(responseResult.isError()){
                            Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:"+responseResult.error());
                            Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:"+responseResult.response().code());
                        }else{
                            Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:"+ responseResult.response().raw().toString());
                            if(responseResult.response().code()== 401){
                                Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:"+ responseResult.response().body().detail);
                            }

                        }


                    }*/
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onError:"+e.getMessage());
                        view.tokenExpiredError();

                    }

                    @Override
                    public void onNext(Response response) {
                       // Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:"+response.status);

                        if (response.status == 1){
                            Log.v(GlobalEntities.HOME_PRESENTER_TAG,response.images.toString()) ;
                            if(response.images.size()!=0)
                                view.updateAdapter(response.images);
                        }
                        else  {
                            Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:" + response.detail);
                        }
                    }
                });
    }
    public void uploadImage(String token, MultipartBody.Part image){
        mSubscription = dataManager.upload(token, image)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onError:"+e.getMessage());
                        Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onError:"+e.getStackTrace());

                        view.tokenExpiredError();
                    }

                    @Override
                    public void onNext(Response response) {
                        Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:"+response.status);
                        if (response.status == 1){
                            view.homeSuccess();
                           Log.v(GlobalEntities.HOME_PRESENTER_TAG,response.image.getImage().toString()) ;
                        }
                        else {
                            Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:"+response.status);
                            Log.v(GlobalEntities.HOME_PRESENTER_TAG,response.image.getImage().toString()) ;
                        }
                       /* if(response.image.getImage().toString()!=null ){
                          //  Log.i(GlobalEntities.HOME_PRESENTER_TAG,response.image.getImage().toString());
                           // view.homeError("not sumbit");
                        }
                        else {

                            view.homeSuccess();
                        }*/

                    }
                });
    }


}

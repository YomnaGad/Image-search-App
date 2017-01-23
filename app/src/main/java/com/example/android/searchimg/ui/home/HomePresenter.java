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


    public void loadImages(){
        list = new ArrayList<>() ;

        list.addAll(DataManager.getInstance(null, null, null, null).imageNameList());
        if(list!=null) {
            Log.v("imagename", "true");
        }
        view.userImages(list);
    }
    public void uploadImage(MultipartBody.Part image){
        mSubscription = dataManager.upload( image)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
              /* .subscribe(new Action1<Response>() {
                   @Override
                   public void call(Response responseBody) {

                           Log.v("ssssssssssss", "call: "+"dddd");
                           Log.i(GlobalEntities.HOME_PRESENTER_TAG, "call: "+responseBody.toString());

                   }
               });*/
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onError:"+e.getMessage());
                        Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onError:"+e.getStackTrace());

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:"+response.status);
                        if (response.status == 1){
                            view.homeSuccess();
                           Log.v(GlobalEntities.HOME_PRESENTER_TAG,response.data.getImage().toString()) ;
                        }
                        else {
                            Log.i(GlobalEntities.HOME_PRESENTER_TAG, "onNext:"+response.status);
                            Log.v(GlobalEntities.HOME_PRESENTER_TAG,response.data.getImage().toString()) ;
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

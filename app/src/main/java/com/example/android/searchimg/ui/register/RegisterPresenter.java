package com.example.android.searchimg.ui.register;

import android.util.Log;

import com.example.android.searchimg.data.DataManager;
import com.example.android.searchimg.data.model.Request;
import com.example.android.searchimg.data.model.Response;
import com.example.android.searchimg.utils.GlobalEntities;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Yomna on 11/22/2016.
 */
public class RegisterPresenter {
    private RegisterBaseView view;
    private DataManager dataManager;
    private Subscription mSubscription;
    public RegisterPresenter(RegisterBaseView view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;

    }

    public void onRegisterClicked(Request userRequest) {
        mSubscription = dataManager.createUser(userRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
//               .subscribe(new Action1<ResponseBody>() {
//                   @Override
//                   public void call(ResponseBody responseBody) {
//                       try {
//                           Log.i(GlobalEntities.LOGIN_PRESENTER_TAG, "call: "+responseBody.string());
//                       } catch (IOException e) {
//                           e.printStackTrace();
//                       }
//                   }
//               });
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(GlobalEntities.REGISTER_PRESENTER_TAG, "onError:"+e.getMessage());
                        Log.i(GlobalEntities.REGISTER_PRESENTER_TAG, "onError:"+e.getStackTrace());
                        view.registerError(e.getMessage());
                    }

                    @Override
                    public void onNext(Response response) {
                        Log.i(GlobalEntities.REGISTER_PRESENTER_TAG, "onNext:"+response.status);
                        if(response.status == 1){
                            view.registerSuccess(response.user);
                        }
                        else {
                            view.registerError("wrong user");
                        }

                    }
                });

    }


}

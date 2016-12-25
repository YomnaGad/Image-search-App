package com.example.android.searchimg.ui.login;

import android.util.Log;

import com.example.android.searchimg.data.DataManager;
import com.example.android.searchimg.data.model.Response;
import com.example.android.searchimg.data.model.User;
import com.example.android.searchimg.utils.GlobalEntities;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Yomna on 11/21/2016.
 */
public class LoginPresenter {
    private LoginBaseView view;
    private DataManager dataManager;
    private Subscription mSubscription;

    public LoginPresenter(LoginBaseView view, DataManager dataManager){
        this.view = view;
        this.dataManager = dataManager;
    }

    public void login(User user){
       mSubscription = dataManager.login(user)
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
                       Log.i(GlobalEntities.LOGIN_PRESENTER_TAG, "onError:"+e.getMessage());
                       Log.i(GlobalEntities.LOGIN_PRESENTER_TAG, "onError:"+e.getStackTrace());
                       view.loginError(e.getMessage());
                   }

                   @Override
                   public void onNext(Response response) {
                       Log.i(GlobalEntities.LOGIN_PRESENTER_TAG, "onNext:"+response.status);
                        if(response.status == 2){
                            view.loginSuccess(response.data);
                        }
                        else {
                            view.loginError("wrong user");
                        }

                   }
               });


    }
}


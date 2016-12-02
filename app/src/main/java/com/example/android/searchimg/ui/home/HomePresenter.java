package com.example.android.searchimg.ui.home;

import android.util.Log;

import com.example.android.searchimg.data.DataManager;

import java.util.ArrayList;


/**
 * Created by Yomna on 11/30/2016.
 */
public class HomePresenter {
    private HomeBaseView view;
    private DataManager dataManager;
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

}

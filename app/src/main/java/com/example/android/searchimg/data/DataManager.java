package com.example.android.searchimg.data;

import android.content.Context;
import android.util.Log;

import com.example.android.searchimg.data.local.DatabaseHelper;
import com.example.android.searchimg.data.local.PreferencesHelper;
import com.example.android.searchimg.data.remote.Service;
import com.example.android.searchimg.utils.GlobalEntities;

import java.util.ArrayList;

/**
 * Created by Yomna on 11/21/2016.
 */
public class DataManager {
    private static DataManager dataManager;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final Service mService;
    private final Context mContext;

    private DataManager(Context context, Service mService, DatabaseHelper mDatabaseHelper, PreferencesHelper mPreferencesHelper) {
        Log.i(GlobalEntities.DATA_MANAGER_TAG, "DataManager: Created");
        mContext = context;
        this.mService = mService;
        this.mDatabaseHelper = mDatabaseHelper;
        this.mPreferencesHelper = mPreferencesHelper;
    }


    public static DataManager getInstance(Context context, Service mService, DatabaseHelper mDatabaseHelper, PreferencesHelper mPreferencesHelper){
        if(dataManager == null){
            dataManager = new DataManager(context, mService, mDatabaseHelper, mPreferencesHelper);
        }

        return dataManager;
    }

    public static boolean isNull(){
        return dataManager==null;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public boolean login(String email, String password) {
        if(email.equals("yomna_gad@hotmail.com") & password.equals("yomna2210")) {
            return true;
        }
        else
            return false;
    }

    public boolean Register(String username, String email, String password) {
        return true;
    }

    // creating dummy data for recyclerview for album in home page
    public ArrayList<String> imageNameList(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("Ali");
        list.add("Yomna");
        list.add("Aziz");
        list.add("Hadeer");
        list.add("Ayah");
        return list;
    }
}

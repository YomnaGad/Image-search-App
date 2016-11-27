package com.example.android.searchimg.data;

/**
 * Created by Yomna on 11/21/2016.
 */
public class DataManager {
    private static DataManager dataManager;




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
}

package com.example.android.searchimg.data.local;

import android.util.Log;

import com.example.android.searchimg.utils.GlobalEntities;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import rx.schedulers.Schedulers;

/**
 * Created by Yomna on 11/27/2016.
 */
public class DatabaseHelper {
    private static DatabaseHelper databaseHelper;
    private final BriteDatabase mDB;


    private DatabaseHelper(DbOpenHelper dbOpenHelper){
        Log.i(GlobalEntities.DATABASE_HELPER_TAG, "DatabaseHelper: created");
        mDB = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, Schedulers.io());
    }

    public static DatabaseHelper getInstance(DbOpenHelper dbOpenHelper){
        if(databaseHelper == null){
            databaseHelper = new DatabaseHelper(dbOpenHelper);
        }

        return databaseHelper;
    }

    public static boolean isNull(){
        return databaseHelper==null;
    }

    public BriteDatabase getBriteDb(){
        return mDB;
    }

}

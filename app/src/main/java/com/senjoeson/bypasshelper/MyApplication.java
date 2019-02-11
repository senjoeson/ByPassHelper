package com.senjoeson.bypasshelper;

import android.app.Application;

import com.senjoeson.bypasshelper.base.AppDatabase;

import androidx.room.Room;

/**
 * @author sunqiao
 * @date 2019/1/1 23:31
 */
public class MyApplication extends Application {


    public static AppDatabase mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppDatabase = Room.databaseBuilder(this, AppDatabase.class, "smsModel.db").build();
    }


}

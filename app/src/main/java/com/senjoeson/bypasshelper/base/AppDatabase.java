package com.senjoeson.bypasshelper.base;

import com.senjoeson.bypasshelper.dao.SmsModelDao;
import com.senjoeson.bypasshelper.model.SmsModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SmsModel.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SmsModelDao getSmsModelDao();
}
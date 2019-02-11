package com.senjoeson.bypasshelper.dao;

import com.senjoeson.bypasshelper.model.SmsModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * @author sunqiao
 * @date 2019/1/1 22:56
 */
@Dao
public interface SmsModelDao {

    @Query("select  * from SmsModel")
    public List<SmsModel> getAllSmsModel();


    @Query("SELECT * FROM smsmodel WHERE id IN (:id)")
    List<SmsModel> findSmsModelById(int[] id);

    @Insert
    Long insert(SmsModel smsModel);
}


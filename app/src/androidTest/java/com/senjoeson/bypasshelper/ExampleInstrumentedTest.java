package com.senjoeson.bypasshelper;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.senjoeson.bypasshelper", appContext.getPackageName());
        AppDatabase appDatabase = Room.databaseBuilder(appContext, AppDatabase.class, "smsModel").build();
        SmsModelDao smsModelDao = appDatabase.getSmsModelDao();
        SmsModel smsModel = new SmsModel();
        smsModel.setPhoneNumber("11111111");
        smsModel.setMsgContent("11111111");
        smsModel.setRevTime("11111111");
        smsModel.setNoticeName("11111111");
        smsModel.setNoticePhoneNumber("11111111");
        smsModel.setId(1);
        smsModelDao.insert(smsModel);


        System.out.println(smsModelDao.getAllSmsModel());


    }
}

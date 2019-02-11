package com.senjoeson.bypasshelper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.senjoeson.bypasshelper.model.SmsModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = "SmsReceiver";

    public static final String EMAIL_189_CENTER = "106591891";  //来自电信短信的号码
    public static final String EMAIL_139CENTER = "10658139";//来自移动短信的号码

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("监听到来短信");
        Bundle bundle = intent.getExtras();
        SmsMessage msg = null;
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            if (smsObj != null) {
                for (Object object : smsObj) {
                    msg = SmsMessage.createFromPdu((byte[]) object);
                    Date date = new Date(msg.getTimestampMillis());//时间
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
                    String receiveTime = format.format(date);

                    SmsModel smsModel = new SmsModel();
                    smsModel.setPhoneNumber(msg.getOriginatingAddress());
                    smsModel.setMsgContent(msg.getDisplayMessageBody());
                    smsModel.setRevTime(receiveTime);
                    checkSmsValid(context, smsModel);
                }
            }
        }
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param context  上下文
     * @param phoneNum 电话号码
     */
    public void takePhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 检查号码是否是来自抢票软件自动推送的消息
     *
     * @param context
     * @param smsModel
     */
    public void checkSmsValid(Context context, SmsModel smsModel) {
        switch (smsModel.getPhoneNumber()) {
            case EMAIL_139CENTER:
            case EMAIL_189_CENTER:
                if (smsModel.getMsgContent().contains("分流通知")) {
                    takePhone(context, smsModel.getNoticePhoneNumber());
                }
        }
    }
}

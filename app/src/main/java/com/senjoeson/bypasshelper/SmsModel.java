package com.senjoeson.bypasshelper;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author sunqiao
 * @date 2018/12/23 16:38
 */
@Entity
public class SmsModel implements Serializable {

    @PrimaryKey
    @NonNull
    private long id;
    private String phoneNumber;
    private String msgContent;
    private String revTime;


    private String noticeName;//通知人
    private String noticePhoneNumber;  //通知人的电话

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getRevTime() {
        return revTime;
    }

    public void setRevTime(String revTime) {
        this.revTime = revTime;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public String getNoticePhoneNumber() {
        return noticePhoneNumber;
    }

    public void setNoticePhoneNumber(String noticePhoneNumber) {
        this.noticePhoneNumber = noticePhoneNumber;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SmsModel{");
        sb.append("phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", msgContent='").append(msgContent).append('\'');
        sb.append(", revTime='").append(revTime).append('\'');
        sb.append(", noticeName='").append(noticeName).append('\'');
        sb.append(", noticePhoneNumber='").append(noticePhoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

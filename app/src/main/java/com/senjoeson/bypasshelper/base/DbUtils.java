package com.senjoeson.bypasshelper.base;

import com.senjoeson.bypasshelper.MyApplication;
import com.senjoeson.bypasshelper.dao.SmsModelDao;

/**
 * <pre>
 *     author : sunqiao
 *     e-mail : sunqiao@kayak.com.cn
 *     time   : 2019/02/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class DbUtils {


    public static SmsModelDao getSmsModelDao() {
        return MyApplication.mAppDatabase.getSmsModelDao();
    }
}

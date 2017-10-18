package com.yuandong.db;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by yuandong on 2017/10/11.
 */

public class DbApplication extends Application {

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this.getApplicationContext();
        Stetho.initializeWithDefaults(this);
    }
}

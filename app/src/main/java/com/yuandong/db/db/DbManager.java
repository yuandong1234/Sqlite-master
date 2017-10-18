package com.yuandong.db.db;

import android.database.sqlite.SQLiteDatabase;

import com.yuandong.db.DbApplication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yuandong on 2017/10/11.
 */

public class DbManager {
    public static String TAG = DbManager.class.getSimpleName();

    private AtomicInteger mOpenCounter = new AtomicInteger();

    private int count;
    private static DBHelper dbHelper;
    private SQLiteDatabase mDatabase;

    private DbManager() {
        dbHelper = new DBHelper(DbApplication.mContext);
    }

    private static class Holder {
        private static DbManager instance = new DbManager();
    }

    //TODO 以静态内部类的方式获得DbManager的单例
    public static DbManager getInstance() {
        return Holder.instance;
    }

    //打开数据库
    public synchronized  SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            mDatabase = dbHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    //关闭数据库
    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            mDatabase.close();
        }
    }
}
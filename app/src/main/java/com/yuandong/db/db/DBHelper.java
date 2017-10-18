package com.yuandong.db.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yuandong on 2017/10/11.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG =DBHelper.class.getSimpleName() ;

    private static final String DB_NAME = "test_db";//数据库名字
    private static final int DB_VERSION = 1;   // 数据库版本

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        String userSql = "create table if not exists " + UserDao.USER_TABLE_NAME + " (id integer primary key autoincrement, name text, age integer, sex text)";
        String jobSql="create table if not exists "+JobDao.JOB_TABLE_NAME+" (id integer primary key autoincrement, job_name text, job_salary integer, job_grade integer)";
        try {
            db.execSQL(userSql);
            db.execSQL(jobSql);
        } catch (SQLException e) {
            Log.e(TAG, "onCreate tab Error :" + e.toString());
            return;
        }

    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

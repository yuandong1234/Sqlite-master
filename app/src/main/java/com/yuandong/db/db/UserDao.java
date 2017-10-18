package com.yuandong.db.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yuandong.db.entity.User;
import java.util.ArrayList;

/**
 * 用户表
 * Created by yuandong on 2017/10/11.
 */

public class UserDao {

    private static String TAG = UserDao.class.getSimpleName();

    public static String USER_TABLE_NAME = "t_user";// 用户表名

    /**
     * 插入用户
     *
     * @param users
     */
    public static synchronized void insertUser(ArrayList<User> users) {
        Log.e(TAG,System.currentTimeMillis()+"  " +Thread.currentThread().toString()+"  insert user data...");
        SQLiteDatabase db = DbManager.getInstance().openDatabase();
        db.beginTransaction();
        String sql = "insert into " + USER_TABLE_NAME + "(name,age,sex) values(?,?,?)";
        try {
            for (User user : users) {
                db.execSQL(sql, new Object[]{user.getName(), user.getAge(), user.getSex()});
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            DbManager.getInstance().closeDatabase();
            Log.e(TAG,System.currentTimeMillis()+"  " +Thread.currentThread().toString()+" insert user success ");
        }
    }

    /**
     * 查询用户
     */
    public static synchronized ArrayList<User> queryUser() {
        Log.e(TAG,"query  user data....");
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = DbManager.getInstance().openDatabase();
        Cursor cursor = null;
        String sql = "select * from " + USER_TABLE_NAME;
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                User user = new User();
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                users.add(user);
            }
            Log.e(TAG," user data size :"+users.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            DbManager.getInstance().closeDatabase();
            Log.e(TAG,"query data end time :"+System.currentTimeMillis()+"   current thread :"+Thread.currentThread().toString());
        }
        return users;
    }

    /**
     * 获得全部用户数量
     * @return
     */
    public static  int queryUserCount(){
        SQLiteDatabase db = DbManager.getInstance().openDatabase();
        Cursor cursor = null;
        int size=0;
        String sql = "select count (*) from " + USER_TABLE_NAME;
        try{
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                size = cursor.getInt(0);
                Log.e(TAG,"数据总条数 :" + size);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
               cursor.close();
            }
            DbManager.getInstance().closeDatabase();
        }
        return size;
    }

    /**
     * 查询一定数量的用户
     */
    public static synchronized ArrayList<User> queryUserBySize(int index) {
        Log.e(TAG,"query  user data from :"+index);
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = DbManager.getInstance().openDatabase();
        Cursor cursor = null;
        String sql = "select * from " + USER_TABLE_NAME+" limit ?,100";
        try {
            cursor = db.rawQuery(sql, new String[]{String.valueOf(index)});
            while (cursor.moveToNext()) {
                User user = new User();
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                users.add(user);
            }
            //Log.e(TAG," user data size :"+users.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            DbManager.getInstance().closeDatabase();
           // Log.e(TAG,"query data end time :"+System.currentTimeMillis()+"   current thread :"+Thread.currentThread().toString());
        }
        return users;
    }

}

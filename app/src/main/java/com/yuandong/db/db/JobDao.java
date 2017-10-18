package com.yuandong.db.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.yuandong.db.entity.Job;
import java.util.ArrayList;

/**
 * Created by yuandong on 2017/10/16.
 */

public class JobDao {
    private static String TAG = JobDao.class.getSimpleName();

    public static String JOB_TABLE_NAME = "t_job";// 职位表名

    /**
     * 插入职位信息
     *
     * @param jobs
     */
    public static synchronized void insertJob(ArrayList<Job> jobs) {
        Log.e(TAG, System.currentTimeMillis() + "  "+Thread.currentThread().toString() + " insert job data...");
        SQLiteDatabase db = DbManager.getInstance().openDatabase();
        db.beginTransaction();
        String sql = "insert into " + JOB_TABLE_NAME + "(job_name,job_salary,job_grade) values(?,?,?)";
        try {
            for (Job user : jobs) {
                db.execSQL(sql, new Object[]{user.getJobName(), user.getSalary(), user.getGrade()});
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            DbManager.getInstance().closeDatabase();
            Log.e(TAG, System.currentTimeMillis() + "  " + Thread.currentThread().toString() + " insert job success ");
        }
    }

    /**
     * 查询职位
     *
     * @return
     */
    public static synchronized ArrayList<Job> queryJobs() {
        Log.e(TAG, "query  job data....");
        ArrayList<Job> jobs = new ArrayList<>();
        SQLiteDatabase db = DbManager.getInstance().openDatabase();
        Cursor cursor = null;
        String sql = "select * from " + JOB_TABLE_NAME;
        try {
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Job job = new Job();
                job.setJobName(cursor.getString(cursor.getColumnIndex("job_name")));
                job.setSalary(cursor.getInt(cursor.getColumnIndex("job_salary")));
                job.setGrade(cursor.getInt(cursor.getColumnIndex("job_grade")));
                jobs.add(job);
            }
            Log.e(TAG, " user data size :" + jobs.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            DbManager.getInstance().closeDatabase();
            Log.e(TAG, "query data end time :" + System.currentTimeMillis() + "   current thread :" + Thread.currentThread().toString());
        }
        return jobs;
    }

    /**
     * 获得全部职位数量
     *
     * @return
     */
    public static int queryJobCount() {
        SQLiteDatabase db = DbManager.getInstance().openDatabase();
        Cursor cursor = null;
        int size = 0;
        String sql = "select count (*) from " + JOB_TABLE_NAME;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                size = cursor.getInt(0);
                Log.e(TAG, "数据总条数 :" + size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            DbManager.getInstance().closeDatabase();
        }
        return size;
    }

    /**
     * 查询一定数量的职位
     */
    public static ArrayList<Job> queryJobBySize(int index) {
        Log.e(TAG, "query  job data from :" + index);
        ArrayList<Job> jobs = new ArrayList<>();
        SQLiteDatabase db = DbManager.getInstance().openDatabase();
        Cursor cursor = null;
        String sql = "select * from " + JOB_TABLE_NAME + " limit ?,100";
        try {
            cursor = db.rawQuery(sql, new String[]{String.valueOf(index)});
            while (cursor.moveToNext()) {
                Job job = new Job();
                job.setJobName(cursor.getString(cursor.getColumnIndex("job_name")));
                job.setSalary(cursor.getInt(cursor.getColumnIndex("job_salary")));
                job.setGrade(cursor.getInt(cursor.getColumnIndex("job_grade")));
                jobs.add(job);
            }
            //Log.e(TAG," job data size :"+users.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            DbManager.getInstance().closeDatabase();
            // Log.e(TAG,"query data end time :"+System.currentTimeMillis()+"   current thread :"+Thread.currentThread().toString());
        }
        return jobs;
    }
}

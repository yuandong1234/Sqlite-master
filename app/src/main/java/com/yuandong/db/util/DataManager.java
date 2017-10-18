package com.yuandong.db.util;

import android.util.Log;

import com.yuandong.db.db.JobDao;
import com.yuandong.db.db.UserDao;
import com.yuandong.db.entity.Job;
import com.yuandong.db.entity.User;

import java.util.ArrayList;

/**
 * Created by yuandong on 2017/10/16.
 */

public class DataManager {

    private static String TAG = DataManager.class.getSimpleName();

    private DataManager() {

    }

    private static class Holder {
        private static DataManager instance = new DataManager();
    }

    public static DataManager getInstance() {
        return DataManager.Holder.instance;
    }


       //这个插入用户数据方法
    public void insertUserData(final ArrayList<User> users){
        ExecutorManager.getInstance().executor(new Runnable() {
            @Override
            public void run() {
                UserDao.insertUser(users);
            }
        });
    }

    //这个处理用户数据方法
    public void queryUserData() {
        int userGroup = groupSize(UserDao.queryUserCount());
        Log.e("USER"," user group size :"+userGroup);
        for (int i = 0; i < userGroup; i++) {
            final int n = i;
            ExecutorManager.getInstance().executor(new Runnable() {
                @Override
                public void run() {
                    ArrayList<User> users = UserDao.queryUserBySize(n * 100);
                    Log.e("USER", "the first  user name :" + users.get(0).getName());
                }
            });
        }
    }

    //这个插入职位数据方法
    public void insertJobData(final ArrayList<Job> jobs){
        ExecutorManager.getInstance().executor(new Runnable() {
            @Override
            public void run() {
                JobDao.insertJob(jobs);
            }
        });
    }


    //这个处理职位数据的方法
    public void queryJobData() {
        int jobGroup = groupSize(JobDao.queryJobCount());
        Log.e("JOB"," job group size :"+jobGroup);
        for (int i = 0; i < jobGroup; i++) {
            final int n = i;
            ExecutorManager.getInstance().executor(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Job> jobs = JobDao.queryJobBySize(n * 100);
                    Log.e("JOB", "the first  job name :" + jobs.get(0).getJobName());
                }
            });
        }

    }



    /**
     * 获得分批组数(每组100)
     *
     * @return
     */
    private int groupSize(int size) {
        int group;
        if (size % 100 == 0) {
            group = size / 100;
        } else {
            group = (size - size % 100) / 100 + 1;
        }
        return group;
    }
}

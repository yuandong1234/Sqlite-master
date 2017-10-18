package com.yuandong.db;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yuandong.db.db.JobDao;
import com.yuandong.db.db.UserDao;
import com.yuandong.db.entity.Job;
import com.yuandong.db.entity.User;
import com.yuandong.db.util.DataManager;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Button tv_insert;
    private Button tv_query;
    private Button tv_insert_job;
    private Button tv_query_job;
    private Button tv_query_mass;
    private Button tv_insert_mass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_insert = (Button) findViewById(R.id.tv_insert);
        tv_query = (Button) findViewById(R.id.tv_query);
        tv_insert_job = (Button) findViewById(R.id.tv_insert_job);
        tv_query_job = (Button) findViewById(R.id.tv_query_job);
        tv_query_mass = (Button) findViewById(R.id.tv_query_mass);
        tv_insert_mass = (Button) findViewById(R.id.tv_insert_mass);

        tv_insert.setOnClickListener(this);
        tv_query.setOnClickListener(this);
        tv_insert_job.setOnClickListener(this);
        tv_query_job.setOnClickListener(this);
        tv_query_mass.setOnClickListener(this);
        tv_insert_mass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_insert:
                insertUser();
                break;
            case R.id.tv_query:
                queryUsers();
                break;
            case R.id.tv_insert_job:
                insertJobs();
                break;
            case R.id.tv_query_job:
                queryJobs();
                break;
            case R.id.tv_query_mass:
                massQuery();
                break;
            case R.id.tv_insert_mass:
                massInsert();
                break;
        }
    }

    //插入用户
    private void insertUser() {
        ArrayList<User> users = createUserData(1000);
        UserDao.insertUser(users);
    }

    //查询用户
    private void queryUsers() {
        ArrayList<User> users = UserDao.queryUser();
    }

    //插入职位
    private void insertJobs() {
        ArrayList<Job> jobs = createJobData(1000);
        JobDao.insertJob(jobs);
    }

    //查询职位
    private void queryJobs() {
        JobDao.queryJobs();
    }

    //多线程查询
    private void massQuery() {
        DataManager.getInstance().queryUserData();
        DataManager.getInstance().queryJobData();
    }

    /**
     * 多线程插入
     */
    public void massInsert(){
        ArrayList<User> users = createUserData(500);
        DataManager.getInstance().insertUserData(users);
        ArrayList<Job> jobs=createJobData(500);
        DataManager.getInstance().insertJobData(jobs);
    }


    /**
     * 造用户数据
     * @param size
     * @return
     */
    private ArrayList<User> createUserData(int size){
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int n = new Random().nextInt(100);
            User user = new User();
            user.setName("袁栋" + i);
            user.setAge(n + 18);
            user.setSex(n / 2 == 0 ? "男" : "女");
            users.add(user);
        }
        return users;
    }

    /**
     * 造职位数据
     * @param size
     * @return
     */
    private ArrayList<Job> createJobData(int size){
        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Job job = new Job();
            job.setJobName("android " + i);
            job.setSalary(12000);
            job.setGrade(2);
            jobs.add(job);
        }
        return jobs;
    }
}


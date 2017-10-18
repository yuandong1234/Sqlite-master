package com.yuandong.db.entity;

/**
 * Created by yuandong on 2017/10/16.
 */

public class Job {
    private  String JobName;
    private int salary;
    private int  grade;


    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Job{" +
                "JobName='" + JobName + '\'' +
                ", salary=" + salary +
                ", grade='" + grade + '\'' +
                '}';
    }
}

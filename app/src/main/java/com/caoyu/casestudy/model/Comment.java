package com.caoyu.casestudy.model;

import org.litepal.crud.DataSupport;

/**
 * Created by caoyu on 2017/4/8.
 */

public class Comment extends DataSupport {
    private int id;
    private int taskId;
    private int projectId;
    private String projectName;
    private String taskName;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}

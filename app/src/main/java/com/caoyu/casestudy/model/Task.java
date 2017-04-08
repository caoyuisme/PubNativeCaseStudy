package com.caoyu.casestudy.model;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by caoyu on 2017/4/8.
 */

public class Task extends DataSupport {
    private int id;
    private int projectId;
    private String projectName;
    private String title;
    private String description;
    private long createDate;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}

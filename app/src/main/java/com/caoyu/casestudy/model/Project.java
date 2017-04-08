package com.caoyu.casestudy.model;

import org.litepal.crud.DataSupport;

/**
 * Created by caoyu on 2017/4/8.
 */

public class Project extends DataSupport {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

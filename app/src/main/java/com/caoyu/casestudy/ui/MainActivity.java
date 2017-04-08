package com.caoyu.casestudy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.caoyu.casestudy.R;
import com.caoyu.casestudy.adapter.ProjectAdapter;
import com.caoyu.casestudy.model.Project;

import org.litepal.crud.DataSupport;

import java.util.List;

import utils.ConstantParams;
import utils.Utils;

public class MainActivity extends BaseListActivity {

    private ProjectAdapter projectAdapter;
    private List<Project> mProjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectAdapter = new ProjectAdapter(this, itemOnClickListener);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(projectAdapter);
        mProjectList = DataSupport.findAll(Project.class);
        projectAdapter.updateDatas(mProjectList);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void addProjectOrTask() {
        if (Utils.ensureNotNull(nameEt)) {
            String projectName = nameEt.getText().toString();
            if (!Utils.isEmptyString(projectName)) {
                int index = 0;
                if (mProjectList.size() > 0) {
                    Project projectLast = mProjectList.get(mProjectList.size() - 1);
                    index = projectLast.getId() + 1;
                }
                Project project = new Project();
                project.setName(projectName);
                project.setId(index);
                boolean isSuccess = project.save();
                if (isSuccess) {
                    mProjectList.add(project);
                    projectAdapter.updateDatas(mProjectList);
                } else {
                    Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onItemClick(View v) {
        Project project = (Project) v.getTag(R.id.info_tag);
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(ConstantParams.PROJECT_ID, project.getId());
        this.startActivity(intent);
    }
}

package com.caoyu.casestudy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.caoyu.casestudy.R;
import com.caoyu.casestudy.adapter.TaskAdapter;
import com.caoyu.casestudy.model.Task;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.InjectView;
import utils.ConstantParams;
import utils.Utils;

/**
 * Created by caoyu on 2017/4/8.
 */

public class TaskActivity extends BaseListActivity {

    @InjectView(R.id.id_task_description_et)
    EditText taskDescriptionEt;

    private TaskAdapter taskAdapter;
    private List<Task> mTaskList;
    private int projectId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        projectId = intent.getIntExtra(ConstantParams.PROJECT_ID, -1);
        taskAdapter = new TaskAdapter(this, itemOnClickListener);
        if (Utils.ensureNotNull(list)) {
            list.setLayoutManager(new LinearLayoutManager(this));
            list.setAdapter(taskAdapter);
        }
        mTaskList = DataSupport.where("projectId=?", projectId + "").find(Task.class);
        taskAdapter.updateDatas(mTaskList);
    }

    @Override
    protected void addProjectOrTask() {
        if (Utils.ensureNotNull(nameEt, taskDescriptionEt)) {
            String taskName = nameEt.getText().toString();
            String taskDescription = taskDescriptionEt.getText().toString();
            if (!Utils.isEmptyString(taskName) && !Utils.isEmptyString(taskDescription)) {
                int index = 0;
                if (mTaskList.size() > 0) {
                    Task taskLast = mTaskList.get(mTaskList.size() - 1);
                    index = taskLast.getId() + 1;
                }
                Task task = new Task();
                task.setId(index);
                task.setTitle(taskName);
                task.setDescription(taskDescription);
                task.setProjectId(projectId);
                task.setCreateDate(System.currentTimeMillis());
                boolean isSuccess = task.save();
                if (isSuccess) {
                    mTaskList.add(task);
                    taskAdapter.updateDatas(mTaskList);
                } else {
                    Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "please input complete content", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onItemClick(View v) {
        Task task = (Task) v.getTag(R.id.info_tag);
        //TODO goto detail
        Intent intent = new Intent(this, TaskDetailActivity.class);
        intent.putExtra(ConstantParams.TASK_ID, task.getId());
        intent.putExtra(ConstantParams.TASK_TITLE, task.getTitle());
        intent.putExtra(ConstantParams.TASK_DESCRIPTION, task.getDescription());
        intent.putExtra(ConstantParams.TASK_TIME, task.getCreateDate());
        intent.putExtra(ConstantParams.PROJECT_ID, projectId);
        this.startActivity(intent);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_task;
    }
}

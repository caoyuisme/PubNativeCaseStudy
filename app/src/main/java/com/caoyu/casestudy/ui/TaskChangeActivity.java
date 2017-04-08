package com.caoyu.casestudy.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import com.caoyu.casestudy.R;
import com.caoyu.casestudy.model.Task;

import org.litepal.crud.DataSupport;

import butterknife.InjectView;
import butterknife.OnClick;
import utils.ConstantParams;
import utils.Utils;

/**
 * Created by caoyu on 2017/4/8.
 */

public class TaskChangeActivity extends BaseActivity {

    @InjectView(R.id.id_change_title_et)
    EditText changeTitleEt;
    @InjectView(R.id.id_change_desc_et)
    EditText changeDescEt;

    private String title;
    private String description;
    private int taskId;
    private int projectId;
    private boolean isChange = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        title = intent.getStringExtra(ConstantParams.TASK_TITLE);
        description = intent.getStringExtra(ConstantParams.TASK_DESCRIPTION);
        taskId = intent.getIntExtra(ConstantParams.TASK_ID, -1);
        projectId = intent.getIntExtra(ConstantParams.PROJECT_ID, -1);
        changeTitleEt.setText(title);
        changeDescEt.setText(description);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_task_change;
    }

    @OnClick(R.id.id_save_change)
    public void saveChange() {
        String newTitle = changeTitleEt.getText().toString();
        String newDescription = changeDescEt.getText().toString();
        ContentValues values = new ContentValues();
        if (!Utils.isEmptyString(newTitle) && !newTitle.equals(title)) {
            values.put("title", newTitle);
            isChange = true;
        }
        if (!Utils.isEmptyString(newDescription) && !newDescription.equals(description)) {
            values.put("description", newDescription);
            isChange = true;
        }
        if (isChange) {
            long changetime = System.currentTimeMillis();
            values.put("createDate",changetime);
            DataSupport.updateAll(Task.class, values, "projectId = ? and id = ?", projectId + "", taskId + "");
            Intent intent = new Intent();
            intent.putExtra(ConstantParams.TASK_TITLE, newTitle);
            intent.putExtra(ConstantParams.TASK_DESCRIPTION, newDescription);
            intent.putExtra(ConstantParams.TASK_TIME,changetime);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "please input or change content", Toast.LENGTH_SHORT).show();

        }

    }


}

package com.caoyu.casestudy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.caoyu.casestudy.R;
import com.caoyu.casestudy.adapter.CommentAdapter;
import com.caoyu.casestudy.adapter.ProjectAdapter;
import com.caoyu.casestudy.model.Comment;
import com.caoyu.casestudy.model.Project;
import com.caoyu.casestudy.model.Task;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import utils.ConstantParams;
import utils.Utils;

/**
 * Created by caoyu on 2017/4/8.
 */

public class TaskDetailActivity extends BaseActivity {

    @InjectView(R.id.id_task_title_tv)
    TextView taskTitleTv;
    @InjectView(R.id.id_task_description_tv)
    TextView taskDescriptionTv;
    @InjectView(R.id.id_task_createdate_tv)
    TextView taskCreateDateTv;
    @InjectView(R.id.id_comment_list)
    RecyclerView commentList;
    @InjectView(R.id.id_task_comment_et)
    EditText taskCommentEt;

    public static int CHANGE_TASK_DETAIL = 0;
    private CommentAdapter commentAdapter;
    private String title = "";
    private String description = "";
    private long createDate = 0;
    private int taskId;
    private int projectId;
    private List<Comment> mCommentList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initViews();
        commentAdapter = new CommentAdapter(this, itemOnClickListener);
        commentList.setLayoutManager(new LinearLayoutManager(this));
        commentList.setAdapter(commentAdapter);
        mCommentList = DataSupport.where("taskId = ? and projectId = ?", taskId + "", projectId + "").find(Comment.class);
        commentAdapter.updateDatas(mCommentList);
    }

    private void initData() {
        Intent intent = getIntent();
        title = intent.getStringExtra(ConstantParams.TASK_TITLE);
        description = intent.getStringExtra(ConstantParams.TASK_DESCRIPTION);
        createDate = intent.getLongExtra(ConstantParams.TASK_TIME, 0);
        taskId = intent.getIntExtra(ConstantParams.TASK_ID, -1);
        projectId = intent.getIntExtra(ConstantParams.PROJECT_ID, -1);
    }

    private void initViews() {
        taskTitleTv.setText(title);
        taskDescriptionTv.setText(description);
        taskCreateDateTv.setText(Utils.getMmDdHhMm(createDate) + "");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_task_detail;
    }

    private View.OnClickListener itemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO we can delete or do something else
        }
    };

    @OnClick(R.id.id_add_comment)
    public void addComment() {
        String commentStr = taskCommentEt.getText().toString();
        if (!Utils.isEmptyString(commentStr)) {
            int index = 0;
            if (mCommentList.size() > 0) {
                Comment commetnLast = mCommentList.get(mCommentList.size() - 1);
                index = commetnLast.getId() + 1;
            }
            Comment comment = new Comment();
            comment.setId(index);
            comment.setTaskId(taskId);
            comment.setProjectId(projectId);
            comment.setComment(commentStr);
            boolean isSuccess = comment.save();
            if (isSuccess) {
                mCommentList.add(comment);
                commentAdapter.updateDatas(mCommentList);
            } else {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "please input complete content", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.id_change_btn)
    public void changeTaskDetail() {
        Intent intent = new Intent(this, TaskChangeActivity.class);
        intent.putExtra(ConstantParams.TASK_ID, taskId);
        intent.putExtra(ConstantParams.PROJECT_ID, projectId);
        intent.putExtra(ConstantParams.TASK_TITLE, title);
        intent.putExtra(ConstantParams.TASK_DESCRIPTION, description);
        this.startActivityForResult(intent, CHANGE_TASK_DETAIL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CHANGE_TASK_DETAIL) {
            title = data.getStringExtra(ConstantParams.TASK_TITLE);
            description = data.getStringExtra(ConstantParams.TASK_DESCRIPTION);
            createDate = data.getLongExtra(ConstantParams.TASK_TIME,0);
            initViews();
        }
    }
}

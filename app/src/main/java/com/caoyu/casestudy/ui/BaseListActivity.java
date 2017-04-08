package com.caoyu.casestudy.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.caoyu.casestudy.R;
import com.caoyu.casestudy.adapter.ProjectAdapter;
import com.caoyu.casestudy.model.Project;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import utils.Utils;

/**
 * Created by caoyu on 2017/4/8.
 */

public abstract class BaseListActivity extends BaseActivity {

    @InjectView(R.id.id_list)
    RecyclerView list;
    @InjectView(R.id.id_name_et)
    EditText nameEt;

    @OnClick(R.id.id_add)
    public void addProject() {
        addProjectOrTask();

    }

    protected View.OnClickListener itemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onItemClick(v);

        }
    };

    protected abstract void onItemClick(View v);

    protected abstract void addProjectOrTask();

}

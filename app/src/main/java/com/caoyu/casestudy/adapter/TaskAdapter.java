package com.caoyu.casestudy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caoyu.casestudy.R;
import com.caoyu.casestudy.model.Task;

import butterknife.InjectView;
import utils.Utils;

/**
 * Created by caoyu on 2017/4/8.
 */

public class TaskAdapter extends BaseRecyclerAdapter<TaskAdapter.TaskViewHolder, Task> {

    public TaskAdapter(Context context, View.OnClickListener itemOnclickListener) {
        super(context, itemOnclickListener);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskViewHolder(mInflater.inflate(R.layout.item_project, parent, false));
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = getItem(position);
        holder.setView(task, getItemOnclickListener());
    }

    class TaskViewHolder extends BaseViewHolder {

        @InjectView(R.id.id_name_tv)
        TextView projectNameTv;
        @InjectView(R.id.id_root_view)
        LinearLayout rootView;


        public TaskViewHolder(View itemView) {
            super(itemView);
        }


        public void setView(Task task, View.OnClickListener itemOnClickListener) {
            if (Utils.ensureNotNull(task)) {
                projectNameTv.setText(task.getTitle());
                rootView.setTag(R.id.info_tag, task);
                rootView.setOnClickListener(itemOnClickListener);
            }
        }
    }
}

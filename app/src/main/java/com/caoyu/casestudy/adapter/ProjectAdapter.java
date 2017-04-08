package com.caoyu.casestudy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caoyu.casestudy.R;
import com.caoyu.casestudy.model.Project;


import butterknife.InjectView;
import utils.Utils;

/**
 * Created by caoyu on 2017/4/8.
 */

public class ProjectAdapter extends BaseRecyclerAdapter<ProjectAdapter.ProjectViewHolder, Project> {


    public ProjectAdapter(Context context, View.OnClickListener itemOnClickListener) {
        super(context, itemOnClickListener);
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectViewHolder(mInflater.inflate(R.layout.item_project, parent, false));
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        Project mProject = getItem(position);
        holder.setViews(mProject, getItemOnclickListener());
    }

    class ProjectViewHolder extends BaseViewHolder {

        @InjectView(R.id.id_name_tv)
        TextView projectNameTv;
        @InjectView(R.id.id_root_view)
        LinearLayout rootView;

        public ProjectViewHolder(View itemView) {
            super(itemView);
        }

        public void setViews(Project mProject, View.OnClickListener itemOnClickListener) {
            if (Utils.ensureNotNull(mProject) && !Utils.isEmptyString(mProject.getName())) {
                projectNameTv.setText(mProject.getName());
                rootView.setTag(R.id.info_tag, mProject);
                rootView.setOnClickListener(itemOnClickListener);
            }
        }
    }

}

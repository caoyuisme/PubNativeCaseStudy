package com.caoyu.casestudy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caoyu.casestudy.R;
import com.caoyu.casestudy.model.Comment;

import butterknife.InjectView;
import utils.Utils;

/**
 * Created by caoyu on 2017/4/8.
 */

public class CommentAdapter extends BaseRecyclerAdapter<CommentAdapter.CommentViewHolder, Comment> {


    public CommentAdapter(Context context, View.OnClickListener itemOnclickListener) {
        super(context, itemOnclickListener);
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(mInflater.inflate(R.layout.item_project, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = getItem(position);
        holder.setViews(comment, getItemOnclickListener());

    }

    class CommentViewHolder extends BaseViewHolder {
        @InjectView(R.id.id_name_tv)
        TextView projectNameTv;
        @InjectView(R.id.id_root_view)
        LinearLayout rootView;

        public CommentViewHolder(View itemView) {
            super(itemView);
        }

        public void setViews(Comment comment, View.OnClickListener itemOnClickListener) {
            if (Utils.ensureNotNull(comment) && !Utils.isEmptyString(comment.getComment())) {
                projectNameTv.setText(comment.getComment());
                rootView.setTag(R.id.info_tag, comment);
                rootView.setOnClickListener(itemOnClickListener);
            }
        }
    }
}

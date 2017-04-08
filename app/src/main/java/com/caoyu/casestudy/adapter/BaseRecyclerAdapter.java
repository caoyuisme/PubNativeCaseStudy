package com.caoyu.casestudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import utils.Utils;

/**
 * Created by caoyu on 2017/4/8.
 */

public abstract class BaseRecyclerAdapter<T extends RecyclerView.ViewHolder, V> extends RecyclerView.Adapter<T> {

    protected LayoutInflater mInflater;
    protected View.OnClickListener itemOnclickListener;

    public BaseRecyclerAdapter(Context context, View.OnClickListener itemOnclickListener) {
        mInflater = LayoutInflater.from(context);
        this.itemOnclickListener = itemOnclickListener;
    }

    public View.OnClickListener getItemOnclickListener() {
        return itemOnclickListener;
    }


    protected ArrayList<V> cacheDatas = new ArrayList<>();

    public V getItem(int position) {
        return cacheDatas.get(position);
    }

    @Override
    public int getItemCount() {
        return cacheDatas.size();
    }

    public void updateDatas(List<V> list) {
        updateDatas(list, false);
    }

    // 数据更新
    public void updateDatas(List<V> list, boolean isAppend) {
        if (isAppend) {
            if (!Utils.isEmptyCollection(list)) {
                int startIndex = getItemCount();
                cacheDatas.addAll(list);
                notifyItemRangeInserted(startIndex, list.size());
            }
        } else {
            cacheDatas.clear();
            if (!Utils.isEmptyCollection(list)) {
                cacheDatas.addAll(list);
            }
            notifyDataSetChanged();
        }
    }

    public void updateData(V v) {
        if (cacheDatas.contains(v)) {
            try {
                int position = cacheDatas.indexOf(v);
                notifyItemChanged(positionGet(position));
            } catch (Throwable e) {
            }
        }
    }

    private int positionGet(int dataPosition) {
        int fixPosition = dataPosition;
        return fixPosition;
    }
}

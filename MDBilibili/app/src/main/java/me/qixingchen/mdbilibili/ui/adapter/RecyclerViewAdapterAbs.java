package me.qixingchen.mdbilibili.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Yulan on 2016/1/21.
 * RecyclerViewAdapter 的抽象类，主要用于文档
 */
public abstract class RecyclerViewAdapterAbs extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 创建 ViewHolder
     *
     * @param parent   需要创建ViewHolder的 ViewGroup
     * @param viewType 记录类型
     * @return ViewHolder
     */
    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 发生绑定时，为viewHolder的元素赋值
     *
     * @param holder   被绑定的ViewHolder
     * @param position 列表位置
     */
    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * @return 记录数
     */
    @Override
    public abstract int getItemCount();

}
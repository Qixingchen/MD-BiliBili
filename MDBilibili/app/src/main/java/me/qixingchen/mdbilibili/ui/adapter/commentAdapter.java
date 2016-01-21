package me.qixingchen.mdbilibili.ui.adapter;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.databinding.VideoCommentBinding;
import me.qixingchen.mdbilibili.model.FeedbackM;

/**
 * Created by Yulan on 2016/1/21.
 */
public class commentAdapter extends RecyclerViewAdapterAbs {

    private SortedList<FeedbackM.ListEntity> comments;

    /**
     * 创建 ViewHolder
     *
     * @param parent   需要创建ViewHolder的 ViewGroup
     * @param viewType 记录类型
     * @return ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_comment, parent, false);
        return new CommentViewHolder(v);
    }

    /**
     * 发生绑定时，为viewHolder的元素赋值
     *
     * @param holder   被绑定的ViewHolder
     * @param position 列表位置
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentViewHolder) {
            CommentViewHolder vh = (CommentViewHolder) holder;
            vh.bind(comments.get(position));
        }
    }

    /**
     * @return 记录数
     */
    @Override
    public int getItemCount() {
        return 0;
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        private VideoCommentBinding binding;

        public CommentViewHolder(View itemView) {
            super(itemView);
            binding = VideoCommentBinding.bind(itemView);
        }

        private void bind(@NotNull FeedbackM.ListEntity comment) {
            binding.setComment(comment);
        }
    }
}

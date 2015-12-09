package me.qixingchen.mdbilibili.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.model.List;
import me.qixingchen.mdbilibili.ui.activity.BilibiliDetailActivity;
import me.qixingchen.mdbilibili.view.BilibiliCardViewHolder;

/**
 * Created by dell on 2015/6/15.
 */
public class CardAdapter extends RecyclerView.Adapter<BilibiliCardViewHolder> {

    private List list;
    private Context mContext;

    public CardAdapter(List list, Context context) {
        this.list = list;
        mContext = context;
    }

    @Override
    public BilibiliCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dast_bilibili_card_item, parent, false);
        BilibiliCardViewHolder vh = new BilibiliCardViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BilibiliCardViewHolder holder, final int position) {
        holder.textView.setText(list.list.get(position).title);
        Glide.with(mContext).load(list.list.get(position).pic).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BilibiliApplication.getApplication(), BilibiliDetailActivity.class);
                String url = list.list.get(position).pic;
                String title = list.list.get(position).title;
                int aid = list.list.get(position).aid;
                intent.putExtra("IMG_URL", url);
                intent.putExtra("TITLE", title);
                intent.putExtra("AID", aid);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.list.size();
    }

    //刷新数据
    public void notifyDateChanged(List list) {
        this.list = list;
        this.notifyDataSetChanged();
    }
}

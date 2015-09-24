package me.qixingchen.mdbilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.model.List;
import me.qixingchen.mdbilibili.network.GetVolley;
import me.qixingchen.mdbilibili.ui.BilibiliDetail;
import me.qixingchen.mdbilibili.view.BilibiliCardViewHolder;

/**
 * Created by dell on 2015/6/15.
 */
public class CardAdapter extends RecyclerView.Adapter<BilibiliCardViewHolder> {

    private List list;
    private ImageLoader mImageLoader;
    private Context mContext;

    public CardAdapter(List list, Context context) {
        this.list = list;
        mContext = context;
        mImageLoader = GetVolley.getmInstance(mContext).getImageLoader();
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
        holder.imageView.setImageUrl(list.list.get(position).pic, mImageLoader);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BilibiliApplication.getApplication(), BilibiliDetail.class);
                String url = String.valueOf(list.list.get(position).pic);
                String title = String.valueOf(list.list.get(position).title);
                String aid = String.valueOf(list.list.get(position).aid);
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

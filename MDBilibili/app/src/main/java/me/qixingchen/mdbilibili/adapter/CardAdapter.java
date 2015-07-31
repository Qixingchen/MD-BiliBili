package me.qixingchen.mdbilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import me.qixingchen.mdbilibili.view.BilibiliCardViewHolder;
import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.model.Recommend;
import me.qixingchen.mdbilibili.network.GetVolley;
import me.qixingchen.mdbilibili.ui.BilibiliDetail;

/**
 * Created by dell on 2015/6/15.
 */
public class CardAdapter extends RecyclerView.Adapter<BilibiliCardViewHolder> {

    private Recommend recommend;
    private ImageLoader mImageLoader;
    private Context mContext;

    public CardAdapter(Recommend recommend, Context context) {
        this.recommend = recommend;
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
        holder.textView.setText(recommend.getLists().get(position).getTitle());
        holder.imageView.setImageUrl(recommend.getLists().get(position).getPic(), mImageLoader);
        holder.rootView.setOnClickListener(v -> {
            Intent intent = new Intent(BilibiliApplication.getApplication(), BilibiliDetail.class);
            String url = String.valueOf(recommend.getLists().get(position).getPic());
            String title = String.valueOf(recommend.getLists().get(position).getTitle());
            String aid = String.valueOf(recommend.getLists().get(position).getAid());
            intent.putExtra("IMG_URL", url);
            intent.putExtra("TITLE", title);
            intent.putExtra("AID", aid);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recommend == null ? 0 : recommend.getLists().size();
    }

    //刷新数据
    public void notifyDateChanged(Recommend recommend) {
        this.recommend = recommend;
        this.notifyDataSetChanged();
    }
}

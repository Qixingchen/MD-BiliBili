package me.qixingchen.mdbilibili.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.model.List;
import me.qixingchen.mdbilibili.ui.activity.BilibiliDetailActivity;

/**
 * Created by dell on 2015/6/15.
 */
public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List list;
    private Context mContext;

    public CardAdapter(List list, Context context) {
        this.list = list;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dast_bilibili_card_item, parent, false);
        BilibiliCardViewHolder vh = new BilibiliCardViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder vh, final int position) {
        if (vh instanceof BilibiliCardViewHolder) {
            BilibiliCardViewHolder holder = (BilibiliCardViewHolder) vh;

            holder.textView.setText(list.list.get(position).title);
            Glide.with(mContext).load(list.list.get(position).pic).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        }
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

    class BilibiliCardViewHolder extends RecyclerView.ViewHolder {
        private final String TAG = BilibiliCardViewHolder.class.getSimpleName();
        private ImageView imageView;
        private TextView textView;
        private View rootView;

        private BilibiliCardViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.card_view_image);
            textView = (TextView) itemView.findViewById(R.id.card_view_text);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BilibiliApplication.getApplication(), BilibiliDetailActivity.class);
                    int aid = list.list.get(getAdapterPosition()).aid;
                    intent.putExtra("AID", aid);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}

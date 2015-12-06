package me.qixingchen.mdbilibili.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.qixingchen.mdbilibili.R;

/**
 * Created by dell on 2015/6/15.
 */
public class BilibiliCardViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "BilibiliCardViewHolder";
    public ImageView imageView;
    public TextView textView;
    public View rootView;

    public BilibiliCardViewHolder(View itemView) {
        super(itemView);
        this.rootView = itemView;
        imageView = (ImageView) itemView.findViewById(R.id.card_view_image);
        textView = (TextView) itemView.findViewById(R.id.card_view_text);
    }

    //todo 同步代码
    public BilibiliCardViewHolder(View itemView, int flag) {
        super(itemView);
        this.rootView = itemView;
    }
}

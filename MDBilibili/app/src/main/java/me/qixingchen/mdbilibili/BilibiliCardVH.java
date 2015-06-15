package me.qixingchen.mdbilibili;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dell on 2015/6/15.
 */
public class BilibiliCardVH extends RecyclerView.ViewHolder {
    private static final String TAG = "BilibiliCardVH";
    private ImageView imageView;
    private TextView textView;
    private View rootView;

    public BilibiliCardVH(View itemView) {
        super(itemView);
        this.rootView = itemView;
    }
    public BilibiliCardVH(View itemView, int flag) {
        super(itemView);
        this.rootView = itemView;
    }
}

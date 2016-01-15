package me.qixingchen.mdbilibili.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Yulan on 2016/1/15.
 */
public class DataBindingUtil {
    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext()).load(url)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
    }

    @BindingAdapter({"bind:imageUrl", "bind:holder"})
    public static void loadImage(ImageView view, String url, @DrawableRes int holder) {
        Glide.with(view.getContext()).load(url)
                .placeholder(holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
    }
}

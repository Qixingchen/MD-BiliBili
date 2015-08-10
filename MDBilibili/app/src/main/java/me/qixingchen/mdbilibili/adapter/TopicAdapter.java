package me.qixingchen.mdbilibili.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by Yulan on 2015/8/1.
 */
public class TopicAdapter extends PagerAdapter {

    private NetworkImageView[] mImageViews;

    public TopicAdapter(NetworkImageView[] mImageViews) {
        this.mImageViews = mImageViews;
    }

    @Override
    public int getCount() {
        return mImageViews.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageViews[position]);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageViews[position]);
        return mImageViews[position];
        //super.instantiateItem(container, position);
    }
}

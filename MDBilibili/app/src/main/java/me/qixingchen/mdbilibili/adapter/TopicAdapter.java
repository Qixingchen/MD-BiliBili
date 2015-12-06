package me.qixingchen.mdbilibili.adapter;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

/**
 * Created by Yulan on 2015/8/1.
 */
public class TopicAdapter extends PagerAdapter {

    private ImageView[] mImageViews;

    public TopicAdapter(ImageView[] mImageViews) {
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

        if (object instanceof ImageView) {
            container.removeView((View) object);
        } else {
            container.removeView(mImageViews[position]);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            container.addView(mImageViews[position]);
        } catch (Exception ignore) {
            ImageView imageCopy = new ImageView(container.getContext());
            Bitmap copy = ((GlideBitmapDrawable) mImageViews[position].getDrawable()).getBitmap();
            imageCopy.setImageBitmap(copy.copy(copy.getConfig(), false));
            container.addView(imageCopy);
            return imageCopy;
        }

        return mImageViews[position];
    }
}

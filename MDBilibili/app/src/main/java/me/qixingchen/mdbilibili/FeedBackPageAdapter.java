package me.qixingchen.mdbilibili;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by Farble on 2015/7/31 00.
 */
public class FeedBackPageAdapter extends PagerAdapter {
    private static final String TAG = "FeedBackPageAdapter";
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}

package me.qixingchen.mdbilibili.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.qixingchen.mdbilibili.ui.fragment.MainFragment;

/**
 * Created by Yulan on 2015/7/26.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private final String tabTitles[] = new String[]{"番组", "动画", "音乐", "舞蹈", "游戏",
            "科技", "娱乐", "鬼畜", "电影", "电视剧"};
    private final String typeid[] = new String[]{"13", "1", "117", "20", "4",
            "36", "5", "119", "23", "11"};
    private Context context;

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(typeid[position], String.valueOf(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}

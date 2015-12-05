package me.qixingchen.mdbilibili.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

/**
 * Created by Yulan on 2015/12/5.
 * Activity 的抽象类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected View view;
    protected Activity mActivity;
    protected Menu mMenu;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mContext = this;
        mActivity = this;
        view = getWindow().getDecorView().getRootView();

        findView();
        initView();
        setViewEvent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 获取布局控件
     */
    protected abstract void findView();

    /**
     * 初始化View的一些数据
     */
    protected abstract void initView();

    /**
     * 设置点击监听
     */
    protected abstract void setViewEvent();

}

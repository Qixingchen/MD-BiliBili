package me.qixingchen.mdbilibili.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

/**
 * Created by Yulan on 2015/12/5.
 * Activity 的抽象类
 */
public abstract class BaseActivity extends AppCompatActivity {

//    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected View view;
    protected Activity mActivity;
//    protected Menu mMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        mContext = this;
        mActivity = this;
        view = getWindow().getDecorView().getRootView();

        bindView();
        initData();
        bindEvent();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        mMenu = menu;
//        return super.onCreateOptionsMenu(menu);
//    }

    /**
     * 设置界面布局
     */
    protected abstract int getContentView();

    /**
     * 绑定控件
     */
    protected abstract void bindView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置事件监听
     */
    protected abstract void bindEvent();

}
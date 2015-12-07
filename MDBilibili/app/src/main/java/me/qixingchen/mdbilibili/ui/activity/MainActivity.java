package me.qixingchen.mdbilibili.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.logger.Log;
import me.qixingchen.mdbilibili.model.Topic;
import me.qixingchen.mdbilibili.network.TopicApi;
import me.qixingchen.mdbilibili.ui.adapter.MainFragmentPagerAdapter;
import me.qixingchen.mdbilibili.ui.adapter.TopicAdapter;
import me.qixingchen.mdbilibili.ui.base.BaseActivity;
import me.qixingchen.mdbilibili.widget.LoopViewPager;

/**
 * Created by chenchao on 15/12/7.
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private NavigationView navigationView;
    private LoopViewPager topicViewPager;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //topic
        topicViewPager = (LoopViewPager) findViewById(R.id.topicViewPager);
    }

    @Override
    protected void initData() {
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mViewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(mViewPager);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        initDrawer();

        TopicApi.getInstance().setCallBack(new TopicApi.OnJsonGot() {
            @Override
            public void TopicOK(Topic topic) {
                //将图片装载到数组中
                ImageView[] mImageViews = new ImageView[topic.getResults()];
                for (int i = 0; i < topic.getResults(); i++) {
                    ImageView imageView = new ImageView(mContext);
                    mImageViews[i] = imageView;
                    Glide.with(mContext).load(topic.getList().get(i).getImg()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
                }
                topicViewPager.setAdapter(new TopicAdapter(mImageViews));
            }

            @Override
            public void TopicError(String errorMessage) {
                Log.e(TAG, errorMessage);
            }
        }).addRequest();
    }

    @Override
    protected void bindEvent() {

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                            case R.id.nav_messages:
                            case R.id.nav_my_focus:
                            case R.id.nav_foucs_me:
                            case R.id.nav_article:
                            case R.id.nav_video:
                                break;
                            case R.id.nav_about:
                                MainActivity.this.navigate(AboutActivity.class);
                                break;
                            default:
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    void initDrawer() {

    }

    private void navigate(Class<? extends AppCompatActivity> activityClass) {
        Intent intent = new Intent(mContext, activityClass);
        mContext.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        //subscription = RxUtils.getNewCompositeSubIfUnsubscribed(subscription);
    }

    @Override
    public void onPause() {
        super.onPause();
        // RxUtils.unsubscribeIfNotNull(subscription);
    }
}
package me.qixingchen.mdbilibili.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.android.volley.toolbox.NetworkImageView;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.adapter.TopicAdapter;
import me.qixingchen.mdbilibili.fragment.main.MainFragmentPagerAdapter;
import me.qixingchen.mdbilibili.logger.Log;
import me.qixingchen.mdbilibili.model.Topic;
import me.qixingchen.mdbilibili.network.GetVolley;
import me.qixingchen.mdbilibili.network.TopicApi;
import rx.subscriptions.CompositeSubscription;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private CompositeSubscription subscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mViewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(mViewPager);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        initDrawer();
        //topic
        ViewPager topicViewPager = (ViewPager) findViewById(R.id.topicViewPager);

        TopicApi.getInstance().setCallBack(new TopicApi.OnJsonGot() {
            @Override
            public void TopicOK(Topic topic) {
                //将图片装载到数组中
                NetworkImageView[] mImageViews = new NetworkImageView[topic.getResults()];
                for (int i = 0; i < topic.getResults(); i++) {
                    NetworkImageView imageView = new NetworkImageView(mContext);
                    mImageViews[i] = imageView;
                    imageView.setImageUrl(topic.getList().get(i).getImg(),
                            GetVolley.getmInstance(mContext).getImageLoader());
                }
                topicViewPager.setAdapter(new TopicAdapter(mImageViews));
            }

            @Override
            public void TopicError(String errorMessage) {
                Log.e(TAG, errorMessage);
            }
        }).addRequest();


    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                        case R.id.nav_messages:
                        case R.id.nav_my_focus:
                        case R.id.nav_foucs_me:
                        case R.id.nav_article:
                        case R.id.nav_video:
                            break;
                        case R.id.nav_about:
                            navigate(About.class);
                            break;
                        default:
                            break;
                    }
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
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

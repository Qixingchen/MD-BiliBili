package me.qixingchen.mdbilibili;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.qixingchen.mdbilibili.fragment.main.MainFragmentPagerAdapter;
import me.qixingchen.mdbilibili.network.Api;
import me.qixingchen.mdbilibili.utils.RxUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext;
    private DrawerLayout mDrawerLayout;
    //private RecyclerView mRecyclerView;
    private CompositeSubscription subscription = new CompositeSubscription();
    private Api.SearchApi searchApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        searchApi = RxUtils.createApi(Api.SearchApi.class);
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
        //mRecyclerView = (RecyclerView) findViewById(R.id.dast_recycler_view);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            //todo 搜索
            subscription.add(searchApi.doSearch("拜年祭", 1, 1, "default")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(json -> {
                        Snackbar.make(view, json.getAuthor(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }));

        });
        //todo 折叠操作
//        toolbar.post(new Runnable() {
//            @Override
//            public void run() {
//                ScrollManager manager = new ScrollManager();
//                manager.attach(mRecyclerView);
//                manager.addView(toolbar, ScrollManager.Direction.UP);
//                manager.addView(fab, ScrollManager.Direction.DOWN);
//                manager.setInitialOffset(toolbar.getHeight());
//            }
//        });
        initDrawer();
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
        subscription = RxUtils.getNewCompositeSubIfUnsubscribed(subscription);
    }

    @Override
    public void onPause() {
        super.onPause();
        RxUtils.unsubscribeIfNotNull(subscription);
    }
}

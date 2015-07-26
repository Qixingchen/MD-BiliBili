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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.qixingchen.mdbilibili.fragment.main.MainFragmentPagerAdapter;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext;
    private DrawerLayout mDrawerLayout;
    //private RecyclerView mRecyclerView;

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
        //mRecyclerView = (RecyclerView) findViewById(R.id.dast_recycler_view);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 搜索
                Snackbar.make(view, "正在刷新", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
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
                                navigate(About.class);
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
}

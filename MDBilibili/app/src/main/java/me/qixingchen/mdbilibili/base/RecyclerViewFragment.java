package me.qixingchen.mdbilibili.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.logger.Log;
import me.qixingchen.mdbilibili.view.MultiSwipeRefreshLayout;


/**
 * Created by farble on 2015/5/11.
 * you need include dast_base_recyclerview.xml
 * @deprecated will be refactored next time
 */
public abstract class RecyclerViewFragment extends Fragment {
    private final static String TAG = "RecyclerViewFragment";
    protected Activity mActivity;
    protected View rootView;
    protected RecyclerView mRecyclerView;

    protected LinearLayoutManager mLayoutManager;

    protected MultiSwipeRefreshLayout mSwipeRefreshLayout;

    protected boolean recyclerViewStateLoading = true;
    /**
     * RecyclerView 是否支持下拉刷新
     * <li>true : yes</li>
     * <li>false : no</li>
     */
    protected boolean canSwipeRefresh = true;
    /**
     * RecyclerView pull to load more
     * <li>true : yes</li>
     * <li>false : no</li>
     */
    protected boolean canPullToLoading = true;
    protected int pastVisiblesItems, visibleItemCount, totalItemCount;

    protected RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = initRootView(inflater,container , savedInstanceState);
        setConfig();
        initRecyclerView();
        if (canSwipeRefresh)
            initRefreshLayout();

        return rootView;
    }

    protected void initRefreshLayout() {
        // Retrieve the SwipeRefreshLayout
        mSwipeRefreshLayout = (MultiSwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);

        // BEGIN_INCLUDE (change_colors)
        // Set the color scheme of the SwipeRefreshLayout by providing 4 color resource ids
        mSwipeRefreshLayout.setColorScheme(
                R.color.md_orange_700, R.color.md_red_500,
                R.color.md_indigo_900, R.color.md_green_700);
        // END_INCLUDE (change_colors)
        mSwipeRefreshLayout.setSwipeableChildren(R.id.recycler_view);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
                doSwapeRefresh();
            }
        });
    }

    protected void initRecyclerView() {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setRecyclerViewAdapter();

        if (canPullToLoading)
            mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (recyclerViewStateLoading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            setRecyclerViewStateLoading(false);
                            pullToLoad();
                        }
                    }
                }
            });
    }

    protected void setRecyclerViewStateLoading(boolean recyclerViewStateLoading) {
        this.recyclerViewStateLoading = recyclerViewStateLoading;
    }

    protected void setCanRefresh(boolean canRefresh) {
        this.canSwipeRefresh = canRefresh;
    }

    protected void setPullToLoading(boolean pullToLoading) {
        this.canPullToLoading = pullToLoading;
    }

    /**
     * 设置canPullToLoading,canRefresh的值<li>{@linkplain RecyclerViewFragment canPullToLoading}</li>
     * <li>{@linkplain RecyclerViewFragment canSwipeRefresh}</li>
     */
    protected abstract void setConfig();

    /**
     * <h>Prepare to fresh,you may call this method n the following cases:</h>
     * <li>After swiping swipeable-children </li>
     * <li>Active called to fresh like function designed in menu</li>
     */
    protected abstract void doSwapeRefresh();

    /**
     * init fragment data
     */
    public abstract void loadInitData();

    /**
     * loading more while pulling up
     */
    public abstract void pullToLoad();

    /**
     * refresh push data
     */

    public abstract void setRecyclerViewAdapter();

    /**init fragment rootView
     * @param inflater
     * @param container
     * @param savedInstanceState*/
    public abstract View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}

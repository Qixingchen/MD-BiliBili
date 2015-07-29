package me.qixingchen.mdbilibili;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.qixingchen.mdbilibili.view.MultiSwipeRefreshLayout;

/**
 * Created by Farble on 2015/6/22 22.
 * <li>swipe refresh <li/>
 * <li>floating action button <li/>
 * you need layout[dast_abs_recyclerview]
 */
public abstract class AbsSwipeRecyclerViewFab extends Fragment {
    private static final String TAG = "AbsSwipeRecyclerViewFab";
    protected Activity mActivity;
    protected View rootView;
    protected RecyclerView mRecyclerView;

    protected LinearLayoutManager mLayoutManager;

    protected MultiSwipeRefreshLayout mSwipeRefreshLayout;

    private FloatingActionButton mFloatingActionButton;

    protected boolean recyclerViewStateLoading = true;
    /**
     * can RecyclerView swipe refresh
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

    protected RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = initRootView(inflater, container, savedInstanceState);
        setConfig();
        initRecyclerView();
        if (canSwipeRefresh)
            initRefreshLayout();
        initFab();
        return rootView;
    }

    protected void initFab() {
        mFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.abs_fab);
    }

    protected void initRefreshLayout() {
        mSwipeRefreshLayout = (MultiSwipeRefreshLayout) rootView.findViewById(R.id.abs_swiperefresh);

        mSwipeRefreshLayout.setColorScheme(
                R.color.md_orange_700, R.color.md_red_500,
                R.color.md_indigo_900, R.color.md_green_700);
        mSwipeRefreshLayout.setSwipeableChildren(R.id.abs_recycler_view);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
            doSwapeRefresh();
        });
    }

    protected void initRecyclerView() {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setRecyclerViewAdapter();

        if (canPullToLoading)
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (recyclerViewStateLoading && mRecyclerView.canScrollVertically(1)) {
                        setRecyclerViewStateLoading(false);
                        pullToLoad();
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

    protected void clickFabShowSnackbar(View view, String text, String title) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction(title, null).show();
    }

    /**
     * set canPullToLoading,canRefresh value<li>{@linkplain AbsSwipeRecyclerViewFab canPullToLoading}</li>
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

    /**
     * init fragment rootView
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    public abstract View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}

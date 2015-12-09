package me.qixingchen.mdbilibili.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.model.List;
import me.qixingchen.mdbilibili.network.ListApi;
import me.qixingchen.mdbilibili.ui.adapter.CardAdapter;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by farble on 2015/6/15.
 * 并不知道用在哪里..
 */
@Deprecated
public class RecyclerViewFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";
    private View rootView;
    private Activity mActivity;
    private RecyclerView recyclerView;
    private List list = null;
    private CardAdapter mCardAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.dast_bilibili_card, container, false);
        }
        initView();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        ListApi.getList(1).observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {

                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }).doOnNext(new Action1<List>() {
            @Override
            public void call(List list) {
                mCardAdapter.notifyDateChanged(list);
            }
        });
    }

    private void initView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mCardAdapter = new CardAdapter(list, mActivity.getApplication());
        recyclerView.setAdapter(mCardAdapter);
    }
}
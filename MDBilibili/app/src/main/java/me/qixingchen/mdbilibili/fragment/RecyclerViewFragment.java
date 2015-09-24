package me.qixingchen.mdbilibili.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.adapter.CardAdapter;
import me.qixingchen.mdbilibili.model.List;
import me.qixingchen.mdbilibili.network.ListApi;
import me.qixingchen.mdbilibili.network.RetrofitNetworkAbs;


/**
 * Created by farble on 2015/6/15.
 */
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
        ListApi.getNewInstance().setNetworkListener(new RetrofitNetworkAbs.NetworkListener() {
            @Override
            public void onOK(Object ts) {
                List list = (List) ts;
                mCardAdapter.notifyDateChanged(list);
            }

            @Override
            public void onError(String Message) {
                Log.w(TAG, Message);
            }
        }).getList(1);
    }

    private void initView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mCardAdapter = new CardAdapter(list, mActivity.getApplication());
        recyclerView.setAdapter(mCardAdapter);
    }
}

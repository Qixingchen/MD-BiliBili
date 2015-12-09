package me.qixingchen.mdbilibili.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.model.List;
import me.qixingchen.mdbilibili.network.ListApi;
import me.qixingchen.mdbilibili.ui.adapter.CardAdapter;
import me.qixingchen.mdbilibili.ui.base.BaseFragment;
import me.qixingchen.mdbilibili.utils.Log;
import me.qixingchen.mdbilibili.widget.AutoGridfitLayoutManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {

    private static final String TAG = "MainFragment";
    private static final String PID = "PID";
    private static final String LOCATION = "LOCATION";
    private RecyclerView mRecyclerView;
    private String pid;
    private String location;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * @param pid      本页要显示的分区id
     * @param position 本页的位置号
     */
    public static MainFragment newInstance(String pid, String position) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(PID, pid);
        args.putString(LOCATION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void findViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.dast_recycler_view);
    }

    @Override
    protected void initViews() {
        ListApi.getList(Integer.valueOf(pid))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List>() {
                               @Override
                               public void call(List list) {
                                   CardAdapter mCardAdapter = new CardAdapter(list, mActivity);
                                   mRecyclerView.setAdapter(mCardAdapter);
                                   mRecyclerView.setLayoutManager(new AutoGridfitLayoutManager(mActivity, 150));
                                   Log.i(TAG, "setAdapter" + location);
                               }
                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                                Snackbar.make(view, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
                            }
                        });
    }

    @Override
    protected void setViewEvent() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pid = getArguments().getString(PID);
            location = getArguments().getString(LOCATION);
        }
    }
}

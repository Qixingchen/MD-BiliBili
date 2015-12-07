package me.qixingchen.mdbilibili.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.model.List;
import me.qixingchen.mdbilibili.network.ListApi;
import me.qixingchen.mdbilibili.network.RetrofitNetworkAbs;
import me.qixingchen.mdbilibili.ui.adapter.CardAdapter;
import me.qixingchen.mdbilibili.utils.Log;
import me.qixingchen.mdbilibili.widget.AutoGridfitLayoutManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container,
                false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.dast_recycler_view);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new AutoGridfitLayoutManager(mActivity, 150));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart" + mParam2);

        ListApi.getNewInstance().setNetworkListener(new RetrofitNetworkAbs.NetworkListener() {
            @Override
            public void onOK(Object ts) {
                List list = (List) ts;
                CardAdapter mCardAdapter = new CardAdapter(list, mActivity);
                mRecyclerView.setAdapter(mCardAdapter);
                Log.e(TAG, "setAdapter" + mParam2);
            }

            @Override
            public void onError(String Message) {
                Log.w(TAG, Message);
            }
        }).getList(Integer.parseInt(mParam1));
    }
}

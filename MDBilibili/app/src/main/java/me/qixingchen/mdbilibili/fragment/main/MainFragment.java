package me.qixingchen.mdbilibili.fragment.main;


import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.qixingchen.mdbilibili.CardAdapter;
import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.logger.Log;
import me.qixingchen.mdbilibili.model.Recommend;
import me.qixingchen.mdbilibili.network.GetRecommend;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private Activity mActivity;
    private RecyclerView mRecyclerView;

    private static final String TAG = "MainFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
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
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));

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
        GetRecommend.getRecommend().setCallBack(new GetRecommend.RecommendCallBack() {
            @Override
            public void recommendCallBack(Recommend recommend) {
                CardAdapter mCardAdapter = new CardAdapter(recommend, mActivity);
                mRecyclerView.setAdapter(mCardAdapter);
                Log.e(TAG, "setAdapter" + mParam2);
            }
        }).GetRecommendInfo(mParam1);
    }
}

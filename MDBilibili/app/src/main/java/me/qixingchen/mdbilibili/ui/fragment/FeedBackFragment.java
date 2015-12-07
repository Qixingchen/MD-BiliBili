package me.qixingchen.mdbilibili.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.qixingchen.mdbilibili.R;

/**
 * Created by Farble on 2015/7/31 00.
 */
public class FeedBackFragment extends Fragment {
    private static final String TAG = "FeedBackFragment";
    private View rootView;
    private Activity mActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.dast_feedback, container, false);
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }
}

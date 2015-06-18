package me.qixingchen.mdbilibili;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by farble on 2015/6/15.
 */
public class TestFragment extends Fragment {
	private static final String TAG = "TestFragment";
	private View rootView;
	private Activity mActivity;
	private RecyclerView recyclerView;

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

	private void initView() {
		recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
		recyclerView.setAdapter(new CardAdapter());
	}

}

package me.qixingchen.mdbilibili.fragment;

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
import me.qixingchen.mdbilibili.adapter.CardAdapter;
import me.qixingchen.mdbilibili.model.Recommend;
import me.qixingchen.mdbilibili.network.GetRecommend;


/**
 * Created by farble on 2015/6/15.
 */
public class RecyclerViewFragment extends Fragment implements GetRecommend.RecommendCallBack {
	private static final String TAG = "RecyclerViewFragment";
	private View rootView;
	private Activity mActivity;
	private RecyclerView recyclerView;
	private Recommend recommend = null;
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
		GetRecommend.getRecommend().setCallBack(this).GetRecommendInfo("1");
	}

	private void initView() {
		recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
		mCardAdapter = new CardAdapter(recommend, mActivity.getApplication());
		recyclerView.setAdapter(mCardAdapter);
	}

	@Override
	public void recommendCallBack(Recommend recommend) {
		this.recommend = recommend;
		mCardAdapter.notifyDateChanged(recommend);
	}
}

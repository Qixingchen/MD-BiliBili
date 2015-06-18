package me.qixingchen.mdbilibili;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dell on 2015/6/15.
 */
public class CardAdapter extends RecyclerView.Adapter<BilibiliCardViewHolder>
		implements BilibiliCardViewHolder.BilibiliCardViewHolderOnClick {


	@Override
	public BilibiliCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.dast_bilibili_card_item, parent, false);
		BilibiliCardViewHolder vh = new BilibiliCardViewHolder(v, this);
		return vh;
	}

	@Override
	public void onBindViewHolder(BilibiliCardViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 20;
	}

	@Override
	public void OnCardViewClick(View view, int position) {
		//todo 开始播放
	}
}

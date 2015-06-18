package me.qixingchen.mdbilibili;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import me.qixingchen.mdbilibili.app.App;
import me.qixingchen.mdbilibili.model.Recommend;
import me.qixingchen.mdbilibili.network.GetVolley;

/**
 * Created by dell on 2015/6/15.
 */
public class CardAdapter extends RecyclerView.Adapter<BilibiliCardViewHolder> {

	private Recommend recommend;
	private ImageLoader mImageLoader;
	private Context mContext;

	public CardAdapter(Recommend recommend, Context context) {
		this.recommend = recommend;
		mContext = context;
		mImageLoader = GetVolley.getmInstance(mContext).getImageLoader();
	}

	@Override
	public BilibiliCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.dast_bilibili_card_item, parent, false);
		BilibiliCardViewHolder vh = new BilibiliCardViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(BilibiliCardViewHolder holder, final int position) {
		holder.textView.setText(recommend.getLists().get(position).getTitle());
		holder.imageView.setImageUrl(recommend.getLists().get(position).getPic(), mImageLoader);
		holder.rootView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent playDemoIntent = new Intent(App.getApplication(), Player.class);
				String Aid = String.valueOf(recommend.getLists().get(position).getAid());
				playDemoIntent.putExtra("AID", Aid);
				playDemoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mContext.startActivity(playDemoIntent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return recommend == null ? 0 : recommend.getLists().size();
	}

	//更新数据
	public void notifyDateChanged(Recommend recommend) {
		this.recommend = recommend;
		this.notifyDataSetChanged();
	}
}

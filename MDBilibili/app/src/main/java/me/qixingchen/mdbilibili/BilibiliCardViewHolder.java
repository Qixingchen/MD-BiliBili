package me.qixingchen.mdbilibili;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dell on 2015/6/15.
 */
public class BilibiliCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
	private static final String TAG = "BilibiliCardViewHolder";
	private ImageView imageView;
	private TextView textView;
	private View rootView;
	private BilibiliCardViewHolderOnClick onClick;

	public BilibiliCardViewHolder(View itemView, BilibiliCardViewHolderOnClick onClick) {
		super(itemView);
		this.rootView = itemView;
		this.onClick = onClick;
		imageView = (ImageView) itemView.findViewById(R.id.card_view_image);
		textView = (TextView) itemView.findViewById(R.id.card_view_text);
	}

	//todo 同步代码
	public BilibiliCardViewHolder(View itemView, BilibiliCardViewHolderOnClick onClick, int flag) {
		super(itemView);
		this.rootView = itemView;
		this.onClick = onClick;
		imageView = (ImageView) itemView.findViewById(R.id.card_view_image);
		textView = (TextView) itemView.findViewById(R.id.card_view_text);
	}

	@Override
	public void onClick(View v) {
		if (onClick != null) {
			onClick.OnCardViewClick(v, getAdapterPosition());
		}
	}

	public interface BilibiliCardViewHolderOnClick {
		void OnCardViewClick(View view, int position);
	}
}

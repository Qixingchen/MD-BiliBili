package me.qixingchen.mdbilibili.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.databinding.DastBilibiliDetailBinding;
import me.qixingchen.mdbilibili.network.Api;
import me.qixingchen.mdbilibili.network.RetrofitNetwork;
import me.qixingchen.mdbilibili.ui.base.BaseActivity;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Farble on 2015/6/24.
 * detail page
 */
public class BilibiliDetailActivity extends BaseActivity {

    private static final String TAG = "BilibiliDetail";
    private static final String IMG_URL = "IMG_URL";
    private static final String TITLE = "TITLE";
    private static final String AID = "AID";

    protected String imageUrl;
    protected String title;
    private int aid;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingtoolbar;
    private android.support.design.widget.AppBarLayout appbar;
    private android.widget.TextView aidTextView;
    private android.widget.TextView PlayTextView;
    private android.widget.TextView reviewTextView;
    private android.widget.TextView videoreviewTextView;
    private android.widget.TextView favoritesTextView;
    private ImageView backdrop;
    private FloatingActionButton detailfab;

    private DastBilibiliDetailBinding binding;

    @Override
    protected int getContentView() {
        binding = DataBindingUtil.setContentView(
                this, R.layout.dast_bilibili_detail);
        return 0;
    }

    @Override
    protected void bindView() {

        this.detailfab = (FloatingActionButton) findViewById(R.id.detail_fab);
        this.favoritesTextView = (TextView) findViewById(R.id.favoritesTextView);
        this.videoreviewTextView = (TextView) findViewById(R.id.video_reviewTextView);
        this.reviewTextView = (TextView) findViewById(R.id.reviewTextView);
        this.PlayTextView = (TextView) findViewById(R.id.PlayTextView);
        this.aidTextView = (TextView) findViewById(R.id.aidTextView);
        this.appbar = (AppBarLayout) findViewById(R.id.appbar);
        this.collapsingtoolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.backdrop = (ImageView) findViewById(R.id.backdrop);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void initData() {
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra(IMG_URL);
        title = intent.getStringExtra(TITLE);
        aid = intent.getIntExtra(AID, 0);

        RetrofitNetwork.retrofitAPI.create(Api.View.class).getViewInfo(aid, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<me.qixingchen.mdbilibili.model.View>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(me.qixingchen.mdbilibili.model.View view) {
                        setVideoInfo(view);
                        binding.setView(view);
                    }
                });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //collapsingtoolbar.setTitle(title);

        loadBackdrop();
    }

    @Override
    protected void bindEvent() {
        detailfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playDemoIntent = new Intent(mContext, PlayerActivity.class);
                playDemoIntent.putExtra("AID", aid);
                mContext.startActivity(playDemoIntent);
            }
        });
    }

    private void setVideoInfo(me.qixingchen.mdbilibili.model.View views) {
        favoritesTextView.setText("收藏数：" + views.getFavorites());
        videoreviewTextView.setText("弹幕数：" + views.getVideo_review());
        reviewTextView.setText("评论数：" + views.getReview());
        PlayTextView.setText("播放数：" + views.getPlay());
        aidTextView.setText("AV" + aid);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadBackdrop() {
        Glide.with(this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(backdrop);
    }

}

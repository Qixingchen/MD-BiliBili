package me.qixingchen.mdbilibili.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.logger.Log;
import me.qixingchen.mdbilibili.network.GetVolley;
import me.qixingchen.mdbilibili.network.ViewAPI;

/**
 * Created by Farble on 2015/6/24.
 * detail page
 */
public class BilibiliDetail extends AppCompatActivity {
    private static final String TAG = "BilibiliDetail";
    private static final String IMG_URL = "IMG_URL";
    private static final String TITLE = "TITLE";
    private static final String AID = "AID";

    protected Context mContext;
    protected FloatingActionButton fab;
    protected String imageUrl;
    protected String title;
    private String aid;
    private NetworkImageView backdrop;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingtoolbar;
    private android.support.design.widget.AppBarLayout appbar;
    private android.widget.TextView aidTextView;
    private android.widget.TextView PlayTextView;
    private android.widget.TextView reviewTextView;
    private android.widget.TextView videoreviewTextView;
    private android.widget.TextView favoritesTextView;
    private FloatingActionButton detailfab;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dast_bilibili_detail);
        findViews();

        mContext = this;
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra(IMG_URL);
        title = intent.getStringExtra(TITLE);
        aid = intent.getStringExtra(AID);

        ViewAPI.getInstance().setCallBack(new ViewAPI.OnJsonGot() {
            @Override
            public void ViewOK(me.qixingchen.mdbilibili.model.View views) {
                setVideoInfo(views);
            }

            @Override
            public void ViewError(String errorMessage) {
                Log.e(TAG, errorMessage);
            }
        }).addRequest(aid, "1");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingtoolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingtoolbar.setTitle(title);

        fab = (FloatingActionButton) findViewById(R.id.detail_fab);
        fab.setOnClickListener(view -> {
            Intent playDemoIntent = new Intent(mContext, PlayerActivity.class);
            playDemoIntent.putExtra("AID", aid);
            mContext.startActivity(playDemoIntent);
        });
        loadBackdrop();
    }

    private void setVideoInfo(me.qixingchen.mdbilibili.model.View views) {
        favoritesTextView.setText("收藏数：" + views.getFavorites());
        videoreviewTextView.setText("弹幕数：" + views.getVideo_review());
        reviewTextView.setText("评论数：" + views.getReview());
        PlayTextView.setText("播放数：" + views.getPlay());
        aidTextView.setText("AV" + aid);
    }

    private void findViews() {
        this.detailfab = (FloatingActionButton) findViewById(R.id.detail_fab);
        this.favoritesTextView = (TextView) findViewById(R.id.favoritesTextView);
        this.videoreviewTextView = (TextView) findViewById(R.id.video_reviewTextView);
        this.reviewTextView = (TextView) findViewById(R.id.reviewTextView);
        this.PlayTextView = (TextView) findViewById(R.id.PlayTextView);
        this.aidTextView = (TextView) findViewById(R.id.aidTextView);
        this.appbar = (AppBarLayout) findViewById(R.id.appbar);
        this.collapsingtoolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.backdrop = (NetworkImageView) findViewById(R.id.backdrop);
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
        final NetworkImageView imageView = (NetworkImageView) findViewById(R.id.backdrop);
        imageView.setImageUrl(imageUrl, GetVolley.getmInstance(this).getImageLoader());
    }

}

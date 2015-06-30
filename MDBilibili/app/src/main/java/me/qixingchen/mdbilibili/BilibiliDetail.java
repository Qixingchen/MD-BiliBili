package me.qixingchen.mdbilibili;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;

import me.qixingchen.mdbilibili.network.GetVolley;

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

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dast_bilibili_detail);

        mContext = this;
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra(IMG_URL);
        title = intent.getStringExtra(TITLE);
        aid = intent.getStringExtra(AID);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(title);

        fab = (FloatingActionButton) findViewById(R.id.detail_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playDemoIntent = new Intent(mContext, PlayerActivity.class);
                playDemoIntent.putExtra("AID", aid);
                mContext.startActivity(playDemoIntent);
            }
        });
        loadBackdrop();
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

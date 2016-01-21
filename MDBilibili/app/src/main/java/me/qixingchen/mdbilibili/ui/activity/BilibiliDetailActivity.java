package me.qixingchen.mdbilibili.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

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

    private static final String AID = "AID";

    private int aid;
    private Toolbar toolbar;
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
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void initData() {
        Intent intent = getIntent();
        aid = intent.getIntExtra(AID, 0);
        binding.setAID("AV" + aid);

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
                        binding.setView(view);
                    }
                });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

package me.qixingchen.mdbilibili.ui.activity;

import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.qixingchen.mdbilibili.BuildConfig;
import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.ui.base.BaseActivity;
import me.qixingchen.mdbilibili.view.AppUtils;

/**
 * Created by Farble on 2015/6/28 14.
 * About application information
 */
public class AboutActivity extends BaseActivity {

    private static final String TAG = "About";
    private Button otherLicense;
    private Toolbar toolbar;

    @Override
    protected int getContentView() {
        return R.layout.dast_activity_about;
    }

    @Override
    protected void bindView() {
        toolbar = (Toolbar) findViewById(R.id.about_toolbar);
        otherLicense = (Button) findViewById(R.id.other_license_button);
    }

    @Override
    protected void initData() {
        getWindow().getDecorView().setBackground(null);

        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        //noinspection ConstantConditions
        ab.setDisplayHomeAsUpEnabled(true);

        String versionName = BuildConfig.VERSION_NAME;

        setTextWithLinks(R.id.text_application_info, getString(R.string.application_info_text, versionName));
        setTextWithLinks(R.id.text_developer_info, getString(R.string.developer_info_text));
        setTextWithLinks(R.id.text_designer, getString(R.string.designer));
        setTextWithLinks(R.id.text_license, getString(R.string.license_text));
    }

    @Override
    protected void bindEvent() {
        otherLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                //todo 增加第三方的开源协议
                builder.setMessage(Html.fromHtml(getString(R.string.other_license_text))).setTitle(Html.fromHtml(getString(R.string.other_license)));
                builder.setPositiveButton("确认", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void setTextWithLinks(@IdRes int textViewResId, String htmlText) {
        AppUtils.setTextWithLinks((TextView) findViewById(textViewResId), htmlText);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AboutActivity.this.finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

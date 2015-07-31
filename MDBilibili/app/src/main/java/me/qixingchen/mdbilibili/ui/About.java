package me.qixingchen.mdbilibili.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.qixingchen.mdbilibili.view.AppUtils;
import me.qixingchen.mdbilibili.R;

/**
 * Created by Farble on 2015/6/28 14.
 * About application information
 */
public final class About extends AppCompatActivity {
    private static final String TAG = "About";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dast_activity_about);
        mContext = this;
        final Toolbar toolbar = (Toolbar) findViewById(R.id.about_toolbar);
        toolbar.setTitle("About");
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        //noinspection ConstantConditions
        ab.setDisplayHomeAsUpEnabled(true);

        String versionName = "0.0.1 技术预览版";
        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // do nothing
        }
        setTextWithLinks(R.id.text_application_info, getString(R.string.application_info_text, versionName));
        setTextWithLinks(R.id.text_developer_info, getString(R.string.developer_info_text));
        setTextWithLinks(R.id.text_designer, getString(R.string.designer));
        setTextWithLinks(R.id.text_license, getString(R.string.license_text));
        Button otherLicense = (Button) findViewById(R.id.other_license_button);
        otherLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                //todo 增加第三方的开源协议
                builder.setMessage(R.string.other_license_text).setTitle(getString(R.string.other_license));
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
                About.this.finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

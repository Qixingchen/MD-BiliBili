package me.qixingchen.mdbilibili.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Yulan on 2015/6/12.
 */
public class BilibiliApplication extends Application {
	private static final String TAG = "BilibiliApplication";
	private static Application application;

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
		LeakCanary.install(this);
	}

	public static Application getApplication() {
		return application;
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
	}
}

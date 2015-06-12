package me.qixingchen.mdbilibili.app;

import android.app.Application;

/**
 * Created by Yulan on 2015/6/12.
 */
public class App extends Application {
	private static Application application;

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
	}

	public static Application getApplication() {
		return application;
	}

}

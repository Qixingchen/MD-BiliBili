package me.qixingchen.mdbilibili.tool;

import android.app.Application;
import android.os.Environment;

import me.qixingchen.mdbilibili.app.BilibiliApplication;

/**
 * Created by Yulan on 2015/6/12.
 */
public class Tool {

	private Application application = BilibiliApplication.getApplication();

	//判断外置储存空间是否有效
	public static boolean isExternalStorageAvlilable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

}

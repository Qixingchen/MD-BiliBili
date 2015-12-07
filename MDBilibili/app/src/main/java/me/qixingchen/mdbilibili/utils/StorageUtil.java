package me.qixingchen.mdbilibili.utils;

import android.os.Environment;

/**
 * storage工具类
 * Created by Yulan on 2015/6/12.
 */
public class StorageUtil {

    //判断外置储存空间是否有效
    public static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}

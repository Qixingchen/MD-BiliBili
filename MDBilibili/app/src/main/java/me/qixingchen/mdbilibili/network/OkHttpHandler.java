package me.qixingchen.mdbilibili.network;

import android.os.Handler;

/**
 * Created by Farble on 2015/7/5 20.
 */
public class OkHttpHandler extends Handler {

    protected String method;

    public OkHttpHandler(String method) {
        this.method = method;
    }

    public void sendMessage(int what, Object object) {
        this.sendMessage(this.obtainMessage(what, object));
    }

}

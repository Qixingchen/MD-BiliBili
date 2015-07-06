package me.qixingchen.mdbilibili.network;

import android.annotation.SuppressLint;

/**
 * Created by Farble on 2015/7/5 20.
 */
public class OkHttpProtocol {
    protected static OkHttpHandler getHandler(
            final HttpCommon listener, String method) {
        @SuppressLint("HandlerLeak")
        @SuppressWarnings("UnnecessaryLocalVariable")
        OkHttpHandler handler = new OkHttpHandler(method) {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case HttpCommon.MSG_NET_DATA_SUCCESS:
                        listener.onNetWorkComplete(this.method, msg.obj);
                        break;
                    case HttpCommon.MSG_NET_DATA_FAIL:
                        listener.onCancel();
                        break;
                    case HttpCommon.MSG_EXCEPITON:
                        listener.onException((Exception) msg.obj);
                        break;
                    default:
                        break;
                }
            }
        };
        return handler;
    }
}

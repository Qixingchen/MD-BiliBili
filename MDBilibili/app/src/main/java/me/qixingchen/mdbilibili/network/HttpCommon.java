package me.qixingchen.mdbilibili.network;

/**
 * Created by Farble on 2015/7/5 20.
 * okhttp common
 */
public interface HttpCommon {
     int OKHTTP_CLIENT_CONNECT_TIMEOUT = 5;
     int OKHTTP_CLIENT_WRITE_TIMEOUT = 10;
     int OKHTTP_CLIENT_READ_TIMEOUT = 10;

     int MSG_NET_DATA_SUCCESS = 200;
     int MSG_NET_DATA_FAIL = 300;
     int MSG_EXCEPITON = 600;

     void onNetWorkComplete(String method, Object values);

     void onException(Exception e);

     void onError(Exception e);

     void onCancel();
}

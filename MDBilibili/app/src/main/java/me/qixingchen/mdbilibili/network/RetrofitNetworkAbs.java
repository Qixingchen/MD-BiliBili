package me.qixingchen.mdbilibili.network;

import me.qixingchen.mdbilibili.logger.Log;

/**
 * Created by Yulan on 2015/9/22.
 * RetrofitNetwork 抽象类
 */
public abstract class RetrofitNetworkAbs {
    protected final String TAG = this.getClass().getSimpleName();
    protected NetworkListener networkListener;

    @SuppressWarnings("unchecked")
    protected <T extends RetrofitNetworkAbs> T setNetworkListener(NetworkListener networkListener, T type) {
        type.networkListener = networkListener;
        return type;
    }

    public abstract <T extends RetrofitNetworkAbs> T setNetworkListener(NetworkListener networkListener);

    /**
     * 检查Response是否成功
     *
     * @param response retrofit 的 response
     *
     * @return 是否成功
     */
    protected boolean myOnResponse(retrofit.Response<? extends Object> response) {
        if (response.isSuccess()) {
            Log.i(TAG, "success");
            if (networkListener != null) {
                networkListener.onOK(response.body());
            }
            return true;
        } else {
            String mess = response.message();
            Log.w(TAG, mess);
            if (networkListener != null) {
                networkListener.onError(mess);
            }
            return false;
        }
    }

    /**
     * OnFailure
     */
    protected void myOnFailure(Throwable t) {
        Log.w(TAG, t.getMessage());
        if (networkListener != null) {
            networkListener.onError(t.getMessage());
        }
    }


    // TODO: 15/9/17 T 抽象
    public interface NetworkListener<T extends Object> {
        void onOK(T ts);

        void onError(String Message);
    }

}

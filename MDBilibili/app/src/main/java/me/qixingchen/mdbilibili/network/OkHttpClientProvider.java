package me.qixingchen.mdbilibili.network;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by Farble on 2015/7/5 20.
 * Okhttp client provider
 */
public enum OkHttpClientProvider {

    NETPPROVIDER, OkHttpClientProvider;

    private final OkHttpClient okHttpClient;
    /**
     * Used for HTTP POST requests in order to avoid retrying requests.
     */
    private final OkHttpClient okHttpClientForNonIdempotent;

    OkHttpClientProvider() {
        okHttpClient = new OkHttpClient();

        okHttpClient.setConnectTimeout(HttpCommon.OKHTTP_CLIENT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(HttpCommon.OKHTTP_CLIENT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(HttpCommon.OKHTTP_CLIENT_READ_TIMEOUT, TimeUnit.SECONDS);

        okHttpClientForNonIdempotent = okHttpClient.clone();
        okHttpClientForNonIdempotent.setRetryOnConnectionFailure(false);
    }

    public static OkHttpClient get() {
        return NETPPROVIDER.okHttpClient;
    }

    public static OkHttpClient getForNonIdempotent() {
        return NETPPROVIDER.okHttpClientForNonIdempotent;
    }
}

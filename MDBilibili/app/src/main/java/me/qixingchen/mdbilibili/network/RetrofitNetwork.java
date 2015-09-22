package me.qixingchen.mdbilibili.network;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import me.qixingchen.mdbilibili.logger.Log;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Yulan on 2015/9/22.
 * Retrofit 网络
 */
public class RetrofitNetwork {
    private final static String TAG = RetrofitNetwork.class.getSimpleName();

    private static String APIURL = "http://api.bilibili.cn";
    private static String MAIN_URL = "http://api.bilibili.cn";

    public static Retrofit retrofitAPI = new Retrofit.Builder()
            .baseUrl(APIURL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit retrofitMain = new Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /**
     * OKHttp log接口
     */
    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            //Log.i(TAG, String.format("Sending request %s on %s%n%s",
            //request.url(), chain.connection(), "request.headers()"));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.i(TAG, String.format("Received response for %s in %.1fms%n",
                    response.request().url(), (t2 - t1) / 1e6d));

            return response;
        }
    }

    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new LoggingInterceptor());
        return client;
    }
}

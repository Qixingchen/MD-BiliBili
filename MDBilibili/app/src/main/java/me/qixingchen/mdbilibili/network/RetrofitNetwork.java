package me.qixingchen.mdbilibili.network;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import me.qixingchen.mdbilibili.app.Secret;
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
    /**
     * API 接口
     */
    public static Retrofit retrofitAPI = new Retrofit.Builder()
            .baseUrl(APIURL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static String MAIN_URL = "http://www.bilibili.com/";
    /**
     * 主站
     */
    public static Retrofit retrofitMain = new Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /**
     * 获取 OkHttpClinet
     */
    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new LoggingInterceptor());
        client.networkInterceptors().add(new SignInterceptor());
        client.networkInterceptors().add(new UserAgentInterceptor());
        return client;
    }

    /**
     * 获取签名串
     */
    private static String getSign(String paraUri) {

        MessageDigest md = null;
        paraUri += Secret.App_Secret;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        byte[] Md5 = md.digest(paraUri.getBytes());
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : Md5) {
            int bt = b & 0xff;
            if (bt < 16) {
                stringBuffer.append(0);
            }
            stringBuffer.append(Integer.toHexString(bt));
        }
        String sign = stringBuffer.toString();
        return sign;
    }

    /**
     * 从 URL 获取参数
     *
     * @param url URL
     *
     * @return 参数MAP
     */
    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new TreeMap<>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    /**
     * A combination of request parameters,added App_Key
     * see {@link RetrofitNetwork getSign(String paraUri)}
     */
    private static String getParaUriNoSigned(Map<String, String> para) {
        para.put("ts", String.valueOf(System.currentTimeMillis() / 1000L));
        para.put("appkey", Secret.App_Key);
        String paraUri = "";
        for (Map.Entry<String, String> entry : para.entrySet()) {
            paraUri += entry.getKey() + "=" + entry.getValue() + "&";
        }
        paraUri = paraUri.substring(0, paraUri.length() - 1);
        return paraUri;
    }

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

    /**
     * 签名接口
     */
    static class SignInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            if (request != null) {
                URL requestURL = request.url();
                if (requestURL.getHost().contains("www.bilibili.com")) {
                    return chain.proceed(request);
                }
                String baseURL = requestURL.getProtocol() + "://" + requestURL.getHost() + requestURL.getPath();

                Map<String, String> queryMap = splitQuery(requestURL);
                String paraUri = getParaUriNoSigned(queryMap);
                paraUri += "&sign=" + getSign(paraUri);

                Request.Builder signedRequestBuilder = request.newBuilder();
                signedRequestBuilder.url(baseURL + "?" + paraUri);
                request = signedRequestBuilder.build();
            }
            return chain.proceed(request);
        }
    }

    /**
     * 设置UA
     */
    static class UserAgentInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", Secret.User_Agent)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }
}

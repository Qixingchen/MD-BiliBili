package me.qixingchen.mdbilibili.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import me.qixingchen.mdbilibili.app.Secret;

/**
 * Created by Yulan on 2015/6/18.
 * 使用 Volley 请求,下载
 */
public class GetVolley {

    private static GetVolley mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private GetVolley(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(2000);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static GetVolley getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (GetVolley.class) {
                if (mInstance == null) {
                    mInstance = new GetVolley(context);
                }
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void addRequestWithSign(int method, String baseuri, Map<String, String> para,
                                   Response.Listener<String> OKListener, Response.ErrorListener errorListener) {
        String uri = baseuri + "?";
        String paraUri = getParaUriNoSigned(para);
        paraUri += "&sign=" + getSign(paraUri);
        uri += paraUri;
        StringRequest stringRequest = new StringRequest(method, uri, OKListener, errorListener);
        addToRequestQueue(stringRequest);
    }

    /**
     * A combination of request parameters,added App_Key
     * see {@link GetVolley getSign(String paraUri)}
     */
    private String getParaUriNoSigned(Map<String, String> para) {
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
     * Md5 encryption
     */
    private String getSign(String paraUri) {

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
}

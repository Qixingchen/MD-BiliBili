package me.qixingchen.mdbilibili.network;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.logger.Log;

/**
 * Created by Yulan on 2015/7/26.
 */
public class GetAPI {

    private Application mApplication;
    private String baseuri = "";
    private static GetAPI Instance;
    private static Response.Listener<String> mOKListener;
    private static Response.ErrorListener mErrorListener;

    private OnJsonGot onJsonGot;

    private static final String TAG = ViewAPI.class.getSimpleName();

    private GetAPI(String appendUri) {
        mApplication = BilibiliApplication.getApplication();
        baseuri = mApplication.getString(R.string.bilibili_api_host) + appendUri;
        mOKListener = makeOKListener();
        mErrorListener = makeErrorListener();
    }

    public static GetAPI getInstance(String appendUri) {

        Instance = new GetAPI(appendUri);
        return Instance;
    }

    public GetAPI setCallBack(OnJsonGot onJsonGot) {
        this.onJsonGot = onJsonGot;
        return Instance;
    }

    public void addRequest(Map<String, String> para) {
        Response.Listener<String> OKListener = mOKListener;
        Response.ErrorListener ErrorListener = mErrorListener;
        GetVolley.getmInstance(mApplication).addRequestWithSign(Request.Method.GET, baseuri, para, OKListener, ErrorListener);
    }

    private Response.Listener<String> makeOKListener() {
        Response.Listener<String> OKListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (onJsonGot != null) {
                    onJsonGot.JsonOK(response);
                    onJsonGot = null;
                }
            }
        };
        return OKListener;
    }

    private Response.ErrorListener makeErrorListener() {
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (onJsonGot != null) {
                    onJsonGot.JsonError(error.getMessage());
                    onJsonGot = null;
                } else {
                    Log.e(TAG, error.getMessage());
                }
            }
        };
        return errorListener;
    }

    public interface OnJsonGot {
        void JsonOK(String json);

        void JsonError(String errorMessage);
    }

}

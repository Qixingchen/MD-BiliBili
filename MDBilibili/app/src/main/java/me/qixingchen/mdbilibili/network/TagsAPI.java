package me.qixingchen.mdbilibili.network;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.Map;
import java.util.TreeMap;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.model.Tags;
import me.qixingchen.mdbilibili.utils.DLog;

/**
 * Created by Yulan on 2015/7/4.
 * 使用 Tags API 获取视频信息
 */
public class TagsAPI {
    private Application mApplication;
    private String baseuri = "";
    private static TagsAPI Instance;
    private static Gson gson;
    private static Response.Listener<String> mOKListener;
    private static Response.ErrorListener mErrorListener;

    private static OnJsonGot onJsonGot;

    private static final String TAG = TagsAPI.class.getSimpleName();

    private TagsAPI() {
        mApplication = BilibiliApplication.getApplication();
        baseuri = mApplication.getString(R.string.bilibili_api_host) + "tags";
        gson = new Gson();
    }

    public static TagsAPI getInstance() {
        if (Instance == null) {
            synchronized (TagsAPI.class) {
                if (Instance == null) {
                    Instance = new TagsAPI();
                }
            }
        }
        mOKListener = makeOKListener();
        mErrorListener = makeErrorListener();
        return Instance;
    }

    public void setOKListener(Response.Listener<String> OKListener) {
        mOKListener = OKListener;
    }

    public void setErrorListener(Response.ErrorListener ErrorListener) {
        mErrorListener = ErrorListener;
    }

    public static void setCallBack(OnJsonGot onJsonGot) {
        TagsAPI.onJsonGot = onJsonGot;
    }

    public void addRequest(Map<String, String> para) {
        Response.Listener<String> OKListener = mOKListener;
        Response.ErrorListener ErrorListener = mErrorListener;
        GetVolley.getmInstance(mApplication).addRequestWithSign(Request.Method.GET, baseuri, para, OKListener, ErrorListener);
        mOKListener = null;
        mErrorListener = null;
    }

    public void addRequest(String tag) {
        Map<String, String> tagspara = new TreeMap<>();
        tagspara.put("tag", tag);
        addRequest(tagspara);
    }

    private static Response.Listener<String> makeOKListener() {
        Response.Listener<String> OKListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Tags tags = gson.fromJson(response, Tags.class);
                if (onJsonGot != null) {
                    onJsonGot.TagsOK(tags);
                }
            }
        };
        return OKListener;
    }

    private static Response.ErrorListener makeErrorListener() {
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (onJsonGot != null) {
                    onJsonGot.TagsError(error.getMessage());
                } else {
                    DLog.e(error.getMessage());
                }
            }
        };
        return errorListener;
    }

    public interface OnJsonGot {
        void TagsOK(Tags tags);

        void TagsError(String errorMessage);
    }
}
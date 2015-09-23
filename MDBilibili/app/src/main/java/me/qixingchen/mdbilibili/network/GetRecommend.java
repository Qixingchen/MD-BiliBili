package me.qixingchen.mdbilibili.network;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.model.Recommend;

/**
 * Created by Yulan on 2015/6/18.
 * 使用 BiliBili 的 Recommend API 接口 获得 用户推荐信息
 */
public class GetRecommend {
    private static GetRecommend getRecommend;
    private Application application;
    private Gson gson = new Gson();
    private static final String TAG = GetRecommend.class.getSimpleName();
    private RequestQueue requestQueue;

    private RecommendCallBack recommendCallBack;

    public static GetRecommend getRecommend() {
        if (getRecommend == null) {
            synchronized (GetRecommend.class) {
                if (getRecommend == null) {
                    getRecommend = new GetRecommend();
                }
            }
        }
        return getRecommend;
    }

    private GetRecommend() {
        application = BilibiliApplication.getApplication();
        requestQueue = Volley.newRequestQueue(application);
    }

    public GetRecommend GetRecommendInfo(String tid) {
        String uri = application.getString(R.string.bilibili_api_host) +
                application.getString(R.string.recommend) + tid;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Recommend recommend = gson.fromJson(response, Recommend.class);
                recommendCallBack.recommendCallBack(recommend);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(application, "网络错误", Toast.LENGTH_LONG).show();
                Log.e(TAG, error.toString());
            }
        });
        requestQueue.add(stringRequest);
        return getRecommend;
    }

    public GetRecommend setCallBack(RecommendCallBack recommendCallBack) {
        this.recommendCallBack = recommendCallBack;
        return getRecommend;
    }

    public interface RecommendCallBack {
        void recommendCallBack(Recommend recommend);
    }
}

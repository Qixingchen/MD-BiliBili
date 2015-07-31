package me.qixingchen.mdbilibili.network;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.model.Topic;

/**
 * Created by Yulan on 2015/7/31.
 */
public class TopicApi {

    private Gson gson;
    private Application application;
    private OnJsonGot onJsonGot;
    private static TopicApi mInstance;
    private static final String TAG = TopicApi.class.getSimpleName();

    private TopicApi() {
        gson = new Gson();
        application = BilibiliApplication.getApplication();
    }

    public static TopicApi getInstance() {
        mInstance = new TopicApi();
        return mInstance;
    }

    public TopicApi setCallBack(OnJsonGot onJsonGot) {
        this.onJsonGot = onJsonGot;
        return mInstance;
    }

    public TopicApi addRequest() {
        Request<String> request = new StringRequest(Request.Method.GET, "http://www.bilibili.com/index/slideshow.json"
                , response -> {
                    Topic topic = gson.fromJson(response, Topic.class);
                    onJsonGot.TopicOK(topic);
                }, error -> onJsonGot.TopicError(error.getMessage()));
        GetVolley.getmInstance(application).addToRequestQueue(request);
        return mInstance;
    }


    public interface OnJsonGot {
        void TopicOK(Topic topic);

        void TopicError(String errorMessage);
    }
}

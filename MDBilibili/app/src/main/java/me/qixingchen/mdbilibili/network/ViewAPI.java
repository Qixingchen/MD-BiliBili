package me.qixingchen.mdbilibili.network;

import android.app.Application;

import com.android.volley.Response;
import com.google.gson.Gson;

import java.util.Map;
import java.util.TreeMap;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.model.Tags;
import me.qixingchen.mdbilibili.model.View;

/**
 * Created by Yulan on 2015/7/26.
 */
public class ViewAPI {

    private Application mApplication;
    private static ViewAPI Instance;
    private static Gson gson;

    private static OnJsonGot onJsonGot;

    private static final String TAG = ViewAPI.class.getSimpleName();

    private ViewAPI() {
        mApplication = BilibiliApplication.getApplication();
        gson = new Gson();
    }

    public static ViewAPI getInstance() {
        if (Instance == null) {
            synchronized (TagsAPI.class) {
                if (Instance == null) {
                    Instance = new ViewAPI();
                }
            }
        }
        return Instance;
    }

    public ViewAPI setCallBack(OnJsonGot onJsonGot) {
        ViewAPI.onJsonGot = onJsonGot;
        return Instance;
    }

    public void addRequest(String id, String page) {
        addRequest(id, page, "0");
    }

    public void addRequest(String id, String page, String fav) {
        Map<String, String> tagspara = new TreeMap<>();
        tagspara.put("id", id);
        tagspara.put("page", page);
        tagspara.put("fav", fav);
        GetAPI.getInstance("view").setCallBack(new GetAPI.OnJsonGot() {
            @Override
            public void JsonOK(String json) {
                View view = gson.fromJson(json, View.class);
                onJsonGot.ViewOK(view);
            }

            @Override
            public void JsonError(String errorMessage) {
                onJsonGot.ViewError(errorMessage);
            }
        }).addRequest(tagspara);
    }

    public interface OnJsonGot {
        void ViewOK(View views);

        void ViewError(String errorMessage);
    }

}

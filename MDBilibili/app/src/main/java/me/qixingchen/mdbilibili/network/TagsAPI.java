package me.qixingchen.mdbilibili.network;

import android.app.Application;

import com.google.gson.Gson;

import java.util.Map;
import java.util.TreeMap;

import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.model.Tags;

/**
 * Created by Yulan on 2015/7/4.
 * 使用 Tags API 获取视频信息
 */
public class TagsAPI {
    private Application mApplication;
    private static TagsAPI Instance;
    private static Gson gson;

    private static OnJsonGot onJsonGot;

    private static final String TAG = TagsAPI.class.getSimpleName();

    private TagsAPI() {
        mApplication = BilibiliApplication.getApplication();
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
        return Instance;
    }

    public void setCallBack(OnJsonGot onJsonGot) {
        TagsAPI.onJsonGot = onJsonGot;
    }

    public void addRequest(String tag) {
        Map<String, String> tagspara = new TreeMap<>();
        tagspara.put("tag", tag);
        GetAPI.getInstance("tags").setCallBack(new GetAPI.OnJsonGot() {
            @Override
            public void JsonOK(String json) {
                Tags tags = gson.fromJson(json, Tags.class);
                onJsonGot.TagsOK(tags);
            }

            @Override
            public void JsonError(String errorMessage) {
                onJsonGot.TagsError(errorMessage);
            }
        }).addRequest(tagspara);
    }

    public interface OnJsonGot {
        void TagsOK(Tags tags);

        void TagsError(String errorMessage);
    }
}
package me.qixingchen.mdbilibili.network;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import me.qixingchen.mdbilibili.model.Topic;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Yulan on 2015/7/31.
 * 首页主题json
 */
public class TopicApi {

    public static Observable<Topic> getTopic() {
        return Observable.create(new Observable.OnSubscribe<Topic>() {
            @Override
            public void call(Subscriber<? super Topic> subscriber) {
                Request request = new Request.Builder()
                        .url("http://www.bilibili.com/index/slideshow.json")
                        .build();
                try {
                    Response response = new OkHttpClient().newCall(request).execute();
                    if (response.isSuccessful()) {
                        Topic topic = new Gson().fromJson(response.body().string(), Topic.class);
                        subscriber.onNext(topic);
                    } else {
                        subscriber.onError(new Exception(response.message()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}

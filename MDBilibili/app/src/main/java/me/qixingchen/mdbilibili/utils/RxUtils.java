package me.qixingchen.mdbilibili.utils;

import com.google.gson.Gson;

import me.qixingchen.mdbilibili.network.Api;
import me.qixingchen.mdbilibili.network.OkHttpClientProvider;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dell on 2015/7/22.
 */
public class RxUtils {
    public static void unsubscribeIfNotNull(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public static CompositeSubscription getNewCompositeSubIfUnsubscribed(CompositeSubscription subscription) {
        if (subscription == null || subscription.isUnsubscribed()) {
            return new CompositeSubscription();
        }

        return subscription;
    }

    public static <T> T createApi(Class<T> c) {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("User-Agent", "MD-Bilibili/1.0");
                request.addHeader("Accept", "application/json; q=0.5");
            }
        };
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Api.URL)
                .setRequestInterceptor(requestInterceptor)
                .setClient(new OkClient(OkHttpClientProvider.get()))
                .setConverter(new GsonConverter(new Gson()))
                .build();
        return restAdapter.create(c);
    }
}

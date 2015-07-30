package me.qixingchen.mdbilibili.utils;

import com.google.gson.Gson;

import me.qixingchen.mdbilibili.network.Api;
import me.qixingchen.mdbilibili.network.OkHttpClientProvider;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dell on 2015/7/22.
 *
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
    static class FarbleError implements ErrorHandler {
        @Override public Throwable handleError(RetrofitError cause) {
            Response r = cause.getResponse();
            assert (r!=null);
                switch (r.getStatus()){
                    case 401:

                        break;
                    default:
                        break;
                }
            return cause;
        }
    }

    public static <T> T createApi(Class<T> c) {
        RequestInterceptor requestInterceptor = request -> {
            request.addHeader("User-Agent", "MD-Bilibili/1.0");
            request.addHeader("Accept", "application/json; q=0.5");
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
    public static <T> T createApi(Class<T> c,String url) {
        RequestInterceptor requestInterceptor = request -> {
            request.addHeader("User-Agent", "MD-Bilibili/1.0");
            request.addHeader("Accept", "application/json; q=0.5");
        };
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(url)
                .setRequestInterceptor(requestInterceptor)
                .setClient(new OkClient(OkHttpClientProvider.get()))
                .setConverter(new GsonConverter(new Gson()))
                .setErrorHandler(new FarbleError())
                .build();
        return restAdapter.create(c);
    }
}

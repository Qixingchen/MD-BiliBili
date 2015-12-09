package me.qixingchen.mdbilibili.network;

import me.qixingchen.mdbilibili.model.List;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Yulan on 2015/12/9.
 */
public class ListApi {
    public static Observable<List> getList(int pid) {
        return RetrofitNetwork.retrofitAPI.create(Api.ListApi.class).getList(pid, 2, 50).subscribeOn(Schedulers.io());
    }
}

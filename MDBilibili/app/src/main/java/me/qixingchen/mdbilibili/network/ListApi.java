package me.qixingchen.mdbilibili.network;

import me.qixingchen.mdbilibili.model.List;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Yulan on 2015/9/22.
 * 读取视频排行信息
 * https://github.com/fython/BilibiliAPIDocs/blob/master/API.list.md
 */
public class ListApi extends RetrofitNetworkAbs {

    private Api.ListApi listServer = RetrofitNetwork.retrofitAPI.create(Api.ListApi.class);

    public static ListApi getNewInstance() {
        return new ListApi();
    }

    public void getList(int tid) {
        listServer.getList(tid, 2, 50).enqueue(new Callback<List>() {
            @Override
            public void onResponse(Response<List> response, Retrofit retrofit) {
                myOnResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                myOnFailure(t);
            }
        });
    }

    public ListApi setNetworkListener(NetworkListener networkListener) {
        return setNetworkListener(networkListener, this);
    }
}

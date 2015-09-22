package me.qixingchen.mdbilibili.network;

import me.qixingchen.mdbilibili.model.List;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Yulan on 2015/9/22.
 */
public class ListApi extends RetrofitNetworkAbs {

    private Api.ListApi listServer = RetrofitNetwork.retrofitAPI.create(Api.ListApi.class);

    public void getList(int tid) {
        listServer.getList(tid, 2).enqueue(new Callback<List>() {
            @Override
            public void onResponse(Response<List> response) {
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

package me.qixingchen.mdbilibili.network;

import me.qixingchen.mdbilibili.model.RecommendM;
import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by Yulan on 2015/6/18.
 * 使用 BiliBili 的 Recommend API 接口 获得 用户推荐信息
 */
public class GetRecommend extends RetrofitNetworkAbs {

    private Api.RecommendApi recommendApiServer = RetrofitNetwork.retrofitAPI.create(Api.RecommendApi.class);

    public static GetRecommend getNewInstance() {
        return new GetRecommend();
    }

    public void GetRecommendInfo(int aid) {

        recommendApiServer.getRecommendApi(aid).enqueue(new Callback<RecommendM>() {
            @Override
            public void onResponse(retrofit.Response<RecommendM> response, Retrofit retrofit) {
                myOnResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                myOnFailure(t);
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public GetRecommend setNetworkListener(NetworkListener networkListener) {
        return setNetworkListener(networkListener, this);
    }

}

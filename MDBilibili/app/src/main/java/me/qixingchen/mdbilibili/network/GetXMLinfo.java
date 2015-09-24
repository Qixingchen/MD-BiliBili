package me.qixingchen.mdbilibili.network;

import me.qixingchen.mdbilibili.model.VideoM;
import retrofit.Callback;

/**
 * Created by Yulan on 2015/6/12.
 * 使用 BiliBili 的 HTML5 API 接口获得相关信息。
 */
public class GetXMLinfo extends RetrofitNetworkAbs {

    private Api.VideoApi videoApiSer = RetrofitNetwork.retrofitMain.create(Api.VideoApi.class);

    public static GetXMLinfo getNewInstance() {
        return new GetXMLinfo();
    }

    public void getUri(final String Aid) {

        videoApiSer.getVideoApi(Aid).enqueue(new Callback<VideoM>() {
            @Override
            public void onResponse(retrofit.Response<VideoM> response) {
                myOnResponse(response);
            }

            @Override
            public void onFailure(Throwable t) {
                myOnFailure(t);
            }
        });
    }

    @Override
    public GetXMLinfo setNetworkListener(NetworkListener networkListener) {
        return setNetworkListener(networkListener, this);
    }


}

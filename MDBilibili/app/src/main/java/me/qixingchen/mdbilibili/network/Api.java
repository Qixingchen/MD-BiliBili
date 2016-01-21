package me.qixingchen.mdbilibili.network;

import me.qixingchen.mdbilibili.model.FeedbackM;
import me.qixingchen.mdbilibili.model.List;
import me.qixingchen.mdbilibili.model.RecommendM;
import me.qixingchen.mdbilibili.model.SearchM;
import me.qixingchen.mdbilibili.model.UserNameM;
import me.qixingchen.mdbilibili.model.UserUidM;
import me.qixingchen.mdbilibili.model.VideoHDM;
import me.qixingchen.mdbilibili.model.VideoM;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Farble on 2015/7/28 22.
 */
public interface Api {
    String URL = "http://api.bilibili.cn/";
    String VIDEO_URL = "http://www.bilibili.com/";

    interface SearchApi {
        @GET("search")
        Observable<SearchM> doSearch(@Query("keyword") String keyword, @Query("page") int page,
                                     @Query("pagesize") int pagesize, @Query("order") String order);
    }

    interface RecommendApi {

        @GET("author_recommend")
        Observable<RecommendM> getRecommendApi(@Query("aid") int aid);
    }

    interface FeedBackApi {

        @GET("feedback")
        Observable<FeedbackM> getFeedBack(
                @Query("aid") int aid,
                @Query("page") int page,
                @Query("pagesize") int pageSize,
                @Query("ver") int ver);

        /**
         * 获取视频评论 <a href="https://github.com/fython/BilibiliAPIDocs/blob/master/API.feedback.md">API.feedback</a>
         *
         * @param aid      AV号
         * @param page     页码
         * @param pageSize 单页返回的记录条数，最大不超过300，默认为10。
         * @param order    API版本,最新是3
         * @param ver      排序方式 默认按发布时间倒序 可选：good 按点赞人数排序 hot 按热门回复排序
         */
        @GET("feedback")
        Observable<FeedbackM> getFeedBack(
                @Query("aid") int aid,
                @Query("page") int page,
                @Query("pagesize") int pageSize,
                @Query("order") String order,
                @Query("ver") int ver);
    }

    interface UserInfoBynameApi {

        @GET("userinfo")
        Observable<UserNameM> getUserInfoBynameApi(@Query("user") String user);
    }

    interface UserInfoByuidApi {

        @GET("userinfo")
        Observable<UserUidM> getUserInfoByuidApi(@Query("uid") String uid);
    }

    interface View {
        @GET("view")
        Observable<me.qixingchen.mdbilibili.model.View> getViewInfo(
                @Query("id") int aid,
                @Query("page") int page
        );

        @GET("view")
        Observable<me.qixingchen.mdbilibili.model.View> getViewInfo(
                @Query("id") int aid,
                @Query("page") int page,
                @Query("fav") int fav
        );
    }

    interface VideoApi {

        /**
         * 获取高清视频接口
         * 请求位于 http://interface.bilibili.com/
         * 海外需要使用 /playurl 接口
         */
        @GET("v_cdn_play")
        Observable<VideoHDM> getVideoApi(
                @Query("otype") String otype,
                @Query("cid") String cid,
                @Query("type") String type,
                @Query("quality") int quality,
                @Query("appkey") String appkey
        );

        @GET("m/html5")
        Observable<VideoM> getVideoApiRx(@Query("aid") int aid);
    }

    interface ListApi {
        @GET("list")
        Observable<List> getList(
                @Query("tid") int tid,
                @Query("ver") int ver,
                @Query("pagesize") int pageSize
        );
    }
}

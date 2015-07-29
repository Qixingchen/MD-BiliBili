package me.qixingchen.mdbilibili.network;

import me.qixingchen.mdbilibili.model.FeedbackM;
import me.qixingchen.mdbilibili.model.RecommendM;
import me.qixingchen.mdbilibili.model.SearchM;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Farble on 2015/7/28 22.
 */
public interface Api {
    String URL = "http://api.bilibili.cn";

    interface SearchApi {
        @GET("/search")
        Observable<SearchM> doSearch(@Query("keyword") String keyword, @Query("page") int page,
                                     @Query("pagesize") int pagesize, @Query("order") String order);
    }

    interface RecommendApi {

        @GET("/author_recommend")
        Observable<RecommendM> getRecommendApi(@Query("aid") int aid);
    }

    interface FeedBackApi {

        @GET("/feedback")
        Observable<FeedbackM> getFeedBackApiApi(@Query("aid") int aid, @Query("ver") int ver);
    }
}

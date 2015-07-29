package me.qixingchen.mdbilibili.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Farble on 2015/7/29 21.
 * @see <a href="http://docs.bilibili.cn/wiki/API.author_recommend">API.author recommend</a>
 */
public class RecommendM implements Serializable{
    private static final long serialVersionUID = 8214811642021127335L;
    private int code;
    private List<RecommendVideo>list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<RecommendVideo> getList() {
        return list;
    }

    public void setList(List<RecommendVideo> list) {
        this.list = list;
    }

    public static class RecommendVideo{
        private int aid;
        private String title;
        private String cover;
        private int click;
        private int review;
        private int favorites;
        private int video_review;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getClick() {
            return click;
        }

        public void setClick(int click) {
            this.click = click;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getFavorites() {
            return favorites;
        }

        public void setFavorites(int favorites) {
            this.favorites = favorites;
        }

        public int getReview() {
            return review;
        }

        public void setReview(int review) {
            this.review = review;
        }

        public int getVideo_review() {
            return video_review;
        }

        public void setVideo_review(int video_review) {
            this.video_review = video_review;
        }
    }
}

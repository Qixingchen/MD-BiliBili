package me.qixingchen.mdbilibili.model;

import java.util.List;

/**
 * Created by Yulan on 2015/7/5.
 * Bilibili 由 Tags 获取视频
 * http://docs.bilibili.cn/wiki/API.tags
 */
public class Tags {

    private int code;
    private int pages;
    private int num;
    private List<ListEntity> list;

    public void setCode(int code) {
        this.code = code;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getCode() {
        return code;
    }

    public int getPages() {
        return pages;
    }

    public int getNum() {
        return num;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public class ListEntity {
        /**
         * play : 36
         * favorites : 0
         * coins : 1
         * author : 雏凤纸
         * description : sm26132153 静心，夜咏，思见，静夜思，月初上，窗清凉。
         * pic : http://i1.hdslb.com/u_user/ba6a5b706349564f2ce0b3ae5856add4.jpg
         * title : 【ピヨミ】『月』【歌ってみた】
         * duration : 3:53
         * review : 3
         * subtitle :
         * create : 2015-07-05 09:01
         * video_review : 1
         * credit : 0
         * aid : 2527115
         * typename : 翻唱
         */
        private String play;
        private String favorites;
        private String coins;
        private String author;
        private String description;
        private String pic;
        private String title;
        private String duration;
        private String review;
        private String subtitle;
        private String create;
        private String video_review;
        private String credit;
        private String aid;
        private String typename;

        public void setPlay(String play) {
            this.play = play;
        }

        public void setFavorites(String favorites) {
            this.favorites = favorites;
        }

        public void setCoins(String coins) {
            this.coins = coins;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setCreate(String create) {
            this.create = create;
        }

        public void setVideo_review(String video_review) {
            this.video_review = video_review;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getPlay() {
            return play;
        }

        public String getFavorites() {
            return favorites;
        }

        public String getCoins() {
            return coins;
        }

        public String getAuthor() {
            return author;
        }

        public String getDescription() {
            return description;
        }

        public String getPic() {
            return pic;
        }

        public String getTitle() {
            return title;
        }

        public String getDuration() {
            return duration;
        }

        public String getReview() {
            return review;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getCreate() {
            return create;
        }

        public String getVideo_review() {
            return video_review;
        }

        public String getCredit() {
            return credit;
        }

        public String getAid() {
            return aid;
        }

        public String getTypename() {
            return typename;
        }
    }
}

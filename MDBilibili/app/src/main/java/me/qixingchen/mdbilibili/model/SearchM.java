package me.qixingchen.mdbilibili.model;

import java.io.Serializable;

/**
 * Created by Farble on 2015/7/28 23.
 * see {@link http://docs.bilibili.cn/wiki/API.search}
 */
public class SearchM implements Serializable {
    private String type;//mylist 我的列表 video 视频 special 专题
    private int lid;//我的列表编号 当type为mylist时才会出现
    private int aid;//视频编号 当type为video时才会出现
    private int spid;//专题编号 当type为special时才会出现
    private String author;//视频作者
    private int play;//播放次数
    private int review;//评论数
    private int video_review;//弹幕数
    private int favorites;//收藏数
    private String title;//视频/mylist/专题标题
    private String description;//视频/mylist/专题描述
    private String tag;//视频/mylist/专题关键字
    private String pic;//封面图片地址

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPlay() {
        return play;
    }

    public void setPlay(int play) {
        this.play = play;
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

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}

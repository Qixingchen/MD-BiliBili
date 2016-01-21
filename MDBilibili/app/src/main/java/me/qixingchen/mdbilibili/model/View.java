package me.qixingchen.mdbilibili.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import me.qixingchen.mdbilibili.utils.TimeUtils;

/**
 * Created by Yulan on 2015/7/26.
 */
public class View extends BaseObservable {

    /**
     * play : 722310
     * favorites : 2925
     * offsite : http://share.acg.tv/flash.swf?aid=7&page=1
     * description : ohyeah.导演!快给全人类发便当~
     * mid : 2
     * instant_server : chat.bilibili.com
     * created_at : 2009-06-26 15:11
     * pic : http://i0.hdslb.com/userup/2/1246000E2-91M.jpg
     * title : 2012地球便当之日宣传片
     * spid : null
     * tid : 82
     * pages : 1
     * allow_feed : 0
     * review : 7349
     * allow_bp : 0
     * tag : B站最早的视频,考古的尽头,历史的起点,祖坟
     * video_review : 14517
     * credit : 3905
     * partname :
     * coins : 670
     * src : c
     * author : 碧诗
     * created : 1246000296
     * face : http://i1.hdslb.com/account/face/2/305df2a4/myface.png
     * typename : 电影相关
     * cid : 3625120
     */
    private String play;
    private String favorites;
    private String offsite;
    private String description;
    private String mid;
    private String instant_server;
    private String created_at;
    private String pic;
    private String title;
    private String spid;
    private int tid;
    private int pages;
    private int allow_feed;
    private String review;
    private int allow_bp;
    private String tag;
    private String video_review;
    private String credit;
    private String partname;
    private String coins;
    private String src;
    private String author;
    private int created;
    private String face;
    private String typename;
    private int cid;

    @Bindable
    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    @Bindable
    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getOffsite() {
        return offsite;
    }

    public void setOffsite(String offsite) {
        this.offsite = offsite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getInstant_server() {
        return instant_server;
    }

    public void setInstant_server(String instant_server) {
        this.instant_server = instant_server;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getAllow_feed() {
        return allow_feed;
    }

    public void setAllow_feed(int allow_feed) {
        this.allow_feed = allow_feed;
    }

    @Bindable
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getAllow_bp() {
        return allow_bp;
    }

    public void setAllow_bp(int allow_bp) {
        this.allow_bp = allow_bp;
    }

    @Bindable
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Bindable
    public String getVideo_review() {
        return video_review;
    }

    public void setVideo_review(String video_review) {
        this.video_review = video_review;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Bindable
    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Bindable
    public String getReadableCreateTime() {
        return TimeUtils.getReadableTime(created);
    }
}

package me.qixingchen.mdbilibili.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2015/7/30.
 *
 * @see <a http://docs.bilibili.cn/wiki/API.userinfo">API.userinfo</a>
 * query user-info by username
 */
public class UserNameM implements Serializable {
    private static final long serialVersionUID = -830079317879143340L;
    private int mid;
    private String name;
    private boolean approve;
    private String sex;
    private int rank;
    private String face;
    private String DisplayRank;
    private int regtime;
    private String birthday;
    private String place;
    private String description;
    private int article;
    private java.util.List<Integer> attentions;
    private int fans;
    private int friend;
    private int attention;
    private String sign;
    private int code;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getDisplayRank() {
        return DisplayRank;
    }

    public void setDisplayRank(String displayRank) {
        DisplayRank = displayRank;
    }

    public int getRegtime() {
        return regtime;
    }

    public void setRegtime(int regtime) {
        this.regtime = regtime;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public List<Integer> getAttentions() {
        return attentions;
    }

    public void setAttentions(List<Integer> attentions) {
        this.attentions = attentions;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getFriend() {
        return friend;
    }

    public void setFriend(int friend) {
        this.friend = friend;
    }

    public int getAttention() {
        return attention;
    }

    public void setAttention(int attention) {
        this.attention = attention;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

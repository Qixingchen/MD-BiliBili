package me.qixingchen.mdbilibili.model;

import java.io.Serializable;

/**
 * Created by dell on 2015/7/30.
 *
 * @see <a http://docs.bilibili.cn/wiki/API.userinfo">API.userinfo</a>
 * query user-info by uid
 */
public class UserUidM implements Serializable {
    private static final long serialVersionUID = -772402971588304193L;
    private int mid;//会员ID
    private String uname;//暱名
    private String userid;//unknown
    private String face;//小头像
    private int rank;//帐号显示标识
    private String DisplayRank;//unknown
    private String sex;//性别 (男/女/不明)
    private String sign;//unknown
    private int code;//unknown

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getDisplayRank() {
        return DisplayRank;
    }

    public void setDisplayRank(String displayRank) {
        DisplayRank = displayRank;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

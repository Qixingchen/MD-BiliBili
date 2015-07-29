package me.qixingchen.mdbilibili.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Farble on 2015/7/29 22.
 *
 * @see <a http://docs.bilibili.cn/wiki/API.feedback">API.feedback</a>
 */
public class FeedbackM implements Serializable {
    private static final long serialVersionUID = 6940275995599257743L;
    private int results;
    private int pages;
    private int isAdmin;
    private boolean needCode;
    private int owner;
    private List<Feed> hotList;
    private List<Feed> list;

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isNeedCode() {
        return needCode;
    }

    public void setNeedCode(boolean needCode) {
        this.needCode = needCode;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public List<Feed> getHotList() {
        return hotList;
    }

    public void setHotList(List<Feed> hotList) {
        this.hotList = hotList;
    }

    public List<Feed> getList() {
        return list;
    }

    public void setList(List<Feed> list) {
        this.list = list;
    }

    public static class Feed {
        private String mid;
        private int lv;
        private int fbid;
        private String msg;
        private String ad_check;
        private String face;
        private int rank;
        private String nick;

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public int getLv() {
            return lv;
        }

        public void setLv(int lv) {
            this.lv = lv;
        }

        public int getFbid() {
            return fbid;
        }

        public void setFbid(int fbid) {
            this.fbid = fbid;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getAd_check() {
            return ad_check;
        }

        public void setAd_check(String ad_check) {
            this.ad_check = ad_check;
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

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }
    }
}

package me.qixingchen.mdbilibili.model;

import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import me.qixingchen.mdbilibili.utils.TimeUtils;

/**
 * Created by Farble on 2015/7/29 22.
 *
 * @see <a https://github.com/fython/BilibiliAPIDocs/blob/master/API.feedback.md">API.feedback</a>
 */
public class FeedbackM implements Serializable {

    /**
     * results : 9286
     * page : 3
     * pages : 4643
     * isAdmin : 0
     * needCode : false
     * owner : 2
     * hotList : [{"mid":313670,"lv":2763,"fbid":"14810447","ad_check":0,"good":4083,"isgood":0,"msg":"ヽ(`Д´)ﾉ淦! 请告诉我从AV1开始一个一个试的不止我一个人!","device":"","create":1402745491,"create_at":"2014-06-14 19:31","reply_count":470,"face":"http://i2.hdslb.com/52_52/52_52/user/3136/313670/myface.jpg","rank":1001,"nick":"吟风丶凌","level_info":{"current_level":4,"current_min":7000,"current_exp":8486,"next_exp":20000},"sex":"男"},{"mid":422,"lv":1,"fbid":"161","ad_check":0,"good":1312,"isgood":0,"msg":"2","device":"","create":1247495193,"create_at":"2009-07-13 22:26","reply_count":362,"face":"http://static.hdslb.com/images/member/noface.gif","rank":10000,"nick":"TDGUP主","level_info":{"current_level":1,"current_min":0,"current_exp":85,"next_exp":200},"sex":""},{"mid":1316060,"lv":1633,"fbid":"9290684","ad_check":0,"good":1268,"isgood":0,"msg":"(〜￣△￣)〜 为了找寻人类足迹首次出现在B站的起源，我义无反顾的踏上了旅途，终于在2013年10月18日21时38分时发现这一神迹，为了见证这一伟大奇迹的诞生，我不得不发一条评论来证明我曾经在人类历史遗迹中留下浓厚的一笔，我的子孙啊，如果你将来有幸看到我的这条评论，希望你能为你的祖先感到骄傲和自豪，因为你的祖先可是全人类第1681位见证这一奇迹的人啊。 〜(￣△￣〜)","device":"","create":1382103666,"create_at":"2013-10-18 21:41","reply_count":41,"face":"http://i1.hdslb.com/52_52/52_52/user/13160/1316060/myface.jpg","rank":10000,"nick":"(+﹏+)~晕！！！","level_info":{"current_level":4,"current_min":7000,"current_exp":7991,"next_exp":20000},"sex":""}]
     * list : [{"mid":10270206,"lv":9378,"fbid":"78029201","ad_check":0,"good":0,"isgood":0,"msg":"考古纪念( \u2022̀∀\u2022́ )","device":"","create":1453358402,"create_at":"2016-01-21 14:40","reply_count":0,"face":"http://i1.hdslb.com/52_52/52_52/account/face/10270206/4381de2a/myface.png","rank":10000,"nick":"白崎祐","level_info":{"current_level":2,"current_min":200,"current_exp":1480,"next_exp":1500},"sex":"男","reply":null},{"mid":1667652,"lv":9377,"fbid":"78022670","ad_check":0,"good":0,"isgood":0,"msg":"考古（￣▽￣）","device":"","create":1453353933,"create_at":"2016-01-21 13:25","reply_count":0,"face":"http://i0.hdslb.com/52_52/52_52/user/16676/1667652/myface.jpg","rank":10000,"nick":"萌系万岁","level_info":{"current_level":3,"current_min":1500,"current_exp":2631,"next_exp":7000},"sex":"男","reply":null}]
     */

    @SerializedName("results")
    private int results;
    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int pages;
    @SerializedName("isAdmin")
    private int isAdmin;
    @SerializedName("needCode")
    private boolean needCode;
    @SerializedName("owner")
    private int owner;
    /**
     * mid : 313670
     * lv : 2763
     * fbid : 14810447
     * ad_check : 0
     * good : 4083
     * isgood : 0
     * msg : ヽ(`Д´)ﾉ淦! 请告诉我从AV1开始一个一个试的不止我一个人!
     * device :
     * create : 1402745491
     * create_at : 2014-06-14 19:31
     * reply_count : 470
     * face : http://i2.hdslb.com/52_52/52_52/user/3136/313670/myface.jpg
     * rank : 1001
     * nick : 吟风丶凌
     * level_info : {"current_level":4,"current_min":7000,"current_exp":8486,"next_exp":20000}
     * sex : 男
     */

    @SerializedName("hotList")
    private java.util.List<HotListEntity> hotList;
    /**
     * mid : 10270206
     * lv : 9378
     * fbid : 78029201
     * ad_check : 0
     * good : 0
     * isgood : 0
     * msg : 考古纪念( •̀∀•́ )
     * device :
     * create : 1453358402
     * create_at : 2016-01-21 14:40
     * reply_count : 0
     * face : http://i1.hdslb.com/52_52/52_52/account/face/10270206/4381de2a/myface.png
     * rank : 10000
     * nick : 白崎祐
     * level_info : {"current_level":2,"current_min":200,"current_exp":1480,"next_exp":1500}
     * sex : 男
     * reply : null
     */

    @SerializedName("list")
    private List<ListEntity> list;

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public List<HotListEntity> getHotList() {
        return hotList;
    }

    public void setHotList(List<HotListEntity> hotList) {
        this.hotList = hotList;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class HotListEntity {
        @SerializedName("mid")
        private int mid;
        @SerializedName("lv")
        private int lv;
        @SerializedName("fbid")
        private String fbid;
        @SerializedName("ad_check")
        private int adCheck;
        @SerializedName("good")
        private int good;
        @SerializedName("isgood")
        private int isgood;
        @SerializedName("msg")
        private String msg;
        @SerializedName("device")
        private String device;
        @SerializedName("create")
        private int create;
        @SerializedName("create_at")
        private String createAt;
        @SerializedName("reply_count")
        private int replyCount;
        @SerializedName("face")
        private String face;
        @SerializedName("rank")
        private int rank;
        @SerializedName("nick")
        private String nick;
        /**
         * current_level : 4
         * current_min : 7000
         * current_exp : 8486
         * next_exp : 20000
         */

        @SerializedName("level_info")
        private LevelInfoEntity levelInfo;
        @SerializedName("sex")
        private String sex;

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getLv() {
            return lv;
        }

        public void setLv(int lv) {
            this.lv = lv;
        }

        public String getFbid() {
            return fbid;
        }

        public void setFbid(String fbid) {
            this.fbid = fbid;
        }

        public int getAdCheck() {
            return adCheck;
        }

        public void setAdCheck(int adCheck) {
            this.adCheck = adCheck;
        }

        public int getGood() {
            return good;
        }

        public void setGood(int good) {
            this.good = good;
        }

        public int getIsgood() {
            return isgood;
        }

        public void setIsgood(int isgood) {
            this.isgood = isgood;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public int getCreate() {
            return create;
        }

        public void setCreate(int create) {
            this.create = create;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
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

        public LevelInfoEntity getLevelInfo() {
            return levelInfo;
        }

        public void setLevelInfo(LevelInfoEntity levelInfo) {
            this.levelInfo = levelInfo;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public static class LevelInfoEntity {
            @SerializedName("current_level")
            private int currentLevel;
            @SerializedName("current_min")
            private int currentMin;
            @SerializedName("current_exp")
            private int currentExp;
            @SerializedName("next_exp")
            private int nextExp;

            public int getCurrentLevel() {
                return currentLevel;
            }

            public void setCurrentLevel(int currentLevel) {
                this.currentLevel = currentLevel;
            }

            public int getCurrentMin() {
                return currentMin;
            }

            public void setCurrentMin(int currentMin) {
                this.currentMin = currentMin;
            }

            public int getCurrentExp() {
                return currentExp;
            }

            public void setCurrentExp(int currentExp) {
                this.currentExp = currentExp;
            }

            public int getNextExp() {
                return nextExp;
            }

            public void setNextExp(int nextExp) {
                this.nextExp = nextExp;
            }
        }
    }

    public static class ListEntity {
        @SerializedName("mid")
        private int mid;
        @SerializedName("lv")
        private String lv;
        @SerializedName("fbid")
        private String fbid;
        @SerializedName("ad_check")
        private int adCheck;
        @SerializedName("good")
        private String good;
        @SerializedName("isgood")
        private int isgood;
        @SerializedName("msg")
        private String msg;
        @SerializedName("device")
        private String device;
        @SerializedName("create")
        private int create;
        @SerializedName("create_at")
        private String createAt;
        @SerializedName("reply_count")
        private int replyCount;
        @SerializedName("face")
        private String face;
        @SerializedName("rank")
        private int rank;
        @SerializedName("nick")
        private String nick;
        /**
         * current_level : 2
         * current_min : 200
         * current_exp : 1480
         * next_exp : 1500
         */

        @SerializedName("level_info")
        private LevelInfoEntity levelInfo;
        @SerializedName("sex")
        private String sex;
        @SerializedName("reply")
        private Object reply;

        public int getMid() {
            return mid;
        }

        public String getLv() {
            return "#" + lv;
        }

        public String getFbid() {
            return fbid;
        }

        public int getAdCheck() {
            return adCheck;
        }

        public String getGood() {
            return good;
        }

        public int getIsgood() {
            return isgood;
        }

        public String getMsg() {
            return msg;
        }

        public String getDevice() {
            return device;
        }

        public int getCreate() {
            return create;
        }

        @Bindable
        public String getReadableCreate() {
            return TimeUtils.getReadableTime(create);
        }

        public String getCreateAt() {
            return createAt;
        }

        public int getReplyCount() {
            return replyCount;
        }

        @Bindable
        public String getFace() {
            return face;
        }

        public int getRank() {
            return rank;
        }

        public String getNick() {
            return nick;
        }

        public LevelInfoEntity getLevelInfo() {
            return levelInfo;
        }

        public String getSex() {
            return sex;
        }

        public Object getReply() {
            return reply;
        }

        public static class LevelInfoEntity {
            @SerializedName("current_level")
            private int currentLevel;
            @SerializedName("current_min")
            private int currentMin;
            @SerializedName("current_exp")
            private int currentExp;
            @SerializedName("next_exp")
            private int nextExp;

            public int getCurrentLevel() {
                return currentLevel;
            }

            public void setCurrentLevel(int currentLevel) {
                this.currentLevel = currentLevel;
            }

            public int getCurrentMin() {
                return currentMin;
            }

            public void setCurrentMin(int currentMin) {
                this.currentMin = currentMin;
            }

            public int getCurrentExp() {
                return currentExp;
            }

            public void setCurrentExp(int currentExp) {
                this.currentExp = currentExp;
            }

            public int getNextExp() {
                return nextExp;
            }

            public void setNextExp(int nextExp) {
                this.nextExp = nextExp;
            }
        }
    }
}

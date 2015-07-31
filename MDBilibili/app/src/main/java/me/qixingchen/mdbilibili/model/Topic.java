package me.qixingchen.mdbilibili.model;

import java.util.*;
import java.util.List;

/**
 * Created by Yulan on 2015/7/31.
 */
public class Topic {

    private java.util.List<ListEntity> list;
    private int results;

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public int getResults() {
        return results;
    }

    public static class ListEntity {
        /**
         * img : http://i1.hdslb.com/u_user/dc4afce34e0d5600796ce4567ade5987.jpg
         * simg :
         * link : http://www.bilibili.com/video/av2659006/?br
         * title : 青春×机关枪 05话
         */
        private String img;
        private String simg;
        private String link;
        private String title;

        public void setImg(String img) {
            this.img = img;
        }

        public void setSimg(String simg) {
            this.simg = simg;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public String getSimg() {
            return simg;
        }

        public String getLink() {
            return link;
        }

        public String getTitle() {
            return title;
        }
    }
}

package me.qixingchen.mdbilibili.model;

import java.util.List;

/**
 * Created by Yulan on 2015/12/8.
 * 高清视频接口
 */
public class VideoHDM {

    /**
     * from : local
     * result : suee
     * format : hdmp4
     * timelength : 71000
     * accept_format : mp4,hdmp4
     * accept_quality : [3,0]
     * seek_param : start
     * seek_type : offset
     * durl : [{"order":1,"length":71000,"size":16158814,"url":"http://ws.acgvideo.com/5/04/5303183-1hd.mp4?wsTime=1449594451&wsSecret2=a7b09d7285855447bb50b557e279592a&oi=1879439584&appkey=7fa41267a72cd358&or=1880708358","backup_url":["http://cn-gdgz5-cmcc.acgvideo.com/vg3/c/5b/5303183.mp4?expires=1449594300&ssig=9R2cq1NNNYgsetKGCu31sw&oi=1879439584&appkey=7fa41267a72cd358&or=1880708358&rate=0","http://ws.acgvideo.com/5/04/5303183-1.mp4?wsTime=1449594451&wsSecret2=0e49a3c8537d06b460b35dfa4cc9e5a8&oi=1879439584&appkey=7fa41267a72cd358&or=1880708358"]}]
     */

    private String from;
    private String result;
    private String format;
    private int timelength;
    private String accept_format;
    private String seek_param;
    private String seek_type;
    private List<Integer> accept_quality;
    /**
     * order : 1
     * length : 71000
     * size : 16158814
     * url : http://ws.acgvideo.com/5/04/5303183-1hd.mp4?wsTime=1449594451&wsSecret2=a7b09d7285855447bb50b557e279592a&oi=1879439584&appkey=7fa41267a72cd358&or=1880708358
     * backup_url : ["http://cn-gdgz5-cmcc.acgvideo.com/vg3/c/5b/5303183.mp4?expires=1449594300&ssig=9R2cq1NNNYgsetKGCu31sw&oi=1879439584&appkey=7fa41267a72cd358&or=1880708358&rate=0","http://ws.acgvideo.com/5/04/5303183-1.mp4?wsTime=1449594451&wsSecret2=0e49a3c8537d06b460b35dfa4cc9e5a8&oi=1879439584&appkey=7fa41267a72cd358&or=1880708358"]
     */

    private List<DurlEntity> durl;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getTimelength() {
        return timelength;
    }

    public void setTimelength(int timelength) {
        this.timelength = timelength;
    }

    public String getAccept_format() {
        return accept_format;
    }

    public void setAccept_format(String accept_format) {
        this.accept_format = accept_format;
    }

    public String getSeek_param() {
        return seek_param;
    }

    public void setSeek_param(String seek_param) {
        this.seek_param = seek_param;
    }

    public String getSeek_type() {
        return seek_type;
    }

    public void setSeek_type(String seek_type) {
        this.seek_type = seek_type;
    }

    public List<Integer> getAccept_quality() {
        return accept_quality;
    }

    public void setAccept_quality(List<Integer> accept_quality) {
        this.accept_quality = accept_quality;
    }

    public List<DurlEntity> getDurl() {
        return durl;
    }

    public void setDurl(List<DurlEntity> durl) {
        this.durl = durl;
    }

    public static class DurlEntity {
        private int order;
        private int length;
        private int size;
        private String url;
        private List<String> backup_url;

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getBackup_url() {
            return backup_url;
        }

        public void setBackup_url(List<String> backup_url) {
            this.backup_url = backup_url;
        }
    }
}

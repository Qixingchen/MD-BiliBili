package me.qixingchen.mdbilibili.model;

import java.io.Serializable;

/**
 * Created by dell on 2015/7/30.
 */
public class VideoM implements Serializable {
    private static final long serialVersionUID = -4733041504602744142L;
    private String img;
    private String cid;
    private String src;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

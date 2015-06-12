package me.qixingchen.mdbilibili.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yulan on 2015/6/12.
 * BiliBili 的 html5 API 接口。
 * http://www.bilibili.com/m/html5?aid=[av号]
 * 可以获得 img ：视频封面
 * cid ：弹幕XML地址
 * src ： 视频源地址
 */
public class HTML5 implements Parcelable {
	private String img, cid, src;

	public String getImg() {
		return img;
	}

	public String getCid() {
		return cid;
	}

	public String getSrc() {
		return src;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(img);
		dest.writeString(cid);
		dest.writeString(src);
	}
}

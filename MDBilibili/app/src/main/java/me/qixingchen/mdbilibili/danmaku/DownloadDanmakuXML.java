package me.qixingchen.mdbilibili.danmaku;

/**
 * Created by Yulan on 2015/6/12.
 * 用于调用XML下载，然而现在并没有什么琴梨用
 */
public class DownloadDanmakuXML {
	private DownloadDanmakuXML downloadDanmakuXML;

	private DownloadDanmakuXML() {
	}

	public DownloadDanmakuXML getDownloadDanmakuXML() {
		if (downloadDanmakuXML == null) {
			synchronized (DownloadDanmakuXML.class) {
				if (downloadDanmakuXML == null) {
					downloadDanmakuXML = new DownloadDanmakuXML();
				}
			}
		}
		return downloadDanmakuXML;
	}

	public void startDownloadByAid(String Aid) {
		//GetXMLinfo.getGetXMLinfo().getUri(Aid);
	}
}

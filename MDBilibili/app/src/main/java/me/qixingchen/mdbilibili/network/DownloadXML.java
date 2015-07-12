package me.qixingchen.mdbilibili.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.tool.Tool;

/**
 * Created by Yulan on 2015/6/12.
 * 下载弹幕 XML 的 AsyncTask 。
 * 但是目前下载的 XML 连长度都不对。。
 * vsv:添加一点回调
 */
public class DownloadXML extends AsyncTask<String, Integer, String> {

	private final static String TAG = DownloadXML.class.getSimpleName();
	private CallBack callBack;

	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}

	protected String doInBackground(String... params) {
		URL url;
		String filename = null;
		BufferedInputStream inputStream = null;
		BufferedOutputStream outputStream = null;
		FileOutputStream fileStream = null;
		URLConnection connection = null;
		final int DOWNLOAD_BUFFER_SIZE = 1024;
		for (String uriString : params) {
			try {
				url = new URL(uriString);
				connection = url.openConnection();
				connection.setUseCaches(false);

				if (Tool.isExternalStorageAvailable()) {
					filename = uriString.substring(uriString.lastIndexOf('=') + 1) + ".xml";
					File file = new File(BilibiliApplication.getApplication().getExternalFilesDir
							("danmaku"), filename);
					if (file.exists()) {
						file.delete();
					}
					inputStream = new BufferedInputStream(connection.getInputStream());
					fileStream = new FileOutputStream(file);
					outputStream = new BufferedOutputStream(fileStream, DOWNLOAD_BUFFER_SIZE);
					byte[] data = new byte[DOWNLOAD_BUFFER_SIZE];
					int bytesRead = 0;
					while ((bytesRead = inputStream.read(data, 0, data.length)) >= 0) {
						outputStream.write(data, 0, bytesRead);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
			} finally {
				try {
					if (outputStream != null)
						outputStream.close();
					if (inputStream != null)
						inputStream.close();
					if (fileStream != null)
						fileStream.close();
				} catch (IOException ignored) {
				}


			}
		}
		return filename;
	}

	public interface CallBack {
		void onXmlSuccess();
		void onXmlError();
	}

	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);
		if (s.compareTo("error") != 0) {
			callBack.onXmlSuccess();
		}
	}
}

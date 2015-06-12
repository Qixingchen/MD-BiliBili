package me.qixingchen.mdbilibili.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import me.qixingchen.mdbilibili.Player;
import me.qixingchen.mdbilibili.app.App;
import me.qixingchen.mdbilibili.tool.Tool;

/**
 * Created by Yulan on 2015/6/12.
 * 下载弹幕 XML 的 AsyncTask 。
 * 但是目前下载的 XML 连长度都不对。。
 */
public class DownloadXML extends AsyncTask<String, Integer, String> {

	private final static String TAG = DownloadXML.class.getSimpleName();

	protected String doInBackground(String... params) {
		URL url = null;
		String filename = null;
		InputStream input = null;
		OutputStream output = null;
		HttpURLConnection connection = null;
		for (String uriString : params) {
			try {
				url = new URL(uriString);
				filename = uriString.substring(uriString.lastIndexOf('/') + 1);
				connection = (HttpURLConnection) url.openConnection();
				int length = connection.getContentLength();
				if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
					String errorMessage = "Server returned HTTP " + connection
							.getResponseCode()
							+ " " + connection.getResponseMessage();
					Log.e(TAG, errorMessage);
				}

				input = connection.getInputStream();

				if (Tool.isExternalStorageAvlilable()) {
					File file = new File(App.getApplication().getExternalFilesDir("danmuku"), filename);
					if (file.exists()) {
						//todo 弹幕更新
						//file.delete();
						return filename;
					}
					output = new FileOutputStream(file);
					byte data[] = new byte[1024 * 8];
					int count;
					while ((count = input.read(data)) != -1) {
						output.write(data, 0, count);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
			} finally {
				try {
					if (output != null)
						output.close();
					if (input != null)
						input.close();
				} catch (IOException ignored) {
				}

				if (connection != null)
					connection.disconnect();
			}
		}
		return filename;
	}


	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);
		if (s.compareTo("error") != 0) {
			Player.xmlIsOK();
		}
	}
}

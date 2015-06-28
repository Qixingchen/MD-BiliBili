package me.qixingchen.mdbilibili;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.android.DanmakuGlobalConfig;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.network.DownloadXML;
import me.qixingchen.mdbilibili.network.GetXMLinfo;


public class Player extends ActionBarActivity implements GetXMLinfo.SendSrc,
		SurfaceHolder.Callback, MediaPlayer.OnPreparedListener, MediaPlayer
				.OnBufferingUpdateListener, DownloadXML.XMLDownloadOK {

	public  Activity mActivity;
	private String mXMLFileName;
	private MediaPlayer mMediaPlayer;
	private SurfaceView mPlayerView;
	private IDanmakuView mDanmakuView;
	private SurfaceHolder mSurfaceHolder;
	private static BaseDanmakuParser mDanmakuParser;
	private static String mSrc;

	private static final String TAG = Player.class.getSimpleName();


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
		mPlayerView = (SurfaceView) findViewById(R.id.playerView);
		mSurfaceHolder = mPlayerView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mActivity = this;
		DanmakuGlobalConfig.DEFAULT.setDanmakuStyle(DanmakuGlobalConfig.DANMAKU_STYLE_STROKEN, 3)
				.setDuplicateMergingEnabled(false).setMaximumVisibleSizeInScreen(80);
		mDanmakuView.enableDanmakuDrawingCache(true);
		Intent intent = getIntent();
		String aid = intent.getStringExtra("AID");
		GetXMLinfo.getGetXMLinfo(this).getUri(aid);

		View decorView = getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
			mDanmakuView.resume();
		}
		if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
			mMediaPlayer.start();
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mMediaPlayer!=null){
			mMediaPlayer.pause();
		}

		if (mDanmakuView != null && mDanmakuView.isPrepared()) {
			mDanmakuView.pause();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (mDanmakuView != null) {
			// dont forget release!
			mDanmakuView.release();
			mDanmakuView = null;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mMediaPlayer != null) {
			mMediaPlayer.release();
		}
		if (mDanmakuView != null) {
			mDanmakuView.release();
		}
	}

	//弹幕加载 传入文件流
	private static BaseDanmakuParser createParser(InputStream stream) {

		if (stream == null) {
			return new BaseDanmakuParser() {

				@Override
				protected Danmakus parse() {
					return new Danmakus();
				}
			};
		}

		ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

		try {
			loader.load(stream);
		} catch (IllegalDataException e) {
			e.printStackTrace();
		}
		BaseDanmakuParser parser = new BiliDanmukuParser();
		IDataSource<?> dataSource = loader.getDataSource();
		parser.load(dataSource);
		return parser;

	}

	//来自 GetXMLinfo 的回调通告
	@Override
	public void getSrcAndXMLFileName(String Src, String XMLUri) {

		mXMLFileName = XMLUri.substring(XMLUri.lastIndexOf('/') + 1);
		String CID = mXMLFileName.substring(0, mXMLFileName.lastIndexOf("."));
		XMLUri = "http://www.bilibilijj.com/ashx/Barrage" +
				".ashx?f=true&av=&p=&s=xml&cid=" + CID + "&n=" + CID;
		//开始下载 XML
		DownloadXML downloadXML = new DownloadXML();
		downloadXML.setXmlDownloadOK(this);
		downloadXML.execute(XMLUri);
		mSrc = Src;
	}

	//DownloadXML 回调用此处 告知 XML 下载完成
	@Override
	public void xmlIsOK() {
		File xmlfile = new File(BilibiliApplication.getApplication().getExternalFilesDir("danmaku"),
				mXMLFileName);
		try {
			InputStream inputStream = new FileInputStream(xmlfile);
			mDanmakuParser = createParser(inputStream);
			mDanmakuView.prepare(mDanmakuParser);
			startPlay();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//视频预加载完毕 开始播放视频与弹幕
	@Override
	public void onPrepared(MediaPlayer mp) {
		int videoWidth = mMediaPlayer.getVideoWidth();
		int videoHeight = mMediaPlayer.getVideoHeight();
		if (videoHeight != 0 && videoWidth != 0) {
			mp.start();
			mDanmakuView.start();
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setDisplay(mSurfaceHolder);
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.setOnBufferingUpdateListener(this);
			mMediaPlayer.setOnPreparedListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		startPlay();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		//todo 进度
	}

	//判断能否开始播放 嘤嘤嘤 渣实现求不骂。。
	private  void startPlay() {
		if (mMediaPlayer != null && mXMLFileName != null && mSrc != null && new File(BilibiliApplication.getApplication().getExternalFilesDir("danmaku"), mXMLFileName).exists()) {
			try {
				if (mMediaPlayer.isPlaying()) {
					mMediaPlayer.stop();
				}
				mMediaPlayer.reset();
				mMediaPlayer.setDataSource(mSrc);
				mMediaPlayer.prepare();//prepare之后自动播放
			} catch (IllegalArgumentException | IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}

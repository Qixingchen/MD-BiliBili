package me.qixingchen.mdbilibili;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
import me.qixingchen.mdbilibili.ui.widget.MediaController;
import me.qixingchen.mdbilibili.ui.widget.VideoView;
import me.qixingchen.mdbilibili.utils.DLog;

public class Player extends AppCompatActivity implements GetXMLinfo.SendSrc, DownloadXML.XMLDownloadOK {

    public Activity mActivity;
    private String mXMLFileName;
    private VideoView mPlayerView;
    private IDanmakuView mDanmakuView;
    private View mBufferingIndicator;
    private MediaController mMediaController;

    private static BaseDanmakuParser mDanmakuParser;
    private String mVideoSrc = "";
    private static final String TAG = Player.class.getSimpleName();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //find views
        mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
        mPlayerView = (VideoView) findViewById(R.id.playerView);
        mBufferingIndicator = findViewById(R.id.buffering_indicator);
        mMediaController = new MediaController(this);
        mActivity = this;
        DanmakuGlobalConfig.DEFAULT.setDanmakuStyle(DanmakuGlobalConfig.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false).setMaximumVisibleSizeInScreen(80);
        mDanmakuView.enableDanmakuDrawingCache(true);
        Intent intent = getIntent();
        String aid = intent.getStringExtra("AID");
        GetXMLinfo.getGetXMLinfo(this).getUri(aid);

        //init playerview
        mPlayerView.setMediaController(mMediaController);
        mPlayerView.setMediaBufferingIndicator(mBufferingIndicator);
        //TODO set URL or path
        mPlayerView.requestFocus();

        mDanmakuView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
        //TODO 读取上次的进度
        if (mPlayerView != null && !mPlayerView.isPlaying()) {
            mPlayerView.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayerView != null) {
            mPlayerView.pause();
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
        if (mPlayerView != null) {
            mPlayerView.destroyDrawingCache();
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

        DLog.i(XMLUri);
        DLog.i(Src);
        mVideoSrc = Src;
        mPlayerView.setVideoURI(Uri.parse(mVideoSrc));
        mPlayerView.start();
        //TODO 弹幕在视频播放前加载
        //TODO 播放器解码失败时重试
        //TODO 弹幕视频同步
        //TODO 修改代码结构，重写文件下载
        //TODO 错误提示
        //开始下载 XML
		DownloadXML downloadXML = new DownloadXML();
		downloadXML.setXmlDownloadOK(this);
		downloadXML.execute(XMLUri);

    }

    //DownloadXML 回调用此处 告知 XML 下载完成
    @Override
    public void xmlIsOK() {
        File xmlfile = new File(BilibiliApplication.getApplication().getExternalFilesDir("danmaku"),
                mXMLFileName);
        DLog.i("danmu download ok");
        try {
            InputStream inputStream = new FileInputStream(xmlfile);
            mDanmakuParser = createParser(inputStream);
            mDanmakuView.prepare(mDanmakuParser);
//			startPlay();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

	//判断能否开始播放 嘤嘤嘤 渣实现求不骂。。
    //预计会改为更渣的实现
//	private  void startPlay() {
//		if (mMediaPlayer != null && mXMLFileName != null && mSrc != null && new File(BilibiliApplication.getApplication().getExternalFilesDir("danmaku"), mXMLFileName).exists()) {
//			try {
//				if (mMediaPlayer.isPlaying()) {
//					mMediaPlayer.stop();
//				}
//				mMediaPlayer.reset();
//				mMediaPlayer.setDataSource(mSrc);
//				mMediaPlayer.prepare();//prepare之后自动播放
//			} catch (IllegalArgumentException | IllegalStateException | IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}

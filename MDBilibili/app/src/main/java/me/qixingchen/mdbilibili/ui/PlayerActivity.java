package me.qixingchen.mdbilibili.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.android.DanmakuGlobalConfig;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.logger.Log;
import me.qixingchen.mdbilibili.model.VideoM;
import me.qixingchen.mdbilibili.network.DownloadXML;
import me.qixingchen.mdbilibili.network.GetXMLinfo;
import me.qixingchen.mdbilibili.network.RetrofitNetworkAbs;
import me.qixingchen.mdbilibili.ui.widget.MediaController;
import me.qixingchen.mdbilibili.ui.widget.VideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class PlayerActivity extends AppCompatActivity implements DownloadXML.CallBack {

    private static final String TAG = PlayerActivity.class.getSimpleName();
    private static BaseDanmakuParser mDanmakuParser;
    public Activity mActivity;
    private String mXMLFileName;
    private VideoView mPlayerView;
    private IDanmakuView mDanmakuView;
    private View mBufferingIndicator;
    private MediaController mMediaController;
    private String mVideoSrc = "";
    private Boolean isPlayerPrepared;
    //页面切换时，播放到的位置
    private int LastPosition = 0;
    //VideoView OnInfo 当正在缓冲时等事件会被调用
    // todo 显示缓冲提示
    private IMediaPlayer.OnInfoListener onInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer mp, int what, int extra) {
            if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_START) {
                if (mDanmakuView != null && mDanmakuView.isPrepared()) {
                    mDanmakuView.pause();
                    if (mBufferingIndicator != null)
                        mBufferingIndicator.setVisibility(View.VISIBLE);
                }
            } else if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_END) {
                if (mDanmakuView != null && mDanmakuView.isPaused()) {
                    mDanmakuView.resume();
                    if (mBufferingIndicator != null)
                        mBufferingIndicator.setVisibility(View.GONE);
                }
            }
            return true;
        }
    };
    //视频缓冲完成
    private IMediaPlayer.OnPreparedListener onPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer mp) {
            //            if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            //                if (!mPlayerView.isPlaying()) {
            //                    mPlayerView.start();
            //                }
            //                mDanmakuView.start();
            //                Log.e(TAG, "弹幕先加载完成");
            //            }
            isPlayerPrepared = true;
        }
    };
    //跳转
    private IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(IMediaPlayer mp) {
            if (mDanmakuView != null && mDanmakuView.isPrepared()) {
                mDanmakuView.seekTo(mp.getCurrentPosition());
            }

        }
    };
    //播放完成
    private IMediaPlayer.OnCompletionListener onCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer mp) {
            if (mDanmakuView != null && mDanmakuView.isPrepared()) {
                mDanmakuView.pause();
                mDanmakuView.seekTo((long) 0);

            }
            mPlayerView.pause();
        }
    };
    //被控制条控制状态
    private VideoView.OnControllerEventsListener onControllerEventsListener = new VideoView.OnControllerEventsListener() {
        @Override
        public void onVideoPause() {
            if (mDanmakuView != null && mDanmakuView.isPrepared()) {
                mDanmakuView.pause();
            }
        }

        @Override
        public void OnVideoResume() {
            if (mDanmakuView != null && mDanmakuView.isPaused() && mPlayerView.isPlaying()) {
                mDanmakuView.resume();
            }
        }
    };

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

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

        //init playerView
        mPlayerView.setMediaController(mMediaController);
        mPlayerView.setMediaBufferingIndicator(mBufferingIndicator);
        mPlayerView.setOnInfoListener(onInfoListener);
        mPlayerView.setOnPreparedListener(onPreparedListener);
        mPlayerView.setOnSeekCompleteListener(onSeekCompleteListener);
        mPlayerView.setOnCompletionListener(onCompletionListener);
        mPlayerView.setOnControllerEventsListener(onControllerEventsListener);
        mPlayerView.requestFocus();

        GetXMLinfo.getNewInstance().setNetworkListener(new RetrofitNetworkAbs.NetworkListener() {
            @Override
            public void onOK(Object ts) {
                VideoM html5 = (VideoM) ts;
                String CIDName = html5.getCid();
                if (CIDName.compareTo("http://comment.bilibili.com/undefined.xml") == 0) {
                    Toast.makeText(mActivity, "视频不存在或不能播放", Toast.LENGTH_LONG).show();
                    return;
                }
                CIDName = CIDName.replace(".com", ".cn");
                //向 player 发送 视频源和弹幕 XML 文件名
                getSrcAndXMLFileName(html5.getSrc(), CIDName);
            }

            @Override
            public void onError(String Message) {
                Toast.makeText(mActivity, "视频不存在或不能播放:" + Message, Toast.LENGTH_LONG).show();
            }
        }).getUri(aid);
        isPlayerPrepared = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
            mDanmakuView.seekTo((long) LastPosition);
        }
        //todo 看看能不能保留缓冲的视频
        if (mPlayerView != null && !mPlayerView.isPlaying()) {
            mPlayerView.start();
            mPlayerView.seekTo(LastPosition);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayerView != null) {
            LastPosition = mPlayerView.getCurrentPosition();
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
        if (mPlayerView != null && mPlayerView.isDrawingCacheEnabled()) {
            mPlayerView.destroyDrawingCache();
        }
        if (mDanmakuView != null && mDanmakuView.isPaused()) {
            mDanmakuView.release();
        }
    }

    //来自 GetXMLinfo 的回调通告
    public void getSrcAndXMLFileName(String Src, String XMLUri) {

        mXMLFileName = XMLUri.substring(XMLUri.lastIndexOf('/') + 1);
        String CID = mXMLFileName.substring(0, mXMLFileName.lastIndexOf("."));
        XMLUri = "http://www.bilibilijj.com/ashx/Barrage" +
                ".ashx?f=true&av=&p=&s=xml&cid=" + CID + "&n=" + CID;

        //DLog.i(XMLUri);
        mVideoSrc = Src;
        //开始下载 XML
        DownloadXML downloadXML = new DownloadXML();
        downloadXML.setCallBack(this);
        downloadXML.execute(XMLUri);

        //DLog.i(Src);
        //TODO 播放器解码失败时重试
        //TODO 修改代码结构，重写文件下载
        //TODO 错误提示

    }

    //DownloadXML 回调用此处 告知 XML 下载完成
    @Override
    public void onXmlSuccess() {
        File xmlfile = new File(BilibiliApplication.getApplication().getExternalFilesDir("danmaku"),
                mXMLFileName);
        //DLog.i("danmu download ok");
        try {
            InputStream inputStream = new FileInputStream(xmlfile);
            mDanmakuParser = createParser(inputStream);
            mDanmakuView.setCallback(new DrawHandler.Callback() {
                @Override
                public void prepared() {
                    if (mPlayerView != null && isPlayerPrepared) {
                        if (!mPlayerView.isPlaying()) {
                            mPlayerView.start();
                        }
                        Log.e(TAG, String.valueOf(mDanmakuView.isPrepared()));
                        mDanmakuView.start();
                        Log.e(TAG, "视频先加载完成");
                    }
                }

                @Override
                public void updateTimer(DanmakuTimer danmakuTimer) {

                }
            });
            mDanmakuView.prepare(mDanmakuParser);
            mPlayerView.setVideoURI(Uri.parse(mVideoSrc));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onXmlError() {
        //先这么写
        Toast.makeText(this, "弹幕加载错误", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}

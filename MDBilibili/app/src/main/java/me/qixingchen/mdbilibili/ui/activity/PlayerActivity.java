package me.qixingchen.mdbilibili.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.model.VideoM;
import me.qixingchen.mdbilibili.network.Api;
import me.qixingchen.mdbilibili.network.DownloadXML;
import me.qixingchen.mdbilibili.network.RetrofitNetwork;
import me.qixingchen.mdbilibili.ui.base.BaseActivity;
import me.qixingchen.mdbilibili.widget.MediaController;
import me.qixingchen.mdbilibili.widget.VideoView;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class PlayerActivity extends BaseActivity {

    private static final String TAG = PlayerActivity.class.getSimpleName();
    private static BaseDanmakuParser mDanmakuParser;
    public Activity mActivity;
    private String mXMLFileName;
    private VideoView mPlayerView;
    private IDanmakuView mDanmakuView;
    private View mBufferingIndicator;
    private MediaController mMediaController;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_player;
    }

    @Override
    protected void bindView() {
        mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
        mPlayerView = (VideoView) findViewById(R.id.playerView);
        mBufferingIndicator = findViewById(R.id.buffering_indicator);
    }

    @Override
    protected void initData() {
        mMediaController = new MediaController(this);
        mActivity = this;
        mDanmakuView.enableDanmakuDrawingCache(true);
        Intent intent = getIntent();
        String aid = intent.getStringExtra("AID");
        mPlayerView.setMediaController(mMediaController);
        mPlayerView.setMediaBufferingIndicator(mBufferingIndicator);
        mPlayerView.requestFocus();

        Observable<VideoM> observable = RetrofitNetwork.retrofitMain.create(Api.VideoApi.class).getVideoApiRx(aid)
                .subscribeOn(Schedulers.io());
        //danmaku
        Observable<Object> danmakuObservable =
                observable
                        .map(new Func1<VideoM, String>() {
                            @Override
                            public String call(VideoM videoM) {
                                if (videoM.getCid().contentEquals("undefined")) {
                                    return "error";
                                }
                                mXMLFileName = videoM.getCid().substring(videoM.getCid().lastIndexOf('/') + 1);
                                String CID = mXMLFileName.substring(0, mXMLFileName.lastIndexOf("."));
                                return "http://www.bilibilijj.com/ashx/Barrage" +
                                        ".ashx?f=true&av=&p=&s=xml&cid=" + CID + "&n=" + CID;
                            }
                        })
                        .flatMap(new Func1<String, Observable<File>>() {
                            @Override
                            public Observable<File> call(String string) {
                                if (string.equals("error")) {
                                    return Observable.error(new Exception("视频不存在或不能播放"));
                                }
                                return new DownloadXML().downloadXML(string);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(new Func1<File, Observable<?>>() {
                            @Override
                            public Observable<?> call(File file) {
                                return preparDanmaku(file);
                            }
                        });

        //video
        Observable<Object> videoObservable =
                observable
                        .map(new Func1<VideoM, Uri>() {
                            @Override
                            public Uri call(VideoM videoM) {
                                return Uri.parse(videoM.getSrc());
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(new Func1<Uri, Observable<?>>() {
                            @Override
                            public Observable<?> call(Uri uri) {
                                return preparVideo(uri);
                            }
                        });

        Observable
                .merge(videoObservable, danmakuObservable)
                .last()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        mPlayerView.start();
                        mDanmakuView.start();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }

    @Override
    protected void bindEvent() {
        mPlayerView.setOnInfoListener(onInfoListener);
        mPlayerView.setOnSeekCompleteListener(onSeekCompleteListener);
        mPlayerView.setOnCompletionListener(onCompletionListener);
        mPlayerView.setOnControllerEventsListener(onControllerEventsListener);
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
    //TODO 播放器解码失败时重试
    //TODO 修改代码结构，重写文件下载
    //TODO 错误提示

    //视频加载
    public Observable preparVideo(final Uri src) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                mPlayerView.setVideoURI(src);
                mPlayerView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(IMediaPlayer mp) {
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }

    //danmaku加载
    public Observable preparDanmaku(final File xmlFile) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                try {
                    InputStream inputStream = new FileInputStream(xmlFile);
                    mDanmakuParser = createParser(inputStream);
                    mDanmakuView.setCallback(new DrawHandler.Callback() {
                        @Override
                        public void prepared() {
                            subscriber.onCompleted();
                        }

                        @Override
                        public void updateTimer(DanmakuTimer danmakuTimer) {

                        }

                        @Override
                        public void drawingFinished() {

                        }
                    });
                    mDanmakuView.prepare(mDanmakuParser, new DanmakuContext());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
}

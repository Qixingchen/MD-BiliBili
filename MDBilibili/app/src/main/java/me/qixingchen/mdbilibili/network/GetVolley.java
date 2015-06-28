package me.qixingchen.mdbilibili.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Yulan on 2015/6/18.
 * 使用 Volley 下载等
 */
public class GetVolley {

	private static GetVolley mInstance;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private static Context mCtx;

	public static GetVolley getmInstance(Context context) {
		if (mInstance == null) {
			synchronized (GetVolley.class) {
				if (mInstance == null) {
					mInstance = new GetVolley(context);
				}
			}
		}
		return mInstance;
	}

	private GetVolley(Context context) {
		mCtx = context;
		mRequestQueue = getRequestQueue();

		mImageLoader = new ImageLoader(mRequestQueue,
				new ImageLoader.ImageCache() {
					private final LruCache<String, Bitmap>
							cache = new LruCache<String, Bitmap>(20);

					@Override
					public Bitmap getBitmap(String url) {
						return cache.get(url);
					}

					@Override
					public void putBitmap(String url, Bitmap bitmap) {
						cache.put(url, bitmap);
					}
				});
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}

	public ImageLoader getImageLoader() {
		return mImageLoader;
	}
}

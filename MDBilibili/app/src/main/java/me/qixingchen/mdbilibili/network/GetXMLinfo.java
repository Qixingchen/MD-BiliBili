package me.qixingchen.mdbilibili.network;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import me.qixingchen.mdbilibili.R;
import me.qixingchen.mdbilibili.app.BilibiliApplication;
import me.qixingchen.mdbilibili.model.HTML5;

/**
 * Created by Yulan on 2015/6/12.
 * 使用 BiliBili 的 HTML5 API 接口获得相关信息。
 */
public class GetXMLinfo {
    private static GetXMLinfo GetXMLinfo;
    private Application application;
    private Gson gson = new Gson();
    private static SendSrc msendSrc;

    private RequestQueue requestQueue;

    private static final String TAG = GetXMLinfo.class.getSimpleName();

    private GetXMLinfo() {
        application = BilibiliApplication.getApplication();
        requestQueue = Volley.newRequestQueue(application);
    }

    public static GetXMLinfo getGetXMLinfo(SendSrc sendSrc) {
        if (GetXMLinfo == null) {
            synchronized (GetXMLinfo.class) {
                if (GetXMLinfo == null) {
                    GetXMLinfo = new GetXMLinfo();
                }
            }
        }
        msendSrc = sendSrc;
        return GetXMLinfo;
    }

    public void getUri(final String Aid) {
        String uri = application.getString(R.string.bilibili_host)
                + application.getString(R.string.html5) + Aid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                HTML5 html5 = gson.fromJson(response, HTML5.class);
                String CIDName = html5.getCid();
                if (CIDName.compareTo("http://comment.bilibili.com/undefined.xml") == 0) {
                    Toast.makeText(application, "视频不存在或不能播放", Toast.LENGTH_LONG).show();
                    msendSrc.getSrcAndXMLFileName("Error", CIDName);
                    return;
                }
                CIDName = CIDName.replace(".com", ".cn");
                //向 player 发送 视频源和弹幕 XML 文件名
                msendSrc.getSrcAndXMLFileName(html5.getSrc(), CIDName);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(application, "网络错误", Toast.LENGTH_LONG).show();
                Log.e(TAG, error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }


    public interface SendSrc {
        void getSrcAndXMLFileName(String Src, String XMLUrl);
    }

}

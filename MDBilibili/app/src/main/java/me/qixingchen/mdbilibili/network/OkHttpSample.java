package me.qixingchen.mdbilibili.network;

import android.os.Bundle;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import me.qixingchen.mdbilibili.R;

/**
 * Created by Farble on 2015/7/5 20.
 * just a sample used by okhttp
 */
public class OkHttpSample extends AppCompatActivity implements HttpCommon {
    private static final String TAG = "OkHttpSample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //just for test
        setContentView(R.layout.dast_activity_about);
        new NetRequestSample().requestSample(this, "bilibili", "test");
    }

    @Override
    public void onNetWorkComplete(String method, Object values) {
        if (method.equals("requestSample")) {
            //get data
        }
    }

    @Override
    public void onException(Exception e) {

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onCancel() {

    }

    class NetRequestSample extends OkHttpProtocol {
        private String url = "";
        private final OkHttpClient client = OkHttpClientProvider.getForNonIdempotent();

        public void requestSample(HttpCommon listener, final String username, final String password) {
            final OkHttpHandler handler = getHandler(listener, "requestSample");
            new HandlerThread("login") {
                public void run() {
                    RequestBody formBody = new FormEncodingBuilder()
                            .add("username", username)
                            .add("password", password)
                            .build();
                    Request request = new Request.Builder()
                            .url(url)
                            .header("User-Agent", "MD-Bilibili")
                            .addHeader("Accept", "application/json; q=0.5")
                            .post(formBody)
                            .build();

                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                        if (!response.isSuccessful()) {
                            handler.sendMessage(HttpCommon.MSG_NET_DATA_FAIL, "");
                            throw new IOException("Unexpected code " + response);
                        } else {
                            //get json data
                        }
                    } catch (IOException e) {
                        handler.sendMessage(HttpCommon.MSG_NET_DATA_FAIL, "");
                        e.printStackTrace();
                    }

                }

                ;
            }.start();
        }
    }
}

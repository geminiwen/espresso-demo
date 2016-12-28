package xyz.geminiwen.espresso;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by geminiwen on 28/12/2016.
 */

public class WebActivity extends Activity implements View.OnClickListener {

    private String mBaseUrl = "http://baidu.com";

    private OkHttpClient mOkHttpClient =  new OkHttpClient.Builder().build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(this);
    }

    public void setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    @Override
    public void onClick(View v) {
        final Toast toast = Toast.makeText(WebActivity.this, "", Toast.LENGTH_LONG);
        mOkHttpClient.newCall(new Request.Builder().url(mBaseUrl).build())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("webActivity", "failure", e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String content = response.body().string();
                        toast.setText(content);
                        toast.show();
                    }
                });
    }
}

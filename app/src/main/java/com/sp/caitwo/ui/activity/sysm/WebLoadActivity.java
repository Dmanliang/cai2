package com.sp.caitwo.ui.activity.sysm;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.util.DateUtil;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.WebViewUtil;
import com.sp.xwjlibrary.widget.RoundProgressBar;

public class WebLoadActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;
    private RoundProgressBar progressBar;
    public static final String URL = "com.sp.caitwo.ui.activity.sysm.web.load.activity.url";
    public static final String TITLE = "com.sp.caitwo.ui.activity.sysm.web.load.activity.title";
    public static final String OTHER_INFO = "com.sp.caitwo.ui.activity.sysm.web.load.activity.other.info";
    private TextView tvTitle;
    private TextView tvMsgTitle;
    private TextView tvTime;
    private TextView tvDelMtvsg;
    private LinearLayout llyMsgDetail;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_load);

        initUI();

        setWeb();
    }

    private void setWeb() {
        String url = getIntent().getStringExtra(URL);
        String title = getIntent().getStringExtra(TITLE);
        tvTitle.setText(title);

        if (url.startsWith("http") || url.startsWith("https") || url.startsWith("www") || url.startsWith("wap")){
            llyMsgDetail.setVisibility(View.GONE);
            loadHttp(url);
        }else if (BaseUtil.equals(title,"消息详情") || BaseUtil.equals(title,"公告详情")){
            if (BaseUtil.equals(title,"消息详情")){
                tvDelMtvsg.setText("删除");
                tvDelMtvsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InterfaceTask.getInstance().delCommonMsg(WebLoadActivity.this,id);
                    }
                });
            }
            String otherInfo = getIntent().getStringExtra(OTHER_INFO);
            String[] split = otherInfo.split(",");
            tvMsgTitle.setText(split[0]);
            tvTime.setText(DateUtil.getTime(split[1]));
            id = Integer.parseInt(split[2]);
            progressBar.setVisibility(View.GONE);
            llyMsgDetail.setVisibility(View.VISIBLE);
            WebViewUtil.loadHtmlText(webView,url);
        }else {
            progressBar.setVisibility(View.GONE);
            WebViewUtil.loadHtmlText(webView,url);
        }

    }

    private void loadHttp(String url) {
        WebViewUtil.loadUrl(webView, url, new WebViewUtil.OnWebViewListener() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initUI() {
        webView = findViewById(R.id.web_view);
        progressBar = findViewById(R.id.round_progress_bar);
        tvTitle = findViewById(R.id.tv_top_title);
        tvMsgTitle = findViewById(R.id.tv_msg_title);
        tvTime = findViewById(R.id.tv_time);
        tvDelMtvsg = findViewById(R.id.icon_right);
        llyMsgDetail = findViewById(R.id.lly_msg_detail);
        findViewById(R.id.iv_top_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (webView.copyBackForwardList().getCurrentIndex() <= 0)
            finish();
        else
            webView.goBack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.clearCache(true);
    }
}

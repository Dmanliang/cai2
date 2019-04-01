package com.sp.caitwo.ui.activity.activitycenter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.ActivityCenterBean;
import com.sp.xwjlibrary.util.BitmapUtil;
import com.sp.xwjlibrary.util.HolderUtil;
import com.sp.xwjlibrary.util.WebViewUtil;

import org.xutils.XCallback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.Serializable;

public class ActivityDetailActivity extends AppCompatActivity {

    private ImageView ivTopImg;
    private TextView tvSubTitle;
    private WebView wvContent;
    private ActivityCenterBean.DataBean dataBean;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dataBean = (ActivityCenterBean.DataBean) getIntent().getSerializableExtra("dataBean");

        ivTopImg = findViewById(R.id.iv_top_img);
        tvSubTitle = findViewById(R.id.tv_sub_title);
        wvContent = findViewById(R.id.wv_content);
        tvTitle = findViewById(R.id.tv_top_title);
        findViewById(R.id.iv_top_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitle.setText(dataBean.getTitle());
        ImageOptions build = new ImageOptions.Builder().setImageScaleType(ImageView.ScaleType.FIT_XY).build();
        x.image().bind(ivTopImg,dataBean.getImg_url(),build,
                new XCallback<Drawable>(){
                    @Override
                    public void onSuccess(Drawable result) {
                        Bitmap bitmap = BitmapUtil.drawableToBitmap(result);
                        Bitmap bmp = BitmapUtil.createRoundConerImage(bitmap,0);
                        ivTopImg.setImageBitmap(bmp);
                        int scale = bitmap.getHeight() * 100 / bitmap.getWidth();
                        HolderUtil.setHolderScale(ivTopImg,scale + "/100");
                    }
                });
        tvSubTitle.setText(dataBean.getSub_title());
        WebViewUtil.loadHtmlText(wvContent,dataBean.getContent());
    }
}

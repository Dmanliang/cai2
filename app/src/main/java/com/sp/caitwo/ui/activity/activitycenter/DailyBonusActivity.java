package com.sp.caitwo.ui.activity.activitycenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.BetBonusBean;
import com.sp.caitwo.bean.DailyBonusBean;
import com.sp.caitwo.ui.adapter.lottery.DailyBonusAdapter;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.BitmapUtil;
import com.sp.xwjlibrary.util.HolderUtil;

import org.xutils.XCallback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * 每日加奖
 */
public class DailyBonusActivity extends AppCompatActivity {

    private TextView mTvZrzz, tv_dqdj, tv_jjbl, tv_kdjj, tv_lq;
    private RecyclerView mRecycler;
    private ImageView image;
    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_bonus);

        imgUrl = getIntent().getStringExtra("imgUrl");
        initView();
        initData();
    }

    private void initData() {
        if(!BaseUtil.isEmpty(imgUrl)){
            ImageOptions build = new ImageOptions.Builder().setImageScaleType(ImageView.ScaleType.FIT_XY).build();
            x.image().bind(image,imgUrl,build,new XCallback<Drawable>(){
                @Override
                public void onSuccess(Drawable result) {
                    Bitmap bitmap = BitmapUtil.drawableToBitmap(result);
                    Bitmap bmp = BitmapUtil.createRoundConerImage(bitmap,0);
                    image.setImageBitmap(bmp);
                    int scale = bitmap.getHeight() * 100 / bitmap.getWidth();
                    HolderUtil.setHolderScale(image,scale + "/100");
                }
            });
        }
        InterfaceTask.getInstance().getBetBonus(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                BetBonusBean homeWanFaBean = (BetBonusBean) obj;
                if(homeWanFaBean!=null&&homeWanFaBean.getData()!=null){
                    mTvZrzz.setText(String.valueOf(homeWanFaBean.getData().getYesterdayBetMoney()));
                    tv_dqdj.setText(String.format(getString(R.string.vip_level_int), homeWanFaBean.getData().getUserLevel()));
                    tv_jjbl.setText(String.format("%s%%", homeWanFaBean.getData().getBonusRate()));
                    tv_kdjj.setText(String.valueOf(homeWanFaBean.getData().getUserBetBonus()));
                    if(homeWanFaBean.getData().getUserBetBonus() > 0){
                        tv_lq.setEnabled(true);
                        tv_lq.setBackground(getResources().getDrawable(R.drawable.shape_rect_blue));
                        tv_lq.setText("点击领取");
                    }else {
                        tv_lq.setEnabled(false);
                    }
                }

            }
        });

        InterfaceTask.getInstance().getsBonusRateLis(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                DailyBonusBean homeWanFaBean = (DailyBonusBean) obj;
                mRecycler.setAdapter(new DailyBonusAdapter(DailyBonusActivity.this,homeWanFaBean.getData()));
            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_top_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView title =  findViewById(R.id.tv_top_title);
        title.setText("每日加奖");
        image = findViewById(R.id.image);
        mTvZrzz = findViewById(R.id.tv_zrzz);
        tv_dqdj = findViewById(R.id.tv_dqdj);
        tv_jjbl = findViewById(R.id.tv_jjbl);
        tv_kdjj = findViewById(R.id.tv_kdjj);
        tv_lq = findViewById(R.id.tv_lq);
        mRecycler = findViewById(R.id.recycler);

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        tv_lq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toGetBetBonus();
            }
        });
    }

    private void toGetBetBonus() {
        InterfaceTask.getInstance().getBetBonusDraw(this, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                tv_lq.setEnabled(false);
                tv_lq.setBackground(getResources().getDrawable(R.drawable.shape_rect_gray));
                tv_lq.setText("领取成功");

            }
        });

    }

    public  static  void  startActivity(Activity activity, String imgUrl){
        Intent intent = new Intent(activity,DailyBonusActivity.class);
        intent.putExtra("imgUrl",imgUrl);
        activity.startActivity(intent);
    }
}

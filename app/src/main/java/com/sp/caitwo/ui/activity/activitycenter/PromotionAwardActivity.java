package com.sp.caitwo.ui.activity.activitycenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.BetBonusBean;
import com.sp.caitwo.bean.UserLevelListBean;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.BitmapUtil;
import com.sp.xwjlibrary.util.HolderUtil;

import org.xutils.XCallback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * 晋级奖励
 */
public class PromotionAwardActivity extends AppCompatActivity {

    private TextView tv_dqdj,tv_kdjj,tv_lq;
    private ImageView image;
    private LinearLayout llyLevelMechanism;
    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_award);

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
        InterfaceTask.getInstance().getLevelBonus( new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                BetBonusBean homeWanFaBean = (BetBonusBean) obj;
                if(homeWanFaBean!=null&&homeWanFaBean.getData()!=null){
                    tv_dqdj.setText(String.valueOf(homeWanFaBean.getData().getUserLevelName()));
                    tv_kdjj.setText(String.valueOf(homeWanFaBean.getData().getUserLevelPromotionBonus()));
                    if(homeWanFaBean.getData().getUserLevelPromotionBonus()>0){
                        tv_lq.setEnabled(true);
                        tv_lq.setBackground(getResources().getDrawable(R.drawable.shape_rect_blue));
                        tv_lq.setText("点击领取");
                    }else {
                        tv_lq.setEnabled(false);
                    }
                }

            }
        });

        InterfaceTask.getInstance().getUserLevelList(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                UserLevelListBean json = (UserLevelListBean) obj;
                List<UserLevelListBean.DataBean> levelMechanismList = json.getData();
                for (UserLevelListBean.DataBean levelMechanism : levelMechanismList) {
                    LinearLayout view = (LinearLayout) View.inflate(getBaseContext(), R.layout.item_level_mechanism_list, null);
                    for (int i = 0; i < view.getChildCount(); i++) {
                        TextView text = (TextView) view.getChildAt(i);
                        String tag = (String) text.getTag();
                        if (BaseUtil.equals(tag,"level")){
                            text.setText(String.format(getString(R.string.vip_level_num),levelMechanism.getId()));
                        }else if (BaseUtil.equals(tag,"honor")){
                            text.setText(levelMechanism.getLevel_name());
                        }else if (BaseUtil.equals(tag,"point")){
                            text.setText(levelMechanism.getRecharge_fee());
                        }else if (BaseUtil.equals(tag,"levelUpPrize")){
                            text.setText(levelMechanism.getPromotion_bonus());
                        }
                    }
                    llyLevelMechanism.addView(view);
                }
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
        title.setText("晋级奖励");
        image = findViewById(R.id.image);
        tv_dqdj = findViewById(R.id.tv_dqdj);
        tv_kdjj = findViewById(R.id.tv_kdjj);
        tv_lq = findViewById(R.id.tv_lq);
        llyLevelMechanism = findViewById(R.id.lly_level_mechanism);

        tv_lq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toGetBetBonus();
            }
        });
    }

    private void toGetBetBonus() {
        InterfaceTask.getInstance().getLevelBonusDraw(this, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                tv_lq.setEnabled(false);
                tv_lq.setBackground(getResources().getDrawable(R.drawable.shape_rect_gray));
                tv_lq.setText("领取成功");

            }
        });

    }

    public  static  void  startActivity(Activity activity, String imgUrl){
        Intent intent = new Intent(activity,PromotionAwardActivity.class);
        intent.putExtra("imgUrl",imgUrl);
        activity.startActivity(intent);
    }
}

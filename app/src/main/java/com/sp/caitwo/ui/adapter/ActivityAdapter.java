package com.sp.caitwo.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.bean.ActivityCenterBean;
import com.sp.caitwo.ui.activity.activitycenter.ActivityDetailActivity;
import com.sp.caitwo.ui.activity.activitycenter.DailyBonusActivity;
import com.sp.caitwo.ui.activity.activitycenter.PromotionAwardActivity;
import com.sp.caitwo.ui.activity.sysm.LoginActivity;
import com.sp.caitwo.ui.fragment.home.ActivityFragment;
import com.sp.caitwo.ui.holder.ActivityHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.BitmapUtil;
import com.sp.xwjlibrary.util.HolderUtil;

import org.xutils.XCallback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

public class ActivityAdapter extends RVBaseAdap<ActivityHolder> {

    private Context context;
    private ActivityFragment frag;
    private ArrayList<ActivityCenterBean.DataBean> dataList;

    public ActivityAdapter(ActivityFragment frag) {
        this.context = frag.getContext();
        this.frag = frag;
    }

    public void notifyDatasChang(ArrayList<ActivityCenterBean.DataBean> dataList) {
        this.dataList = dataList;
        super.notifyDatasChang();
    }

    @Override
    public ActivityHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ActivityHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity,parent,false));
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(dataList) ? 0 : dataList.size();
    }

    @Override
    public void onBindHolder(final ActivityHolder holder, final int position) {
        ImageOptions build = new ImageOptions.Builder().setImageScaleType(ImageView.ScaleType.FIT_XY).build();
        x.image().bind(holder.ivBannerImg,dataList.get(position).getImg_url(),build,new XCallback<Drawable>(){
            @Override
            public void onSuccess(Drawable result) {
                Bitmap bitmap = BitmapUtil.drawableToBitmap(result);
                Bitmap bmp = BitmapUtil.createRoundConerImage(bitmap,20,BitmapUtil.CORNER_TOP);
                holder.ivBannerImg.setImageBitmap(bmp);
                int scale = bitmap.getHeight() * 100 / bitmap.getWidth();
                HolderUtil.setHolderScale(holder.ivBannerImg,scale + "/100");
            }
        });

        holder.tvTitle.setText(dataList.get(position).getTitle());
        holder.tvSubTitle.setText(dataList.get(position).getSub_title());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BaseUtil.equals(dataList.get(position).getTitle(),"每日加奖")){
                    if (!BaseUtil.isEmpty(App.userInfoBean.getData().getAccess_token()))
                        DailyBonusActivity.startActivity((Activity) context,dataList.get(position).getImg_url());
                    else {
                        context.startActivity(new Intent(context,LoginActivity.class));
                    }
                }else if(BaseUtil.equals(dataList.get(position).getTitle(),"晋级奖励")){
                    if (!BaseUtil.isEmpty(App.userInfoBean.getData().getAccess_token()))
                        PromotionAwardActivity.startActivity((Activity) context,dataList.get(position).getImg_url());
                    else {
                        context.startActivity(new Intent(context,LoginActivity.class));
                    }
                }else {
                    Intent intent = new Intent(context, ActivityDetailActivity.class);
                    intent.putExtra("dataBean",dataList.get(position));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onPullDownToRefresh() {
        frag.setData();
    }
}

package com.sp.caitwo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sp.caitwo.R;
import com.sp.caitwo.bean.SystemHeadPortraitBean;
import com.sp.caitwo.ui.fragment.UserInfo.PersonInfoFrag;
import com.sp.caitwo.ui.holder.SystemHeadPortraitHolder;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

public class SystemHeadPortraitAdap extends RVBaseAdap<SystemHeadPortraitHolder> {
    private ArrayList<SystemHeadPortraitBean.DataBean.ListBean> dataList;
    private PersonInfoFrag personInfoFrag;

    public SystemHeadPortraitAdap(PersonInfoFrag personInfoFrag) {
        this.personInfoFrag = personInfoFrag;
    }

    @Override
    public SystemHeadPortraitHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new SystemHeadPortraitHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_system_head_portrait,parent,false));
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(dataList) ? 0 : dataList.size();
    }

    @Override
    public void onBindHolder(SystemHeadPortraitHolder holder, int position) {
        ImageOptions imageOptions= new ImageOptions.Builder()
                .setRadius(10)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setAutoRotate(true)
                .setFadeIn(true)
                .build(); //淡入效果
        x.image().bind(holder.image,dataList.get(position).getUser_photo(),imageOptions);
        holder.image.setTag(dataList.get(position).getUser_photo());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgUrl = (String) v.getTag();
                personInfoFrag.setPerviewHead(imgUrl);
            }
        });
    }

    public void notifyDatasChang(ArrayList<SystemHeadPortraitBean.DataBean.ListBean> dataList) {
        this.dataList = dataList;
        super.notifyDatasChang();
    }
}

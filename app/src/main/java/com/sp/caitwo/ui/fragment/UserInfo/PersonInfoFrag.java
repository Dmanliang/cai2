package com.sp.caitwo.ui.fragment.UserInfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.SystemHeadPortraitBean;
import com.sp.caitwo.bean.UserDetailBean;
import com.sp.caitwo.ui.adapter.SystemHeadPortraitAdap;
import com.sp.caitwo.ui.adapter.securitycenter.WheelQuestionAdap;
import com.sp.caitwo.ui.view.dialog.WheelDialog;
import com.sp.xwjlibrary.dialog.ViewDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;

import kankan.wheel.widget.WheelView;

public class PersonInfoFrag extends Fragment implements View.OnClickListener {

    private View inflate;
    private ImageView ivHeadPortrait;
    private TextView tvAccount;
    private TextView tvSex;
    private IconEditClearView iecvNickName;
    private UserDetailBean userDetail;
    private ViewDialog systemHeadViewDialog;
    private SystemHeadPortraitAdap mAdapter;
    private ImageView ivPreviewHead;
    private String imgUrl;
    private WheelView wheelSex;
    private WheelDialog sexDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frag_person_info, null, false);

        userDetail = (UserDetailBean) getArguments().getSerializable("userDetail");

        initUI();

        setHeadSelect();

        getSystemHead();

        initWheelView();

        return this.inflate;
    }

    private void initWheelView() {
        View inflate = View.inflate(getContext(),R.layout.dialog_security_question, null);
        wheelSex = inflate.findViewById(R.id.wheel_question);
        inflate.findViewById(R.id.tv_wheel_cancle).setOnClickListener(this);
        inflate.findViewById(R.id.tv_wheel_sure).setOnClickListener(this);
        wheelSex.setWheelBackground(R.color.white);
        wheelSex.setWheelForeground(R.drawable.top_bottom_grayside_whitebg);

        wheelSex.setViewAdapter(new WheelQuestionAdap(new ArrayList<>(Arrays.asList(new String[]{"男","女"}))));
        wheelSex.setDrawShadows(false);
        wheelSex.setVisibleItems(7);
        sexDialog = new WheelDialog(getContext(), inflate);
    }

    private void getSystemHead() {
        InterfaceTask.getInstance().getSystemHeadPortrait(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                SystemHeadPortraitBean json = (SystemHeadPortraitBean) obj;
                mAdapter.notifyDatasChang(new ArrayList<>(json.getData().getList()));
            }
        });
    }

    private void setHeadSelect() {
        View systemHeadView = View.inflate(getContext(), R.layout.system_head_portrait, null);
        RecyclerView rvHead = systemHeadView.findViewById(R.id.rv_head);
        ivPreviewHead = systemHeadView.findViewById(R.id.iv_preview_head);
        systemHeadView.findViewById(R.id.tv_save_head).setOnClickListener(this);
        systemHeadView.findViewById(R.id.tv_cancel).setOnClickListener(this);
        rvHead.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));
        mAdapter = new SystemHeadPortraitAdap(this);
        rvHead.setAdapter(mAdapter);

        systemHeadViewDialog = new ViewDialog(getContext(), systemHeadView);
        systemHeadViewDialog.setMaxWidth().setGravity(Gravity.BOTTOM);
    }

    private void initUI() {
        ivHeadPortrait = inflate.findViewById(R.id.iv_head_portrait);
        tvAccount = inflate.findViewById(R.id.tv_account);
        tvSex = inflate.findViewById(R.id.tv_sex);
        iecvNickName = inflate.findViewById(R.id.iecv_nick_name);
        inflate.findViewById(R.id.btn_save).setOnClickListener(this);
        ivHeadPortrait.setOnClickListener(this);
        tvSex.setOnClickListener(this);
        if (!BaseUtil.isEmpty(userDetail.getData().getNick_name())){
            iecvNickName.setEnabled(false);
            iecvNickName.getClearView().setText("");
        }

        ImageOptions imageOptions= new ImageOptions.Builder()
                .setRadius(10)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setAutoRotate(true)
                .setFadeIn(true)
                .build(); //淡入效果
        x.image().bind(ivHeadPortrait, userDetail.getData().getUser_photo(),imageOptions);
        tvAccount.setText(userDetail.getData().getAccount());
        iecvNickName.setInputInfo(userDetail.getData().getNick_name());
        tvSex.setText(BaseUtil.equals(userDetail.getData().getSex(),"1") ? "男" : "女");
        imgUrl = userDetail.getData().getUser_photo();
    }

    @Override
    public void onClick(View v) {
        ImageOptions imageOptions= new ImageOptions.Builder()
                .setRadius(10)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setAutoRotate(true)
                .setFadeIn(true)
                .build(); //淡入效果
        switch(v.getId()){
            case R.id.iv_head_portrait:
                x.image().bind(ivPreviewHead, imgUrl,imageOptions);
                systemHeadViewDialog.show();
                break;
            case R.id.btn_save:
                InterfaceTask.getInstance().changeUserDetail(getActivity(),"","",iecvNickName.getInputInfo());
                break;
            case R.id.tv_save_head:
                InterfaceTask.getInstance().changeUserDetail(getActivity(),"",imgUrl,"");
                x.image().bind(ivHeadPortrait, imgUrl,imageOptions);
                systemHeadViewDialog.dismiss();
                break;
            case R.id.tv_cancel:
                systemHeadViewDialog.dismiss();
                break;
            case R.id.tv_wheel_cancle:
                sexDialog.dismiss();
                break;
            case R.id.tv_sex:
                sexDialog.show();
                break;
            case R.id.tv_wheel_sure:
                String sex;
                if (wheelSex.getCurrentItem() == 0) {
                    sex = "1";
                }else {
                    sex = "2";
                }
                tvSex.setText(BaseUtil.equals(sex,"1") ? "男" : "女");
                InterfaceTask.getInstance().changeUserDetail(getActivity(),sex,"","");
                sexDialog.dismiss();
                break;
        }
    }

    public void setPerviewHead(String imgUrl){
        this.imgUrl = imgUrl;
        ImageOptions imageOptions= new ImageOptions.Builder()
                .setRadius(10)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setAutoRotate(true)
                .setFadeIn(true)
                .build(); //淡入效果
        x.image().bind(ivPreviewHead, imgUrl,imageOptions);
    }
}

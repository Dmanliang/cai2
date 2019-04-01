package com.sp.caitwo.ui.fragment.UserInfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.UserDetailBean;
import com.sp.caitwo.bean.UserLevelListBean;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.widget.ShowTextProgressBar;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

public class LevelHonorFrag extends Fragment {

    private View inflate;
    private LinearLayout llyLevelMechanism;
    private ImageView ivHeadPortrait;
    private TextView tvNickName;
    private TextView tvVipLevel;
    private TextView tvHonor;
    private TextView tvPoint;
    private TextView tvCurrHonor;
    private TextView tvNextHonor;
    private TextView tvLevelUpExperience;
    private ShowTextProgressBar stpbExperiencePoint;
    private UserDetailBean userDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frag_level_honor, null, false);

        userDetail = (UserDetailBean) getArguments().getSerializable("userDetail");

        initUI();

        setData();

        return inflate;
    }

    private void setData() {
        InterfaceTask.getInstance().getUserLevelList(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                UserLevelListBean json = (UserLevelListBean) obj;
                List<UserLevelListBean.DataBean> levelMechanismList = json.getData();
                int index = 0;
                for (UserLevelListBean.DataBean levelMechanism : levelMechanismList) {
                    LinearLayout view = (LinearLayout) View.inflate(getContext(), R.layout.item_level_mechanism_list, null);
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
                    if (BaseUtil.equals(levelMechanism.getId(),userDetail.getData().getLevel())) {
                        tvHonor.setText(String.format(getString(R.string.honor),levelMechanism.getLevel_name()));
                        tvCurrHonor.setText(levelMechanism.getLevel_name());
                        if (index + 1 < levelMechanismList.size()){
                            tvNextHonor.setText(levelMechanismList.get(index + 1).getLevel_name());
                            String rechargeFee = levelMechanismList.get(index + 1).getRecharge_fee();
                            stpbExperiencePoint.setMax(Double.valueOf(rechargeFee).intValue());
                            stpbExperiencePoint.setProgress(Double.valueOf(userDetail.getData().getAll_point()).intValue());
                            tvLevelUpExperience.setText(String.format(getString(R.string.level_up_experience_point), rechargeFee));
                        }else {
                            tvNextHonor.setText(levelMechanism.getLevel_name());
                        }
                    }

                    index ++;
                }
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            InterfaceTask.getInstance().getUserDetail(getActivity(), new InterfaceTask.OnInterfaceTask() {
                @Override
                public void onSuccess(Object obj) {
                    userDetail = (UserDetailBean) obj;
                    ImageOptions imageOptions = new ImageOptions.Builder()
                            .setCircular(true)
                            .setAutoRotate(true)
                            .setFadeIn(true)
                            .build(); //淡入效果
                    x.image().bind(ivHeadPortrait, userDetail.getData().getUser_photo(), imageOptions);
                    tvNickName.setText(userDetail.getData().getNick_name());
                    tvVipLevel.setText(String.format(getString(R.string.vip_lv), userDetail.getData().getLevel()));
                    tvPoint.setText(String.format(getString(R.string.point_count), userDetail.getData().getAll_point()));
                }
            });
        }
    }

    private void initUI() {
        llyLevelMechanism = inflate.findViewById(R.id.lly_level_mechanism);
        ivHeadPortrait = inflate.findViewById(R.id.iv_head_portrait);
        tvNickName = inflate.findViewById(R.id.tv_nick_name);
        tvVipLevel = inflate.findViewById(R.id.tv_vip_level);
        tvHonor = inflate.findViewById(R.id.tv_honor);
        tvPoint = inflate.findViewById(R.id.tv_point);
        tvCurrHonor = inflate.findViewById(R.id.tv_curr_honor);
        tvNextHonor = inflate.findViewById(R.id.tv_next_honor);
        tvLevelUpExperience = inflate.findViewById(R.id.tv_level_up_experience);
        stpbExperiencePoint = inflate.findViewById(R.id.stpb_experience_point);
    }
}

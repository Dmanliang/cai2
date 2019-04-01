package com.sp.caitwo.ui.fragment.agentcenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.PersonalAgentInfoBean;
import com.sp.util.SetTextViewSameWidthUtil;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.HolderUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

import java.util.List;

public class SubordinateOpenerFrag extends Fragment implements View.OnClickListener {

    private IconEditClearView iecvKuai3,iecvShishicai,iecv11xuan5,iecvFucai3d,iecvPailie3_5,iecv28,
            iecvPk10,iecvLiuhecai,iecvDipincai;
    private IconEditClearView[] iecvs = null;
    private String openerType = "1";
    private View inflate;
    private List<PersonalAgentInfoBean.DataBean.ProfitListBean> profitList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.frag_subordinate_opener, null, false);

        initUI();

        SetTextViewSameWidthUtil.setIecv((ViewGroup) inflate);

        getData();

        return inflate;
    }

    private void getData() {
        InterfaceTask.getInstance().getPersonalAgentInfo(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                PersonalAgentInfoBean info = (PersonalAgentInfoBean) obj;
                profitList = info.getData().getProfitList();
                int index = 0;
                for (int i = iecvs.length - 1; i >= 0; i--) {
                    String profitValue = profitList.get(i).getProfit_value();
                    if (BaseUtil.equals(profitList.get(i).getStatus(),"0")){
                        HolderUtil.disableSubControls(iecvs[index]);
                        iecvs[index].setInputInfo(profitValue);
                    }
                    iecvs[index].setHint(String.format(getString(R.string.subordinate_opener_hint), profitValue, profitValue));
                    index ++;
                }
            }
        });
    }

    private void setData() {
        boolean isHasError = false;
        if (!BaseUtil.isEmpty(profitList)) {
            for (PersonalAgentInfoBean.DataBean.ProfitListBean profitListBean : profitList) {
                int id = Integer.parseInt(profitListBean.getId());
                for (int i = 0; i < iecvs.length; i++) {
                    if (id == i + 1){
                        IconEditClearView iecv = iecvs[i];
                        if (!BaseUtil.isEmpty(iecv.getInputInfo())) {
                            double inputNum = Double.parseDouble(iecv.getInputInfo());
                            double profitNum = Double.parseDouble(profitListBean.getProfit_value());
                            if (inputNum < 0 || inputNum > profitNum) {
                                isHasError = true;
                                iecv.setErrorInfo("超出最大值");
                            }
                        }else {
                            isHasError = true;
                            iecv.setErrorInfo("请输入分润值");
                        }
                        break;
                    }
                }
            }
        }
        if (!isHasError) {
            InterfaceTask.getInstance().generateInvite(getActivity(),openerType, iecvKuai3.getInputInfo(), iecvShishicai.getInputInfo(), iecv11xuan5.getInputInfo(),
                    iecvFucai3d.getInputInfo(), iecvPailie3_5.getInputInfo(), iecv28.getInputInfo(), iecvPk10.getInputInfo(), iecvLiuhecai.getInputInfo(),
                    iecvDipincai.getInputInfo());
        }
    }

    private void initUI() {
        iecvKuai3 = inflate.findViewById(R.id.iecv_kuai3);
        iecvShishicai = inflate.findViewById(R.id.iecv_shishicai);
        iecv11xuan5 = inflate.findViewById(R.id.iecv_11xuan5);
        iecvFucai3d = inflate.findViewById(R.id.iecv_fucai3d);
        iecvPailie3_5 = inflate.findViewById(R.id.iecv_pailie3_5);
        iecv28 = inflate.findViewById(R.id.iecv_28);
        iecvPk10 = inflate.findViewById(R.id.iecv_pk10);
        iecvLiuhecai = inflate.findViewById(R.id.iecv_liuhecai);
        iecvDipincai = inflate.findViewById(R.id.iecv_dipincai);
        iecvs = new IconEditClearView[]{iecvKuai3,iecvShishicai,iecv11xuan5,iecvFucai3d,
                iecvPailie3_5,iecv28,iecvPk10,iecvLiuhecai,iecvDipincai};

        RadioGroup rgOpenerType = inflate.findViewById(R.id.rg_opener_type);
        rgOpenerType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (group.getChildAt(i).getId() == checkedId){
                        openerType = (String) group.getChildAt(i).getTag();
                    }
                }
            }
        });

        inflate.findViewById(R.id.btn_generate_invite).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_generate_invite:
                setData();
                break;
        }
    }
}

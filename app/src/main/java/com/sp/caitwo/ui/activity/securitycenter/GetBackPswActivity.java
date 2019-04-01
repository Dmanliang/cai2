package com.sp.caitwo.ui.activity.securitycenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sp.caitwo.ui.activity.sysm.CustomServiceActivity;
import com.sp.xwjlibrary.util.BaseUtil;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.PersonalSecurityQuestionBean;
import com.sp.caitwo.ui.adapter.securitycenter.WheelQuestionAdap;
import com.sp.caitwo.ui.view.dialog.WheelDialog;
import com.sp.xwjlibrary.util.CheckCodeUtil;
import com.sp.xwjlibrary.util.TextViewUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

import java.util.ArrayList;

import kankan.wheel.widget.WheelView;

public class GetBackPswActivity extends AppCompatActivity implements View.OnClickListener {

    private IconEditClearView iecvAccount;
    private IconEditClearView iecvCheckCode;
    private ImageView ivCheckCode;
    private String checkCode;
    private WheelView wheelQuestion;
    private WheelDialog wheelDialog;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_back);

        list = new ArrayList<>();

        initUI();

        getCheckCode();

        initWheelView();

    }

    private void getGetBackType(String account) {
        InterfaceTask.getInstance().getPersonalSecurityQuestion(this,account,new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                list.clear();
                PersonalSecurityQuestionBean info = (PersonalSecurityQuestionBean) obj;
                int securityStatus = info.getData().getSecurityStatus();
                switch(securityStatus){
                    case 0:
                        //验证密保问题界面包含客服,没有密保问题就显示客服
                        startActivity(new Intent(getBaseContext(),CustomServiceActivity.class));
                        return;
                    case 1:
                        list.add("安全密码找回");
                        break;
                    case 2:
                        list.add("密保问题找回");
                        break;
                    case 3:
                        list.add("安全密码找回");
                        list.add("密保问题找回");
                        break;
                }
                wheelQuestion.invalidateWheel(true);
                wheelDialog.show();
            }
        });
    }

    private void initWheelView() {
        View inflate = View.inflate(this,R.layout.dialog_security_question, null);
        wheelQuestion = inflate.findViewById(R.id.wheel_question);
        inflate.findViewById(R.id.tv_wheel_cancle).setOnClickListener(this);
        inflate.findViewById(R.id.tv_wheel_sure).setOnClickListener(this);
        wheelQuestion.setWheelBackground(R.color.white);
        wheelQuestion.setWheelForeground(R.drawable.top_bottom_grayside_whitebg);

        wheelQuestion.setViewAdapter(new WheelQuestionAdap(list));
        wheelQuestion.setDrawShadows(false);
        wheelQuestion.setVisibleItems(7);
        wheelDialog = new WheelDialog(this, inflate);
    }

    private void getCheckCode() {
        checkCode = CheckCodeUtil.create();
        Bitmap checkCodeBmp = CheckCodeUtil.generateBmp(checkCode);
        ivCheckCode.setImageBitmap(checkCodeBmp);
    }

    private void initUI() {
        TextView tvTitle = findViewById(R.id.tv_top_title);
        iecvAccount = findViewById(R.id.iecv_account);
        iecvCheckCode = findViewById(R.id.iecv_check_code);
        ivCheckCode = findViewById(R.id.iv_check_code);

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
        ivCheckCode.setOnClickListener(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        TextViewUtil.setSameWidth(new TextView[]{iecvAccount.getIconView(),iecvCheckCode.getIconView()},layoutParams);
        tvTitle.setText(getString(R.string.get_back_psw));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.btn_sure:
                String account = iecvAccount.getInputInfo();
                String checkCode = iecvCheckCode.getInputInfo();
                if (BaseUtil.isEmpty(account))
                    iecvAccount.setErrorInfo(getString(R.string.input_account_hint));
                if (BaseUtil.isEmpty(checkCode))
                    iecvCheckCode.setErrorInfo(getString(R.string.input_check_code_hint));
                else if (!BaseUtil.equals(checkCode.toLowerCase(),this.checkCode.toLowerCase())){
                    getCheckCode();
                    iecvCheckCode.setInputInfo("");
                    iecvCheckCode.setErrorInfo("验证码错误");
                }
                if (!iecvAccount.isHasError() && !iecvCheckCode.isHasError()){
                    getGetBackType(account);
                }
                break;
            case R.id.iv_check_code:
                getCheckCode();
                iecvCheckCode.setInputInfo("");
                break;
            case R.id.tv_wheel_cancle:
                wheelDialog.dismiss();
                break;
            case R.id.tv_wheel_sure:
                String type = list.get(wheelQuestion.getCurrentItem());
                if (BaseUtil.equals(type,"安全密码找回")){
                    Intent intent = new Intent(this, CheckSecurityPswActivity.class);
                    intent.putExtra("Class","getBackPsw");
                    intent.putExtra("account",iecvAccount.getInputInfo());
                    wheelDialog.dismiss();
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(this, CheckSecurityQuestionActivity.class);
                    intent.putExtra("Class","getBackPsw");
                    intent.putExtra("account",iecvAccount.getInputInfo());
                    wheelDialog.dismiss();
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}

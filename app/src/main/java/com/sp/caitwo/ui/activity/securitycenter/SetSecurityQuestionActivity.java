package com.sp.caitwo.ui.activity.securitycenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.sp.xwjlibrary.util.BaseUtil;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.SecurityQuestionBean;
import com.sp.caitwo.ui.adapter.securitycenter.WheelQuestionAdap;
import com.sp.caitwo.ui.view.dialog.WheelDialog;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

import java.util.ArrayList;
import java.util.List;

import kankan.wheel.widget.WheelView;

public class SetSecurityQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    
    private WheelDialog wheelDialog;
    private View clickView;
    private ArrayList<SecurityQuestionBean.DataBean> dataList;
    private ArrayList<String> questionList;
    private WheelView wheelQuestion;
    private TextView tvQuestion1;
    private TextView tvQuestion2;
    private TextView tvQuestion3;
    private IconEditClearView iecvAnswer1;
    private IconEditClearView iecvAnswer2;
    private IconEditClearView iecvAnswer3;
    private int q1Id = -1;
    private int q2Id = -2;
    private int q3Id = -3;
    private String securityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_security_question);

        dataList = new ArrayList<>();
        questionList = new ArrayList<>();

        initUI();

        initWheelView();

        InterfaceTask.getInstance().getSecurityQuestionList(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                SecurityQuestionBean info = (SecurityQuestionBean) obj;
                List<SecurityQuestionBean.DataBean> data = info.getData();
                dataList.clear();
                dataList.addAll(data);
                for (SecurityQuestionBean.DataBean bean : data) {
                    questionList.add(bean.getQuestion_desc());
                }
                wheelQuestion.invalidateWheel(true);
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

        wheelQuestion.setViewAdapter(new WheelQuestionAdap(questionList));
        wheelQuestion.setDrawShadows(false);
        wheelQuestion.setVisibleItems(7);
        wheelDialog = new WheelDialog(this, inflate);
    }

    private void initUI() {
        tvQuestion1 = findViewById(R.id.tv_question1);
        tvQuestion2 = findViewById(R.id.tv_question2);
        tvQuestion3 = findViewById(R.id.tv_question3);
        iecvAnswer1 = findViewById(R.id.tv_answer1);
        iecvAnswer2 = findViewById(R.id.tv_answer2);
        iecvAnswer3 = findViewById(R.id.tv_answer3);
        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.set_security_question));

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
        tvQuestion1.setOnClickListener(this);
        tvQuestion2.setOnClickListener(this);
        tvQuestion3.setOnClickListener(this);

        securityCode = getIntent().getStringExtra("securityCode");
        if (!BaseUtil.isEmpty(securityCode)){
            tvTitle.setText("修改密保问题");
        }
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.tv_question1:
                clickView = v;
                wheelQuestion.setCurrentItem(0);
                wheelDialog.show();
                break;
            case R.id.tv_question2:
                clickView = v;
                wheelQuestion.setCurrentItem(0);
                wheelDialog.show();
                break;
            case R.id.tv_question3:
                clickView = v;
                wheelQuestion.setCurrentItem(0);
                wheelDialog.show();
                break;
            case R.id.tv_wheel_cancle:
                wheelDialog.dismiss();
                break;
            case R.id.tv_wheel_sure:
                switch(clickView.getId()){
                    case R.id.tv_question1:
                        int q1Id = dataList.get(wheelQuestion.getCurrentItem()).getId();
                        if (q1Id == this.q2Id || q1Id == this.q3Id){
                            ToastUtil.Toast(this,"不能选择相同问题");
                            return;
                        }else {
                            this.q1Id = q1Id;
                        }
                        tvQuestion1.setText(dataList.get(wheelQuestion.getCurrentItem()).getQuestion_desc());
                        break;
                    case R.id.tv_question2:
                        int q2Id = dataList.get(wheelQuestion.getCurrentItem()).getId();
                        if (this.q1Id == q2Id || q2Id == q3Id){
                            ToastUtil.Toast(this,"不能选择相同问题");
                            return;
                        }else {
                            this.q2Id = q2Id;
                        }
                        tvQuestion2.setText(dataList.get(wheelQuestion.getCurrentItem()).getQuestion_desc());
                        break;
                    case R.id.tv_question3:
                        int q3Id = dataList.get(wheelQuestion.getCurrentItem()).getId();
                        if (this.q1Id == q3Id || this.q2Id == q3Id){
                            ToastUtil.Toast(this,"不能选择相同问题");
                            return;
                        }else {
                            this.q3Id = q3Id;
                        }
                        tvQuestion3.setText(dataList.get(wheelQuestion.getCurrentItem()).getQuestion_desc());
                        break;
                }
                wheelDialog.dismiss();
                break;
            case R.id.btn_sure:
                String answer1 = iecvAnswer1.getInputInfo();
                String answer2 = iecvAnswer2.getInputInfo();
                String answer3 = iecvAnswer3.getInputInfo();
                if (BaseUtil.isEmpty(answer1) || BaseUtil.isEmpty(answer2) ||
                        BaseUtil.isEmpty(answer3) || q1Id == -1 || q2Id == -1 || q3Id == -1){
                    ToastUtil.Toast(this,"请填写完整信息");
                }else {
                    if (!BaseUtil.isEmpty(securityCode)){
                        InterfaceTask.getInstance().changeSecurityQuestion(this,q1Id,q2Id,q3Id,answer1,answer2,answer3);
                    }else {
                        InterfaceTask.getInstance().setSecurityQuestion(this,q1Id,q2Id,q3Id,answer1,answer2,answer3);
                    }
                }
                break;
        }
    }
}

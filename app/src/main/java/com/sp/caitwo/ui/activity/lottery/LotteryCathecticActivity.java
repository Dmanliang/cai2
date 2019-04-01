package com.sp.caitwo.ui.activity.lottery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.info.BetInfo;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.info.LhcAllWanFaInfo;
import com.sp.caitwo.info.LhcLotteryOrderInfo;
import com.sp.caitwo.info.LotterOrderInfo;
import com.sp.caitwo.info.RoomOpenInfo;
import com.sp.caitwo.ui.adapter.lottery.LotteryCathecticAdapter;
import com.sp.caitwo.ui.view.LotteryCountDown;
import com.sp.caitwo.ui.view.dialog.CathecticDialog;
import com.sp.util.SoftKeyBoardListener;
import com.sp.xwjlibrary.util.HolderUtil;
import com.sp.xwjlibrary.util.JSONUtil;
import com.sp.xwjlibrary.util.StatusBarUtil;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.widget.DoubleTextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.UPDATE_INFO;
import static com.sp.caitwo.ui.fragment.lottery.WanFa28Fragment.CLEAR_LIST;
import static com.sp.caitwo.ui.fragment.lottery.WanFa28Fragment.getSubList;
import static com.sp.util.Util.getTwoDecimal;

public class LotteryCathecticActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String UPDATE_TIME = "update_time";
    public static final int REQUEST_CODE = 1008;
    public static final int MAX_MONEY = 200000;
    public static final int ZHUIHAO_ISSUE_NUM = 50;
    public static String DATA = "data";
    public static String DATA_BASEWANFA_ID = "DATA_BASEWANFA_ID";
    public static String DATA_WANFA_LIST = "DATA_WANFA_LIST";
    public static String DATA_LHCWANFA_LIST = "DATA_LHCWANFA_LIST";
    public static String DATA_GAME_TYPE = "DATA_GAME_TYPE";
    public static String DATA_TITLE = "DATA_TITLE";
    public static String DATA_SECONDS = "DATA_SECONDS";
    public static String DATA_CHONE = "DATA_CHONE";
    public static String DATA_BILILIST = "DATA_BILILIST";
    public static String DATA_UNIT_TYPE = "DATA_UNIT_TYPE";
    public static String DATA_TYPE = "DATA_TYPE";
    public static String DATA_LHCBILILIST = "DATA_LHCBILILIST";
    public static String TITLEINDEX = "titleindex";
    public static String TABINDEX = "tabindex";
    public static String UNIT_TYPE = "unit_type";
    public static String LHCPOINT = "lhc_point";
    private TextView cathecticIssues, lotteryMachineSelect, lotteryTotally, lotteryOk;
    private LinearLayout layout_lottery_bottom;
    private DoubleTextView lotteryTotallyMsg;
    private LotteryCountDown cathecticTimes;
    private RelativeLayout rebackSelect, selectOne, selectFive, checklayout;
    private LinearLayout multipleLayout, issueLayout;
    private RecyclerView cathecticRecyclerview;
    private RelativeLayout cathecticSelectLayout3;
    private EditText lotteryMultiple, lotteryIssueNum;
    private CheckBox iszhuihaoCheckbox;
    private ImageView ivTopBack;
    private TextView tvTopTitle;
    private LotteryCathecticAdapter adapter;
    private String mTitle;
    private String choice_no, betStr, lotteryTitle, bufferStr, betStr2, wanfa_type;
    private int game_type, allCount, issueCount = 1, multipleCount = 1, unit_type, mBaseWanfaId;
    private int zjjt = 1;
    private double allMoney;
    private CathecticDialog cathecticDialog;
    private BiliListInfo.DataBean wanfaList;
    private LhcAllWanFaInfo.DataBean lhcwanfaList;
    private ArrayList<HashMap<String, Object>> subList;
    private long second, curtime;
    private List<BetInfo> betInfoList;
    private List<BiliListInfo.ListBeans> ballHm;
    private List<LhcAllWanFaInfo.DataBean.ListsBean> lhcballHm;
    private double allTotalMoney, allPoint;
    private int bet_type, lhcPoint;
    private int titleindex, tabindex;
    private int baseWanFaID;
    private String tempQihao ="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_cathectic);
        getData();
        initView();
    }

    public void getData() {
        allPoint = App.userInfoBean.getData().getPoint();
        curtime = System.currentTimeMillis();
        betInfoList = (List<BetInfo>) getIntent().getSerializableExtra(DATA);
        game_type = getIntent().getIntExtra(DATA_GAME_TYPE, 0);
        bet_type = getIntent().getIntExtra(DATA_TYPE, 0);
        mTitle = getIntent().getStringExtra(DATA_TITLE);
        titleindex = getIntent().getIntExtra(TITLEINDEX, 0);
        tabindex = getIntent().getIntExtra(TABINDEX, 0);
        mBaseWanfaId = getIntent().getIntExtra(DATA_BASEWANFA_ID, 0);
        wanfaList = (BiliListInfo.DataBean) getIntent().getSerializableExtra(DATA_BILILIST);
        ballHm = (List<BiliListInfo.ListBeans>) getIntent().getSerializableExtra(DATA_WANFA_LIST);
        lhcballHm = (List<LhcAllWanFaInfo.DataBean.ListsBean>) getIntent().getSerializableExtra(DATA_LHCWANFA_LIST);
        lhcwanfaList = (LhcAllWanFaInfo.DataBean) getIntent().getSerializableExtra(DATA_LHCBILILIST);
        unit_type = getIntent().getIntExtra(UNIT_TYPE, 0);
        lhcPoint = getIntent().getIntExtra(LHCPOINT, 0);
        lotteryTitle = mTitle;
        subList = getSubList();
    }

    public void initView() {
        EventBus.getDefault().register(this);
        cathecticIssues = findViewById(R.id.cathectic_issues);
        layout_lottery_bottom = findViewById(R.id.layout_lottery_bottom);
        lotteryMachineSelect = findViewById(R.id.lottery_machine_select);
        lotteryTotally = findViewById(R.id.lottery_totally);
        lotteryTotallyMsg = findViewById(R.id.lottery_totally_msg);
        lotteryOk = findViewById(R.id.lottery_ok);
        cathecticTimes = findViewById(R.id.cathectic_times);
        rebackSelect = findViewById(R.id.reback_select);
        multipleLayout = findViewById(R.id.multiple_layout);
        issueLayout = findViewById(R.id.issue_layout);
        selectOne = findViewById(R.id.select_one);
        selectFive = findViewById(R.id.select_five);
        checklayout = findViewById(R.id.checklayout);
        ivTopBack = findViewById(R.id.iv_top_back);
        tvTopTitle = findViewById(R.id.tv_top_title);
        cathecticRecyclerview = findViewById(R.id.cathectic_recyclerview);
        cathecticSelectLayout3 = findViewById(R.id.cathectic_select_layout3);
        lotteryMultiple = findViewById(R.id.lottery_multiple);
        lotteryIssueNum = findViewById(R.id.lottery_issue_num);
        iszhuihaoCheckbox = findViewById(R.id.iszhuihao_checkbox);
        rebackSelect.setOnClickListener(this);
        selectOne.setOnClickListener(this);
        selectFive.setOnClickListener(this);
        lotteryMachineSelect.setOnClickListener(this);
        lotteryOk.setOnClickListener(this);
        ivTopBack.setOnClickListener(this);
        ivTopBack.setOnClickListener(this);
        lotteryMachineSelect.setText("清空");
        cathecticIssues.setText(choice_no + "期投注截止");
        tvTopTitle.setText(mTitle + "投注");
        cathecticTimes.setTime_type(0);
        cathecticSelectLayout3.setVisibility(View.VISIBLE);
        lotteryTotallyMsg.setVisibility(View.VISIBLE);
        lotteryTotally.setVisibility(View.GONE);
        cathecticRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LotteryCathecticAdapter(this, betInfoList);
        cathecticRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setRemoveOnClickListener(new LotteryCathecticAdapter.RemoveOnClickListener() {
            @Override
            public void removeOnClick(View view, int position) {
                removeItem(position);
            }
        });
        if (bet_type == 0 || bet_type == 2) {
            issueLayout.setVisibility(View.GONE);
        }
        iszhuihaoCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    zjjt = 1;
                } else {
                    zjjt = 0;
                }
            }
        });
        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                HolderUtil.setMargin(layout_lottery_bottom,0,0,0,height);
            }

            @Override
            public void keyBoardHide(int height) {
                HolderUtil.setMargin(layout_lottery_bottom,0,0,0,0);
            }
        });
        lotteryMultiple.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(lotteryMultiple.getText())) {
                    return;
                }
                if (!TextUtils.isEmpty(editable.toString())) {
                    multipleCount = Integer.parseInt(editable.toString());
                }
                if(!TextUtils.isEmpty(lotteryIssueNum.getText())){
                    lotteryTotallyMsg.setTwoText(allCount + "注 " + Integer.parseInt(lotteryMultiple.getText().toString()) + "倍 " + Integer.parseInt(lotteryIssueNum.getText().toString()) + "期");
                    allTotalMoney = allMoney * Integer.parseInt(lotteryIssueNum.getText().toString()) * Integer.parseInt(lotteryMultiple.getText().toString());
                    lotteryTotallyMsg.setOneText(getTwoDecimal(allTotalMoney) + "元");
                }
            }
        });
        lotteryIssueNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(lotteryIssueNum.getText())) {
                    return;
                }
                issueCount = Integer.parseInt(editable.toString());
                if (issueCount > 1) {
                    checklayout.setVisibility(View.VISIBLE);
                } else {
                    checklayout.setVisibility(View.GONE);
                }
                if (issueCount > ZHUIHAO_ISSUE_NUM) {
                    ToastUtil.Toast(LotteryCathecticActivity.this, "追号期数上限50期!");
                    lotteryIssueNum.setText("50");
                }
                if (!TextUtils.isEmpty(lotteryMultiple.getText())){
                    lotteryTotallyMsg.setTwoText(allCount + "注 " + Integer.parseInt(lotteryMultiple.getText().toString()) + "倍 " + Integer.parseInt(lotteryIssueNum.getText().toString()) + "期");
                    allTotalMoney = allMoney * Integer.parseInt(lotteryIssueNum.getText().toString()) * Integer.parseInt(lotteryMultiple.getText().toString());
                    lotteryTotallyMsg.setOneText(getTwoDecimal(allTotalMoney) + "元");
                }
            }
        });
        cathecticDialog = new CathecticDialog(this);
        cathecticDialog.cathectic_dialog_ok.setOnClickListener(this);
        setData();
        requestTime();
    }

    public void getRoomPointAdd() {
        InterfaceTask.getInstance().getRoomPointAdd(this, choice_no, betStr, game_type, new InterfaceTask.OnInterfaceTask() {

            @Override
            public void onError() {
                super.onError();
                cathecticDialog.dismiss();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                clearList();
                cathecticDialog.dismiss();
                EventBus.getDefault().post(UPDATE_INFO);
                EventBus.getDefault().post(CLEAR_LIST);
                finish();
            }
        });
    }

    public void getLotterySubBetAdd() {
        if (bet_type == 0) {
            if (game_type == LotteryCons.FENFENCAI) {
                baseWanFaID = 4;
            } else if (game_type == LotteryCons.YDWFC) {
                baseWanFaID = 11;
            } else if (game_type == LotteryCons.PAILIE3) {
                baseWanFaID = 3;
            } else if (game_type == LotteryCons.PAILIE5) {
                baseWanFaID = 5;
            } else if (game_type == LotteryCons.FUCAI3D) {
                baseWanFaID = 2;
            } else if (game_type == LotteryCons.ELEVENXFIVE) {
                baseWanFaID = 15;
            }
        }
        InterfaceTask.getInstance().getLotterySubBet(this, choice_no, betStr, baseWanFaID, new InterfaceTask.OnInterfaceTask() {

            @Override
            public void onError() {
                super.onError();
                cathecticDialog.dismiss();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                clearList();
                cathecticDialog.dismiss();
                EventBus.getDefault().post(UPDATE_INFO);
                EventBus.getDefault().post(CLEAR_LIST);
                finish();
            }
        });
    }

    public void lhcgetSubBet() {
        InterfaceTask.getInstance().lhcgetSubBet(this, choice_no, betStr, new InterfaceTask.OnInterfaceTask() {

            @Override
            public void onError() {
                super.onError();
                cathecticDialog.dismiss();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                clearList();
                cathecticDialog.dismiss();
                EventBus.getDefault().post(UPDATE_INFO);
                EventBus.getDefault().post(CLEAR_LIST);
                finish();
            }
        });
    }

    public void getDragonAddChasing() {
        issueCount = Integer.parseInt(lotteryIssueNum.getText().toString());
        multipleCount = 1;
        InterfaceTask.getInstance().getDragonAddChasing(this, game_type, choice_no, issueCount, multipleCount, zjjt + "", betStr, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                clearList();
                cathecticDialog.dismiss();
                EventBus.getDefault().post(UPDATE_INFO);
                EventBus.getDefault().post(CLEAR_LIST);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lottery_machine_select:
                clearList();
                break;
            case R.id.lottery_ok:
                subLottery();
                break;
            case R.id.reback_select:
                onBackPressed();
                break;
            case R.id.select_one:
                RandomSelectAddItem(1);
                break;
            case R.id.select_five:
                RandomSelectAddItem(5);
                break;
            case R.id.cathectic_dialog_ok:
                if (allTotalMoney > allPoint) {
                    ToastUtil.Toast(this, "余额不够!");
                    return;
                }
                if (allTotalMoney > MAX_MONEY) {
                    ToastUtil.Toast(this, "下注金额不得超过200000!");
                    return;
                }
                if (issueCount > 1) {
                    getDragonAddChasing();
                } else {
                    if (bet_type == 0) {
                        getLotterySubBetAdd();
                    } else if (bet_type == 1) {
                        getRoomPointAdd();
                    } else {
                        lhcgetSubBet();
                    }
                }
                break;
            case R.id.iv_top_back:
                onBackPressed();
                break;
        }
    }

    private void getBetDes() {
        List<BetInfo.DataBean> betlist = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        for (BetInfo betInfo : betInfoList) {
            betlist.addAll(betInfo.getData());
            buffer.append(betInfo.bet_dec).append("");
        }
        if (betlist.size() > 0) {
            if (bet_type == 0) {
                for (BetInfo.DataBean dataBean : betlist) {
                    dataBean.multiple_count = Integer.parseInt(lotteryMultiple.getText().toString());
                    dataBean.money = dataBean.money * dataBean.multiple_count;
                    dataBean.totalMoney = Double.parseDouble(getTwoDecimal(dataBean.money * dataBean.bet_count));
                }
                bufferStr = buffer.toString();
            } else if (bet_type == 1) {
                for (BetInfo.DataBean dataBean : betlist) {
                    if (issueCount > 1) {
                        dataBean.multiple_count = 1;
                        if (unit_type == 1) {
                            dataBean.point = 2 * Integer.parseInt(lotteryMultiple.getText().toString());
                        } else {
                            dataBean.unit_type = 1;
                            dataBean.point = 0.2 * Integer.parseInt(lotteryMultiple.getText().toString());
                        }
                    } else {
                        dataBean.multiple_count = Integer.parseInt(lotteryMultiple.getText().toString());
                        dataBean.point = 2;
                    }
                }
                bufferStr = buffer.toString();
            } else {
                for (BetInfo.DataBean dataBean : betlist) {
                    dataBean.money = dataBean.money * Integer.parseInt(lotteryMultiple.getText().toString());
                }
                bufferStr = buffer.toString();
            }
        }
        if (betlist.size() > 0) {
            betStr = JSONUtil.toJson(betlist);
            bufferStr = buffer.toString();
        }
        allTotalMoney = allMoney * Integer.parseInt(lotteryIssueNum.getText().toString()) * Integer.parseInt(lotteryMultiple.getText().toString());
    }

    public void requestTime() {
        switch (game_type) {
            case LotteryCons.CQSSC:
            case LotteryCons.TJSSC:
            case LotteryCons.BJPK10:
            case LotteryCons.XYPK10:
            case LotteryCons.JSK3:
            case LotteryCons.BJK3:
            case LotteryCons.AHK3:
            case LotteryCons.XJP28:
            case LotteryCons.JND_28:
            case LotteryCons.BJ_28:
                getRoomOpenInfo();
                break;
            case LotteryCons.FENFENCAI:
            case LotteryCons.YDWFC:
            case LotteryCons.FUCAI3D:
            case LotteryCons.PAILIE3:
            case LotteryCons.PAILIE5:
            case LotteryCons.ELEVENXFIVE:
                getLotteryOrderInfo(mBaseWanfaId);
                break;
            case LotteryCons.SIXHECAI:
                getlhcLotteryOrderInfo();
                break;
        }
    }

    public void setTime() {
        if (second >= 0) {
            cathecticTimes.startCountDown(second, 1000, 1, curtime, game_type);
        } else {
            cathecticTimes.startCountDown(second, 1000, 3, curtime, game_type);
        }
        cathecticIssues.setText(choice_no + "期投注截止");
    }

    public void setData() {
        allMoney = 0;
        allCount = 0;
        for (BetInfo betInfo : betInfoList) {
            allMoney += betInfo.getBet_point() * betInfo.getBet_num();
            allCount += betInfo.bet_num;
        }
        lotteryTotallyMsg.setOneText(getTwoDecimal(allMoney) + "元");
        lotteryTotallyMsg.setTwoText(allCount + "注 " + lotteryMultiple.getText().toString() + "倍 " + lotteryIssueNum.getText().toString() + "期");
    }

    public void subLottery() {
        if (TextUtils.isEmpty(lotteryIssueNum.getText().toString())) {
            ToastUtil.Toast(this, "期数不能为空!");
            return;
        }
        if (TextUtils.isEmpty(lotteryMultiple.getText().toString())) {
            ToastUtil.Toast(this, "倍数不能为空!");
            return;
        }
        if (issueCount == 0) {
            ToastUtil.Toast(this, "期数不能为0!");
            return;
        }
        if (multipleCount == 0) {
            ToastUtil.Toast(this, "倍数不能为0!");
            return;
        }
        if (betInfoList.size() == 0) {
            ToastUtil.Toast(this, "请选择号码!");
            return;
        }
        getBetDes();
        cathecticDialog.setContext(lotteryTitle, choice_no, allTotalMoney, bufferStr);
        cathecticDialog.show();
    }

    public void RandomSelectAddItem(int count) {
        if (game_type == LotteryCons.SIXHECAI) {
            for (int i = 0; i < count; i++) {
                for (int k = 0; k < lhcballHm.size(); k++) {
                    Integer[] ints = LotteryCons.randomCommon(0, lhcballHm.get(k).getList().size(), lhcballHm.get(k).getSomeNum());
                    BetInfo.DataBean betInfo = new BetInfo.DataBean();
                    List<BetInfo.DataBean> betlist = new ArrayList<>();
                    BetInfo betInf = new BetInfo();
                    boolean isFist = true;
                    StringBuffer buffer = new StringBuffer();
                    StringBuffer bufferNumbers = new StringBuffer();
                    for (int j = 0; j < ints.length; j++) {
                        if (isFist && (lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().contains("连码") || lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().contains("合肖") || lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().contains("生肖连") || lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().contains("尾数连") || lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().contains("全不中"))) {
                            buffer.append(TextUtils.isEmpty(lhcballHm.get(k).getList().get(ints[j]).getRemarks()) ? "" : lhcballHm.get(k).getList().get(ints[j]).getRemarks());
                            isFist = false;
                        }
                        BetInfo betInf1 = new BetInfo();
                        List<BetInfo.DataBean> betlist1 = new ArrayList<>();
                        BetInfo.DataBean betInfo1 = new BetInfo.DataBean();
                        bufferNumbers.append(lhcballHm.get(k).getList().get(ints[j]).getWanfa_name()).append(",");
                        betInfo.wanfa_id = lhcballHm.get(k).getList().get(ints[j]).getId();
                        betInfo.wanfa_type = lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname() + "," + lhcballHm.get(k).getList().get(ints[j]).getRemarks();
                        betInfo.money = lhcPoint * multipleCount;
                        betInfo1.wanfa_id = lhcballHm.get(k).getList().get(ints[j]).getId();
                        betInfo1.wanfa_type = lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname() + "," + lhcballHm.get(k).getList().get(ints[j]).getRemarks();
                        betInfo1.money = lhcPoint * multipleCount;
                        if (lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().contains("特码") || lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().contains("正码") || lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().contains("正码特") || lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().equals("生肖") || lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().equals("一肖") || lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().contains("半波") || lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname().contains("特码生肖")) {
                            buffer.append(TextUtils.isEmpty(lhcballHm.get(k).getList().get(ints[j]).getWanfa_name()) ? "" : lhcballHm.get(k).getList().get(ints[j]).getWanfa_name()).append(";");
                            betInfo1.isbetcount = 0;
                            betInfo1.wanfa_name = lhcballHm.get(k).getList().get(ints[j]).getWanfa_name();
                            betlist1.add(betInfo1);
                            betInf1.setBet_dec(buffer.toString());
                            betInf1.setBet_money("每注" + lhcPoint + "元");
                            betInf1.setBet_point(lhcPoint);
                            betInf1.bet_num = 1;
                            betInf1.setWanfa_name(lhcballHm.get(k).getList().get(ints[j]).getWanfa_parentname());
                            betInf1.getData().add(betInfo1);
                            addItem(betInf1);
                        } else {
                            buffer.append("_").append(TextUtils.isEmpty(lhcballHm.get(k).getList().get(ints[j]).getWanfa_name()) ? "" : lhcballHm.get(k).getList().get(ints[j]).getWanfa_name()).append(";");
                        }
                    }
                    if (lhcballHm.get(0).getList().get(0).getWanfa_parentname().contains("连码") || lhcballHm.get(0).getList().get(0).getWanfa_parentname().contains("合肖") || lhcballHm.get(0).getList().get(0).getWanfa_parentname().contains("生肖连") || lhcballHm.get(0).getList().get(0).getWanfa_parentname().contains("尾数连") || lhcballHm.get(0).getList().get(0).getWanfa_parentname().contains("全不中")) {
                        betInfo.betcount = 1;
                        betInfo.isbetcount = 1;
                        betInfo.numbers = bufferNumbers.toString().substring(0, bufferNumbers.toString().length() - 1);
                        betlist.add(betInfo);
                        betInf.setBet_dec(buffer.toString());
                        betInf.setBet_money("每注" + lhcPoint + "元");
                        betInf.setBet_point(lhcPoint);
                        betInf.bet_num = 1;
                        betInf.setWanfa_name(lhcballHm.get(0).getList().get(0).getWanfa_parentname());
                        betInf.getData().add(betInfo);
                        addItem(betInf);
                    }
                }
            }
        } else if (game_type == LotteryCons.ELEVENXFIVE) {
            for (int i = 0; i < count; i++) {
                Integer[] ints = null;
                StringBuffer buffer = new StringBuffer();
                StringBuffer bufferNumbers = new StringBuffer();
                BetInfo.DataBean betInfo = new BetInfo.DataBean();
                BetInfo betInf = new BetInfo();
                List<BetInfo.DataBean> betlist = new ArrayList<>();
                if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
                    for (int k = 0; k < ballHm.get(tabindex).getLists().size(); k++) {
                        ints = LotteryCons.randomCommon(0, ballHm.get(tabindex).getLists().get(k).getList().size(), ballHm.get(tabindex).getLists().get(k).getSomeNum());
                        for (int j = 0; j < ints.length; j++) {
                            buffer.append(ballHm.get(tabindex).getLists().get(k).getName() + "_" + ballHm.get(tabindex).getLists().get(k).getList().get(ints[j]).getWanfa_name()).append(";");
                            bufferNumbers.append(ballHm.get(tabindex).getLists().get(k).getList().get(ints[j]).getWanfa_name()).append(",");
                            betInfo.wanfa_id = ballHm.get(tabindex).getWanfa_id();
                            betInfo.unit_type = unit_type;
                            if (unit_type == 1) {
                                betInfo.money = 2;
                            } else {
                                betInfo.money = 0.2;
                            }
                            if (wanfaList.getName().contains("定位胆")) {
                                betInfo.bet_count = 5;
                            } else {
                                betInfo.bet_count = 1;
                            }
                            betInfo.wanfa_type = wanfaList.getName() + ballHm.get(tabindex).getName();
                        }
                        bufferNumbers.delete(bufferNumbers.toString().length() - 1, bufferNumbers.toString().length());
                        bufferNumbers.append("|");
                    }
                    betInf.setBet_dec(buffer.toString());
                    betInfo.numbers = bufferNumbers.toString().substring(0, bufferNumbers.toString().length() - 1);
                    betlist.add(betInfo);
                    if (unit_type == 1) {
                        betInf.setBet_money("每注2元");
                        betInf.setBet_point(2);
                    } else {
                        betInf.setBet_money("每注0.2元");
                        betInf.setBet_point(0.2);
                    }
                    betInf.bet_num = 1;
                    betInf.setData(betlist);
                    betInf.setWanfa_name(betInfo.wanfa_type);
                    addItem(betInf);
                } else {
                    for (int k = 0; k < ballHm.size(); k++) {
                        ints = LotteryCons.randomCommon(0, ballHm.get(k).getList().size(), ballHm.get(k).getSomeNum());
                        for (int j = 0; j < ints.length; j++) {
                            buffer.append(ballHm.get(k).getName() + "_" + ballHm.get(k).getList().get(ints[j]).getWanfa_name()).append(";");
                            bufferNumbers.append(ballHm.get(k).getList().get(ints[j]).getWanfa_name()).append(",");
                            betInfo.wanfa_id = ballHm.get(k).getWanfa_id();
                            betInfo.unit_type = unit_type;
                            if (unit_type == 1) {
                                betInfo.money = 2;
                            } else {
                                betInfo.money = 0.2;
                            }
                            if (wanfaList.getName().contains("定位胆")) {
                                betInfo.bet_count = 5;
                                betInfo.wanfa_type = wanfaList.getName();
                            } else {
                                betInfo.bet_count = 1;
                                betInfo.wanfa_type = wanfaList.getName() + wanfaList.getLists().get(tabindex).getName();
                            }
                        }
                        bufferNumbers.delete(bufferNumbers.toString().length() - 1, bufferNumbers.toString().length());
                        bufferNumbers.append("|");
                    }
                    betInf.setBet_dec(buffer.toString());
                    betInfo.numbers = bufferNumbers.toString().substring(0, bufferNumbers.toString().length() - 1);
                    betlist.add(betInfo);
                    if (unit_type == 1) {
                        betInf.setBet_money("每注2元");
                        betInf.setBet_point(2);
                    } else {
                        betInf.setBet_money("每注0.2元");
                        betInf.setBet_point(0.2);
                    }
                    betInf.bet_num = betInfo.bet_count;
                    betInf.setData(betlist);
                    betInf.setWanfa_name(betInfo.wanfa_type);
                    addItem(betInf);
                }
            }
        } else if (game_type == LotteryCons.FENFENCAI || game_type == LotteryCons.YDWFC) {
            for (int i = 0; i < count; i++) {
                Integer[] ints = null;
                StringBuffer buffer = new StringBuffer();
                StringBuffer bufferNumbers = new StringBuffer();
                BetInfo.DataBean betInfo = new BetInfo.DataBean();
                BetInfo betInf = new BetInfo();
                List<BetInfo.DataBean> betlist = new ArrayList<>();
                for (int k = 0; k < ballHm.get(tabindex).getLists().size(); k++) {
                    ints = LotteryCons.randomCommon(0, ballHm.get(tabindex).getLists().get(k).getList().size(), ballHm.get(tabindex).getLists().get(k).getSomeNum());
                    for (int j = 0; j < ints.length; j++) {
                        buffer.append(ballHm.get(tabindex).getLists().get(k).getName() + "_" + ballHm.get(tabindex).getLists().get(k).getList().get(ints[j]).getWanfa_name()).append(";");
                        bufferNumbers.append(ballHm.get(tabindex).getLists().get(k).getList().get(ints[j]).getWanfa_name());
                        betInfo.wanfa_id = ballHm.get(tabindex).getWanfa_id();
                        betInfo.unit_type = unit_type;
                        if (unit_type == 1) {
                            betInfo.money = 2;
                        } else {
                            betInfo.money = 0.2;
                        }
                        if (wanfaList.getName().contains("定位胆")) {
                            betInfo.bet_count = 5;
                        } else if (ballHm.get(tabindex).getName().contains("和值")) {
                            if (ints[j] < 10) {
                                betInfo.bet_count = ints[j] + 1;
                            } else {
                                betInfo.bet_count = 19 - ints[j];
                            }
                        } else {
                            betInfo.bet_count = 1;
                        }
                        betInfo.wanfa_type = wanfaList.getName() + ballHm.get(tabindex).getName();
                    }
                    bufferNumbers.append(",");
                }
                betInf.setBet_dec(buffer.toString());
                betInfo.numbers = bufferNumbers.toString().substring(0, bufferNumbers.toString().length() - 1);
                betlist.add(betInfo);
                if (unit_type == 1) {
                    betInf.setBet_money("每注2元");
                    betInf.setBet_point(2);
                } else {
                    betInf.setBet_money("每注0.2元");
                    betInf.setBet_point(0.2);
                }
                betInf.bet_num = betInfo.bet_count;
                betInf.setData(betlist);
                betInf.setWanfa_name(betInfo.wanfa_type);
                addItem(betInf);
            }
        } else if (game_type == LotteryCons.CQSSC || game_type == LotteryCons.TJSSC || game_type == LotteryCons.BJPK10 || game_type == LotteryCons.XYPK10) {
            for (int i = 0; i < count; i++) {
                Integer[] ints = null;
                boolean isFist = true;
                StringBuffer buffer = new StringBuffer();
                StringBuffer bufferNumbers = new StringBuffer();
                Integer[] ints1 = LotteryCons.randomCommon(0, ballHm.size(), 1);
                if (ballHm.get(ints1[0]).getName().equals("后三一码") || ballHm.get(ints1[0]).getName().equals("后三二码") || ballHm.get(ints1[0]).getName().equals("前三一码") || ballHm.get(ints1[0]).getName().equals("前三二码")
                        || ballHm.get(ints1[0]).getName().equals("前五三码") || ballHm.get(ints1[0]).getName().equals("前五二码") || ballHm.get(ints1[0]).getName().equals("后五三码") || ballHm.get(ints1[0]).getName().equals("后五二码")) {
                    ints = LotteryCons.randomCommon(0, ballHm.get(ints1[0]).getList().size(), ballHm.get(ints1[0]).getSomeNum());
                    BetInfo betInf = new BetInfo();
                    List<BetInfo.DataBean> betlist = new ArrayList<>();
                    BetInfo.DataBean betInfo = new BetInfo.DataBean();
                    for (int j = 0; j < ints.length; j++) {
                        if (isFist) {
                            buffer.append(ballHm.get(ints1[0]).getName());
                            isFist = false;
                        }
                        bufferNumbers.append(ballHm.get(ints1[0]).getList().get(ints[j]).getBili_name()).append(",");
                        betInfo.bet_count = 1;
                        betInfo.multiple_count = multipleCount;
                        betInfo.unit_type = unit_type;
                        betInfo.point = 2;
                        betInfo.wanfa_name = ballHm.get(ints1[0]).getList().get(ints[j]).getBili_name();
                        betInfo.bili_id = ballHm.get(ints1[0]).getList().get(ints[j]).getId();
                        betInfo.wanfa_type = ballHm.get(ints1[0]).getName();
                        buffer.append("_" + betInfo.wanfa_name + ";");
                    }
                    betInfo.betNumbers = bufferNumbers.toString().substring(0,bufferNumbers.length()-1);
                    betlist.add(betInfo);
                    if (unit_type == 1) {
                        betInf.setBet_money("每注2元");
                        betInf.setBet_point(2);
                    } else {
                        betInf.setBet_money("每注0.2元");
                        betInf.setBet_point(0.2);
                    }
                    betInf.bet_num = 1;
                    betInf.setData(betlist);
                    betInf.setWanfa_name(ballHm.get(ints1[0]).getName());
                    betInf.setBet_dec(buffer.toString());
                    addItem(betInf);
                } else {
                    ints = LotteryCons.randomCommon(0, ballHm.get(ints1[0]).getList().size(), 1);
                    for (int j = 0; j < ints.length; j++) {
                        BetInfo betInf = new BetInfo();
                        List<BetInfo.DataBean> betlist = new ArrayList<>();
                        BetInfo.DataBean betInfo = new BetInfo.DataBean();
                        betInfo.bet_count = 1;
                        betInfo.multiple_count = multipleCount;
                        betInfo.unit_type = unit_type;
                        betInfo.point = 2;
                        betInfo.wanfa_name = ballHm.get(ints1[0]).getList().get(ints[j]).getBili_name();
                        betInfo.bili_id = ballHm.get(ints1[0]).getList().get(ints[j]).getId();
                        betInfo.wanfa_type = ballHm.get(ints1[0]).getName();
                        betInf.setBet_dec(ballHm.get(ints1[0]).getName() + "_" + betInfo.wanfa_name + ";");
                        betlist.add(betInfo);
                        if (unit_type == 1) {
                            betInf.setBet_money("每注2元");
                            betInf.setBet_point(2);
                        } else {
                            betInf.setBet_money("每注0.2元");
                            betInf.setBet_point(0.2);
                        }
                        betInf.bet_num = 1;
                        betInf.setData(betlist);
                        betInf.setWanfa_name(ballHm.get(ints1[0]).getName());
                        addItem(betInf);
                    }
                }

            }
        } else {
            for (int i = 0; i < count; i++) {
                Integer[] ints = null;
                StringBuffer buffer = new StringBuffer();
                StringBuffer bufferNumbers = new StringBuffer();
                BetInfo.DataBean betInfo1 = new BetInfo.DataBean();
                BetInfo betInf1 = new BetInfo();
                List<BetInfo.DataBean> betlist1 = new ArrayList<>();
                for (int k = 0; k < ballHm.size(); k++) {
                    switch (game_type) {
                        case LotteryCons.XJP28:
                        case LotteryCons.JND_28:
                        case LotteryCons.BJ_28:
                            ints = LotteryCons.randomCommon(0, ballHm.get(k).getList().size(), 1);
                            break;
                        case LotteryCons.FUCAI3D:
                        case LotteryCons.PAILIE3:
                        case LotteryCons.PAILIE5:
                            ints = LotteryCons.randomCommon(0, ballHm.get(k).getList().size(), ballHm.get(k).getSomeNum());
                            break;
                    }
                    for (int j = 0; j < ints.length; j++) {
                        switch (game_type) {
                            case LotteryCons.XJP28:
                            case LotteryCons.JND_28:
                            case LotteryCons.BJ_28:
                                BetInfo betInf = new BetInfo();
                                List<BetInfo.DataBean> betlist = new ArrayList<>();
                                BetInfo.DataBean betInfo = new BetInfo.DataBean();
                                betInfo.bet_count = 1;
                                betInfo.multiple_count = multipleCount;
                                betInfo.unit_type = unit_type;
                                betInfo.point = 2;
                                betInfo.wanfa_name = ballHm.get(k).getList().get(ints[j]).getBili_name();
                                betInfo.bili_id = ballHm.get(k).getList().get(ints[j]).getId();
                                betInfo.wanfa_type = ballHm.get(k).getName();
                                betInf.setBet_dec(ballHm.get(k).getName() + "_" + betInfo.wanfa_name+ ";");
                                betlist.add(betInfo);
                                if (unit_type == 1) {
                                    betInf.setBet_money("每注2元");
                                    betInf.setBet_point(2);
                                } else {
                                    betInf.setBet_money("每注0.2元");
                                    betInf.setBet_point(0.2);
                                }
                                betInf.bet_num = 1;
                                betInf.setData(betlist);
                                betInf.setWanfa_name(ballHm.get(k).getName());
                                addItem(betInf);
                                break;
                            case LotteryCons.FUCAI3D:
                            case LotteryCons.PAILIE3:
                            case LotteryCons.PAILIE5:
                                buffer.append(ballHm.get(k).getName() + "_" + ballHm.get(k).getList().get(ints[j]).getWanfa_name()).append(";");
                                if (titleindex == 0 || titleindex == 3) {
                                    bufferNumbers.append(ballHm.get(k).getList().get(ints[j]).getWanfa_name()).append(",");
                                } else {
                                    bufferNumbers.append(ballHm.get(k).getList().get(ints[j]).getWanfa_name());
                                }
                                betInfo1.wanfa_id = ballHm.get(k).getWanfa_id();
                                betInfo1.unit_type = unit_type;
                                if (unit_type == 1) {
                                    betInfo1.money = 2;
                                } else {
                                    betInfo1.money = 0.2;
                                }
                                if (wanfaList.getName().contains("定位胆")) {
                                    betInfo1.bet_count = 3;
                                } else if (wanfaList.getName().contains("组三")) {
                                    betInfo1.bet_count = 2;
                                } else {
                                    betInfo1.bet_count = 1;
                                }
                                betInfo1.wanfa_type = wanfaList.getName();
                                break;
                        }
                    }
                }
                switch (game_type) {
                    case LotteryCons.FUCAI3D:
                    case LotteryCons.PAILIE3:
                    case LotteryCons.PAILIE5:
                        betInf1.setBet_dec(buffer.toString());
                        if (titleindex == 0 || titleindex == 3) {
                            betInfo1.numbers = bufferNumbers.toString().substring(0, bufferNumbers.toString().length() - 1);
                        } else {
                            betInfo1.numbers = bufferNumbers.toString();
                        }
                        betlist1.add(betInfo1);
                        if (unit_type == 1) {
                            betInf1.setBet_money("每注2元");
                            betInf1.setBet_point(2);
                        } else {
                            betInf1.setBet_money("每注0.2元");
                            betInf1.setBet_point(0.2);
                        }
                        if (wanfaList.getName().contains("定位胆")) {
                            betInf1.bet_num = 3;
                        } else if (wanfaList.getName().contains("组三")) {
                            betInf1.bet_num = 2;
                        } else {
                            betInf1.bet_num = 1;
                        }
                        betInf1.setData(betlist1);
                        betInf1.setWanfa_name(wanfaList.getName());
                        addItem(betInf1);
                        break;
                }
            }
        }
    }

    public void removeItem(int position) {
        betInfoList.remove(position);
        adapter.notifyDataSetChanged();
        setData();
    }

    public void addItem(BetInfo betInf) {
        betInfoList.add(betInf);
        adapter.notifyDataSetChanged();
        setData();
    }

    public void clearList() {
        betInfoList.clear();
        adapter.notifyDataSetChanged();
        lotteryMultiple.setText("1");
        lotteryIssueNum.setText("1");
        lotteryTotallyMsg.setOneText("");
        lotteryTotallyMsg.setTwoText("");
        allMoney = 0;
        allCount = 0;
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra(DATA, (Serializable) betInfoList);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        cathecticTimes.removeCallback();
    }

    public static void startActivity(Activity activity, List<BetInfo> betInfoList, int game_type, int mBaseWanfaId, String title, BiliListInfo.DataBean bililist, List<BiliListInfo.ListBeans> ballHm, int bet_type, int unit_type, int titleindex, int tabindex) {
        Intent intent = new Intent(activity, LotteryCathecticActivity.class);
        intent.putExtra(DATA, (Serializable) betInfoList);
        intent.putExtra(DATA_GAME_TYPE, game_type);
        intent.putExtra(DATA_TITLE, title);
        intent.putExtra(DATA_TYPE, bet_type);
        intent.putExtra(DATA_BILILIST, bililist);
        intent.putExtra(DATA_BASEWANFA_ID, mBaseWanfaId);
        intent.putExtra(DATA_WANFA_LIST, (Serializable) ballHm);
        intent.putExtra(TITLEINDEX, titleindex);
        intent.putExtra(TABINDEX, tabindex);
        intent.putExtra(UNIT_TYPE, unit_type);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    public static void startLhcActivity(Activity activity, List<BetInfo> betInfoList, int game_type, String title, LhcAllWanFaInfo.DataBean bililist, List<LhcAllWanFaInfo.DataBean.ListsBean> ballHm, int bet_type, int titleindex, int tabindex, int lhcPoint) {
        Intent intent = new Intent(activity, LotteryCathecticActivity.class);
        intent.putExtra(DATA, (Serializable) betInfoList);
        intent.putExtra(DATA_GAME_TYPE, game_type);
        intent.putExtra(DATA_TITLE, title);
        intent.putExtra(DATA_TYPE, bet_type);
        intent.putExtra(DATA_LHCBILILIST, bililist);
        intent.putExtra(DATA_LHCWANFA_LIST, (Serializable) ballHm);
        intent.putExtra(TITLEINDEX, titleindex);
        intent.putExtra(TABINDEX, tabindex);
        intent.putExtra(LHCPOINT, lhcPoint);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void Event(String messageEvent) {
        if (messageEvent.equals(UPDATE_TIME)) {
            if (System.currentTimeMillis() - curtime <= 3000) {
                curtime = System.currentTimeMillis();
                return;
            }
            curtime = System.currentTimeMillis();
            requestTime();
        }
    }

    public void getRoomOpenInfo() {
        InterfaceTask.getInstance().getRoomOpenInfo(game_type, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                RoomOpenInfo roomOpenInfo = (RoomOpenInfo) obj;
                setFJData(roomOpenInfo);
            }
        });
    }

    public void getLotteryOrderInfo(int mBaseWanfaId) {
        InterfaceTask.getInstance().getLotteryOrderInfo(mBaseWanfaId, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                LotterOrderInfo lotterOrderInfo = (LotterOrderInfo) obj;
                setZTData(lotterOrderInfo);
            }
        });
    }

    public void getlhcLotteryOrderInfo() {
        InterfaceTask.getInstance().getlhcLotteryOrderInfo(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                LhcLotteryOrderInfo lhcLotteryOrderInfo = (LhcLotteryOrderInfo) obj;
                setLhcData(lhcLotteryOrderInfo);
            }
        });
    }


    public void setFJData(RoomOpenInfo roomOpenInfo) {
        if (roomOpenInfo != null && roomOpenInfo.getData() != null) {
            choice_no = roomOpenInfo.getData().getGame_num();
            second = roomOpenInfo.getData().getSeconds();
            if(!tempQihao.equals(choice_no)){
                cathecticTimes.setChange(true);
                tempQihao = choice_no;
            }else{
                cathecticTimes.setChange(false);
            }
            setTime();
        }
    }

    public void setZTData(LotterOrderInfo lotterOrderInfo) {
        if (lotterOrderInfo != null && lotterOrderInfo.getData() != null) {
            choice_no = lotterOrderInfo.getData().getQihao();
            second = lotterOrderInfo.getData().getSecond();
            if(!tempQihao.equals(choice_no)){
                cathecticTimes.setChange(true);
                tempQihao = choice_no;
            }else{
                cathecticTimes.setChange(false);
            }
            setTime();
        }
    }

    public void setLhcData(LhcLotteryOrderInfo lhcLotteryOrderInfo) {
        if (lhcLotteryOrderInfo != null && lhcLotteryOrderInfo.getData() != null) {
            choice_no = lhcLotteryOrderInfo.getData().getQihao();
            second = lhcLotteryOrderInfo.getData().getSecond();
            if(!tempQihao.equals(choice_no)){
                cathecticTimes.setChange(true);
                tempQihao = choice_no;
            }else{
                cathecticTimes.setChange(false);
            }
            setTime();
        }
    }

}

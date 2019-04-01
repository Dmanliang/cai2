package com.sp.caitwo.ui.fragment.lottery;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.BaseLotteryFragment;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.info.LhcAllWanFaInfo;
import com.sp.caitwo.info.LhcLotteryOrderInfo;
import com.sp.caitwo.info.LhcWinsRecordInfo;
import com.sp.caitwo.ui.adapter.lottery.LhcHistoryAdapter;
import com.sp.caitwo.ui.adapter.lottery.LhcLotteryAdapter;
import com.sp.caitwo.ui.view.LotteryCountDown;
import com.sp.util.ViewUtil;
import com.sp.xwjlibrary.dialog.PopupDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.sp.util.Util.C;
import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by Administrator on 2018/11/10.
 */

public class LhcFragment extends BaseLotteryFragment implements View.OnClickListener {

    private String[] wanfa_name = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    public static final String[] redball = {"1", "2", "7", "8", "12", "13", "18", "19", "23", "24", "29", "30", "34", "35", "40", "45", "46"};
    public static final String[] blueball = {"3", "4", "9", "10", "14", "15", "20", "25", "26", "31", "36", "37", "41", "42", "47", "48"};
    public static final String[] greenball = {"5", "6", "11", "16", "17", "21", "22", "27", "28", "32", "33", "38", "39", "43", "44", "49"};
    private TextView tvIssue;
    private LotteryCountDown lotteryCountDown;
    private TextView tvIssueLottery;
    private LinearLayout llyLotteryNum;
    private LinearLayout llyLotteryName;
    private LinearLayout llyLotteryHistory;
    private RelativeLayout cathecticSelectLayout1;
    private XRecyclerView rv_bet;
    private EditText lotteryXiazhu;
    private TextView maxmoney;
    private TextView lotteryMachineSelect;
    private TextView lotteryTotally;
    private TextView lotteryOk;
    private TextView tvWanfaIntroduce;
    private TextView[] tvs_nums = new TextView[8];
    private TextView[] tvs_name = new TextView[8];
    private boolean isPanseBallAnim = true;
    private LhcLotteryAdapter lotterAdapter;
    private List<LhcAllWanFaInfo.DataBean.ListsBean> ballHm = new ArrayList<>();
    private int titleindex, tabindex;
    private int game_type;
    private View inflate;
    private Timer ballAnimTimer;
    private long curtime;
    private double allPoint, allmoney;
    private XRecyclerView xrvLotteryHistory;
    private PopupDialog myDialog;
    private List<LhcWinsRecordInfo.DataBean> historyList = new ArrayList<>();
    private LhcHistoryAdapter lhcHistoryAdapter;
    private int bet_count = 0, unit_type = 0, point = 2;
    private String mGameNum;
    private boolean isClear = false;
    private HashMap<String, ArrayList<String>> sublotto = new HashMap<>();
    private String tempQiHao = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.fragment_lhc, null, false);
            init();
        }
        return inflate;
    }

    private void init() {
        tvIssue = inflate.findViewById(R.id.tv_issue);
        lotteryCountDown = inflate.findViewById(R.id.lottery_countdown);
        tvIssueLottery = inflate.findViewById(R.id.tv_issue_lottery);
        llyLotteryNum = inflate.findViewById(R.id.lly_lottery_num);
        llyLotteryName = inflate.findViewById(R.id.lly_lottery_name);
        llyLotteryHistory = inflate.findViewById(R.id.lly_lottery_history);
        cathecticSelectLayout1 = inflate.findViewById(R.id.cathectic_select_layout1);
        rv_bet = inflate.findViewById(R.id.xrv_bet);
        lotteryXiazhu = inflate.findViewById(R.id.lottery_xiazhu);
        maxmoney = inflate.findViewById(R.id.maxmoney);
        lotteryMachineSelect = inflate.findViewById(R.id.lottery_machine_select);
        lotteryTotally = inflate.findViewById(R.id.lottery_totally);
        lotteryOk = inflate.findViewById(R.id.lottery_ok);
        View head = View.inflate(getContext(), R.layout.fragment_bet_class_head, null);
        tvWanfaIntroduce = head.findViewById(R.id.tv_wanfa_introduce);
        rv_bet.setLayoutManager(new LinearLayoutManager(LhcFragment.this.getContext()));
        rv_bet.addHeaderView(head);
        rv_bet.setPullRefreshEnabled(false);
        llyLotteryName.setVisibility(View.VISIBLE);
        lotteryOk.setOnClickListener(this);
        lotteryMachineSelect.setOnClickListener(this);
        llyLotteryHistory.setOnClickListener(this);
        lotteryTotally.setOnClickListener(this);
        cathecticSelectLayout1.setVisibility(View.VISIBLE);
        lotteryXiazhu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!BaseUtil.isEmpty(editable.toString())) {
                    point = Integer.parseInt(editable.toString());
                    allmoney = bet_count * point;
                    maxmoney.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
                }
            }
        });
        View lotteryHistoryView = View.inflate(getContext(), R.layout.ffc_lottery_history, null);
        xrvLotteryHistory = lotteryHistoryView.findViewById(R.id.xrv_lottery_history);
        myDialog = new PopupDialog(getContext(), lotteryHistoryView);
        xrvLotteryHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        xrvLotteryHistory.setPullRefreshEnabled(false);
        xrvLotteryHistory.setLoadingMoreEnabled(false);
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divide_line_horizontal_px1));
        xrvLotteryHistory.addItemDecoration(decor);
        lhcHistoryAdapter = new LhcHistoryAdapter(this.getContext(), historyList);
        xrvLotteryHistory.setAdapter(lhcHistoryAdapter);
        setBadgeView(lotteryOk);
    }

    public void getlhcLotteryOrderInfo(long curtime) {
        this.curtime = curtime;
        InterfaceTask.getInstance().getlhcLotteryOrderInfo(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                LhcLotteryOrderInfo lhcLotteryOrderInfo = (LhcLotteryOrderInfo) obj;
                setData(lhcLotteryOrderInfo);
            }
        });
    }

    public void lhcGetWinsRecord() {
        InterfaceTask.getInstance().lhcGetWinsRecord(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                LhcWinsRecordInfo lhcWinsRecordInfo = (LhcWinsRecordInfo) obj;
                if (null != lhcWinsRecordInfo.getData() || lhcWinsRecordInfo.getData().size() != 0) {
                    historyList.clear();
                    historyList.addAll(lhcWinsRecordInfo.getData());
                    lhcHistoryAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lottery_ok:
                if (onMenuItemClickListener != null) {
                    onMenuItemClickListener.onConfirmBet(mGameNum, badgeView, null, ballHm);
                }
                break;
            case R.id.lottery_machine_select:
                selectOrClear();
                break;
            case R.id.lottery_totally:
                if (onMenuItemClickListener != null) {
                    setSelectNum(ballHm);
                }
                break;
            case R.id.lly_lottery_history:
                myDialog.show(v);
                break;
        }
    }

    private void setLotteryAnim() {
        ballAnimTimer = new Timer("BallAnim", false);
        ballAnimTimer.schedule(new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                if (!isPanseBallAnim)
                    llyLotteryNum.post(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < tvs_nums.length; i++) {
                                if (i != 6) {
                                    tvs_nums[i].setText("0" + count);
                                }
                            }
                        }
                    });
                count++;
                if (count == 10) count = 0;
            }
        }, 0, 100);
    }

    public TextView setTextView(String text, int type) {
        int width = ViewUtil.Dip2Px(LhcFragment.this.getContext(), 16);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);
        lp.setMargins(5, 0, 5, 0);
        TextView textView = new TextView(LhcFragment.this.getContext());
        textView.setGravity(Gravity.CENTER);
        if (type == 0) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            textView.setTextColor(Color.WHITE);
            if (Arrays.asList(redball).contains(text)) {
                textView.setBackground(this.getResources().getDrawable(R.drawable.shape_lottery_ball_red_red)); //红色球设置背景
            } else if (Arrays.asList(blueball).contains(text)) {
                textView.setBackground(this.getResources().getDrawable(R.drawable.shape_lottery_ball_blue)); //蓝色球设置背景
            } else if (Arrays.asList(greenball).contains(text)) {
                textView.setBackground(this.getResources().getDrawable(R.drawable.shape_lottery_ball_green)); //绿色球设置背景
            }
        } else {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.color_333));
        }
        textView.setText(text);
        textView.setLayoutParams(lp);
        return textView;
    }

    public void startLotteryAnim() {
        isPanseBallAnim = false;
    }

    public void stopLotteryAnim() {
        isPanseBallAnim = true;
    }


    public void setData(LhcLotteryOrderInfo lhcLotteryOrderInfo) {
        llyLotteryNum.removeAllViews();
        llyLotteryName.removeAllViews();
        tvs_nums[0] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber1(), 0);
        tvs_nums[1] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber2(), 0);
        tvs_nums[2] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber3(), 0);
        tvs_nums[3] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber4(), 0);
        tvs_nums[4] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber5(), 0);
        tvs_nums[5] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber6(), 0);
        tvs_nums[7] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber7(), 0);
        tvs_name[0] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber1_sx(), 1);
        tvs_name[1] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber2_sx(), 1);
        tvs_name[2] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber3_sx(), 1);
        tvs_name[3] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber4_sx(), 1);
        tvs_name[4] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber5_sx(), 1);
        tvs_name[5] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber6_sx(), 1);
        tvs_name[7] = setTextView(lhcLotteryOrderInfo.getData().getWin_info().getNumber7_sx(), 1);
        tvs_nums[6] = setTextView("+", 1);
        tvs_name[6] = setTextView("+", 1);
        for (int i = 0; i < tvs_name.length; i++) {
            llyLotteryNum.addView(tvs_nums[i]);
            llyLotteryName.addView(tvs_name[i]);
        }
        setLotteryAnim();
        mGameNum = lhcLotteryOrderInfo.getData().getQihao();
        if (!tempQiHao.equals(mGameNum)) {
            lotteryCountDown.setChange(true);
            stopLotteryAnim();
            tempQiHao = mGameNum;
        } else {
            lotteryCountDown.setChange(false);
        }
        tvIssue.setText(String.format(getString(R.string.how_issue), lhcLotteryOrderInfo.getData().getQihao()));
        tvIssueLottery.setText(String.format(getString(R.string.issue_lottery), lhcLotteryOrderInfo.getData().getWin_info().getQihao()));
        setTime(lhcLotteryOrderInfo.getData().getSecond());
    }

    public void getData(int game_type, int titleindex, final int tabindex, List<LhcAllWanFaInfo.DataBean> lhcwanfalist) {
        this.game_type = game_type;
        this.titleindex = titleindex;
        this.tabindex = tabindex;
        ballHm.clear();
        if (lhcwanfalist.get(titleindex).getLists().size() != 0) {
            if (titleindex == 8) {
                LhcAllWanFaInfo.DataBean.ListsBean listBeans = new LhcAllWanFaInfo.DataBean.ListsBean();
                List<LhcAllWanFaInfo.DataBean.ListsBean.ListBean> listbean = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    LhcAllWanFaInfo.DataBean.ListsBean.ListBean tempbean = lhcwanfalist.get(titleindex).getLists().get(tabindex).getList().get(0);
                    LhcAllWanFaInfo.DataBean.ListsBean.ListBean bean = new LhcAllWanFaInfo.DataBean.ListsBean.ListBean(tempbean.getId(), wanfa_name[i],
                            tempbean.getA_odds(), tempbean.getB_odds(), tempbean.getC_odds(), tempbean.getIs_disable(), tempbean.getRemarks(), tempbean.getQuota(),
                            tempbean.getOdd(), tempbean.getWanfa_parentname(), tempbean.getMin_money(), tempbean.getMax_money(), tempbean.getShengxiao_nums());
                    listbean.add(bean);
                }
                listBeans.setSomeNum(lhcwanfalist.get(titleindex).getLists().get(tabindex).getSomeNum());
                listBeans.setWanfa_dec(lhcwanfalist.get(titleindex).getLists().get(tabindex).getWanfa_dec());
                listBeans.setMax_bili(lhcwanfalist.get(titleindex).getLists().get(tabindex).getMax_bili());
                listBeans.setMin_bili(lhcwanfalist.get(titleindex).getLists().get(tabindex).getMin_bili());
                listBeans.getList().addAll(listbean);
                ballHm.add(listBeans);
            } else if (titleindex == 9) {
                LhcAllWanFaInfo.DataBean.ListsBean listBeans = new LhcAllWanFaInfo.DataBean.ListsBean();
                List<LhcAllWanFaInfo.DataBean.ListsBean.ListBean> listbean = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    LhcAllWanFaInfo.DataBean.ListsBean.ListBean tempbean = lhcwanfalist.get(titleindex).getLists().get(tabindex).getList().get(0);
                    LhcAllWanFaInfo.DataBean.ListsBean.ListBean bean = new LhcAllWanFaInfo.DataBean.ListsBean.ListBean(tempbean.getId(), i + "尾",
                            tempbean.getA_odds(), tempbean.getB_odds(), tempbean.getC_odds(), tempbean.getIs_disable(), tempbean.getRemarks(), tempbean.getQuota(),
                            tempbean.getOdd(), tempbean.getWanfa_parentname(), tempbean.getMin_money(), tempbean.getMax_money(), tempbean.getShengxiao_nums());
                    listbean.add(bean);
                }
                listBeans.setSomeNum(lhcwanfalist.get(titleindex).getLists().get(tabindex).getSomeNum());
                listBeans.setWanfa_dec(lhcwanfalist.get(titleindex).getLists().get(tabindex).getWanfa_dec());
                listBeans.setMax_bili(lhcwanfalist.get(titleindex).getLists().get(tabindex).getMax_bili());
                listBeans.setMin_bili(lhcwanfalist.get(titleindex).getLists().get(tabindex).getMin_bili());
                listBeans.getList().addAll(listbean);
                ballHm.add(listBeans);
            } else if (titleindex == 3) {
                LhcAllWanFaInfo.DataBean.ListsBean listBeans = new LhcAllWanFaInfo.DataBean.ListsBean();
                List<LhcAllWanFaInfo.DataBean.ListsBean.ListBean> listbean = new ArrayList<>();
                for (int i = 1; i <= 49; i++) {
                    LhcAllWanFaInfo.DataBean.ListsBean.ListBean tempbean = lhcwanfalist.get(titleindex).getLists().get(tabindex).getList().get(0);
                    LhcAllWanFaInfo.DataBean.ListsBean.ListBean bean = new LhcAllWanFaInfo.DataBean.ListsBean.ListBean(tempbean.getId(), i + "",
                            tempbean.getA_odds(), tempbean.getB_odds(), tempbean.getC_odds(), tempbean.getIs_disable(), tempbean.getRemarks(), tempbean.getQuota(),
                            tempbean.getOdd(), tempbean.getWanfa_parentname(), tempbean.getMin_money(), tempbean.getMax_money(), tempbean.getShengxiao_nums());
                    listbean.add(bean);
                }
                listBeans.setSomeNum(lhcwanfalist.get(titleindex).getLists().get(tabindex).getSomeNum());
                listBeans.setWanfa_dec(lhcwanfalist.get(titleindex).getLists().get(tabindex).getWanfa_dec());
                listBeans.setMax_bili(lhcwanfalist.get(titleindex).getLists().get(tabindex).getMax_bili());
                listBeans.setMin_bili(lhcwanfalist.get(titleindex).getLists().get(tabindex).getMin_bili());
                listBeans.getList().addAll(listbean);
                ballHm.add(listBeans);
            } else {
                ballHm.add(lhcwanfalist.get(titleindex).getLists().get(tabindex));
            }
        } else if (lhcwanfalist.get(titleindex).getList().size() != 0) {
            if (titleindex == 7) {
                LhcAllWanFaInfo.DataBean.ListsBean listBeans = new LhcAllWanFaInfo.DataBean.ListsBean();
                List<LhcAllWanFaInfo.DataBean.ListsBean.ListBean> listbean = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    LhcAllWanFaInfo.DataBean.ListsBean.ListBean tempbean = lhcwanfalist.get(titleindex).getList().get(tabindex);
                    LhcAllWanFaInfo.DataBean.ListsBean.ListBean bean = new LhcAllWanFaInfo.DataBean.ListsBean.ListBean(tempbean.getId(), wanfa_name[i],
                            tempbean.getA_odds(), tempbean.getB_odds(), tempbean.getC_odds(), tempbean.getIs_disable(), tempbean.getRemarks(), tempbean.getQuota(),
                            tempbean.getOdd(), tempbean.getWanfa_parentname(), tempbean.getMin_money(), tempbean.getMax_money(), tempbean.getShengxiao_nums());
                    listbean.add(bean);
                }
                listBeans.setSomeNum(lhcwanfalist.get(titleindex).getList().get(tabindex).getSomeNum());
                listBeans.setWanfa_dec(lhcwanfalist.get(titleindex).getWanfa_dec());
                listBeans.setMax_bili(lhcwanfalist.get(titleindex).getMax_bili());
                listBeans.setMin_bili(lhcwanfalist.get(titleindex).getMin_bili());
                listBeans.getList().addAll(listbean);
                ballHm.add(listBeans);
            } else if (titleindex == 10) {
                LhcAllWanFaInfo.DataBean.ListsBean listBeans = new LhcAllWanFaInfo.DataBean.ListsBean();
                List<LhcAllWanFaInfo.DataBean.ListsBean.ListBean> listbean = new ArrayList<>();
                for (int i = 1; i <= 49; i++) {
                    LhcAllWanFaInfo.DataBean.ListsBean.ListBean tempbean = lhcwanfalist.get(titleindex).getList().get(tabindex);
                    LhcAllWanFaInfo.DataBean.ListsBean.ListBean bean = new LhcAllWanFaInfo.DataBean.ListsBean.ListBean(tempbean.getId(), i + "",
                            tempbean.getA_odds(), tempbean.getB_odds(), tempbean.getC_odds(), tempbean.getIs_disable(), tempbean.getRemarks(), tempbean.getQuota(),
                            tempbean.getOdd(), tempbean.getWanfa_parentname(), tempbean.getMin_money(), tempbean.getMax_money(), tempbean.getShengxiao_nums());
                    listbean.add(bean);
                }
                listBeans.setSomeNum(lhcwanfalist.get(titleindex).getList().get(tabindex).getSomeNum());
                listBeans.setWanfa_dec(lhcwanfalist.get(titleindex).getWanfa_dec());
                listBeans.setMax_bili(lhcwanfalist.get(titleindex).getMax_bili());
                listBeans.setMin_bili(lhcwanfalist.get(titleindex).getMin_bili());
                listBeans.getList().addAll(listbean);
                ballHm.add(listBeans);
            } else {
                LhcAllWanFaInfo.DataBean.ListsBean listBeans = new LhcAllWanFaInfo.DataBean.ListsBean();
                List<LhcAllWanFaInfo.DataBean.ListsBean.ListBean> listBean = new ArrayList<>();
                listBean.addAll(lhcwanfalist.get(titleindex).getList());
                listBeans.getList().addAll(listBean);
                listBeans.setSomeNum(lhcwanfalist.get(titleindex).getSomeNum());
                listBeans.setWanfa_dec(lhcwanfalist.get(titleindex).getWanfa_dec());
                listBeans.setMax_bili(lhcwanfalist.get(titleindex).getMax_bili());
                listBeans.setMin_bili(lhcwanfalist.get(titleindex).getMin_bili());
                ballHm.add(listBeans);
            }
        }
        lotterAdapter = new LhcLotteryAdapter(LhcFragment.this.getContext(), ballHm, titleindex, tabindex);
        rv_bet.setAdapter(lotterAdapter);
        resetData();
        lotterAdapter.setOnItemClickListener(new LhcLotteryAdapter.OnItemClickListener() {

            @Override
            public void OnItemImageViewClick(View view, int position, int position_item) {
                ballHm.get(position).getList().get(position_item).setFocus(!ballHm.get(position).getList().get(position_item).isFocus());
                lotterAdapter.notifyDataSetChanged();
                ItemClick();
            }

        });
        LotteryCons.setLhcWanfaDec(tvWanfaIntroduce, titleindex, tabindex, lhcwanfalist);
    }

    public void setTime(int secd) {
        if (secd >= 0) {
            lotteryCountDown.startCountDown(secd, 1000, 1, curtime, game_type);
        } else {
            lotteryCountDown.setChange(false);
            lotteryCountDown.startCountDown(secd, 1000, 3, curtime, game_type);
        }
    }

    public void selectOrClear() {
        if (isClear) {
            resetData();
        } else {
            randomNum();
        }
    }

    public void randomNum() {
        resetData();
        for (int i = 0; i < ballHm.size(); i++) {
            Integer[] ints = LotteryCons.randomCommon(0, ballHm.get(i).getList().size(), ballHm.get(i).getSomeNum());
            for (int j = 0; j < ints.length; j++) {
                ballHm.get(i).getList().get(ints[j]).setFocus(true);
            }
        }
        getBetCount(ballHm);
        allmoney = point * bet_count;
        maxmoney.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
        lotteryTotally.setTextColor(this.getResources().getColor(R.color.big_double_yellow));
        lotteryMachineSelect.setText("清空");
        isClear = true;
    }

    public void ItemClick() {
        getBetCount(ballHm);
        if (bet_count != 0) {
            lotteryMachineSelect.setText("清空");
            isClear = true;
            allmoney = point * this.bet_count;
            maxmoney.setText("共" + this.bet_count + "注," + getTwoDecimal(allmoney) + "元");
            lotteryTotally.setTextColor(this.getResources().getColor(R.color.big_double_yellow));
        } else {
            resetData();
        }
    }

    private void clearList() {
        for (int i = 0; i < ballHm.size(); i++) {
            if (ballHm.get(i).getList().size() != 0) {
                for (int j = 0; j < ballHm.get(i).getList().size(); j++) {
                    ballHm.get(i).getList().get(j).setFocus(false);
                }
            }
        }
        lotterAdapter.notifyDataSetChanged();
    }

    public void resetData() {
        clearList();
        sublotto.clear();
        lotteryMachineSelect.setText("机选");
        isClear = false;
        allmoney = 0;
        bet_count = 0;
        maxmoney.setText("共0注,0元");
        lotteryTotally.setTextColor(this.getResources().getColor(R.color.text_white));
    }

    public void getBetCount(List<LhcAllWanFaInfo.DataBean.ListsBean> data) {
        int count = 0;
        int allcount = 0;
        int size = data.size();
        boolean isfirst = true;
        for (int i = 0; i < size; i++) {
            List<LhcAllWanFaInfo.DataBean.ListsBean.ListBean> listBeanList = data.get(i).getList();
            ArrayList<String> blHm = new ArrayList<>();
            for (LhcAllWanFaInfo.DataBean.ListsBean.ListBean listBean : listBeanList) {
                if (listBean.isFocus()) {
                    blHm.add(listBean.getWanfa_name());
                    sublotto.put(i + "", blHm);
                    count++;
                    if (isfirst) {
                        allcount = 1;
                        isfirst = false;
                    }
                }
            }
            count = C(data.get(i).getSomeNum(), count);
            allcount = count * allcount;
            count = 0;
        }
        bet_count = allcount;
    }

    public void setSelectNum(List<LhcAllWanFaInfo.DataBean.ListsBean> data) {
        getBetCount(data);
        if (lotteryXiazhu.getText().toString().equals("")) {
            ToastUtil.Toast(this.getContext(), "请输入投注金额!");
            return;
        }
        if (point == 0) {
            ToastUtil.Toast(this.getContext(), "投注金额不能为0!");
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            if (null == sublotto.get(i + "") || sublotto.get(i + "").size() < data.get(i).getSomeNum()) {
                ToastUtil.Toast(this.getContext(), "至少选择" + data.get(i).getSomeNum() + "个码！");
                return;
            }
        }
        sublotto.clear();
        onMenuItemClickListener.onAddLhcBetNum(ballHm, mPosition, badgeView, lotterAdapter, mGameNum, bet_count, point);
        resetData();
    }

    public void setBadgeView(BadgeView badgeView) {
        this.badgeView = badgeView;
    }

    public BadgeView getBadgeView() {
        return badgeView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        lotteryCountDown.removeCallback();
    }
}

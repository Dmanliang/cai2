package com.sp.caitwo.ui.fragment.lottery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.BaseLotteryFragment;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.info.LotterOrderInfo;
import com.sp.caitwo.ui.adapter.lottery.LotteryAdapter;
import com.sp.caitwo.ui.adapter.lottery.ZhiTouLotteryHistoryAdapter;
import com.sp.caitwo.ui.info.LotteryKJLogInfo;
import com.sp.caitwo.ui.view.LotteryCountDown;
import com.sp.caitwo.ui.view.NestXRecycleView;
import com.sp.xwjlibrary.dialog.PopupDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.sp.util.Util.A;
import static com.sp.util.Util.C;
import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by Administrator on 2018/11/15.
 */

public class ElevenXFiveFragment extends BaseLotteryFragment implements View.OnClickListener {

    private TextView tvIssue;
    private TextView tvIssueLottery;
    private LinearLayout llyLotteryNum;
    private RelativeLayout cathecticSelectLayout2;
    private RadioGroup selectMoney;
    private TextView cathecticTotalMsg;
    private NestXRecycleView rvBet;
    private EditText lotteryXiazhu;
    private TextView maxMoney;
    private TextView lotteryMachineSelect;
    private TextView lotteryTotally;
    private TextView lotteryOk;
    private TextView tvWanfaIntroduce;
    private LotteryAdapter lotterAdapter;
    private TextView[] tvs = new TextView[5];
    private boolean isPanseBallAnim = true;
    private ArrayList<BiliListInfo.ListBeans> ballHm = new ArrayList<>();
    private ArrayList<LotteryKJLogInfo.DataBean> historylist = new ArrayList<>();
    private ZhiTouLotteryHistoryAdapter lotteryHistoryAdapter;
    private PopupDialog myDialog;
    private XRecyclerView xrvLotteryHistory;
    private int titleindex, tabindex;
    private int mBaseWanfaId;
    private View inflate;
    private LotteryCountDown lotterTimer;
    private long curtime;
    private String mGameNum;
    private int bet_count = 0, game_type, unit_type = 1;
    private double point = 2, allmoney = 0;
    private boolean isClear = false;
    private HashMap<String, ArrayList<String>> sublotto = new HashMap<>();
    private String tempNumbers = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.fragment_11x5, null, false);
            init();
            initBas();
        }
        return inflate;
    }

    private void initBas() {
        initBaseUI(inflate);
        lotteryTotally.setOnClickListener(this);
        lotteryMachineSelect.setOnClickListener(this);
        lotteryOk.setOnClickListener(this);
        setBadgeView(lotteryOk);
    }

    private void init() {
        tvIssue = inflate.findViewById(R.id.tv_issue);
        lotterTimer = inflate.findViewById(R.id.lottery_countdown);
        tvIssueLottery = inflate.findViewById(R.id.tv_issue_lottery);
        llyLotteryNum = inflate.findViewById(R.id.lly_lottery_num);
        rvBet = inflate.findViewById(R.id.xrv_bet);
        lotteryXiazhu = inflate.findViewById(R.id.lottery_xiazhu);
        maxMoney = inflate.findViewById(R.id.maxmoney);
        lotteryMachineSelect = inflate.findViewById(R.id.lottery_machine_select);
        lotteryTotally = inflate.findViewById(R.id.lottery_totally);
        lotteryOk = inflate.findViewById(R.id.lottery_ok);
        selectMoney = inflate.findViewById(R.id.select_money);
        cathecticTotalMsg = inflate.findViewById(R.id.cathectic_total_msg);
        cathecticSelectLayout2 = inflate.findViewById(R.id.cathectic_select_layout2);
        cathecticSelectLayout2.setVisibility(View.VISIBLE);
        selectMoney.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.select_money_rb1:
                        point = 2;
                        unit_type = 1;
                        if (isClear) {
                            allmoney = point * bet_count;
                            cathecticTotalMsg.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
                        }
                        break;
                    case R.id.select_money_rb2:
                        point = 0.2;
                        unit_type = 2;
                        if (isClear) {
                            allmoney = point * bet_count;
                            cathecticTotalMsg.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
                        }
                        break;
                }
            }
        });
        View head = View.inflate(getContext(), R.layout.fragment_bet_class_head, null);
        tvWanfaIntroduce = head.findViewById(R.id.tv_wanfa_introduce);
        rvBet.setLayoutManager(new LinearLayoutManager(ElevenXFiveFragment.this.getContext()));
        rvBet.addHeaderView(head);
        rvBet.setPullRefreshEnabled(false);
        lotteryOk.setOnClickListener(this);
        inflate.findViewById(R.id.lly_lottery_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show(view);
            }
        });
        View lotteryHistoryView = View.inflate(getContext(), R.layout.ffc_lottery_history, null);
        xrvLotteryHistory = lotteryHistoryView.findViewById(R.id.xrv_lottery_history);
        myDialog = new PopupDialog(getContext(), lotteryHistoryView);
        xrvLotteryHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lotteryHistoryAdapter = new ZhiTouLotteryHistoryAdapter(getContext(), historylist, game_type);
        xrvLotteryHistory.setAdapter(lotteryHistoryAdapter);
        xrvLotteryHistory.setPullRefreshEnabled(false);
        xrvLotteryHistory.setLoadingMoreEnabled(false);
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divide_line_horizontal_px1));
        xrvLotteryHistory.addItemDecoration(decor);
        setLotteryAnim();
    }

    private void setLotteryAnim() {
        llyLotteryNum.removeAllViews();
        if (llyLotteryNum.getChildCount() == 0) {
            for (int i = 0; i < tvs.length; i++) {
                tvs[i] = new TextView(getContext());
                tvs[i].setBackgroundResource(R.drawable.icon_ball_red);
                tvs[i].setGravity(Gravity.CENTER);
                tvs[i].setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                tvs[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
                llyLotteryNum.addView(tvs[i]);
            }
        }
        Timer ballAnimTimer = new Timer("BallAnim", false);
        ballAnimTimer.schedule(new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                if (!isPanseBallAnim)
                    llyLotteryNum.post(new Runnable() {
                        @Override
                        public void run() {
                            for (TextView tv : tvs) {
                                tv.setText("0" + count);
                            }
                        }
                    });
                count++;
                if (count == 10) count = 0;
            }
        }, 0, 130);
    }

    public void startLotteryAnim() {
        isPanseBallAnim = false;
    }

    public void stopLotteryAnim() {
        isPanseBallAnim = true;
    }

    public void getLotteryOrderInfo(int mBaseWanfaId, long curtime) {
        this.curtime = curtime;
        InterfaceTask.getInstance().getLotteryOrderInfo(mBaseWanfaId, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                LotterOrderInfo lotterOrderInfo = (LotterOrderInfo) obj;
                setData(lotterOrderInfo);
            }
        });
    }

    public void getLotteryKjLog(int mBaseWanfaId) {
        InterfaceTask.getInstance().getLotteryKjLog(mBaseWanfaId, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                LotteryKJLogInfo lotteryKJLogInfo = (LotteryKJLogInfo) obj;
                if (null != lotteryKJLogInfo.getData() || lotteryKJLogInfo.getData().size() != 0) {
                    historylist.clear();
                    historylist.addAll(lotteryKJLogInfo.getData());
                    lotteryHistoryAdapter.notifyDatasChang();
                }
            }
        });
    }

    public void setData(LotterOrderInfo lotterOrderInfo) {

        mGameNum = lotterOrderInfo.getData().getQihao();
        tvIssue.setText(String.format(getString(R.string.how_issue), lotterOrderInfo.getData().getQihao() != null ? lotterOrderInfo.getData().getQihao() : ""));
        tvIssueLottery.setText(String.format(getString(R.string.issue_lottery), lotterOrderInfo.getData().getWin_info().getQihao() != null ? lotterOrderInfo.getData().getWin_info().getQihao() : ""));
        String[] numArray = null;
        String get_result = lotterOrderInfo.getData().getWin_info().getNumbers();
        if (!tempNumbers.equals(get_result)) {
            lotterTimer.setChange(true);
            stopLotteryAnim();
            tempNumbers = get_result;
        } else {
            lotterTimer.setChange(false);
        }
        numArray = get_result.split(",");
        int index = 0;
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setText(numArray[index]);
            index++;
        }
        setTime(lotterOrderInfo.getData().getSecond());
    }

    public void setTime(int secd) {
        if (secd >= 0) {
            lotterTimer.startCountDown(secd, 1000, 1, curtime, game_type);
        } else {
            lotterTimer.setChange(false);
            lotterTimer.startCountDown(secd, 1000, 3, curtime, game_type);
        }
    }

    public void getBetCount(List<BiliListInfo.ListBeans> data) {
        int count = 0;
        int allcount = 0;
        int tempcount = 0;
        boolean isfirst = true;
        if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
            int size = data.get(tabindex).getLists().size();
            if (null != data.get(tabindex).getLists() && size != 0) {
                List<BiliListInfo.ListBean2> listBeanList2 = data.get(tabindex).getLists();
                for (int i = 0; i < size; i++) {
                    ArrayList<String> blHm = new ArrayList<>();
                    for (BiliListInfo.ListBean listBean : listBeanList2.get(i).getList()) {
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
                    if (sublotto.get(i + "") == null) {
                        blHm.add("");
                        sublotto.put(i + "", blHm);
                    }
                    count = C(data.get(tabindex).getLists().get(i).getSomeNum(), count);
                    allcount = count * allcount;
                    count = 0;
                }
            }
        } else {
            int size = data.size();
            for (int i = 0; i < size; i++) {
                if (null != data.get(i).getList() && data.get(i).getList().size() != 0) {
                    List<BiliListInfo.ListBean> listBeanList = data.get(i).getList();
                    ArrayList<String> blHm = new ArrayList<>();
                    for (BiliListInfo.ListBean listBean : listBeanList) {
                        if (listBean.isFocus()) {
                            blHm.add(listBean.getWanfa_name());
                            sublotto.put(i + "", blHm);
                            count++;
                            if (titleindex != 5) {
                                if (isfirst) {
                                    allcount = 1;
                                    isfirst = false;
                                }
                            }
                        }
                    }
                    if (titleindex == 5) {
                        tempcount += count;
                        allcount = tempcount;
                    } else {
                        count = C(data.get(i).getSomeNum(), count);
                        allcount = count * allcount;
                    }
                    count = 0;
                } else if (null != data.get(i).getLists() && data.get(i).getLists().size() != 0) {
                    List<BiliListInfo.ListBean2> listBeanList2 = data.get(i).getLists();
                    ArrayList<String> blHm = new ArrayList<>();
                    for (BiliListInfo.ListBean2 listBean2 : listBeanList2) {
                        for (BiliListInfo.ListBean listBean : listBean2.getList()) {
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
                        if (sublotto.get(i + "") == null) {
                            blHm.add("");
                            sublotto.put(i + "", blHm);
                        }
                        count = C(listBean2.getSomeNum(), count);
                        allcount = count * allcount;
                        count = 0;
                    }
                }
            }
        }
        bet_count = allcount;
    }

    public void setSelectNum(List<BiliListInfo.ListBeans> data) {
        getBetCount(data);
        if (titleindex == 5) {
            if (bet_count == 0) {
                ToastUtil.Toast(this.getContext(), "至少选择一个号码");
                return;
            }
        } else if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
            for (int i = 0; i < data.get(tabindex).getLists().size(); i++) {
                if (sublotto.size() != 0) {
                    for (int j = 0; j < sublotto.size(); j++) {
                        if (sublotto.get(j + "").size() == 0 || null == sublotto.get(j + "") || sublotto.get(j + "").get(0).equals("") || sublotto.get(j + "").size() < data.get(tabindex).getLists().get(i).getSomeNum()) {
                            ToastUtil.Toast(this.getContext(), "每位至少选择" + data.get(tabindex).getLists().get(i).getSomeNum() + "个码！");
                            return;
                        }
                    }
                } else {
                    ToastUtil.Toast(this.getContext(), "每位至少选择一个号码");
                    return;
                }
            }
        } else {
            for (int i = 0; i < data.size(); i++) {
                if (null == sublotto.get(i + "") || sublotto.get(i + "").size() < data.get(i).getSomeNum()) {
                    ToastUtil.Toast(this.getContext(), "每位至少选择" + data.get(i).getSomeNum() + "个码！");
                    return;
                }
            }
        }
        sublotto.clear();
        onMenuItemClickListener.onAddBetNum(lotterAdapter.getData(), mPosition, unit_type, badgeView, lotterAdapter, mGameNum, bet_count);
        resetData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lottery_machine_select:
                selectOrClear();
                break;
            case R.id.lottery_totally:
                if (onMenuItemClickListener != null) {
                    setSelectNum(lotterAdapter.getData());
                }
                break;
            case R.id.lottery_ok:
                if (onMenuItemClickListener != null) {
                    onMenuItemClickListener.onConfirmBet(mGameNum, badgeView, ballHm, null);
                }
                break;
            case R.id.lottery_head_layout:
                myDialog.show();
                break;
        }
    }

    public void getData(int mBaseWanfaId, final int game_type, final int titleindex, final int tabindex, List<BiliListInfo.DataBean> bililist) {
        this.game_type = game_type;
        this.titleindex = titleindex;
        this.tabindex = tabindex;
        this.tabindex = tabindex;
        this.mBaseWanfaId = mBaseWanfaId;
        ballHm.clear();
        if (bililist.get(titleindex).getLists().size() != 0) {
            if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
                ballHm.addAll(bililist.get(titleindex).getLists());
            } else if (titleindex == 5) {
                ballHm.addAll(bililist.get(titleindex).getLists());
                for (BiliListInfo.ListBeans listBeans : ballHm) {
                    listBeans.setWanfa_id(bililist.get(titleindex).getWanfa_id());
                    listBeans.setWanfa_odds(bililist.get(titleindex).getWanfa_odds());
                }
            } else {
                BiliListInfo.ListBeans listBeans = new BiliListInfo.ListBeans();
                List<BiliListInfo.ListBean> listBean = new ArrayList<>();
                listBean.addAll(bililist.get(titleindex).getLists().get(tabindex).getList());
                listBeans.setName(bililist.get(titleindex).getLists().get(tabindex).getName());
                listBeans.setWanfa_id(bililist.get(titleindex).getLists().get(tabindex).getWanfa_id());
                listBeans.setSomeNum(bililist.get(titleindex).getLists().get(tabindex).getSomeNum());
                listBeans.getList().addAll(listBean);
                ballHm.add(listBeans);
            }
        }
        lotterAdapter = new LotteryAdapter(ElevenXFiveFragment.this.getContext(), ballHm, titleindex, tabindex, game_type, mBaseWanfaId);
        rvBet.setAdapter(lotterAdapter);
        resetData();
        lotterAdapter.setOnItemClickListener(new LotteryAdapter.OnItemClickListener() {

            @Override
            public void OnItemImageViewClick(View view, int position, int position_item) {
                mPosition = position;
                if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
                    ballHm.get(tabindex).getLists().get(position).getList().get(position_item).setFocus(!ballHm.get(tabindex).getLists().get(position).getList().get(position_item).isFocus());
                } else {
                    ballHm.get(position).getList().get(position_item).setFocus(!ballHm.get(position).getList().get(position_item).isFocus());
                }
                lotterAdapter.notifyDataSetChanged();
                ItemClick();
            }

        });
        lotterAdapter.setOnItemTypeClickListener(new LotteryAdapter.OnItemTypeClickListener() {
            @Override
            public void OnItemTypeClick(View view, String key, int position) {
                typeFocus("clear", position);
                typeFocus(key, position);
                ItemClick();
            }
        });
        LotteryCons.setWanfaDec(tvWanfaIntroduce, titleindex, tabindex, bililist);
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
        if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
            for (int i = 0; i < ballHm.get(tabindex).getLists().size(); i++) {
                Integer[] ints;
                ints = LotteryCons.randomCommon(0, ballHm.get(tabindex).getLists().get(i).getList().size(), ballHm.get(tabindex).getLists().get(i).getSomeNum());
                for (int j = 0; j < ints.length; j++) {
                    ballHm.get(tabindex).getLists().get(i).getList().get(ints[j]).setFocus(true);
                }
            }
        } else {
            for (int i = 0; i < ballHm.size(); i++) {
                Integer[] ints;
                ints = LotteryCons.randomCommon(0, ballHm.get(i).getList().size(), ballHm.get(i).getSomeNum());
                for (int j = 0; j < ints.length; j++) {
                    ballHm.get(i).getList().get(ints[j]).setFocus(true);
                }
            }
        }
        getBetCount(ballHm);
        allmoney = point * bet_count;
        cathecticTotalMsg.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
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
            cathecticTotalMsg.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
            lotteryTotally.setTextColor(this.getResources().getColor(R.color.big_double_yellow));
        } else {
            resetData();
        }
    }

    public void resetData() {
        clearList();
        sublotto.clear();
        lotteryMachineSelect.setText("机选");
        isClear = false;
        allmoney = 0;
        bet_count = 0;
        cathecticTotalMsg.setText("共0注,0元");
        lotteryTotally.setTextColor(this.getResources().getColor(R.color.text_white));
    }

    private void clearList() {
        for (int i = 0; i < ballHm.size(); i++) {
            if (ballHm.get(i).getList().size() != 0) {
                for (int j = 0; j < ballHm.get(i).getList().size(); j++) {
                    ballHm.get(i).getList().get(j).setFocus(false);
                }
            } else if (ballHm.get(i).getLists().size() != 0) {
                for (int k = 0; k < ballHm.get(i).getLists().size(); k++) {
                    for (int l = 0; l < ballHm.get(i).getLists().get(k).getList().size(); l++) {
                        ballHm.get(i).getLists().get(k).getList().get(l).setFocus(false);
                    }
                }
            }
        }
        lotterAdapter.notifyDataSetChanged();
    }

    public void typeFocus(String key, int position) {
        int start = 0, lenth = 0;
        if (BaseUtil.equals(key, "all")) {
            start = 0;
            lenth = 0;
        } else if (BaseUtil.equals(key, "big")) {
            start = 5;
            lenth = 0;
        } else if (BaseUtil.equals(key, "small")) {
            start = 0;
            lenth = 6;
        } else if (BaseUtil.equals(key, "single")) {
            singleOrDounleFocus(position, 0);
            return;
        } else if (BaseUtil.equals(key, "double")) {
            singleOrDounleFocus(position, 1);
            return;
        } else if (BaseUtil.equals(key, "clear")) {
            clearFocus(position);
            return;
        }
        if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
            if (ballHm.get(tabindex).getLists().size() != 0) {
                for (int l = start; l < ballHm.get(tabindex).getLists().get(position).getList().size() - lenth; l++) {
                    ballHm.get(tabindex).getLists().get(position).getList().get(l).setFocus(true);
                }
            }
        } else {
            if (ballHm.get(position).getList().size() != 0) {
                for (int j = start; j < ballHm.get(position).getList().size() - lenth; j++) {
                    ballHm.get(position).getList().get(j).setFocus(true);
                }
            }
        }
        lotterAdapter.notifyDataSetChanged();
    }

    public void singleOrDounleFocus(int position, int num) {
        if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
            if (ballHm.get(tabindex).getLists().size() != 0) {
                for (int l = 0; l < ballHm.get(tabindex).getLists().get(position).getList().size(); l++) {
                    if (l % 2 == num) {
                        ballHm.get(tabindex).getLists().get(position).getList().get(l).setFocus(true);
                    }
                }
            }
        } else {
            if (ballHm.get(position).getList().size() != 0) {
                for (int j = 0; j < ballHm.get(position).getList().size(); j++) {
                    if (j % 2 == num) {
                        ballHm.get(position).getList().get(j).setFocus(true);
                    }
                }
            }
        }
        lotterAdapter.notifyDataSetChanged();
    }

    public void clearFocus(int position) {
        if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
            if (ballHm.get(tabindex).getLists().size() != 0) {
                for (int l = 0; l < ballHm.get(tabindex).getLists().get(position).getList().size(); l++) {
                    ballHm.get(tabindex).getLists().get(position).getList().get(l).setFocus(false);
                }
            }
        } else {
            if (ballHm.get(position).getList().size() != 0) {
                for (int j = 0; j < ballHm.get(position).getList().size(); j++) {
                    ballHm.get(position).getList().get(j).setFocus(false);
                }
            }
        }
        lotterAdapter.notifyDataSetChanged();
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
        lotterTimer.removeCallback();
    }
}

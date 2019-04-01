package com.sp.caitwo.ui.fragment.lottery;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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
import com.sp.caitwo.info.LhcAllWanFaInfo;
import com.sp.caitwo.info.RoomOpenInfo;
import com.sp.caitwo.ui.adapter.lottery.LotteryAdapter;
import com.sp.caitwo.ui.adapter.lottery.LotteryHistoryAdapter;
import com.sp.caitwo.ui.view.LotteryCountDown;
import com.sp.caitwo.ui.view.NestXRecycleView;
import com.sp.xwjlibrary.dialog.PopupDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.sp.util.Util.C;
import static com.sp.util.Util.getTwoDecimal;

public class LotteryFragment extends BaseLotteryFragment implements View.OnClickListener {

    private TextView tvIssue;
    private TextView tvIssueLottery;
    private TextView cathecticTotalMsg;
    private RelativeLayout cathecticSelectLayout2;
    private RadioGroup selectMoney;
    private LinearLayout llyLotteryNum;
    private LinearLayout lotteryHeadLayout;
    private LinearLayout headLayout1, headLayout2;
    private NestXRecycleView rvBet;
    private EditText lotteryXiazhu;
    private TextView maxmoney;
    private TextView lotteryMachineSelect;
    private TextView lotteryTotally;
    private TextView lotteryOk;
    private TextView tvWanfaIntroduce;
    private ArrayList<RoomOpenInfo.DataBean.OpenTimeBean> historylist = new ArrayList<>();
    private LotteryHistoryAdapter lotteryHistoryAdapter;
    private PopupDialog myDialog;
    private XRecyclerView xrvLotteryHistory;
    private LotteryAdapter lotterAdapter;
    private ArrayList<BiliListInfo.ListBeans> ballHm = new ArrayList<>();
    private int titleindex, tabindex;
    private View inflate;
    private TextView[] tvs;
    private int tvslength = 0;
    private boolean isPanseBallAnim = true;
    private LotteryCountDown lotterTimer;
    private Timer ballAnimTimer;
    private long curtime;
    private int mPosition;
    private String mGameNum;
    private int bet_count = 0, unit_type = 1;
    private double point = 2, allmoney = 0;
    private boolean isClear = false;
    private String tempNumbers = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.fragment_lottery, null, false);
            init();
            setLotteryAnim();
        }
        return inflate;
    }

    private void init() {
        tvIssue = inflate.findViewById(R.id.tv_issue);
        lotterTimer = inflate.findViewById(R.id.lottery_countdown);
        tvIssueLottery = inflate.findViewById(R.id.tv_issue_lottery);
        llyLotteryNum = inflate.findViewById(R.id.lly_lottery_num);
        lotteryHeadLayout = inflate.findViewById(R.id.lottery_head_layout);
        headLayout1 = inflate.findViewById(R.id.head_layout1);
        headLayout2 = inflate.findViewById(R.id.head_layout2);
        rvBet = inflate.findViewById(R.id.xrv_bet);
        lotteryXiazhu = inflate.findViewById(R.id.lottery_xiazhu);
        maxmoney = inflate.findViewById(R.id.maxmoney);
        lotteryMachineSelect = inflate.findViewById(R.id.lottery_machine_select);
        lotteryTotally = inflate.findViewById(R.id.lottery_totally);
        lotteryTotally.setOnClickListener(this);
        lotteryMachineSelect.setOnClickListener(this);
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
        rvBet.setLayoutManager(new LinearLayoutManager(LotteryFragment.this.getContext()));
        rvBet.addHeaderView(head);
        rvBet.setPullRefreshEnabled(false);
        lotteryOk.setOnClickListener(this);
        lotteryHeadLayout.setOnClickListener(this);
        lotterAdapter = new LotteryAdapter(LotteryFragment.this.getContext(), ballHm, titleindex, tabindex, game_type);
        rvBet.setAdapter(lotterAdapter);
        View lotteryHistoryView = View.inflate(getContext(), R.layout.ffc_lottery_history, null);
        xrvLotteryHistory = lotteryHistoryView.findViewById(R.id.xrv_lottery_history);
        myDialog = new PopupDialog(getContext(), lotteryHistoryView);
        xrvLotteryHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        xrvLotteryHistory.setPullRefreshEnabled(false);
        xrvLotteryHistory.setLoadingMoreEnabled(false);
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divide_line_horizontal_px1));
        xrvLotteryHistory.addItemDecoration(decor);
        setBadgeView(lotteryOk);
    }

    private void setLotteryAnim() {
        llyLotteryNum.removeAllViews();
        if (game_type == LotteryCons.CQSSC || game_type == LotteryCons.TJSSC) {
            tvslength = 5;
        } else if (game_type == LotteryCons.XYPK10 || game_type == LotteryCons.BJPK10) {
            tvslength = 10;
        }
        if (llyLotteryNum.getChildCount() == 0) {
            tvs = new TextView[tvslength];
            for (int i = 0; i < tvs.length; i++) {
                tvs[i] = new TextView(getContext());
                tvs[i].setBackgroundResource(R.drawable.icon_ball_red);
                tvs[i].setGravity(Gravity.CENTER);
                tvs[i].setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                if (game_type == LotteryCons.CQSSC || game_type == LotteryCons.TJSSC) {
                    tvs[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
                } else if (game_type == LotteryCons.XYPK10 || game_type == LotteryCons.BJPK10) {
                    tvs[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                }
                llyLotteryNum.addView(tvs[i]);
            }
        }
        ballAnimTimer = new Timer("BallAnim", false);
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
        }, 0, 150);
    }

    public void startLotteryAnim() {
        isPanseBallAnim = false;
    }

    public void stopLotteryAnim() {
        isPanseBallAnim = true;
    }


    public void getRoomOpenInfo(int game_type, long curtime) {
        this.curtime = curtime;
        InterfaceTask.getInstance().getRoomOpenInfo(game_type, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                RoomOpenInfo roomOpenInfo = (RoomOpenInfo) obj;
                setData(roomOpenInfo);
            }
        });
    }

    public void setData(RoomOpenInfo roomOpenInfo) {
        historylist.clear();
        historylist.addAll(roomOpenInfo.getData().getOpen_time());
        lotteryHistoryAdapter = new LotteryHistoryAdapter(getContext(), historylist, game_type);
        xrvLotteryHistory.setAdapter(lotteryHistoryAdapter);
        tvIssue.setText(String.format(getString(R.string.how_issue), roomOpenInfo.getData().getGame_num()));
        mGameNum = roomOpenInfo.getData().getGame_num();
        tvIssueLottery.setText(String.format(getString(R.string.issue_lottery), roomOpenInfo.getData().getFirst_result().getGame_num()));
        String[] numArray = null;
        String get_result = roomOpenInfo.getData().getFirst_result().getGet_result();
        if (!tempNumbers.equals(get_result)) {
            lotterTimer.setChange(true);
            stopLotteryAnim();
            tempNumbers = get_result;
        } else {
            lotterTimer.setChange(false);
        }
        get_result = get_result.replaceAll("\\+", ",");
        get_result = get_result.replaceAll("\\=", ",");
        numArray = get_result.split(",");
        int index = 0;
        for (int i = 0; i < tvs.length; i++) {
            tvs[i].setText(numArray[index]);
            index++;
        }
        setTime(roomOpenInfo.getData().getSeconds());
    }

    public void setTime(int secd) {
        if (secd >= 0) {
            lotterTimer.startCountDown(secd, 1000, 1, curtime, game_type);
        } else {
            lotterTimer.setChange(false);
            lotterTimer.startCountDown(secd, 1000, 3, curtime, game_type);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lottery_machine_select:
                selectOrClear();
                break;
            case R.id.lottery_totally:
                if (onMenuItemClickListener != null) {
                    if (bet_count == 0) {
                        ToastUtil.Toast(this.getContext(), "请先选择号码!");
                        return;
                    }
                    onMenuItemClickListener.onAddBetNum(lotterAdapter.getData(), mPosition, unit_type, badgeView, lotterAdapter, mGameNum, bet_count);
                    resetData();
                }
                break;
            case R.id.lottery_ok:
                if (onMenuItemClickListener != null) {
                    onMenuItemClickListener.onConfirmBet(mGameNum, badgeView, ballHm, null);
                }
                break;
            case R.id.lottery_head_layout:
                myDialog.show(v);
                break;
        }
    }

    public void getData(final int game_type, final int titleindex, int tabindex, List<BiliListInfo.DataBean> bililist) {
        this.game_type = game_type;
        this.titleindex = titleindex;
        this.tabindex = tabindex;
        ballHm.clear();
        if (game_type == LotteryCons.XYPK10 || game_type == LotteryCons.BJPK10) {
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f);
            headLayout1.setLayoutParams(lp1);
            headLayout2.setLayoutParams(lp2);
        } else {
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            headLayout1.setLayoutParams(lp1);
            headLayout2.setLayoutParams(lp2);
        }
        if (bililist.get(titleindex).getLists().size() != 0) {
            switch (game_type) {
                case LotteryCons.CQSSC:
                case LotteryCons.TJSSC:
                    if (titleindex == 0 || titleindex == 1) {
                        ballHm.addAll(bililist.get(titleindex).getLists());
                    } else if (titleindex == 4) {
                        BiliListInfo.ListBeans listBeans = new BiliListInfo.ListBeans();
                        List<BiliListInfo.ListBean> listBean = new ArrayList<>();
                        listBean.addAll(bililist.get(titleindex).getLists().get(tabindex).getList());
                        listBeans.getList().addAll(listBean);
                        listBeans.setWanfa_odds(bililist.get(titleindex).getMaxBili());
                        ballHm.add(listBeans);
                    }
                    break;
                case LotteryCons.BJPK10:
                case LotteryCons.XYPK10:
                    if (titleindex == 0 || titleindex == 1 || titleindex == 2) {
                        ballHm.addAll(bililist.get(titleindex).getLists());
                    } else if (titleindex == 4) {
                        ballHm.add(bililist.get(titleindex).getLists().get(tabindex));
                    }
                    break;
            }
        } else if (bililist.get(titleindex).getList().size() != 0) {
            BiliListInfo.ListBeans listBeans = new BiliListInfo.ListBeans();
            List<BiliListInfo.ListBean> listBean = new ArrayList<>();
            listBean.addAll(bililist.get(titleindex).getList());
            listBeans.getList().addAll(listBean);
            listBeans.setWanfa_id(bililist.get(0).getLists().get(0).getWanfa_id());
            ballHm.add(listBeans);
        }
        lotterAdapter.setGameType(game_type);
        lotterAdapter.setTitleindex(titleindex);
        resetData();
        lotterAdapter.setOnItemClickListener(new LotteryAdapter.OnItemClickListener() {

            @Override
            public void OnItemImageViewClick(View view, int position, int position_item) {
                mPosition = position;
                ballHm.get(position).getList().get(position_item).setFocus(!ballHm.get(position).getList().get(position_item).isFocus());
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
        for (int i = 0; i < ballHm.size(); i++) {
            Integer[] ints = null;
            if (ballHm.get(i).getName().equals("后三一码") || ballHm.get(i).getName().equals("后三二码") || ballHm.get(i).getName().equals("前三一码") || ballHm.get(i).getName().equals("前三二码")
                    || ballHm.get(i).getName().equals("前五三码") || ballHm.get(i).getName().equals("前五二码") || ballHm.get(i).getName().equals("后五三码") || ballHm.get(i).getName().equals("后五二码")) {
                ints = LotteryCons.randomCommon(0, ballHm.get(i).getList().size(), ballHm.get(i).getSomeNum());
            } else {
                ints = LotteryCons.randomCommon(0, ballHm.get(i).getList().size(), 1);
            }
            for (int j = 0; j < ints.length; j++) {
                ballHm.get(i).getList().get(ints[j]).setFocus(true);
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

    public void getBetCount(List<BiliListInfo.ListBeans> data) {
        int count = 0;
        int allcount = 0;
        int size = data.size();
        for (int i = 0; i < size; i++) {
            if (ballHm.get(i).getList().size() != 0) {
                List<BiliListInfo.ListBean> listBeanList = data.get(i).getList();
                for (BiliListInfo.ListBean listBean : listBeanList) {
                    if (listBean.isFocus()) {
                        count++;
                    }
                }
            } else if (ballHm.get(i).getLists().size() != 0) {
                List<BiliListInfo.ListBean2> listBean2 = data.get(i).getLists();
                for (BiliListInfo.ListBean2 bean2 : listBean2) {
                    for (BiliListInfo.ListBean listBean : bean2.getList()) {
                        if (listBean.isFocus()) {
                            count++;
                        }
                    }
                }
            }
            if (ballHm.get(i).getName().equals("后三一码") || ballHm.get(i).getName().equals("后三二码") || ballHm.get(i).getName().equals("前三一码") || ballHm.get(i).getName().equals("前三二码")
                    || ballHm.get(i).getName().equals("前五三码") || ballHm.get(i).getName().equals("前五二码") || ballHm.get(i).getName().equals("后五三码") || ballHm.get(i).getName().equals("后五二码")) {
                count = C(data.get(i).getSomeNum(), count);
                allcount = count;
            } else {
                allcount += count;
            }
            count = 0;
        }
        bet_count = allcount;
    }

    public void clearList() {
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

    public void resetData() {
        clearList();
        lotteryMachineSelect.setText("机选");
        isClear = false;
        allmoney = 0;
        bet_count = 0;
        cathecticTotalMsg.setText("共0注,0元");
        lotteryTotally.setTextColor(this.getResources().getColor(R.color.text_white));
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
            lenth = 5;
        } else if (BaseUtil.equals(key, "single")) {
            if (game_type == LotteryCons.XYPK10 || game_type == LotteryCons.BJPK10) {
                singleOrDounleFocus(position, 0);
            } else {
                singleOrDounleFocus(position, 1);
            }
            return;
        } else if (BaseUtil.equals(key, "double")) {
            if (game_type == LotteryCons.XYPK10 || game_type == LotteryCons.BJPK10) {
                singleOrDounleFocus(position, 1);
            } else {
                singleOrDounleFocus(position, 0);
            }
            return;
        } else if (BaseUtil.equals(key, "clear")) {
            clearFocus(position);
            return;
        }
        if (ballHm.get(position).getList().size() != 0) {
            for (int j = start; j < ballHm.get(position).getList().size() - lenth; j++) {
                ballHm.get(position).getList().get(j).setFocus(true);
            }
        } else if (ballHm.get(position).getLists().size() != 0) {
            for (int k = 0; k < ballHm.get(position).getLists().size(); k++) {
                for (int l = start; l < ballHm.get(position).getLists().get(k).getList().size() - lenth; l++) {
                    ballHm.get(position).getLists().get(k).getList().get(l).setFocus(true);
                }
            }
        }
        lotterAdapter.notifyDataSetChanged();
    }


    public void singleOrDounleFocus(int position, int num) {
        if (ballHm.get(position).getList().size() != 0) {
            for (int j = 0; j < ballHm.get(position).getList().size(); j++) {
                if (j % 2 == num) {
                    ballHm.get(position).getList().get(j).setFocus(true);
                }
            }
        } else if (ballHm.get(position).getLists().size() != 0) {
            for (int k = 0; k < ballHm.get(position).getLists().size(); k++) {
                for (int l = 0; l < ballHm.get(position).getLists().get(k).getList().size(); l++) {
                    if (l % 2 == num) {
                        ballHm.get(position).getLists().get(k).getList().get(l).setFocus(true);
                    }
                }
            }
        }
        lotterAdapter.notifyDataSetChanged();
    }

    public void clearFocus(int position) {
        if (ballHm.get(position).getList().size() != 0) {
            for (int j = 0; j < ballHm.get(position).getList().size(); j++) {
                ballHm.get(position).getList().get(j).setFocus(false);
            }
        } else if (ballHm.get(position).getLists().size() != 0) {
            for (int k = 0; k < ballHm.get(position).getLists().size(); k++) {
                for (int l = 0; l < ballHm.get(position).getLists().get(k).getList().size(); l++) {
                    ballHm.get(position).getLists().get(k).getList().get(l).setFocus(false);
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

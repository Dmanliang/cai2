package com.sp.caitwo.ui.fragment.lottery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.sp.caitwo.base.SeBoCons;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.info.RoomOpenInfo;
import com.sp.caitwo.ui.adapter.lottery.BetKuai3Adapter;
import com.sp.caitwo.ui.adapter.lottery.WanFa28LotteryHistoryAdapter;
import com.sp.caitwo.ui.view.LotteryCountDown;
import com.sp.xwjlibrary.dialog.PopupDialog;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by Administrator on 2018/11/5.
 */


public class WanFa28Fragment extends BaseLotteryFragment implements View.OnClickListener {

    public static final String CLEAR_LIST = "clear_list";
    private RelativeLayout cathecticSelectLayout2;
    private TextView cathecticTotalMsg, lotteryMachineSelect, lotteryTotally, lotteryOk;
    private RadioGroup selectMoney;
    private TextView tvIssue;
    private TextView tvIssueLottery;
    private TextView tvWanfaIntroduce;
    private XRecyclerView xrvBet;
    private List<BiliListInfo.ListBeans> ballHm = new ArrayList<>();
    private List<RoomOpenInfo.DataBean.OpenTimeBean> historyList = new ArrayList<>();
    private WanFa28LotteryHistoryAdapter lotteryHistoryAdapter;
    private BetKuai3Adapter betKuai3Adapter;
    private PopupDialog myDialog;
    private XRecyclerView xrvLotteryHistory;
    private int game_type;
    private int titleindex;
    private LinearLayout llyLotteryNum;
    private TextView[] tvs = new TextView[7];
    private boolean isPanseBallAnim = true;
    private View inflate;
    private LotteryCountDown lotterTimer;
    private Timer ballAnimTimer;
    private long curtime;
    private boolean isClear = false;
    private String choice_no;
    private double allPoint, allmoney = 0, point = 2;
    private int bet_count = 0, unit_type = 1;
    public static final ArrayList<HashMap<String, Object>> subList = new ArrayList<>();
    private String tempNumbers = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.fragment_kuai3, null, false);
            init();
        }
        return inflate;
    }

    private void init() {
        initUI();
        setRecyclerView();
        setLotteryAnim();
        setBadgeView(lotteryOk);
    }

    private void setRecyclerView() {
        betKuai3Adapter = new BetKuai3Adapter(getContext(), ballHm);
        xrvBet.setLayoutManager(new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false));
        xrvBet.setAdapter(betKuai3Adapter);
        View head = View.inflate(getContext(), R.layout.fragment_bet_class_head, null);
        xrvBet.addHeaderView(head);
        xrvBet.setPullRefreshEnabled(false);
        xrvBet.setLoadingMoreEnabled(false);
        tvWanfaIntroduce = head.findViewById(R.id.tv_wanfa_introduce);
        xrvLotteryHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lotteryHistoryAdapter = new WanFa28LotteryHistoryAdapter(getContext(), historyList);
        xrvLotteryHistory.setAdapter(lotteryHistoryAdapter);
        xrvLotteryHistory.setPullRefreshEnabled(false);
        xrvLotteryHistory.setLoadingMoreEnabled(false);
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divide_line_horizontal_px1));
        xrvLotteryHistory.addItemDecoration(decor);
        betKuai3Adapter.setKuai3ClickListener(new BetKuai3Adapter.Kuai3ItemClickListener() {
            @Override
            public void ItemClickListener(View view, int position) {
                ballHm.get(0).getList().get(position).setFocus(!ballHm.get(0).getList().get(position).isFocus());
                betKuai3Adapter.notifyDatasChang();
                ItemClick();
            }
        });
    }

    private void initUI() {
        xrvBet = inflate.findViewById(R.id.xrv_bet);
        tvIssue = inflate.findViewById(R.id.tv_issue);
        tvIssueLottery = inflate.findViewById(R.id.tv_issue_lottery);
        llyLotteryNum = inflate.findViewById(R.id.lly_lottery_num);
        lotterTimer = inflate.findViewById(R.id.lottery_countdown);
        cathecticSelectLayout2 = inflate.findViewById(R.id.cathectic_select_layout2);
        cathecticTotalMsg = inflate.findViewById(R.id.cathectic_total_msg);
        selectMoney = inflate.findViewById(R.id.select_money);
        lotteryOk = inflate.findViewById(R.id.lottery_ok);
        lotteryMachineSelect = inflate.findViewById(R.id.lottery_machine_select);
        lotteryTotally = inflate.findViewById(R.id.lottery_totally);
        cathecticSelectLayout2.setVisibility(View.VISIBLE);
        lotteryMachineSelect.setOnClickListener(this);
        lotteryTotally.setOnClickListener(this);
        lotteryOk.setOnClickListener(this);
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
        inflate.findViewById(R.id.lly_lottery_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show(view);
            }
        });
        View lotteryHistoryView = View.inflate(getContext(), R.layout.wanfa28_lottery_history, null);
        xrvLotteryHistory = lotteryHistoryView.findViewById(R.id.xrv_lottery_history);
        myDialog = new PopupDialog(getContext(), lotteryHistoryView);
    }

    private void setLotteryAnim() {
        llyLotteryNum.removeAllViews();
        if (llyLotteryNum.getChildCount() == 0) {
            for (int i = 0; i < tvs.length; i++) {
                tvs[i] = new TextView(getContext());
                if (i == 1 || i == 3 || i == 5) {
                    if (i != 5) tvs[i].setText("+");
                    else tvs[i].setText("=");
                    tvs[i].setTextColor(ContextCompat.getColor(getContext(), R.color.color_333));
                    tvs[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                } else if (i == 6) {
                    tvs[i].setGravity(Gravity.CENTER);
                    tvs[i].setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                    tvs[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
                } else {
                    tvs[i].setBackgroundResource(R.drawable.icon_ball_red);
                    tvs[i].setGravity(Gravity.CENTER);
                    tvs[i].setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                    tvs[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
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
                            tvs[0].setText("0" + count);
                            tvs[2].setText("0" + count);
                            tvs[4].setText("0" + count);
                            tvs[6].setText((count * 3 < 10 ? "0" : "") + (count * 3));
                        }
                    });
                count++;
                if (count == 10) count = 0;
            }
        }, 0, 180);
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
        historyList.clear();
        historyList.addAll(roomOpenInfo.getData().getOpen_time());
        lotteryHistoryAdapter.notifyDataSetChanged();
        choice_no = roomOpenInfo.getData().getGame_num();
        tvIssue.setText(String.format(getString(R.string.how_issue), roomOpenInfo.getData().getGame_num()));
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
            if (i % 2 == 0) {
                tvs[i].setText(numArray[index]);
                index++;
            }
            if (i == tvs.length - 1) {
                Integer num = Integer.valueOf(numArray[numArray.length - 1]);
                if (0 == num || 13 == num || 14 == num || 27 == num) {
                    tvs[i].setBackgroundResource(R.drawable.icon_ball_gray);
                } else if (0 == num % 3) {
                    tvs[i].setBackgroundResource(R.drawable.icon_ball_red);
                } else if (1 == num % 3) {
                    tvs[i].setBackgroundResource(R.drawable.icon_ball_green);
                } else {
                    tvs[i].setBackgroundResource(R.drawable.icon_ball_blue);
                }
            }
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

    public void getData(int game_type, int titleindex, List<BiliListInfo.DataBean> bililist, double allPoint) {
        this.game_type = game_type;
        this.titleindex = titleindex;
        this.allPoint = allPoint;
        ballHm.clear();
        BiliListInfo.ListBeans listBeans = new BiliListInfo.ListBeans();
        List<BiliListInfo.ListBean> listBean = new ArrayList<>();
        listBean.addAll(bililist.get(titleindex).getList());
        listBeans.getList().addAll(listBean);
        listBeans.setWanfa_id(bililist.get(titleindex).getWanfa_id());
        listBeans.setWanfa_dec(bililist.get(titleindex).getWanfa_dec());
        listBeans.setName(bililist.get(titleindex).getName());
        listBeans.setMax_money(bililist.get(titleindex).getMaxBili());
        ballHm.add(listBeans);
        resetData();
        LotteryCons.setWanfaDec(tvWanfaIntroduce, bililist.get(this.titleindex).getWanfa_dec(), bililist.get(this.titleindex).getMaxBili());
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
        Integer[] ints = LotteryCons.randomCommon(0, ballHm.get(0).getList().size(), 1);
        for (int j = 0; j < ints.length; j++) {
            ballHm.get(0).getList().get(ints[j]).setFocus(true);
        }
        getBetCount(ballHm);
        allmoney = point * bet_count;
        cathecticTotalMsg.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
        lotteryTotally.setTextColor(this.getResources().getColor(R.color.big_double_yellow));
        lotteryMachineSelect.setText("清空");
        isClear = true;
    }

    public void getBetCount(List<BiliListInfo.ListBeans> data) {
        int size = ballHm.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            List<BiliListInfo.ListBean> listBeanList = data.get(i).getList();
            for (BiliListInfo.ListBean listBean : listBeanList) {
                if (listBean.isFocus()) {
                    count++;
                }
            }
        }
        bet_count = count;
    }


    public void ItemClick() {
        getBetCount(ballHm);
        if (bet_count != 0) {
            lotteryMachineSelect.setText("清空");
            isClear = true;
            allmoney = point * bet_count;
            cathecticTotalMsg.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
            lotteryTotally.setTextColor(this.getResources().getColor(R.color.big_double_yellow));
        } else {
            resetData();
        }
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

    public void clearList() {
        for (int i = 0; i < ballHm.size(); i++) {
            if (ballHm.get(i).getList().size() != 0) {
                for (int j = 0; j < ballHm.get(i).getList().size(); j++) {
                    ballHm.get(i).getList().get(j).setFocus(false);
                }
            }
        }
        betKuai3Adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lottery_ok:
                if (onMenuItemClickListener != null) {
                    onMenuItemClickListener.onConfirmBet(choice_no, badgeView, ballHm, null);
                }
                break;
            case R.id.lottery_machine_select:
                selectOrClear();
                break;
            case R.id.lottery_totally:
                if (onMenuItemClickListener != null) {
                    if (bet_count == 0) {
                        ToastUtil.Toast(this.getContext(), "请先选择号码!");
                        return;
                    }
                    onMenuItemClickListener.onAddBetNum(ballHm, mPosition, unit_type, badgeView, betKuai3Adapter, choice_no, bet_count);
                    resetData();
                }
                break;
        }
    }

    public static ArrayList<HashMap<String, Object>> getSubList() {
        return subList;
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

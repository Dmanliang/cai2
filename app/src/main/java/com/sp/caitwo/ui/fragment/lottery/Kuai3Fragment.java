package com.sp.caitwo.ui.fragment.lottery;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;

import com.jauker.widget.BadgeView;
import com.sp.caitwo.base.BaseLotteryFragment;
import com.sp.caitwo.ui.view.NestXRecycleView;
import com.sp.util.SoftKeyBoardListener;
import com.sp.xwjlibrary.util.BaseUtil;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.info.RoomOpenInfo;
import com.sp.caitwo.ui.adapter.lottery.BetKuai3Adapter;
import com.sp.caitwo.ui.adapter.lottery.Kuai3HistoryAdapter;
import com.sp.caitwo.ui.view.LotteryCountDown;
import com.sp.caitwo.ui.view.dialog.CathecticDialog;
import com.sp.xwjlibrary.dialog.PopupDialog;
import com.sp.xwjlibrary.util.AnimationUtil;
import com.sp.xwjlibrary.util.HolderUtil;
import com.sp.xwjlibrary.util.JSONUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.UPDATE_INFO;
import static com.sp.caitwo.ui.activity.lottery.LotteryCathecticActivity.MAX_MONEY;
import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by Administrator on 2018/11/5.
 */

public class Kuai3Fragment extends BaseLotteryFragment implements View.OnClickListener {

    private TextView tvIssue;
    private TextView tvIssueLottery;
    private TextView lottery_machine_select, lottery_totally, lottery_ok, maxmoney, tvWanfaIntroduce;
    private EditText lotteryXiazhu;
    private LinearLayout layout_lottery_bottom;
    private RelativeLayout cathectic_select_layout1;
    private LinearLayout llyLotteryHistory;
    private NestXRecycleView xrvBet;
    private List<BiliListInfo.ListBeans> ballHm = new ArrayList<>();
    private List<RoomOpenInfo.DataBean.OpenTimeBean> historyList = new ArrayList<>();
    private BetKuai3Adapter betKuai3Adapter;
    private PopupDialog myDialog;
    private XRecyclerView xrvLotteryHistory;
    private LotteryCountDown lotterTimer;
    private int game_type;
    private int titleindex;
    private LinearLayout llyLotteryNum;
    private ImageView[] ivDices = new ImageView[3];
    private AnimationDrawable[] adDices = new AnimationDrawable[3];
    private int[] dices = new int[]{R.drawable.the_dice_1, R.drawable.the_dice_2,
            R.drawable.the_dice_3, R.drawable.the_dice_4,
            R.drawable.the_dice_5, R.drawable.the_dice_6};
    private View inflate;
    private long curtime;
    private boolean isClear = false;
    private String choice_no, point;
    private double allPoint, allmoney, maxbili;
    private CathecticDialog cathecticDialog;
    private StringBuffer buffer;
    private String wanfa_type, lotteryTitle, betStr;
    private int bet_count = 0;
    private int money;
    private Kuai3HistoryAdapter kuai3HistoryAdapter;
    private String tempNumbers = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.fragment_kuai3, null, false);
            init();
            setLotteryAnim();
        }
        return inflate;
    }

    private void init() {
        initUI();
    }

    private void initUI() {
        xrvBet = inflate.findViewById(R.id.xrv_bet);
        tvIssue = inflate.findViewById(R.id.tv_issue);
        tvIssueLottery = inflate.findViewById(R.id.tv_issue_lottery);
        layout_lottery_bottom = inflate.findViewById(R.id.layout_lottery_bottom);
        llyLotteryNum = inflate.findViewById(R.id.lly_lottery_num);
        lotterTimer = inflate.findViewById(R.id.lottery_countdown);
        lottery_machine_select = inflate.findViewById(R.id.lottery_machine_select);
        lottery_totally = inflate.findViewById(R.id.lottery_totally);
        lottery_ok = inflate.findViewById(R.id.lottery_ok);
        maxmoney = inflate.findViewById(R.id.maxmoney);
        lotteryXiazhu = inflate.findViewById(R.id.lottery_xiazhu);
        cathectic_select_layout1 = inflate.findViewById(R.id.cathectic_select_layout1);
        llyLotteryHistory = inflate.findViewById(R.id.lly_lottery_history);
        lottery_machine_select.setOnClickListener(this);
        lottery_ok.setOnClickListener(this);
        llyLotteryHistory.setOnClickListener(this);
        View lotteryHistoryView = View.inflate(getContext(), R.layout.kuai3_lottery_history, null);
        xrvLotteryHistory = lotteryHistoryView.findViewById(R.id.xrv_lottery_history);
        myDialog = new PopupDialog(getContext(), lotteryHistoryView);
        setRecyclerView();
        cathecticDialog = new CathecticDialog(Kuai3Fragment.this.getContext());
        cathecticDialog.cathectic_dialog_ok.setOnClickListener(this);
        cathectic_select_layout1.setVisibility(View.VISIBLE);
        lottery_totally.setText("共0注,0元");
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
                    money = Integer.parseInt(editable.toString());
                    double maxAllmoney = money * maxbili;
                    allmoney = bet_count * money;
                    maxmoney.setText("最高可中" + getTwoDecimal(maxAllmoney) + "元");
                    lottery_totally.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
                }
            }
        });
        SoftKeyBoardListener.setListener(this.getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                HolderUtil.setMargin(layout_lottery_bottom, 0, 0, 0, height);
            }

            @Override
            public void keyBoardHide(int height) {
                HolderUtil.setMargin(layout_lottery_bottom, 0, 0, 0, 0);
            }
        });
    }

    public void getRoomPointAdd(String betStr) {
        InterfaceTask.getInstance().getRoomPointAdd(Kuai3Fragment.this.getContext(), choice_no, betStr, game_type, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
                resetData();
                cathecticDialog.dismiss();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                resetData();
                cathecticDialog.dismiss();
                EventBus.getDefault().post(UPDATE_INFO);
            }
        });
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

    private void setRecyclerView() {
        betKuai3Adapter = new BetKuai3Adapter(getContext(), ballHm);
        xrvBet.setLayoutManager(new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false));
        xrvBet.setAdapter(betKuai3Adapter);
        betKuai3Adapter.setKuai3ClickListener(new BetKuai3Adapter.Kuai3ItemClickListener() {
            @Override
            public void ItemClickListener(View view, int position) {
                ballHm.get(0).getList().get(position).setFocus(!ballHm.get(0).getList().get(position).isFocus());
                betKuai3Adapter.notifyDatasChang();
                ItemClick();
            }
        });
        View head = View.inflate(getContext(), R.layout.fragment_bet_class_head, null);
        tvWanfaIntroduce = head.findViewById(R.id.tv_wanfa_introduce);
        xrvBet.addHeaderView(head);
        xrvBet.setPullRefreshEnabled(false);
        xrvBet.setLoadingMoreEnabled(false);
        xrvLotteryHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        kuai3HistoryAdapter = new Kuai3HistoryAdapter(getContext(), historyList);
        xrvLotteryHistory.setAdapter(kuai3HistoryAdapter);
        xrvLotteryHistory.setPullRefreshEnabled(false);
        xrvLotteryHistory.setLoadingMoreEnabled(false);
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divide_line_horizontal_px1));
        xrvLotteryHistory.addItemDecoration(decor);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lottery_machine_select:
                selectOrClear();
                break;
            case R.id.lottery_ok:
                if (BaseUtil.isEmpty(lotteryXiazhu.getText().toString())) {
                    ToastUtil.Toast(this.getContext(), "请输入金额!");
                    return;
                }
                if (money == 0) {
                    ToastUtil.Toast(this.getContext(), "输入金额不能为0!");
                    return;
                }
                if (bet_count == 0) {
                    ToastUtil.Toast(this.getContext(), "至少选择一个号码!");
                    return;
                }
                if (allPoint < allmoney) {
                    ToastUtil.Toast(this.getContext(), "余额不足!");
                    return;
                }
                subLottery();
                break;
            case R.id.lly_lottery_history:
                myDialog.show(view);
                break;
            case R.id.cathectic_dialog_ok:
                getRoomPointAdd(betStr);
                break;
        }
    }

    public void setData(RoomOpenInfo roomOpenInfo) {
        historyList.clear();
        historyList.addAll(roomOpenInfo.getData().getOpen_time());
        kuai3HistoryAdapter.notifyDataSetChanged();
        choice_no = roomOpenInfo.getData().getGame_num();
        tvIssue.setText(String.format(getString(R.string.how_issue), choice_no));
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
        for (int i = 0; i < ivDices.length; i++) {
            ivDices[i].setImageDrawable(this.getResources().getDrawable(dices[Integer.parseInt(numArray[i]) - 1]));
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

    public void getData(int game_type, int titleindex, List<BiliListInfo.DataBean> bililist, String lotteryTitle, String wanfa_type, double allPoint) {
        this.game_type = game_type;
        this.titleindex = titleindex;
        this.wanfa_type = wanfa_type;
        this.lotteryTitle = lotteryTitle;
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
        maxbili = bililist.get(titleindex).getMaxBili();
        resetData();
        LotteryCons.setWanfaDec(tvWanfaIntroduce, bililist.get(titleindex).getWanfa_dec(), bililist.get(titleindex).getMaxBili());
    }

    private void setLotteryAnim() {
        if (llyLotteryNum.getChildCount() == 0) {
            for (int i = 0; i < ivDices.length; i++) {
                ivDices[i] = new ImageView(getContext());
                if (i == 1) ivDices[i].setPadding(20, 0, 20, 0);
                llyLotteryNum.addView(ivDices[i]);
                adDices[i] = AnimationUtil.anim(getContext(), dices, 100);
                ivDices[i].setImageDrawable(adDices[i]);
            }
        }
    }

    public void startLotteryAnim() {
        for (int i = 0; i < adDices.length; i++) {
            adDices[i].start();
        }
    }

    public void stopLotteryAnim() {
        for (int i = 0; i < adDices.length; i++) {
            adDices[i].stop();
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
        Integer[] ints = LotteryCons.randomCommon(0, ballHm.get(0).getList().size(), 1);
        for (int j = 0; j < ints.length; j++) {
            ballHm.get(0).getList().get(ints[j]).setFocus(true);
        }
        getBetCount(ballHm);
        lottery_machine_select.setText("清空");
        isClear = true;
        if (!BaseUtil.isEmpty(lotteryXiazhu.getText().toString())) {
            point = lotteryXiazhu.getText().toString();
            allmoney = Double.parseDouble(point) * bet_count;
            lottery_totally.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
            double maxAllmoney = Double.parseDouble(point) * maxbili;
            maxmoney.setText("最高可中" + getTwoDecimal(maxAllmoney) + "元");
        } else {
            point = "0";
            allmoney = Double.parseDouble(point) * bet_count;
            lottery_totally.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
            double maxAllmoney = Double.parseDouble(point) * maxbili;
            maxmoney.setText("最高可中" + getTwoDecimal(maxAllmoney) + "元");
        }
    }

    public void ItemClick() {
        getBetCount(ballHm);
        if (bet_count != 0) {
            lottery_machine_select.setText("清空");
            isClear = true;
            if (!BaseUtil.isEmpty(lotteryXiazhu.getText().toString())) {
                point = lotteryXiazhu.getText().toString();
                allmoney = Double.parseDouble(point) * bet_count;
                lottery_totally.setText("共" + bet_count + "注," + getTwoDecimal(allmoney) + "元");
                double maxAllmoney = Double.parseDouble(point) * maxbili;
                maxmoney.setText("最高可中" + getTwoDecimal(maxAllmoney) + "元");
            }
        } else {
            resetData();
        }
    }

    public void resetData() {
        clearList();
        lottery_machine_select.setText("机选");
        isClear = false;
        allmoney = 0;
        bet_count = 0;
        lotteryXiazhu.setText("2");
        lottery_totally.setText("共0注,0元");
        maxmoney.setText("最高可中0元");
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

    public void subLottery() {
        point = lotteryXiazhu.getText().toString();
        ArrayList<HashMap<String, Object>> subList = new ArrayList<>();
        buffer = new StringBuffer();
        if (BaseUtil.isEmpty(point)) {
            ToastUtil.Toast(Kuai3Fragment.this.getContext(), "请选择下注金额!");
            return;
        }
        if (bet_count == 0) {
            ToastUtil.Toast(Kuai3Fragment.this.getContext(), "至少选择一个号码!");
            return;
        }
        if (Double.parseDouble(point) > allPoint) {
            ToastUtil.Toast(Kuai3Fragment.this.getContext(), "余额不足!");
            return;
        }
        if (allmoney > MAX_MONEY) {
            ToastUtil.Toast(Kuai3Fragment.this.getContext(), "下注金额不得超过200000!");
            return;
        }
        allmoney = 0;
        allmoney = Double.parseDouble(point) * bet_count;
        for (int i = 0; i < ballHm.size(); i++) {
            List<BiliListInfo.ListBean> listBeanList = ballHm.get(i).getList();
            for (BiliListInfo.ListBean listBean : listBeanList) {
                if (listBean.isFocus()) {
                    HashMap<String, Object> hashMaps = new HashMap<>();
                    hashMaps.put("bili_id", listBean.getId());
                    hashMaps.put("point", point);
                    hashMaps.put("bet_count", 1);
                    hashMaps.put("multiple_count", 1);
                    hashMaps.put("unit_type", 1);
                    hashMaps.put("wanfa_type", wanfa_type);
                    subList.add(hashMaps);
                    buffer.append(listBean.getBili_name());
                    buffer.append(";");
                }
            }
        }
        if (allmoney > allPoint) {
            ToastUtil.Toast(Kuai3Fragment.this.getContext(), "余额不足!");
            return;
        }
        betStr = JSONUtil.createJsonArray(subList).toString();
        cathecticDialog.setContext(lotteryTitle, choice_no, allmoney, buffer.toString());
        cathecticDialog.show();
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

package com.sp.caitwo.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.bean.CathecticInfo;
import com.sp.caitwo.bean.HomeWanFaBean;
import com.sp.caitwo.ui.activity.lottery.AllWanFaActivity;
import com.sp.caitwo.ui.activity.lottery.LongDragonActivity;
import com.sp.caitwo.ui.adapter.lottery.CathecticAdpater;
import com.sp.caitwo.ui.fragment.lottery.CathecticRecordFragment;
import com.sp.caitwo.ui.view.dialog.CathecticDialog;
import com.sp.caitwo.ui.view.refresh.PullLoadMoreRecyclerView;
import com.sp.util.Util;
import com.sp.util.ViewUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;


/**
 * 长龙页面
 */

public class CathecticFragment extends Fragment implements View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    public static String REFRESH_DATA = "refresh_data";
    private int MIN_MARK = 1;
    private int MAX_MARK = 100000;
    private PullLoadMoreRecyclerView cathectic_recycler;
    private TextView maxmoney, total_num_money, cathectic_ok;
    private EditText cathectic_xiazhu;
    private CathecticAdpater cathecticAdpater;
    private List<CathecticInfo.DataBeanX.DataBean> list = new ArrayList<>();
    private boolean isRefresh;
    private double allmaxmoney = 0;
    private int money = 1;
    private boolean odds;
    private int beinums = 1;
    private long server_time;
    private CathecticDialog cathecticDialog;
    private int positions = 0, indexs = 0;
    private String dragonBet = "";
    private boolean isclick = false;
    private double mypoint;
    private View inflate;
    private long curtime;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.fragment_cathectic, null, false);
            init();
        }
        return inflate;
    }

    protected void init() {
        EventBus.getDefault().register(this);
        curtime = System.currentTimeMillis();
        if (App.userInfoBean != null && App.userInfoBean.getData() != null) {
            mypoint = App.userInfoBean.getData().getPoint();
        }
        cathectic_recycler = inflate.findViewById(R.id.cathectic);
        cathectic_recycler.setLinearLayout();
        cathectic_recycler.setOnPullLoadMoreListener(this);
        cathectic_recycler.setRefreshing(true);
        cathectic_recycler.setColorSchemeResources(R.color.light_red);
        maxmoney = inflate.findViewById(R.id.maxmoney);
        total_num_money = inflate.findViewById(R.id.total_num_money);
        cathectic_ok = inflate.findViewById(R.id.cathectic_ok);
        cathectic_xiazhu = inflate.findViewById(R.id.cathectic_xiazhu);
        cathecticDialog = new CathecticDialog(CathecticFragment.this.getContext());
        cathecticDialog.cathectic_dialog_clear.setOnClickListener(this);
        cathecticDialog.cathectic_dialog_ok.setOnClickListener(this);
        cathecticAdpater = new CathecticAdpater(getContext(), list);
        cathectic_recycler.setAdapter(cathecticAdpater);
        cathecticAdpater.setOnClickListener(new CathecticAdpater.ButtonOnClickListener() {
            @Override
            public void setButtonOnclick(RecyclerView.ViewHolder viewHolder, int position, int index) {
                if (index == 0) {
                    if (viewHolder instanceof CathecticAdpater.CathecticViewHolder) {
                        if (!list.get(position).isFocus1()) {
                            for (int i = 0; i < list.size(); i++) {
                                list.get(i).setFocus1(false);
                                list.get(i).setFocus2(false);
                            }
                            list.get(position).setFocus1(true);
                            positions = position;
                            indexs = index;
                            isclick = true;
                            getCathectic(position, index);
                        } else {
                            list.get(position).setFocus1(false);
                            isclick = false;
                            resetCathectic();
                        }
                        cathecticAdpater.notifyDataSetChanged();
                    }
                } else {
                    if (viewHolder instanceof CathecticAdpater.CathecticViewHolder) {
                        if (!list.get(position).isFocus2()) {
                            for (int i = 0; i < list.size(); i++) {
                                list.get(i).setFocus1(false);
                                list.get(i).setFocus2(false);
                            }
                            list.get(position).setFocus2(true);
                            positions = position;
                            indexs = index;
                            isclick = true;
                            getCathectic(position, index);
                        } else {
                            list.get(position).setFocus2(false);
                            isclick = false;
                            resetCathectic();
                        }
                        cathecticAdpater.notifyDataSetChanged();
                    }
                }
            }
        });
        cathectic_xiazhu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    int num = Integer.parseInt(s.toString());
                    if (isclick) {
                        if (num <= MAX_MARK && num >= MIN_MARK) {
                            if (num <= mypoint) {
                                getCathectic(positions, indexs);
                            } else {
                                ToastUtil.Toast(CathecticFragment.this.getContext(), "下注金额不够!");
                                cathectic_xiazhu.setText("1");
                            }

                        } else {
                            ToastUtil.Toast(CathecticFragment.this.getContext(), "最高上限10万倍!");
                            cathectic_xiazhu.setText("100000");
                        }
                    } else {
                        if (num > MAX_MARK || num < MIN_MARK) {
                            ToastUtil.Toast(CathecticFragment.this.getContext(), "最高上限10万倍!");
                            cathectic_xiazhu.setText("100000");
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cathectic_ok.setOnClickListener(this);
        requestCathecticList();
    }

    public void requestCathecticList() {

        InterfaceTask.getInstance().getLongDragonList(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
                cathectic_recycler.setPullLoadMoreCompleted();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                cathectic_recycler.setPullLoadMoreCompleted();
                CathecticInfo cathecticInfo = (CathecticInfo) obj;
                server_time = cathecticInfo.getData().getServerTime();
                if (isRefresh) {
                    list.clear();
                } else {
                    if (cathecticInfo.getData().getData().size() == 0) {
                        cathectic_recycler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.Toast(CathecticFragment.this.getContext(), "没有更多数据!");
                            }
                        }, 500);
                        return;
                    }
                }
                isRefresh = false;
                list.addAll(cathecticInfo.getData().getData());
                server_time = cathecticInfo.getData().getServerTime();
                cathecticAdpater.setCurrTime();
                cathecticAdpater.notifyDataSetChanged();
            }
        });

    }

    public void requestCathectic() {
        int multiple_count = 1;
        int bet_count = 1;
        int unit_type = 1;//1元 2角
        int bili_id = list.get(positions).getBili_list().get(indexs).getId();

        String choice_no = list.get(positions).getGame_num() + "";//
        String wanfa_type = "大小单双";//
        String game_type = list.get(positions).getGame_type() + "";//

        InterfaceTask.getInstance().getdragonAdd(getContext(), wanfa_type, choice_no,
                multiple_count, bet_count, dragonBet, unit_type,
                money, bili_id, game_type, new InterfaceTask.OnInterfaceTask() {
                    @Override
                    public void onError() {
                        super.onError();
                        cathecticDialog.dismiss();
                    }

                    @Override
                    public void onSuccess(Object obj) {
                        super.onSuccess(obj);
                        ToastUtil.Toast(getContext(), "投注成功");
                        cathecticDialog.dismiss();
                        EventBus.getDefault().post(LongDragonActivity.UPDATE_INFO);

                    }
                });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cathectic_ok:
                if (mypoint <= 0 || mypoint < money) {
                    ToastUtil.Toast(getContext(), "余额不足，请充值！");
                    return;
                }
                if (cathectic_xiazhu.getText().toString().equals("")) {
                    ToastUtil.Toast(getContext(), "请输入下注倍数!");
                } else {
                    if (isclick) {
                        if (mypoint >= money) {
                            int categoryTpey = LotteryCons.getCategory_type(list.get(positions).getGame_type());
                            dragonBet = list.get(positions).getBili_list().get(indexs).getBili_name();
                            if (categoryTpey == 1) {
                                dragonBet = list.get(positions).getBili_list().get(indexs).getBili_name();
                            } else if (categoryTpey == 3) {
                                String s = "";
                                for (int i = 0; i < 10; i++) {
                                    if (i == list.get(positions).getPosition() - 1) {
                                        s += list.get(positions).getBili_list().get(indexs).getBili_name();
                                    } else {
                                        s += "-";
                                    }
                                    if (i != 9) {
                                        s += ",";
                                    }
                                }
                                dragonBet = s;
                            } else if (categoryTpey == 2) {
                                String s = "";
                                for (int i = 0; i < 5; i++) {
                                    if (i == list.get(positions).getPosition() - 1) {
                                        s += list.get(positions).getBili_list().get(indexs).getBili_name();
                                    } else {
                                        s += "-";
                                    }
                                    if (i != 9) {
                                        s += ",";
                                    }
                                }
                                dragonBet = s;
                            }
                            cathecticDialog.setContext(list.get(positions).getGame_name(), list.get(positions).getGame_num() + "", money, dragonBet);
                            cathecticDialog.show();
                        } else {
                            ToastUtil.Toast(getContext(), "下注金额不够!");
                            cathectic_xiazhu.setText("1");
                        }
                        cathecticDialog.setContext(list.get(positions).getGame_name(), list.get(positions).getGame_num() + "", money, dragonBet);
                        cathecticDialog.show();
                    } else {
                        ToastUtil.Toast(getContext(), "下注金额不够!");
                        cathectic_xiazhu.setText("1");
                    }
                }
                break;
            case R.id.cathectic_dialog_clear:
                cathecticDialog.dismiss();
                break;
            case R.id.cathectic_dialog_ok:
                requestCathectic();
                break;
        }
    }

    public void getCathectic(int position, int index) {
        if (!cathectic_xiazhu.getText().toString().equals("")) {
            beinums = Integer.parseInt(cathectic_xiazhu.getText().toString());
        }
        allmaxmoney = list.get(position).getBili_list().get(index).getBili() * beinums;
        money = beinums;
        String maxmoneystr = "最高可中<font color='#FE504F'>" + Util.getTwoDecimal(allmaxmoney) + "</font>元";
        maxmoney.setText(Html.fromHtml(maxmoneystr));
        String totalstr = "共<font color='#FE504F'>1</font>注, <font color='#FE504F'>" + money + "</font>元";
        total_num_money.setText(Html.fromHtml(totalstr));
    }

    public void resetCathectic() {
        beinums = 1;
        allmaxmoney = 0;
        money = 0;
        positions = 0;
        indexs = 0;
        dragonBet = "";
        maxmoney.setText("最高可中0元");
        total_num_money.setText("共0注,0元");
        cathectic_xiazhu.setText("1");
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void Event(String messageEvent) {
        if (System.currentTimeMillis() - curtime <= 3000) {
            curtime = System.currentTimeMillis();
            return;
        }
        curtime = System.currentTimeMillis();
        if (!TextUtils.isEmpty(messageEvent) && messageEvent.equals(REFRESH_DATA)) {
            onRefresh();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        if (cathecticDialog != null) {
            cathecticDialog.dismiss();
        }
        curtime = System.currentTimeMillis();
        resetCathectic();
        requestCathecticList();
    }

    @Override
    public void onLoadMore() {
        onXLoadMore();
    }

    public void onXLoadMore() {

        cathectic_recycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtil.Toast(getContext(), "没有更多数据!");
                if (cathectic_recycler != null)
                    cathectic_recycler.setPullLoadMoreCompleted();
            }
        }, 500);
    }
}

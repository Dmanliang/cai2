package com.sp.caitwo.ui.activity.lottery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.HomeWanFaBean;
import com.sp.caitwo.bean.WanFaInfo;
import com.sp.caitwo.ui.adapter.lottery.AllWanFaAdapter;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.CATEGORY_TYPE;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.DATA_BASEWANFA_ID;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.DATA_GAME_TYPE;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.DATA_TITLE;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.DATA_TYPE;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.WANFA_LIST;

/**
 * Created by Administrator on 2018/11/2.
 */

public class AllWanFaActivity extends AppCompatActivity {


    private XRecyclerView rvData;
    private AllWanFaAdapter allWanFaAdapter;
    private List<WanFaInfo> list = new ArrayList<>();
    private HomeWanFaBean homeWanFaBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allwanfa);
        initView();
    }

    public void initView() {
        rvData = findViewById(R.id.allwanfa_recycler);
        TextView tvTopTitle = findViewById(R.id.tv_top_title);
        rvData.setLayoutManager(new LinearLayoutManager(AllWanFaActivity.this));
        rvData.setPullRefreshEnabled(false);
        allWanFaAdapter = new AllWanFaAdapter(this, list);
        rvData.setAdapter(allWanFaAdapter);
        allWanFaAdapter.notifyDataSetChanged();
        allWanFaAdapter.setWanfaItemListener(new AllWanFaAdapter.AllWanfaItemClickListener() {
            @Override
            public void AllWanfaClick(View view, int positionPare, int position) {
                Intent intent = new Intent(AllWanFaActivity.this, LotteryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(DATA_BASEWANFA_ID, list.get(positionPare).getWanFaList().get(position).getId());
                bundle.putInt(DATA_GAME_TYPE, list.get(positionPare).getWanFaList().get(position).getGame_type());
                bundle.putString(DATA_TITLE, list.get(positionPare).getWanFaList().get(position).getName());
                bundle.putInt(DATA_TYPE, list.get(positionPare).getWanFaList().get(position).getType());
                bundle.putInt(CATEGORY_TYPE,list.get(positionPare).getWanFaList().get(position).getCategory_type());
                if(!BaseUtil.isEmpty(homeWanFaBean)){
                    bundle.putSerializable(WANFA_LIST,homeWanFaBean);
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        tvTopTitle.setText(getString(R.string.all_lottery_type));
        findViewById(R.id.iv_top_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loadWanFa();
    }

    private void loadWanFa() {
        InterfaceTask.getInstance().getHomeWanFa(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                homeWanFaBean = (HomeWanFaBean) obj;
                WanFaInfo wanFaInfo1 = new WanFaInfo();
                WanFaInfo wanFaInfo2 = new WanFaInfo();
                WanFaInfo wanFaInfo3 = new WanFaInfo();
                WanFaInfo wanFaInfo4 = new WanFaInfo();
                WanFaInfo wanFaInfo5 = new WanFaInfo();
                WanFaInfo wanFaInfo6 = new WanFaInfo();
                WanFaInfo wanFaInfo7 = new WanFaInfo();
                for (int i = 0; i < homeWanFaBean.getData().size(); i++) {
                    if( !homeWanFaBean.getData().get(i).getName().equals("双色球")
                            && !homeWanFaBean.getData().get(i).getName().equals("七星彩")
                            && !homeWanFaBean.getData().get(i).getName().equals("七乐彩")
                            && !homeWanFaBean.getData().get(i).getName().equals("超级大乐透")){
                        homeWanFaBean.getData().get(i).setGame_type(homeWanFaBean.getData().get(i).getGame_type());
                        if (homeWanFaBean.getData().get(i).getCategory_type() == 1) {
                            wanFaInfo1.getWanFaList().add(homeWanFaBean.getData().get(i));
                        } else if (homeWanFaBean.getData().get(i).getCategory_type() == 2) {
                            wanFaInfo2.getWanFaList().add(homeWanFaBean.getData().get(i));
                        } else if (homeWanFaBean.getData().get(i).getCategory_type() == 3) {
                            wanFaInfo3.getWanFaList().add(homeWanFaBean.getData().get(i));
                        } else if (homeWanFaBean.getData().get(i).getCategory_type() == 4) {
                            wanFaInfo4.getWanFaList().add(homeWanFaBean.getData().get(i));
                        } else if (homeWanFaBean.getData().get(i).getCategory_type() == 5) {
                            wanFaInfo5.getWanFaList().add(homeWanFaBean.getData().get(i));
                        } /*else if (homeWanFaBean.getData().get(i).getCategory_type() == 6) {
                            wanFaInfo6.getWanFaList().add(homeWanFaBean.getData().get(i));
                        } else if (homeWanFaBean.getData().get(i).getCategory_type() == 7) {
                            wanFaInfo7.getWanFaList().add(homeWanFaBean.getData().get(i));
                        }*/
                    }
                }
                wanFaInfo1.setWanfa_type_name("快3");
                wanFaInfo2.setWanfa_type_name("时时彩");
                wanFaInfo3.setWanfa_type_name("快乐彩");
                wanFaInfo4.setWanfa_type_name("11选5");
                wanFaInfo5.setWanfa_type_name("低频彩");
           /*     wanFaInfo6.setWanfa_type_name("游戏彩");
                wanFaInfo7.setWanfa_type_name("体育彩");*/
                list.add(wanFaInfo1);
                list.add(wanFaInfo2);
                list.add(wanFaInfo3);
                list.add(wanFaInfo4);
                list.add(wanFaInfo5);
             /*   list.add(wanFaInfo6);
                list.add(wanFaInfo7);*/
                allWanFaAdapter.notifyDataSetChanged();
            }
        });
    }
}

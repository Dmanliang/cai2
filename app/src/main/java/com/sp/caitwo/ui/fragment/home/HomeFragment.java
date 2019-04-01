package com.sp.caitwo.ui.fragment.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.SeBoCons;
import com.sp.caitwo.bean.BannerBean;
import com.sp.caitwo.bean.HomeWanFaBean;
import com.sp.caitwo.bean.MessageBean;
import com.sp.caitwo.info.SeboNumberInfo;
import com.sp.caitwo.ui.activity.lottery.AllWanFaActivity;
import com.sp.caitwo.ui.activity.lottery.LongDragonActivity;
import com.sp.caitwo.ui.activity.lottery.LotteryActivity;
import com.sp.caitwo.ui.activity.sysm.LoginActivity;
import com.sp.caitwo.ui.activity.sysm.WebLoadActivity;
import com.sp.caitwo.ui.activity.transferaccounts.RechargeWayActivity;
import com.sp.caitwo.ui.adapter.lottery.WanFaAdapter;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.TextViewUtil;
import com.sp.xwjlibrary.widget.BannerCycleView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.sp.caitwo.base.SeBoCons.grayballNumbers;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.CATEGORY_TYPE;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.DATA_BASEWANFA_ID;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.DATA_GAME_TYPE;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.DATA_TITLE;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.DATA_TYPE;
import static com.sp.caitwo.ui.activity.lottery.LotteryActivity.WANFA_LIST;

/**
 * Created by Administrator on 2018/10/28.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private BannerCycleView bcvHome;
    private List<BannerBean.Datas> bannerList;
    private RecyclerView rvWanFa;
    private RelativeLayout itemLongdragon;
    private LinearLayout itemCzcenter, itemConnectServer;
    private List<HomeWanFaBean.DataBean> wanFas = new ArrayList<>();
    private WanFaAdapter wanFaAdapter;
    private LinearLayout moreWanfa;
    private TextView tvNoticeAd;
    private TextView tvTopTitle;
    private ImageView connectService;
    private View inflate;
    private HomeWanFaBean homeWanFaBean;
    private ArrayList<MessageBean.DataBean.ListBean> noticeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflate == null) {
            noticeList = new ArrayList<>();
            inflate = inflater.inflate(R.layout.fragment_home_vip, null, false);
            init();
            loadWanFa();
            loadBanner();
            loadNotice();
            getSeboNumber();
        }
        return inflate;
    }

    private void loadNotice() {
        InterfaceTask.getInstance().getNoticeMsg(1, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                MessageBean json = (MessageBean) obj;
                if (!BaseUtil.isEmpty(json.getData()) && !BaseUtil.isEmpty(json.getData().getList())) {
                    ArrayList<String> tiplist = new ArrayList<>();
                    noticeList.clear();
                    for (MessageBean.DataBean.ListBean listBean : json.getData().getList()) {
                        if (listBean.getNotice_type() == 1) {
                            tiplist.add(listBean.getTitle());
                            noticeList.add(listBean);
                        }
                    }
                    TextViewUtil.setVerticalAnim(tvNoticeAd, tiplist.toArray(new String[0]), 5, 2);
                }
            }
        });
    }

    private void init() {
        bannerList = new ArrayList<>();
        bcvHome = inflate.findViewById(R.id.bcv_home);
        moreWanfa = inflate.findViewById(R.id.more_wanfa);
        itemCzcenter = inflate.findViewById(R.id.item_czcenter);
        itemLongdragon = inflate.findViewById(R.id.item_longdragon);
        itemConnectServer = inflate.findViewById(R.id.item_connect_server);
        tvNoticeAd = inflate.findViewById(R.id.tv_notice_ad);
        connectService = inflate.findViewById(R.id.connect_service);
        rvWanFa = inflate.findViewById(R.id.rvWanFa);
        tvTopTitle = inflate.findViewById(R.id.tv_top_title);
        tvTopTitle.setText("购彩大厅");
        rvWanFa.setLayoutManager(new GridLayoutManager(getContext(), 3));
        itemCzcenter.setOnClickListener(this);
        itemLongdragon.setOnClickListener(this);
        itemConnectServer.setOnClickListener(this);
        connectService.setOnClickListener(this);
        connectService.setVisibility(View.VISIBLE);
        moreWanfa.setOnClickListener(this);
        wanFaAdapter = new WanFaAdapter(HomeFragment.this.getContext(), wanFas);
        wanFaAdapter.setOnItemImageViewClickListener(new WanFaAdapter.OnItemImageViewClickListener() {
            @Override
            public void OnItemImageViewClick(View view, int positionPare, int position) {
                if (!BaseUtil.isEmpty(App.userInfoBean.getData().getAccess_token())) {
                    Intent intent = new Intent(HomeFragment.this.getActivity(), LotteryActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(DATA_BASEWANFA_ID, wanFas.get(position).getId());
                    bundle.putInt(DATA_GAME_TYPE, wanFas.get(position).getGame_type());
                    bundle.putString(DATA_TITLE, wanFas.get(position).getName());
                    bundle.putInt(DATA_TYPE, wanFas.get(position).getType());
                    bundle.putInt(CATEGORY_TYPE, wanFas.get(position).getCategory_type());
                    if (!BaseUtil.isEmpty(homeWanFaBean)) {
                        bundle.putSerializable(WANFA_LIST, homeWanFaBean);
                    }
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(HomeFragment.this.getContext(), LoginActivity.class));
                }
            }
        });
        tvNoticeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = tvNoticeAd.getTag(com.sp.xwjlibrary.R.id.tvAnimPos);
                if (BaseUtil.isEmpty(tag)) return;
                int pos = (int) tag;
                Intent intent = new Intent(getContext(), WebLoadActivity.class);
                intent.putExtra(WebLoadActivity.URL, noticeList.get(pos).getContent());
                intent.putExtra(WebLoadActivity.OTHER_INFO, noticeList.get(pos).getTitle() + "," + noticeList.get(pos).getCreate_time() + "," + noticeList.get(pos).getId());
                intent.putExtra(WebLoadActivity.TITLE, "公告详情");
                startActivity(intent);

            }
        });
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
                App.homeWanFaBean = homeWanFaBean;
                for (HomeWanFaBean.DataBean dataBean : homeWanFaBean.getData()) {
                    if (!dataBean.getName().equals("双色球")
                            && !dataBean.getName().equals("七星彩")
                            && !dataBean.getName().equals("七乐彩")
                            && !dataBean.getName().equals("超级大乐透")
                            && dataBean.getHot() == 1) {
                        wanFas.add(dataBean);
                    }
                }
                rvWanFa.setAdapter(wanFaAdapter);
                wanFaAdapter.notifyDataSetChanged();
            }
        });
    }

    private void loadBanner() {
        InterfaceTask.getInstance().getBanner(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onSuccess(Object obj) {
                BannerBean info = (BannerBean) obj;
                bannerList.clear();
                bannerList.addAll(info.getData());
                setBannerView();
            }
        });

    }

    public void setBannerView() {
        bcvHome.setShowBothSidesPart(0.80f, 0.5f, 1f, 20);
        bcvHome.setIndicatorStyle(BannerCycleView.IndicationStyle.COLOR, 8, 8, ContextCompat.getColor(getContext(), R.color.gray2), ContextCompat.getColor(getContext(), R.color.text_white));
        bcvHome.loadData(bannerList.size(), new BannerCycleView.OnBannerCycleListener() {
            @Override
            public void onBannerCycleView(ImageView view, int index) {
                ImageOptions options = new ImageOptions.Builder()
                        .setImageScaleType(ImageView.ScaleType.FIT_XY)
                        .setRadius(20).setCrop(true)
                        .setConfig(Bitmap.Config.RGB_565)
                        .build();
                x.image().bind(view, bannerList.get(index).getBanner_imgurl(), options);
            }


            @Override
            public void onBannerClick(View v, int index) {
                if (bannerList.get(index).getIs_go() == 1) {
                    Intent intent = new Intent(getActivity(), WebLoadActivity.class);
                    intent.putExtra(WebLoadActivity.TITLE, bannerList.get(index).getBanner_name());
                    intent.putExtra(WebLoadActivity.URL, bannerList.get(index).getUrl());
                    startActivity(intent);
                }
            }

            @Override
            public void onBannerIsZero(ImageView view) {

            }
        });
    }

    public void getSeboNumber() {
        InterfaceTask.getInstance().lhcgetSeboNumber(HomeFragment.this.getContext(), new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                SeboNumberInfo seboNumberInfo = (SeboNumberInfo) obj;
                if (null != seboNumberInfo.getData() || seboNumberInfo.getData().size() != 0) {
                    SeBoCons.getInstant().getRedball().clear();
                    SeBoCons.getInstant().getGreenball().clear();
                    SeBoCons.getInstant().getBlueball().clear();
                    String[] redball = ((SeboNumberInfo) obj).getData().get(0).getNumber().split(",");
                    String[] greenball = ((SeboNumberInfo) obj).getData().get(1).getNumber().split(",");
                    String[] blueball = ((SeboNumberInfo) obj).getData().get(2).getNumber().split(",");
                    for (String s : redball) {
                        SeBoCons.getInstant().getRedball().add(s);
                    }
                    for (String s : greenball) {
                        SeBoCons.getInstant().getGreenball().add(s);
                    }
                    for (String s : blueball) {
                        SeBoCons.getInstant().getBlueball().add(s);
                    }
                    for (String s : grayballNumbers) {
                        SeBoCons.getInstant().getGrayball().add(s);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_czcenter:
                InterfaceTask.getInstance().getUserDetail(getActivity(), new InterfaceTask.OnInterfaceTask() {
                    @Override
                    public void onSuccess(Object obj) {
                        startActivity(new Intent(getActivity(), RechargeWayActivity.class));
                    }
                });
                break;
            case R.id.item_longdragon:
                if (App.homeWanFaBean != null) {
                    startActivity(new Intent(getActivity(), LongDragonActivity.class));
                }
                break;
            case R.id.more_wanfa:
                if (!BaseUtil.isEmpty(App.userInfoBean.getData().getAccess_token())) {
                    Intent intent = new Intent(HomeFragment.this.getContext(), AllWanFaActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(HomeFragment.this.getContext(), LoginActivity.class));
                }
                break;
            case R.id.item_connect_server:
            case R.id.connect_service:
                if (!BaseUtil.isEmpty(App.customServerAddr)) {
                    Intent intent = new Intent(getContext(), WebLoadActivity.class);
                    intent.putExtra(WebLoadActivity.URL, App.customServerAddr);
                    intent.putExtra(WebLoadActivity.TITLE, getString(R.string.custom_service));
                    startActivity(intent);
                }
                break;
        }
    }

}

package com.sp.caitwo.ui.activity.lottery;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.base.BaseLotteryFragment;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.bean.HomeWanFaBean;
import com.sp.caitwo.info.BetInfo;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.info.LhcAllWanFaInfo;
import com.sp.caitwo.ui.adapter.lottery.LotteryFragmentAdpter;
import com.sp.caitwo.ui.adapter.lottery.TabGridAdapter;
import com.sp.caitwo.ui.adapter.lottery.TitleGridAdapter;
import com.sp.caitwo.ui.adapter.lottery.WanFaMenuAdapter;
import com.sp.caitwo.ui.fragment.lottery.CathecticRecordFragment;
import com.sp.caitwo.ui.fragment.lottery.ElevenXFiveFragment;
import com.sp.caitwo.ui.fragment.lottery.FenFenCaiFragment;
import com.sp.caitwo.ui.fragment.lottery.Kuai3Fragment;
import com.sp.caitwo.ui.fragment.lottery.LhcFragment;
import com.sp.caitwo.ui.fragment.lottery.LotteryFragment;
import com.sp.caitwo.ui.fragment.lottery.PaiLie3Fragment;
import com.sp.caitwo.ui.fragment.lottery.PaiLie5Fragment;
import com.sp.caitwo.ui.fragment.lottery.WanFa28Fragment;
import com.sp.caitwo.ui.info.LotteryTabInfo;
import com.sp.caitwo.ui.info.LotteryTitle;
import com.sp.util.ProjectUtil;
import com.sp.xwjlibrary.dialog.PopupDialog;
import com.sp.xwjlibrary.util.BaseUtil;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.widget.NestedGridView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

import static com.sp.caitwo.ui.activity.lottery.LotteryCathecticActivity.REQUEST_CODE;
import static com.sp.caitwo.ui.fragment.lottery.WanFa28Fragment.CLEAR_LIST;
import static com.sp.util.Util.C;


public class LotteryActivity extends AppCompatActivity implements View.OnClickListener, BaseLotteryFragment.OnMenuItemClickListener {

    private int[][] paile3_select = {{1, 1, 1}, {2}, {3}, {1, 1, 1}, {1, 2}};
    private int[][] paile5_select = {{1, 1, 1, 1, 1}};
    private int[][][] elevenx5_select = {{{2}, {3}, {4}, {5}, {6}, {7}, {8}}, {{1}}, {{1, 1}, {2}}, {{1, 1, 1}, {1}}, {{2}, {1}, {2}, {1}}, {{1}, {1}, {1}, {1}, {1}}};
    private int[][][] fencai_select = {{{1}, {1, 1, 1}, {1, 1, 1}, {1, 1}, {1, 1}}, {{1, 1}, {1}, {1, 1}, {1}}, {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, {{1, 1, 1, 1}}, {{1, 1, 1, 1, 1}}, {{1}, {1}, {1}, {1}, {1}}, {{1}, {1}}, {{1}, {2}, {1}, {2}}, {{1, 1, 1, 1, 1}}};
    private int[][] lhc_select = {{1}, {1}, {1, 1, 1, 1, 1, 1}, {4, 3, 2, 2, 2, 2, 3}, {1}, {1, 1}, {1}, {2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {2, 3, 4, 5, 2, 3, 4}, {2, 3, 4, 2, 3, 4}, {5, 6, 7, 8, 9, 10, 11, 12}};
    public static String REFRESH_DATA = "refresh_data";
    public static final String UPDATE_INFO = "update_info";
    public static final String START_ANIMATION = "start_animation";
    public static final String DATA_BASEWANFA_ID = "DATA_BASEWANFA_ID";
    public static final String DATA_GAME_TYPE = "DATA_GAME_TYPE";
    public static final String DATA_TITLE = "DATA_TITLE";
    public static final String DATA_SECONDS = "DATA_SECONDS";
    public static final String DATA_CHONE = "DATA_CHONE";
    public static String DATA_BILILIST = "DATA_BILILIST";
    public static String DATA_TYPE = "DATA_TYPE";
    public static String WANFA_LIST = "WANFA_LIST";
    public static String CATEGORY_TYPE = "CATEGORY_TYPE";
    private LinearLayout lotteryToolbar;
    private TabLayout tabLayout;
    private ViewPager lotteryViewpager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private LotteryFragmentAdpter lotteryFragmentAdpter;
    private RelativeLayout remainmoneyLayout;
    private TextView lotteryRemainmoney;
    private ImageView iconRemainmoney;
    private TextView lotteryTitle;
    private LinearLayout lotteryTitleSelect;
    private LinearLayout areaTitleSelect;
    private TextView tvArea;
    private ImageView lotteryArrow, areaArrow, ivTopBack;
    private List<LotteryTitle> mlistLotteryTitle = new ArrayList<>();
    private String mTitle, title, tabtitle;
    private int titleindex = 0, tabindex = 0;
    private int mBaseWanfaId;
    private int game_type;
    private PopupDialog mAreaSelDialog;
    private NestedGridView lotteryTitleGridview;
    private NestedGridView lotteryTabGridview;
    private TitleGridAdapter titleGridAdapter;
    private TabGridAdapter tabGridAdapter;
    private List<LotteryTabInfo> mlistLotteryTab = new ArrayList<>();
    private PopupDialog mLotteryTitleDialog;
    private List<BiliListInfo.DataBean> bililist = new ArrayList<>();
    private List<LhcAllWanFaInfo.DataBean> lhcwanfalist = new ArrayList<>();
    private long curtime;
    private boolean isShowMoney = false;
    private double allPoint;
    private int unit_type, lhcPoint;
    private List<BetInfo.DataBean> betInfoList = new ArrayList<>();
    private List<BetInfo> betInfos = new ArrayList<>();
    private BaseLotteryFragment fragment = null;
    private int bet_type;//type 0直投 1房间 2六合彩
    private WanFaMenuAdapter wanFaMenuAdapter;
    private RecyclerView wanfaMenu;
    private List<HomeWanFaBean.DataBean> wanfalist = new ArrayList<>();
    private HomeWanFaBean homeWanFaBean;
    private BadgeView badgeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        curtime = System.currentTimeMillis();
        setContentView(R.layout.activity_lottery);
        getData();
        initView();
        initFragmet();
        titleDialog();
        initDialog();
        selectRequest();
        setLotteryTitle();
    }

    public void selectRequest() {
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
                requestBiliList(game_type);
                break;
            case LotteryCons.FENFENCAI:
            case LotteryCons.YDWFC:
            case LotteryCons.FUCAI3D:
            case LotteryCons.PAILIE3:
            case LotteryCons.PAILIE5:
            case LotteryCons.ELEVENXFIVE:
                requestWanfaList(mBaseWanfaId);
                break;
            case LotteryCons.SIXHECAI:
                getlhcAllWanFaList();
                break;
        }
    }

    private void initDialog() {
        wanfalist = ProjectUtil.getWanFaMenu(mTitle, homeWanFaBean);
        View view = View.inflate(this, R.layout.wanfa_menu, null);
        mAreaSelDialog = new PopupDialog(this, view);
        wanfaMenu = view.findViewById(R.id.wanfa_menu);
        wanfaMenu.setLayoutManager(new GridLayoutManager(LotteryActivity.this, 2, GridLayoutManager.VERTICAL, false));
        wanFaMenuAdapter = new WanFaMenuAdapter(LotteryActivity.this, wanfalist);
        wanfaMenu.setAdapter(wanFaMenuAdapter);
        wanFaMenuAdapter.setOnItemClickListener(new WanFaMenuAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                tvArea.setText(wanfalist.get(position).getName());
                mAreaSelDialog.dismiss();
                game_type = wanfalist.get(position).getGame_type();
                mBaseWanfaId = wanfalist.get(position).getId();
                mTitle = wanfalist.get(position).getName();
                titleindex = 0;
                tabindex = 0;
                betInfos.clear();
                betInfoList.clear();
                selectRequest();
                getLotterInfo();
                getCathecticRecord();
                setBadgeView();
            }
        });
        tvArea.setText(mTitle);
        mLotteryTitleDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ObjectAnimator.ofFloat(lotteryArrow,"rotation",180).setDuration(500).start();
            }
        });
        mLotteryTitleDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ObjectAnimator.ofFloat(lotteryArrow,"rotation",0).setDuration(500).start();
            }
        });
        mAreaSelDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ObjectAnimator.ofFloat(areaArrow,"rotation",180).setDuration(500).start();
            }
        });
        mAreaSelDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ObjectAnimator.ofFloat(areaArrow,"rotation",0).setDuration(500).start();
            }
        });
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        mBaseWanfaId = bundle.getInt(DATA_BASEWANFA_ID);
        game_type = bundle.getInt(DATA_GAME_TYPE);
        if (game_type == LotteryCons.SIXHECAI) {
            bet_type = 2;
        } else {
            bet_type = bundle.getInt(DATA_TYPE);
        }
        mTitle = bundle.getString(DATA_TITLE);
        homeWanFaBean = (HomeWanFaBean) bundle.getSerializable(WANFA_LIST);
    }

    public void initView() {
        allPoint = App.userInfoBean.getData().getPoint();
        tabLayout = findViewById(R.id.tab_layout);
        lotteryToolbar = findViewById(R.id.lottery_toolbar);
        lotteryViewpager = findViewById(R.id.lottery_viewpager);
        lotteryRemainmoney = findViewById(R.id.lottery_remainmoney);
        remainmoneyLayout = findViewById(R.id.lly_balance_layout);
        lotteryTitle = findViewById(R.id.lottery_title);
        lotteryTitleSelect = findViewById(R.id.lottery_title_select);
        areaTitleSelect = findViewById(R.id.area_title_select);
        iconRemainmoney = findViewById(R.id.icon_remainmoney);
        tvArea = findViewById(R.id.tv_area);
        lotteryArrow = findViewById(R.id.lottery_arrow);
        areaArrow = findViewById(R.id.area_arrow);
        ivTopBack = findViewById(R.id.iv_top_back);
        lotteryTitleSelect.setOnClickListener(this);
        areaTitleSelect.setOnClickListener(this);
        ivTopBack.setOnClickListener(this);
        remainmoneyLayout.setOnClickListener(this);
    }

    public void initFragmet() {
        addFragment();
        list_title.add("我要投注");
        list_title.add("最近投注");
        lotteryFragmentAdpter = new LotteryFragmentAdpter(getSupportFragmentManager(), LotteryActivity.this, fragmentList, list_title);
        lotteryViewpager.setAdapter(lotteryFragmentAdpter);
        tabLayout.setupWithViewPager(lotteryViewpager);
    }

    public void addFragment() {
        if (game_type != 0) {
            switch (game_type) {
                case LotteryCons.CQSSC:
                case LotteryCons.BJPK10:
                case LotteryCons.XYPK10:
                case LotteryCons.TJSSC:
                    fragment = new LotteryFragment();
                    fragment.setGameType(game_type);
                    break;
                case LotteryCons.JSK3:
                case LotteryCons.BJK3:
                case LotteryCons.AHK3:
                    fragment = new Kuai3Fragment();
                    fragment.setGameType(game_type);
                    break;
                case LotteryCons.XJP28:
                case LotteryCons.JND_28:
                case LotteryCons.BJ_28:
                    fragment = new WanFa28Fragment();
                    fragment.setGameType(game_type);
                    break;
                case LotteryCons.FENFENCAI:
                case LotteryCons.YDWFC:
                    fragment = new FenFenCaiFragment();
                    fragment.setGameType(game_type);
                    break;
                case LotteryCons.SIXHECAI:
                    fragment = new LhcFragment();
                    fragment.setGameType(game_type);
                    break;
                case LotteryCons.FUCAI3D:
                case LotteryCons.PAILIE3:
                    fragment = new PaiLie3Fragment();
                    fragment.setGameType(game_type);
                    break;
                case LotteryCons.PAILIE5:
                    fragment = new PaiLie5Fragment();
                    fragment.setGameType(game_type);
                    break;
                case LotteryCons.ELEVENXFIVE:
                    fragment = new ElevenXFiveFragment();
                    fragment.setGameType(game_type);
                    break;
            }
            if (fragment != null) {
                fragment.setOnMenuItemClickListener(this);
                fragmentList.add(fragment);
            }
        }
        fragmentList.add(new CathecticRecordFragment());
        ivTopBack.postDelayed(new Runnable() {
            @Override
            public void run() {
                getLotterInfo();
                getCathecticRecord();
            }
        }, 500);
    }

    public void requestBiliList(int game_type) {
        InterfaceTask.getInstance().getBiliList(game_type, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                BiliListInfo biliListInfo = (BiliListInfo) obj;
                if (biliListInfo != null && biliListInfo.getData() != null && biliListInfo.getData().size() > 0) {
                    bililist.clear();
                    List<BiliListInfo.DataBean> list = biliListInfo.getData();
                    for (BiliListInfo.DataBean bean : list) {
                        if (bean.getName().contains("不定胆") && null != bean.getLists() && bean.getLists().size() > 0) {
                            for (BiliListInfo.ListBeans info2 : bean.getLists()) {
                                if (info2.getName().contains("后三一码")) {
                                    info2.someNum = 1;
                                } else if (info2.getName().contains("后三二码")) {
                                    info2.someNum = 2;
                                } else if (info2.getName().contains("前三一码")) {
                                    info2.someNum = 1;
                                } else if (info2.getName().contains("前三二码")) {
                                    info2.someNum = 2;
                                } else if (info2.getName().contains("前五三码")) {
                                    info2.someNum = 3;
                                } else if (info2.getName().contains("前五二码")) {
                                    info2.someNum = 2;
                                } else if (info2.getName().contains("后五三码")) {
                                    info2.someNum = 3;
                                } else if (info2.getName().contains("后五二码")) {
                                    info2.someNum = 2;
                                }
                            }
                        }
                    }
                    bililist.addAll(list);
                    setLotteryTitleData();
                    getFragment();
                }
            }
        });
    }

    public void requestWanfaList(int baseWanfaId) {
        InterfaceTask.getInstance().getWanfaList(baseWanfaId, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                BiliListInfo biliListInfo = (BiliListInfo) obj;
                if (biliListInfo != null && biliListInfo.getData() != null && biliListInfo.getData().size() > 0) {
                    bililist.clear();
                    List<BiliListInfo.DataBean> data = biliListInfo.getData();
                    bililist.addAll(data);
                    setSelectNum();
                    setLotteryTitleData();
                    getFragment();
                }
            }
        });
    }

    public void getlhcAllWanFaList() {
        InterfaceTask.getInstance().getlhcAllWanFaList(new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                LhcAllWanFaInfo lhcAllWanFaInfo = (LhcAllWanFaInfo) obj;
                lhcwanfalist.addAll(lhcAllWanFaInfo.getData());
                setSelectNum();
                setLotteryTitleData();
                getFragment();
            }
        });
    }

    public void setSelectNum() {
        if (bililist.size() != 0) {
            for (int i = 0; i < bililist.size(); i++) {
                BiliListInfo.DataBean bean = bililist.get(i);
                if (null != bean.getLists() && bean.getLists().size() != 0) {
                    for (int j = 0; j < bean.getLists().size(); j++) {
                        BiliListInfo.ListBeans beans = bililist.get(i).getLists().get(j);
                        switch (game_type) {
                            case LotteryCons.FENFENCAI:
                            case LotteryCons.YDWFC:
                                if (null != bililist.get(i).getLists().get(j).getLists() && bililist.get(i).getLists().get(j).getLists().size() != 0) {
                                    for (int k = 0; k < bililist.get(i).getLists().get(j).getLists().size(); k++) {
                                        BiliListInfo.ListBean2 bean2 = bililist.get(i).getLists().get(j).getLists().get(k);
                                        bean2.setSomeNum(fencai_select[i][j][k]);
                                    }
                                } else {
                                    beans.setSomeNum(fencai_select[i][j][0]);
                                }
                                break;
                            case LotteryCons.FUCAI3D:
                            case LotteryCons.PAILIE3:
                                beans.setSomeNum(paile3_select[i][j]);
                                break;
                            case LotteryCons.PAILIE5:
                                beans.setSomeNum(paile5_select[i][j]);
                                break;
                            case LotteryCons.ELEVENXFIVE:
                                if (null != bililist.get(i).getLists().get(j).getLists() && bililist.get(i).getLists().get(j).getLists().size() != 0) {
                                    for (int k = 0; k < bililist.get(i).getLists().get(j).getLists().size(); k++) {
                                        BiliListInfo.ListBean2 bean2 = bililist.get(i).getLists().get(j).getLists().get(k);
                                        bean2.setSomeNum(elevenx5_select[i][j][k]);
                                    }
                                } else {
                                    beans.setSomeNum(elevenx5_select[i][j][0]);
                                }
                                break;
                        }
                    }
                } else if (null != bean.getList() && bean.getList().size() != 0) {
                    switch (game_type) {
                        case LotteryCons.FENFENCAI:
                        case LotteryCons.YDWFC:
                            bean.setSomeNum(fencai_select[i][0][0]);
                            break;
                        case LotteryCons.FUCAI3D:
                        case LotteryCons.PAILIE3:
                            bean.setSomeNum(paile3_select[i][0]);
                            break;
                        case LotteryCons.PAILIE5:
                            break;
                        case LotteryCons.ELEVENXFIVE:
                            bean.setSomeNum(elevenx5_select[i][0][0]);
                            break;
                    }
                }
            }
        } else if (lhcwanfalist.size() != 0) {
            for (int i = 0; i < lhcwanfalist.size(); i++) {
                List<LhcAllWanFaInfo.DataBean.ListsBean> listsBeans = lhcwanfalist.get(i).getLists();
                List<LhcAllWanFaInfo.DataBean.ListsBean.ListBean> listsBean = lhcwanfalist.get(i).getList();
                if (i == 7 || i == 10) {
                    for (int j = 0; j < listsBean.size(); j++) {
                        lhcwanfalist.get(i).getList().get(j).setSomeNum(lhc_select[i][j]);
                    }
                } else {
                    if (null != listsBeans && listsBeans.size() != 0) {
                        for (int j = 0; j < listsBeans.size(); j++) {
                            listsBeans.get(j).setSomeNum(lhc_select[i][j]);
                        }
                    } else if (null != listsBean && listsBean.size() != 0) {
                        lhcwanfalist.get(i).setSomeNum(lhc_select[i][0]);
                    }
                }
            }
        }
    }


    public void loadUserInfo() {
        InterfaceTask.getInstance().createUserInfo(LotteryActivity.this, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
            }

            @Override
            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                allPoint = App.userInfoBean.getData().getPoint();
                if (isShowMoney) {
                    lotteryRemainmoney.setText("余额:" + allPoint);
                }
            }
        });
    }

    public void setLotteryTitleData() {
        switch (game_type) {
            case LotteryCons.CQSSC:
            case LotteryCons.TJSSC:
            case LotteryCons.JSK3:
            case LotteryCons.BJK3:
            case LotteryCons.AHK3:
            case LotteryCons.XJP28:
            case LotteryCons.JND_28:
            case LotteryCons.BJ_28:
            case LotteryCons.BJPK10:
            case LotteryCons.XYPK10:
            case LotteryCons.FENFENCAI:
            case LotteryCons.YDWFC:
            case LotteryCons.SIXHECAI:
            case LotteryCons.PAILIE3:
            case LotteryCons.FUCAI3D:
            case LotteryCons.PAILIE5:
            case LotteryCons.ELEVENXFIVE:
                setTitle();
                break;
        }
    }

    private void setTitle() {
        mlistLotteryTitle.clear();
        if (game_type == LotteryCons.SIXHECAI) {
            LotteryTitle lhclotteryTitle;
            for (int i = 0; i < lhcwanfalist.size(); i++) {
                lhclotteryTitle = new LotteryTitle(lhcwanfalist.get(i).getName());
                if (i == 0) {
                    lhclotteryTitle.setFocus(true);
                }
                if (i == 7 || i == 10) {
                    if (null != lhcwanfalist.get(i).getList() || lhcwanfalist.get(i).getList().size() != 0) {
                        for (int j = 0; j < lhcwanfalist.get(i).getList().size(); j++) {
                            LotteryTabInfo lotteryTabInfo = new LotteryTabInfo();
                            lotteryTabInfo.setTitle(lhcwanfalist.get(i).getList().get(j).getWanfa_name());
                            lhclotteryTitle.getLottery_tab_title().add(lotteryTabInfo);
                        }
                    }
                } else if (i != 4 && i != 7 && i != 10) {
                    if (null != lhcwanfalist.get(i).getLists() || lhcwanfalist.get(i).getLists().size() != 0) {
                        for (int j = 0; j < lhcwanfalist.get(i).getLists().size(); j++) {
                            LotteryTabInfo lotteryTabInfo = new LotteryTabInfo();
                            lotteryTabInfo.setTitle(lhcwanfalist.get(i).getLists().get(j).getName());
                            lhclotteryTitle.getLottery_tab_title().add(lotteryTabInfo);
                        }
                    }
                }
                mlistLotteryTitle.add(lhclotteryTitle);
            }
        } else {
            LotteryTitle lotteryTitle;
            for (int i = 0; i < bililist.size(); i++) {
                lotteryTitle = new LotteryTitle(bililist.get(i).getName());
                if (i == 0) {
                    lotteryTitle.setFocus(true);
                }
                if (game_type != 0) {
                    switch (game_type) {
                        case LotteryCons.CQSSC:
                        case LotteryCons.TJSSC:
                        case LotteryCons.BJPK10:
                        case LotteryCons.XYPK10:
                        case LotteryCons.FUCAI3D:
                        case LotteryCons.PAILIE3:
                            if (i == 4) {
                                if (null != bililist.get(i).getLists() || bililist.get(i).getLists().size() != 0) {
                                    for (int j = 0; j < bililist.get(i).getLists().size(); j++) {
                                        LotteryTabInfo lotteryTabInfo = new LotteryTabInfo();
                                        lotteryTabInfo.setTitle(bililist.get(i).getLists().get(j).getName());
                                        lotteryTitle.getLottery_tab_title().add(lotteryTabInfo);
                                    }
                                }
                            }
                            break;
                        case LotteryCons.JSK3:
                        case LotteryCons.BJK3:
                        case LotteryCons.AHK3:
                        case LotteryCons.XJP28:
                        case LotteryCons.JND_28:
                        case LotteryCons.BJ_28:
                            break;
                        case LotteryCons.FENFENCAI:
                        case LotteryCons.YDWFC:
                            if (null != bililist.get(i).getLists() || bililist.get(i).getLists().size() != 0) {
                                for (int j = 0; j < bililist.get(i).getLists().size(); j++) {
                                    LotteryTabInfo lotteryTabInfo = new LotteryTabInfo();
                                    lotteryTabInfo.setTitle(bililist.get(i).getLists().get(j).getName());
                                    lotteryTitle.getLottery_tab_title().add(lotteryTabInfo);
                                }
                            }
                            break;
                        case LotteryCons.ELEVENXFIVE:
                            if (i != 5) {
                                if (null != bililist.get(i).getLists() || bililist.get(i).getLists().size() != 0) {
                                    for (int j = 0; j < bililist.get(i).getLists().size(); j++) {
                                        LotteryTabInfo lotteryTabInfo = new LotteryTabInfo();
                                        lotteryTabInfo.setTitle(bililist.get(i).getLists().get(j).getName());
                                        lotteryTitle.getLottery_tab_title().add(lotteryTabInfo);
                                    }
                                }
                            }
                            break;
                    }
                }
                mlistLotteryTitle.add(lotteryTitle);
            }
        }
        title = mlistLotteryTitle.get(0).getLottery_title();
        if (mlistLotteryTitle.get(0).getLottery_tab_title().size() != 0) {
            setLotteryTabData(0);
            lotteryTitle.setText(title + mlistLotteryTitle.get(0).getLottery_tab_title().get(0).getTitle());
        } else {
            lotteryTitle.setText(title);
            lotteryTabGridview.setVisibility(View.GONE);
        }
        titleGridAdapter.notifyDataSetChanged();
    }

    //选择彩种计划头列表
    public void setLotteryTitle() {
        titleGridAdapter.setOnClickListener(new TitleGridAdapter.OnItemClickListener() {
            @Override
            public void OnClick(TitleGridAdapter.GridTitleViewHolder viewHolder, int position) {
                title = mlistLotteryTitle.get(position).getLottery_title();
                if (mlistLotteryTitle.get(position).getLottery_tab_title().size() != 0) {
                    lotteryTitle.setText(title + mlistLotteryTitle.get(position).getLottery_tab_title().get(0).getTitle());
                } else {
                    lotteryTitle.setText(title);
                }
                for (int i = 0; i < mlistLotteryTitle.size(); i++) {
                    if (i != position) {
                        mlistLotteryTitle.get(i).setFocus(false);
                    } else {
                        mlistLotteryTitle.get(i).setFocus(true);
                    }
                }
                titleindex = position;
                tabindex = 0;
                titleGridAdapter.notifyDataSetChanged();
                if (game_type != 0) {
                    switch (game_type) {
                        case LotteryCons.JSK3:
                        case LotteryCons.BJK3:
                        case LotteryCons.AHK3:
                        case LotteryCons.XJP28:
                        case LotteryCons.JND_28:
                        case LotteryCons.BJ_28:
                        case LotteryCons.PAILIE5:
                            mLotteryTitleDialog.dismiss();
                            break;
                        case LotteryCons.CQSSC:
                        case LotteryCons.TJSSC:
                        case LotteryCons.BJPK10:
                        case LotteryCons.XYPK10:
                        case LotteryCons.FUCAI3D:
                        case LotteryCons.PAILIE3:
                            if (position == 4) {
                                setLotteryTabData(position);
                                lotteryTabGridview.setVisibility(View.VISIBLE);
                            } else {
                                mLotteryTitleDialog.dismiss();
                                lotteryTabGridview.setVisibility(View.GONE);
                            }
                            break;
                        case LotteryCons.SIXHECAI:
                            if ( position == 0 || position == 1 || position == 4 || position == 6) {
                                mLotteryTitleDialog.dismiss();
                                lotteryTabGridview.setVisibility(View.GONE);
                            } else {
                                setLotteryTabData(position);
                                lotteryTabGridview.setVisibility(View.VISIBLE);
                            }
                            break;
                        case LotteryCons.FENFENCAI:
                        case LotteryCons.YDWFC:
                            setLotteryTabData(position);
                            break;
                        case LotteryCons.ELEVENXFIVE:
                            if (position != 5) {
                                setLotteryTabData(position);
                                lotteryTabGridview.setVisibility(View.VISIBLE);
                            } else {
                                lotteryTabGridview.setVisibility(View.GONE);
                            }
                            break;
                    }
                }
                getFragment();
            }
        });
        tabGridAdapter.setOnItemClickListener(new TabGridAdapter.TabOnItemClickListener() {
            @Override
            public void onItemClick(TabGridAdapter.GridTabViewHolder viewHolder, int position) {
                tabtitle = mlistLotteryTab.get(position).getTitle();
                lotteryTitle.setText(title + tabtitle);
                for (int i = 0; i < mlistLotteryTab.size(); i++) {
                    if (i != position) {
                        mlistLotteryTab.get(i).setFocus(false);
                    } else {
                        mlistLotteryTab.get(i).setFocus(true);
                    }
                }
                tabindex = position;
                tabGridAdapter.notifyDataSetChanged();
                mLotteryTitleDialog.dismiss();
                getFragment();
            }
        });
    }

    public void setLotteryTabData(int position) {
        mlistLotteryTab.clear();
        if (null != mlistLotteryTitle.get(position).getLottery_tab_title() || mlistLotteryTitle.get(position).getLottery_tab_title().size() != 0) {
            for (int i = 0; i < mlistLotteryTitle.get(position).getLottery_tab_title().size(); i++) {
                LotteryTabInfo lotteryTabInfo = mlistLotteryTitle.get(position).getLottery_tab_title().get(i);
                if (i == 0) {
                    lotteryTabInfo.setFocus(true);
                } else {
                    lotteryTabInfo.setFocus(false);
                }
                mlistLotteryTab.add(lotteryTabInfo);
            }
            tabGridAdapter.notifyDataSetChanged();
        } else {
            mLotteryTitleDialog.dismiss();
            tabGridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lottery_title_select:
                if (game_type != LotteryCons.PAILIE5) {
                    mLotteryTitleDialog.show(lotteryToolbar);
                    titleGridAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.area_title_select:
                if (mAreaSelDialog != null)
                    mAreaSelDialog.show(v, 300, Gravity.END, 20);
                break;
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.lly_balance_layout:
                showMoney();
                break;
        }
    }

    public void getFragment() {
        List<Fragment> fragments = this.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof LotteryFragment) {
                ((LotteryFragment) fragment).getData(game_type, titleindex, tabindex, bililist);
            } else if (fragment instanceof Kuai3Fragment) {
                ((Kuai3Fragment) fragment).getData(game_type, titleindex, bililist, mTitle, title, allPoint);
            } else if (fragment instanceof WanFa28Fragment) {
                ((WanFa28Fragment) fragment).getData(game_type, titleindex, bililist, allPoint);
            } else if (fragment instanceof FenFenCaiFragment) {
                ((FenFenCaiFragment) fragment).getData(mBaseWanfaId, game_type, titleindex, tabindex, bililist);
            } else if (fragment instanceof LhcFragment) {
                ((LhcFragment) fragment).getData(game_type, titleindex, tabindex, lhcwanfalist);
            } else if (fragment instanceof PaiLie3Fragment) {
                ((PaiLie3Fragment) fragment).getData(mBaseWanfaId, game_type, titleindex, tabindex, bililist);
            } else if (fragment instanceof PaiLie5Fragment) {
                ((PaiLie5Fragment) fragment).getData(mBaseWanfaId, game_type, titleindex, tabindex, bililist);
            } else if (fragment instanceof ElevenXFiveFragment) {
                ((ElevenXFiveFragment) fragment).getData(mBaseWanfaId, game_type, titleindex, tabindex, bililist);
            }
        }
    }

    public void getLotterInfo() {
        List<Fragment> fragments = this.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof LotteryFragment) {
                ((LotteryFragment) fragment).getRoomOpenInfo(game_type, curtime);
            } else if (fragment instanceof Kuai3Fragment) {
                ((Kuai3Fragment) fragment).getRoomOpenInfo(game_type, curtime);
            } else if (fragment instanceof WanFa28Fragment) {
                ((WanFa28Fragment) fragment).getRoomOpenInfo(game_type, curtime);
            } else if (fragment instanceof FenFenCaiFragment) {
                ((FenFenCaiFragment) fragment).getLotteryOrderInfo(mBaseWanfaId, curtime);
                ((FenFenCaiFragment) fragment).getLotteryKjLog(mBaseWanfaId);
            } else if (fragment instanceof LhcFragment) {
                ((LhcFragment) fragment).getlhcLotteryOrderInfo(curtime);
                ((LhcFragment) fragment).lhcGetWinsRecord();
            } else if (fragment instanceof PaiLie3Fragment) {
                ((PaiLie3Fragment) fragment).getLotteryOrderInfo(mBaseWanfaId, curtime);
                ((PaiLie3Fragment) fragment).getLotteryKjLog(mBaseWanfaId);
            } else if (fragment instanceof PaiLie5Fragment) {
                ((PaiLie5Fragment) fragment).getLotteryOrderInfo(mBaseWanfaId, curtime);
                ((PaiLie5Fragment) fragment).getLotteryKjLog(mBaseWanfaId);
            } else if (fragment instanceof ElevenXFiveFragment) {
                ((ElevenXFiveFragment) fragment).getLotteryOrderInfo(mBaseWanfaId, curtime);
                ((ElevenXFiveFragment) fragment).getLotteryKjLog(mBaseWanfaId);
            }
        }
    }

    public void getCathecticRecord() {
        List<Fragment> fragments = this.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof CathecticRecordFragment) {
                ((CathecticRecordFragment) fragment).getGame_type(game_type);
            }
        }
    }

    public void setStartAnimation(){
        List<Fragment> fragments = this.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof LotteryFragment) {
                ((LotteryFragment) fragment).startLotteryAnim();
            } else if (fragment instanceof Kuai3Fragment) {
                ((Kuai3Fragment) fragment).startLotteryAnim();
            } else if (fragment instanceof WanFa28Fragment) {
                ((WanFa28Fragment) fragment).startLotteryAnim();
            } else if (fragment instanceof FenFenCaiFragment) {
                ((FenFenCaiFragment) fragment).startLotteryAnim();
            } else if (fragment instanceof LhcFragment) {
                ((LhcFragment) fragment).startLotteryAnim();
            } else if (fragment instanceof PaiLie3Fragment) {
                ((PaiLie3Fragment) fragment).startLotteryAnim();
            } else if (fragment instanceof PaiLie5Fragment) {
                ((PaiLie5Fragment) fragment).startLotteryAnim();
            } else if (fragment instanceof ElevenXFiveFragment) {
                ((ElevenXFiveFragment) fragment).startLotteryAnim();
            }
        }
    }

    public void setBadgeView(){
        List<Fragment> fragments = this.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof LotteryFragment) {
                badgeView = ((LotteryFragment) fragment).getBadgeView();
            } else if (fragment instanceof Kuai3Fragment) {
                badgeView = ((Kuai3Fragment) fragment).getBadgeView();
            } else if (fragment instanceof WanFa28Fragment) {
                badgeView = ((WanFa28Fragment) fragment).getBadgeView();
            } else if (fragment instanceof FenFenCaiFragment) {
                badgeView = ((FenFenCaiFragment) fragment).getBadgeView();
            } else if (fragment instanceof LhcFragment) {
                badgeView = ((LhcFragment) fragment).getBadgeView();
            } else if (fragment instanceof PaiLie3Fragment) {
                badgeView = ((PaiLie3Fragment) fragment).getBadgeView();
            } else if (fragment instanceof PaiLie5Fragment) {
                badgeView = ((PaiLie5Fragment) fragment).getBadgeView();
            } else if (fragment instanceof ElevenXFiveFragment) {
                badgeView = ((ElevenXFiveFragment) fragment).getBadgeView();
            }
        }
        switch (game_type) {
            case LotteryCons.CQSSC:
            case LotteryCons.TJSSC:
            case LotteryCons.BJPK10:
            case LotteryCons.XYPK10:
            case LotteryCons.FUCAI3D:
            case LotteryCons.PAILIE3:
            case LotteryCons.XJP28:
            case LotteryCons.JND_28:
            case LotteryCons.BJ_28:
            case LotteryCons.PAILIE5:
            case LotteryCons.SIXHECAI:
            case LotteryCons.FENFENCAI:
            case LotteryCons.YDWFC:
            case LotteryCons.ELEVENXFIVE:
                badgeView.setVisibility(View.GONE);
                break;
        }

    }

    private void titleDialog() {
        View inflate = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_lottery_title, null, false);
        lotteryTitleGridview = inflate.findViewById(R.id.lottery_title_gridview);
        lotteryTabGridview = inflate.findViewById(R.id.lottery_tab_gridview);
        titleGridAdapter = new TitleGridAdapter(getBaseContext(), mlistLotteryTitle);
        tabGridAdapter = new TabGridAdapter(getBaseContext(), mlistLotteryTab);
        lotteryTitleGridview.setAdapter(titleGridAdapter);
        lotteryTabGridview.setAdapter(tabGridAdapter);
        mLotteryTitleDialog = new PopupDialog(this, inflate);
    }

    public void showMoney() {
        if (isShowMoney) {
            isShowMoney = false;
            iconRemainmoney.setVisibility(View.VISIBLE);
            lotteryRemainmoney.setText("余额:");
        } else {
            isShowMoney = true;
            iconRemainmoney.setVisibility(View.GONE);
            lotteryRemainmoney.setText("余额:" + allPoint);
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void Event(String messageEvent) {
        if (messageEvent.equals(REFRESH_DATA)) {
            if (System.currentTimeMillis() - curtime <= 3000) {
                curtime = System.currentTimeMillis();
                return;
            }
            curtime = System.currentTimeMillis();
            getLotterInfo();
        } else if (messageEvent.equals(UPDATE_INFO)) {
            loadUserInfo();
            getCathecticRecord();
        } else if (messageEvent.equals(CLEAR_LIST)) {
            betInfoList.clear();
            betInfos.clear();
            badgeView.setVisibility(View.GONE);
        } else if(messageEvent.equals(START_ANIMATION)){
            setStartAnimation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 房间彩种
     *
     * @param data
     * @param position
     * @param unit_type
     * @param badgeView
     * @param adapter
     * @param mGameNum
     */

    @Override
    public void onAddBetNum(List<BiliListInfo.ListBeans> data, int position, int unit_type, BadgeView badgeView, RecyclerView.Adapter adapter, String mGameNum, int bet_count) {
        this.badgeView = badgeView;
        if (data != null && data.size() > 0) {
            this.unit_type = unit_type;
            if (bet_type == 0) {
                onAddBetNumLottery(data, unit_type, bet_count);
            } else {
                BetInfo betInf = new BetInfo();
                StringBuffer buffer = new StringBuffer();
                StringBuffer bufferBetNum = new StringBuffer();
                BetInfo.DataBean betInfo1 = new BetInfo.DataBean();
                betInfoList.clear();
                for (BiliListInfo.ListBeans beans : data) {
                    if (beans.getList() != null) {
                        boolean isFist = true;
                        StringBuffer bufferNumbers = new StringBuffer();
                        for (BiliListInfo.ListBean bean : beans.getList()) {
                            if (bean.isFocus) {
                                if (isFist) {
                                    buffer.append(TextUtils.isEmpty(beans.getName()) ? "" : beans.getName());
                                    isFist = false;
                                }
                                inBetData(betInfo1, bean, buffer, beans, bufferBetNum, unit_type, bufferNumbers);
                            }
                        }
                    } else if (beans.getLists() != null) {
                        List<BiliListInfo.ListBean2> lists = beans.getLists();
                        if (lists != null && lists.size() > 0) {
                            for (BiliListInfo.ListBean2 listBean2 : lists) {
                                if (listBean2 != null) {
                                    boolean isFist = true;
                                    StringBuffer bufferNumbers = new StringBuffer();
                                    List<BiliListInfo.ListBean> listBean = listBean2.getList();
                                    for (BiliListInfo.ListBean bean : listBean) {
                                        if (bean.isFocus) {
                                            if (isFist) {
                                                buffer.append(TextUtils.isEmpty(beans.getName()) ? "" : beans.getName());
                                                isFist = false;
                                            }
                                            BetInfo.DataBean betInfo = new BetInfo.DataBean();
                                            inBetData(betInfo, bean, buffer, beans, bufferBetNum, unit_type, bufferNumbers);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (lotteryTitle.getText().toString().contains("不定胆")) {
                    betInfoList.add(betInfo1);
                    betInf.bet_num = betInfo1.bet_count;
                    if (!betInfo1.is_min_bet_count) {
                        ToastUtil.Toast(LotteryActivity.this, "最少选择" + betInfo1.min_bet_count + "个码！");
                        return;
                    }
                    if (betInfoList.size() > 0) {
                        betInf.setBet_dec(buffer.toString());
                        if (unit_type == 1) {
                            betInf.setBet_money("每注2元");
                            betInf.setBet_point(2);
                        } else {
                            betInf.setBet_money("每注0.2元");
                            betInf.setBet_point(0.2);
                        }
                        betInf.setWanfa_name(lotteryTitle.getText().toString());
                        betInf.setData(betInfoList);
                        betInfos.add(betInf);
                    }
                }else{
                    if (betInfoList.size() > 0) {
                        betInf.setBet_dec(buffer.toString());
                        if (unit_type == 1) {
                            betInf.setBet_money("每注2元");
                            betInf.setBet_point(2);
                        } else {
                            betInf.setBet_money("每注0.2元");
                            betInf.setBet_point(0.2);
                        }
                        betInf.bet_num = bet_count;
                        betInf.setWanfa_name(lotteryTitle.getText().toString());
                        betInf.setData(betInfoList);
                        betInfos.add(betInf);
                    }
                }
            }
            if (betInfos.size() > 0) {
                badgeView.setVisibility(View.VISIBLE);
                badgeView.setBadgeCount(betInfos.size());
            } else {
                badgeView.setVisibility(View.GONE);
            }
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 直投彩种
     *
     * @param data
     * @param unit_type
     * @param bet_count
     */
    private void onAddBetNumLottery(List<BiliListInfo.ListBeans> data, int unit_type, int bet_count) {
        BetInfo betInf = new BetInfo();
        StringBuffer buffer = new StringBuffer();
        StringBuffer bufferBetNum = new StringBuffer();
        BetInfo.DataBean betInfo = new BetInfo.DataBean();
        StringBuffer bufferNumbers = new StringBuffer();
        for (BiliListInfo.ListBeans beans : data) {
            if (beans.getList() != null) {
                boolean isFist = true;
                for (BiliListInfo.ListBean bean : beans.getList()) {
                    if (bean.isFocus) {
                        if (isFist) {
                            buffer.append(TextUtils.isEmpty(beans.getName()) ? "" : beans.getName());
                            isFist = false;
                        }
                        inBetData(betInfo, bean, buffer, beans, bufferBetNum, unit_type, bufferNumbers);
                        if (game_type == LotteryCons.ELEVENXFIVE) {
                            bufferNumbers.append(",");
                        }
                    }
                }
                if (game_type == LotteryCons.ELEVENXFIVE && titleindex == 5) {
                    bufferNumbers.delete(bufferNumbers.toString().length()-1,bufferNumbers.toString().length());
                    bufferNumbers.append("|");
                }
            }
            if (beans.getLists() != null) {
                List<BiliListInfo.ListBean2> lists = beans.getLists();
                if (lists != null && lists.size() > 0) {
                    for (BiliListInfo.ListBean2 listBean2 : lists) {
                        if (listBean2 != null) {
                            boolean isFist = true;
                            List<BiliListInfo.ListBean> listBean = listBean2.getList();
                            for (BiliListInfo.ListBean bean : listBean) {
                                if (bean.isFocus) {
                                    if (isFist) {
                                        if(game_type == LotteryCons.ELEVENXFIVE || game_type == LotteryCons.FENFENCAI || game_type == LotteryCons.YDWFC){
                                            if (lotteryTitle.getText().toString().contains("不定胆")) {
                                                buffer.append(TextUtils.isEmpty(beans.getName()) ? "" : beans.getName());
                                            }else{
                                                buffer.append(TextUtils.isEmpty(listBean2.getName()) ? "" : listBean2.getName());
                                            }
                                        }else{
                                            buffer.append(TextUtils.isEmpty(beans.getName()) ? "" : beans.getName());
                                        }
                                        isFist = false;
                                    }
                                    inBetData(betInfo, bean, buffer, beans, bufferBetNum, unit_type, bufferNumbers);
                                    if (game_type == LotteryCons.ELEVENXFIVE || (game_type == LotteryCons.FENFENCAI && !TextUtils.isEmpty(lotteryTitle.getText().toString()) && (lotteryTitle.getText().toString().contains("大小单双总和") || lotteryTitle.getText().toString().contains("和值") || lotteryTitle.getText().toString().contains("猜豹子") || lotteryTitle.getText().toString().contains("龙虎"))) || (game_type == LotteryCons.YDWFC && !TextUtils.isEmpty(lotteryTitle.getText().toString()) && (lotteryTitle.getText().toString().contains("大小单双总和") || lotteryTitle.getText().toString().contains("和值") || lotteryTitle.getText().toString().contains("猜豹子") || lotteryTitle.getText().toString().contains("龙虎")))) {
                                        bufferNumbers.append(",");
                                    }
                                }
                            }
                            if (!TextUtils.isEmpty(bufferNumbers.toString())) {
                                if (game_type == LotteryCons.FENFENCAI || game_type == LotteryCons.YDWFC) {
                                    bufferNumbers.append(",");
                                } else if (game_type == LotteryCons.ELEVENXFIVE) {
                                    bufferNumbers.delete(bufferNumbers.toString().length()-1,bufferNumbers.toString().length());
                                    bufferNumbers.append("|");
                                }
                            }
                        }
                    }
                }
                if (!TextUtils.isEmpty(bufferNumbers.toString())) {
                    if (game_type == LotteryCons.FENFENCAI || game_type == LotteryCons.YDWFC) {
                        bufferNumbers.append(",");
                    }
                }
            }
            if (!TextUtils.isEmpty(bufferNumbers.toString())) {
                if (game_type != LotteryCons.ELEVENXFIVE){
                    bufferNumbers.append(",");
                }
            }
        }
        betInfo.bet_count = bet_count;
        betInfoList.add(betInfo);
        if (betInfoList.size() > 0) {
            betInf.setBet_dec(buffer.toString());
            if (unit_type == 1) {
                betInf.setBet_money("每注2元");
                betInf.setBet_point(2);
            } else {
                betInf.setBet_money("每注0.2元");
                betInf.setBet_point(0.2);
            }
            betInf.bet_num = bet_count;
            betInf.setWanfa_name(lotteryTitle.getText().toString());
            betInf.getData().add(betInfo);
            betInfos.add(betInf);
        }
    }


    /**
     * 六合彩
     *
     * @param data
     * @param mPosition
     * @param badgeView
     * @param adapter
     * @param mGameNum
     */
    @Override
    public void onAddLhcBetNum(List<LhcAllWanFaInfo.DataBean.ListsBean> data, int mPosition, BadgeView badgeView, RecyclerView.Adapter adapter, String mGameNum, int bet_count, int point) {
        BetInfo betInf = new BetInfo();
        StringBuffer buffer = new StringBuffer();
        StringBuffer bufferNumbers = new StringBuffer();
        BetInfo.DataBean betInfo = new BetInfo.DataBean();
        betInfoList.clear();
        boolean isFist = true;
        this.lhcPoint = point;
        for (LhcAllWanFaInfo.DataBean.ListsBean bean : data) {
            for (LhcAllWanFaInfo.DataBean.ListsBean.ListBean listBean : bean.getList()) {
                if (isFist && (listBean.getWanfa_parentname().contains("连码") || listBean.getWanfa_parentname().contains("合肖") || listBean.getWanfa_parentname().contains("生肖连") || listBean.getWanfa_parentname().contains("尾数连") || listBean.getWanfa_parentname().contains("全不中"))) {
                    buffer.append(TextUtils.isEmpty(listBean.getRemarks()) ? "" : listBean.getRemarks());
                    isFist = false;
                }
                initLhcBetData(betInfo, listBean, buffer, bufferNumbers, point);
            }
        }
        if (data.get(0).getList().get(0).getWanfa_parentname().contains("连码") || data.get(0).getList().get(0).getWanfa_parentname().contains("合肖") || data.get(0).getList().get(0).getWanfa_parentname().contains("生肖连") || data.get(0).getList().get(0).getWanfa_parentname().contains("尾数连") || data.get(0).getList().get(0).getWanfa_parentname().contains("全不中")) {
            betInfo.betcount = bet_count;
            betInfo.isbetcount = 1;
            betInfo.numbers = bufferNumbers.toString().substring(0, bufferNumbers.toString().length() - 1);
            betInfoList.add(betInfo);
            if (betInfoList.size() > 0) {
                betInf.setBet_dec(buffer.toString());
                betInf.setBet_money("每注" + point + "元");
                betInf.setBet_point(point);
                betInf.bet_num = bet_count;
                betInf.setWanfa_name(lotteryTitle.getText().toString());
                betInf.getData().add(betInfo);
                betInfos.add(betInf);
            }
        }
        if (betInfos.size() > 0) {
            badgeView.setVisibility(View.VISIBLE);
            badgeView.setBadgeCount(betInfos.size());
        } else {
            badgeView.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }


    public void inBetData(BetInfo.DataBean betInfo1, BiliListInfo.ListBean bean, StringBuffer buffer, BiliListInfo.ListBeans beans, StringBuffer bufferBetNum, int unit_type, StringBuffer bufferNumbers) {
        if (bean.isFocus) {
            BetInfo.DataBean betInfo = new BetInfo.DataBean();
            bufferNumbers.append(bean.getBili_name());
            betInfo.bet_count = 1;
            betInfo1.bet_count = 1;
            if (bet_type == 1) {
                betInfo.bili_id = bean.getId() == 0 ? beans.getWanfa_id() : bean.getId();
                betInfo1.bili_id = bean.getId() == 0 ? beans.getWanfa_id() : bean.getId();
            } else {
                betInfo.wanfa_id = bean.getId() == 0 ? beans.getWanfa_id() : bean.getId();
                betInfo1.wanfa_id = bean.getId() == 0 ? beans.getWanfa_id() : bean.getId();
            }
            if (bet_type == 1) {
                betInfo.point = unit_type == 1 ? 2 : 0.2;
                betInfo1.point = unit_type == 1 ? 2 : 0.2;
            } else {
                betInfo.money = unit_type == 1 ? 2 : 0.2;
                betInfo.totalMoney = unit_type == 1 ? 2 : 0.2;
                betInfo.numbers = bufferNumbers.toString();
                betInfo1.money = unit_type == 1 ? 2 : 0.2;
                betInfo1.totalMoney = unit_type == 1 ? 2 : 0.2;
                betInfo1.numbers = bufferNumbers.toString();
            }
            betInfo.multiple_count = 1;
            betInfo.unit_type = unit_type;
            betInfo.wanfa_type = lotteryTitle.getText().toString();
            betInfo1.multiple_count = 1;
            betInfo1.unit_type = unit_type;
            betInfo1.wanfa_type = lotteryTitle.getText().toString();
            if (bet_type == 1 && lotteryTitle.getText().toString().contains("不定胆")) {
                bufferBetNum.append(bean.getBili_name()).append(",");
                String[] strings = bufferBetNum.toString().split(",");
                betInfo1.bet_count = C(beans.getSomeNum(), strings.length);
                betInfo1.min_bet_count = beans.getSomeNum();
                betInfo1.is_min_bet_count = strings.length >= beans.getSomeNum() ? true : false;
                betInfo1.betNumbers = bufferBetNum.toString().substring(0,bufferBetNum.toString().length()-1);
            } else if (bet_type == 1) {
                betInfoList.add(betInfo);
            }
            bean.setFocus(false);
            buffer.append("_").append(TextUtils.isEmpty(bean.getBili_name()) ? "" : bean.getBili_name()).append(";");
        }
    }

    public void initLhcBetData(BetInfo.DataBean betInfo, LhcAllWanFaInfo.DataBean.ListsBean.ListBean bean, StringBuffer buffer, StringBuffer bufferNumbers, int point) {
        if (bean.isFocus()) {
            BetInfo betInf = new BetInfo();
            BetInfo.DataBean betInfo1 = new BetInfo.DataBean();
            bufferNumbers.append(bean.getWanfa_name()).append(",");
            betInfo.wanfa_id = bean.getId();
            betInfo.money = point;
            betInfo.wanfa_type = bean.getWanfa_parentname() + "," + bean.getRemarks();
            betInfo1.wanfa_id = bean.getId();
            betInfo1.money = point;
            betInfo1.wanfa_type = bean.getWanfa_parentname() + "," + bean.getRemarks();
            bean.setFocus(false);
            if (bean.getWanfa_parentname().contains("特码") || bean.getWanfa_parentname().contains("正码") || bean.getWanfa_parentname().contains("正码特") || bean.getWanfa_parentname().equals("生肖") || bean.getWanfa_parentname().equals("一肖") || bean.getWanfa_parentname().contains("半波") || bean.getWanfa_parentname().contains("特码生肖")) {
                buffer.append(TextUtils.isEmpty(bean.getWanfa_name()) ? "" : bean.getWanfa_name()).append(";");
                betInfo1.isbetcount = 0;
                betInfo1.wanfa_name = bean.getWanfa_name();
                betInfoList.add(betInfo1);
                if (betInfoList.size() > 0) {
                    betInf.setBet_dec(buffer.toString());
                    betInf.setBet_money("每注" + point + "元");
                    betInf.setBet_point(point);
                    betInf.bet_num = 1;
                    betInf.setWanfa_name(lotteryTitle.getText().toString());
                    betInf.getData().add(betInfo1);
                    betInfos.add(betInf);
                    buffer.delete(0, buffer.toString().length());
                }
            } else {
                buffer.append("_").append(TextUtils.isEmpty(bean.getWanfa_name()) ? "" : bean.getWanfa_name()).append(";");
            }
        }
    }


    @Override
    public void onConfirmBet(String mGameNum, BadgeView badgeView, List<BiliListInfo.ListBeans> ballHm, List<LhcAllWanFaInfo.DataBean.ListsBean> lhcballHm) {
        this.badgeView = badgeView;
        if (betInfos.size() > 0) {
            if (game_type == LotteryCons.SIXHECAI) {
                LotteryCathecticActivity.startLhcActivity(LotteryActivity.this, betInfos, game_type, mTitle, lhcwanfalist.get(titleindex), lhcballHm, bet_type, titleindex, tabindex, lhcPoint);
            } else {
                LotteryCathecticActivity.startActivity(LotteryActivity.this, betInfos, game_type, mBaseWanfaId, mTitle, bililist.get(titleindex), ballHm, bet_type, unit_type, titleindex, tabindex);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            List<BetInfo> dataList = (List<BetInfo>) data.getSerializableExtra(LotteryCathecticActivity.DATA);
            betInfos.clear();
            betInfoList.clear();
            if (dataList != null && dataList.size() > 0) {
                betInfos.addAll(dataList);
            }
            if (betInfos.size() > 0) {
                badgeView.setBadgeCount(betInfos.size());
                badgeView.setVisibility(View.VISIBLE);
            } else {
                badgeView.setVisibility(View.GONE);
            }
        }
        if (resultCode == 10001) {
            loadUserInfo();
            getCathecticRecord();
        }
    }


}

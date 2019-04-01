package com.sp.caitwo.asynctask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sp.caitwo.MainActivity;
import com.sp.caitwo.base.Constants;
import com.sp.caitwo.bean.ActivityCenterBean;
import com.sp.caitwo.bean.BetBonusBean;
import com.sp.caitwo.bean.BetRecordBean;
import com.sp.caitwo.bean.CathecticInfo;
import com.sp.caitwo.bean.CustomServerAddrBean;
import com.sp.caitwo.bean.DailyBonusBean;
import com.sp.caitwo.bean.InviteCodeListBean;
import com.sp.caitwo.bean.LongChoiceListInfo;
import com.sp.caitwo.bean.MessageBean;
import com.sp.caitwo.bean.NewWinPrizeBean;
import com.sp.caitwo.bean.NotReadMsgBean;
import com.sp.caitwo.bean.PayListBean;
import com.sp.caitwo.bean.SubAccountDetailBean;
import com.sp.caitwo.bean.SubRechargeRecordDetailBean;
import com.sp.caitwo.bean.SubWithdrawRecordBean;
import com.sp.caitwo.bean.SubordinateBetDetailBean;
import com.sp.caitwo.bean.SubordinateManageListBean;
import com.sp.caitwo.bean.SystemConfigBean;
import com.sp.caitwo.bean.SystemHeadPortraitBean;
import com.sp.caitwo.bean.TraceNumListBean;
import com.sp.caitwo.bean.UpdateApkBean;
import com.sp.caitwo.bean.UserDetailBean;
import com.sp.caitwo.bean.UserLevelListBean;
import com.sp.caitwo.bean.WithdrawInfoBean;
import com.sp.caitwo.info.SeboNumberInfo;
import com.sp.caitwo.ui.activity.agentcenter.SubordinateManageActivity;
import com.sp.caitwo.ui.activity.sysm.WebLoadActivity;
import com.sp.caitwo.ui.activity.transferaccounts.CompanyIncomeActivity;
import com.sp.caitwo.ui.activity.transferaccounts.RechargePayActivity;
import com.sp.caitwo.ui.info.LotteryKJLogInfo;
import com.sp.caitwo.ui.info.ProfitLossInfo;
import com.sp.xwjlibrary.dialog.LoadDialog;
import com.sp.xwjlibrary.dialog.SimpleDialog;
import com.sp.xwjlibrary.dialog.ViewDialog;
import com.sp.xwjlibrary.myinterface.OnDialogButtonListener;
import com.sp.xwjlibrary.util.BaseUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp.caitwo.App;
import com.sp.caitwo.R;
import com.sp.caitwo.bean.BannerBean;
import com.sp.caitwo.bean.CathecticRecordInfo;
import com.sp.caitwo.bean.HomeWanFaBean;
import com.sp.caitwo.bean.PersonalAgentInfoBean;
import com.sp.caitwo.bean.PersonalSecurityQuestionBean;
import com.sp.caitwo.bean.ReturnCommissionDetailBean;
import com.sp.caitwo.bean.SecurityQuestionBean;
import com.sp.caitwo.bean.SubordinateReportFormsBean;
import com.sp.caitwo.bean.TeamReportFormsBean;
import com.sp.caitwo.bean.UserBankList;
import com.sp.caitwo.bean.UserInfoBean;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.caitwo.info.LhcAllWanFaInfo;
import com.sp.caitwo.info.LhcLotteryOrderInfo;
import com.sp.caitwo.info.LhcWinsRecordInfo;
import com.sp.caitwo.info.LotterOrderInfo;
import com.sp.caitwo.info.RoomOpenInfo;
import com.sp.caitwo.info.ShengXiaoNumInfo;
import com.sp.caitwo.ui.activity.securitycenter.AddBankCardActivity;
import com.sp.caitwo.ui.activity.securitycenter.ChangePswActivity;
import com.sp.caitwo.ui.activity.securitycenter.SetSecurityPswActivity;
import com.sp.caitwo.ui.activity.securitycenter.SetSecurityQuestionActivity;
import com.sp.caitwo.ui.activity.sysm.LoginActivity;
import com.sp.caitwo.ui.activity.sysm.RegisterActivity;
import com.sp.xwjlibrary.util.ACache;
import com.sp.xwjlibrary.util.FileUtil;
import com.sp.xwjlibrary.util.JSONUtil;
import com.sp.xwjlibrary.util.MD5Util;
import com.sp.xwjlibrary.util.ToastUtil;
import com.sp.xwjlibrary.util.UpdateUtil;

import org.xutils.XCallback;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.net.SocketTimeoutException;
import java.util.HashMap;

/**
 * Created by vinson on 2018/11/12.
 */

public class InterfaceTask {

    private InterfaceTask() {}
    private static InterfaceTask instance;
    public static InterfaceTask getInstance() {
        if (instance == null) {
            instance = new InterfaceTask();
        }
        return instance;
    }
    private LoadDialog mLoadDialog = null;

    public static abstract class OnInterfaceTask {
        public void onError() {}
        public void onSuccess(Object obj) {}
    }

    /**
     * 获取首页广告栏信息
     */
    public void getBanner(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.banner);
        rp.addQueryStringParameter("place", "1");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                BannerBean json = JSONUtil.parseJSON(s, BannerBean.class);
                if (json.getResult_code() == 0) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取全部彩种
     */
    public void getHomeWanFa(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.homeWanFa);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HomeWanFaBean homeWanFaBean = JSONUtil.parseJSON(s, HomeWanFaBean.class);
                if (BaseUtil.equals(homeWanFaBean.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(homeWanFaBean);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 房间彩种玩法
     */
    public void getBiliList(int gameType, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.roomBiliList);
        rp.addQueryStringParameter("game_type", gameType + "");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                BiliListInfo biliListInfo = JSONUtil.parseJSON(s, BiliListInfo.class);
                if (BaseUtil.equals(biliListInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(biliListInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 直投彩种玩法
     */
    public void getWanfaList(int baseWanfaId, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.wanfaBiliList);
        rp.addQueryStringParameter("baseWanfaId", baseWanfaId + "");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                BiliListInfo biliListInfo = JSONUtil.parseJSON(s, BiliListInfo.class);
                if (BaseUtil.equals(biliListInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(biliListInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 房间投注
     */
    public void getRoomPointAdd(final Context context, String choice_no, String betStr, int game_type, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.roomPointAdd);
        rp.addBodyParameter("choice_no", choice_no);
        rp.addBodyParameter("betStr", betStr);
        rp.addBodyParameter("game_type", game_type + "");
        rp.setConnectTimeout(60000);
        x.http().post(rp, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (json != null) {
                    if (BaseUtil.equals((CharSequence) json.get("result_code"), "0")) {
                        ToastUtil.Toast(context, context.getString(R.string.touzhu_success));
                        onInterfaceTask.onSuccess(s);
                    } else {
                        ToastUtil.Toast(context, (String) json.get("result_desc"));
                        onInterfaceTask.onError();
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 直投投注
     */
    public void getLotterySubBet(final Context context, String choice_no, String betarr,int baseWanFaID, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.lotteryOrderSubBet);
        rp.addBodyParameter("betarr", betarr);
        rp.addBodyParameter("qihao", choice_no);
        rp.addBodyParameter("baseWanFaID", baseWanFaID + "");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (json != null) {
                    if (BaseUtil.equals((CharSequence) json.get("result_code"), "0")) {
                        ToastUtil.Toast(context, context.getString(R.string.touzhu_success));
                        onInterfaceTask.onSuccess(s);
                    } else {
                        ToastUtil.Toast(context, (String) json.get("result_desc"));
                        onInterfaceTask.onError();
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 房间开奖记录及倒计时
     */
    public void getRoomOpenInfo(int game_type, final OnInterfaceTask onInterfaceTask) {
        String userId = App.userInfoBean.getData().getId();
        XRequestParams rp = new XRequestParams(InterfaceAddr.roomOpenInfo);
        rp.addQueryStringParameter("game_type", game_type + "");
        rp.addQueryStringParameter("user_id", userId + "");
        rp.addQueryStringParameter("room_id", "1");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                RoomOpenInfo roomOpenInfo = JSONUtil.parseJSON(s, RoomOpenInfo.class);
                if (BaseUtil.equals(roomOpenInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(roomOpenInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 直投开奖记录及倒计时
     */
    public void getLotteryOrderInfo(int baseWanfaId, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.lotteryOrderInfo);
        rp.addQueryStringParameter("baseWanFaID", baseWanfaId + "");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                LotterOrderInfo lotterOrderInfo = JSONUtil.parseJSON(s, LotterOrderInfo.class);
                if (BaseUtil.equals(lotterOrderInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(lotterOrderInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 直投其他彩种历史开奖列表
     */
    public void getLotteryKjLog(int baseWanfaId, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.lotteryKjLog);
        rp.addQueryStringParameter("baseWanFaID", baseWanfaId + "");
        rp.addQueryStringParameter("page", "1");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                LotteryKJLogInfo lotteryKJLogInfo = JSONUtil.parseJSON(s, LotteryKJLogInfo.class);
                if (BaseUtil.equals(lotteryKJLogInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(lotteryKJLogInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取用户投注记录
     * 房间六合彩获取下注参数
     */
    public void getlhcAllWanFaList(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.lhcAllWanFaList);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                LhcAllWanFaInfo lhcAllWanFaInfo = JSONUtil.parseJSON(s, LhcAllWanFaInfo.class);
                if (BaseUtil.equals(lhcAllWanFaInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(lhcAllWanFaInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 六合彩开奖倒计时与最新开奖记录
     */
    public void getlhcLotteryOrderInfo(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.lhcLotteryOrderInfo);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                LhcLotteryOrderInfo lhcLotteryOrderInfo = JSONUtil.parseJSON(s, LhcLotteryOrderInfo.class);
                if (BaseUtil.equals(lhcLotteryOrderInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(lhcLotteryOrderInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 根据生肖名称返回生肖号码
     */
    public void getShengxiaoNum(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.lhcGetShengXiaoNum);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ShengXiaoNumInfo shengXiaoNumInfo = JSONUtil.parseJSON(s, ShengXiaoNumInfo.class);
                if (BaseUtil.equals(shengXiaoNumInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(shengXiaoNumInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 六合彩历史开奖记录
     */
    public void lhcGetWinsRecord(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.lhcGetWinsRecord);
        rp.addQueryStringParameter("page", "1");
        rp.addQueryStringParameter("pageSize", "20");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                LhcWinsRecordInfo lhcWinsRecordInfo = JSONUtil.parseJSON(s, LhcWinsRecordInfo.class);
                if (BaseUtil.equals(lhcWinsRecordInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(lhcWinsRecordInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 六合彩下注
     */
    public void lhcgetSubBet(final Context context, String qihao, String betarr, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.lhcSubBet);
        rp.addQueryStringParameter("qihao", qihao);
        rp.addQueryStringParameter("betarr", betarr);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (json != null) {
                    if (BaseUtil.equals((CharSequence) json.get("result_code"), "0")) {
                        ToastUtil.Toast(context, context.getString(R.string.touzhu_success));
                        onInterfaceTask.onSuccess(s);
                    } else {
                        ToastUtil.Toast(context, (String) json.get("result_desc"));
                        onInterfaceTask.onError();
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 六合彩色波
     */
    public void lhcgetSeboNumber(final Context context, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.lhcSeboNumber);
        rp.addQueryStringParameter("baseWanFaId", "1");
        rp.addQueryStringParameter("type", "1");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                SeboNumberInfo seboNumberInfo = JSONUtil.parseJSON(s, SeboNumberInfo.class);
                if (BaseUtil.equals(seboNumberInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(seboNumberInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取投注记录
     */
    public void getBetRecordList(int game_type, int status_type, int page_no, int page_size, String start_time, String end_time, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.getUserBetRecord);
        rp.addQueryStringParameter("game_type", game_type + "");
        rp.addQueryStringParameter("status_type", status_type + "");
        rp.addQueryStringParameter("page_no", page_no + "");
        rp.addQueryStringParameter("page_size", page_size + "");
        if (!TextUtils.isEmpty(start_time) && !TextUtils.isEmpty(end_time)) {
            rp.addQueryStringParameter("start_time", start_time);
            rp.addQueryStringParameter("end_time", end_time);
        }
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                CathecticRecordInfo cathecticRecordInfo = JSONUtil.parseJSON(s, CathecticRecordInfo.class);
                if (BaseUtil.equals(cathecticRecordInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(cathecticRecordInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void getDragonAddChasing(final Context context, int game_type, String choice_no, int times, int multiple, String zjjt, String betStr, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.dragonAddChasing);
        rp.addQueryStringParameter("user_id", App.userInfoBean.getData().getId()+ "");
        rp.addQueryStringParameter("game_type", game_type + "");
        rp.addQueryStringParameter("choice_no", choice_no);
        rp.addQueryStringParameter("times", times + "");
        rp.addQueryStringParameter("multiple", multiple + "");
        rp.addQueryStringParameter("zjjt", zjjt);
        rp.addQueryStringParameter("betStr", betStr);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (json != null) {
                    if (BaseUtil.equals((CharSequence) json.get("result_code"), "0")) {
                        ToastUtil.Toast(context, context.getString(R.string.touzhu_success));
                        onInterfaceTask.onSuccess(s);
                    } else {
                        ToastUtil.Toast(context, (String) json.get("result_desc"));
                        onInterfaceTask.onError();
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 长龙投注记录
     *
     * */
    public void getDragonChoiceLogList(final Context context,final OnInterfaceTask onInterfaceTask){
        XRequestParams rp = new XRequestParams(InterfaceAddr.dragonAddChasing);
        rp.addQueryStringParameter("page_no", "1");
        rp.addQueryStringParameter("page_size", "20");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                LongChoiceListInfo longChoiceListInfo = JSONUtil.parseJSON(s, LongChoiceListInfo.class);
                if (BaseUtil.equals(longChoiceListInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(longChoiceListInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 登录
     */
    public void login(final LoginActivity activity, final String account, String psw) {
        mLoadDialog = new LoadDialog(activity,"登录中,请稍等...", Constants.drawableResids);
        mLoadDialog.show();
        for (int i = 0; i < 3; i++) {
            psw = MD5Util.getMd5(psw);
        }
        XRequestParams rp = new XRequestParams(InterfaceAddr.login);
        rp.addBodyParameter("account", account);
        rp.addBodyParameter("password", psw);
        rp.addBodyParameter("registration_id", "1");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                mLoadDialog.cancel();
                UserInfoBean json = JSONUtil.parseJSON(s, UserInfoBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    App.userInfoBean = json;
                    ACache.get(activity).put("UserInfo", json);
                    ToastUtil.Toast(activity, activity.getString(R.string.login_success));
                    if (BaseUtil.equals(activity.getIntent().getStringExtra("Class"), "SettingActivity"))
                        activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.setResult(Activity.RESULT_OK);
                    activity.finish();
                } else {
                    ToastUtil.Toast(activity, json.getResult_desc());
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                mLoadDialog.cancel();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 修改登录密码
     */
    public void changeLoginPsw(final Activity activity, String newPsw, String oldPsw) {
        for (int i = 0; i < 3; i++) {
            newPsw = MD5Util.getMd5(newPsw);
            oldPsw = MD5Util.getMd5(oldPsw);
        }
        XRequestParams params = new XRequestParams(InterfaceAddr.changeUserInfo);
        params.addBodyParameter("user_id", App.userInfoBean.getData().getId());
        params.addBodyParameter("password", newPsw);
        params.addBodyParameter("old_password", oldPsw);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    ToastUtil.Toast(activity, "修改成功");
                    activity.finish();
                } else {
                    ToastUtil.Toast(activity, "原密码错误");
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 找回密码
     */
    public void getBackPsw(final Activity activity, String securityCode, String newPsw,
                           String account, String securityPsw, String type) {
        for (int i = 0; i < 3; i++) {
            newPsw = MD5Util.getMd5(newPsw);
        }
        XRequestParams params = new XRequestParams(InterfaceAddr.getBackPsw);
        params.addQueryStringParameter("securityCode", securityCode);
        params.addQueryStringParameter("newPassword", newPsw);
        params.addQueryStringParameter("account", account);
        params.addQueryStringParameter("withdrawalsPassword", securityPsw);
        params.addQueryStringParameter("type", type);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    ToastUtil.Toast(activity, "修改成功");
                    activity.finish();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 注册
     */
    public void register(final RegisterActivity activity, String account, String psw, String inviteCode) {
        mLoadDialog = new LoadDialog(activity,"注册中,请稍等...", Constants.drawableResids);
        mLoadDialog.show();
        for (int i = 0; i < 3; i++) {
            psw = MD5Util.getMd5(psw);
        }
        XRequestParams rp = new XRequestParams(InterfaceAddr.register);
        rp.addBodyParameter("account", account);
        rp.addBodyParameter("password", psw);
        rp.addBodyParameter("code", inviteCode);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                mLoadDialog.cancel();
                final HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (json != null) {
                    if (BaseUtil.equals((CharSequence) json.get("result_code"), "0")) {
                        ToastUtil.Toast(activity, activity.getString(R.string.register_success));
                        activity.finish();
                    } else {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.Toast(activity, (String) json.get("result_desc"));
                            }
                        });
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                mLoadDialog.cancel();
                if (throwable instanceof SocketTimeoutException) {
                    ToastUtil.Toast(activity, "请求超时");
                }
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取用户银行卡列表
     */
    public void getUserBankList(final Activity activity, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.userBankList);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                UserBankList json = JSONUtil.parseJSON(s, UserBankList.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(json);
                } else {
                    activity.startActivity(new Intent(activity, LoginActivity.class));
                    activity.finish();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 添加用户绑定银行卡
     */
    public void addUserBank(final AddBankCardActivity activity, String openerName, String bankName,
                            String OpenerAddr, String bankNum, String securityPsw) {
        for (int i = 0; i < 3; i++) {
            securityPsw = MD5Util.getMd5(securityPsw);
        }
        XRequestParams params = new XRequestParams(InterfaceAddr.addUserBank);
        params.addBodyParameter("account", App.userInfoBean.getData().getAccount());
        params.addBodyParameter("realName", openerName);
        params.addBodyParameter("bankName", bankName);
        params.addBodyParameter("openCardAddress", OpenerAddr);
        params.addBodyParameter("bankNo", bankNum);
        params.addBodyParameter("withdrawalsPassword", securityPsw);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "SUCCESS")) {
                    ToastUtil.Toast(activity, "添加成功");
                    activity.setResult(Activity.RESULT_OK);
                    activity.finish();
                } else {
                    ToastUtil.Toast(activity, String.valueOf(json.get("result_desc")));
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 修改用户银行卡
     */
    public void changeUserBank(final AddBankCardActivity activity, String bankId, String openerName,
                               String bankName, String OpenerAddr, String bankNum, String securityPsw) {
        for (int i = 0; i < 3; i++) {
            securityPsw = MD5Util.getMd5(securityPsw);
        }
        XRequestParams params = new XRequestParams(InterfaceAddr.changeUserBank);
        params.addBodyParameter("id", bankId);
        params.addBodyParameter("realName", openerName);
        params.addBodyParameter("bankName", bankName);
        params.addBodyParameter("openCardAddress", OpenerAddr);
        params.addBodyParameter("bankNo", bankNum);
        params.addBodyParameter("withdrawalsPassword", securityPsw);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "SUCCESS")) {
                    ToastUtil.Toast(activity, "修改成功");
                    activity.setResult(Activity.RESULT_OK);
                    activity.finish();
                } else {
                    ToastUtil.Toast(activity, String.valueOf(json.get("result_desc")));
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 验证安全密码
     */
    public void checkSecurityPsw(final Activity activity, final String account, String securityPsw) {
        for (int i = 0; i < 3; i++) {
            securityPsw = MD5Util.getMd5(securityPsw);
        }
        final String spsw = securityPsw;
        XRequestParams params = new XRequestParams(InterfaceAddr.checkSecurityPsw);
        params.addQueryStringParameter("withdrawalsPassword", securityPsw);
        params.addQueryStringParameter("account", account);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                String aClass = activity.getIntent().getStringExtra("Class");
                if (BaseUtil.equals(String.valueOf(json.get("data")), "true")) {
                    if (BaseUtil.equals(aClass, "getBackPsw")) {
                        Intent intent = new Intent(activity, ChangePswActivity.class);
                        intent.putExtra("securityPsw", spsw);
                        intent.putExtra("account", account);
                        activity.startActivity(intent);
                        activity.finish();
                    } else if (BaseUtil.equals(aClass, "set")) {
                        Intent intent = new Intent(activity, SetSecurityPswActivity.class);
                        intent.putExtra("account", account);
                        intent.putExtra("oldSecurityPsw", spsw);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                } else {
                    ToastUtil.Toast(activity, "安全密码错误");
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 设置安全密码
     */
    public void setSecurityPsw(final Activity activity, String securityPsw) {
        for (int i = 0; i < 3; i++) {
            securityPsw = MD5Util.getMd5(securityPsw);
        }
        //验证安全密码时加密后传过来,无需加密,为空表示初始设置
        final String oldSecurityPsw = activity.getIntent().getStringExtra("oldSecurityPsw");
        XRequestParams params = new XRequestParams(InterfaceAddr.changeUserInfo);
        params.addBodyParameter("user_id", App.userInfoBean.getData().getId());
        params.addBodyParameter("withdrawals_password", securityPsw);
        if (!BaseUtil.isEmpty(oldSecurityPsw)) {
            params.addBodyParameter("old_password", oldSecurityPsw);
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    App.userInfoBean.getData().setWithdrawals_password("xxxx");
                    if (BaseUtil.isEmpty(oldSecurityPsw)) {
                        App.userInfoBean.getData().setSecurity_level(String.valueOf(Integer.valueOf(App.userInfoBean.getData().getSecurity_level()) + 1));
                    }
                    ToastUtil.Toast(activity, "设置成功");
                    activity.finish();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 找回安全密码
     */
    public void getBackSecurityPsw(final Activity activity, String securityCode, String account, String psw) {
        for (int i = 0; i < 3; i++) {
            psw = MD5Util.getMd5(psw);
        }
        XRequestParams params = new XRequestParams(InterfaceAddr.getBackSecurityPsw);
        params.addQueryStringParameter("securityCode", securityCode);
        params.addQueryStringParameter("account", account);
        params.addQueryStringParameter("newPassword", psw);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    ToastUtil.Toast(activity, "修改成功");
                    activity.finish();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取密保问题列表
     */
    public void getSecurityQuestionList(final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getSecurityQuestionList);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                SecurityQuestionBean json = JSONUtil.parseJSON(s, SecurityQuestionBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 设置密保问题
     */
    public void setSecurityQuestion(final Activity activity, int q1Id, int q2Id, int q3Id, String answer1, String answer2, String answer3) {
        XRequestParams params = new XRequestParams(InterfaceAddr.setSecurityQuestion);
        params.addQueryStringParameter("questionId1", String.valueOf(q1Id));
        params.addQueryStringParameter("questionId2", String.valueOf(q2Id));
        params.addQueryStringParameter("questionId3", String.valueOf(q3Id));
        params.addQueryStringParameter("answer1", answer1);
        params.addQueryStringParameter("answer2", answer2);
        params.addQueryStringParameter("answer3", answer3);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    App.userInfoBean.getData().setIs_set_security_answer("true");
                    App.userInfoBean.getData().setSecurity_level(String.valueOf(Integer.valueOf(App.userInfoBean.getData().getSecurity_level()) + 1));
                    ToastUtil.Toast(activity, "设置成功");
                    activity.finish();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 验证密保问题
     */
    public void checkSecurityQuestion(final Activity activity,String account, int q1Id, int q2Id, String answer1, String answer2) {
        XRequestParams params = new XRequestParams(InterfaceAddr.checkSecurityQuestion);
        params.addQueryStringParameter("account",account);
        params.addQueryStringParameter("questionId1", String.valueOf(q1Id));
        params.addQueryStringParameter("questionId2", String.valueOf(q2Id));
        params.addQueryStringParameter("answer1", answer1);
        params.addQueryStringParameter("answer2", answer2);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("result_code")), "0")) {
                    String aClass = activity.getIntent().getStringExtra("Class");
                    if (BaseUtil.equals(aClass, "set")) {
                        Intent intent = new Intent(activity, SetSecurityQuestionActivity.class);
                        intent.putExtra("securityCode", String.valueOf(json.get("data")));
                        activity.startActivity(intent);
                    } else if (BaseUtil.equals(aClass, "getBackPsw")) {
                        Intent intent = new Intent(activity, ChangePswActivity.class);
                        intent.putExtra("securityCode", String.valueOf(json.get("data")));
                        intent.putExtra("account", activity.getIntent().getStringExtra("account"));
                        activity.startActivity(intent);
                    } else if (BaseUtil.equals(aClass, "forgetPsw")) {
                        Intent intent = new Intent(activity, SetSecurityPswActivity.class);
                        intent.putExtra("securityCode", String.valueOf(json.get("data")));
                        intent.putExtra("account", activity.getIntent().getStringExtra("account"));
                        activity.startActivity(intent);
                    }
                    activity.finish();
                } else {
                    ToastUtil.Toast(activity,String.valueOf(json.get("result_desc")));
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 修改密保问题
     */
    public void changeSecurityQuestion(final Activity activity, int q1Id, int q2Id, int q3Id, String answer1, String answer2, String answer3) {
        XRequestParams params = new XRequestParams(InterfaceAddr.changeSecurityQuestion);
        params.addQueryStringParameter("questionId1", String.valueOf(q1Id));
        params.addQueryStringParameter("questionId2", String.valueOf(q2Id));
        params.addQueryStringParameter("questionId3", String.valueOf(q3Id));
        params.addQueryStringParameter("answer1", answer1);
        params.addQueryStringParameter("answer2", answer2);
        params.addQueryStringParameter("answer3", answer3);
        params.addQueryStringParameter("securityCode", activity.getIntent().getStringExtra("securityCode"));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    ToastUtil.Toast(activity, "修改成功");
                    activity.finish();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取个人密保问题
     */
    public void getPersonalSecurityQuestion(final Activity activity,String account,final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getPersonalSecurityQuestion);
        params.addQueryStringParameter("account",account);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                PersonalSecurityQuestionBean json = JSONUtil.parseJSON(s, PersonalSecurityQuestionBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }else {
                    ToastUtil.Toast(activity,json.getResult_desc());
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取个人代理信息
     */
    public void getPersonalAgentInfo(final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.personalAgentInfo);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                PersonalAgentInfoBean json = JSONUtil.parseJSON(s, PersonalAgentInfoBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 更新apk
     */
    public void updateApk(final Activity activity) {
        XRequestParams params = new XRequestParams(InterfaceAddr.updateApk);
        params.addBodyParameter("client_type", "1");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                final UpdateApkBean json = JSONUtil.parseJSON(s, UpdateApkBean.class);
                if (!BaseUtil.isEmpty(json.getData()) && UpdateUtil.isNeedUpdate(activity, json.getData().getVersion_no())) {
                    SimpleDialog simpleDialog = new SimpleDialog(activity)
                            .setTitle("版本更新")
                            .setContent(json.getData().getUpdate_content())
                            .setStyle(SimpleDialog.STYLE_RIGHT)
                            .setText("稍后更新", "立即更新");
                    simpleDialog.show();
                    simpleDialog.setOnDialogButtonListener(new OnDialogButtonListener() {
                        @Override
                        public void onLeftBtn() {

                        }

                        @Override
                        public void onRightBtn() {
                            UpdateUtil.downloadApk(activity, json.getData().getVersion_url());
                        }
                    });
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void createUserInfo(final Context context, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.createUserInfo);
        rp.addBodyParameter("user_id", App.userInfoBean.getData().getId());
        x.http().get(rp, new Callback.CommonCallback<String>() {
            //        rp.addBodyParameter("user_id",App.userInfoBean.getData().getId());
//        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                UserInfoBean json = JSONUtil.parseJSON(s, UserInfoBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    App.userInfoBean.getData().setPoint(json.getData().getPoint());
                    ACache.get(context).put("UserInfo", App.userInfoBean);
                    json.getData().setAccess_token(App.userInfoBean.getData().getAccess_token());
                    App.userInfoBean = json;
                    ACache.get(context).put("UserInfo", json);
                    onInterfaceTask.onSuccess(s);
                } else {
                    onInterfaceTask.onError();
                    ToastUtil.Toast(context, json.getResult_desc());
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取团队报表
     */
    public void getTeamReportForms(String startTime, String endTime, String account, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getTeamReportForms);
        params.addQueryStringParameter("start_time", startTime);
        params.addQueryStringParameter("end_time", endTime);
        params.addQueryStringParameter("account", account);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                TeamReportFormsBean json = JSONUtil.parseJSON(s, TeamReportFormsBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取下级报表
     */
    public void getSubordinateReportForms(int page, String startTime, String endTime, String account, String sort, String sortType, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getSubordinateReportForms);
        params.addQueryStringParameter("start_time", startTime);
        params.addQueryStringParameter("end_time", endTime);
        params.addQueryStringParameter("account", account);
        params.addQueryStringParameter("sort", sort);
        params.addQueryStringParameter("order_by", sortType);
        params.addQueryStringParameter("page", String.valueOf(page));
        params.addQueryStringParameter("pageSize", "10");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                SubordinateReportFormsBean json = JSONUtil.parseJSON(s, SubordinateReportFormsBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取返佣明细
     */
    public void getReturnCommissionDetail(int page, String startTime, String endTime, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getReturnCommissionDetail);
        params.addQueryStringParameter("startTime", startTime);
        params.addQueryStringParameter("endTime", endTime);
        params.addQueryStringParameter("page", String.valueOf(page));
        params.addQueryStringParameter("pageSize", "10");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ReturnCommissionDetailBean json = JSONUtil.parseJSON(s, ReturnCommissionDetailBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 下级管理列表
     */
    public void getSubordinateList(final SubordinateManageActivity activity, int page, String subUserId, String account, String sortType, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getSubordinateList);
        params.addQueryStringParameter("page", String.valueOf(page));
        params.addQueryStringParameter("pageSize", "10");
        params.addQueryStringParameter("agentType", "0");
        params.addQueryStringParameter("subUserId", subUserId);
        params.addQueryStringParameter("account", account);
        params.addQueryStringParameter("sortType", sortType);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                SubordinateManageListBean json = JSONUtil.parseJSON(s, SubordinateManageListBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                } else {
                    ToastUtil.Toast(activity, String.valueOf(json.getResult_desc()));
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 可提现信息
     */
    public void withdrawInfo(final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.withdrawInfo);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                WithdrawInfoBean json = JSONUtil.parseJSON(s, WithdrawInfoBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 提现
     */
    public void withdraw(final Activity activity, String bankId, String fee, String securityPsw) {
        for (int i = 0; i < 3; i++) {
            securityPsw = MD5Util.getMd5(securityPsw);
        }
        XRequestParams params = new XRequestParams(InterfaceAddr.withdraw);
        params.addBodyParameter("fee", fee);
        params.addBodyParameter("withdrawals_password", securityPsw);
        params.addBodyParameter("id", bankId);
        params.addBodyParameter("type", "3");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    ToastUtil.Toast(activity, "提现成功,请等待客服审核即可到账");
                    activity.finish();
                } else {
                    ToastUtil.Toast(activity, String.valueOf(json.get("result_desc")));
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取用户投注记录
     */
    public void getUserBetRecord(int page, String stateType, String gameType, String startTime,
                                 String endTime, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getUserBetRecord);
        params.addQueryStringParameter("status_type", stateType);
        params.addQueryStringParameter("game_type", gameType);
        params.addQueryStringParameter("start_time", startTime);
        params.addQueryStringParameter("end_time", endTime);
        params.addQueryStringParameter("page_no", String.valueOf(page));
        params.addQueryStringParameter("page_size", "10");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                BetRecordBean json = JSONUtil.parseJSON(s, BetRecordBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取用户追号列表
     */
    public void getUserTraceNumList(int page, String stateType, String gameType, String startTime,
                                    String endTime, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getUserTraceNumList);
        params.addQueryStringParameter("status_type", stateType);
        params.addQueryStringParameter("game_type", gameType);
        params.addQueryStringParameter("start_time", startTime);
        params.addQueryStringParameter("end_time", endTime);
        params.addQueryStringParameter("page_no", String.valueOf(page));
        params.addQueryStringParameter("page_size", "10");
        x.http().get(params, new XCallback<String>() {
            @Override
            public void onSuccess(String result) {
                TraceNumListBean json = JSONUtil.parseJSON(result, TraceNumListBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }
        });
    }

    /**
     * 获取用户交易明细
     */
    public void getUserBusinessRecord(int page, final String type, String stateType, String startTime,
                                      String endTime, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getUserBusinessRecord);
        params.addQueryStringParameter("type", type);
        params.addQueryStringParameter("statusType", stateType);
        params.addQueryStringParameter("start_time", startTime);
        params.addQueryStringParameter("end_time", endTime);
        params.addQueryStringParameter("page", String.valueOf(page));
        params.addQueryStringParameter("pageSize", "10");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                if (BaseUtil.equals(type, "1")) {
                    SubAccountDetailBean json = JSONUtil.parseJSON(s, SubAccountDetailBean.class);
                    if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                        onInterfaceTask.onSuccess(json);
                    }
                } else if (BaseUtil.equals(type, "3")) {
                    SubWithdrawRecordBean json = JSONUtil.parseJSON(s, SubWithdrawRecordBean.class);
                    if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                        onInterfaceTask.onSuccess(json);
                    }
                } else if (BaseUtil.equals(type, "2")) {
                    SubRechargeRecordDetailBean json = JSONUtil.parseJSON(s, SubRechargeRecordDetailBean.class);
                    if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                        onInterfaceTask.onSuccess(json);
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取用户信息
     */
    public void getUserDetail(final Activity activity, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getUserDetail);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                UserDetailBean json = JSONUtil.parseJSON(s, UserDetailBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                } else {
                    if (json.getResult_desc().contains("accessToken"))
                        activity.startActivityForResult(new Intent(activity, LoginActivity.class), 0);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 修改用户信息
     */
    public void changeUserDetail(final Activity activity, String sex, String headUrl, String nickName) {
        XRequestParams params = new XRequestParams(InterfaceAddr.changeUserInfo);
        if (!BaseUtil.isEmpty(sex))
            params.addBodyParameter("sex", sex);
        if (!BaseUtil.isEmpty(nickName))
            params.addBodyParameter("nick_name", nickName);
        if (!BaseUtil.isEmpty(headUrl))
            params.addBodyParameter("user_photo", headUrl);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    ToastUtil.Toast(activity, "修改成功");
                } else {
                    ToastUtil.Toast(activity, "修改失败");
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取系统头像
     */
    public void getSystemHeadPortrait(final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getSystemHeadPortrait);
        params.addQueryStringParameter("page", "1");
        params.addQueryStringParameter("pageSize", "1000");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                SystemHeadPortraitBean json = JSONUtil.parseJSON(s, SystemHeadPortraitBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取用户等级列表
     */
    public void getUserLevelList(final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getUserLevelList);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                UserLevelListBean json = JSONUtil.parseJSON(s, UserLevelListBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 用户撤单
     */
    public void userCancelBetOrder(final Activity activity, String gameType, String orderNum,final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.userCancelBetOrder);
        params.addBodyParameter("game_type", gameType);
        params.addBodyParameter("order_no", orderNum);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    onInterfaceTask.onSuccess(json);
                } else {
                    ToastUtil.Toast(activity, String.valueOf(json.get("result_desc")));
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取支付列表
     */
    public void getPayList(final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getPayList);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                PayListBean json = JSONUtil.parseJSON(s, PayListBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 生成订单,线下转账
     */
    public void offlinePay(final Activity activity, String rechargeMoney, String type, String accountId, String payPersonName) {
        XRequestParams params = new XRequestParams(InterfaceAddr.offlinePay);
        params.addBodyParameter("point", rechargeMoney);
        params.addBodyParameter("account_type", type);
        params.addBodyParameter("account_id", accountId);
        params.addBodyParameter("real_name", payPersonName);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    if (activity instanceof CompanyIncomeActivity) {
                        new SimpleDialog(activity)
                                .setTitle("请求成功")
                                .setContent(activity.getString(R.string.bank_pay_hint))
                                .setStyle(SimpleDialog.STYLE_SINGLE)
                                .setText("", "我知道了")
                                .show();
                    }else if (activity instanceof RechargePayActivity){
                        new SimpleDialog(activity)
                                .setTitle("请求成功")
                                .setContent(activity.getString(R.string.zifubao_pay_hint))
                                .setStyle(SimpleDialog.STYLE_SINGLE)
                                .setText("", "我知道了")
                                .show();
                    }
                }else {
                    ToastUtil.Toast(activity,String.valueOf(json.get("result_desc")));
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 充值支付,线上支付
     */
    public void onlinePay(final Activity activity,final String title, final String money, String accountId) {
        XRequestParams params = new XRequestParams(InterfaceAddr.onlinePay);
        params.addQueryStringParameter("money", money);
        params.addQueryStringParameter("uid", App.userInfoBean.getData().getId());
        params.addQueryStringParameter("account_id", accountId);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("code")), "00")) {
                    if (BaseUtil.equals(String.valueOf(json.get("type")), "1")) {
                        Intent intent = new Intent(activity, WebLoadActivity.class);
                        intent.putExtra(WebLoadActivity.TITLE, title);
                        intent.putExtra(WebLoadActivity.URL, String.valueOf(json.get("msg")));
                        activity.startActivity(intent);
                    } else {
                        generateQrcodeByUrl(activity, money, String.valueOf(json.get("msg")));
                    }
                }else {
                    ToastUtil.Toast(activity,String.valueOf(json.get("msg")));
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 根据支付地址生成二维码
     */
    public void generateQrcodeByUrl(final Activity activity, final String money, String qrcodeUrl) {
        XRequestParams params = new XRequestParams(InterfaceAddr.generateQrcodeByUrl);
        params.addQueryStringParameter("code_url", qrcodeUrl);
        try {
            params.setSaveFilePath(FileUtil.saveToAppName(activity,"image","qrcode"));
        } catch (FileUtil.SdcardDisableException e) {
            e.printStackTrace();
        }
        x.http().get(params,new XCallback<File>(){

            private File result;
            private ViewDialog qrcodeDialog;

            @Override
            public void onSuccess(File result) {
                this.result = result;
                View qrcodeView = View.inflate(activity, R.layout.dialog_qrcode, null);
                qrcodeView.findViewById(R.id.icon_close).setOnClickListener(new OnClickListener());
                qrcodeView.findViewById(R.id.lly_save_2_picture).setOnClickListener(new OnClickListener());
                TextView tvRechargeMoney = qrcodeView.findViewById(R.id.tv_recharge_money);
                ImageView ivQrcode = qrcodeView.findViewById(R.id.iv_qrcode);
                tvRechargeMoney.setText(money);
                Bitmap bitmap = BitmapFactory.decodeFile(result.getPath());
                ivQrcode.setImageBitmap(bitmap);

                qrcodeDialog = new ViewDialog(activity, qrcodeView);
                qrcodeDialog.setCancelable(false);
                qrcodeDialog.setBgTransparent();
                qrcodeDialog.show();
            }

            class OnClickListener implements View.OnClickListener {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.icon_close:
                            qrcodeDialog.dismiss();
                            break;
                        case R.id.lly_save_2_picture:
                            ToastUtil.Toast(activity,"下载地址:" + result);
                            break;
                    }
                }
            }
        });
    }

    /**
     * 获取下级投注明细
     */
    public void getSubordinateBetDetail(int page, String stateType, String gameType, String startTime,
                                        String endTime, String account, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getSubordinateBetDetail);
        params.addQueryStringParameter("statusType", stateType);
        params.addQueryStringParameter("gameType", gameType);
        params.addQueryStringParameter("start_time", startTime);
        params.addQueryStringParameter("end_time", endTime);
        params.addQueryStringParameter("account", account);
        params.addQueryStringParameter("page", String.valueOf(page));
        params.addQueryStringParameter("pageSize", "10");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                SubordinateBetDetailBean json = JSONUtil.parseJSON(s, SubordinateBetDetailBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取下级交易明细
     */
    public void getSubordinateBusinessDetail(int page, final String type, String stateType, String startTime,
                                             String endTime, String account, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getSubordinateBusinessDetail);
        params.addQueryStringParameter("type", type);
        params.addQueryStringParameter("statusType", stateType);
        params.addQueryStringParameter("start_time", startTime);
        params.addQueryStringParameter("end_time", endTime);
        params.addQueryStringParameter("account", account);
        params.addQueryStringParameter("page", String.valueOf(page));
        params.addQueryStringParameter("pageSize", "10");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                if (BaseUtil.equals(type, "1")) {
                    SubAccountDetailBean json = JSONUtil.parseJSON(s, SubAccountDetailBean.class);
                    if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                        onInterfaceTask.onSuccess(json);
                    }
                } else if (BaseUtil.equals(type, "3")) {
                    SubWithdrawRecordBean json = JSONUtil.parseJSON(s, SubWithdrawRecordBean.class);
                    if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                        onInterfaceTask.onSuccess(json);
                    }
                } else if (BaseUtil.equals(type, "2")) {
                    SubRechargeRecordDetailBean json = JSONUtil.parseJSON(s, SubRechargeRecordDetailBean.class);
                    if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                        onInterfaceTask.onSuccess(json);
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 生成邀请码
     */
    public void generateInvite(final FragmentActivity activity, String type, String kuai3, String ssc, String game11x5, String fucai3d, String pailie35,
                               String game28, String pk10, String lhc, String dpc) {
        XRequestParams params = new XRequestParams(InterfaceAddr.generateInvite);
        params.addQueryStringParameter("type", type);
        params.addQueryStringParameter("kuaiThreeProfit", kuai3);
        params.addQueryStringParameter("shishicaipProfit", ssc);
        params.addQueryStringParameter("elevenFiveProfit", game11x5);
        params.addQueryStringParameter("fucaiProfit", fucai3d);
        params.addQueryStringParameter("arrayProfit", pailie35);
        params.addQueryStringParameter("twentyEightProfit", game28);
        params.addQueryStringParameter("pkTenProfit", pk10);
        params.addQueryStringParameter("lhcProfit", lhc);
        params.addQueryStringParameter("lowFrequencyProfit", dpc);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    ToastUtil.Toast(activity, "生成邀请码成功");
                } else {
                    ToastUtil.Toast(activity, String.valueOf(json.get("result_desc")));
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取邀请码列表
     */
    public void getInviteCodeList(String type, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getInviteCodeList);
        params.addQueryStringParameter("type", type);
        params.addQueryStringParameter("page", "1");
        params.addQueryStringParameter("page_size", "10000");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                InviteCodeListBean json = JSONUtil.parseJSON(s, InviteCodeListBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 删除邀请码
     */
    public void delInviteCode(final Context context, String inviteCodeId, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.delInviteCode);
        params.addQueryStringParameter("inviteCodeId", inviteCodeId);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("data")), "ok")) {
                    ToastUtil.Toast(context, "删除成功");
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取普通消息
     */
    public void getCommonMsg(int page, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getCommonMsg);
        params.addQueryStringParameter("page_no", String.valueOf(page));
        params.addQueryStringParameter("page_size", "10");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                MessageBean json = JSONUtil.parseJSON(s, MessageBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取公告消息
     */
    public void getNoticeMsg(int page, final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getNoticeMsg);
        params.addQueryStringParameter("page_no", String.valueOf(page));
        params.addQueryStringParameter("page_size", "10");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                MessageBean json = JSONUtil.parseJSON(s, MessageBean.class);
                if (BaseUtil.equals(String.valueOf(json.getResult_code()), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取未读消息
     */
    public void getNotReadMsg(final OnInterfaceTask onInterfaceTask) {
        XRequestParams params = new XRequestParams(InterfaceAddr.getNotReadMsg);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                NotReadMsgBean json = JSONUtil.parseJSON(s, NotReadMsgBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 设置普通消息已读
     */
    public void setMsgRead(int id) {
        XRequestParams params = new XRequestParams(InterfaceAddr.setCommonMsgRead);
        params.addQueryStringParameter("ids", String.valueOf(id));
        x.http().get(params, new XCallback<String>(){});
    }

    /**
     * 删除普通消息
     */
    public void delCommonMsg(final Activity activity, int id) {
        XRequestParams params = new XRequestParams(InterfaceAddr.delCommonMsg);
        params.addQueryStringParameter("ids", String.valueOf(id));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("result_code")), "0")) {
                    ToastUtil.Toast(activity, "删除成功");
                    activity.finish();
                } else {
                    ToastUtil.Toast(activity, String.valueOf(json.get("result_desc")));
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取客服地址
     */
    public void getCustomServerAddr(){
        XRequestParams params = new XRequestParams(InterfaceAddr.getCustomServerAddr);
        x.http().get(params,new XCallback<String>(){
            @Override
            public void onSuccess(String result) {
                CustomServerAddrBean json = JSONUtil.parseJSON(result, CustomServerAddrBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    App.customServerAddr = json.getData().getOther_value();
                }
            }
        });
    }

    /**
     * 获取首页内容(4每日加奖5晋级奖励,6一般活动)
     */
    public void getActivityInfo(final OnInterfaceTask onInterfaceTask){
        XRequestParams params = new XRequestParams(InterfaceAddr.getActivityInfo);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ActivityCenterBean json = JSONUtil.parseJSON(s, ActivityCenterBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 长龙投注
     */
    public void getdragonAdd(final Context context, String wanfa_type, String choice_no, int multiple_count, int bet_count, String dragonBet, int unit_type, int point, int bili_id, String game_type, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.getdragonAdd);
        rp.addBodyParameter("choice_no", choice_no);
        rp.addBodyParameter("point", point + "");
        rp.addBodyParameter("bili_id", bili_id + "");
        rp.addBodyParameter("dragonBet", dragonBet + "");
        rp.addBodyParameter("wanfa_type", wanfa_type);
        rp.addBodyParameter("bet_count", bet_count + "");
        rp.addBodyParameter("multiple_count", multiple_count + "");
        rp.addBodyParameter("unit_type", unit_type + "");
        rp.addBodyParameter("game_type", game_type + "");

        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("result_code")), "0")) {
                    onInterfaceTask.onSuccess("ok");
                } else {
                    ToastUtil.Toast(context, "投注失败");
                    onInterfaceTask.onError();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 长龙投注列表
     */
    public void getLongDragonList(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.getLongDragonList);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                CathecticInfo homeWanFaBean = JSONUtil.parseJSON(s, CathecticInfo.class);
                if (BaseUtil.equals(homeWanFaBean.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(homeWanFaBean);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 最新中奖榜
     */
    public void getNewWinPrize(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.getNewWinPrize);
        rp.addBodyParameter("topCount", "10");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                NewWinPrizeBean json = JSONUtil.parseJSON(s, NewWinPrizeBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取昨日中奖榜
     */
    public void getYesterDayWinRank(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.getYesterdayWinRank);
        rp.addBodyParameter("topCount", "10");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                NewWinPrizeBean json = JSONUtil.parseJSON(s, NewWinPrizeBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void getSystemConfig(final OnInterfaceTask onInterfaceTask){
        XRequestParams params = new XRequestParams(InterfaceAddr.getSystemConfig);
        x.http().get(params,new XCallback<String>(){
            @Override
            public void onSuccess(String result) {
                SystemConfigBean json = JSONUtil.parseJSON(result, SystemConfigBean.class);
                if (BaseUtil.equals(json.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(json);
                }
            }
        });
    }

    /**
     * 今日盈亏
     */
    public void getTodayProfitLoss(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.getTodayProfitLoss);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                ProfitLossInfo profitLossInfo = JSONUtil.parseJSON(s, ProfitLossInfo.class);
                if (BaseUtil.equals(profitLossInfo.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(profitLossInfo);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 每日加奖比例列表
     */
    public void getsBonusRateLis(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.getsBonusRateLis);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                DailyBonusBean homeWanFaBean = JSONUtil.parseJSON(s, DailyBonusBean.class);
                if (BaseUtil.equals(homeWanFaBean.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(homeWanFaBean);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 获取用户每日加奖奖励
     */
    public void getBetBonus(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.getBetBonus);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                BetBonusBean homeWanFaBean = JSONUtil.parseJSON(s, BetBonusBean.class);
                if (BaseUtil.equals(homeWanFaBean.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(homeWanFaBean);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 领取用户每日加奖奖励
     */
    public void getBetBonusDraw(final Context context, final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.getBetBonusDraw);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("result_code")), "0")) {
                    onInterfaceTask.onSuccess("ok");
                } else {
                    ToastUtil.Toast(context, "领取失败");
                    onInterfaceTask.onError();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void getLevelBonusDraw(final Context context, final OnInterfaceTask onInterfaceTask){
        XRequestParams params = new XRequestParams(InterfaceAddr.getLevelBonusDraw);
        x.http().get(params,new XCallback<String>(){
            @Override
            public void onSuccess(String s) {
                HashMap<String, Object> json = JSONUtil.parseJSON(s);
                if (BaseUtil.equals(String.valueOf(json.get("result_code")), "0")) {
                    onInterfaceTask.onSuccess("ok");
                } else {
                    ToastUtil.Toast(context, "领取失败");
                    onInterfaceTask.onError();
                }
            }
        });
    }

    /**
     * 获取用户等级晋升奖励
     */
    public void getLevelBonus(final OnInterfaceTask onInterfaceTask) {
        XRequestParams rp = new XRequestParams(InterfaceAddr.getLevelBonus);
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                BetBonusBean homeWanFaBean = JSONUtil.parseJSON(s, BetBonusBean.class);
                if (BaseUtil.equals(homeWanFaBean.getResult_code(), "0")) {
                    onInterfaceTask.onSuccess(homeWanFaBean);
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                throwable.printStackTrace();
                onInterfaceTask.onError();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


}

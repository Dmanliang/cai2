package com.sp.caitwo.asynctask;


/**
 * Created by vinson on 2018/11/12.
 */

public class InterfaceAddr {

    public final static String host = "http://47.100.234.151:8099";

    /**
     * 首页广告栏信息
     */
    public final static String banner = host + "/banner/list";

    /**
     * 获取客服地址
     */
    public final static String getCustomServerAddr = host + "/otherInfo/details?key=kefu_url";
    /**
     * 全部彩种
     */
    public final static String homeWanFa = host + "/home/wanfa";
    /**
     * 用户登录
     */
    public final static String login = host + "/user/login";
    /**
     * 用户注册
     */
    public final static String register = host + "/user/register";
    /**
     * 房间模式所有彩种列表
     */
    public final static String roomBiliList = host + "/room/bili/list";
    /**
     * 直投模式所有彩种列表
     */
    public final static String wanfaBiliList = host + "/lottery/wanfa/list";
    /**
     * 房间投注
     */
    public final static String roomPointAdd = host + "/room/point/add/list";
    /**
     * 直投投注
     */
    public final static String lotteryOrderSubBet = host + "/lottery/Order/subBet";
    /**
     * 用户绑定银行卡列表
     */
    public final static String userBankList = host + "/userinfobank/list";
    /**
     * 添加用户绑定银行卡列表
     */
    public final static String addUserBank = host + "/userinfobank/add";
    /**
     * 修改用户银行卡
     */
    public final static String changeUserBank = host + "/userinfobank/update";
    /**
     * 验证安全密码
     */
    public final static String checkSecurityPsw = host + "/user/validate/security/pwd";
    /**
     * 更新用户信息
     */
    public final static String changeUserInfo = host + "/user/update";
    /**
     * 个人代理信息
     */
    public final static String personalAgentInfo = host + "/agent/info";
    /**
     * 房间开奖记录及倒计时
     */
    public final static String roomOpenInfo = host + "/room/open/info";
    /**
     * 直投开奖记录及倒计时
     */
    public final static String lotteryOrderInfo = host + "/lottery/Order/getLotteryInfo";
    /**
     * 直投其他彩种历史开奖列表
     */
    public final static String lotteryKjLog = host + "/lottery/kjLog";
    /**
     * 房间 六合彩 获取下注参数
     */
    public final static String lhcAllWanFaList = host + "/lhc/allwanfa/list";
    /**
     * 六合彩开奖倒计时与最新开奖记录
     */
    public final static String lhcLotteryOrderInfo = host + "/Home/Order/getLotteryInfo";
    /**
     * 根据生肖名称返回生肖号码
     */
    public final static String lhcGetShengXiaoNum = host + "/Home/Other/getShengxiaoNum";
    /**
     * 六合彩历史开奖记录
     */
    public final static String lhcGetWinsRecord = host + "/Home/Index/getWins";
    /**
     * 六合彩下注
     */
    public final static String lhcSubBet = host + "/lhc/subBet";
    /**
     * 六合彩色波
     */
    public final static String lhcSeboNumber = host + "/lottery/number";
    /**
     * 追号投注接口
     */
    public final static String dragonAddChasing = host + "/dragon/addChasing";
    /**
     * 追号列表
     */
    public final static String dragonChasingList = host + "/dragon/chasing/list";
    /**
    * 长龙助手投注记录
    * */
    public final static String dragonChoicelogList = host + "/dragon/choicelog/list";
    /**
     * 获取密保问题列表
     */
    public final static String getSecurityQuestionList = host + "/user/security/question/list";
    /**
     * 设置密保问题
     */
    public final static String setSecurityQuestion = host + "/user/security/answer/add";
    /**
     * 验证密保问题
     */
    public final static String checkSecurityQuestion = host + "/user/security/answer/validate";
    /**
     * 修改密保问题
     */
    public final static String changeSecurityQuestion = host + "/user/security/answer/update";
    /**
     * 找回安全密码
     */
    public final static String getBackSecurityPsw = host + "/user/security/find/withdrawPassword";
    /**
     * 获取个人密保问题
     */
    public final static String getPersonalSecurityQuestion = host + "/user/security/answer/account";
    /**
     * 找回密码
     */
    public final static String getBackPsw = host + "/user/security/find/password";
    /**
     * 用户信息
     */
    public final static String createUserInfo = host + "/user/details";
    /**
     * 获取团队报表
     */
    public final static String getTeamReportForms = host + "/agent/team/statistics";
    /**
     * 获取下级报表
     */
    public final static String getSubordinateReportForms = host + "/agent/sub/statistics";
    /**
     * 获取返佣明细
     */
    public final static String getReturnCommissionDetail = host + "/agent/info/record/list";
    /**
     * 获取下级代理列表
     */
    public final static String getSubordinateList = host + "/agent/sub/list";
    /**
     * 获取下级投注明细
     */
    public final static String getSubordinateBetDetail = host + "/agent/sub/bet/record/list";
    /**
     * 获取下级交易明细
     */
    public final static String getSubordinateBusinessDetail = host + "/agent/sub/trade/record/list";
    /**
     * 生成邀请码
     */
    public final static String generateInvite = host + "/agent/inviteCode/add";
    /**
     * 获取邀请码列表
     */
    public final static String getInviteCodeList = host + "/agent/inviteCode/list";
    /**
     * 删除邀请码
     */
    public final static String delInviteCode = host + "/agent/inviteCode/del";
    /**
     * 获取支付列表
     */
    public final static String getPayList = host + "/pay/list";
    /**
     * 生成账单
     */
    public final static String offlinePay = host + "/account/user/add";
    /**
     * 可提现信息
     */
    public final static String withdrawInfo = host + "/user/available/withdraw";
    /**
     * 提现
     */
    public final static String withdraw = host + "/withdrawals/add";
    /**
     * 获取投注记录
     */
    public final static String getUserBetRecord = host + "/user/bet/record/status";
    /**
     * 获取用户交易记录
     */
    public final static String getUserBusinessRecord = host + "/user/trade/record/list";
    /**
     * 获取用户详情
     */
    public final static String getUserDetail = host + "/user/details";
    /**
     * 获取系统头像
     */
    public final static String getSystemHeadPortrait = host + "/default/photo";
    /**
     * 获取用户等级列表
     */
    public final static String getUserLevelList = host + "/user/level/list";
    /**
     * 用户撤销投注订单
     */
    public final static String userCancelBetOrder = host + "/user/cancel/bet/order";
    /**
     * 获取普通消息
     */
    public final static String getCommonMsg = host + "/notice/list";
    /**
     * 获取系统公告
     */
    public final static String getNoticeMsg = host + "/system/notice/list";
    /**
     * 获取未读通知数量
     */
    public final static String getNotReadMsg = host + "/notice/count";
    /**
     * 获取设置普通消息为已读
     */
    public final static String setCommonMsgRead = host + "/notice/sign/read";
    /**
     * 删除普通消息
     */
    public final static String delCommonMsg = host + "/notice/del";
    /**
     * 更新apk
     */
    public final static String updateApk = host + "/version";
    /**
     * 获取首页内容(4每日加奖5晋级奖励,6一般活动)
     */
    public final static String getActivityInfo = host + "/home/content";
    /**
     * 充值支付
     */
    public final static String onlinePay = host + "/recharge/pay";
    /**
     * 根据支付地址生成二维码
     */
    public final static String generateQrcodeByUrl = host + "/recharge/qrcode";
    /**
     * 获取用户追号列表
     */
    public final static String getUserTraceNumList = host + "/dragon/chasing/list";
    /**
     * 获取系统配置
     */
    public final static String getSystemConfig = host + "/system/config/list";

    /**
     * 长龙投注列表
     */
    public final static String getLongDragonList = host + "/dragon/list";

    /**
     * 长龙投注
     */
    public final static String getdragonAdd = host + "/room/point/add";
    /**
     * 最新中奖榜
     */
    public final static String getNewWinPrize = host + "/newest/winRank";
    /**
     * 获取昨日中奖榜
     */
    public final static String getYesterdayWinRank = host + "/yesterday/winRank";
    /**
     * 今日盈亏
     */
    public final static String getTodayProfitLoss = host + "/user/today/profitLoss";


    /**
     * 每日加奖比例列表
     */
    public final static String getsBonusRateLis = host + "/user/bonus/rate/list";

    /**
     * 获取用户每日加奖奖励
     */
    public final static String getBetBonus = host + "/user/bet/bonus";

    /**
     * 领取用户每日加奖奖励
     */
    public final static String getBetBonusDraw = host + "/user/bet/bonus/draw";
    /**
     * 获取等级晋升奖励
     */
    public final static String getLevelBonus = host + "/user/level/bonus";
    /**
     * 领取等级晋升奖励
     */
    public final static String getLevelBonusDraw = host + "/user/level/bonus/draw";


}

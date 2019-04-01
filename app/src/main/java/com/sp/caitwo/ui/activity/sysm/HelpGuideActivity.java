package com.sp.caitwo.ui.activity.sysm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;

public class HelpGuideActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_guide);

        findViewById(R.id.iv_top_back).setOnClickListener(this);
        findViewById(R.id.lly_how_register).setOnClickListener(this);
        findViewById(R.id.lly_how_buy_lottery).setOnClickListener(this);
        findViewById(R.id.lly_how_recharge).setOnClickListener(this);
        findViewById(R.id.lly_how_withdraw).setOnClickListener(this);
        findViewById(R.id.lly_how_phone_buy_lottery).setOnClickListener(this);
        findViewById(R.id.lly_online_costom_service_time).setOnClickListener(this);
        findViewById(R.id.lly_what_is_security_psw).setOnClickListener(this);
        findViewById(R.id.lly_everyday_withdraw_count).setOnClickListener(this);
        findViewById(R.id.lly_how_bind_bank_card).setOnClickListener(this);
        findViewById(R.id.lly_account_security).setOnClickListener(this);
        findViewById(R.id.lly_what_is_can_withdraw_money).setOnClickListener(this);

        TextView tvTitle = findViewById(R.id.tv_top_title);
        tvTitle.setText(getString(R.string.help_guide));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_top_back){
            finish();
            return;
        }
        String content = "";
        String title = "";
        Intent intent = new Intent(this, WebLoadActivity.class);
        switch(v.getId()){
            case R.id.lly_how_register:
                content = "<p style=\"color:#666666; font-family:&quot;font-size:14px;\">\n" +
                        "\t点击网站右上角＂用户注册＂按钮，就可以进入用户注册页面；<br>\n" +
                        "按照提示，填写注册信息，即可完成注册；<br>\n" +
                        "账号为4-16位字符，支持数字和字母；<br>\n" +
                        "密码为6-16位字符，支持数字、字母、符号；<br>\n" +
                        "注册完成后，系统将随机分配一张头像，可以在我的账户中修改头像和设置昵称；\n" +
                        "</p>";
                title = getString(R.string.how_register);
                break;
            case R.id.lly_how_buy_lottery:
                content = "<p style=\"color:#666666; font-family:&quot;font-size:14px;\">\n" +
                        "\t购彩前需要先进行充值，账户有余额才可以进行购彩投注；<br>\n" +
                        "充值完成后，可进入＂购彩大厅＂选择您喜欢的彩票进行投注；<br>\n" +
                        "彩票投注页面，提供了＂玩法说明＂＂走势图＂，可以帮您更快了解＂玩法规则＂和＂开奖走势＂<br>\n" +
                        "投注完成后，可以进入＂我的账户＂＂投注记录＂，查看您的投注明细，查看是否中奖；<br>\n" +
                        "投注完成后，需等开奖后，才能查看是否中奖；<br>\n" +
                        "投注成功后，系统将自动从您的账户余额扣除对应的投注金额，如果中奖，奖金会自动添加到您的账户余额；<br>\n" +
                        "可以进入＂我的账户＂＂交易明细＂，查看您的账户交易明细；<br>\n" +
                        "</p>";
                title = getString(R.string.how_buy_lottery);
                break;
            case R.id.lly_how_recharge:
                content = "<p style=\"color:#666666; font-family:&quot;font-size:14px;\">\n" +
                        "\t账号登录后，点击网站右上角＂充值＂按钮，就可以进入充值页面；<br>\n" +
                        "目前网站提供了多种主流充值方式，如＂银行转账＂＂快捷支付＂＂支付宝＂＂微信支付＂ \"QQ钱包\"等；<br>\n" +
                        "如实填写充值信息，并提交充值订单，1-5分钟左右，刷新账户余额，即可到账；<br>\n" +
                        "点击＂我的账户＂＂交易明细＂可查看每日的交易明细；<br>\n" +
                        "大额充值建议使用＂银行转账＂；\n" +
                        "</p>";
                title = getString(R.string.how_recharge);
                break;
            case R.id.lly_how_withdraw:
                content = "<p style=\"color:#666666; font-family:&quot;font-size:14px;\">\n" +
                        "\t中奖后如何提现？点击网站右上角＂提现＂按钮，就可以进入提现页面；<br>\n" +
                        "选择您绑定的银行卡，填写您要提现的金额，并输入＂安全密码＂；<br>\n" +
                        "提现订单提交后，3-5分钟左右即可到账，可登陆银行查看银行账户余额；<br>\n" +
                        "点击＂我的账户＂＂交易明细＂可查看每日的交易明细；<br>\n" +
                        "提现之前需先设置＂安全密码＂和绑定您要用来提现的银行卡；<br>\n" +
                        "提现金额不能大于可提现金额；<br>\n" +
                        "为了提供更好的服务体验，降低广大用户提现订单的处理时间，每日最多只允许提现5次，建议用户合理安排每日的提现时间和次数；<br>\n" +
                        "</p>";
                title = getString(R.string.how_withdraw);
                break;
            case R.id.lly_how_phone_buy_lottery:
                content = "<p style=\"color:#666666; font-family:&quot;font-size:14px;\">\n" +
                        "\t直接在手机浏览器输入网址，即可登录手机版，无需下载；<br>\n" +
                        "手机版随时随地可以进行投注，更加便捷，建议广大用户使用手机版进行投注；<br>\n" +
                        "建议使用＂谷歌＂＂苹果＂＂UC＂浏览器，操作体验更佳；<br>\n" +
                        "</p>";
                title = getString(R.string.how_phone_buy_lottery);
                break;
            case R.id.lly_online_costom_service_time:
                content = "<p style=\"color:#666666; font-family:&quot;font-size:14px;\">\n" +
                        "\t在线客服提供全天24小时咨询服务，全年无休；<br>\n" +
                        "在游戏中遇到任何问题，欢迎随时咨询我们的在线客服，我们竭诚为您提供最高品质的咨询服务；<br>\n" +
                        "</p>";
                title = getString(R.string.online_costom_service_time);
                break;
            case R.id.lly_what_is_security_psw:
                content = "<p style=\"color:#666666; font-family:&quot;font-size:14px;\">\n" +
                        "\t安全密码不同于登陆密码，是另外要设置的一个密码；<br>\n" +
                        "安全密码一般用于提现、绑定银行卡等操作，可以保障账户资金安全；<br>\n" +
                        "为了账户安全，设置安全密码时建议不要跟登陆密码相同；\n" +
                        "</p>";
                title = getString(R.string.what_is_security_psw);
                break;
            case R.id.lly_everyday_withdraw_count:
                content = "<span style=\"font-size:14px;\">为了提供更好的服务体验，降低广大用户提现订单的处理时间，每日最多只允许提现5次，建议用户合理安排每日的提现时间和次数；</span>";
                title = getString(R.string.everyday_withdraw_count);
                break;
            case R.id.lly_how_bind_bank_card:
                content = "<p style=\"color:#666666; font-family:&quot;font-size:14px;\">\n" +
                        "\t进入＂我的账户＂＂银行卡管理＂可以绑定您要用来提现的银行卡；<br>\n" +
                        "绑定银行卡前，需先设置安全密码；<br>\n" +
                        "银行卡最多只允许绑定5张；<br>\n" +
                        "银行卡绑定支持所有主流银行；<br>\n" +
                        "为了用户资金安全，已成功提现的银行卡将自动锁定，无法删除和修改；如想弃用银行卡，可联系在线客服禁用该银行卡；<br>\n" +
                        "</p>";
                title = getString(R.string.how_bind_bank_card);
                break;
            case R.id.lly_account_security:
                content = "<div data-v-08bae35f=\"\" class=\"content\"><span style=\"font-size:14px;\">为了广大用户的账户安全，网站的＂安全中心＂提供了多项提升账户安全系数的功能；</span><br>\n" +
                        "<span style=\"font-size:14px;\"> 新用户注册完成后，请及时进入＂安全中心＂设置安全密码，绑定＂密保问题＂；</span><br>\n" +
                        "<span style=\"font-size:14px;\"> 用户忘记密码时，可通过以上功能，找回密码；</span><br></div>";
                title = getString(R.string.account_security);
                break;
            case R.id.lly_what_is_can_withdraw_money:
                content = "<p style=\"font-family:&quot;font-size:14px; color:#666666; background-color:#FFFFFF;\">\n" +
                        "\t<strong><span style=\"color:#000000; font-size:14px;\">可提现金额计算规则如下：</span></strong><br>\n" +
                        "为防止洗黑钱等行为，您的每一笔充值，有效投注金额需达到充值金额的50%才可以全额提现，有效投注金额不包含撤单的投注金额。\n" +
                        "</p>";
                title = getString(R.string.what_is_can_withdraw_money);
                break;
        }
        intent.putExtra(WebLoadActivity.TITLE,title);
        intent.putExtra(WebLoadActivity.URL,content);
        startActivity(intent);
    }
}

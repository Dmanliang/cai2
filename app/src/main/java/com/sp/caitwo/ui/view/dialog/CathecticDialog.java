package com.sp.caitwo.ui.view.dialog;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sp.caitwo.R;

import static com.sp.util.Util.getTwoDecimal;


/**
 * Created by Administrator on 2018/10/23.
 */

public class CathecticDialog extends CommonDialog{

    public Context context;
    public TextView cathectic_dialog_name,cathectic_dialog_money,cathectic_dialog_content,cathectic_dialog_clear,cathectic_dialog_ok;

    public CathecticDialog(Context context) {
        super(context, R.layout.view_cathectic_dialog, RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        this.context = context;
        initDlgView();
        this.context = context;
    }

    @Override
    public void initDlgView() {
        cathectic_dialog_name = getView(R.id.cathectic_dialog_name);
        cathectic_dialog_money = getView(R.id.cathectic_dialog_money);
        cathectic_dialog_content = getView(R.id.cathectic_dialog_content);
        cathectic_dialog_clear = getView(R.id.cathectic_dialog_clear);
        cathectic_dialog_ok = getView(R.id.cathectic_dialog_ok);
        cathectic_dialog_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setContext(String name, String issue, double money, String content){
        cathectic_dialog_name.setText(name+":"+issue+"期");
        cathectic_dialog_money.setText(Html.fromHtml("投注金额:<font color='#FE504F'>"+getTwoDecimal(money)+"</font>元"));
        cathectic_dialog_content.setText("投注内容:"+content);
    }
}

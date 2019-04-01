package com.sp.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.xwjlibrary.util.TextViewUtil;
import com.sp.xwjlibrary.widget.IconEditClearView;

import java.util.ArrayList;

public class SetTextViewSameWidthUtil {

    private static ArrayList<TextView> iecvLabelList = new ArrayList<>();

    public static void setIecv(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof IconEditClearView){
                iecvLabelList.add(((IconEditClearView) v).getIconView());
            }else if(v instanceof ViewGroup){
                setIecv((ViewGroup) v);
            }
        }
        TextViewUtil.setSameWidth(iecvLabelList.toArray(new TextView[0]),null);
    }

}

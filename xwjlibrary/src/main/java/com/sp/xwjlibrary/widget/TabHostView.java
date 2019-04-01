package com.sp.xwjlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.sp.xwjlibrary.R;

import java.util.ArrayList;

/**
 * 需要在Application中调用静态方法init(..)初始化TabHostView
 * Created by vinson on 2017/1/10.
 */

public class TabHostView extends FrameLayout{

    private static Typeface iconTypeFace;
    private FrameLayout flyFragContain;
    private LinearLayout llyTabHostBar;
    private ArrayList<Fragment> fragList = new ArrayList<>();
    private ArrayList<String> iconFontList = new ArrayList<>();
    private ArrayList<String> iconFontSelList = new ArrayList<>();
    private FragmentActivity mFa;
    private int defColor;
    private int selColor;
    private OnBtnClick onBtnClick;
    private final int navigaBarBg;
    private int currPage = 0;

    public TabHostView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TabHostView);
        navigaBarBg = ta.getResourceId(R.styleable.TabHostView_navigaBarBg, Color.WHITE);
        defColor = ta.getColor(R.styleable.TabHostView_defColor, ContextCompat.getColor(context,R.color.text_def_gray));
        selColor = ta.getColor(R.styleable.TabHostView_selColor, ContextCompat.getColor(context,R.color.water_blue));
        ta.recycle();
        initUI(context);
    }

    private void initUI(Context context){
        View inflate = View.inflate(context, R.layout.tab_host_view, null);
        flyFragContain = inflate.findViewById(R.id.fly_frag_contain);
        llyTabHostBar = inflate.findViewById(R.id.lly_tab_host_bar);
        this.setBackgroundColor(Color.WHITE);
        onBtnClick = new OnBtnClick();

        llyTabHostBar.setBackgroundColor(navigaBarBg);
        addView(inflate);
    }

    public ArrayList<Fragment> getFragment(){
        return fragList;
    }

    public void setTab(int tabIndex){
        llyTabHostBar.getChildAt(tabIndex).performClick();
    }

    public void reloadFragView(int tabIndex,Class<?> fragment){
        if (tabIndex >= 0 && tabIndex < fragList.size()){
            mFa.getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragList.get(tabIndex))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
            fragList.remove(fragList.get(tabIndex));
            try {
                fragList.add(tabIndex,(Fragment) fragment.newInstance());
                if (tabIndex == currPage) {
                    mFa.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(flyFragContain.getId(), (Fragment) fragment.newInstance())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 需要在Application中调用
     * @param iconTypeFace
     */
    public static void init(Typeface iconTypeFace){
        TabHostView.iconTypeFace = iconTypeFace;
    }

    /**
     * 添加页面
     * @param context
     * @param iconFont
     * @param iconSelFont
     * @param text
     * @param fragment
     */
    public void addTab(Context context, String iconFont,String iconSelFont, String text,Class<?> fragment){
        mFa = (FragmentActivity) context;
        View view = View.inflate(context,R.layout.tab_host_navigation,null);
        TextView tvIcon = view.findViewById(R.id.tv_nav_icon);
        TextView tvText = view.findViewById(R.id.tv_nav_title);

        tvIcon.setTypeface(iconTypeFace);
        tvIcon.setText(iconFont);
        tvText.setText(text);
        if (TextUtils.isEmpty(text))
            tvText.setVisibility(View.GONE);

        LinearLayout.LayoutParams llyParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1);
        llyParams.gravity = Gravity.BOTTOM;
        llyTabHostBar.addView(view,llyParams);

        iconFontList.add(iconFont);
        iconFontSelList.add(iconSelFont);
        if (fragment != null){
            try {
                fragList.add((Fragment) fragment.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }else {
            fragList.add(null);
            return;
        }

        //设置监听
        view.setOnClickListener(onBtnClick);
        llyTabHostBar.getChildAt(0).performClick();
    }

    /**
     * 实现监听接口
     */
    private class OnBtnClick implements OnClickListener{

        @Override
        public void onClick(View v) {
            int position = 0;
            for (int i = 0; i < llyTabHostBar.getChildCount(); i ++){
                LinearLayout view = (LinearLayout) llyTabHostBar.getChildAt(i);
                TextView tvIcon = (TextView) view.getChildAt(0);
                TextView tvText = (TextView) view.getChildAt(1);
                if(view == v){
                    position = i;
                    tvIcon.setTextColor(selColor);
                    if (!TextUtils.isEmpty(iconFontSelList.get(i)))
                        tvIcon.setText(iconFontSelList.get(i));
                    tvText.setTextColor(selColor);
                }else{
                    tvIcon.setTextColor(defColor);
                    if (!TextUtils.isEmpty(iconFontList.get(i)))
                        tvIcon.setText(iconFontList.get(i));
                    tvText.setTextColor(defColor);
                }
            }

            currPage = position;
            mFa.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(flyFragContain.getId(),fragList.get(position))
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();

        }
    }

    /*
    public void setTabStyle(String defColor,String selColor){
        this.defColor = defColor;
        this.selColor = selColor;
        for (int i = 0; i < llyTabHostBar.getChildCount(); i ++){
            LinearLayout view = (LinearLayout) llyTabHostBar.getChildAt(i);
            TextView tvIcon = (TextView) view.getChildAt(0);
            TextView tvText = (TextView) view.getChildAt(1);
            tvIcon.setTextColor(Color.parseColor(defColor));
            tvText.setTextColor(Color.parseColor(defColor));
        }
        llyTabHostBar.getChildAt(0).performClick();
    }
    */

}











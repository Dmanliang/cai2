package com.sp.xwjlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.sp.xwjlibrary.R;
import com.sp.xwjlibrary.myinterface.OnIconEditClearListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 需要在Application中调用静态方法init(..)初始化IconEditClearView
 * Created by vinson on 2017/7/19.
 */

public class IconEditClearView extends LinearLayout {

    private boolean focus;
    private String filterChars;
    private int maxLength;
    private ArrayAdapter<String> mAdapter;
    private Context mContext;
    private int ems;
    private float inputBottomSpace;
    private float inputLeftSpace;

    private enum GravityType{
        center(0),center_vertical(1),center_horizontal(2),left(3),top(4),right(5),bottom(6);
        private int gravity;
        GravityType(int gravity) {
            this.gravity = gravity;
        }
    }

    private OnIconEditClearListener textChangedListener;
    private String text = "";
    private String hint = "";
    private String labelIcon = "";
    private String clearIcon = "";
    private int textSize;
    private int textColor;
    private int hintColor;
    private int labelIconSize;
    private int labelIconColor;
    private int clearIconSize;
    private int clearIconColor;
    private int gravity;
    private int inputType = 0;
    private boolean showUnderline;
    private boolean singleLine;
    private int lines;
    private int maxLine;
    private static Typeface iconTypeFace;
    private int background;
    private LinearLayout llyError;
    private TextView tvError;

    private TextView icon;
    private TextView clear;
    private LastInputEditText edit;
    private boolean mEditFocusState;

    public IconEditClearView(Context context) {
        super(context);
        initView(context);
    }

    public IconEditClearView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.IconEditClearView);
        text = ta.getString(R.styleable.IconEditClearView_text);
        textSize = ta.getInteger(R.styleable.IconEditClearView_textSize, 14);
        textColor = ta.getColor(R.styleable.IconEditClearView_textColor, Color.parseColor("#707070"));
        hint = ta.getString(R.styleable.IconEditClearView_hint);
        hintColor = ta.getColor(R.styleable.IconEditClearView_hintColor, Color.parseColor("#8a8a8a"));
        labelIcon = ta.getString(R.styleable.IconEditClearView_labelIcon);
        labelIconSize = ta.getInteger(R.styleable.IconEditClearView_labelIconSize,14);
        labelIconColor = ta.getColor(R.styleable.IconEditClearView_labelIconColor,Color.parseColor("#707070"));
        clearIcon = ta.getString(R.styleable.IconEditClearView_clearIcon);
        clearIconSize = ta.getInteger(R.styleable.IconEditClearView_clearIconSize,14);
        clearIconColor = ta.getColor(R.styleable.IconEditClearView_clearIconSize,Color.parseColor("#8a8a8a"));
        gravity = ta.getInt(R.styleable.IconEditClearView_inputGravity, GravityType.left.gravity);
        inputType = ta.getInt(R.styleable.IconEditClearView_inputType, 0);
        background = ta.getResourceId(R.styleable.IconEditClearView_inputBg, android.R.color.transparent);
        showUnderline = ta.getBoolean(R.styleable.IconEditClearView_showUnderline, true);
        singleLine = ta.getBoolean(R.styleable.IconEditClearView_singleLine, false);
        lines = ta.getInt(R.styleable.IconEditClearView_lines, 1);
        maxLine = ta.getInt(R.styleable.IconEditClearView_maxLine, Integer.MAX_VALUE);
        focus = ta.getBoolean(R.styleable.IconEditClearView_focus, true);
        filterChars = ta.getString(R.styleable.IconEditClearView_filterChars);
        maxLength = ta.getInt(R.styleable.IconEditClearView_maxLength, Integer.MAX_VALUE);
        ems = ta.getInt(R.styleable.IconEditClearView_ems, 1);
        inputBottomSpace = ta.getDimension(R.styleable.IconEditClearView_inputBottomSpace, 0);
        inputLeftSpace = ta.getDimension(R.styleable.IconEditClearView_inputLeftSpace, 0);
        ta.recycle();
        initView(context);
    }

    private void initView(Context context){
        mContext = context;
        View.inflate(context, R.layout.icon_edit_clear_view,this);
        icon = findViewById(R.id.icon);
        clear = findViewById(R.id.clear);
        edit = findViewById(R.id.edit);
        llyError = findViewById(R.id.lly_error);
        //警告信息占位符,为了与输入平齐
        TextView iconPlaceholder = findViewById(R.id.icon_placeholder);
        tvError = findViewById(R.id.tv_error);
        View underline = findViewById(R.id.underline);

        MarginLayoutParams params = (MarginLayoutParams) edit.getLayoutParams();
        params.bottomMargin = (int) inputBottomSpace;
        params.topMargin = (int) inputBottomSpace;
        params.leftMargin = (int) inputLeftSpace;
        edit.setLayoutParams(params);
        edit.setFocusable(focus);
        edit.setFocusableInTouchMode(focus);
        edit.setText(text);
        edit.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        edit.setTextColor(textColor);
        edit.setHint(hint);
        edit.setHintTextColor(hintColor);
        edit.setSingleLine(singleLine);
        edit.setLines(lines);
        edit.setMaxLines(maxLine);
        edit.setEms(ems);
        edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        if(gravity == GravityType.center.gravity){
            edit.setGravity(Gravity.CENTER);
            tvError.setGravity(Gravity.CENTER);
        }
        if(gravity == GravityType.center_vertical.gravity){
            edit.setGravity(Gravity.CENTER_VERTICAL);
        }
        if(gravity == GravityType.center_horizontal.gravity){
            edit.setGravity(Gravity.CENTER_HORIZONTAL);
            tvError.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        if(gravity == GravityType.left.gravity){
            edit.setGravity(Gravity.LEFT);
            tvError.setGravity(Gravity.LEFT);
        }
        if(gravity == GravityType.top.gravity){
            edit.setGravity(Gravity.TOP);
        }
        if(gravity == GravityType.right.gravity){
            edit.setGravity(Gravity.RIGHT);
            tvError.setGravity(Gravity.RIGHT);
        }
        if(gravity == GravityType.bottom.gravity){
            edit.setGravity(Gravity.BOTTOM);
        }
        //文本
        if (inputType == 0){
            edit.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        //数字
        if (inputType == 1){
            edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        //密码
        if (inputType == 2){
            edit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            edit.setKeyListener(new NumberKeyListener() {
                @Override
                public int getInputType() {
                    return InputType.TYPE_TEXT_VARIATION_PASSWORD;
                }
                @Override
                protected char[] getAcceptedChars() {
                    char[] data = "qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM1234567890".toCharArray();
                    return data;
                }
            });
        }
        //过滤
        if (inputType == 3){
            edit.setKeyListener(new NumberKeyListener(){
                @Override
                protected char[] getAcceptedChars() {
                    return filterChars.toCharArray();
                }

                @Override
                public int getInputType() {
                    return InputType.TYPE_TEXT_VARIATION_FILTER;
                }
            });
        }
        //小数点数字
        if (inputType == 4){
            edit.setKeyListener(new NumberKeyListener(){
                @Override
                protected char[] getAcceptedChars() {
                    return "0123456789.".toCharArray();
                }

                @Override
                public int getInputType() {
                    return InputType.TYPE_TEXT_VARIATION_FILTER;
                }
            });
        }

        //自定义密码过滤
        if (inputType == 5){
            edit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            edit.setKeyListener(new NumberKeyListener(){
                @Override
                protected char[] getAcceptedChars() {
                    return filterChars.toCharArray();
                }

                @Override
                public int getInputType() {
                    return InputType.TYPE_TEXT_VARIATION_PASSWORD;
                }
            });
        }

        icon.setText(labelIcon);
        icon.setTextSize(TypedValue.COMPLEX_UNIT_SP,labelIconSize);
        icon.setTextColor(labelIconColor);
        clear.setText(clearIcon);
        clear.setTextSize(TypedValue.COMPLEX_UNIT_SP,clearIconSize);
        clear.setTextColor(clearIconColor);

        //设置警告语占位符
        iconPlaceholder.setText(labelIcon);
        iconPlaceholder.setTextSize(TypedValue.COMPLEX_UNIT_SP,labelIconSize);
        iconPlaceholder.setTextColor(labelIconColor);

        //设置输入框背景
        LinearLayout parent = (LinearLayout) icon.getParent();
        parent.setBackgroundResource(background);
        if (showUnderline){
            underline.setVisibility(View.VISIBLE);
        }else{
            underline.setVisibility(View.GONE);
        }

        if (iconTypeFace != null){
            icon.setTypeface(iconTypeFace);
            clear.setTypeface(iconTypeFace);
            iconPlaceholder.setTypeface(iconTypeFace);
        }

        edit.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mEditFocusState = hasFocus;
                if (!TextUtils.isEmpty(edit.getText().toString()) && hasFocus && clearIcon != null && !clearIcon.equals("")){
                    clear.setVisibility(View.VISIBLE);
                }else{
                    clear.setVisibility(View.GONE);
                }
                if (textChangedListener != null)
                    textChangedListener.onFocusChange(v,hasFocus);
            }
        });
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (textChangedListener != null){
                    textChangedListener.beforeTextChanged(s,start,count,after);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (textChangedListener != null){
                    textChangedListener.afterTextChanged(s);
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s) || clearIcon == null || clearIcon.equals("") || !mEditFocusState){
                    clear.setVisibility(View.GONE);
                }else{
                    clear.setVisibility(View.VISIBLE);
                }
                if (textChangedListener != null){
                    textChangedListener.OnTextChanged(s,start,before,count);
                }
                setErrorInfo("");
            }
        });
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("");
            }
        });

    }



    /**
     * 设置自动完成
     */
    public void setAutoComplete(int layoutId,ArrayList<String> datas) {
        edit.setThreshold(1);
        mAdapter = new ArrayAdapter<>(mContext, layoutId, datas);
        edit.setAdapter(mAdapter);
    }

    /**
     * 保留小数点位数
     * @param integerNumber 整数最多位数
     * @param decimalNumber 小数点后保留位数
     */
    public void setReservedDecimal(final int integerNumber,final int decimalNumber,InputFilter other){
        edit.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            /*
                source 新输入的字符串
                start 新输入的字符串起始下标，一般为0
                end 新输入的字符串终点下标，一般为source长度-1
                dest 输入之前文本框内容
                dstart 原内容起始坐标，一般为0
                dend 原内容终点坐标，一般为dest长度-1
             */
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String sourceContent = source.toString();
                String lastInputContent = dest.toString();

                if (!Objects.equals(lastInputContent,"") && !Objects.equals(sourceContent,"")){
                    //数字开头,不能有字母
                    if (Character.isDigit(lastInputContent.charAt(0))){
                        if (Character.isLetter(sourceContent.charAt(0)))
                            return "";
                    }
                    //字母开头,不做处理
                    if (Character.isLetter(lastInputContent.charAt(0))){
                        return null;
                    }
                }

                //验证删除等按键
                if (TextUtils.isEmpty(sourceContent)) {
                    return "";
                }
                //以小数点"."开头，默认为设置为“0.”开头
                if (sourceContent.equals(".") && lastInputContent.length() == 0) {
                    return "0.";
                }
                //输入“0”，默认设置为以"0."开头
                if (sourceContent.equals("0") && lastInputContent.length() == 0) {
                    return "0.";
                }
                if (lastInputContent.startsWith("0") && !lastInputContent.contains(".") && !sourceContent.equals(".")){
                    return "." + sourceContent;
                }
                //小数点后保留位数
                if (lastInputContent.contains(".")) {
                    int pointIndex = lastInputContent.indexOf(".");
                    if (dend - pointIndex >= decimalNumber + 1 || sourceContent.equals(".")) {
                        return "";
                    }
                }else {
                    //整数位数
                    if (lastInputContent.length() == integerNumber && !sourceContent.equals(".")){
                        return "." + sourceContent;
                    }
                }
                return null;
            }
        },new InputFilter.LengthFilter(maxLength),
                other != null ? other : new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                        return null;
                    }
                }
        });
    }

    public void setInputFilter(InputFilter inputFilter){
        edit.setFilters(new InputFilter[]{
                inputFilter != null ? inputFilter : new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        return null;
                    }
                },
                new InputFilter.LengthFilter(maxLength)
        });
    }

    public final TextView getIconView(){
        return icon;
    }
    public final TextView getClearView(){
        return clear;
    }
    public final AutoCompleteTextView getInputView(){
        return edit;
    }
    public final void setInputInfo(CharSequence text){
        edit.setText(text);
    }
    public final String getInputInfo(){
        return edit.getText().toString();
    }
    public final void setOnIconEditClearListener(OnIconEditClearListener textChangedListener){
        this.textChangedListener = textChangedListener;
    }

    /**
     * 需要在Application中调用
     * @param iconTypeFace
     */
    public static void init(Typeface iconTypeFace){
        IconEditClearView.iconTypeFace = iconTypeFace;
    }

    public void setErrorInfo(String error){
        tvError.setText(error);
        if (TextUtils.isEmpty(error))
            llyError.setVisibility(View.GONE);
        else
            llyError.setVisibility(View.VISIBLE);
    }

    public void clearError(){
        tvError.setText("");
    }

    public boolean isHasError(){
        return !TextUtils.isEmpty(tvError.getText().toString().trim());
    }

    public void setSeletionEnd(){
        edit.setSelection(true);
    }

    public void setHint(String hint){
        edit.setHint(hint);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        disableSubControls(this,enabled);
    }

    private void disableSubControls(ViewGroup viewGroup,boolean enabled) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup){
                disableSubControls((ViewGroup) v,enabled);
            }else {
                v.setEnabled(enabled);
                v.setClickable(enabled);
            }
        }
    }
}



















package com.sp.xwjlibrary.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;

public class LastInputEditText extends AppCompatAutoCompleteTextView {

    private boolean isSelection;

    public LastInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);  
    }  
  
    public LastInputEditText(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public LastInputEditText(Context context) {  
        super(context);  
    }  

    public void setSelection(boolean isSelection){
        this.isSelection = isSelection;
    }

    @Override  
    protected void onSelectionChanged(int selStart, int selEnd) {  
        super.onSelectionChanged(selStart, selEnd);  
        //保证光标始终在最后面  
        if(selStart == selEnd && isSelection){//防止不能多选
            setSelection(getText().length());  
        }  
          
    }  
}  
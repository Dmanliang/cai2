package com.sp.xwjlibrary.myinterface;

import android.text.Editable;
import android.view.View;

public abstract class OnIconEditClearListener {
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    public void afterTextChanged(Editable s) {}
    public void OnTextChanged(CharSequence s, int start, int before, int count){}
    public void onFocusChange(View view, boolean b){}
}
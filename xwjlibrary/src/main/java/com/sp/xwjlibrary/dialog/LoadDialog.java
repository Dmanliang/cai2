package com.sp.xwjlibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.xwjlibrary.util.AnimationUtil;

public class LoadDialog extends AlertDialog{

	private AnimationDrawable mAnimationDrawable;
	private Context context;
	private int[] drawableResids;
	private ImageView ivLoad;
	private TextView tvHint;
	private LinearLayout llyContent;

	public LoadDialog(Context context, String hint,int[] drawableResids){
		super(context);
		this.context = context;
		this.drawableResids = drawableResids;
		setContentView(hint);
	}

	public LoadDialog(Context context,int[] drawableResids){
		super(context);
		this.context = context;
		this.drawableResids = drawableResids;
		setContentView("");
	}

	@Override
	public void cancel() {
		if (mAnimationDrawable != null)
			mAnimationDrawable.stop();
		if (isShowing())
			super.cancel();
	}

	@Override
	public void show() {
		if (!isShowing())
			super.show();
		if (mAnimationDrawable != null)
			mAnimationDrawable.start();
	}

	private View getLayout(String hint){
		//根布局
		FrameLayout flyRoot = new FrameLayout(context);
		flyRoot.setBackgroundColor(Color.TRANSPARENT);
		//内容布局
		llyContent = new LinearLayout(context);
		llyContent.setOrientation(LinearLayout.VERTICAL);
		llyContent.setPadding(60,100,60,100);
		llyContent.setGravity(Gravity.CENTER);
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(20);
		drawable.setColor(Color.parseColor("#88000000"));
		llyContent.setBackground(drawable);
		//动画载体
		ivLoad = new ImageView(context);
		//提示语言
		tvHint = new TextView(context);
		tvHint.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
		tvHint.setTextColor(ContextCompat.getColor(context,android.R.color.white));
		tvHint.setText(hint);

		llyContent.addView(ivLoad);
		llyContent.addView(tvHint);

		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT,Gravity.CENTER);
		flyRoot.addView(llyContent,params);

		return flyRoot;
	}

	private void setContentView(String hint){
		this.show();
		this.cancel();
//		this.setCancelable(false);
		// 设置点击在区域外不消失
		this.setCanceledOnTouchOutside(false);
		// 获取dialog窗体对象
		Window window = this.getWindow();
		if (window == null) return;
		// 设置dialog窗体样式
		window.setContentView(getLayout(hint));
		window.setBackgroundDrawableResource(android.R.color.transparent);
		window.setDimAmount(0);

		mAnimationDrawable = AnimationUtil.anim(context, drawableResids,60);
		ivLoad.setImageDrawable(mAnimationDrawable);
	}

	public void setHint(String hint){
		tvHint.setText(hint);
	}

	public void setStyle(int bgColor,int hintColor,float radius){
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(radius);
		drawable.setColor(bgColor);
		tvHint.setTextColor(hintColor);
		llyContent.setBackground(drawable);
	}

}
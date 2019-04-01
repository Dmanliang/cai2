package com.sp.xwjlibrary.util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sp.xwjlibrary.R;

public class TextViewUtil {

	private static Runnable action;
	private static int dynamicLoopNum = 0;

	/**
	 * 对指定字符设置颜色大小
	 * 
	 * @param tv
	 * @param str
	 * @param start
	 * @param end
	 * @param color
	 * @param size
	 */
	public static void setStyle(TextView tv,String str,int start,int end,int color,int size){
		SpannableStringBuilder builder = new SpannableStringBuilder(str);
		ColorStateList colors = ColorStateList.valueOf(color);
		TextAppearanceSpan span = new TextAppearanceSpan(null,0,size,colors,null);
		builder.setSpan(span,start,end + 1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv.setText(builder);
	}

	/**
	 * 获取TextView文本字符长度
	 * @param tv
	 * @return
	 */
	public static float getTextWidth(TextView tv){
		TextPaint paint = tv.getPaint();
		paint.setTextSize(tv.getTextSize());
		return paint.measureText(tv.getText().toString());
	}

	/**
	 * 设置TextView切换文本动画
	 * @param tvDynamicMsg
	 * @param datas
	 * @param animTime 动画总时间,单位S
	 * @param pauseTime 文本停顿时间,单位S
	 */
	public static void setVerticalAnim(final TextView tvDynamicMsg, final String[] datas,
									   int animTime,int pauseTime){
		if(action != null){
			System.out.println("action != null");
			ACache.get(tvDynamicMsg.getContext()).put("action","action != null");
			return;
		}
		final int animT = animTime * 1000;
		final int pauseT = pauseTime * 1000;
		action = new Runnable() {
			@Override
			public void run() {
				if (datas.length == 0) {
					System.out.println("datas.length == 0");
					ACache.get(tvDynamicMsg.getContext()).put("datas","datas.length == 0");
					return;
				}
				System.out.println("正常运行");
				ACache.get(tvDynamicMsg.getContext()).put("run","正常运行");
				tvDynamicMsg.setText(datas[dynamicLoopNum % datas.length]);
				tvDynamicMsg.setTag(R.id.tvAnimPos, dynamicLoopNum % datas.length);
				final int smooth = tvDynamicMsg.getMeasuredHeight() + tvDynamicMsg.getPaddingTop();
				final int duration = (animT - pauseT) / 2;

				AnimatorSet set = new AnimatorSet();
				set.playTogether(
						ObjectAnimator.ofFloat(tvDynamicMsg, "translationY", smooth, 0),
						ObjectAnimator.ofFloat(tvDynamicMsg, "alpha", 0, 1)
				);
				set.addListener(new Animator.AnimatorListener() {
					@Override
					public void onAnimationStart(Animator animation) {

					}

					@Override
					public void onAnimationEnd(Animator animation) {
						tvDynamicMsg.postDelayed(new Runnable() {
							@Override
							public void run() {
								AnimatorSet set1 = new AnimatorSet();
								set1.playTogether(
										ObjectAnimator.ofFloat(tvDynamicMsg, "translationY", 0, -smooth),
										ObjectAnimator.ofFloat(tvDynamicMsg, "alpha", 1, 0)
								);
								set1.setDuration(duration);
								set1.start();
							}
						}, pauseT);
					}

					@Override
					public void onAnimationCancel(Animator animation) {

					}

					@Override
					public void onAnimationRepeat(Animator animation) {

					}
				});
				set.setDuration(duration);
				set.start();

				dynamicLoopNum++;
				tvDynamicMsg.postDelayed(this, animT);
			}
		};
		tvDynamicMsg.post(action);
	}

	public static void setSameWidth(TextView[] tvs,LayoutParams params){
		float[] tvsWidth = new float[tvs.length];
		for (int i = 0; i < tvs.length; i++) {
			tvsWidth[i] = getTextWidth(tvs[i]);
		}
		float maxWidth = 0;
		for (int i = 0; i < tvsWidth.length; i++) {
			if (tvsWidth[i] > maxWidth){
				maxWidth = tvsWidth[i];
			}
		}
		if (params == null){
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.gravity = Gravity.CENTER_VERTICAL;
			params = lp;
		}
		params.width = (int) maxWidth;
		for (TextView tv : tvs) {
            tv.setGravity(Gravity.CENTER_VERTICAL);
			tv.setLayoutParams(params);
		}
	}

	/**
	 * 设置超链接
	 * @param tv
	 * @param link
	 * @param clickableSpan
	 * @param sLink
	 * @param eLink
	 */
	public static void setHyperlink(TextView tv, String link, ClickableSpan clickableSpan,
									int sLink, int eLink){
		SpannableString ss = new SpannableString(link);
		ss.setSpan(clickableSpan,sLink,eLink,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv.setText(ss);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
	}

	/**
	 * 设置TextView可滑动
	 */
	public static void setScroll(TextView tv,final ScrollView sv,final ListView lv,final GridView gv){
		/**
		 * 只有调用了该方法，TextView才能不依赖于ScrollView而实现滚动的效果。
		 * 要在XML中设置TextView的textcolor，否则，当TextView被触摸时，会灰掉。
		 */
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());
		tv.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v,MotionEvent event){
				switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						if(sv != null){
							sv.requestDisallowInterceptTouchEvent(true);
						}
						if(lv != null){
							lv.requestDisallowInterceptTouchEvent(true);
						}
						if(gv != null){
							gv.requestDisallowInterceptTouchEvent(true);
						}
						break;
					case MotionEvent.ACTION_UP:
						if(sv != null){
							sv.requestDisallowInterceptTouchEvent(false);
						}
						if(lv != null){
							lv.requestDisallowInterceptTouchEvent(false);
						}
						if(gv != null){
							gv.requestDisallowInterceptTouchEvent(false);
						}
						break;
				}
				return false;
			}
		});
	}

}

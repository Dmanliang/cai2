package com.sp.xwjlibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.xwjlibrary.R;
import com.sp.xwjlibrary.myinterface.OnDialogButtonListener;


public class SimpleDialog extends AlertDialog implements View.OnClickListener{

	public static final int STYLE_SIMPLE = 0x100;
	public static final int STYLE_RIGHT = 0x101;
	public static final int STYLE_SINGLE = 0x102;

	private OnDialogButtonListener onButtonListener;
	private TextView tvTitle;
	private TextView tvContent;
	private Button btnLeft;
	private Button btnRight;
	private Button btnDoubleLeft;
	private Button btnDoubleRight;
	private Button btnSingle;
	private LinearLayout llyDouble;
	private LinearLayout llyDoubleRight;
	private View mDivide;

	public void setColorStyle(int color){
		tvTitle.setTextColor(color);
		btnLeft.setTextColor(color);
		btnRight.setTextColor(color);
		mDivide.setBackgroundColor(color);
	}

	public SimpleDialog(Context context){
		super(context);

		//设置对话框布局样式
		setContentView();
	}

	public void setOnDialogButtonListener(OnDialogButtonListener onButtonListener){
		this.onButtonListener = onButtonListener;
	}

	public SimpleDialog setTitle(String title){
		tvTitle.setText(title);
		return this;
	}

	public SimpleDialog setContent(String content){
		tvContent.setText(content);
		return this;
	}

	public SimpleDialog setText(String leftStr,String rightStr){
		btnLeft.setText(leftStr);
		btnRight.setText(rightStr);
		btnDoubleLeft.setText(leftStr);
		btnDoubleRight.setText(rightStr);
		btnSingle.setText(rightStr);
		return this;
	}

	public SimpleDialog setStyle(int style){
		switch(style){
		    case STYLE_SIMPLE:
				llyDouble.setVisibility(View.VISIBLE);
				llyDoubleRight.setVisibility(View.GONE);
				btnSingle.setVisibility(View.GONE);
		        break;
		    case STYLE_RIGHT:
				llyDouble.setVisibility(View.GONE);
				llyDoubleRight.setVisibility(View.VISIBLE);
				btnSingle.setVisibility(View.GONE);
		        break;
		    case STYLE_SINGLE:
				llyDouble.setVisibility(View.GONE);
				llyDoubleRight.setVisibility(View.GONE);
				btnSingle.setVisibility(View.VISIBLE);
		        break;
		}
		return this;
	}

	private void setContentView(){
		this.show();
		// 设置点击在区域外不消失
		this.setCanceledOnTouchOutside(false);
		// 获取dialog窗体对象
		Window window = this.getWindow();
		// 设置dialog窗体样式
		window.setContentView(R.layout.dialog_simple);
		window.setBackgroundDrawableResource(android.R.color.transparent);
		this.cancel();
		//点击返回键不取消
		this.setCancelable(false);

		btnSingle = window.findViewById(R.id.btn_single);
		llyDouble = window.findViewById(R.id.lly_double);
		llyDoubleRight = window.findViewById(R.id.lly_double_right);
		tvTitle = window.findViewById(R.id.tv_title);
		tvContent = window.findViewById(R.id.tv_content);
		btnLeft = window.findViewById(R.id.btn_left);
		btnRight = window.findViewById(R.id.btn_right);
		btnDoubleLeft = window.findViewById(R.id.btn_double_left);
		btnDoubleRight = window.findViewById(R.id.btn_double_right);
		mDivide = window.findViewById(R.id.dialog_simple_divide);
		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		btnSingle.setOnClickListener(this);
		btnDoubleLeft.setOnClickListener(this);
		btnDoubleRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v){
		if (onButtonListener != null){
			if (v == btnLeft || v == btnDoubleLeft){
				onButtonListener.onLeftBtn();
			}else if (v == btnRight || v == btnSingle || v == btnDoubleRight){
				onButtonListener.onRightBtn();
			}
		}
		this.cancel();
	}

}

package com.sp.xwjlibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sp.xwjlibrary.R;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.HolderUtil;
import com.sp.xwjlibrary.widget.ActionBarLayout;


public abstract class RecyclerViewDialog<VH extends RecyclerView.ViewHolder> extends AlertDialog {

    public static final int STYLE_DEFAULT = 0;
    public static final int STYLE_BOTTOM_CONTINUE = 1;
    public static final int STYLE_BOTTOM_DIVIDE = 2;
    public static final int ID_HEADER_LEFT = 0X1001;
    public static final int ID_HEADER_RIGHT = 0X1002;
    public static final int ID_FOOTER = 0X1003;

    private Context mContext;
    private final Window mWindow;
    private ActionBarLayout ablHeader;
    private TextView tvFooter;
    private ImageView ivHeaderLine;
    private ImageView ivFooterLine;
    private RecyclerView rcvContent;
    private MyAdapter mAdapter;
    private boolean outside;
    private boolean able;

    public RecyclerViewDialog(Context context) {
        super(context);
        mContext = context;
        this.show();
        this.cancel();
        mWindow = this.getWindow();
    }

    public RecyclerViewDialog<VH> setStyle(int style){

        mWindow.setContentView(R.layout.dialog_recyclerview);
        mWindow.setBackgroundDrawableResource(android.R.color.transparent);
        mWindow.setWindowAnimations(R.style.bottomEnterAndExit);

        ablHeader = mWindow.findViewById(R.id.abl_header);
        tvFooter = mWindow.findViewById(R.id.tv_footer);
        ivHeaderLine = mWindow.findViewById(R.id.iv_header_line);
        ivFooterLine = mWindow.findViewById(R.id.iv_footer_line);
        rcvContent = mWindow.findViewById(R.id.rcv_content);
        LinearLayout llyRoot = mWindow.findViewById(R.id.lly_root);

        ablHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case ActionBarLayout.ID_LEFT_TEXT_VIEW:
                        RecyclerViewDialog.this.onClick(ID_HEADER_LEFT);
                        break;
                    case ActionBarLayout.ID_RIGHT_TEXT_VIEW:
                        RecyclerViewDialog.this.onClick(ID_HEADER_RIGHT);
                        break;
                }
            }
        });
        tvFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewDialog.this.onClick(ID_FOOTER);
            }
        });

        ViewGroup.MarginLayoutParams rootParams = (ViewGroup.MarginLayoutParams) llyRoot.getLayoutParams();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        switch(style){
            case STYLE_DEFAULT:
                mWindow.setGravity(Gravity.CENTER);
                params.width = mContext.getResources().getDisplayMetrics().widthPixels * 2 / 3;
                params.height = mContext.getResources().getDisplayMetrics().heightPixels / 2;
                ablHeader.setBackgroundResource(R.drawable.round_corner_top_radius_whitebg);
                rcvContent.setBackgroundResource(R.drawable.round_corner_bottom_radius_whitebg);
                break;
            case STYLE_BOTTOM_CONTINUE:
                mWindow.setGravity(Gravity.BOTTOM);
                params.width = mContext.getResources().getDisplayMetrics().widthPixels;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                rootParams.topMargin = mContext.getResources().getDisplayMetrics().heightPixels / 2;
                break;
            case STYLE_BOTTOM_DIVIDE:
                int padSize = 24;
                llyRoot.setPadding(padSize,padSize,padSize,padSize);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.topMargin = padSize;
                tvFooter.setLayoutParams(lp);
                rcvContent.setBackgroundResource(R.drawable.bg_white_r10);
                tvFooter.setBackgroundResource(R.drawable.bg_white_r10);

                mWindow.setGravity(Gravity.BOTTOM);
                params.width = mContext.getResources().getDisplayMetrics().widthPixels;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                rootParams.topMargin = mContext.getResources().getDisplayMetrics().heightPixels / 2;
                tvFooter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancel();
                    }
                });
                break;
        }
        mWindow.setAttributes(params);
        llyRoot.setLayoutParams(rootParams);

        mAdapter = new MyAdapter();
        mAdapter.registerPageLoad(rcvContent);
        rcvContent.setAdapter(mAdapter);

        return this;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (HolderUtil.isClickOutsideArea(rcvContent, event) && outside && able)
            cancel();
        return super.onTouchEvent(event);
    }

    public RecyclerViewDialog<VH> setGridLayoutManager(int spanCount,int orientation){
        GridLayoutManager manager = new GridLayoutManager(mContext,spanCount, orientation,false);
        rcvContent.setLayoutManager(manager);
        return this;
    }

    public RecyclerViewDialog<VH> setLinearLayoutManager(int orientation){
        LinearLayoutManager manager = new LinearLayoutManager(mContext, orientation,false);
        rcvContent.setLayoutManager(manager);
        return this;
    }

    private class MyAdapter extends RVBaseAdap<VH> {

        @Override
        public VH onCreateHolder(ViewGroup parent, int viewType) {
            return RecyclerViewDialog.this.onCreateHolder(parent,viewType);
        }

        @Override
        public int getCount() {
            return RecyclerViewDialog.this.getCount();
        }

        @Override
        public void onBindHolder(VH holder, int position) {
            RecyclerViewDialog.this.onBindHolder(holder,position);
        }

        @Override
        public void onItemClick(View itemView, int position) {
            RecyclerViewDialog.this.onItemClick(itemView,position);
            cancel();
        }

        @Override
        public void onPageLoad() {
            RecyclerViewDialog.this.onPageLoad();
        }
    }

    public void performItemClick(int position){
        RecyclerViewDialog.this.onItemClick(rcvContent.getChildAt(position),position);
    }

    public void notifyDatasChang(){
        mAdapter.notifyDatasChang();
    }
    public abstract VH onCreateHolder(ViewGroup parent, int viewType);
    public abstract int getCount();
    public abstract void onBindHolder(VH holder, int position);
    public void onItemClick(View itemView, int position){}
    public void onClick(int id){}
    public void onPageLoad(){}

    public RecyclerViewDialog<VH> setCancel(boolean outside, boolean able){
        this.outside = outside;
        this.able = able;
        this.setCanceledOnTouchOutside(outside);
        this.setCancelable(able);
        return this;
    }

    public void showDivide(boolean header,boolean footer){
        if (header){
            ivHeaderLine.setVisibility(View.VISIBLE);
        }else {
            ivHeaderLine.setVisibility(View.GONE);
        }
        if (footer){
            ivFooterLine.setVisibility(View.VISIBLE);
        }else {
            ivFooterLine.setVisibility(View.GONE);
        }
    }

    public RecyclerViewDialog<VH> setHeaderText(CharSequence left,CharSequence title,CharSequence right){
        ablHeader.setVisibility(View.VISIBLE);
        ablHeader.getView(0).setText(left);
        ablHeader.getView(1).setText(title);
        ablHeader.getView(2).setText(right);
        return this;
    }

    public RecyclerViewDialog<VH> setHeaderColor(int left,int title,int right){
        ablHeader.getView(0).setTextColor(left);
        ablHeader.getView(1).setTextColor(title);
        ablHeader.getView(2).setTextColor(right);
        return this;
    }

    public RecyclerViewDialog<VH> setHeaderBg(int bgRes){
        ablHeader.setBackgroundResource(bgRes);
        return this;
    }

    public RecyclerViewDialog<VH> setHeaderSize(int left,int title,int right){
        ablHeader.getView(0).setTextSize(TypedValue.COMPLEX_UNIT_SP,left);
        ablHeader.getView(1).setTextSize(TypedValue.COMPLEX_UNIT_SP,title);
        ablHeader.getView(2).setTextSize(TypedValue.COMPLEX_UNIT_SP,right);
        return this;
    }

    public RecyclerViewDialog<VH> setFooterText(CharSequence cancle){
        tvFooter.setVisibility(View.VISIBLE);
        tvFooter.setText(cancle);
        return this;
    }

    public RecyclerViewDialog<VH> setFooterColor(int color){
        tvFooter.setTextColor(color);
        return this;
    }

    public RecyclerViewDialog<VH> setFooterBg(int bgRes){
        tvFooter.setBackgroundResource(bgRes);
        return this;
    }

    public RecyclerViewDialog<VH> setFooterSize(int size){
        tvFooter.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
        return this;
    }

    public RecyclerViewDialog<VH> setFooterGravity(int gravity){
        tvFooter.setGravity(gravity);
        return this;
    }

}

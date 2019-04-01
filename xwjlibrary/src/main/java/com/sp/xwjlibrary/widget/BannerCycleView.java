package com.sp.xwjlibrary.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sp.xwjlibrary.R;

/**
 * Created by vinson on 2017/1/4.
 */

public class BannerCycleView extends FrameLayout {

    private Context mContext;
    private ViewPager mViewPager;
    private LinearLayout llyIndicatorContain;
    private OnBannerCycleListener mCallback;
    private Bitmap focusIndicatorStyle;
    private Bitmap unfocusIndicatorStyle;
    /** 轮播图片的数量 */
    private int mCount = 0;
    /** 总数量 */
    private int mBannerCount = 1000000;
    /** 自动轮播时间间隔默认5秒 */
    private int mCycleDelayed = 5000;
    /** 是否自动播放,默认true */
    private boolean isAutoCycle = true;
    private PagerAdapter mPagerAdapter;
    private int lastPosition = 0;
    private float pageScale;

    public enum IndicationStyle {
        COLOR, IMAGE
    }

    public interface OnBannerCycleListener{
        void onBannerCycleView(ImageView view, int index);
        void onBannerClick(View v, int index);
        void onBannerIsZero(ImageView view);
    }

    public BannerCycleView(Context context) {
        super(context);
        try{
            initLayout(context);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BannerCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        try{
            initLayout(context);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BannerCycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try{
            initLayout(context);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 设置两边显示一部分
     * @param widthScale 宽度为手机屏幕宽度的比例;最佳显示效果:0.75
     * @param whScale 宽高比;
     * @param pageScale 两边页面缩小比例,为1不缩放,有缩小值时会自动生成页面间距
     * @param pageMargin 页面间距,不设为0
     */
    public void setShowBothSidesPart(float widthScale, final float whScale,float pageScale,int pageMargin){
        this.pageScale = pageScale;
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        final int pagerWidth = (int) (widthPixels * widthScale);
        ViewGroup.LayoutParams lp = mViewPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, (int) (pagerWidth * whScale));
        } else {
            lp.width = pagerWidth;
            lp.height = (int) (pagerWidth * whScale);
        }
        mViewPager.setLayoutParams(lp);
        mViewPager.setPageMargin(pageMargin);
        mViewPager.setPageTransformer(true,new MyGallyPageTransformer());
        setIndicatorStyle(IndicationStyle.COLOR,20,20,Color.WHITE,Color.GRAY);
        llyIndicatorContain.setGravity(Gravity.CENTER_HORIZONTAL);
        mViewPager.post(new Runnable() {
            @Override
            public void run() {
                int holderHeight = BannerCycleView.this.getMeasuredHeight();
                llyIndicatorContain.setPadding(0,0,0, (holderHeight - (int) (pagerWidth * whScale)) / 2);
            }
        });

    }

    private void initLayout(Context context){
        mContext = context;
        unfocusIndicatorStyle = drawOval(20,15, Color.GRAY);
        focusIndicatorStyle = drawOval(20,15, Color.WHITE);
        View.inflate(context, R.layout.banner_cycle,this);
        mViewPager = (ViewPager) findViewById(R.id.vp_banner_cycle);
        llyIndicatorContain = (LinearLayout) findViewById(R.id.lly_indicator_contain);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                position = position % mCount;
                ImageView lastImageView = (ImageView) llyIndicatorContain.getChildAt(lastPosition);
                lastImageView.setImageBitmap(unfocusIndicatorStyle);

                ImageView imageView = (ImageView) llyIndicatorContain.getChildAt(position);
                imageView.setImageBitmap(focusIndicatorStyle);

                lastPosition = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) { }
        });

    }

    public class MyGallyPageTransformer implements ViewPager.PageTransformer {
        private static final float min_scale = 0.85f;

        @Override
        public void transformPage(View page, float position) {
            float scaleFactor = Math.max(pageScale == 0 ? min_scale : pageScale, 1 - Math.abs(position));
            if (position < -1) {
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            } else if (position < 0) {
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            } else if (position >= 0 && position < 1) {
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            } else if (position >= 1) {
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            }
        }
    }

    public void requestDisallowInterceptTouchEvent(final View view){
        mViewPager.setOnTouchListener(new OnTouchListener() {

            private boolean b;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(!b){
                            view.setEnabled(false);
                            b = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setEnabled(true);
                        b = false;
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 数据加载
     * @param count
     * @param callback
     */
    public void loadData(int count,OnBannerCycleListener callback){
        if(count == 0){
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(imageView);
            callback.onBannerIsZero(imageView);
            return;
        }
        mCount = count;
        mCallback = callback;
        //初始化指示器
        initIndication();
        //设置ViewPager适配器
        mViewPager.setOffscreenPageLimit(count);
        setPagerAdapter();
        // 最大值中间 的第一个
        mViewPager.setCurrentItem(mBannerCount / 2 - ((mBannerCount / 2) % mCount));
    }

    public void notifyDataChanges(int count){
        if(count == 0){
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(imageView);
            mCallback.onBannerIsZero(imageView);
            return;
        }
        mCount = count;
        lastPosition = 0;
        initIndication();
        mViewPager.setOffscreenPageLimit(count);
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(mBannerCount / 2 - ((mBannerCount / 2) % mCount));
    }

    /**
     * 设置ViewPager适配器
     */
    private void setPagerAdapter() {
        mPagerAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                final int index = position % mCount;
                ImageView imageView = new ImageView(mContext);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCallback.onBannerClick(v, index);
                    }
                });
                mCallback.onBannerCycleView(imageView, index);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return mBannerCount;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }
        };
        mViewPager.setAdapter(mPagerAdapter);
    }

    /**
     * 当width == height为正圆
     * @param style 图片(IMAGE)或者颜色(COLOR)
     * @param width
     * @param height
     * @param focus
     * @param unfocus
     */
    public void setIndicatorStyle(IndicationStyle style,int width,int height,int focus, int unfocus){
        if(style == IndicationStyle.COLOR){
            focusIndicatorStyle = drawOval(width,height, focus);
            unfocusIndicatorStyle = drawOval(width,height, unfocus);
        }else if(style == IndicationStyle.IMAGE){
            focusIndicatorStyle = BitmapFactory.decodeResource(mContext.getResources(), focus);
            unfocusIndicatorStyle = BitmapFactory.decodeResource(mContext.getResources(), unfocus);
        }
        initIndication();
    }

    public void setIndicatorGravity(int gravity){
        llyIndicatorContain.setGravity(gravity);
    }

    /**
     * 初始化指标器
     */
    private void initIndication() {
        llyIndicatorContain.removeAllViews();
        for (int i = 0; i < mCount; i++) {
            ImageView imageView = new ImageView(mContext);
            if(i == 0){
                imageView.setImageBitmap(focusIndicatorStyle);
            }else{
                imageView.setImageBitmap(unfocusIndicatorStyle);
            }
            llyIndicatorContain.addView(imageView);
        }
    }

	/**
     * 当width == height为正圆
     * @param width
     * @param height
     * @param color
     * @return
     */
    private Bitmap drawOval(int width, int height, int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        Bitmap bitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        RectF oval = new RectF(0, 0, width, height);// 设置个新的长方形，扫描测量
        canvas.drawArc(oval, width / 2, height / 2, true, paint);
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        //画椭圆，把oval改一下
        oval.set(0, 0, width, height);
        canvas.drawOval(oval, paint);
        return bitmap;
    }

    /**
     * 设置是否自动无限轮播
     *
     * @param state
     * @param delay 自动轮播时间间隔,state为false无效
     */
    public void setAutoCycle(Boolean state,int delay) {
        isAutoCycle = state;
        mCycleDelayed = delay;
    }

    /**
     * 实现自动轮播
     */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (mViewPager != null) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                handler.sendEmptyMessageDelayed(0, mCycleDelayed);
            }
            return false;
        }
    });

    /**
     * 触摸停止计时器，抬起启动计时器
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isAutoCycle) {
                // 开始图片滚动
                handler.sendEmptyMessageDelayed(0, mCycleDelayed);
            }
        } else {
            if (isAutoCycle) {
                // 停止图片滚动
                handler.removeCallbacksAndMessages(null);
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 停止图片滚动
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isAutoCycle) {
            handler.sendEmptyMessageDelayed(0, mCycleDelayed);
        }
    }

}






















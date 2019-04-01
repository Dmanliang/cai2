package com.sp.xwjlibrary.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by vinson on 2017/12/4.
 */

public class BitmapUtil {

    /*--------------------任意角圆角常量start--------------------*/
    public static final int CORNER_NONE = 0;
    public static final int CORNER_TOP_LEFT = 1;
    public static final int CORNER_TOP_RIGHT = 1 << 1;
    public static final int CORNER_BOTTOM_LEFT = 1 << 2;
    public static final int CORNER_BOTTOM_RIGHT = 1 << 3;
    public static final int CORNER_ALL = CORNER_TOP_LEFT | CORNER_TOP_RIGHT | CORNER_BOTTOM_LEFT | CORNER_BOTTOM_RIGHT;
    public static final int CORNER_TOP = CORNER_TOP_LEFT | CORNER_TOP_RIGHT;
    public static final int CORNER_BOTTOM = CORNER_BOTTOM_LEFT | CORNER_BOTTOM_RIGHT;
    public static final int CORNER_LEFT = CORNER_TOP_LEFT | CORNER_BOTTOM_LEFT;
    public static final int CORNER_RIGHT = CORNER_TOP_RIGHT | CORNER_BOTTOM_RIGHT;
    /*--------------------任意角圆角常量end--------------------*/

    /**
     * 创建圆形图片
     * @param bmp Bitmap to crop
     * @return
     */
    public static Bitmap createCircleImage(Bitmap bmp) {
        Bitmap sbmp = scaleBitmap(bmp, bmp.getWidth(), bmp.getHeight());

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);

        canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f,
                sbmp.getWidth() / 2.1f + 0.1f, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }

    /**
     * Scale bitmap keep aspect-ratio
     * @param original bitmap to scale
     * @param width
     * @param height
     * @return
     */
    private static Bitmap scaleBitmap(Bitmap original, int width, int height) {
        Bitmap background = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        float originalWidth = original.getWidth();
        float originalHeight = original.getHeight();
        Canvas canvas = new Canvas(background);
        float scaleX = width / originalWidth;
        float scaleY = height / originalHeight;
        float xTranslation = (width - originalWidth * scaleX) / 2.0f;
        float yTranslation = (height - originalHeight * scaleY) / 1.0f;
        Matrix transformation = new Matrix();
        transformation.postTranslate(xTranslation, yTranslation);
        transformation.preScale(scaleX, scaleY);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(original, transformation, paint);
        return background;
    }

    /**
     * 四角圆角
     * @param source
     * @param radius
     * @return
     */
    public static Bitmap createRoundConerImage(Bitmap source,int radius){
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rect, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /*--------------------任意角圆角方法start--------------------*/
    /**
     * 任意角圆角
     * @param bitmap
     * @param roundPx
     * @param corners
     * @return
     */
    public static Bitmap createRoundConerImage(Bitmap bitmap, int roundPx,int corners) {
        try {
            // 其原理就是：先建立一个与图片大小相同的透明的Bitmap画板
            // 然后在画板上画出一个想要的形状的区域。
            // 最后把源图片帖上。
            final int width = bitmap.getWidth();
            final int height = bitmap.getHeight();

            Bitmap paintingBoard = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(paintingBoard);
            canvas.drawARGB(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);

            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);

            //画出4个圆角
            final RectF rectF = new RectF(0, 0, width, height);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            //把不需要的圆角去掉
            int notRoundedCorners = corners ^ CORNER_ALL;
            if ((notRoundedCorners & CORNER_TOP_LEFT) != 0) {
                clipTopLeft(canvas,paint,roundPx,width,height);
            }
            if ((notRoundedCorners & CORNER_TOP_RIGHT) != 0) {
                clipTopRight(canvas, paint, roundPx, width, height);
            }
            if ((notRoundedCorners & CORNER_BOTTOM_LEFT) != 0) {
                clipBottomLeft(canvas,paint,roundPx,width,height);
            }
            if ((notRoundedCorners & CORNER_BOTTOM_RIGHT) != 0) {
                clipBottomRight(canvas, paint, roundPx, width, height);
            }
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

            //帖子图
            final Rect src = new Rect(0, 0, width, height);
            final Rect dst = src;
            canvas.drawBitmap(bitmap, src, dst, paint);
            return paintingBoard;
        } catch (Exception exp) {
            return bitmap;
        }
    }

    private static void clipTopLeft(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(0, 0, offset, offset);
        canvas.drawRect(block, paint);
    }

    private static void clipTopRight(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(width - offset, 0, width, offset);
        canvas.drawRect(block, paint);
    }

    private static void clipBottomLeft(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(0, height - offset, offset, height);
        canvas.drawRect(block, paint);
    }

    private static void clipBottomRight(final Canvas canvas, final Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(width - offset, height - offset, width, height);
        canvas.drawRect(block, paint);
    }
    /*--------------------任意角圆角方法end--------------------*/

    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 保存Bmp到sdcard
     * @param b
     * @param format 图片格式,例:Bitmap.CompressFormat.JPEG
     * @param imgPath 图片保存路径
     * @param quality 图片压缩后的质量0~100,100最佳,0最差
     */
    public static void saveBitmap(Bitmap b, Bitmap.CompressFormat format, String imgPath, int quality){
        try {
            FileOutputStream fout = new FileOutputStream(imgPath);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(format,quality, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存Bmp到sdcard,默认jpg,质量80
     * @param bmp
     * @param imgPath 图片保存路径
     */
    public static void saveBitmap(Bitmap bmp,String imgPath){
        saveBitmap(bmp, Bitmap.CompressFormat.JPEG,imgPath,80);
    }

    public static Bitmap compressBitmap(Bitmap bmp, int pixelW, int pixelH){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, os);
        while ( os.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            os.reset();//重置baos即清空baos
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, os);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = pixelH;// 设置高度为240f时，可以明显看到图片缩小了
        float ww = pixelW;// 设置宽度为120f，可以明显看到图片缩小了
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0) be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        is = new ByteArrayInputStream(os.toByteArray());
        bmp = BitmapFactory.decodeStream(is, null, newOpts);
        //压缩好比例大小后再进行质量压缩
//      return compress(bitmap, maxSize); // 这里再进行质量压缩的意义不大，反而耗资源，删除
        return bmp;
    }

    /**
     * 读取Bmp
     * @param imgPath
     * @return
     */
    public static Bitmap readBitmap(String imgPath){
        return BitmapFactory.decodeFile(imgPath);
    }

    /**
     * 读取Bmp
     * @param imgPath
     * @param format
     * @param quality
     * @return
     */
    public static Bitmap readBitmap(String imgPath,Bitmap.CompressFormat format,int quality){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
        bitmap.compress(format,quality,baos);
        return bitmap;
    }

    public static Bitmap readBitmap(Resources res,int resId){
        Bitmap bitmap = BitmapFactory.decodeResource(res, resId);
        return bitmap;
    }

    /**
     * 压缩图片
     *
     * @param bitmap  要压缩的bitmap对象
     * @param maxSize 压缩的大小(kb)不是很准确大约比输入值大于100k是因为比例决定的
     * @return
     */
    public Bitmap compressBitmap(Bitmap bitmap, double maxSize) {
        if (bitmap != null) {
            //将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            //将字节换成KB
            double mid = b.length / 1024;
            //判断bitmap占用空间是否大于允许最大空间  如果大于则压缩 小于则不压缩
            if (mid > maxSize) {
                //获取bitmap大小 是允许最大大小的多少倍
                double i = mid / maxSize;
                //开始压缩  此处用到平方根 将宽带和高度压缩掉对应的平方根倍 （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
                bitmap = zoomBitmap(bitmap, bitmap.getWidth() / Math.sqrt(i),
                        bitmap.getHeight() / Math.sqrt(i));
            }
        }
        return bitmap;
    }


    /***
     * 图片的缩放方法
     *
     * @param bmp   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bmp, double newWidth,double newHeight) {
        // 获取这个图片的宽和高
        float width = bmp.getWidth();
        float height = bmp.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bmp, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    /**
     * 缩放图片,通过限制高度缩放比例,小于指定值则显示原图,大于则缩放到指定值比例
     * @param bitmap
     * @param height
     * @param isAllowEnlarge 是否允许放大,此时绝对缩放
     * @return
     */
    public static Bitmap zoomBitmapByHeight(Bitmap bitmap,int height,boolean isAllowEnlarge){
        int width = height * bitmap.getWidth() / bitmap.getHeight();
        if (bitmap.getWidth() < width && bitmap.getHeight() < height && !isAllowEnlarge){
            width = bitmap.getWidth();
            height = bitmap.getHeight();
        }
        return zoomBitmap(bitmap,width, height);
    }

    /**
     * 缩放图片,通过限制宽度缩放比例,小于指定值则显示原图,大于则缩放到指定值比例
     * @param bitmap
     * @param width
     * @param isAllowEnlarge 是否允许放大,此时绝对缩放
     * @return
     */
    public static Bitmap zoomBitmapByWidth(Bitmap bitmap,int width,boolean isAllowEnlarge){
        int height = width * bitmap.getHeight() / bitmap.getWidth();
        if (bitmap.getWidth() < width && bitmap.getHeight() < height && !isAllowEnlarge){
            width = bitmap.getWidth();
            height = bitmap.getHeight();
        }
        return zoomBitmap(bitmap,width, height);
    }

    /**
     * 生成SpannaleString类型图片,在edittext或textview输入图片
     * @param context
     * @param bitmap 图片bmp
     * @return
     */
    public static SpannableString createSpannaleStringBmp(Context context, Bitmap bitmap) {
        String tag = "[图片]";
        try {
            ImageSpan imageSpan = new ImageSpan(context, bitmap);
            SpannableString ss = new SpannableString(tag);
            ss.setSpan(imageSpan, 0, tag.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SpannableString(tag);
    }

    /**
     * 生成SpannaleString类型图片,在edittext或textview输入图片
     * @param context
     * @param resId 图片资源id
     * @return
     */
    public static SpannableString createSpannaleStringBmpByResId(Context context, int resId) {
        String tag = "[图片]";
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
            ImageSpan imageSpan = new ImageSpan(context, bitmap);
            SpannableString ss = new SpannableString(tag);
            ss.setSpan(imageSpan, 0, tag.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SpannableString(tag);
    }

}

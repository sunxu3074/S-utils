package xyz.isunxu.sutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;
import android.view.Window;
import java.io.ByteArrayOutputStream;

/**
 * Created by sunxu on 16/1/27.
 */
public class BitmapUtils {

    /**
     * bitmap 转换为byte[]
     * @param bitmap
     * @return
     */
    public static byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    /**
     * 获取屏幕截图
     * @param context
     * @return bitmap
     */
    public static Bitmap getWindowBitmap(Context context) {
        Activity activity = (Activity) context;
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    /**
     * @param bm
     * @param tone 色调
     * @param saturation 饱和度
     * @param lum 亮度
     * @return 生成新的bitmap
     */
    public static Bitmap handleImageMatrix(Bitmap bm,float tone,float saturation,float lum) {
        Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        // 色调
        ColorMatrix toneMatrix = new ColorMatrix();
        toneMatrix.setRotate(0, tone);
        toneMatrix.setRotate(1, tone);
        toneMatrix.setRotate(2, tone);

        // 饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        // 亮度
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(toneMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm,0,0,paint);
        return bitmap;
    }
}


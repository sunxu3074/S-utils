package xyz.isunxu.sutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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
}


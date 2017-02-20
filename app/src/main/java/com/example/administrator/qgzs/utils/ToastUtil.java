package com.example.administrator.qgzs.utils;

import android.widget.Toast;
import com.example.administrator.qgzs.MainAPP;

/**
 * Created by 陈相宇 on 2017/1/22.
 */

public class ToastUtil {

    private static Toast mToast = null;
    private static long lastClickTime;

    // 防止连续点击按钮
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1900) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    static {
        mToast = Toast.makeText(MainAPP.app, "",
                Toast.LENGTH_SHORT);
    }

    public static void showToast(String str) {
        try {
            mToast.setText(str);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(int resId) {
        try {
            mToast.setText(resId);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

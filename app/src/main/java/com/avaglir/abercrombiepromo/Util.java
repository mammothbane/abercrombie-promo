package com.avaglir.abercrombiepromo;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;

/**
 * Created by mammothbane on 9/11/2015.
 */
public class Util {
    public static boolean isAfterLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static int pixelsFromDp(int dp, Context context) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}

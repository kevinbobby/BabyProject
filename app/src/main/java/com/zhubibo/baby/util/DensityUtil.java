package com.zhubibo.baby.util;

import android.content.Context;

/**
 * Created by kevin on 2017/7/5.
 */

public class DensityUtil {

    public static final int dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static final int px2dp(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }
}

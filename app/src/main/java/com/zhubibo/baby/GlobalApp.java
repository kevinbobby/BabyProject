package com.zhubibo.baby;

import android.app.Application;

import com.zhubibo.baby.util.PreferenceUtil;

/**
 * Created by kevin on 2017/6/19.
 */

public class GlobalApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceUtil.init(this);
    }
}

package com.zhubibo.baby;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by kevin on 2017/6/16.
 * 获取音量改变的广播
 */
public class MyVolumeReceiver extends BroadcastReceiver {

    private VolumeChangedActionCallback callback;

    public void setCallback(VolumeChangedActionCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
            callback.updateVolume();
        }
    }

    interface VolumeChangedActionCallback {

        void updateVolume();
    }
}

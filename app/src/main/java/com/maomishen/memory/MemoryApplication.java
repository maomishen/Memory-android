package com.maomishen.memory;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by lunagao on 2017/7/9.
 * Project for Memory.
 */

public class MemoryApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }

}

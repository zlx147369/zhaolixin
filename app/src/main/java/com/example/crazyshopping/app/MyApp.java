package com.example.crazyshopping.app;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import java.util.ArrayList;

import leakcanary.LeakCanary;

public class MyApp extends MultiDexApplication {

    public static ArrayList<Integer> levellist;


    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        MultiDex.install(this);

        LeakCanary.Config config = LeakCanary.getConfig().newBuilder()
                .retainedVisibleThreshold(3)
                .computeRetainedHeapSize(false)
                .build();
        LeakCanary.setConfig(config);

    }

}

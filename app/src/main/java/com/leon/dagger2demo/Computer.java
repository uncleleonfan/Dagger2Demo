package com.leon.dagger2demo;


import android.util.Log;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

public class Computer {

    private static final String TAG = "Computer";

    @Inject
    Cpu mCpu;

    @Inject
    Lazy<Usb> mUsb;

    @Inject
    Provider<Network> mNetwork;

    @Inject
    @MouseType("wireless")
    Mouse mMouse;

    public void init() {
        DaggerComputerComponent.builder().build().inject(this);

        Log.d(TAG, "init: " + mUsb.get());
        Log.d(TAG, "init: " + mUsb.get());

        Log.d(TAG, "init: " + mNetwork.get());
        Log.d(TAG, "init: " + mNetwork.get());

        Log.d(TAG, "init: " + mMouse.name());
    }

}

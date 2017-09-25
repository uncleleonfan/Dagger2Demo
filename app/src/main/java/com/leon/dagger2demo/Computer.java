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

    @Inject
    MainBoard mainBoard1;

    @Inject
    MainBoard mainBoard2;

    @Inject
    HardDisk250G mHardDisk250G;



    public void init() {
        DaggerComputerComponent.builder()
                .hardDiskComponent(DaggerHardDiskComponent.create())
                .build().inject(this);

        Log.d(TAG, "init: " + mUsb.get());
        Log.d(TAG, "init: " + mUsb.get());

        Log.d(TAG, "init: " + mNetwork.get());
        Log.d(TAG, "init: " + mNetwork.get());

        Log.d(TAG, "init: " + mMouse.name());

        Log.d(TAG, "init: " + mainBoard1);
        Log.d(TAG, "init: " + mainBoard2);

        Log.d(TAG, "init: " + mHardDisk250G);

    }

}

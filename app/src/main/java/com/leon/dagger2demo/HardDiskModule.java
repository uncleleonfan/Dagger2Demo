package com.leon.dagger2demo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HardDiskModule {

    @Singleton
    @Provides
    HardDisk250G provideHardDisk250G(){
        return new HardDisk250G();
    }

    @Provides
    HardDisk500G provideHardDisk500G(){
        return new HardDisk500G();
    }
}

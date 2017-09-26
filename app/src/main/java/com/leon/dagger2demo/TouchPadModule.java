package com.leon.dagger2demo;

import dagger.Module;
import dagger.Provides;

@Module
public class TouchPadModule {

//    @MyScope
    @Provides
    TouchPad provideTouchPad(){
        return new TouchPad();
    }
}

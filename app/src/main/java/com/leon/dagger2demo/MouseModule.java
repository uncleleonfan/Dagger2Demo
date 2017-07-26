package com.leon.dagger2demo;


import dagger.Module;
import dagger.Provides;

@Module
public class MouseModule {

    @Provides
    @MouseType("wired")
    Mouse provideWiredMouse() {
        return new WiredMouse();
    }

    @Provides
    @MouseType("wireless")
    Mouse provideWirelessMouse() {
        return new WirelessMouse();
    }
}

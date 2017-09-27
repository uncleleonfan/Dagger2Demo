package com.leon.dagger2demo;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AndroidInjectionModule.class, ActivityModule.class})
public interface AppComponent {

    void inject(DemoApplication instance);

}

package com.leon.dagger2demo;

import dagger.Component;

@Component(modules = TextViewModule.class)
public interface MainComponent {

    void inject(MainActivity activity);
}



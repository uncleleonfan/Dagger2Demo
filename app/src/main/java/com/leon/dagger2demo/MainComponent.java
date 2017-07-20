package com.leon.dagger2demo;

import dagger.Component;

@Component
public interface MainComponent {

    void inject(MainActivity activity);
}

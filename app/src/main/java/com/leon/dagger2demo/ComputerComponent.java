package com.leon.dagger2demo;

import dagger.Component;

@Component(modules = MouseModule.class)
public interface ComputerComponent {
    void inject(Computer computer);
}

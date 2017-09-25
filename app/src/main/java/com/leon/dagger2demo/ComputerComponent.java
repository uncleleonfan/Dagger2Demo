package com.leon.dagger2demo;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = HardDiskComponent.class, modules = MouseModule.class)
public interface ComputerComponent {
    void inject(Computer computer);
}

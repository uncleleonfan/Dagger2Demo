package com.leon.dagger2demo;

import dagger.Component;

@MyScope
@Component(dependencies = HardDiskComponent.class, modules = MouseModule.class)
public interface ComputerComponent {
//    void inject(Computer computer);

    LaptopComponent laptopComponent();
}

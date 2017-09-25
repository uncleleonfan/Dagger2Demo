package com.leon.dagger2demo;

import dagger.Component;

@Component(modules = HardDiskModule.class)
public interface HardDiskComponent {

    //Only expose HardDisk250G
    HardDisk250G getHardDisk250G();

    //not have to inject to some object
    //void inject(Computer computer);
}

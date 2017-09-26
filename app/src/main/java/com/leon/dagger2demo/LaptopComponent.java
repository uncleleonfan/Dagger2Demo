package com.leon.dagger2demo;

import dagger.Subcomponent;

//@MyScope
@Subcomponent(modules = TouchPadModule.class)
public interface LaptopComponent {
    void inject(Computer computer);
}

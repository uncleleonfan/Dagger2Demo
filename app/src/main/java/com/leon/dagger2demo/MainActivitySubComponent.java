package com.leon.dagger2demo;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = TextViewModule.class)
public interface MainActivitySubComponent extends AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

        abstract void textViewModule(TextViewModule textViewModule);

        @Override
        public void seedInstance(MainActivity instance) {
            textViewModule(new TextViewModule(instance));
        }
    }
}

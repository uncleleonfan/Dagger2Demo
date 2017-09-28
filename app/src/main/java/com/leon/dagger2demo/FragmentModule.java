package com.leon.dagger2demo;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract MainActivity.MainFragment contributeMainActivityInjector();
}

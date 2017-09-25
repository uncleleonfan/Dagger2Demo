package com.leon.dagger2demo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainBoardModule {

    @Singleton
    @Provides
    MainBoard provideMainBoard(){
        return new MainBoard();
    }
}

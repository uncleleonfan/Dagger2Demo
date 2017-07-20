package com.leon.dagger2demo;

import android.content.Context;
import android.widget.TextView;

import dagger.Module;
import dagger.Provides;

@Module
public class TextViewModule {

    private Context mContext;

    public TextViewModule(Context context) {
        this.mContext = context;
    }

    @Provides
    TextView provideTextView(Context context) {
        return new TextView(context);
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}

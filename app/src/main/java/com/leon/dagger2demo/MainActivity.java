package com.leon.dagger2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject
    User mUser;

    @Inject
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent.builder()
                .textViewModule(new TextViewModule(this))
                .build()
                .inject(this);

        Log.d(TAG, "onCreate: User is " + mUser.name);

//        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame);
//        mTextView.setText(mUser.name);
//        frameLayout.addView(mTextView);

        Computer computer = new Computer();
        computer.init();
    }
}




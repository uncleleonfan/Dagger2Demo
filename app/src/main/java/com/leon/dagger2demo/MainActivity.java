package com.leon.dagger2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject
    User mUser;

    @Inject
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainComponent.builder().textViewModule(new TextViewModule(this)).build().inject(this);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame);
        mTextView.setText(mUser.name);
        frameLayout.addView(mTextView);

        Computer computer = new Computer();
        computer.init();
    }
}




package com.leon.dagger2demo;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
//
//        DaggerMainComponent.builder()
//                .build()
//                .inject(this);

//        DaggerMainComponent.builder()
//                .textViewModule(new TextViewModule(this))
//                .build()
//                .inject(this);

        Log.d(TAG, "onCreate: User is " + mUser.name);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame);
        mTextView.setText(mUser.name);
        frameLayout.addView(mTextView);

        Computer computer = new Computer();
        computer.init();
    }


    public static class MainFragment extends Fragment {

        @Inject
        User mUser;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setTextColor(Color.WHITE);
            textView.setText(mUser.name);
            textView.setBackgroundColor(Color.BLUE);
            return textView;
        }

        @Override
        public void onAttach(Context context) {
            AndroidInjection.inject(this);
            super.onAttach(context);
        }
    }
}




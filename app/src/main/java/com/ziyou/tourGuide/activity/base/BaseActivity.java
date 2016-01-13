package com.ziyou.tourGuide.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Edward on 15/12/31.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG;
    {
        TAG = getClass().getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

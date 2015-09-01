package com.hhl.swipebacksample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    public void nextPage(View v){
        startActivity(new Intent(this,NextActivity.class));
    }
}

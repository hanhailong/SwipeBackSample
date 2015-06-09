package com.hhl.swipebacksample;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

/**
 * Created by hailonghan on 15/6/9.
 */
public abstract class BaseSwipeBackActivity extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener {

    public final static String TAG = BaseSwipeBackActivity.class.getCanonicalName();

    SlidingPaneLayout mSlidingPaneLayout;

    FrameLayout mContainerFl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO 通过反射来改变SlidingPanelayout的值
        try {
            mSlidingPaneLayout = new SlidingPaneLayout(this);
            //属性
            Field f_overHang = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            f_overHang.setAccessible(true);
            f_overHang.set(mSlidingPaneLayout, 0);

            mSlidingPaneLayout.setPanelSlideListener(this);
            mSlidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);

        //添加两个view
        View leftView = new View(this);
        leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mSlidingPaneLayout.addView(leftView, 0);


        mContainerFl = new FrameLayout(this);
        mContainerFl.setBackgroundColor(getResources().getColor(android.R.color.white));
        mContainerFl.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mSlidingPaneLayout.addView(mContainerFl, 1);
    }

    @Override
    public void setContentView(int id) {
        setContentView(getLayoutInflater().inflate(id, null));
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(android.view.View)
     */
    @Override
    public void setContentView(View v) {
        setContentView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    @Override
    public void setContentView(View v, ViewGroup.LayoutParams params) {
//        super.setContentView(v, params);
        super.setContentView(mSlidingPaneLayout, params);

        mContainerFl.removeAllViews();
        mContainerFl.addView(v, params);
    }

    @Override
    public void onPanelClosed(View view) {

    }

    @Override
    public void onPanelOpened(View view) {
        finish();
        this.overridePendingTransition(0, R.anim.slide_out_right);
    }

    @Override
    public void onPanelSlide(View view, float v) {
    }
}

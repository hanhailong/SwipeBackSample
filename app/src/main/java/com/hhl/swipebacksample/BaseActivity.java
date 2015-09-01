package com.hhl.swipebacksample;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Created by hailonghan on 15/6/9.
 */
public abstract class BaseActivity extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener {

    public final static String TAG = BaseActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);

    }

    /**
     * 初始化滑动返回
     */
    private void initSwipeBackFinish() {
        if (isSupportSwipeBack()) {
            SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);
            //通过反射改变mOverhangSize的值为0，这个mOverhangSize值为菜单到右边屏幕的最短距离，默认
            //是32dp，现在给它改成0
            try {
                //属性
                Field f_overHang = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
                f_overHang.setAccessible(true);
                f_overHang.set(slidingPaneLayout, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            slidingPaneLayout.setPanelSlideListener(this);
            slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));

            View leftView = new View(this);
            leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            slidingPaneLayout.addView(leftView, 0);

            ViewGroup decor = (ViewGroup) getWindow().getDecorView();
            ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
            decorChild.setBackgroundColor(getResources().getColor(android.R.color.white));
            decor.removeView(decorChild);
            decor.addView(slidingPaneLayout);
            slidingPaneLayout.addView(decorChild, 1);
        }
    }

    /**
     * 是否支持滑动返回
     *
     * @return
     */
    protected boolean isSupportSwipeBack() {
        return true;
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

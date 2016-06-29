package com.example.feng.circlemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CircleMenu extends ViewGroup {
    private static final int DEFAULT_RADIUS = 150;
    private static final double FACTOR = 1.0 / 4;
    private String[] data = {"a", "b", "c", "d", "e", "f", "g", "H"};
    private int mCount = data.length;

    public CircleMenu(Context context) {
        this(context, null);
    }

    public CircleMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CircleMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int resWidth = 0, resHeight = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("liujianfeng", "heightMode = " + heightMode + "|EXACTLY=" + MeasureSpec.EXACTLY);
        Log.d("liujianfeng", "equals = " + (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY));
        Log.d("liujianfeng", "width " + width);


        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            resWidth = getSuggestedMinimumWidth();
            resWidth = resWidth == 0 ? DEFAULT_RADIUS*2 : resWidth;
            resHeight = getSuggestedMinimumHeight();
            resHeight = resHeight == 0 ? DEFAULT_RADIUS*2 : resHeight;
        } else {
            resWidth = resHeight = Math.min(width, height);
            Log.d("liujianfeng1", "resWidth=" + resWidth + "resHeight = " + resHeight);
        }

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int childMeasureSpec = MeasureSpec.makeMeasureSpec((int) (resWidth * FACTOR), MeasureSpec.EXACTLY);
            childView.measure(childMeasureSpec, childMeasureSpec);
        }
        Log.d("liujianfeng2222222", "resWidth=" + resWidth + "resHeight = " + resHeight);
        setMeasuredDimension(resWidth, resHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("liujianfeng", "layout width=" + getMeasuredWidth());
        Log.d("liujianfeng", "layout height=" + getMeasuredHeight());
        double deltDegree = 2 * Math.PI / getChildCount();
        int centerX = getMeasuredWidth() / 2;
        int centerY = getMeasuredHeight() / 2;
        int width = centerX;
        int childWidth = (int) (width * FACTOR);
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            double degree = deltDegree * i;
            double distance = width - childWidth;
            double left = centerX + distance * Math.cos(degree) - childWidth;
            double top = centerY - distance * Math.sin(degree) - childWidth;
            childView.layout((int) left, (int) top, (int) left + childWidth * 2, (int) top + childWidth * 2);
        }
    }
}

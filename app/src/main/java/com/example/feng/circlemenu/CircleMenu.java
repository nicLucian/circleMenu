package com.example.feng.circlemenu;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CircleMenu extends ViewGroup{
    private String[] data = {"a", "b", "c", "d", "e", "f", "g", "H"};
    private int mCount = data.length;
    private int mRadius = 300;
    private int mChildRadius = 50;

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
        for (int i = 0; i < mCount; i++) {
            TextView textView = new TextView(context);
            textView.setText(data[i]);
            textView.setTextSize(20);
            textView.setTextColor(Color.parseColor("#000000"));
            addView(textView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mRadius, mRadius);
        for (int i = 0; i < mCount; i++) {
            View childView = getChildAt(i);
            int childMeasureSpec = MeasureSpec.makeMeasureSpec(mChildRadius, MeasureSpec.EXACTLY);
            childView.measure(childMeasureSpec, childMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        double deltRadius = 2 * Math.PI / mCount;
        int centerX = getMeasuredWidth() / 2;
        int centerY = getMeasuredHeight() / 2;
        for (int i = 0; i < mCount; i++) {
            View childView = getChildAt(i);
            double childLeft = centerX + mRadius*Math.cos(deltRadius*i)/3 - mChildRadius/2;
            double childRight = childLeft + mChildRadius;
            double childTop = centerY - mRadius*Math.sin(deltRadius*i)/3 - mChildRadius/2;
            double childBottom = childTop + mChildRadius;
            childView.layout((int)childLeft, (int)childTop, (int)childRight, (int)childBottom);
        }

//        for (int i = 0; i < mCount; i++) {
//            View childView = getChildAt(i);
//            childView.layout(100 * i, 100 * i, 100 * i + mChildRadius, 100 * i + mChildRadius);
//        }
    }
}

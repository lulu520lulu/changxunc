package com.mall.hangxunc.pages.center.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.mall.hangxunc.R;


public class CenterShapeView extends View {
    private Shape mCurrentShape = Shape.Circle;
    Paint mPaint;
    private Path mPath;

    public CenterShapeView(Context context) {
        this(context, null);
    }

    public CenterShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CenterShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 只保证是正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        switch (mCurrentShape) {
            case Circle:
                // 画圆形
                int center = getWidth() / 2;
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.center_loading_circle));
                canvas.drawCircle(center, center, center, mPaint);
                break;
            case Square:
                // 画正方形
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.center_loading_rect));
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
                break;
            case Triangle:
                // 画三角  Path 画路线
                mPaint.setColor(ContextCompat.getColor(getContext(), R.color.center_loading_triangle));
                if (mPath == null) {
                    // 画路径
                    mPath = new Path();
                    mPath.moveTo(getWidth() / 2, 0);
                    mPath.lineTo(0, (float) ((getWidth() / 2) * Math.sqrt(3)));
                    mPath.lineTo(getWidth(), (float) ((getWidth() / 2) * Math.sqrt(3)));
                    // path.lineTo(getWidth()/2,0);
                    mPath.close();// 把路径闭合
                }
                canvas.drawPath(mPath, mPaint);
                break;
        }
    }

    /**
     * 改变形状
     */
    public void exchange() {
        switch (mCurrentShape) {
            case Circle:
                mCurrentShape = Shape.Square;
                break;
            case Square:
                mCurrentShape = Shape.Triangle;
                break;
            case Triangle:
                // 画三角  Path 画路线
                mCurrentShape = Shape.Circle;
                break;
        }
        // 不断重新绘制形状
        invalidate();
    }

    public enum Shape {
        Circle, Square, Triangle
    }

    public Shape getCurrentShape() {
        return mCurrentShape;
    }
}
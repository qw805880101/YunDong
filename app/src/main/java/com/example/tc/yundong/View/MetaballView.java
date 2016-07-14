package com.example.tc.yundong.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 带导航点的viewpage
 * Created by tc on 2016/6/1.
 */
public class MetaballView extends ViewPager {

    Context context;
    Paint paint;

    public MetaballView(Context context) {
        super(context);
    }

    public MetaballView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawCycle(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int height = 0;
//        //下面遍历所有child的高度
//        for (int i = 0; i < getChildCount(); i++) {
//            View child = getChildAt(i);
//            child.getHeight();
//            child.measure(widthMeasureSpec,
//                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//            int h = child.getMeasuredHeight();
//            if (h > height) //采用最大的view的高度。
//                height = h;
//        }
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
//                MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //画笔画小圆点
    private void drawCycle(Canvas canvas) {
        canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        int count = 0;
        if (this.getAdapter() != null) {
            count = this.getAdapter().getCount();
        }
        if (count > 1) {
            int select = getCurrentItem();
            float density = getContext().getResources().getDisplayMetrics().density;
            int itemWidth = (int) (10 * density);
            int itemHeight = itemWidth / 2;
            int x = (getWidth() - count * itemWidth) / 2;
            int y = getHeight() - itemWidth;
            int minItemHeight = (int) ((float) itemHeight * 0.5F);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            for (int i = 0; i < count; i++) {
                if (select == i) {
                    paint.setColor(0xFF486ba5);
                    canvas.drawCircle(x + itemWidth * i + itemWidth / 2, y, minItemHeight, paint);
                } else {
                    paint.setColor(0xFFababab);
                    canvas.drawCircle(x + itemWidth * i + itemWidth / 2, y, minItemHeight, paint);
                }
            }
        }
        canvas.restore();
    }
}

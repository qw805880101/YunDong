package com.example.tc.yundong.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
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
            int minItemHeight = (int) ((float) itemHeight * 0.8F);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            for (int i = 0; i < count; i++) {
                if (select == i) {
                    paint.setColor(0xFFbdbdbd);
                    canvas.drawCircle(x + itemWidth * i + itemWidth / 2, y, minItemHeight, paint);
                } else {
                    paint.setColor(0xFFe6e6e6);
                    canvas.drawCircle(x + itemWidth * i + itemWidth / 2, y, minItemHeight, paint);
                }
            }
        }
        canvas.restore();
    }
}

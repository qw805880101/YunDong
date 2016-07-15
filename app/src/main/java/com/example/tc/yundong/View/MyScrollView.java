package com.example.tc.yundong.View;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

    private OnScrollListener onScrollListener;
    /**
     * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;

    private int downY;
    private int mTouchSlop;

    private OnLoadListener mOnLoadListener;
    private ScrollViewListener scrollViewListener;

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    /**
     * 设置滚动接口
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }


    /**
     * 清除 ScrollView 嵌套 RecyclerView ，RecyclerView滑动没有惯性
     */
    private boolean hua = false;
    public void setHua(boolean hua){
        this.hua = hua;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }

    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            int scrollY = MyScrollView.this.getScrollY();

            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollY != scrollY) {
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }
            if (onScrollListener != null) {
                onScrollListener.onScroll(scrollY);
            }

        }

        ;

    };

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (onScrollListener != null) {
            onScrollListener.onScroll(lastScrollY = this.getScrollY());
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (scrollViewListener != null)
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        View view = (View) getChildAt(getChildCount() - 1);
        int d = view.getBottom();
        d -= (getHeight() + getScrollY());
        if (d == 0) {
            loadData();
        } else
            super.onScrollChanged(l, t, oldl, oldt);
    }

    public static interface OnLoadListener {
        public void onLoad();
    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    private void loadData() {
        if (mOnLoadListener != null) {
            mOnLoadListener.onLoad();
        }
    }

    class YScrollDetector extends SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            /**
             * 如果我们滚动更接近水平方向,返回false,让子视图来处理它
             */
            return (Math.abs(distanceY) > Math.abs(distanceX));
        }
    }

    public interface ScrollViewListener {

        void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy);

    }

    /**
     * 滚动的回调接口
     *
     * @author xiaanming
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         *
         * @param scrollY
         */
        public void onScroll(int scrollY);
    }

}

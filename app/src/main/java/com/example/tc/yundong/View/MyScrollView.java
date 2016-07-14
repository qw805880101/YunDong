package com.example.tc.yundong.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

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

	public void setScrollViewListener(ScrollViewListener scrollViewListener){
		this.scrollViewListener = scrollViewListener;
	}


	/**
	 * 清除 ScrollView 嵌套 RecyclerView ，RecyclerView滑动没有惯性
	 */

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

}

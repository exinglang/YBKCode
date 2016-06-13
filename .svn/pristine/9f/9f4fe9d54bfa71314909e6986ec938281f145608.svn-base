package com.puxtech.ybk.hangqing.detail;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by fanshuo on 16/5/17.
 */
public class CustomViewPager extends ViewPager {

    private boolean isCanScroll = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

	@Override
    public void scrollTo(int x, int y) {
		if (isCanScroll) {
			super.scrollTo(x, y);
		}
	}

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
		if (!isCanScroll) {
			return false;
		}
		return super.onTouchEvent(arg0);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (!isCanScroll) {
			return false;
		}
		return super.onInterceptTouchEvent(arg0);
    }
}

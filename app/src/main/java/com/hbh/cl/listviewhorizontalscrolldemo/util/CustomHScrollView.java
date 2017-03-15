package com.hbh.cl.listviewhorizontalscrolldemo.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbh on 2017/3/15.
 * 自定义横向滚动控件
 * 重载了 onScrollChanged（滚动条变化）,监听每次的变化通知给滚动观察者
 */

public class CustomHScrollView extends HorizontalScrollView {

    ScrollViewObserver mScrollViewObserver = new ScrollViewObserver();

    public CustomHScrollView(Context context) {
        super(context);
    }

    public CustomHScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //滚动时通知观察者
        if (mScrollViewObserver != null) {
            mScrollViewObserver.NotifyOnScrollChanged(l, t, oldl, oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /*
     * 当发生了滚动事件时接口，供外部访问
     */
    public static interface OnScrollChangedListener {
        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    /*
     * 添加滚动事件监听
     * */
    public void AddOnScrollChangedListener(OnScrollChangedListener listener) {
        mScrollViewObserver.AddOnScrollChangedListener(listener);
    }

    /*
     * 移除滚动事件监听
     * */
    public void RemoveOnScrollChangedListener(OnScrollChangedListener listener) {
        mScrollViewObserver.RemoveOnScrollChangedListener(listener);
    }
    /*
     * 滚动观察者
     */
    public static class ScrollViewObserver {
        List<OnScrollChangedListener> mChangedListeners;

        public ScrollViewObserver() {
            super();
            mChangedListeners = new ArrayList<OnScrollChangedListener>();
        }
        //添加滚动事件监听
        public void AddOnScrollChangedListener(OnScrollChangedListener listener) {
            mChangedListeners.add(listener);
        }
        //移除滚动事件监听
        public void RemoveOnScrollChangedListener(OnScrollChangedListener listener) {
            mChangedListeners.remove(listener);
        }
        //通知
        public void NotifyOnScrollChanged(int l, int t, int oldl, int oldt) {
            if (mChangedListeners == null || mChangedListeners.size() == 0) {
                return;
            }
            for (int i = 0; i < mChangedListeners.size(); i++) {
                if (mChangedListeners.get(i) != null) {
                    mChangedListeners.get(i).onScrollChanged(l, t, oldl, oldt);
                }
            }
        }
    }
}

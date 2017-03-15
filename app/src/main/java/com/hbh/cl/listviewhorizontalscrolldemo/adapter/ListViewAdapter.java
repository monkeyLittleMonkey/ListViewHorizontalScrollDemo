package com.hbh.cl.listviewhorizontalscrolldemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbh.cl.listviewhorizontalscrolldemo.MainActivity;
import com.hbh.cl.listviewhorizontalscrolldemo.R;
import com.hbh.cl.listviewhorizontalscrolldemo.model.TestData;
import com.hbh.cl.listviewhorizontalscrolldemo.util.CustomHScrollView;

import java.util.List;

/**
 * Created by hbh on 2017/3/15.
 */

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<TestData> mList;
    private LayoutInflater mInflater;
    private RelativeLayout mHead;
    private int n = 1;//标记变量

    public ListViewAdapter(Context context, List<TestData> list, RelativeLayout head) {
        this.mContext = context;
        this.mList = list;
        this.mHead = head;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup group) {
        MyViewHolder holder = null;
        if (view == null){
            view = mInflater.inflate(R.layout.list_item, group, false);
            holder = new MyViewHolder();
            CustomHScrollView scrollView = (CustomHScrollView) view.findViewById(R.id.h_scrollView);
            holder.scrollView = scrollView;
            holder.mTextView1 = (TextView) view.findViewById(R.id.textView_1);
            holder.mTextView2 = (TextView) view.findViewById(R.id.textView_2);
            holder.mTextView3 = (TextView) view.findViewById(R.id.textView_3);
            holder.mTextView4 = (TextView) view.findViewById(R.id.textView_4);
            holder.mTextView5 = (TextView) view.findViewById(R.id.textView_5);
            holder.mTextView6 = (TextView) view.findViewById(R.id.textView_6);
            holder.mTextView7 = (TextView) view.findViewById(R.id.textView_7);

            CustomHScrollView headSrcrollView = (CustomHScrollView) mHead.findViewById(R.id.h_scrollView);
            headSrcrollView.AddOnScrollChangedListener(new OnScrollChangedListenerImp(scrollView));

            view.setTag(holder);
        }else{
            holder = (MyViewHolder) view.getTag();
        }

        holder.mTextView1.setText(mList.get(i).getText1());
        holder.mTextView2.setText(mList.get(i).getText2());
        holder.mTextView3.setText(mList.get(i).getText3());
        holder.mTextView4.setText(mList.get(i).getText4());
        holder.mTextView5.setText(mList.get(i).getText5());
        holder.mTextView6.setText(mList.get(i).getText6());
        holder.mTextView7.setText(mList.get(i).getText7());

        return view;
    }

    class OnScrollChangedListenerImp implements CustomHScrollView.OnScrollChangedListener {
        CustomHScrollView mScrollViewArg;

        public OnScrollChangedListenerImp(CustomHScrollView scrollViewar) {
            mScrollViewArg = scrollViewar;
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            mScrollViewArg.smoothScrollTo(l, t);
            if (n == 1) {//记录滚动的起始位置，避免因刷新数据引起错乱
                new MainActivity().setPosData(oldl, oldt);
            }
            n++;
        }
    };

    class MyViewHolder{
        TextView mTextView1;
        TextView mTextView2;
        TextView mTextView3;
        TextView mTextView4;
        TextView mTextView5;
        TextView mTextView6;
        TextView mTextView7;
        HorizontalScrollView scrollView;
    }
}

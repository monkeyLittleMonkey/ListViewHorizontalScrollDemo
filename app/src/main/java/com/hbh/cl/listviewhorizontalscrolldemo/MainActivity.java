package com.hbh.cl.listviewhorizontalscrolldemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.hbh.cl.listviewhorizontalscrolldemo.adapter.ListViewAdapter;
import com.hbh.cl.listviewhorizontalscrolldemo.model.TestData;
import com.hbh.cl.listviewhorizontalscrolldemo.util.CustomHScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mHead;//标题头
    private ListView mListView;
    private List<TestData> mDataList;
    private ListViewAdapter mAdapter;
    private int leftPos;//用于记录CustomHScrollView的初始位置
    private int topPos;
    CustomHScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        initData();
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.list_view);
        mScrollView = (CustomHScrollView) findViewById(R.id.h_scrollView);
        mHead = (RelativeLayout) findViewById(R.id.head_layout);
        mHead.setBackgroundResource(R.color.colorAccent);
        mHead.setFocusable(true);
        mHead.setClickable(true);
        mHead.setOnTouchListener(new MyTouchLinstener());
        mListView.setOnTouchListener(new MyTouchLinstener());
    }

    /**
     * 加载数据
     */
    private void initData(){
        mDataList = new ArrayList<>();
        TestData data = null;
        for (int i = 1; i <= 100; i++) {
            data = new TestData();
            data.setText1("第"+i+"行-1");
            data.setText2("第"+i+"行-2");
            data.setText3("第"+i+"行-3");
            data.setText4("第"+i+"行-4");
            data.setText5("第"+i+"行-5");
            data.setText6("第"+i+"行-6");
            data.setText7("第"+i+"行-7");
            mDataList.add(data);
        }
        setData();
    }

    private void setData(){
        mAdapter = new ListViewAdapter(this, mDataList, mHead);
        mListView.setAdapter(mAdapter);
    }

    class MyTouchLinstener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {
            //当在表头和listView控件上touch时，将事件分发给 ScrollView
            HorizontalScrollView headSrcrollView = (HorizontalScrollView) mHead.findViewById(R.id.h_scrollView);
            headSrcrollView.onTouchEvent(arg1);
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            initData();//刷新，重新加载数据
            mScrollView.smoothScrollTo(leftPos, topPos);//每次刷新数据都让CustomHScrollView回到初始位置，避免错乱
            if (mAdapter != null){
                mAdapter.notifyDataSetChanged();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 记录CustomHScrollView的初始位置
     * @param l
     * @param t
     */
    public void setPosData(int l, int t){
        this.leftPos = l;
        this.topPos = t;
    }
}

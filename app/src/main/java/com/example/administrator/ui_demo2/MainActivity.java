package com.example.administrator.ui_demo2;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ui_demo2.Fragment.TabFragment;

public class MainActivity extends FragmentActivity
        implements OnClickListener,TabFragment.OnFragmentInteractionListener,UserFragment.OnFragmentInteractionListener {

    // 三个选项卡
    //private LinearLayout tab1Layout, tab2Layout, tab3Layout;
    private ImageView a1, a2, a3;
    private TextView a1_text,a2_text,a3_text;
    // 默认选中第一个tab
    private int index = 1;
    // fragment管理类
    private FragmentManager fragmentManager;
    // 三个fragment
    private Fragment tab1Fragment, tab2Fragment, tab3Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        init();
    }

    /**
     * 初始化控件
     */
    private void init() {
        a1 = (ImageView) findViewById(R.id.a1);
        a2 = (ImageView) findViewById(R.id.a2);
        a3 = (ImageView) findViewById(R.id.a3);

        a1_text = (TextView) findViewById(R.id.a1_text);
        a2_text = (TextView) findViewById(R.id.a2_text);
        a3_text = (TextView) findViewById(R.id.a3_text);


        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
        //
        setDefaultFragment();
    }

    /**
     * 设置默认显示的fragment
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        tab1Fragment = new TabFragment();
        transaction.replace(R.id.content, tab1Fragment);
        transaction.commit();
    }

    /**
     *切换fragment
     * @param newFragment
     */
    private void replaceFragment(Fragment newFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!newFragment.isAdded()) {
            transaction.replace(R.id.content, newFragment);
            transaction.commit();
        } else {
            transaction.show(newFragment);
        }
    }

    /**
     * 改变现象卡的选中状态
     */
    private void clearStatus() {
        if (index == 1) {
            a1.setBackground(getResources().getDrawable(R.drawable.a1));
            a1_text.setTextColor(getResources().getColor(R.color.initcolor));
        } else if (index == 2) {
            a2.setBackground(getResources().getDrawable(R.drawable.a2));
            a2_text.setTextColor(getResources().getColor(R.color.initcolor));
        } else if (index == 3) {
            a3.setBackground(getResources().getDrawable(R.drawable.a3));
            a3_text.setTextColor(getResources().getColor(R.color.initcolor));
        }
    }

    @Override
    public void onClick(View v) {
        clearStatus();
        switch (v.getId()) {
            case R.id.a1_linear:
            case R.id.a1_text:
            case R.id.a1:
                Log.v("tag","fragment1");
                if (tab1Fragment == null) {
                    tab1Fragment = new TabFragment();
                }
                replaceFragment(tab1Fragment);
                a1.setBackground(getResources().getDrawable(R.drawable.a11));
                a1_text.setTextColor(getResources().getColor(R.color.cornflowerblue));
                index = 1;
                break;
            case R.id.a2_linear:
            case R.id.a2_text:
            case R.id.a2:
                Log.v("tag","fragment2");
                if (tab2Fragment == null) {
                    tab2Fragment = new TabFragment();
                }
                replaceFragment(tab2Fragment);
                a2.setBackground(getResources().getDrawable(R.drawable.a22));
                a2_text.setTextColor(getResources().getColor(R.color.cornflowerblue));
                index = 2;
                break;
            case R.id.a3_linear:
            case R.id.a3_text:
            case R.id.a3:
                Log.v("tag","fragment3");
                if (tab3Fragment == null) {
                    tab3Fragment = new UserFragment();
                }
                replaceFragment(tab3Fragment);
                a3.setBackground(getResources().getDrawable(R.drawable.a33));
                a3_text.setTextColor(getResources().getColor(R.color.cornflowerblue));
                index = 3;
                break;
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}


package com.example.ayou.jk_takeout.firstpage.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.adapter.MainFragmentStatePagerAdapter;
import com.example.ayou.jk_takeout.firstpage.fragment.FirstPageFragment;
import com.example.ayou.jk_takeout.mine.fragment.MineFragment;
import com.example.ayou.jk_takeout.order.fragment.OrderFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.rb_firstpage)
    RadioButton mRbFirstpage;
    @BindView(R.id.rb_order)
    RadioButton mRbOrder;
    @BindView(R.id.rb_mine)
    RadioButton mRbMine;
    @BindView(R.id.rb_tab)
    RadioGroup mRbTab;
    //类型为Fragment的动态数组
    private ArrayList<Fragment> fragmentList;
    private int mMineFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        initView();

        //初始化适配器
        initViewPager();

//        //用于当用户注册或者短信直接注册后跳转到MainActivity的MineFragment中


//        if (mMineFragment == 1) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.view_pager, new MineFragment())
//                    .addToBackStack(null)
//                    .commit();
//        }
    }

    private void initView() {
        //设置RadioGroup的点击事件
        ButterKnife.bind(this);
        mRbTab.setOnCheckedChangeListener(this);

    }

    private void initViewPager() {
        //初始化Fragment的数据集
        fragmentList = new ArrayList<Fragment>();

        //初始化Fragment
        FirstPageFragment firstPageFragment = new FirstPageFragment();
        OrderFragment orderFragment = new OrderFragment();
        MineFragment mineFragment = new MineFragment();

        //将各Fragment加入到数组中
        fragmentList.add(firstPageFragment);
        fragmentList.add(orderFragment);
        fragmentList.add(mineFragment);


        //设置ViewPager的设配器
        mViewPager.setAdapter(new MainFragmentStatePagerAdapter(getSupportFragmentManager(), fragmentList));

        //判断当从注册界面返回MainActvity的MineFragment中

        Intent intent = getIntent();
        mMineFragment = intent.getIntExtra("index", 0);

        mViewPager.setCurrentItem(mMineFragment);
        if (mMineFragment == 2) {
            mRbMine.performClick();
            mRbMine.setTextColor(getResources().getColor(R.color.color_table_text_press));
            mRbFirstpage.setTextColor(getResources().getColor(R.color.color_table_text));
            mRbOrder.setTextColor(getResources().getColor(R.color.color_table_text));

        }

        //ViewPager的页面改变监听器
        mViewPager.setOnPageChangeListener(new MyListener());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        //获取当前被选中的RadioButton的ID，用于改变ViewPager的当前页
        int current = 0;
        switch (checkedId) {
            case R.id.rb_firstpage:
                current = 0;
                mRbFirstpage.setTextColor(getResources().getColor(R.color.color_table_text_press));
                mRbOrder.setTextColor(getResources().getColor(R.color.color_table_text));
                mRbMine.setTextColor(getResources().getColor(R.color.color_table_text));
                break;
            case R.id.rb_order:
                current = 1;
                mRbFirstpage.setTextColor(getResources().getColor(R.color.color_table_text));
                mRbOrder.setTextColor(getResources().getColor(R.color.color_table_text_press));
                mRbMine.setTextColor(getResources().getColor(R.color.color_table_text));
                break;
            case R.id.rb_mine:
                current = 2;
                mRbMine.setTextColor(getResources().getColor(R.color.color_table_text_press));
                mRbFirstpage.setTextColor(getResources().getColor(R.color.color_table_text));
                mRbOrder.setTextColor(getResources().getColor(R.color.color_table_text));
                break;
        }
        if (mViewPager.getCurrentItem() != current) {
            mViewPager.setCurrentItem(current);
        }
    }

    /**
     * ViewPager的页面滑动监听
     */
    public class MyListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            //获取当前页面用于改变对应RadioButton的状态
            int current = mViewPager.getCurrentItem();
            switch (current) {
                case 0:
                    mRbTab.check(R.id.rb_firstpage);
                    mRbFirstpage.setTextColor(getResources().getColor(R.color.color_table_text_press));
                    mRbOrder.setTextColor(getResources().getColor(R.color.color_table_text));
                    mRbMine.setTextColor(getResources().getColor(R.color.color_table_text));
                    break;
                case 1:
                    mRbTab.check(R.id.rb_order);
                    mRbFirstpage.setTextColor(getResources().getColor(R.color.color_table_text));
                    mRbOrder.setTextColor(getResources().getColor(R.color.color_table_text_press));
                    mRbMine.setTextColor(getResources().getColor(R.color.color_table_text));
                    break;
                case 2:
                    mRbTab.check(R.id.rb_mine);
                    mRbMine.setTextColor(getResources().getColor(R.color.color_table_text_press));
                    mRbFirstpage.setTextColor(getResources().getColor(R.color.color_table_text));
                    mRbOrder.setTextColor(getResources().getColor(R.color.color_table_text));
                    break;
            }
        }
    }
}

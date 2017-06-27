package com.example.ayou.jk_takeout.firstpage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by AYOU on 2017/4/25.
 */

public class classFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> dataList;

    public classFragmentAdapter(FragmentManager fm,List<Fragment> dataList) {
        super(fm);
        this.dataList = dataList;
    }

    @Override
    public Fragment getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}

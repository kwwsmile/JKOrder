package com.example.ayou.jk_takeout.utils;

import android.util.SparseArray;

import com.example.ayou.jk_takeout.firstpage.shopcat.GoodsItem;

/**
 * Created by kongweiwei on 2017/6/5.
 */

public class SelectFoodEvent extends BaseEvent {

    private SparseArray<GoodsItem> selectedList;

    public SparseArray<GoodsItem> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(SparseArray<GoodsItem> selectedList) {
        this.selectedList = selectedList;
    }

    public SelectFoodEvent(int WHAT) {
        super(WHAT);
    }
}


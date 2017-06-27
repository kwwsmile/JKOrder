package com.example.ayou.jk_takeout.firstpage.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.model.bean_class;
import com.example.ayou.jk_takeout.firstpage.view.classShopActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by AYOU on 2017/4/25.
 */

public class oneClass extends Fragment {

    private Context context;
    private List<bean_class.DataBean.ListBean> mList_class;
    private LayoutInflater inflater;

    public oneClass() {
    }

    public oneClass(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.class_one_item, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    //传递数据
    public void getData(List<bean_class.DataBean.ListBean> list){
        if (mList_class==null){
            mList_class = new ArrayList<>();
        }
        mList_class.addAll(list);
    }


    @OnClick({R.id.iv_class_one_fastfood, R.id.iv_class_one_chinese, R.id.iv_class_one_chicken, R.id.iv_class_one_noodle, R.id.iv_class_one_place, R.id.iv_class_one_seafood, R.id.iv_class_one_fruit, R.id.iv_class_one_drink})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_class_one_fastfood:
                Intent intent = new Intent(context, classShopActivity.class);
                intent.putExtra("id",mList_class.get(0).getId());
                startActivity(intent);
                break;
            case R.id.iv_class_one_chinese:
                Intent intent1 = new Intent(context, classShopActivity.class);
                intent1.putExtra("id",mList_class.get(1).getId());
                startActivity(intent1);
                break;
            case R.id.iv_class_one_chicken:
                Intent intent2 = new Intent(context, classShopActivity.class);
                intent2.putExtra("id",mList_class.get(2).getId());
                startActivity(intent2);
                break;
            case R.id.iv_class_one_noodle:
                Intent intent3 = new Intent(context, classShopActivity.class);
                intent3.putExtra("id",mList_class.get(3).getId());
                startActivity(intent3);
                break;
            case R.id.iv_class_one_place:
                Intent intent4 = new Intent(context, classShopActivity.class);
                intent4.putExtra("id",mList_class.get(4).getId());
                startActivity(intent4);
                break;
            case R.id.iv_class_one_seafood:
                Intent intent5 = new Intent(context, classShopActivity.class);
                intent5.putExtra("id",mList_class.get(5).getId());
                startActivity(intent5);
                break;
            case R.id.iv_class_one_fruit:
                Intent intent6 = new Intent(context, classShopActivity.class);
                intent6.putExtra("id",mList_class.get(6).getId());
                startActivity(intent6);
                break;
            case R.id.iv_class_one_drink:
                Intent intent7 = new Intent(context, classShopActivity.class);
                intent7.putExtra("id",mList_class.get(7).getId());
                startActivity(intent7);
                break;
        }
    }
}

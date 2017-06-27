package com.example.ayou.jk_takeout.order.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.mine.view.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by kongweiwei on 2017/4/23.
 */

public class OrderFragment extends Fragment {

    @BindView(R.id.btn_order2)
    Button btnOrder2;

    public OrderFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences preferences = getActivity().getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
        boolean orderLogin = preferences.getBoolean("isLogin", false);
        if (orderLogin) {
            View view = inflater.inflate(R.layout.fragment_order, container, false);
            return view;
        } else {
            View view = inflater.inflate(R.layout.fragment_order2, container, false);
            ButterKnife.bind(this, view);
            btnOrder2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }
            });

            return view;
        }

    }
//    @OnClick(R.id.btn_order2)
//    public void onClick() {
//       startActivity(new Intent(getActivity(), LoginActivity.class));
//        getActivity().finish();
//
//   }
}

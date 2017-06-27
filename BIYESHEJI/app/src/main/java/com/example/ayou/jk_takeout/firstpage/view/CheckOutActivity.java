package com.example.ayou.jk_takeout.firstpage.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.adapter.checkout_adapter;
import com.example.ayou.jk_takeout.firstpage.shopcat.GoodsItem;
import com.example.ayou.jk_takeout.utils.EventConfig;
import com.example.ayou.jk_takeout.utils.SelectFoodEvent;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class CheckOutActivity extends AppCompatActivity implements View.OnClickListener {

    private checkout_adapter adapter;
    private RecyclerView mRecyclerView;

    private ImageView iv_back;
    private TextView tv_buy;
    //购买的商品
    private SparseArray<GoodsItem> selectedList;
    private TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        selectedList = new SparseArray<>();

        EventBus.getDefault().registerSticky(this);

        adapter = new checkout_adapter(this);

        initView();


    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.check_out_rv);
        adapter = new checkout_adapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        iv_back = (ImageView) findViewById(R.id.check_out_back);
        iv_back.setOnClickListener(this);
        tv_buy = (TextView) findViewById(R.id.check_out_tv_buy);
        tv_buy.setOnClickListener(this);
        price = (TextView) findViewById(R.id.check_out_tv_price);

    }




    @Subscriber
    public void getFood(SelectFoodEvent event){
        if (event.WHAT == EventConfig.SELECT_FOOD){
            selectedList = event.getSelectedList();
            Log.i("jzq", "hello"+selectedList.valueAt(0).name);
            adapter.getData(selectedList);
            //商品总价
            int count = 0;
            double cost = 0;
            for (int i = 0; i < selectedList.size(); i++) {
                GoodsItem item = selectedList.valueAt(i);
                count += item.count;
                cost += item.count * item.price;
            }
            price.setText(cost+"元");
        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.check_out_back:
                finish();
                break;
            case R.id.check_out_tv_buy:
                Toast.makeText(this, "购买成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
package com.example.ayou.jk_takeout.firstpage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.shopcat.GoodsItem;
import com.example.ayou.jk_takeout.mine.view.HaveAddressActivity;

/**
 * Created by kongweiwei on 2017/5/23.
 */

public class checkout_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;
    public static final int TYPE_THREE = 2;
    public static final int TYPE_FORTH = 3;
    private LayoutInflater mInflater;

    private int count;
    private double cost;
    //购买的商品
    private SparseArray<GoodsItem> selectedList;

    public checkout_adapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    //获取购买的商品信息
    public void getData(SparseArray<GoodsItem> selectedList){
        if (this.selectedList == null){
            this.selectedList = new SparseArray<>();
        }
            this.selectedList = selectedList;

    }

    @Override
    public int getItemCount() {
        return selectedList == null?2:selectedList.size()+2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ONE;
        } else if (position == 1) {
            return TYPE_TWO;
        } else {
            return TYPE_THREE;
        }
//        else {
//            return TYPE_FORTH;
//        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_ONE:
                return new viewHolderOne(mInflater.inflate(R.layout.checkout_one,parent,false));
            case TYPE_TWO:
                return new viewHolderTwo(mInflater.inflate(R.layout.checkout_two,parent,false));
            case TYPE_THREE:
                return new viewHolderThree(mInflater.inflate(R.layout.checkout_three,parent,false));
//            case TYPE_FORTH:
//                return new viewHolderForth(mInflater.inflate(R.layout.checkout_forth,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int type = getItemViewType(position);
        switch (type){
            case TYPE_ONE:
                viewHolderOne holderOne = (viewHolderOne) holder;
                holderOne.mTv.setOnClickListener(this);

                break;
            case TYPE_TWO:
                viewHolderTwo holderTwo = (viewHolderTwo) holder;

                break;
            case TYPE_THREE:
                viewHolderThree holderThree = (viewHolderThree) holder;
                int count = 0;
                double cost = 0;
                GoodsItem item = selectedList.valueAt(position-2);
                String name = item.name;
                count += item.count;
                cost = item.price;
                holderThree.mtv_name.setText(name);
                holderThree.mtv_num.setText(count+"");
                holderThree.mtv_price.setText(cost+"");

                break;
//            case TYPE_FORTH:
//                viewHolderForth viewHolderForth = (checkout_adapter.viewHolderForth) holder;
//
//                break;


        }
    }

    //地址填写
    @Override
    public void onClick(View v) {
        mContext.startActivity(new Intent(mContext,HaveAddressActivity.class));
    }

    //收货地址
    public class viewHolderOne extends RecyclerView.ViewHolder {
        private TextView mTv;
        public viewHolderOne(View itemView) {
            super(itemView);
            mTv = (TextView)itemView.findViewById(R.id.checkout_one_tv);
        }
    }

    //备注
    public class viewHolderTwo extends RecyclerView.ViewHolder {
        private EditText mEt;
        public viewHolderTwo(View itemView) {
            super(itemView);
            mEt = (EditText) itemView.findViewById(R.id.check_out_et);
        }
    }

    //购买菜品
    public class viewHolderThree extends RecyclerView.ViewHolder {
        private TextView mtv_name;
        private TextView mtv_num;
        private TextView mtv_price;
        public viewHolderThree(View itemView) {
            super(itemView);
            mtv_name = (TextView) itemView.findViewById(R.id.checkout_three_tv_name);
            mtv_num = (TextView) itemView.findViewById(R.id.checkout_three_tv_count);
            mtv_price = (TextView) itemView.findViewById(R.id.checkout_three_tv_price);

        }
    }

    //配送费
//    public class viewHolderForth extends RecyclerView.ViewHolder {
//        private TextView mtv_sendPrice;
//        public viewHolderForth(View itemView) {
//            super(itemView);
//            mtv_sendPrice = (TextView) itemView.findViewById(R.id.checkout_forth_price);
//        }
//    }


//    public void getData(int count,double cost){
//        count = count;
//        cost = cost;
//    }

}
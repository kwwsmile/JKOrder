package com.example.ayou.jk_takeout.firstpage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.adapter.adapter_firstpage;
import com.example.ayou.jk_takeout.firstpage.config.netConfig;
import com.example.ayou.jk_takeout.firstpage.model.bean_ad;
import com.example.ayou.jk_takeout.firstpage.model.bean_class;
import com.example.ayou.jk_takeout.firstpage.model.bean_shop;
import com.example.ayou.jk_takeout.firstpage.shopcat.ShoppingCartActivity;
import com.yalantis.phoenix.PullToRefreshView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by kongweiwei on 2017/4/23.
 */

public class FirstPageFragment extends Fragment implements adapter_firstpage.OnItemClickListener {


    @BindView(R.id.rv_firstpage_show)
    RecyclerView rvFirstpageShow;
    @BindView(R.id.ptf_refresh)
    PullToRefreshView ptfRefresh;


    private adapter_firstpage adapter;

    private int page = 1; //商铺列表切换

    public FirstPageFragment() {
    }

    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firstpage, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initData();
        setData();

        setListener();
        setLoadMore();

        adapter.setOnItemClickListener(this);


    }

    /**
     * 上拉加载更多
     */
    private void setLoadMore() {
        rvFirstpageShow.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) rvFirstpageShow.getLayoutManager();
                //不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    //最后一个item
                    int lastItem = manager.findLastVisibleItemPosition();
                    //所有的item
                    int totalItemCount = manager.getItemCount();
                    if (lastItem == totalItemCount -1){
                        page = page+1;
                        initShopData(false,page);
                    }

                }

            }
        });

    }

    /**
     * 下拉刷新
     */
    private void setListener() {
        ptfRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initShopData(true, page);
            }
        });

    }

    private void setData() {
        adapter = new adapter_firstpage(getActivity(), getActivity().getSupportFragmentManager());
        rvFirstpageShow.setAdapter(adapter);
        rvFirstpageShow.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initData() {
        initAdData(false);
        initClassData(false);
        initShopData(false, page);
    }

    /**
     * 广告
     */
    private void initAdData(final boolean isRefresh) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(netConfig.BASIC_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        netConfig netConfig = retrofit.create(netConfig.class);
        Call<bean_ad> call = netConfig.getAdData();
        call.enqueue(new Callback<bean_ad>() {
            @Override
            public void onResponse(Call<bean_ad> call, Response<bean_ad> response) {
                bean_ad bean = response.body();
                adapter.getAdData(bean.getData().getList(), isRefresh);
            }

            @Override
            public void onFailure(Call<bean_ad> call, Throwable t) {

            }
        });
    }

    /**
     * 分类
     */
    private void initClassData(final boolean isRefresh) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(netConfig.BASIC_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        netConfig netConfig = retrofit.create(netConfig.class);
        Call<bean_class> call = netConfig.getClassData();
        call.enqueue(new Callback<bean_class>() {
            @Override
            public void onResponse(Call<bean_class> call, Response<bean_class> response) {
                bean_class bean = response.body();
                adapter.getClassData(bean.getData().getList(), isRefresh);
            }

            @Override
            public void onFailure(Call<bean_class> call, Throwable t) {

            }
        });
    }

    /**
     * 商铺列表
     */
    private void initShopData(final boolean isRefresh, int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(netConfig.BASIC_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        netConfig netConfig = retrofit.create(netConfig.class);
        Call<bean_shop> call = netConfig.getShopData(page);
        call.enqueue(new Callback<bean_shop>() {
            @Override
            public void onResponse(Call<bean_shop> call, Response<bean_shop> response) {
                final bean_shop bean = response.body();
                if (bean != null){
                    adapter.getShopData(bean.getData().getList(), isRefresh);
                }

                //取消刷新
                ptfRefresh.setRefreshing(false);


            }

            @Override
            public void onFailure(Call<bean_shop> call, Throwable t) {

            }
        });
    }

    //店铺点击事件
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ShoppingCartActivity.class);
        bean_shop.DataBean.ListBean shopData = adapter.getShopData(position);
        intent.putExtra("cartID",shopData.getId());
        intent.putExtra("lowPrice",shopData.getLowest_deliver_fee());
        startActivity(intent);
    }

}

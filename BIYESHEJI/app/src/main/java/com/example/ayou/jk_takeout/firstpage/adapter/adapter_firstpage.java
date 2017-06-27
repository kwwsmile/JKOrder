package com.example.ayou.jk_takeout.firstpage.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.config.netConfig;
import com.example.ayou.jk_takeout.firstpage.fragment.oneClass;
import com.example.ayou.jk_takeout.firstpage.fragment.twoClass;
import com.example.ayou.jk_takeout.firstpage.model.bean_ad;
import com.example.ayou.jk_takeout.firstpage.model.bean_class;
import com.example.ayou.jk_takeout.firstpage.model.bean_shop;
import com.example.ayou.jk_takeout.utils.imageload;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AYOU on 2017/4/24.
 */

public class adapter_firstpage extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<bean_ad.DataBean.ListBean> list_ad; //广告数据
    private List<bean_class.DataBean.ListBean> list_class; //分类数据
    private List<bean_shop.DataBean.ListBean> list_shop;  //店铺数据


    private final int TYPE_AD = 0;
    private final int TYPE_CLASS = 1;
    private final int TYPE_SHOP = 2;

    private LayoutInflater mInflater;

    private classFragmentAdapter classAdapter;
    private List<Fragment> fragments;

    private FragmentManager manager;

    private OnItemClickListener mOnItemClickListener;
    private RecyclerView mRv;


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRv = recyclerView;
    }

    @Override
    public void onClick(View v) {
        if (mRv != null) {
            int childAdapterPosition = mRv.getChildAdapterPosition(v);

            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(childAdapterPosition - 2);
            }
        }
    }

    //recyclerview的点击事件
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    //点击的方法
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public adapter_firstpage(Context context, FragmentManager manager) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.manager = manager;
    }

    @Override
    public int getItemCount() {
        return list_shop == null ? 2 : list_shop.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_AD;
        }
        if (position == 1) {
            return TYPE_CLASS;
        } else {
            return TYPE_SHOP;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_AD:
                ADHolder adHolder = new ADHolder(mInflater.inflate(R.layout.firstpage_ad, parent, false));
                return adHolder;
            case TYPE_CLASS:
                ClassHolder classHolder = new ClassHolder(mInflater.inflate(R.layout.firstpage_class, parent, false));
                return classHolder;
            case TYPE_SHOP:
                ShopHolder shopHolder = new ShopHolder(mInflater.inflate(R.layout.firstpage_shop, parent, false));
                return shopHolder;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type) {
            //广告轮播
            case TYPE_AD:
                if (list_ad != null) {
                    ADHolder holder1 = (ADHolder) holder;
                    holder1.bannerFirstpageAdapter.setImageLoader(new imageload());
                    List<String> imgList = new ArrayList<>();
                    for (int i = 0; i < list_ad.size(); i++) {
                        imgList.add(list_ad.get(i).getImage());
                    }
                    //轮播资源
                    holder1.bannerFirstpageAdapter.setImages(imgList);
                    //轮播动画
                    holder1.bannerFirstpageAdapter.setBannerAnimation(Transformer.RotateDown);
                    //轮播间隔时间
                    holder1.bannerFirstpageAdapter.setDelayTime(2500);
                    holder1.bannerFirstpageAdapter.start();
                }

                break;
            //分类导航
            case TYPE_CLASS:
                if (list_class != null) {
                    final ClassHolder holder2 = (ClassHolder) holder;
                    initFragment(holder2, list_class);
                    holder2.vpClassShow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                            int currentItem = holder2.vpClassShow.getCurrentItem();
                            switch (currentItem) {
                                case 0:
                                    holder2.ivFirstclassOne.setImageResource(R.drawable.big);
                                    holder2.ivFirstclassTwo.setImageResource(R.drawable.small);
                                    break;
                                case 1:
                                    holder2.ivFirstclassOne.setImageResource(R.drawable.small);
                                    holder2.ivFirstclassTwo.setImageResource(R.drawable.big);
                                    break;

                            }
                        }
                    });


                }
                break;
            //店铺列表
            case TYPE_SHOP:
                if (position == list_shop.size() + 2) {
                    return;
                }
                if (list_shop != null) {
                    final ShopHolder holder3 = (ShopHolder) holder;
                    String newbie_cut = list_shop.get(position - 2).getNewbie_cut() + "";
                    List<bean_shop.FullBean> full_set = list_shop.get(position - 2).getFull_set();
                    //判断是否有首单优惠
                    if (!TextUtils.isEmpty(newbie_cut)) {
                        holder3.llShopShou.setVisibility(View.VISIBLE);
                        holder3.tvShopFirst.setText("首单立减" + newbie_cut);
                    }
                    //判断是否有满减优惠
                    if (full_set.size() != 0) {
                        holder3.llShopJian.setVisibility(View.VISIBLE);
                        holder3.tvShopJian.setText("在线支付满" + full_set.get(0).getMan() + "减" + full_set.get(0).getJian());
                    }
                    //加载商铺图片
                    Picasso.with(context).load(netConfig.IMG_URL + list_shop.get(position - 2).getImg()).error(R.drawable.log).placeholder(R.drawable.log).into(holder3.ivShopIcon);
                    holder3.tvShopTitle.setText(list_shop.get(position - 2).getSellername());
                    holder3.tvShopSell.setText("已售" + list_shop.get(position - 2).getSell_out() + "份");
                    holder3.tvShopDistance.setText(list_shop.get(position - 2).getGdistance() + "米");
                    holder3.tvShopLowest.setText(list_shop.get(position - 2).getLowest_deliver_fee() + "");
                    holder3.tvShopSend.setText(list_shop.get(position - 2).getDeliver_fee() + "");

                    holder3.itemView.setOnClickListener(this);

                }

                break;


        }


    }

    /**
     * 分类页面的fragment
     */
    private void initFragment(ClassHolder holder2, List<bean_class.DataBean.ListBean> list_class) {
        fragments = new ArrayList<>();
        //初始化fragment
        oneClass one = new oneClass(context);
        twoClass two = new twoClass(context);
        //传递数据
        one.getData(list_class);
        two.getData(list_class);
        //将fragment加入集合中
        fragments.add(one);
        fragments.add(two);
        //初始化适配器
        classAdapter = new classFragmentAdapter(manager, fragments);
        holder2.vpClassShow.setAdapter(classAdapter);

        //显示第一个页面
        holder2.vpClassShow.setCurrentItem(0);


    }

    public class ADHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner_firstpageAdapter)
        Banner bannerFirstpageAdapter;

        public ADHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ClassHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vp_class_show)
        ViewPager vpClassShow;
        @BindView(R.id.iv_firstclass_one)
        ImageView ivFirstclassOne;
        @BindView(R.id.iv_firstclass_two)
        ImageView ivFirstclassTwo;

        public ClassHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ShopHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_shop_icon)
        ImageView ivShopIcon;
        @BindView(R.id.tv_shop_title)
        TextView tvShopTitle;
        @BindView(R.id.tv_shop_sell)
        TextView tvShopSell;
        @BindView(R.id.tv_shop_distance)
        TextView tvShopDistance;
        @BindView(R.id.tv_shop_first)
        TextView tvShopFirst;
        @BindView(R.id.ll_shop_shou)
        LinearLayout llShopShou;
        @BindView(R.id.tv_shop_jian)
        TextView tvShopJian;
        @BindView(R.id.ll_shop_jian)
        LinearLayout llShopJian;
        @BindView(R.id.tv_shop_lowest)
        TextView tvShopLowest;
        @BindView(R.id.tv_shop_send)
        TextView tvShopSend;

        public ShopHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //广告数据
    public void getAdData(List<bean_ad.DataBean.ListBean> data, boolean isRefresh) {
        if (this.list_ad == null) {
            this.list_ad = new ArrayList<>();
        }
        if (isRefresh) {
            this.list_ad.clear();
        }
        this.list_ad.addAll(data);
        notifyDataSetChanged();

    }

    //分类数据
    public void getClassData(List<bean_class.DataBean.ListBean> data, boolean isRefresh) {
        if (this.list_class == null) {
            this.list_class = new ArrayList<>();
        }
        if (isRefresh) {
            this.list_class.clear();
        }
        this.list_class.addAll(data);
        notifyDataSetChanged();
    }

    //店铺数据
    public void getShopData(List<bean_shop.DataBean.ListBean> data, boolean isRefresh) {
        if (this.list_shop == null) {
            this.list_shop = new ArrayList<>();
        }
        if (isRefresh) {
            this.list_shop.clear();
        }
        this.list_shop.addAll(data);
        notifyDataSetChanged();
    }

    public bean_shop.DataBean.ListBean getShopData(int position) {
        return list_shop.get(position);

    }


}

package com.example.ayou.jk_takeout.firstpage.expandtabview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.adapter.SearchItemAdapter;
import com.example.ayou.jk_takeout.firstpage.adapter.SearchSysParamAdapter;
import com.example.ayou.jk_takeout.firstpage.config.netConfig;
import com.example.ayou.jk_takeout.firstpage.model.selectshopBean;

import java.util.ArrayList;
import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description:expandTab 左边 店铺选择
 */
public class SearchTabMiddle extends LinearLayout implements ViewBaseAction {

    private ListView oneListView;
    private Context mContext;
    private ListView twoListView;
    private ArrayList<selectshopBean.DataBean.ListBean> groups = new ArrayList<selectshopBean.DataBean.ListBean>();
    private LinkedList<selectshopBean.DataBean.ListBean.SublogsBean> childrenItem = new LinkedList<selectshopBean.DataBean.ListBean.SublogsBean>();
    private SparseArray<LinkedList<selectshopBean.DataBean.ListBean.SublogsBean>> children = new SparseArray<LinkedList<selectshopBean.DataBean.ListBean.SublogsBean>>();
    private SearchItemAdapter twoListViewAdapter;
    private SearchSysParamAdapter oneListViewAdapter;
    private OnSelectListener mOnSelectListener;
    private int tEaraPosition = 0;
    private int tBlockPosition = 0;
    private String showString = "不限";


    public SearchTabMiddle(Context context) {
        super(context);
        mContext = context;
        init(context);
    }

    public SearchTabMiddle(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }

    public void updateShowText(String showArea, String showBlock) {
        if (showArea == null || showBlock == null) {
            return;
        }
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getName().equals(showArea)) {
                oneListViewAdapter.setSelectedPosition(i);
                childrenItem.clear();
                if (i < children.size()) {
                    childrenItem.addAll(children.get(i));
                }
                tEaraPosition = i;
                break;
            }
        }
        for (int j = 0; j < childrenItem.size(); j++) {
            if (childrenItem.get(j).getName().replace("不限", "").equals(showBlock.trim())) {
                twoListViewAdapter.setSelectedPosition(j);
                tBlockPosition = j;
                break;
            }
        }
        setDefaultSelect();
    }

    /**
     * @Description: 读取数据
     */
    private void testData(Context context) {
        //联网解析数据
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(netConfig.BASIC_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        netConfig iservice = retrofit.create(netConfig.class);
        Call<selectshopBean> call = iservice.getData();
        call.enqueue(new Callback<selectshopBean>() {
            @Override
            public void onResponse(Call<selectshopBean> call, Response<selectshopBean> response) {
                selectshopBean bean = response.body();
                //获得一级菜单数据
                groups.addAll(bean.getData().getList());
                for (int j = 0; j < groups.size(); j++) {
                    LinkedList<selectshopBean.DataBean.ListBean.SublogsBean> item = new LinkedList<selectshopBean.DataBean.ListBean.SublogsBean>();
                    //获取二级菜单数据
                    item.addAll(bean.getData().getList().get(j).getSublogs());
                    //一级二级联动
                    children.put(j, item);
                }
            }

            public void onFailure(Call<selectshopBean> call, Throwable t) {

            }
        });


    }


    /**
     * @param context
     * @Description:初始化视图
     */
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.search_double_item, this, true);
        oneListView = (ListView) findViewById(R.id.listViewLvOne);
        twoListView = (ListView) findViewById(R.id.listViewLvTwo);

        // 测试数据
        testData(context);

        oneListViewAdapter = new SearchSysParamAdapter(context, groups, 0, 0, 0);
        oneListViewAdapter.setTextSize(17);
        oneListViewAdapter.setSelectedPositionNoNotify(tEaraPosition);
        oneListView.setAdapter(oneListViewAdapter);
        oneListViewAdapter.setOnItemClickListener(new SearchSysParamAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                if (position < children.size()) {
                    if (position == 0) {
                        if (mOnSelectListener != null) {
                            showString = "小吃快餐";
                            mOnSelectListener.getValue(groups.get(position).getId(), showString);
                        }
                    }
                    childrenItem.clear();
                    //重新获取
                    childrenItem.addAll(children.get(position));
                    twoListViewAdapter.notifyDataSetChanged();
                }
            }
        });
        if (tEaraPosition < children.size()) childrenItem.addAll(children.get(tEaraPosition));
        twoListViewAdapter = new SearchItemAdapter(context, childrenItem, 0, 0, 1);
        twoListViewAdapter.setTextSize(15);
        // twoListViewAdapter.setSelectedPositionNoNotify(tBlockPosition);
        twoListView.setAdapter(twoListViewAdapter);
        twoListViewAdapter.setOnItemClickListener(new SearchItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showString = childrenItem.get(position).getName();
                if (mOnSelectListener != null) {

                    mOnSelectListener.getValue(childrenItem.get(position).getId(), showString);
                }
            }
        });
        if (tBlockPosition < childrenItem.size())
            showString = childrenItem.get(tBlockPosition).getName();
        if (showString.contains("不限")) {
            showString = showString.replace("不限", "");
        }
        setDefaultSelect();

    }

    /**
     * @Description: 设置默认选中项
     */
    public void setDefaultSelect() {
        oneListView.setSelection(tEaraPosition);
        twoListView.setSelection(tBlockPosition);
    }

    public String getShowText() {
        return showString;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    //回調选择监听
    public interface OnSelectListener {

        public void getValue(String itemLvTwocode, String showText);
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }
}

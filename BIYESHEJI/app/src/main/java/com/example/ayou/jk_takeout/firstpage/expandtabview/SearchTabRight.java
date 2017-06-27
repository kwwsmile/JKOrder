package com.example.ayou.jk_takeout.firstpage.expandtabview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.adapter.RightAdapter;
import com.example.ayou.jk_takeout.firstpage.model.SysParamsBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description:expandTab 右 距离
 */
public class SearchTabRight extends RelativeLayout implements ViewBaseAction {

    private ListView mListView;
    private List<SysParamsBean> specialtyList;
    private final String[] items = new String[]{"智能排序", "距离最近", "销量最高"};//显示字段
    private final String[] itemsVaule = new String[]{"1", "2", "3"};//隐藏id
    private OnSelectListener mOnSelectListener;
    private RightAdapter adapter;
    private String mDistance;
    private long selectId;
    private String showText = "地区";
    private Context mContext;

    public String getShowText() {
        return showText;
    }

    public SearchTabRight(Context context) {
        super(context);
        init(context);
    }

    public SearchTabRight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public SearchTabRight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    //test数据
    private void testData() {
        specialtyList = new ArrayList<SysParamsBean>();
        SysParamsBean bean;
        for (int i = 0; i < items.length; i++) {
            bean = new SysParamsBean();
            bean.setParName(items[i]);
            bean.setParCode("" + i);
            specialtyList.add(bean);
        }
    }


    /**
     * @param context
     * @Description: 初始化视图

     */
    private void init(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.search_single_item, this, true);
        //setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_mid));
        mListView = (ListView) findViewById(R.id.listViewSingle);

        testData();
        adapter = new RightAdapter(context, specialtyList, 0, 0, 0);

        adapter.setSelectedPosition(0);
        adapter.setTextSize(17);
        if (mDistance != null) {
            for (int i = 0; i < specialtyList.size(); i++) {
                if (specialtyList.get(i).getId() == selectId) {
                    adapter.setSelectedPositionNoNotify(i);
                    showText = specialtyList.get(i).getParName();
                    break;
                }
            }
        }
        mListView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RightAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showText = specialtyList.get(position).getParName();
                mOnSelectListener.getValue(specialtyList.get(position).getParCode(), specialtyList.get(position).getParName());
            }
        });

    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        public void getValue(String itemCode, String showText);
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

}


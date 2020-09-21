package com.example.crazyshopping.ui.home;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.crazyshopping.R;
import com.example.crazyshopping.base.BaseFragment;
import com.example.crazyshopping.bean.HomeBean;
import com.example.crazyshopping.interfaces.home.IHome;
import com.example.crazyshopping.persenter.home.HomePersenter;
import com.example.crazyshopping.ui.home.adapter.HomeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<IHome.HomePersenter> implements IHome.HomeView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    HomeListAdapter homeListAdapter;
    List<HomeBean.HomeListBean> listBeans;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        listBeans = new ArrayList<>();
        homeListAdapter = new HomeListAdapter(listBeans,context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        homeListAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int type = listBeans.get(i).currentType;
                switch (type){
                    case HomeBean.ITEM_TYPE_BANNER:
                    case HomeBean.ITEM_TYPE_TITLE:
                    case HomeBean.ITEM_TYPE_TITLETOP:
                    case HomeBean.ITEM_TYPE_TOPIC:
                    case HomeBean.ITEM_TYPE_HOT:
                    case HomeBean.ITEM_TYPE_TAB:
                        return 2;
                    case HomeBean.ITEM_TYPE_BRAND:
                    case HomeBean.ITEM_TYPE_NEW:
                    case HomeBean.ITEM_TYPE_CATEGORY:
                        return 1;

                }
                return 0;
            }
        });
        recyclerview.setLayoutManager(gridLayoutManager);
        homeListAdapter.bindToRecyclerView(recyclerview);
    }

    @Override
    protected IHome.HomePersenter initPersenter() {
        return new HomePersenter();
    }

    @Override
    protected void initData() {
        persenter.getHome();
    }


    @Override
    public void getHomeResult(List<HomeBean.HomeListBean> list) {
        listBeans.addAll(list);
        homeListAdapter.notifyDataSetChanged();
    }

}
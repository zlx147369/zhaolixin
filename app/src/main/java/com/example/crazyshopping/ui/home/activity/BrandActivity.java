package com.example.crazyshopping.ui.home.activity;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crazyshopping.R;
import com.example.crazyshopping.base.BaseActivity;
import com.example.crazyshopping.bean.HomeBrandBean;
import com.example.crazyshopping.interfaces.home.brand.IBrand;
import com.example.crazyshopping.persenter.home.brand.BrandPersneter;
import com.example.crazyshopping.ui.home.adapter.HomeBrandAdapter;
import com.example.crazyshopping.ui.home.adapter.HomeBrandPingLunAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BrandActivity extends BaseActivity<IBrand.IBrandPersenter> implements IBrand.IBrandView {

    @BindView(R.id.recy)
    RecyclerView recy;

    private static final String TAG = "BrandActivity";
    int type;
    @BindView(R.id.recy_brand)
    RecyclerView recyBrand;
    private List<HomeBrandBean.DataBean.BrandBean> list;
    private HomeBrandAdapter homeBrandAdapter;
    private HomeBrandPingLunAdapter homeBrandPingLunAdapter;
    private List<HomeBrandBean.DataBean.BrandBean> brandBeans;
    @Override
    protected void initData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("id", 0);
        Log.i(TAG, "initData: " + type);
        persenter.getBrand(type);
    }

    @Override
    protected IBrand.IBrandPersenter initPersenter() {
        return new BrandPersneter();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        recy.setLayoutManager(new LinearLayoutManager(this));
        homeBrandAdapter = new HomeBrandAdapter(this, list);
        recy.setAdapter(homeBrandAdapter);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyBrand.setLayoutManager(manager);
        homeBrandPingLunAdapter = new HomeBrandPingLunAdapter(this, list);
        recyBrand.setAdapter(homeBrandPingLunAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_brand;
    }

    @Override
    public void getBrandRetuln(HomeBrandBean retuln) {
        list.add(retuln.getData().getBrand());
        homeBrandAdapter.notifyDataSetChanged();

//        brandBeans.add(retuln.getData().getBrand());
        homeBrandPingLunAdapter.notifyDataSetChanged();
    }

    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visible) {

    }



}
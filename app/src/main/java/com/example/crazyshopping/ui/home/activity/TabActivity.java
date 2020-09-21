package com.example.crazyshopping.ui.home.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.crazyshopping.R;
import com.example.crazyshopping.adapter.VpFragmentAda;
import com.example.crazyshopping.base.BaseActivity;
import com.example.crazyshopping.bean.ClassificationBean;
import com.example.crazyshopping.interfaces.home.ITab;
import com.example.crazyshopping.persenter.home.TabPersenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class TabActivity extends BaseActivity<ITab.TabPersenter> implements ITab.TabView {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    int id;

    @Override
    protected void initData() {
        persenter.getClassification(id);
    }

    @Override
    protected ITab.TabPersenter initPersenter() {
        return new TabPersenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_tab;
    }

    @Override
    public void getClassificationRetuln(ClassificationBean retuln) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < retuln.getData().getCategoryList().size(); i++) {
            fragments.add(new Fragment(retuln.getData().getCurrentCategory().getType()));
            strings.add(retuln.getData().getCategoryList().get(i).getName());
        }
        VpFragmentAda vpFragmentAda = new VpFragmentAda(getSupportFragmentManager(), fragments, strings);
        viewpager.setAdapter(vpFragmentAda);
        tablayout.setupWithViewPager(viewpager);
    }

    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visible) {

    }
}
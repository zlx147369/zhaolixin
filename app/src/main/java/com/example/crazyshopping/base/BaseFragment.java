package com.example.crazyshopping.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crazyshopping.interfaces.IBasePersenter;
import com.example.crazyshopping.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePersenter> extends Fragment implements IBaseView {

    Unbinder unbinder;

    protected P persenter;
    protected Context context;
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(),null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        initView();
        persenter = initPersenter();
        if(persenter != null){
            persenter.attatchView(this);
            initData();
        }
    }

    /**
     * 获取界面布局的方法
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化p层
     */
    protected abstract P initPersenter();

    /**
     * 初始化加载数据
     */
    protected abstract void initData();

    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
        if(persenter != null){
            persenter.datachView();
        }
    }
}

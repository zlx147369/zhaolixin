package com.example.crazyshopping.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.crazyshopping.interfaces.IBasePersenter;
import com.example.crazyshopping.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasePersenter> extends AppCompatActivity implements IBaseView {
    Unbinder unbinder;
    protected P persenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        initView();
        persenter = initPersenter();
        if (persenter != null) {
            persenter.attatchView(this);
            initData();
        }
    }

    protected abstract void initData();

    protected abstract P initPersenter();

    protected abstract void initView();

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder!=null){
            unbinder.unbind();
        }
        if(persenter!=null){
            persenter.datachView();
        }
    }
}

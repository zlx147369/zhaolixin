package com.example.crazyshopping.persenter.home.brand;

import com.example.crazyshopping.base.BasePersenter;
import com.example.crazyshopping.bean.HomeBrandBean;
import com.example.crazyshopping.common.CommonSubscriber;
import com.example.crazyshopping.interfaces.IBasePersenter;
import com.example.crazyshopping.interfaces.home.brand.IBrand;
import com.example.crazyshopping.model.HttpManager;
import com.example.crazyshopping.utils.RxUtils;

public class BrandPersneter extends BasePersenter<IBrand.IBrandView> implements IBrand.IBrandPersenter {
    @Override
    public void getBrand(int type) {
        addSubscribe(HttpManager.getInstance().getTpApi().getBrand(type)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<HomeBrandBean>(mView) {
            @Override
            public void onNext(HomeBrandBean homeBrandBean) {
                mView.getBrandRetuln(homeBrandBean);
            }
        }));
    }
}

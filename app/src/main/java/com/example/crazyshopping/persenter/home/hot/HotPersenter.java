package com.example.crazyshopping.persenter.home.hot;

import android.util.Log;

import com.example.crazyshopping.base.BasePersenter;
import com.example.crazyshopping.bean.AddCartInfoBean;
import com.example.crazyshopping.bean.HomeHotBean;
import com.example.crazyshopping.common.CommonSubscriber;
import com.example.crazyshopping.interfaces.home.hot.IHot;
import com.example.crazyshopping.model.HttpManager;
import com.example.crazyshopping.utils.RxUtils;

public class HotPersenter extends BasePersenter<IHot.IHotView> implements IHot.IHotPersenter {


    @Override
    public void getHot(int type) {
        addSubscribe(HttpManager.getInstance().getTpApi().getHomeHot(type)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<HomeHotBean>(mView) {
            @Override
            public void onNext(HomeHotBean homeHotBean) {
                mView.getHotRetuln(homeHotBean);
            }
        }));
    }
    @Override
    public void addCart(int goodsId, int number, int productId) {
        addSubscribe(HttpManager.getInstance().getTpApi().addCart(goodsId,number,productId)
                .compose(RxUtils.<AddCartInfoBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCartInfoBean>(mView) {
                    @Override
                    public void onNext(AddCartInfoBean result) {
                        mView.addCartInfoReturn(result);
                        Log.e("TAG", "onNext: "+mView.toString() );
                    }
                }));
    }
}

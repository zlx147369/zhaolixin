package com.example.crazyshopping.persenter.home.cart;

import com.example.crazyshopping.base.BasePersenter;
import com.example.crazyshopping.bean.AdressBean;
import com.example.crazyshopping.common.CommonSubscriber;
import com.example.crazyshopping.interfaces.home.cart.ICart;
import com.example.crazyshopping.model.HttpManager;
import com.example.crazyshopping.utils.RxUtils;

public class AdressPersenter extends BasePersenter<ICart.IAdressView> implements ICart.IAdressPersenter {
    @Override
    public void getAdressById(int parentId) {
        addSubscribe(HttpManager.getInstance().getTpApi().getAdressById(parentId)
                .compose(RxUtils.<AdressBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AdressBean>(mView) {
                    @Override
                    public void onNext(AdressBean result) {
                        mView.getAdressByIdReturn(result);
                    }
                }));
    }
}

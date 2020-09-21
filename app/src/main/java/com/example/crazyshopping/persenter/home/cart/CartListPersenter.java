package com.example.crazyshopping.persenter.home.cart;

import com.example.crazyshopping.base.BasePersenter;
import com.example.crazyshopping.bean.CartBean;
import com.example.crazyshopping.bean.DeleteCartBean;
import com.example.crazyshopping.common.CommonSubscriber;
import com.example.crazyshopping.interfaces.home.cart.ICart;
import com.example.crazyshopping.model.HttpManager;
import com.example.crazyshopping.utils.RxUtils;

public class CartListPersenter extends BasePersenter<ICart.ICartView> implements ICart.ICartPersenter {
    @Override
    public void getCartList() {
        addSubscribe(HttpManager.getInstance().getTpApi().getCartList()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CartBean>(mView) {
                    @Override
                    public void onNext(CartBean result) {
                        mView.getCartListReturn(result);
                    }
                }));
    }

    @Override
    public void deleteCartList(String productIds) {
        addSubscribe(HttpManager.getInstance().getTpApi().cartDelete(productIds)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCartBean>(mView) {
                    @Override
                    public void onNext(DeleteCartBean result) {
                        mView.deleteCartListReturn(result);
                    }
                }));
    }
}

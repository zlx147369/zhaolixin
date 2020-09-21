package com.example.crazyshopping.persenter.home.cart;

import com.example.crazyshopping.base.BasePersenter;
import com.example.crazyshopping.bean.AddCartInfoBean;
import com.example.crazyshopping.bean.HomeHotBean;
import com.example.crazyshopping.common.CommonSubscriber;
import com.example.crazyshopping.interfaces.home.cart.ICart;
import com.example.crazyshopping.model.HttpManager;
import com.example.crazyshopping.utils.RxUtils;

public class CartPersenter extends BasePersenter<ICart.IView> implements ICart.IPersenter {
    @Override
    public void getGoodDetail(int id) {
        addSubscribe(HttpManager.getInstance().getTpApi().getHomeHot(id)
                .compose(RxUtils.<HomeHotBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeHotBean>(mView) {
                    @Override
                    public void onNext(HomeHotBean result) {
                        mView.getGoodDetailReturn(result);
                    }
                }));
    }

    /**
     * 添加购物车
     * @param goodsId
     * @param number
     * @param productId
     */
    @Override
    public void addCart(int goodsId, int number, int productId) {
        addSubscribe(HttpManager.getInstance().getTpApi().addCart(goodsId,number,productId)
                .compose(RxUtils.<AddCartInfoBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCartInfoBean>(mView) {
                    @Override
                    public void onNext(AddCartInfoBean result) {
                        mView.addCartInfoReturn(result);
                    }
                }));
    }

}

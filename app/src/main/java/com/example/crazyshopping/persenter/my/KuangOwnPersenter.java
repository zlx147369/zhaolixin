package com.example.crazyshopping.persenter.my;

import com.example.crazyshopping.base.BasePersenter;
import com.example.crazyshopping.bean.UserLogBean;
import com.example.crazyshopping.common.CommonSubscriber;
import com.example.crazyshopping.interfaces.home.my.IKuangOwn;
import com.example.crazyshopping.model.HttpManager;
import com.example.crazyshopping.utils.RxUtils;

import java.util.Map;


public class KuangOwnPersenter extends BasePersenter<IKuangOwn.View> implements IKuangOwn.Persenter {

    @Override
    public void getUserLog(Map<String, String> map) {
        addSubscribe(HttpManager.getInstance().getTpApi().getVertical(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UserLogBean>(mView) {
                    @Override
                    public void onNext(UserLogBean hanFuDaBean) {
                        mView.getUserLog(hanFuDaBean);
                    }
                }));
    }

    @Override
    public void getDengLu(Map<String, String> map) {
        addSubscribe(HttpManager.getInstance().getTpApi().getDengLu(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UserLogBean>(mView) {
                    @Override
                    public void onNext(UserLogBean hanFuDaBean) {
                        mView.getDengLu(hanFuDaBean);
                    }
                }));
    }
}

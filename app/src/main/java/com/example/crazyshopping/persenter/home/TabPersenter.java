package com.example.crazyshopping.persenter.home;

import com.example.crazyshopping.base.BasePersenter;
import com.example.crazyshopping.bean.ClassificationBean;
import com.example.crazyshopping.common.CommonSubscriber;
import com.example.crazyshopping.interfaces.home.ITab;
import com.example.crazyshopping.model.HttpManager;
import com.example.crazyshopping.utils.RxUtils;

public class TabPersenter extends BasePersenter<ITab.TabView> implements ITab.TabPersenter {


    @Override
    public void getClassification(int id) {
        addSubscribe(HttpManager.getInstance().getTpApi().getClassification(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<ClassificationBean>(mView) {
            @Override
            public void onNext(ClassificationBean classificationBean) {
                mView.getClassificationRetuln(classificationBean);
            }
        }));
    }
}

package com.example.crazyshopping.interfaces;

public interface IBasePersenter<T extends IBaseView> {
    //V层接口的关联
    void attatchView(T view);
    //V层接口的取消关联
    void datachView();
}

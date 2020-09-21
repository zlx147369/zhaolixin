package com.example.crazyshopping.interfaces.home.hot;

import com.example.crazyshopping.bean.AddCartInfoBean;
import com.example.crazyshopping.bean.HomeHotBean;
import com.example.crazyshopping.interfaces.IBasePersenter;
import com.example.crazyshopping.interfaces.IBaseView;

public interface IHot {
    interface IHotView extends IBaseView{

        void getHotRetuln(HomeHotBean retuln);

        void addCartInfoReturn(AddCartInfoBean result);
    }
    interface IHotPersenter extends IBasePersenter<IHotView>{
        void getHot(int id);

        void addCart(int goodsId,int number,int productId);
    }
}

package com.example.crazyshopping.interfaces.home.brand;

import com.example.crazyshopping.bean.HomeBrandBean;
import com.example.crazyshopping.interfaces.IBasePersenter;
import com.example.crazyshopping.interfaces.IBaseView;

public interface IBrand {
    interface IBrandView extends IBaseView{
        void getBrandRetuln(HomeBrandBean retuln);
    }
    interface IBrandPersenter extends IBasePersenter<IBrandView>{
        void getBrand(int type);
    }
}

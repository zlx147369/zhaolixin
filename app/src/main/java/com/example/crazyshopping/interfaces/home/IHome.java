package com.example.crazyshopping.interfaces.home;

import com.example.crazyshopping.bean.HomeBean;
import com.example.crazyshopping.interfaces.IBasePersenter;
import com.example.crazyshopping.interfaces.IBaseView;

import java.util.List;

public interface IHome {
    interface View extends IBaseView{

    }
    interface Persenter extends IBasePersenter<View>{

    }

    interface HomeView extends IBaseView{
        void getHomeResult(List<HomeBean.HomeListBean> list);
    }
    interface HomePersenter extends IBasePersenter<HomeView>{
        void getHome();

    }
}

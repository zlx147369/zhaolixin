package com.example.crazyshopping.interfaces.home.my;



import com.example.crazyshopping.bean.UserLogBean;
import com.example.crazyshopping.interfaces.IBasePersenter;
import com.example.crazyshopping.interfaces.IBaseView;

import java.util.Map;

public interface IKuangOwn {
    interface View extends IBaseView {

        void getUserLog(UserLogBean userBean);
        void getDengLu(UserLogBean userBean);
    }


    interface Persenter extends IBasePersenter<View> {

        void getUserLog(Map<String, String> map);

        void getDengLu(Map<String, String> map);
    }

}

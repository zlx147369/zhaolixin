package com.example.crazyshopping.interfaces.home;

import com.example.crazyshopping.bean.ClassificationBean;
import com.example.crazyshopping.interfaces.IBasePersenter;
import com.example.crazyshopping.interfaces.IBaseView;

public interface ITab {
    interface TabView extends IBaseView{
        void getClassificationRetuln(ClassificationBean retuln);
    }
    interface TabPersenter extends IBasePersenter<TabView>{
        void getClassification(int id);
    }
}

package com.example.crazyshopping.interfaces.home.cart;


import com.example.crazyshopping.bean.AddCartInfoBean;
import com.example.crazyshopping.bean.AdressBean;
import com.example.crazyshopping.bean.CartBean;
import com.example.crazyshopping.bean.DeleteCartBean;
import com.example.crazyshopping.bean.HomeHotBean;
import com.example.crazyshopping.interfaces.IBasePersenter;
import com.example.crazyshopping.interfaces.IBaseView;

public interface ICart {
    interface IView extends IBaseView{

        void getGoodDetailReturn(HomeHotBean result);

        //添加商品信息返回
        void addCartInfoReturn(AddCartInfoBean result);

    }

    interface IPersenter extends IBasePersenter<IView>{
        //获取商品详情
        void getGoodDetail(int id);

        //添加到购物车
        void addCart(int goodsId,int number,int productId);
    }

    /**
     * 购物车接口
     */
    interface ICartView extends IBaseView {
        void getCartListReturn(CartBean result);

        void deleteCartListReturn(DeleteCartBean result);
    }

    interface ICartPersenter extends IBasePersenter<ICartView>{

        //获取购物车的数据
        void getCartList();

        //删除购物车数据
        void deleteCartList(String productIds);

    }

    interface IAdressView extends IBaseView{
        void getAdressByIdReturn(AdressBean result);
    }

    interface IAdressPersenter extends IBasePersenter<IAdressView>{
        void getAdressById(int parentId);
    }
}

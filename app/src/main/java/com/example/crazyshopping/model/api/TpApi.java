package com.example.crazyshopping.model.api;

import com.example.crazyshopping.bean.AddCartInfoBean;
import com.example.crazyshopping.bean.AdressBean;
import com.example.crazyshopping.bean.CartBean;
import com.example.crazyshopping.bean.ClassificationBean;
import com.example.crazyshopping.bean.DeleteCartBean;
import com.example.crazyshopping.bean.HomeBean;
import com.example.crazyshopping.bean.HomeBrandBean;
import com.example.crazyshopping.bean.HomeHotBean;
import com.example.crazyshopping.bean.UserLogBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TpApi {
    @GET("index")
    Flowable<HomeBean> getHome();

    //分类
    @GET("catalog/index")
    Flowable<ClassificationBean> getClassification(int id);

    //人气详情
    @GET("goods/detail")
    Flowable<HomeHotBean> getHomeHot(@Query("id") int type);

    //品牌详情
    @GET("brand/detail")
    Flowable<HomeBrandBean> getBrand(@Query("id") int type);

    //添加到购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCartInfoBean> addCart(@Field("goodsId") int goodsId, @Field("number") int number, @Field("productId") int productId);


    @GET("cart/index")
    Flowable<CartBean> getCartList();

    //删除购物车
    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCartBean> cartDelete(@Field("productIds") String productIds);

    @GET("region/list")
    Flowable<AdressBean> getAdressById(@Query("parentId") int parentId);


    //登陆
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<UserLogBean> getVertical(@FieldMap Map<String, String> map);

    @POST("auth/register")
    @FormUrlEncoded
    Flowable<UserLogBean> getDengLu(@FieldMap Map<String, String> map);

}

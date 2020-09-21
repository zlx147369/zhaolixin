package com.example.crazyshopping.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.crazyshopping.R;
import com.example.crazyshopping.bean.HomeBean;
import com.example.crazyshopping.ui.home.activity.BrandActivity;
import com.example.crazyshopping.ui.home.activity.HotdetailsActivity;
import com.example.crazyshopping.ui.home.activity.TabActivity;
import com.example.crazyshopping.utils.SystemUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;

public class HomeListAdapter extends BaseMultiItemQuickAdapter<HomeBean.HomeListBean, BaseViewHolder> {

    private Context context;
    private String priceWord;
    private TopicAdapter topicAdapter;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeListAdapter(List<HomeBean.HomeListBean> data, Context context) {
        super(data);
        this.context = context;
        priceWord = context.getString(R.string.word_price_brand);
        //做UI绑定
        addItemType(HomeBean.ITEM_TYPE_BANNER, R.layout.layout_home_banner);
        addItemType(HomeBean.ITEM_TYPE_TAB,R.layout.layout_home_tab);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP,R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_BRAND,R.layout.layout_home_brand);
        addItemType(HomeBean.ITEM_TYPE_TITLE,R.layout.layout_title);
        addItemType(HomeBean.ITEM_TYPE_NEW,R.layout.layout_home_newgood);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP,R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_HOT,R.layout.layout_home_hot);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP,R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_TOPIC,R.layout.layout_home_topiclist);
        addItemType(HomeBean.ITEM_TYPE_CATEGORY,R.layout.layout_home_category);
    }

    /**
     * 主要刷新数据，绑定数据  onbindviewholder
     * @param helper viewholder 界面
     * @param item 数据
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HomeListBean item) {
        switch (item.getItemType()){
            case HomeBean.ITEM_TYPE_TITLE:
                updateTitle(helper, (String) item.data);
                break;
            case HomeBean.ITEM_TYPE_TITLETOP:
                updateTitle(helper, (String) item.data);
                break;
            case HomeBean.ITEM_TYPE_BANNER:
                updateBanner(helper, (List<HomeBean.DataBean.BannerBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_TAB:
                updateTab(helper, (List<HomeBean.DataBean.ChannelBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_BRAND:
                updateBrand(helper, (HomeBean.DataBean.BrandListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_NEW:
                updateNewGood(helper, (HomeBean.DataBean.NewGoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_HOT:
                udpateHot(helper, (HomeBean.DataBean.HotGoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_TOPIC:
                updateTopic(helper, (List<HomeBean.DataBean.TopicListBean>) item.data);
                break;
                case HomeBean.ITEM_TYPE_CATEGORY:
                    updateCategory(helper, (HomeBean.DataBean.CategoryListBean.GoodsListBean) item.data);
                    break;
}
    }

    /**
     * 刷新title
     * @param viewHolder
     * @param title
     */
    private void updateTitle(BaseViewHolder viewHolder, String title){
        viewHolder.setText(R.id.txt_title,title);
    }

    /**
     * 刷新banner
     * @param viewHolder
     * @param bannsers
     */
    private void updateBanner(BaseViewHolder viewHolder, List<HomeBean.DataBean.BannerBean> bannsers){
        Banner banner = viewHolder.getView(R.id.banner);
        banner.setImages(bannsers);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean bean = (HomeBean.DataBean.BannerBean) path;
                Glide.with(context).load(bean.getImage_url()).into(imageView);
            }
        }).start();
    }

    /**
     * 刷新channel
     * @param viewHolder
     * @param channels
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateTab(BaseViewHolder viewHolder, List<HomeBean.DataBean.ChannelBean> channels){
        LinearLayout layoutChannels = viewHolder.getView(R.id.layout_tab);
        //只让当前的布局内容添加一次 only one
        Log.d(TAG, "updateTab: "+channels.size());
        if(layoutChannels.getChildCount() == 0){
            for(HomeBean.DataBean.ChannelBean item:channels){
                TextView tab = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1);
                int size = SystemUtils.dp2px(context,10);
                tab.setTextSize(size);
                tab.setGravity(Gravity.CENTER);
                tab.setText(item.getName());
                params.leftMargin=10;
                params.rightMargin=10;
                tab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, TabActivity.class);
                        context.startActivity(intent);
                    }
                });
//                Drawable icon = context.getDrawable(R.mipmap.ic_channel1);
//                tab.setCompoundDrawables(null,icon,null,null);
                tab.setLayoutParams(params);
                layoutChannels.addView(tab);
            }
        }
    }

    /**
     * 刷新品牌
     * @param viewHolder
     * @param brands
     */
    private void updateBrand(BaseViewHolder viewHolder, HomeBean.DataBean.BrandListBean brands){
        if(!TextUtils.isEmpty(brands.getNew_pic_url())){
            Glide.with(context).load(brands.getNew_pic_url()).into((ImageView) viewHolder.getView(R.id.img_brand));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BrandActivity.class);
                intent.putExtra("id",brands.getId());
                context.startActivity(intent);
            }
        });
        viewHolder.setText(R.id.txt_brand_name,brands.getName());
        String price = priceWord.replace("$", String.valueOf(brands.getFloor_price()));
        viewHolder.setText(R.id.txt_brand_price,price);

    }

    /**
     * 刷新新品数据
     * @param viewHolder
     * @param newGoods
     */
    private void updateNewGood(BaseViewHolder viewHolder, HomeBean.DataBean.NewGoodsListBean newGoods){
        Glide.with(context).load(newGoods.getList_pic_url()).into((ImageView) viewHolder.getView(R.id.img_newgood));
        viewHolder.setText(R.id.txt_newgood_name,newGoods.getName());
        String pice = priceWord.replace("$", String.valueOf(newGoods.getRetail_price()));
        viewHolder.setText(R.id.txt_newgood_price,pice);
    }

    /**
     * 刷新人气数据
     */
    private void udpateHot(BaseViewHolder viewHolder, HomeBean.DataBean.HotGoodsListBean hotGoods){
        Glide.with(context).load(hotGoods.getList_pic_url()).into((ImageView) viewHolder.getView(R.id.img_hot));
        viewHolder.setText(R.id.txt_hot_name,hotGoods.getName());
        viewHolder.setText(R.id.txt_hot_title,hotGoods.getGoods_brief());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HotdetailsActivity.class);
                intent.putExtra("id",hotGoods.getId());
                context.startActivity(intent);
            }
        });
        String pice = priceWord.replace("$", String.valueOf(hotGoods.getRetail_price()));
        viewHolder.setText(R.id.txt_hot_price,pice);
    }

    /**
     * 刷新专题
     * @param viewHolder
     * @param topicGoods
     */
    private void updateTopic(BaseViewHolder viewHolder, List<HomeBean.DataBean.TopicListBean> topicGoods){
        RecyclerView recyclerView = viewHolder.getView(R.id.recyclerviewTopic);
        if(topicAdapter == null){
            topicAdapter = new TopicAdapter(context,topicGoods);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(topicAdapter);
        }else if(recyclerView.getAdapter() == null){
            recyclerView.setAdapter(topicAdapter);
        }
    }

    private void updateCategory(BaseViewHolder holder, HomeBean.DataBean.CategoryListBean.GoodsListBean data) {
        if (!TextUtils.isEmpty(data.getList_pic_url())){
            Glide.with(context).load(data.getList_pic_url()).into((ImageView)holder.getView(R.id.image_head));
        }
        if (!TextUtils.isEmpty(data.getName())){
            ((TextView)holder.getView(R.id.tv_title)).setText(data.getName());
        }
        ((TextView)holder.getView(R.id.money)).setText("￥"+(double)data.getRetail_price());
    }
}

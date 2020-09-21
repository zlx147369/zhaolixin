package com.example.crazyshopping.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.crazyshopping.R;
import com.example.crazyshopping.base.BaseAdapter;
import com.example.crazyshopping.bean.HomeBrandBean;

import java.util.List;

public class HomeBrandAdapter extends BaseAdapter {

    public HomeBrandAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.brand_item_layout;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data) {
        ImageView image_head = (ImageView) vh.getViewById(R.id.image_head);
        TextView title = (TextView) vh.getViewById(R.id.title);
        HomeBrandBean.DataBean.BrandBean _data = (HomeBrandBean.DataBean.BrandBean) data;
        Glide.with(context).load(_data.getPic_url()).into(image_head);
        title.setText(_data.getSimple_desc());
    }
}
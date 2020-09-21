package com.example.crazyshopping.ui.home.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.crazyshopping.R;
import com.example.crazyshopping.base.BaseAdapter;
import com.example.crazyshopping.bean.HomeBrandBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeBrandPingLunAdapter extends BaseAdapter {


    public HomeBrandPingLunAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.brand_pinglun_layout;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data) {
        ImageView image_head = (ImageView) vh.getViewById(R.id.image_hea);
        TextView tvtitle = (TextView) vh.getViewById(R.id.tvtitle);
        HomeBrandBean.DataBean.BrandBean _data = (HomeBrandBean.DataBean.BrandBean) data;
        tvtitle.setText(_data.getName());
        Glide.with(context).load(_data.getNew_pic_url()).into(image_head);
    }
}

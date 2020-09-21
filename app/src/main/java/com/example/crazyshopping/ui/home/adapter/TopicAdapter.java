package com.example.crazyshopping.ui.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.crazyshopping.R;
import com.example.crazyshopping.base.BaseAdapter;
import com.example.crazyshopping.bean.HomeBean;

import java.util.List;

public class TopicAdapter extends BaseAdapter {
    public TopicAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_home_topic;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data) {
        HomeBean.DataBean.TopicListBean topicListBean = (HomeBean.DataBean.TopicListBean) data;
        if(!TextUtils.isEmpty(topicListBean.getItem_pic_url())){
            Glide.with(context).load(topicListBean.getItem_pic_url()).into((ImageView) vh.getViewById(R.id.img_topic));
        }
        ((TextView)vh.getViewById(R.id.txt_topic_name)).setText(topicListBean.getTitle());
    }


}

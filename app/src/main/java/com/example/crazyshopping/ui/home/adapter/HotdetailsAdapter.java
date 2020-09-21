package com.example.crazyshopping.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crazyshopping.R;
import com.example.crazyshopping.bean.HomeHotBean;

import java.util.ArrayList;

public class HotdetailsAdapter extends RecyclerView.Adapter<HotdetailsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HomeHotBean.DataBeanX.InfoBean> list;

    public HotdetailsAdapter(Context context, ArrayList<HomeHotBean.DataBeanX.InfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.hotdetalis_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvtitle.setText(list.get(position).getName()+"");
        holder.tvmoney.setText("￥"+list.get(position).getRetail_price());
        Glide.with(context).load(list.get(position).getPrimary_pic_url()).into(holder.imagehead);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagehead;
        TextView tvtitle;
        TextView tvmoney;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             imagehead = itemView.findViewById(R.id.image_head);
             tvtitle = itemView.findViewById(R.id.tv_title);
             tvmoney = itemView.findViewById(R.id.tv_money);
        }
    }
}

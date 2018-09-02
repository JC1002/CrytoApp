package com.example.jn1002.crytoapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jn1002.crytoapp.CoinDetails;
import com.example.jn1002.crytoapp.Interface.ILoadMore;
import com.example.jn1002.crytoapp.MainActivity;
import com.example.jn1002.crytoapp.Model.CoinModel;
import com.example.jn1002.crytoapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ILoadMore iLoadMore;
    boolean isLoading;
    Activity activity;
    List<CoinModel> items;
    Context ctx;

    int visibleThreshold = 5, lastVisibleItem, totalItemCount;

    public CoinAdapter(RecyclerView recyclerView, Activity activity, List<CoinModel> items, Context ctx) {
        this.activity = activity;
        this.items = items;
        this.ctx = ctx;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <=(lastVisibleItem+visibleThreshold))
                {
                    if(iLoadMore != null)
                        iLoadMore.onLoadMore();
                    isLoading = true;
                }
            }
        });
    }

    public void setiLoadMore(ILoadMore iLoadMore) {
        this.iLoadMore = iLoadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity)
                .inflate(R.layout.coin_layout,parent,false);
        return new CoinViewHolder(view,ctx,items);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        CoinModel item = items.get(position);
        CoinViewHolder holderItem = (CoinViewHolder)holder;

        holderItem.coin_name.setText(item.getName());
        holderItem.coin_symbol.setText(item.getSymbol());
        holderItem.coin_price.setText(item.getPrice_usd());
        holderItem.one_hour_change.setText(item.getPercent_change_1h()+"%");
        holderItem.twenty_hours_change.setText(item.getPercent_change_24h()+"%");
        holderItem.seven_days_change.setText(item.getPercent_change_7d()+"%");


        //Loading Images
        Picasso.with(activity)
                .load(new StringBuilder("https://res.cloudinary.com/jn1002/image/upload/v1532214386/coins/")
                        .append(item.getSymbol().toLowerCase()).append(".png").toString())
                        .into(holderItem.coin_icon);

        holderItem.one_hour_change.setTextColor(item.getPercent_change_1h().contains("-")?
                Color.parseColor("#FF0000"):Color.parseColor("#32CD32"));
        holderItem.twenty_hours_change.setTextColor(item.getPercent_change_24h().contains("-")?
                Color.parseColor("#FF0000"):Color.parseColor("#32CD32"));
        holderItem.seven_days_change.setTextColor(item.getPercent_change_7d().contains("-")?
                Color.parseColor("#FF0000"):Color.parseColor("#32CD32"));


    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public void setLoaded()
    {isLoading = false;}

    public void updateData(List<CoinModel> coinModels) {

        this.items = coinModels;
        notifyDataSetChanged();
    }

    public void updateList(List<CoinModel> coins){

        items.addAll(coins);
        notifyDataSetChanged();

    }


}

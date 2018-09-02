package com.example.jn1002.crytoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jn1002.crytoapp.CoinDetails;
import com.example.jn1002.crytoapp.Model.CoinModel;
import com.example.jn1002.crytoapp.R;

import java.util.ArrayList;
import java.util.List;

public class CoinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView coin_icon;
    public TextView coin_symbol, coin_name, coin_price, one_hour_change, twenty_hours_change, seven_days_change;

    //New ArrayList
    List<CoinModel> items = new ArrayList<CoinModel>();
    Context ctx;
    public CoinViewHolder(View itemView, Context ctx, List<CoinModel> items)
    {
        super(itemView);
        this.items = items;
        this.ctx = ctx;
        itemView.setOnClickListener(this);
        coin_symbol = (TextView) itemView.findViewById(R.id.coin_symbol);
        coin_icon = (ImageView)itemView.findViewById(R.id.coin_icon);
        coin_name = (TextView) itemView.findViewById(R.id.coin_name);
        coin_price = (TextView) itemView.findViewById(R.id.priceUsdText);
        one_hour_change = (TextView) itemView.findViewById(R.id.oneHourText);
        twenty_hours_change = (TextView) itemView.findViewById(R.id.twentyFourHourText);
        seven_days_change = (TextView) itemView.findViewById(R.id.sevenDayText);

}

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        CoinModel coinModel = this.items.get(position);
        Intent intent = new Intent(this.ctx, CoinDetails.class);
        intent.putExtra("coin_icon",coinModel.getId());
        intent.putExtra("name", coinModel.getName());
        intent.putExtra("price", coinModel.getPrice_usd());
        intent.putExtra("symbol", coinModel.getSymbol());
        this.ctx.startActivity(intent);


    }
}

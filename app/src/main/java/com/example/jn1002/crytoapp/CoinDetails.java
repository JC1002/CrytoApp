package com.example.jn1002.crytoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jn1002.crytoapp.Model.CoinModel;
import com.squareup.picasso.Picasso;

public class CoinDetails extends AppCompatActivity {
    ImageView coin_icon;
    TextView coin_name, coin_price, coin_symbol;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_details_layout);
        coin_icon = (ImageView)findViewById(R.id.coin_image_details);
        coin_name = (TextView)findViewById(R.id.coin_name_details);
        coin_price = (TextView)findViewById(R.id.coin_price_details);
        coin_symbol = (TextView)findViewById(R.id.coin_symbol_details);


        coin_icon.setImageResource(getIntent().getIntExtra("coin_icon",00));
        coin_name.setText("Name : "+getIntent().getStringExtra("name"));
        coin_price.setText("Price : $"+getIntent().getStringExtra("price"));
        coin_symbol.setText("Symbol :" + getIntent().getStringExtra("symbol"));

        Picasso.with(ctx).load("https://res.cloudinary.com/jn1002/image/upload/v1532214386/coins/").into(coin_icon);
        Picasso.with(ctx).setIndicatorsEnabled(true);


    }
}

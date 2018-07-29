package com.example.jn1002.crytoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CoinDetails extends AppCompatActivity {
    ImageView coin_icon;
    TextView coin_name, coin_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_details_layout);
        coin_icon = (ImageView)findViewById(R.id.coin_image_details);
        coin_name = (TextView)findViewById(R.id.coin_name_details);
        coin_price = (TextView)findViewById(R.id.coin_price_details);

        Intent getId = getIntent();
        int gettingCoins = getId.getIntExtra("coin_icon",4);
        Picasso.with(CoinDetails.this).load(gettingCoins).into(coin_icon);

        /* coin_icon.setImageResource(getIntent().getIntExtra("coin_icon",00)); */
        coin_name.setText("Name : "+getIntent().getStringExtra("name"));
        coin_price.setText("Price : $"+getIntent().getStringExtra("price"));


    }
}

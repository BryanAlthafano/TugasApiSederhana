package com.example.tryapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailSport extends AppCompatActivity {

    TextView tvDetailName;
    TextView tvDetailDescription;
    ImageView ivDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sport);

        tvDetailName = (TextView) findViewById(R.id.tvDetailName);
        tvDetailDescription = (TextView) findViewById(R.id.tvDetailDescription);
        ivDetailImage = (ImageView) findViewById(R.id.ivDetailImage);

        tvDetailName.setText(getIntent().getStringExtra("name"));
        tvDetailDescription.setText(getIntent().getStringExtra("description"));
        Glide.with(this)
                .load(getIntent().getStringExtra("image"))
                .into(ivDetailImage);
    }
}
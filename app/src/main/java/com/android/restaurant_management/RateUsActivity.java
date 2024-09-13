package com.android.restaurant_management;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RateUsActivity extends AppCompatActivity {
    ImageView ivRateTypeEmoji;
    RatingBar ratingBar;
    TextView tvRateType;
    AppCompatButton btnCancel,btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);

        ivRateTypeEmoji = findViewById(R.id.ivRateTypeEmoji);
        ratingBar = findViewById(R.id.ratingBar);
        tvRateType = findViewById(R.id.tvRateType);
        btnCancel = findViewById(R.id.btnCancel);
        btnSubmit = findViewById(R.id.btnSubmit);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RateUsActivity.this, String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(RateUsActivity.this, HomeActivity.class);
               startActivity(i);
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(ratingBar.getRating()==1) {
                    tvRateType.setText("Very Bad");
                    ivRateTypeEmoji.setImageResource(R.drawable.verybad);
                }else if (ratingBar.getRating()==2) {
                    tvRateType.setText("Bad");
                    ivRateTypeEmoji.setImageResource(R.drawable.bad);
                }else if (ratingBar.getRating()==3) {
                    tvRateType.setText("Average");
                    ivRateTypeEmoji.setImageResource(R.drawable.average);
                }else if (ratingBar.getRating()==4) {
                    tvRateType.setText("Good");
                    ivRateTypeEmoji.setImageResource(R.drawable.good);
                }else if (ratingBar.getRating()==5) {
                    tvRateType.setText("Excellent");
                    ivRateTypeEmoji.setImageResource(R.drawable.excellent);
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ratingBar.getRating()>0)
                {
                Toast.makeText(RateUsActivity.this, "Thank You For Rating Us", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RateUsActivity.this, "Can Not Submit Without Rating", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.android.restaurant_management;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    ImageView ivLogo;
    TextView tvTitle,tvSubtitle;
    Animation fadeInAnim,bounce,blink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogo=findViewById(R.id.ivMainLogo);
        tvTitle=findViewById(R.id.tvMainTitle);
        tvSubtitle=findViewById(R.id.tvMainsubtitle);

        fadeInAnim= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fadein);
        bounce=AnimationUtils.loadAnimation(SplashActivity.this,R.anim.bounce);
        blink=AnimationUtils.loadAnimation(SplashActivity.this,R.anim.blink);

        ivLogo.setAnimation(bounce);
        tvTitle.setAnimation(fadeInAnim);
        tvSubtitle.setAnimation(blink);

        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LottieFile.class);
                startActivity(i);
            }
        },4000);

    }
}
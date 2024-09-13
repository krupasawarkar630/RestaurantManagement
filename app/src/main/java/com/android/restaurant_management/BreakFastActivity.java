package com.android.restaurant_management;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class BreakFastActivity extends AppCompatActivity {
    VideoView gifView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_fast);

        setTitle("BreakFast Page");

        gifView=findViewById(R.id.gifView);
        String videoPath = "android.resource://" + BreakFastActivity.this.getPackageName()
                + "/" + R.raw.gif;

        Uri uri = Uri.parse(videoPath);
        gifView.setVideoURI(uri);

        MediaController mediaController = new MediaController(BreakFastActivity.this);
        gifView.setMediaController(mediaController);
        mediaController.setAnchorView(gifView);
        gifView.start();

    }
}
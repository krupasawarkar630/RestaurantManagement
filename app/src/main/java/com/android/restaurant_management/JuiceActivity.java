package com.android.restaurant_management;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class JuiceActivity extends AppCompatActivity {

    ImageSlider imageSlider;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juice);
        setTitle("Juice Page");

        imageSlider=findViewById(R.id.ImageSlider);

        ArrayList<SlideModel> slideModels=new ArrayList<>();

        boolean add = slideModels.add(new SlideModel(R.drawable.juices, ScaleTypes.FIT));

        slideModels.add(new SlideModel(R.drawable.juicecombo, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels);
    }
}
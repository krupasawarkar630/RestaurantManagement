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

public class CoffeeActivity extends AppCompatActivity {

    ImageSlider imageSlider;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        setTitle("Coffee Page");

        imageSlider=findViewById(R.id.ImageSlider);

        ArrayList<SlideModel> slideModels=new ArrayList<>();

        boolean add = slideModels.add(new SlideModel(R.drawable.coffeeadd1, ScaleTypes.FIT));

        slideModels.add(new SlideModel(R.drawable.coffeeadd2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.coffeeadd3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels);
    }
}
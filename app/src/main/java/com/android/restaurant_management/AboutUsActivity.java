package com.android.restaurant_management;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class AboutUsActivity extends AppCompatActivity {

    ImageSlider imageSlider,feedbackSlider;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        imageSlider=findViewById(R.id.ImageSlider);
        feedbackSlider=findViewById(R.id.feedbackImageSlider);

        ArrayList<SlideModel> slideModels=new ArrayList<>();

        boolean add = slideModels.add(new SlideModel(R.drawable.aboutus4, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.aboutus1, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.aboutus3, ScaleTypes.CENTER_INSIDE));
        //slideModels.add(new SlideModel(R.drawable.aboutus2, ScaleTypes.CENTER_INSIDE));
        imageSlider.setImageList(slideModels,ScaleTypes.CENTER_INSIDE);
        imageSlider.setImageList(slideModels);
        imageSlider.setSlideAnimation(AnimationTypes.FIDGET_SPINNER);


        ArrayList<SlideModel> slideFeedback =new ArrayList<>();

        slideFeedback.add(new SlideModel(R.drawable.feedback1, ScaleTypes.CENTER_INSIDE));
        slideFeedback.add(new SlideModel(R.drawable.feedback2, ScaleTypes.CENTER_INSIDE));
        slideFeedback.add(new SlideModel(R.drawable.feedback3, ScaleTypes.CENTER_INSIDE));
        slideFeedback.add(new SlideModel(R.drawable.feedback4, ScaleTypes.CENTER_INSIDE));
        slideFeedback.add(new SlideModel(R.drawable.feedback5, ScaleTypes.CENTER_INSIDE));
        feedbackSlider.setImageList(slideFeedback,ScaleTypes.CENTER_INSIDE);
        feedbackSlider.setImageList(slideFeedback);

        feedbackSlider.setSlideAnimation(AnimationTypes.CUBE_IN);

    }
}
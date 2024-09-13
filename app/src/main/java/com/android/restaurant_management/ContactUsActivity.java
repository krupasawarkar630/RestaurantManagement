package com.android.restaurant_management;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class ContactUsActivity extends AppCompatActivity {
    ImageSlider imageSlider;
    ImageView contactUsLocation;
    EditText etUsername,etPhone,etEmail,etMessage;
    AppCompatButton btnBookTable;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        etUsername=findViewById(R.id.etUsername);
        etEmail=findViewById(R.id.etEmail);
        etPhone=findViewById(R.id.etPhone);
        etMessage=findViewById(R.id.etMessage);
        btnBookTable=findViewById(R.id.btnBookTable);

        imageSlider=findViewById(R.id.contactUsImageSlider);
        contactUsLocation=findViewById(R.id.contactUsLocation);

        ArrayList<SlideModel> slideModels=new ArrayList<>();

        boolean add = slideModels.add(new SlideModel(R.drawable.contactus1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.contactus2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.contactus3, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels,ScaleTypes.CENTER_INSIDE);
        imageSlider.setImageList(slideModels);

        imageSlider.setSlideAnimation(AnimationTypes.BACKGROUND_TO_FOREGROUND);


        btnBookTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etUsername.getText().toString().isEmpty())
                {
                    etUsername.setError("Enter Your Fullname");
                }
                else if(etEmail.getText().toString().isEmpty())
                {
                    etEmail.setError("Enter Valid Email");
                }
                else if(etPhone.getText().toString().isEmpty())
                {
                    etPhone.setError("Enter Valid Phone no");
                }
                else if (etUsername.getText().toString().length() < 8)
                {
                    etUsername.setError("Username must contain at least 8 characters");
                }
                else if (!etEmail.getText().toString().matches(".*[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+.[@ || .com].*")) {
                    etEmail.setError("Invalid Email-id");
                }
                else if(etPhone.getText().toString().length()!=10)
                {
                    etPhone.setError("Phone number must contain 10 numbers");
                }
                else
                {
                    Toast.makeText(ContactUsActivity.this,"Book a Table Here !",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ContactUsActivity.this, BookTableActivity.class);
                    startActivity(i);
                }
            }
        });

        contactUsLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(ContactUsActivity.this,"Location is Opened",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ContactUsActivity.this, MyLocationActivity.class);
                startActivity(i);
            }
        });
    }
}
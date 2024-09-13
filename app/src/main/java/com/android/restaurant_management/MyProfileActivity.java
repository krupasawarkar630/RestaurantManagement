package com.android.restaurant_management;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MyProfileActivity extends AppCompatActivity {
    TextView tvToken, tvFullName, tvEmail, tvPhone, tvUserName, tvPassword;
    ImageView ivProfilePhoto;
    Spinner spinnerGender;
    String[] gender = {"Gender", "Male", "Female", "Other"};
    AppCompatButton btnUpdateProfile, btnChangeProfile;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Uri filePath;
    private Bitmap bitmap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        preferences = PreferenceManager.getDefaultSharedPreferences(MyProfileActivity.this);
        editor = preferences.edit();

        ivProfilePhoto = findViewById(R.id.ivProfilePhoto);
        tvToken = findViewById(R.id.tvMyProfileToken);
        spinnerGender = findViewById(R.id.spinnerGender);

        tvFullName = findViewById(R.id.tvFullName);
        tvEmail = findViewById(R.id.tvEmailId);
        tvPhone = findViewById(R.id.tvPhone);
        tvUserName = findViewById(R.id.tvUserName);
        tvPassword = findViewById(R.id.tvPassword);

        btnChangeProfile = findViewById(R.id.btnChangeProfile);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);

        File imageFile = new File(getFilesDir(),"profile.jpg");
        if(imageFile.exists()){
            Bitmap savedBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            ivProfilePhoto.setImageBitmap(savedBitmap);
        }

        String strFullName = preferences.getString("Name", "");
        tvFullName.setText(strFullName);

        String strMobileNo = preferences.getString("Mobile No", "");
        tvPhone.setText(strMobileNo);

        String strEmailId = preferences.getString("Email Id", "");
        tvEmail.setText(strEmailId);

        String strUsername = preferences.getString("Username", "");
        tvUserName.setText(strUsername);

        String strPassword = preferences.getString("Password", "");
        tvPassword.setText(strPassword);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {

                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MyProfileActivity.this, "FCM TOken not Received", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String token = task.getResult();
                        tvToken.setText(token);
                        Toast.makeText(MyProfileActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        btnChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showImageChooser();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyProfileActivity.this
                , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(MyProfileActivity.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MyProfileActivity.this, " Update Profile Successfully ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showImageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(i.ACTION_GET_CONTENT);
        startActivityForResult(i.createChooser(i, "Select Profile Picture"), 1);
    }
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            filePath = data.getData();
            try
            {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivProfilePhoto.setImageBitmap(bitmap);

                File imageFile = new File(getFilesDir(),"profile.jpg");
                FileOutputStream outputStream = openFileOutput(imageFile.getName(), Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
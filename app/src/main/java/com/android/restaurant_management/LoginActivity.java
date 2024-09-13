package com.android.restaurant_management;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {
    androidx.cardview.widget.CardView cvLogin;
    Animation fadein;
    EditText etUsername,etPassword;
    CheckBox cbShowHidePassword;
    TextView tvForgetPassword,tvSignup;
    AppCompatButton btnSignin;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setTitle("Login Activity");

        preferences= PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        editor= preferences.edit();

        if(preferences.getBoolean("isLogin",false))
        {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
        }
        cvLogin=findViewById(R.id.cvLogin);
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        cbShowHidePassword=findViewById(R.id.cbShowHidePassword);
        tvForgetPassword=findViewById(R.id.tvForgetPassword);
        btnSignin=findViewById(R.id.btnSignin);
        tvSignup=findViewById(R.id.tvSignup);

        fadein=AnimationUtils.loadAnimation(LoginActivity.this,R.anim.fadein);
        cvLogin.setAnimation(fadein);

        cbShowHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsername.getText().toString().isEmpty()) {
                    etUsername.setError("Enter Valid Email-Id");
                }
                else if(etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Enter your password");
                }
                else if(etPassword.getText().toString().length()<8) {
                    etPassword.setError("Password must contain at least 8 characters");
                }  else if (etUsername.getText().toString().length() < 8) {
                    etUsername.setError("Username must contain at least 8 characters");
                }
                else if (!etUsername.getText().toString().matches(".*[A-Z].*")) {
                    etUsername.setError("Must contain 1 Upper case letter");
                }
                else if (!etUsername.getText().toString().matches(".*[a-z].*")) {
                    etUsername.setError("Must contain 1 Lower case letter");
                }
                else if (!etUsername.getText().toString().matches(".*[0-9].*")) {
                    etUsername.setError("Must contain 1 digit");
                }
                else if (!etUsername.getText().toString().matches(".*[@,#,$.&].*")) {
                    etUsername.setError("Must contain 1 special symbol");
                }
                else if ((!etPassword.getText().toString().matches(".*[A-Z].*")||
                        (!etPassword.getText().toString().matches(".*[a-z].*")||
                                (!etPassword.getText().toString().matches(".*[0-9].*")||
                                        (!etPassword.getText().toString().matches(".*[@,#,$.&].*"))))))
                {
                    etPassword.setError("Must contain at least 1 Upper case letter,1 lower case letter,1 digit and 1 special symbol such as @,#,$,& ");
                }
                else{
                    Intent i = new Intent(LoginActivity.this,LottieDone.class);
                    editor.putBoolean("isLogin",true).commit();
                    startActivity(i);

                    editor.putString("LoginUsername",etUsername.getText().toString()).commit();

                    Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(LoginActivity.this, SignUp.class);
                startActivity(i);
            }
        });
    }
}

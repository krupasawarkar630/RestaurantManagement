package com.android.restaurant_management;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SignUp extends AppCompatActivity {
    EditText etSignupFullName,etSignupUsername,etSignupPhone,etSignupEmail,etSignupPassword
            ,etSignupConfirmPassword;
    AppCompatButton btnRegister;
    TextView tvSignin;
    boolean passwordVisible,passwordInVisible;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        preferences = PreferenceManager.getDefaultSharedPreferences(SignUp.this);
        editor = preferences.edit();

        etSignupFullName = findViewById(R.id.etFullName);
        etSignupUsername=findViewById(R.id.etUsername);
        etSignupEmail=findViewById(R.id.etSignupemail);
        etSignupPhone=findViewById(R.id.etPhone);
        etSignupPassword=findViewById(R.id.etSignupPassword);
        etSignupConfirmPassword=findViewById(R.id.etSignupConfirmPassword);
        btnRegister=findViewById(R.id.btnRegister);
        tvSignin=findViewById(R.id.tvSignin);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSignupFullName.getText().toString().isEmpty()) {
                    etSignupFullName.setError("Enter valid Username");
                }
                else if (etSignupUsername.getText().toString().isEmpty()) {
                    etSignupUsername.setError("Enter valid Username");
                }
                else if(etSignupEmail.getText().toString().isEmpty()) {
                    etSignupEmail.setError("Enter Valid Email");
                }
                else if(etSignupPhone.getText().toString().isEmpty())
                {
                    etSignupPhone.setError("Enter Valid Phone no");
                }
                else if(etSignupPassword.getText().toString().isEmpty()) {
                    etSignupPassword.setError("Enter your password");
                }
                else if(etSignupConfirmPassword.getText().toString().isEmpty()) {
                    etSignupConfirmPassword.setError("Enter your confirm password");
                }
                else if (etSignupUsername.getText().toString().length() < 8) {
                    etSignupUsername.setError("Username must contain at least 8 characters");
                }
                else if (!etSignupUsername.getText().toString().matches(".*[A-Z].*")) {
                    etSignupUsername.setError("Must contain 1 Upper case letter");
                }
                else if (!etSignupUsername.getText().toString().matches(".*[a-z].*")) {
                    etSignupUsername.setError("Must contain 1 Lower case letter");
                }
                else if (!etSignupUsername.getText().toString().matches(".*[0-9].*")) {
                    etSignupUsername.setError("Must contain 1 digit");
                }
                else if (!etSignupUsername.getText().toString().matches(".*[@,#,$.&].*")) {
                    etSignupUsername.setError("Must contain 1 special symbol");
                }
                else if(etSignupPassword.getText().toString().length()<8) {
                    etSignupPassword.setError("Password must contain at least 8 characters");
                }
                else if (!etSignupEmail.getText().toString().matches(".*[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+.[@ || .com].*")) {
                    etSignupEmail.setError("Invalid Email-id");
                }
                else if(etSignupPhone.getText().toString().length()!=10)
                {
                    etSignupPhone.setError("Phone number must contain 10 numbers");
                } else if (!etSignupPassword.getText().toString().matches(".*[A-Z].*")) {
                    etSignupPassword.setError("Must contain 1 Upper case letter");
                }
                else if (!etSignupPassword.getText().toString().matches(".*[a-z].*")) {
                    etSignupPassword.setError("Must contain 1 Lower case letter");
                }
                else if (!etSignupPassword.getText().toString().matches(".*[0-9].*")) {
                    etSignupPassword.setError("Must contain 1 digit");
                }
                else if (!etSignupPassword.getText().toString().matches(".*[@,#,$.&].*")) {
                    etSignupPassword.setError("Must contain 1 special symbol");
                }
                else if(!etSignupPassword.getText().toString().matches(etSignupConfirmPassword.getText().toString()))
                {
                    etSignupConfirmPassword.setError("Password Not Match both field");
                }
                else
                {
                    Toast.makeText(SignUp.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUp.this, LottieRegistration.class);
                    startActivity(i);

                    editor.putString("Name",etSignupFullName.getText().toString()).commit();
                    editor.putString("Mobile No",etSignupPhone.getText().toString()).commit();
                    editor.putString("Email Id",etSignupEmail.getText().toString()).commit();
                    editor.putString("Username",etSignupUsername.getText().toString()).commit();
                    editor.putString("Password",etSignupPassword.getText().toString()).commit();
                }
            }
        });
        etSignupPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>=etSignupPassword.getRight()-etSignupPassword.getCompoundDrawables()[Right].getBounds().width())
                    {
                       int selection=etSignupPassword.getSelectionEnd();
                        if(passwordVisible){
                            etSignupPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.eyeinvisible,0);
                            etSignupPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            etSignupPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.eyevisible,0);
                            etSignupPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        etSignupPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        etSignupConfirmPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>=etSignupConfirmPassword.getRight()-etSignupConfirmPassword.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int Selection=etSignupConfirmPassword.getSelectionEnd();
                        if(passwordInVisible){
                            etSignupConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.eyeinvisible,0);
                            etSignupConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordInVisible=false;
                        }else{
                            etSignupConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.eyevisible,0);
                            etSignupConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordInVisible=true;
                        }
                        etSignupConfirmPassword.setSelection(Selection);
                        return true;
                    }
                }
                return false;
            }
        });

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(SignUp.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
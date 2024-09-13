package com.android.restaurant_management;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
public class HomeActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {
    boolean doubleTap=false;
    BottomNavigationView bottomNavigationView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home Page");

        preferences= PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        editor= preferences.edit();
        boolean firstTime=preferences.getBoolean("isFirstTime",true);
        if(firstTime)
        {
            welcome();
        }
        bottomNavigationView=findViewById(R.id.homeBottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menuBottomNavHome);
    }private void welcome() {
        AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("CHHAPAN BHOG");
        ad.setMessage("Welcome to Chappan Bhog");
        ad.setPositiveButton("Thank you", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        }).create().show();
    editor.putBoolean("isFirstTime",false).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_home_QRCode)
        {
            Intent i = new Intent(HomeActivity.this, QRCodeActivity.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.menu_home_Location)
        {
            Intent i = new Intent(HomeActivity.this, MyLocationActivity.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.menu_home_Favorite) {
            Intent i = new Intent(HomeActivity.this, MyFavoriteActivity.class);
            startActivity(i);
            Toast.makeText(HomeActivity.this,"My Favorite List is opened",Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_home_MyProfile) {
            Intent i = new Intent(HomeActivity.this, MyProfileActivity.class);
            startActivity(i);
            Toast.makeText(HomeActivity.this,"My Profile is opened",Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_home_Settings) {
            Intent i = new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(i);
            Toast.makeText(HomeActivity.this,"Setting is opened",Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_home_ContactUs) {
            Intent i = new Intent(HomeActivity.this, ContactUsActivity.class);
            startActivity(i);
            Toast.makeText(HomeActivity.this,"Contact Us is opened",Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_home_AboutUs) {
            Intent i = new Intent(HomeActivity.this, AboutUsActivity.class);
            startActivity(i);
            Toast.makeText(HomeActivity.this,"About Us is opened",Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_home_Logout) {
            logout();
        }
        return true;
    }
    private void logout(){
        AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("CHHAPAN BHOG");
        ad.setMessage("Are you sure you want to LogOut");
        ad.setPositiveButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
             dialog.cancel();
            }
        });
        ad.setNegativeButton("Log Out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                editor.putBoolean("isLogin",false).commit();
                startActivity(i);
            }
        }).create().show();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if(doubleTap)
        {
            finishAffinity();
        }else {
            Toast.makeText(HomeActivity.this,"Press Again to Exit App",Toast.LENGTH_SHORT).show();
        doubleTap=true;
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                 doubleTap=false;
                }
            },2000);
        }
    }
    HomeFragment homeFragment=new HomeFragment();
    CategoryFragment categoryFragment=new CategoryFragment();
    MenuFragment menuFragment=new MenuFragment();
    CartFragment cartFragment=new CartFragment();
    AccountFragment accountFragment=new AccountFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuBottomNavHome) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,homeFragment).commit();
        }
        else if (item.getItemId()==R.id.menuBottomNavAccount) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,accountFragment).commit();
        }
        else if (item.getItemId()==R.id.menuBottomNavCategory) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,categoryFragment).commit();
        }
        else if (item.getItemId()==R.id.menuBottomNavMenuCard) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,menuFragment).commit();
        }
        else if (item.getItemId()==R.id.menuBottomNavCart) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,cartFragment).commit();
        }
        return true;
    }
}
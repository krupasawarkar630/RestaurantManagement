package com.android.restaurant_management;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.Animation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.android.restaurant_management.databinding.ActivityMyLocationBinding;
import com.google.android.gms.maps.model.PolylineOptions;

public class MyLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMyLocationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng office = new LatLng(20.899230971437472, 77.76417091811707);
        mMap.addMarker(new MarkerOptions().position(office).title("Marker at Mountreach")
                .icon(setIcon(MyLocationActivity.this,R.drawable.icon_office)));
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(SweetBakery));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(office,16),4000,null);
        mMap.addCircle(new CircleOptions().strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(70,150,50,50)).radius(200).center(office));

        LatLng college = new LatLng(20.94306206114469, 77.76656748732078);
        mMap.addMarker(new MarkerOptions().position(college).title("CHHAPAN BHOG")
                .icon(setIcon(MyLocationActivity.this,R.drawable.icon_restaurant)));
        mMap.addCircle(new CircleOptions().strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(70,150,50,50)).radius(200).center(college));
        mMap.addPolyline(new PolylineOptions().add(college,office).color(Color.RED).width(5));
    }

    public BitmapDescriptor setIcon(Context context, int drawableId)
    {
        Drawable drawable= ContextCompat.getDrawable(context,drawableId);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());

        Bitmap bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);

        Canvas canvas=new Canvas(bitmap);
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
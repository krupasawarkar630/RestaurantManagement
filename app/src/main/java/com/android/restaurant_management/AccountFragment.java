package com.android.restaurant_management;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AccountFragment extends Fragment {
    CardView cvMyProfile,cvFavorites,cvMoney;
    TextView tvBankAndUpi,tvPayment,tvFavoriteItems,tvSharedItems
            ,tvOrderList,tvCategory,tvSettings,tvRateUs,tvShareApp,tvQRScanner;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        cvFavorites = view.findViewById(R.id.cvFavorites);
        cvMoney = view.findViewById(R.id.cvMoney);
        cvMyProfile=view.findViewById(R.id.cvMyProfile);

        tvBankAndUpi = view.findViewById(R.id.tvBankAndUpi);
        tvPayment = view.findViewById(R.id.tvPayments);
        tvFavoriteItems = view.findViewById(R.id.tvFavoriteItems);
        tvSharedItems = view.findViewById(R.id.tvSharedItems);
        tvOrderList = view.findViewById(R.id.tvOrderList);
        tvCategory = view.findViewById(R.id.tvCategory);
        tvSettings = view.findViewById(R.id.tvSettings);
        tvRateUs = view.findViewById(R.id.tvRateUs);
        tvShareApp = view.findViewById(R.id.tvShareApp);
        tvQRScanner = view.findViewById(R.id.tvQRScanner);


        final String appPackageName = getActivity().getPackageName();

        cvMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),MyProfileActivity.class);
                startActivity(i);
            }
        });

        cvFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),MyFavoriteActivity.class);
                startActivity(i);
            }
        });

        tvFavoriteItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),MyFavoriteActivity.class);
                startActivity(i);
            }
        });

        tvRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),RateUsActivity.class);
                startActivity(i);
            }
        });


        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),SettingActivity.class);
                startActivity(i);
            }
        });

        tvShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(i.ACTION_SEND);
                i.putExtra(i.EXTRA_TEXT,"Check this App \n" + " https://play.google.com/store/apps?hl=en-IN" + appPackageName);
                i.setType("text/plain");
                startActivity(i.createChooser(i,"share this apps"));
            }
        });
        tvQRScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), QRCodeActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
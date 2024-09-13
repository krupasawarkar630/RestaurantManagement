package com.android.restaurant_management;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRCodeActivity extends AppCompatActivity {
    CardView cvShareQR;
    ImageView ivQRCode;
    private static final int QRWidth=300,QRHeight=300;
    SharedPreferences preferences;
    String strUsername;
    Bitmap bitmap;
    String resultData;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        cvShareQR = findViewById(R.id.cvShareQR);

        preferences = PreferenceManager.getDefaultSharedPreferences(QRCodeActivity.this);
        strUsername = preferences.getString("LoginUsername","");

        resultData = getIntent().getStringExtra("result");

        cvShareQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,resultData + "\n Scan and Pay \n " +
                        "https://play.google.com/store/apps/details?id=com.phonepe.app&hl=en_IN"
                        + getApplicationContext().getPackageName());
                startActivity(Intent.createChooser(i,"Send Using"));

            }
        });

        ivQRCode=findViewById(R.id.ivQRCode);
        try{
            createQRCode();
        }
        catch (WriterException e)
        {
            Toast.makeText(QRCodeActivity.this, ""+e.getMessage()
                    , Toast.LENGTH_SHORT).show();
        }
    }

    private void createQRCode()throws WriterException {
        bitmap = textToImageEncode(strUsername);
            ivQRCode.setImageBitmap(bitmap);
        }

    private Bitmap textToImageEncode(String strUsername) throws WriterException {
        BitMatrix bitMatrix;
        bitMatrix = new MultiFormatWriter().encode(strUsername, BarcodeFormat.QR_CODE,QRWidth,QRHeight);

        int[] pixels = new int[bitMatrix.getWidth()*bitMatrix.getHeight()];

        for (int x = 0; x<bitMatrix.getWidth(); x++)
        {
            int offset = x*bitMatrix.getHeight();

            for(int y= 0 ; y<bitMatrix.getHeight(); y++)
            {
                pixels[offset+y]=bitMatrix.get(x,y)?getResources().getColor(R.color.black):
                        getResources().getColor(R.color.white);
            }
        }

        bitmap = Bitmap.createBitmap(bitMatrix.getWidth(),bitMatrix.getHeight(),Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels,0,bitMatrix.getWidth(),0,0,bitMatrix.getWidth()
                ,bitMatrix.getHeight());
        return bitmap;
    }
}

package com.sachin.batteryandcamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtbattery;
    Button btnbattery, btncamera;
    int CAMERA_CODE = 1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtbattery = findViewById(R.id.text_info);
        btnbattery = findViewById(R.id.btn_info);
        btncamera = findViewById(R.id.btn_cam);
        imageView = findViewById(R.id.image_view);

        btnbattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BatteryManager batteryManager = (BatteryManager) getApplicationContext().getSystemService(BATTERY_SERVICE);
                int batterypercent = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
                txtbattery.setText("current battery percentage is "+batterypercent);
            }
        });

        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent,CAMERA_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CODE){
            Bitmap picture = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(picture);
        }
    }
}
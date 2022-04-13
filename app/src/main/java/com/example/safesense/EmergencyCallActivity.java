package com.example.safesense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmergencyCallActivity extends AppCompatActivity {

    Button cancelCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);
        cancelCall = (Button) findViewById(R.id.cancelCall);
        cancelCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(EmergencyCallActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }

    public void callPolice(View view) {
        String policeNumber = "110";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + policeNumber));
        if(ActivityCompat.checkSelfPermission(EmergencyCallActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
           return;
        }
        startActivity(intent);
    }

    public void callAmbulance(View view) {
        String ambulanceNumber = "119";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + ambulanceNumber));
        if(ActivityCompat.checkSelfPermission(EmergencyCallActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(intent);
    }

    public void callNDP(View view) {
        String ndpNumber = "129";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + ndpNumber));
        if(ActivityCompat.checkSelfPermission(EmergencyCallActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(intent);
    }

    public void callSAR(View view) {
        String sarNumber = "115";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + sarNumber));
        if(ActivityCompat.checkSelfPermission(EmergencyCallActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(intent);
    }

    public void callUEN(View view) {
        String uenNumber = "112";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + uenNumber));
        if(ActivityCompat.checkSelfPermission(EmergencyCallActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(intent);
    }



}


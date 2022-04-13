package com.example.safesense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.account.service.AccountAuthService;

public class HomeActivity extends AppCompatActivity {
    Button emergencyCallButton, personalHealthButton, logoutButton , myProfileButton, exitAppButton;
    // AccountAuthService provides a set of APIs, including silentSignIn, getSignInIntent, and signOut.
    private AccountAuthService mAuthService;
    private int ALLOW_PHONE_CALL = 1;
    private static final String TAG = "Account";
    private TextView logTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        emergencyCallButton = (Button) findViewById(R.id.emergencyCallButton);
        emergencyCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(HomeActivity.this, "You have granted phone call permission!", Toast.LENGTH_SHORT).show();
                    Intent i = null;
                    i = new Intent(HomeActivity.this, EmergencyCallActivity.class);
                    startActivity(i);
                } else {
                    requestPhoneCall();
                }
            }
        });

        personalHealthButton = (Button) findViewById(R.id.personalHealthButton);
        personalHealthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(HomeActivity.this, PersonalHealthActivity.class);
                startActivity(i);
            }
        });

        myProfileButton = (Button) findViewById(R.id.myProfileButton);
        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(HomeActivity.this, MyMedicalProfile.class);
                startActivity(i);
            }
        });

        exitAppButton = (Button) findViewById(R.id.exitAppButton);
        exitAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(HomeActivity.this, SplashScreen.class);
                startActivity(i);
                finish();
            }
        });


    }

    private void requestPhoneCall() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, Manifest.permission.CALL_PHONE)){

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to call emergency numbers")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                     @Override
                     public void onClick(DialogInterface dialog, int which){
                         ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.CALL_PHONE},ALLOW_PHONE_CALL );
                     }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
          ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.CALL_PHONE},ALLOW_PHONE_CALL );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ALLOW_PHONE_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(HomeActivity.this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(HomeActivity.this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
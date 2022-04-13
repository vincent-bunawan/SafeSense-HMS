package com.example.safesense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyMedicalProfile extends AppCompatActivity {

    TextView userName, userBirthday, userGender, userBloodType, userHomeAddress, userAllergies, userMedicalNotes;
    Button clearProfileButton, homeButton;
    SharedPreferences getData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medical_profile);

        userName = findViewById(R.id.userName);
        userBirthday = findViewById(R.id.userBirthday);
        userGender = findViewById(R.id.userGender);
        userBloodType = findViewById(R.id.userBloodType);
        userHomeAddress = findViewById(R.id.userHomeAddress);
        userAllergies = findViewById(R.id.userAllergies);
        userMedicalNotes = findViewById(R.id.userMedicalNotes);

        getData = getSharedPreferences("data", MODE_PRIVATE);

        String name = (getData.getString("name", userName.getText().toString()));
        String birthday = (getData.getString("birthdate", userBirthday.getText().toString()));
        String address = (getData.getString("address", userHomeAddress.getText().toString()));
        String allergies = (getData.getString("allergies", userAllergies.getText().toString()));
        String medicalNotes = (getData.getString("medicalNotes", userMedicalNotes.getText().toString()));
        String bloodTypeValue = (getData.getString("bloodTypeValue", userBloodType.getText().toString()));
        String genderSelect = (getData.getString("genderSelect", userGender.getText().toString()));

        userName.setText("Name : \n" + name);
        userBirthday.setText("Birth Date : \n" + birthday);
        userGender.setText("Gender : \n" +genderSelect);
        userBloodType.setText("Blood Type : \n" + bloodTypeValue);
        userHomeAddress.setText("Home Address : \n" + address);
        userAllergies.setText("Allergies : \n" + allergies);
        userMedicalNotes.setText("Medical Notes : \n" + medicalNotes);



        clearProfileButton = (Button) findViewById(R.id.clearProfileButton);
        clearProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData = getSharedPreferences("data", MODE_PRIVATE);
                getData.edit().clear().apply();

                Toast.makeText(MyMedicalProfile.this, "Your Medical Profile has been reset", Toast.LENGTH_SHORT).show();
                Intent i = null;
                i = new Intent(MyMedicalProfile.this, HomeActivity.class);
                startActivity(i);
            }
            });


        homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                i = new Intent(MyMedicalProfile.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }


}
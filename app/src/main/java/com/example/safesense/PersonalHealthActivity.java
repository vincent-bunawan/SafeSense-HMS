package com.example.safesense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class PersonalHealthActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name, address, allergies, medicalNotes;
    RadioButton genderSelect;
    RadioGroup genderGroup;
    Spinner bloodType;
    TextView bloodTypeValue;
    SharedPreferences setData;
    Button birthdate;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_health);
        initDatePicker();

        name = findViewById(R.id.enterName);
        birthdate = findViewById(R.id.enterBirthdate);
        birthdate.setText(getTodaysDate());
        address = findViewById(R.id.enterAddress);
        allergies = findViewById(R.id.enterAllergies);
        medicalNotes = findViewById(R.id.enterMedicalNotes);

        genderGroup = findViewById(R.id.genderGroup);

        bloodType = findViewById(R.id.bloodTypeSpinner);
        bloodTypeValue = findViewById(R.id.viewBloodType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_type, android.R.layout.simple_spinner_dropdown_item);

        bloodType.setAdapter(adapter);
        bloodType.setOnItemSelectedListener(this);

        int radioId = genderGroup.getCheckedRadioButtonId();
        genderSelect = findViewById(radioId);


    }
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String myBloodType = adapterView.getItemAtPosition(i).toString();
        bloodTypeValue.setText(String.valueOf(myBloodType));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void saveMyProfile(View view) {
        setData = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor profileEditor = setData.edit();
        profileEditor.putString("name", name.getText().toString());
        profileEditor.putString("birthdate", birthdate.getText().toString());
        profileEditor.putString("address", address.getText().toString());
        profileEditor.putString("allergies", allergies.getText().toString());
        profileEditor.putString("medicalNotes", medicalNotes.getText().toString());
        profileEditor.putString("bloodTypeValue", bloodTypeValue.getText().toString());
        profileEditor.putString("genderSelect", genderSelect.getText().toString());
        profileEditor.apply();

        Toast.makeText(PersonalHealthActivity.this, "Your Medical Profile is Saved!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PersonalHealthActivity.this, MyMedicalProfile.class);
        startActivity(intent);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                birthdate.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get (Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(PersonalHealthActivity.this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month){
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";
        return "JAN";
    }


    public void openDatePicker (View view){
        datePickerDialog.show();
    }
}
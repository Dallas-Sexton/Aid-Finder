package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;

import java.io.FileOutputStream;


public class loginActivity extends AppCompatActivity {

    TextView username, name, email, phone_number, DOB, header;
    Bundle bundle;
    Intent intent;
    Button register, skip;
    EditText editTextdob;
    ImageView datepicker;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username = findViewById(R.id.login_username);
        editTextdob = findViewById(R.id.login_DOB);
        datepicker = findViewById(R.id.datpicker);
        datepicker.setOnClickListener(v -> showDatePickerDialog());
        header = findViewById(R.id.registrationHeader);

        header.setText("Login with Aid finder");
        skip = findViewById(R.id.login_skip_button);

        intent = new Intent(this, HomeActivity.class);


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);

            }
        });
    }

    //  date picker dialog
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    editTextdob.setText(selectedDate);
                },
                2000, // default year
                0,    // default month (January)
                1     // default day of the month
        );
        datePickerDialog.show();


    }


    //save user info to a file
    public void save_data(View v) {
        String u = username.getText().toString();
        String n = name.getText().toString();
        String e = email.getText().toString();
        String p = phone_number.getText().toString();
        String d = DOB.getText().toString();

        String filename = "users_data.txt";
        String fileContents = "\n" + u + "\n" + e + "\n" + p + "\n" + d;
        FileOutputStream outputStream; //allow a file to be opened for writing
        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception err) {
            err.printStackTrace();
        }


    }
}
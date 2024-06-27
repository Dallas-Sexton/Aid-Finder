package com.example.myapplication.ui.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.ui.dashboard.DashboardFragment;

import java.io.FileOutputStream;
import java.util.Calendar;

public class loginFragment extends Fragment {
    TextView username, name, email, phone_number, DOB, header;
    Bundle bundle;
    Intent intent;
    Button register, skip;
    EditText editTextdob;
    ImageView datepicker;
    public loginFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.signup, container, false);


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views and set click listener here
        Button submit = view.findViewById(R.id. login_submit_button);
        submit.setOnClickListener(this::submit);
        Button skip = view.findViewById(R.id. login_skip_button);
        skip.setOnClickListener(this::skip);

        username = getView().findViewById(R.id.login_username);
        name=getView().findViewById(R.id.login_name);
        email=getView().findViewById(R.id.login_email);
        phone_number=getView().findViewById(R.id.login_phoneNumber);
        editTextdob = getView().findViewById(R.id.login_DOB);
        datepicker = getView().findViewById(R.id.datpicker);
        editTextdob.setOnClickListener(this::showDatePickerDialog);

    }

//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null){
//               getFragmentManager().beginTransaction().add(android.R.id.content, new DashboardFragment()).commit();
//        }
//
//    }

    public void submit(View view){
        save_data();
        Toast.makeText(getContext(),"User details saved",Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager =  requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BlankFragment newFragment = new BlankFragment();
        fragmentTransaction.replace(R.id.f, newFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void skip(View view){
        FragmentManager fragmentManager =  requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BlankFragment newFragment = new BlankFragment();
        fragmentTransaction.replace(R.id.f, newFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void save_data() {
        String u = username.getText().toString();
        String n = name.getText().toString();
        String e = email.getText().toString();
        String p = phone_number.getText().toString();
        String d = editTextdob.getText().toString();

        String filename = "users_data.txt";
        String fileContents = "\n" + u + "\n" + e + "\n" + p + "\n" + d;

        FileOutputStream outputStream;
        try {
            // Ensure that the context is obtained from the hosting activity
            Context context = requireActivity();

            outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception err) {
            err.printStackTrace();
        }}

    public void showDatePickerDialog(View view) {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (dialog, year, month, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    editTextdob.setText(selectedDate);
                },
                currentYear,   // default year set to the current year
                currentMonth,  // default month set to the current month
                currentDay      // default day of the month set to the current day
        );
        datePickerDialog.show();
    }







}

package com.example.form;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.DTO.UserDTO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText txtDate;
    private EditText txtName;
    private EditText txtLastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    private void initComponent() {
        txtDate = findViewById(R.id.txtDate);
        txtName = findViewById(R.id.txtName);
        txtLastName = findViewById(R.id.txtLastName);

    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                txtDate.setText(day + "/" + (month+1) + "/" + year);

            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static int calculateAge(String fecha) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date birthdate = formatter.parse(fecha);
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthdate);
            Calendar today = Calendar.getInstance();

            int yearDifference = today.get(Calendar.YEAR)
                    - birth.get(Calendar.YEAR);

            if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
                yearDifference--;
            } else {
                if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
                        && today.get(Calendar.DAY_OF_MONTH) < birth
                        .get(Calendar.DAY_OF_MONTH)) {
                    yearDifference--;
                }

            }

            return yearDifference;
        }catch (Exception e){
            Log.i("error------------>", String.valueOf(e));
            return -1;
        }
    }


    public void goToSecondActivity(View view) {

        String name = txtName.getText().toString();
        if ("".equals(name)) {
            txtName.setError(getString(R.string.requerido));
        }
        String lastName = txtLastName.getText().toString();
        if ("".equals(lastName)) {
            txtLastName.setError(getString(R.string.requerido));
        }
        String date = txtDate.getText().toString();
        if ("".equals(date)) {
            txtDate.setError(getString(R.string.requerido));
        }

        int age = calculateAge(date);
        Log.i("->", String.valueOf(age));


        if (!"".equals(name) && !"".equals(lastName) && !"".equals(date)) {

            if (age >= 18) {
                Toast.makeText(this, "EDAD:".concat(String.valueOf(age) + " Años"), Toast.LENGTH_LONG).show();
                UserDTO user = new UserDTO();
                user.setName(name);
                user.setLastName(lastName);
                user.setDate(date);
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            } else if (age <= 0) {
                Toast.makeText(this, "Debe elegir una fecha correcta", Toast.LENGTH_LONG).show();

            } else if (age > 0 && age < 18) {
                Toast.makeText(this, "ES MENOR DE EDAD:".concat(String.valueOf(age) + " Años"), Toast.LENGTH_LONG).show();
            }
        }
    }
}





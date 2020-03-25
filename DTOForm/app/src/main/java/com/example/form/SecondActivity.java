package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.DTO.UserDTO;

public class SecondActivity extends AppCompatActivity {

    private TextView txtViewName;
    private TextView txtViewLastName;
    private TextView txtViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtViewName = findViewById(R.id.txtViewName);
        txtViewLastName= findViewById(R.id.txtViewLastName);
        txtViewDate = findViewById(R.id.txtViewDate);
        UserDTO user = (UserDTO) getIntent().getSerializableExtra("USER");
        txtViewName.setText(user.getName());
        txtViewLastName.setText(user.getLastName());
        txtViewDate.setText(user.getDate());





    }
}

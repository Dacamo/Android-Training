package com.example.eventoonclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private Button btnAceptar1;
    private Button btnAceptar2;
    //no es necesario ni declararlo ni instanciarlo
    private Button btnAceptar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        onClickBtnLegacy();
        onClickBtnByLambda();
    }

    private void onClickBtnByLambda() {
        btnAceptar2.setOnClickListener(v->{
            showToast();
        });
    }

    private void onClickBtnLegacy() {
        btnAceptar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });
    }

    private void initComponent(){
        txtNombre= findViewById(R.id.txtNombre);
        btnAceptar1= findViewById(R.id.btnAceptar1);
        btnAceptar2= findViewById(R.id.btnAceptar2);

    }

    public void onClickFromLayout(View view) {
        showToast();
    }

    private void showToast() {
        String nombre = txtNombre.getText().toString();
        Toast.makeText(getApplicationContext(), nombre, Toast.LENGTH_LONG).show();
        txtNombre.setText("");
    }
}

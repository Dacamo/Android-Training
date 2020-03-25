package com.example.listview;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MutableLiveData<String> mutableLiveDataCiudades;
    String[] ciudades = new String[5000];
    private ListView listViewCiudades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intitComponents();
        loadInfo();
        onItemClickListener();
    }

    private void onItemClickListener() {
        listViewCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), getNombreCiudad(position),Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getNombreCiudad(int position) {
        return ciudades[position];
    }


    private void loadInfo() {
        for (int i = 0; i< ciudades.length; i++){
            ciudades[i]= "Registro".concat((" "+ i+1));
        }
        ArrayAdapter <String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, ciudades);
        listViewCiudades.setAdapter(arrayAdapter);
    }

    private void intitComponents() {
        listViewCiudades = findViewById(R.id.listViewCiudades);
    }
}

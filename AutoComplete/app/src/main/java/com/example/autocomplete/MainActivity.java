package com.example.autocomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {



    private AutoCompleteTextView txtAutoComplete;
    private Spinner spinnerDiasSemana;

    private String[] lenguages = {"C", "C#", "Java","Python","PHP","Swift"};

    private String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes","Sábado","Domingo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        loadInfoAutomComplete();
        loadInfoSpinner();
        seletedAutocompleteListener();
        selectedSpinnerItem();

    }

    private void selectedSpinnerItem() {
        spinnerDiasSemana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),getDia(i),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private String getDia(int i) {
        return dias[i];
    }

    private void loadInfoSpinner() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,dias);
        spinnerDiasSemana.setAdapter(arrayAdapter);
    }

    private void seletedAutocompleteListener() {
        txtAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),getLenguage(i),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getLenguage(int i) {
        return lenguages[i];
    }

    private void loadInfoAutomComplete() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,lenguages);
        txtAutoComplete.setThreshold(1);
        txtAutoComplete.setAdapter(arrayAdapter);
    }


    private void initComponent() {
        txtAutoComplete = findViewById(R.id.txtAutoComplete);
        spinnerDiasSemana = findViewById(R.id.spinnerDiasSemana);
    }
}

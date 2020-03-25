package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView txtview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtview = findViewById(R.id.txtName);
        String valor = getIntent().getStringExtra("VALOR");
        txtview.setText(valor);
        Toast.makeText(this, "onCreate ".concat(SecondActivity.class.getName()), Toast.LENGTH_LONG).show();
        Log.d("->","onCreate()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart ".concat(SecondActivity.class.getName()), Toast.LENGTH_LONG).show();
        Log.d("->","onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume ".concat(SecondActivity.class.getName()), Toast.LENGTH_LONG).show();
        Log.d("->","onResume()");
    }

    @Override
    public void onPause() {

        super.onPause();
        Toast.makeText(this, "onPause ".concat(SecondActivity.class.getName()), Toast.LENGTH_LONG).show();
        Log.d("->","onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop ".concat(SecondActivity.class.getName()), Toast.LENGTH_LONG).show();
        Log.d("->","onStop()");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart ".concat(SecondActivity.class.getName()), Toast.LENGTH_LONG).show();
        Log.d("->","onRestart()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy ".concat(SecondActivity.class.getName()), Toast.LENGTH_LONG).show();
        Log.d("->","onDestroy()");
    }

}

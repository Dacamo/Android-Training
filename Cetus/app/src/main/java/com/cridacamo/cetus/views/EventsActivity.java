package com.cridacamo.cetus.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cridacamo.cetus.R;

public class EventsActivity extends AppCompatActivity {

    MutableLiveData<String> mutableLiveDataEvents;
    String[] events = new String[30];
    private ListView listViewEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        intitComponents();
        loadInfo();
        onItemClickListener();
    }

    private void onItemClickListener() {
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), getEventName(position),Toast.LENGTH_LONG).show();
            }
        });
    }
    private String getEventName(int position) {
        return events[position];
    }

    private void loadInfo() {
        for (int i = 0; i< events.length; i++){
            events[i]= "Evento".concat((" "+ i+1));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, events);
        listViewEvents.setAdapter(arrayAdapter);
    }

    private void intitComponents() {
        listViewEvents = findViewById(R.id.listViewEvents);
    }

    public void goToRegisterEventActivity(View view) {
        Intent intent = new Intent(this, RegisterEventActivity.class);
        startActivity(intent);
    }
}

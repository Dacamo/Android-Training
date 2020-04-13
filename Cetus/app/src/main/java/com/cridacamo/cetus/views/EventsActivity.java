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
import com.cridacamo.cetus.utilities.ActionBarUtil;

public class EventsActivity extends AppCompatActivity {

    MutableLiveData<String> mutableLiveDataEvents;
    String[] events = new String[30];
    private ListView listViewEvents;
    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        initComponents();
        loadInfo();
        onItemClickListener();
    }

    private void onItemClickListener() {
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), AssistantsActivity.class);
                intent.putExtra("name", getEventName(position));
                startActivity(intent);
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

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.EventsActivity));
        listViewEvents = findViewById(R.id.listViewAssistants);
    }

    public void goToRegisterEventActivity(View view) {
        Intent intent = new Intent(this, RegisterEventActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

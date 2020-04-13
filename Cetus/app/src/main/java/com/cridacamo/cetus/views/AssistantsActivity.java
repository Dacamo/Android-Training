package com.cridacamo.cetus.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cridacamo.cetus.R;
import com.cridacamo.cetus.utilities.ActionBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssistantsActivity extends AppCompatActivity {

    MutableLiveData<String> mutableLiveDataEvents;
    String[] assistants = new String[5];
    private ListView listViewAssistants;
    private ActionBarUtil actionBarUtil;
    @BindView(R.id.txtEventName)
    public TextView txtEventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistants);
        ButterKnife.bind(this);
        initComponents();
        loadInfo();
        onItemClickListener();
    }

    private void onItemClickListener() {
        listViewAssistants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.AssistantsActivity));
        listViewAssistants = findViewById(R.id.listViewAssistants);
        String eventName = getIntent().getStringExtra("name");
        txtEventName.setText(eventName);

    }

    private void loadInfo() {
        for (int i = 0; i< assistants.length; i++){
            assistants[i]= "Asistente".concat((" "+ i+1));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, assistants);
        listViewAssistants.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goToRegisterAssistantActivity(View view) {
        Intent intent = new Intent(this, RegisterAssistantActivity.class);
        startActivity(intent);
    }
}

package com.cridacamo.cetus.views;

import androidx.annotation.NonNull;
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
import com.cridacamo.cetus.models.Assistant;
import com.cridacamo.cetus.models.Event;
import com.cridacamo.cetus.utilities.ActionBarUtil;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssistantsActivity extends AppCompatActivity {

    private List<Assistant> assistantList = new ArrayList<>();
    ArrayAdapter<Assistant> assistantArrayAdapter;


    private ListView listViewAssistants;
    private ActionBarUtil actionBarUtil;
    @BindView(R.id.txtEventName)
    public TextView txtEventName;
    private  String eventUid;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistants);
        ButterKnife.bind(this);
        initComponents();
        onItemClickListener();
        initFirebase();
        loadData();
    }

    private void loadData() {
        databaseReference.child("assistants").orderByChild("eventUid").equalTo(eventUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                assistantList.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Assistant assistant = objSnapshot.getValue(Assistant.class);
                    assistantList.add(assistant);

                    assistantArrayAdapter = new ArrayAdapter<Assistant>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, assistantList);
                    listViewAssistants.setAdapter(assistantArrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

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
        eventUid = getIntent().getStringExtra("EVENT_UID");
        txtEventName.setText(eventUid);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goToRegisterAssistantActivity(View view) {
        Intent intent = new Intent(this, RegisterAssistantActivity.class);
        intent.putExtra("EVENT_UID", eventUid);
        startActivity(intent);
    }
}

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
import android.widget.Toast;

import com.cridacamo.cetus.R;
import com.cridacamo.cetus.models.Event;
import com.cridacamo.cetus.utilities.ActionBarUtil;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    private List<Event> eventsList = new ArrayList<Event>();
    ArrayAdapter<Event> eventArrayAdapter;

    private ListView listViewEvents;
    private ActionBarUtil actionBarUtil;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        initComponents();
        onItemClickListener();
        initFirebase();
        loadData();
    }

    private void loadData() {
        FirebaseUser usuario = mAuth.getCurrentUser();
        databaseReference.child("events").orderByChild("userUid").equalTo(usuario.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventsList.clear();
                for (DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Event event = objSnapshot.getValue(Event.class);
                    eventsList.add(event);

                    eventArrayAdapter = new ArrayAdapter<Event>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, eventsList);
                    listViewEvents.setAdapter(eventArrayAdapter);
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
        mAuth = FirebaseAuth.getInstance();

    }

    private void onItemClickListener() {
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), AssistantsActivity.class);
                intent.putExtra("EVENT_UID", getEventUid(position));
                startActivity(intent);
            }
        });
    }
    private String getEventUid(int position) {
        Event e = eventsList.get(position);
        return e.getUid();
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

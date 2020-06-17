package com.cridacamo.cetus.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.cridacamo.cetus.R;
import com.cridacamo.cetus.models.Event;
import com.cridacamo.cetus.utilities.ActionBarUtil;
import com.cridacamo.cetus.utilities.DatePickerFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterEventActivity extends AppCompatActivity {



    @BindView(R.id.register_event_txt_date)
    public EditText txtDate;
    @BindView(R.id.register_event_txt_name)
    public EditText txtName;
    @BindView(R.id.register_event_txt_location)
    public EditText txtLocation;

    private ActionBarUtil actionBarUtil;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);
        ButterKnife.bind(this);
        initComponents();
        initFirebase();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.RegisterEvent));
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                txtDate.setText(day + "/" + (month+1) + "/" + year);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goToEvenstListActivity(View view) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String date = txtDate.getText().toString();
        String location = txtLocation.getText().toString();
        String name = txtName.getText().toString();

        if(validateModel(date, location, name, currentUser.getUid())){
            Event event = new Event();
            event.setDate(date);
            event.setLocation(location);
            event.setUid(UUID.randomUUID().toString());
            event.setName(name);
            event.setUserUid(currentUser.getUid());

            databaseReference.child("events").child(event.getUid()).setValue(event);
            RemoveAllFields();
            Toast.makeText(this, getString(R.string.successfully), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
        }

    }

    private void RemoveAllFields() {
        txtName.setText("");
        txtLocation.setText("");
        txtDate.setText("");
    }

    private boolean validateModel(String date, String location, String name, String Uid) {
        boolean isValid = true;
        if("".equals(date)){
            txtDate.setError(getString(R.string.requerido));
            isValid = false;
        }
        if("".equals(location)){
            txtLocation.setError(getString(R.string.requerido));
            isValid = false;
        }
        if("".equals(name)){
            txtName.setError(getString(R.string.requerido));
            isValid = false;
        }

        if("".equals(Uid)){
            isValid = false;
        }

        return isValid;
    }
}

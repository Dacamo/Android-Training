package com.cridacamo.cetus.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cridacamo.cetus.R;
import com.cridacamo.cetus.models.Assistant;
import com.cridacamo.cetus.models.Event;
import com.cridacamo.cetus.utilities.ActionBarUtil;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterAssistantActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;

    @BindView(R.id.register_assistant_txt_document_number)
    public EditText txtNumber;
    @BindView(R.id.register_assistant_txt_name)
    public EditText txtName;
    @BindView(R.id.spinner_document_Type)
    public Spinner spinnerDocumentType;
    private String[] arrayDocumentTypes;
    private String documentType;
    private String eventUid;
    List<String> documentTypes = new ArrayList<>();


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_assistant);
        ButterKnife.bind(this);
        initComponents();
        loadSpinner();
        initFirebase();
        spinnerOnItemSelected();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void loadSpinner() {
        documentTypes.add("Cédula de Ciudadania");
        documentTypes.add("Tarjeta de Identidad");
        documentTypes.add("Registro Civil");

        arrayDocumentTypes = new String[documentTypes.size()];
        for (int i = 0; i<documentTypes.size(); i++){
            arrayDocumentTypes[i] = documentTypes.get(i);
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayDocumentTypes);
            spinnerDocumentType.setAdapter(arrayAdapter);
        }
    }

    private void spinnerOnItemSelected() {
        spinnerDocumentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                documentType = documentTypes.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.RegisterAssistant));
        eventUid = getIntent().getStringExtra("EVENT_UID");

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goToAssistantsActivity(View view) {
        String name = txtName.getText().toString();
        String document = txtNumber.getText().toString();

        if(validateModel(name, document, eventUid, documentType)){
            Assistant assistant = new Assistant();
            assistant.setDocument(document);
            assistant.setDocumentType(documentType);
            assistant.setEventUid(eventUid);
            assistant.setUid(UUID.randomUUID().toString());
            assistant.setName(name);

            databaseReference.child("assistants").child(assistant.getUid()).setValue(assistant);
            RemoveAllFields();
            Toast.makeText(this, getString(R.string.successfully), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AssistantsActivity.class);
            intent.putExtra("EVENT_UID", eventUid);
            startActivity(intent);
        }

    }

    private void RemoveAllFields() {
        txtNumber.setText("");
        txtName.setText("");
    }

    private boolean validateModel(String name, String document, String eventUid, String documentType) {

        boolean isValid = true;
        if("".equals(name)){
            txtName.setError(getString(R.string.requerido));
            isValid = false;
        }
        if("".equals(document)){
            txtNumber.setError(getString(R.string.requerido));
            isValid = false;
        }
        if("".equals(documentType)){
            Toast.makeText(getApplicationContext(), "Debe seleccionar un tipo", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if("".equals(eventUid)){
            isValid = false;
        }

        return isValid;
    }

    public void openCam(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }
}

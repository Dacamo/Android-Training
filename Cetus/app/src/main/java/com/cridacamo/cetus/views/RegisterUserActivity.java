package com.cridacamo.cetus.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cridacamo.cetus.R;
import com.cridacamo.cetus.models.User;
import com.cridacamo.cetus.utilities.ActionBarUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterUserActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.register_user_txt_name)
    public EditText txtName;
    @BindView(R.id.register_user_txt_email)
    public EditText txtEmail;
    @BindView(R.id.register_user_txt_pass)
    public EditText txtPass;
    @BindView(R.id.register_user_txt_phone)
    public EditText txtPhone;
    boolean success = false;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);
        initComponents();
        initFirebase();
    }

    private void initFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.RegisterUser));
    }

    public void goToProfileActivity(View view) {
        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();
        String pass = txtPass.getText().toString();

        if(ValidateModel(name, email, phone, pass)){
            //register new user
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPass(pass);
            user.setPhoneNumber(phone);

            //Register authentication
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        success = true;
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getApplicationContext(), getString(R.string.problem),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

            //Register database
            if(success){
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                user.setUid(currentUser.getUid());
                databaseReference.child("users").child(user.getUid()).setValue(user);
                Toast.makeText(this, getString(R.string.successfully), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, UserProfileActivity.class);
                intent.putExtra("name", user.getName());
                intent.putExtra("email", user.getEmail());
                intent.putExtra("phone", user.getPhoneNumber());
                startActivity(intent);
                RemoveAllField();
                success = false;
            }

        }
    }

    private void RemoveAllField() {
        txtPass.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtName.setText("");

    }

    private boolean ValidateModel(String name, String email, String phone, String pass) {
        boolean isValid = true;

        if("".equals(name)){
            txtName.setError(getString(R.string.requerido));
            isValid = false;
        }
        if("".equals(pass)){
            txtPass.setError(getString(R.string.requerido));
            isValid = false;
        }
        if("".equals(phone)){
            txtPhone.setError(getString(R.string.requerido));
            isValid = false;
        }
        if("".equals(email)){
            txtEmail.setError(getString(R.string.requerido));
            isValid = false;
        }
        return isValid;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

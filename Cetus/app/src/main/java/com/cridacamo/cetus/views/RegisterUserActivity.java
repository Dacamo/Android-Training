package com.cridacamo.cetus.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cridacamo.cetus.R;
import com.cridacamo.cetus.utilities.ActionBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterUserActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.register_event_txt_name)
    public EditText txtName;
    @BindView(R.id.register_user_txt_email)
    public EditText txtEmail;
    @BindView(R.id.register_user_txt_pass)
    public EditText txtPass;
    @BindView(R.id.register_user_txt_phone)
    public EditText txtPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);
        initComponents();
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

        if(ValidateModel (name, email, phone, pass)){
            //register new User
        }

        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
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

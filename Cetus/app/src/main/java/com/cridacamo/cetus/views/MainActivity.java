package com.cridacamo.cetus.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cridacamo.cetus.R;
import com.cridacamo.cetus.models.User;
import com.cridacamo.cetus.utilities.ActionBarUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_txt_pass)
    public EditText txtPass;
    @BindView(R.id.main_txt_email)
    public EditText txtEmail;

    private FirebaseAuth mAuth;
    DatabaseReference bbdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        bbdd = FirebaseDatabase.getInstance().getReference("users");
    }

    public void goToUserProfileActivity(View view) {
        String pass = txtPass.getText().toString();
        String email = txtEmail.getText().toString();

        if(ValidateModel(pass, email)){

            //SIGN IN
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser usuario = mAuth.getCurrentUser();
                                Query q = bbdd.orderByChild("email").equalTo(usuario.getEmail()).limitToFirst(1);
                                q.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                                            User existingUser = messageSnapshot.getValue(User.class);
                                            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                                            intent.putExtra("name", existingUser.getName());
                                            intent.putExtra("email", existingUser.getEmail());
                                            intent.putExtra("phone", existingUser.getPhoneNumber());
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(getApplicationContext(), getString(R.string.serverError),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });





                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), getString(R.string.failed),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private boolean ValidateModel(String pass, String email) {
        boolean isValid = true;
        if("".equals(pass)){
            txtPass.setError(getString(R.string.requerido));
            isValid = false;
        }
        if("".equals(email)){
            txtEmail.setError(getString(R.string.requerido));
            isValid = false;
        }
        return isValid;
    }

    public void goToRegisterUserActivity(View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        ButterKnife.bind(this);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

}

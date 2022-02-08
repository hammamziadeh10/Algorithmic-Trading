package com.zgapplications.robinhoodtrading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailField;
    EditText passwordField;
    TextView incorrectView;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.login_page_username_edit_text);
        passwordField = (EditText) findViewById(R.id.login_page_password_edit_text);
        incorrectView = (TextView) findViewById(R.id.incorrect_view);
        firebaseAuth = FirebaseAuth.getInstance();


    }


    public void authenticate(View view) {
        firebaseAuth.signInWithEmailAndPassword(emailField.getText().toString(), passwordField.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    incorrectView.setVisibility(View.VISIBLE);
                }
            }
        });




    }
}

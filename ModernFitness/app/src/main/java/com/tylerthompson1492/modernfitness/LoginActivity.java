package com.tylerthompson1492.modernfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() != null)
        {
            // User is already logged in
            SwitchToHomeActivity();

        }


        editTextEmail = (EditText)findViewById(R.id.email_field);
        editTextPassword = (EditText)findViewById(R.id.password_field);
        textViewSignup = (TextView) findViewById(R.id.login_header);
        buttonSignIn = (Button)findViewById(R.id.login_button);


    }

    public void LoginOnClick(View view)
    {
        UserLogin();
    }

    public void RegisterButton(View view)
    {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void UserLogin()
    {
        String email = editTextEmail.getText().toString().trim();
        String pass = editTextPassword.getText().toString().trim();


        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass))
        {
            //pass is empty
            Toast.makeText(this, "Please enter an password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            // Start HomeScreen Activity
                            SwitchToHomeActivity();
                        }
                    }
                }
        );
    }

    private void SwitchToHomeActivity()
    {
        finish();
        startActivity(new Intent(this, Home.class));
    }



}

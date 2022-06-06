package com.example.flowerapp.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flowerapp.Activites.LoginActivity;
import com.example.flowerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText user_name,input_pass,input_email;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        input_pass= findViewById(R.id.password2);
        input_email = findViewById(R.id.email_new_user);
        register = findViewById(R.id.ok_register);
        mAuth = FirebaseAuth.getInstance();
       // register.setEnabled(true);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString();
                String password = input_pass.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) ){
                    register(email, password);

                }else {
                    Toast.makeText(SignUp.this, "Email or Password is Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void register (String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Welcome to you" , Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                } }});
    }
}





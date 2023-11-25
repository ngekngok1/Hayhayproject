package com.example.hayhay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpass extends AppCompatActivity {
    Button button;
    EditText email;
    TextView login1;
    FirebaseAuth mAuth;
    String stremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        button = findViewById(R.id.reset);
        email = findViewById(R.id.email);
        login1 = findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forgotpass.this, MainActivity.class);
                // Start the NextActivity
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stremail = email.getText().toString().trim();
                if(!TextUtils.isEmpty(stremail)){
                    ResetPassword();

                }else{
                    email.setError("Enter email!");
                }
            }
        });

    }
    private void ResetPassword(){
        mAuth.sendPasswordResetEmail(stremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Forgotpass.this,"Reset Password link has been sent to your registered email", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Forgotpass.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Forgotpass.this,"ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
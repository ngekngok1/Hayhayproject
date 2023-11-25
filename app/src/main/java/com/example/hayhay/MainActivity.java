package com.example.hayhay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView forgot;
    Button button, button1;
    EditText email;
    FirebaseAuth mAuth;
    String stremail;

    EditText email1, password1;

    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forgot = findViewById(R.id.forgot);
        email = findViewById(R.id.email);
        email1 = findViewById(R.id.email);
        button = findViewById(R.id.login);
        button1 = findViewById(R.id.signup);
        password1 = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Forgotpass.class);
                // Start the NextActivity
                startActivity(intent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                // Start the NextActivity
                startActivity(intent);
            }
        });
        if(fAuth.getCurrentUser() != null){
            Toast.makeText(MainActivity.this,"", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),Homepage.class));
            finish();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = email1.getText().toString().trim();
                String password2 = password1.getText().toString().trim();
                if(TextUtils.isEmpty(email2)){
                    email1.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password2)){
                    password1.setError("Password is Required");
                    return;
                }

                if(password2.length() < 5) {
                    password1.setError(("Password is atleast 6 Characters"));
                }

                fAuth.signInWithEmailAndPassword(email2, password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            if (user != null && user.isEmailVerified()) {
                                // Email is verified, proceed with login
                                Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Homepage.class));
                            } else {
                                // Email is not verified, prevent login
                                Toast.makeText(MainActivity.this, "Email not yet verified. Please verify your email.", Toast.LENGTH_SHORT).show();
                                // Consider logging the user out or prompting them to verify their email before allowing login
                                FirebaseAuth.getInstance().signOut();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "WRONG EMAIL OR PASSWORD!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        });
    }

}
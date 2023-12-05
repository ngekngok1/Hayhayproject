package com.example.hayhay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.Change;

public class Changepassword extends AppCompatActivity {
    Button button;
    EditText email;
    TextView login1,info,info1;
    FirebaseAuth fAuth;
    LinearLayout layout1;
    RelativeLayout layout;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    String stremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        button = findViewById(R.id.reset);
        email = findViewById(R.id.email);
        login1 = findViewById(R.id.login);
        info = findViewById(R.id.info);
        info1 = findViewById(R.id.info1);
        layout = findViewById(R.id.layout);
        layout1 = findViewById(R.id.layout1);
        int matt = ContextCompat.getColor(this, R.color.first);
        int third = ContextCompat.getColor(this, R.color.third1);
        int white = ContextCompat.getColor(this, R.color.white);
        int yellow = ContextCompat.getColor(this, R.color.gold);
        fAuth = FirebaseAuth.getInstance();
        String userId = user.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(userId);
        databaseReference.child("Mode").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and whenever the data at this location is updated
                String powerValue = dataSnapshot.getValue(String.class);
                if (powerValue != null && powerValue.equals("1")) {
                    // If Power value is "1", turn on the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            login1.setTextColor(getResources().getColor(R.color.lightgray));
                            info1.setTextColor(getResources().getColor(R.color.lightgray));
                            info.setTextColor(getResources().getColor(R.color.gold));
                            email.setTextColor(getResources().getColor(R.color.lightgray));
                            ViewCompat.setBackgroundTintList(layout, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(layout1, ColorStateList.valueOf(matt));
                        }
                    });
                } else {
                    // If Power value is not "1", turn off the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            info1.setTextColor(getResources().getColor(R.color.lightgray));
                            info.setTextColor(getResources().getColor(R.color.black));
                            email.setTextColor(getResources().getColor(R.color.black));
                            ViewCompat.setBackgroundTintList(layout, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(layout1, ColorStateList.valueOf(white));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Changepassword.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child("Email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and whenever the data at this location is updated
                String powerValue = dataSnapshot.getValue(String.class);
                email.setText(powerValue);
                email.setEnabled(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Changepassword.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Changepassword.this, Update.class);
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
                        Toast.makeText(Changepassword.this,"Reset Password link has been sent to your registered email", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Changepassword.this,Update.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Changepassword.this,"ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
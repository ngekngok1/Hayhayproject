package com.example.hayhay;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Emailchange extends AppCompatActivity {
    TextView goback;
    FirebaseAuth fAuth;
    EditText phone1,pass;
    Button update;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailchange);
        goback = findViewById(R.id.back);
        update = findViewById(R.id.update1);
        phone1 = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userId = user.getUid();
        DatabaseReference myNodeRef = database.getReference(userId);
        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myNodeRef.child("Password").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and whenever the data at this location is updated
                        String usernamee = phone1.getText().toString().trim();
                        String password = pass.getText().toString().trim();
                        String powerValue = dataSnapshot.getValue(String.class);

                        if (!password.matches(powerValue)) {
                            pass.setError("Password isn't correct!");
                            return;
                        }
                        myNodeRef.child("Email").setValue(usernamee);

                        user1.updateEmail(usernamee)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Email updated successfully
                                            // Now, send a reset email to the new email address
                                            sendResetEmailToNewEmail(usernamee);
                                        } else {
                                            // Handle error updating email
                                            Exception exception = task.getException();
                                            Log.e(TAG, "Error updating email address: " + exception.getMessage());
                                            Toast.makeText(Emailchange.this, "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        Toast.makeText(getApplicationContext(), "YOU SUCCESSFULLY CHANGED YOUR EMAIL!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors
                        Toast.makeText(Emailchange.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Update.class));
            }
        });
    }
        private void sendResetEmailToNewEmail(String newEmail) {
            FirebaseAuth.getInstance().sendPasswordResetEmail(newEmail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Reset email sent successfully
                                Toast.makeText(getApplicationContext(), "Reset email link sent to your new email address!", Toast.LENGTH_SHORT).show();
                            } else {
                                // Handle error sending reset email
                                Exception exception = task.getException();
                                Log.e(TAG, "Error sending reset email: " + exception.getMessage());
                                Toast.makeText(Emailchange.this, "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }

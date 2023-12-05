package com.example.hayhay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Usernamechange extends AppCompatActivity {
    TextView goback, info1, info;
    FirebaseAuth fAuth;

    Button update;
    EditText username1, pass;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    LinearLayout layout1;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usernamechange);
        goback = findViewById(R.id.back);
        username1 = findViewById(R.id.username);
        info1 = findViewById(R.id.info1);
        info = findViewById(R.id.info);
        update = findViewById(R.id.update1);
        pass = findViewById(R.id.password);
        layout = findViewById(R.id.layout);
        layout1 = findViewById(R.id.layout1);
        fAuth = FirebaseAuth.getInstance();
        int matt = ContextCompat.getColor(this, R.color.first);
        int third = ContextCompat.getColor(this, R.color.third1);
        int white = ContextCompat.getColor(this, R.color.white);
        int yellow = ContextCompat.getColor(this, R.color.gold);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userId = user.getUid();
        DatabaseReference myNodeRef = database.getReference(userId);
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
                            goback.setTextColor(getResources().getColor(R.color.lightgray));
                            info1.setTextColor(getResources().getColor(R.color.lightgray));
                            info.setTextColor(getResources().getColor(R.color.gold));
                            pass.setTextColor(getResources().getColor(R.color.lightgray));
                            username1.setTextColor(getResources().getColor(R.color.lightgray));
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
                            pass.setTextColor(getResources().getColor(R.color.black));
                            username1.setTextColor(getResources().getColor(R.color.black));
                            ViewCompat.setBackgroundTintList(layout, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(layout1, ColorStateList.valueOf(white));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Usernamechange.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // Inside your onClickListener for the update button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = pass.getText().toString().trim(); // New password for username change
                String usernamee = username1.getText().toString().trim(); // New username

                // Get the user's current FirebaseUser
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                // Create AuthCredential using the user's email and the current password entered by the user
                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), newPassword);

                // Re-authenticate the user with the provided credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Password re-authenticated, proceed with updating username
                                    myNodeRef.child("Username").setValue(usernamee);
                                    Toast.makeText(getApplicationContext(), "YOU SUCCESSFULLY CHANGED YOUR USERNAME!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Settings1.class));
                                } else {
                                    // Re-authentication failed
                                    pass.setError("Password isn't correct!");
                                    // Handle re-authentication failure
                                }
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


}
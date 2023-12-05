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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Codeupdate extends AppCompatActivity {
    TextView goback, info, info1;
    FirebaseAuth fAuth;
    EditText phone1,pass;
    Button update;
    LinearLayout layout1;
    RelativeLayout layout;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeupdate);
        goback = findViewById(R.id.back);
        update = findViewById(R.id.update1);
        info1 = findViewById(R.id.info1);
        info = findViewById(R.id.info);
        pass = findViewById(R.id.password);
        phone1 = findViewById(R.id.phonenumber);
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
                            phone1.setTextColor(getResources().getColor(R.color.lightgray));
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
                            phone1.setTextColor(getResources().getColor(R.color.black));
                            ViewCompat.setBackgroundTintList(layout, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(layout1, ColorStateList.valueOf(white));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Codeupdate.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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

                        if(!password.matches(powerValue))
                        {
                            pass.setError("Password isn't correct!");
                            return;
                        }

                        DatabaseReference myNodeRef1 = database.getReference(usernamee);
                        myNodeRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    if (snapshot.child("Status").exists()) {
                                        String power = snapshot.child("Status").getValue(String.class);
                                        if(power.equals("1")) {
                                            phone1.setError("Code is already taken");
                                            return;
                                        }
                                    }
                                    myNodeRef1.child("Status").setValue("0");
                                    myNodeRef.child("Code").setValue(usernamee);
                                    DatabaseReference childNodeRef3 = myNodeRef1.child("Fan");
                                    DatabaseReference childNodeRef6 = myNodeRef1.child("Cover");
                                    childNodeRef3.setValue("0");
                                    childNodeRef6.setValue("0");
                                    Toast.makeText(getApplicationContext(), "YOU SUCCESSFULLY CHANGED YOUR CODE", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Settings.class));
                                }
                                else {
                                    Toast.makeText(Codeupdate.this, "CODE DOEST NOT EXIST", Toast.LENGTH_SHORT).show();
                                    phone1.setError(("CODE DOEST NOT EXIST"));
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors
                        Toast.makeText(Codeupdate.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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

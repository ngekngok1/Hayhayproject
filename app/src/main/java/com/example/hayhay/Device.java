package com.example.hayhay;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Device extends AppCompatActivity {
    private Switch switchFan, switchCover, switchDevice;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        final RelativeLayout layout = findViewById(R.id.layout);
        String userId = user.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(userId);
        switchFan = findViewById(R.id.Fan);
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
                            layout.setBackgroundColor(getResources().getColor(R.color.gray));
                        }
                    });
                } else {
                    // If Power value is not "1", turn off the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            layout.setBackgroundColor(Color.WHITE);
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Device.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.child("Fan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and whenever the data at this location is updated
                String powerValue = dataSnapshot.getValue(String.class);

                if (powerValue != null && powerValue.equals("1")) {
                    // If Power value is "1", turn on the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchFan.setChecked(true);
                        }
                    });
                } else {
                    // If Power value is not "1", turn off the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchFan.setChecked(false);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Device.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        switchCover = findViewById(R.id.Cover);
        databaseReference.child("Cover").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and whenever the data at this location is updated
                String powerValue = dataSnapshot.getValue(String.class);

                if (powerValue != null && powerValue.equals("1")) {
                    // If Power value is "1", turn on the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchCover.setChecked(true);
                        }
                    });
                } else {
                    // If Power value is not "1", turn off the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchCover.setChecked(false);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Device.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        switchDevice = findViewById(R.id.device);
        databaseReference.child("Device").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and whenever the data at this location is updated
                String powerValue = dataSnapshot.getValue(String.class);

                if (powerValue != null && powerValue.equals("1")) {
                    // If Power value is "1", turn on the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchDevice.setChecked(true);
                        }
                    });
                } else {
                    // If Power value is not "1", turn off the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchDevice.setChecked(false);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Device.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        switchFan.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                databaseReference.child("Fan").setValue("1");
                Toast.makeText(Device.this,"Fan ON", Toast.LENGTH_SHORT).show();
            } else {
                databaseReference.child("Fan").setValue("0");
                Toast.makeText(Device.this,"Fan OFF", Toast.LENGTH_SHORT).show();
            }

            // Initialize and set a listener for the second Switch

        });

        switchCover.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                databaseReference.child("Cover").setValue("1");
                Toast.makeText(Device.this,"Cover ON", Toast.LENGTH_SHORT).show();
            } else {
                databaseReference.child("Cover").setValue("0");
                Toast.makeText(Device.this,"Cover OFF", Toast.LENGTH_SHORT).show();
            }

            // Initialize and set a listener for the second Switch

        });
        switchDevice.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                databaseReference.child("Device").setValue("1");
                Toast.makeText(Device.this,"Device ON", Toast.LENGTH_SHORT).show();
            } else {
                databaseReference.child("Device").setValue("0");
                Toast.makeText(Device.this,"Device OFF", Toast.LENGTH_SHORT).show();
            }

            // Initialize and set a listener for the second Switch

        });






    }
}
package com.example.hayhay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

public class Settings1 extends AppCompatActivity {

    CardView back1,back2,back3,back4;
    TextView text11,text22,text33,text44,goback;
    ImageButton update, about, log, help1;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    LinearLayout layout2;
    FirebaseUser user = mAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings1);
        layout2 = findViewById(R.id.layout1);
        goback = findViewById(R.id.back);
        text11 = findViewById(R.id.text1);
        text22 = findViewById(R.id.text2);
        text33 = findViewById(R.id.text3);
        text44 = findViewById(R.id.text4);
        back1 = findViewById(R.id.updateback);
        back2 = findViewById(R.id.aboutback);
        back3 = findViewById(R.id.darkback);
        back4 = findViewById(R.id.logback);
        update = findViewById(R.id.update1);
        help1 = findViewById(R.id.help1);
        about = findViewById(R.id.about1);
        log = findViewById(R.id.log1);
        int matt = ContextCompat.getColor(this, R.color.first);
        int white = ContextCompat.getColor(this, R.color.white);

        String userId = user.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(userId);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Settings1.this,"Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Homepage.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings1.this, Aboutus.class);
                // Start the NextActivity
                startActivity(intent);
            }
        });
        help1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings1.this, Helpcenter.class);
                // Start the NextActivity
                startActivity(intent);
            }
        });


        final RelativeLayout layout = findViewById(R.id.layout);

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
                            layout2.setBackgroundColor(getResources().getColor(R.color.first));
                            layout.setBackgroundColor(getResources().getColor(R.color.gray));
                            text11.setTextColor(getResources().getColor(R.color.lightgray));
                            text22.setTextColor(getResources().getColor(R.color.lightgray));
                            text33.setTextColor(getResources().getColor(R.color.lightgray));
                            text44.setTextColor(getResources().getColor(R.color.lightgray));
                            ViewCompat.setBackgroundTintList(back1, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(back2, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(back3, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(back4, ColorStateList.valueOf(matt));
                            update.setBackgroundColor(getResources().getColor(R.color.first));
                            about.setBackgroundColor(getResources().getColor(R.color.first));
                            help1.setBackgroundColor(getResources().getColor(R.color.first));
                            log.setBackgroundColor(getResources().getColor(R.color.first));
                        }
                    });
                } else {
                    // If Power value is not "1", turn off the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            layout2.setBackgroundColor(getResources().getColor(R.color.yellow2));
                            layout.setBackgroundColor(Color.WHITE);
                            text11.setTextColor(getResources().getColor(R.color.black));
                            text22.setTextColor(getResources().getColor(R.color.black));
                            text33.setTextColor(getResources().getColor(R.color.black));
                            text44.setTextColor(getResources().getColor(R.color.black));
                            ViewCompat.setBackgroundTintList(back1, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(back2, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(back3, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(back4, ColorStateList.valueOf(white));
                            update.setBackgroundColor(getResources().getColor(R.color.white));
                            about.setBackgroundColor(getResources().getColor(R.color.white));
                            help1.setBackgroundColor(getResources().getColor(R.color.white));
                            log.setBackgroundColor(getResources().getColor(R.color.white));
                        }
                    });
                }
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Settings1.this, Update.class);
                        // Start the NextActivity
                        startActivity(intent);
                    }
                });
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Settings1.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
package com.example.hayhay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Aboutus extends AppCompatActivity {
TextView text, text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;
    ScrollView layout;
    LinearLayout layout1;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        layout = findViewById(R.id.layout);
        layout1 = findViewById(R.id.layout1);
        text = findViewById(R.id.text);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);
        text9 = findViewById(R.id.text9);
        text10 = findViewById(R.id.text10);
        int matt = ContextCompat.getColor(this, R.color.first);
        int third = ContextCompat.getColor(this, R.color.third1);
        int white = ContextCompat.getColor(this, R.color.white);
        int yellow = ContextCompat.getColor(this, R.color.gold);
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
                            text.setTextColor(getResources().getColor(R.color.black));
                            text1.setTextColor(getResources().getColor(R.color.lightgray));
                            text2.setTextColor(getResources().getColor(R.color.black));
                            text3.setTextColor(getResources().getColor(R.color.lightgray));
                            text4.setTextColor(getResources().getColor(R.color.black));
                            text5.setTextColor(getResources().getColor(R.color.lightgray));
                            text6.setTextColor(getResources().getColor(R.color.lightgray));
                            text7.setTextColor(getResources().getColor(R.color.lightgray));
                            text8.setTextColor(getResources().getColor(R.color.lightgray));
                            text9.setTextColor(getResources().getColor(R.color.black));
                            text10.setTextColor(getResources().getColor(R.color.lightgray));
                            ViewCompat.setBackgroundTintList(layout, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(layout1, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(text, ColorStateList.valueOf(third));
                            ViewCompat.setBackgroundTintList(text2, ColorStateList.valueOf(third));
                            ViewCompat.setBackgroundTintList(text4, ColorStateList.valueOf(third));
                            ViewCompat.setBackgroundTintList(text9, ColorStateList.valueOf(third));
                        }
                    });
                } else {
                    // If Power value is not "1", turn off the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.setTextColor(getResources().getColor(R.color.black));
                            text1.setTextColor(getResources().getColor(R.color.black));
                            text2.setTextColor(getResources().getColor(R.color.black));
                            text3.setTextColor(getResources().getColor(R.color.black));
                            text4.setTextColor(getResources().getColor(R.color.black));
                            text5.setTextColor(getResources().getColor(R.color.black));
                            text6.setTextColor(getResources().getColor(R.color.black));
                            text7.setTextColor(getResources().getColor(R.color.black));
                            text8.setTextColor(getResources().getColor(R.color.black));
                            text9.setTextColor(getResources().getColor(R.color.black));
                            text10.setTextColor(getResources().getColor(R.color.black));
                            ViewCompat.setBackgroundTintList(layout, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(layout1, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(text, ColorStateList.valueOf(yellow));
                            ViewCompat.setBackgroundTintList(text2, ColorStateList.valueOf(yellow));
                            ViewCompat.setBackgroundTintList(text4, ColorStateList.valueOf(yellow));
                            ViewCompat.setBackgroundTintList(text9, ColorStateList.valueOf(yellow));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Aboutus.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // No need to set click listeners here since we specified them in XML
    }

    // Method to open Facebook app
    public void openFacebook(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/hayhayproduct"));
        startActivity(intent);
    }



    // Method to open Instagram app
    public void openInstagram(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/hayhayproject1"));
        startActivity(intent);
    }

    // Method to open Gmail app
    public void openGmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:hayhayprojectt@gmail.com"));
        startActivity(intent);
    }

    // Method to open Twitter app
    public void openTwitter(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/hayhayprojectt"));
        startActivity(intent);
    }
}

package com.example.hayhay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import android.widget.Toolbar;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


public class Helpcenter extends AppCompatActivity {
    TextView about,tex,tex1,tex22,tex4,tex2,tex3,tex5,tex6,tex7,tex8,tex9,tex10,tex11,tex12,tex13,tex14,tex15,tex17,tex18,tex19,tex20,tex16,tex21,tex23;
    ScrollView layout;
    Toolbar toolbar;
    ImageView logo1;
    LinearLayout layout1;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpcenter);
        // No need to set click listeners here since we specified them in XML
        layout = findViewById(R.id.layout);
        logo1 = findViewById(R.id.logo1);
        toolbar = findViewById(R.id.toolbar);
        layout1 = findViewById(R.id.layout1);
        about = findViewById(R.id.aboutus);
        tex = findViewById(R.id.text);
        tex16 = findViewById(R.id.text16);
        tex21 = findViewById(R.id.text21);
        tex23 = findViewById(R.id.text23);
        tex1 = findViewById(R.id.text1);
        tex2 = findViewById(R.id.text2);
        tex22 = findViewById(R.id.text22);
        tex4 = findViewById(R.id.text4);
        tex3 = findViewById(R.id.text3);
        tex5 = findViewById(R.id.text5);
        tex6 = findViewById(R.id.text6);
        tex7= findViewById(R.id.text7);
        tex8 = findViewById(R.id.text8);
        tex9 = findViewById(R.id.text9);
        tex10 = findViewById(R.id.text10);
        tex11 = findViewById(R.id.text11);
        tex12 = findViewById(R.id.text12);
        tex13= findViewById(R.id.text13);
        tex14 = findViewById(R.id.text14);
        tex15 = findViewById(R.id.text15);
        tex17 = findViewById(R.id.text17);
        tex18 = findViewById(R.id.text18);
        tex19 = findViewById(R.id.text19);
        tex20 = findViewById(R.id.text20);
        int matt = ContextCompat.getColor(this, R.color.first);
        int third = ContextCompat.getColor(this, R.color.third1);
        int white = ContextCompat.getColor(this, R.color.white);
        int yellow = ContextCompat.getColor(this, R.color.gold);
        String userId = user.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(userId);

        logo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Homepage.class));

            }
        });
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
                            toolbar.setBackgroundColor(getResources().getColor(R.color.gray));
                            tex11.setTextColor(getResources().getColor(R.color.lightgray));
                            tex16.setTextColor(getResources().getColor(R.color.lightgray));
                            tex23.setTextColor(getResources().getColor(R.color.lightgray));
                            tex21.setTextColor(getResources().getColor(R.color.lightgray));
                            tex1.setTextColor(getResources().getColor(R.color.lightgray));
                            tex3.setTextColor(getResources().getColor(R.color.lightgray));
                            tex22.setTextColor(getResources().getColor(R.color.lightgray));
                            tex8.setTextColor(getResources().getColor(R.color.lightgray));
                            tex4.setTextColor(getResources().getColor(R.color.lightgray));
                            about.setTextColor(getResources().getColor(R.color.lightgray));
                            tex10.setTextColor(getResources().getColor(R.color.lightgray));
                            tex6.setTextColor(getResources().getColor(R.color.lightgray));
                            tex13.setTextColor(getResources().getColor(R.color.lightgray));
                            tex15.setTextColor(getResources().getColor(R.color.lightgray));
                            tex18.setTextColor(getResources().getColor(R.color.lightgray));
                            tex20.setTextColor(getResources().getColor(R.color.lightgray));
                            ViewCompat.setBackgroundTintList(layout, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(layout1, ColorStateList.valueOf(matt));

                        }
                    });
                } else {
                    // If Power value is not "1", turn off the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toolbar.setBackgroundColor(getResources().getColor(R.color.gold));
                            tex11.setTextColor(getResources().getColor(R.color.black));
                            tex16.setTextColor(getResources().getColor(R.color.black));
                            tex4.setTextColor(getResources().getColor(R.color.black));
                            about.setTextColor(getResources().getColor(R.color.black));
                            tex10.setTextColor(getResources().getColor(R.color.black));
                            tex23.setTextColor(getResources().getColor(R.color.black));
                            tex21.setTextColor(getResources().getColor(R.color.black));
                            tex1.setTextColor(getResources().getColor(R.color.black));
                            tex3.setTextColor(getResources().getColor(R.color.black));
                            tex22.setTextColor(getResources().getColor(R.color.black));
                            tex6.setTextColor(getResources().getColor(R.color.black));
                            tex10.setTextColor(getResources().getColor(R.color.black));
                            tex13.setTextColor(getResources().getColor(R.color.black));
                            tex15.setTextColor(getResources().getColor(R.color.black));
                            tex18.setTextColor(getResources().getColor(R.color.black));
                            tex20.setTextColor(getResources().getColor(R.color.black));
                            ViewCompat.setBackgroundTintList(layout, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(layout1, ColorStateList.valueOf(white));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Helpcenter.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // No need to set click listeners here since we specified them in XML


        tex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "HayHay is a Capstone Project made by the Team Sensor Fusion. The general objective of this is to design, develop, and test a device that can automatically retract a clothes rack system when it rains. The device will be particularly useful for busy individuals in a household who may not have the time to constantly monitor their clothes or items placed on the rack.";
                if (tex1.getVisibility() == View.VISIBLE) {
                    tex1.setVisibility(View.GONE);
                } else {
                    tex1.setText(value);
                    tex1.setVisibility(View.VISIBLE);

                }

            }
        });

        tex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "If you have specific questions or issues related to a product, service, or platform called HayHay, please reach out to our support team at support@hayhayprojectt.com. Our dedicated team is ready to assist you.\n";
                if (tex3.getVisibility() == View.VISIBLE) {
                    tex3.setVisibility(View.GONE);
                } else {
                    tex3.setText(value);
                    tex3.setVisibility(View.VISIBLE);

                }

            }
        });
        tex5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "If HayHay requires user accounts, you can usually create one by visiting our website or app. Look for the \"Sign Up\" or \"Create Account\" option and follow the prompts to set up your account.\n";
                tex6.setText(value);
                if (tex6.getVisibility() == View.VISIBLE) {
                    tex6.setVisibility(View.GONE);
                } else {
                    tex6.setVisibility(View.VISIBLE);
                    tex6.setText(value);
                }

            }
        });

        tex7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "Forgot your password? No worries! On the login page, click on the \"Forgot Password\" or similar link, and follow the instructions to reset your password securely.\n";
                tex22.setText(value);
                if (tex22.getVisibility() == View.VISIBLE) {
                    tex22.setVisibility(View.GONE);
                } else {
                    tex22.setVisibility(View.VISIBLE);
                    tex22.setText(value);
                }

            }
        });
        tex9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "If you're experiencing issues with the HayHay app or platform, try the following steps:\n" +
                        "Ensure your app is updated to the latest version.\n" +
                        "Check your internet connection.\n" +
                        "Clear cache and cookies if using a web platform.\n";
                tex10.setText(value);
                if (tex10.getVisibility() == View.VISIBLE) {
                    tex10.setVisibility(View.GONE);
                } else {
                    tex10.setVisibility(View.VISIBLE);
                    tex10.setText(value);
                }

            }
        });
        tex12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "Learn about how HayHay handles your data by reviewing our Privacy Policy. We take your privacy seriously, and this policy outlines how we collect, use, and protect your information.";
                tex13.setText(value);
                if (tex13.getVisibility() == View.VISIBLE) {
                    tex13.setVisibility(View.GONE);
                } else {
                    tex13.setVisibility(View.VISIBLE);
                    tex13.setText(value);
                }

            }
        });
        tex14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "If you suspect any security issues or unauthorized access, contact us immediately at security @hayhayprojectt@gmail.com.";
                tex15.setText(value);
                if (tex15.getVisibility() == View.VISIBLE) {
                    tex15.setVisibility(View.GONE);
                } else {
                    tex15.setVisibility(View.VISIBLE);
                    tex15.setText(value);
                }

            }
        });
        tex17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "We value your feedback! If you have suggestions or comments about HayHay, please share them with us at @hayhayprojectt@gmail.com.";
                tex18.setText(value);
                if (tex18.getVisibility() == View.VISIBLE) {
                    tex18.setVisibility(View.GONE);
                } else {
                    tex18.setVisibility(View.VISIBLE);
                    tex18.setText(value);
                }

            }
        });
        tex19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "For details on the terms of use, please refer to our Terms of Service.";
                tex20.setText(value);
                if (tex20.getVisibility() == View.VISIBLE) {
                    tex20.setVisibility(View.GONE);
                } else {
                    tex20.setVisibility(View.VISIBLE);
                    tex20.setText(value);
                }

            }
        });



    }
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

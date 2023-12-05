package com.example.hayhay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {
    ImageView logo1;
    private List<String> messages;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private FirebaseAuth mAuth;

    private FirebaseUser user;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        logo1 = findViewById(R.id.logo1);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        messages = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        logo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Homepage.class));

            }
        });

        if (user != null) {
            String userId = user.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference().child(userId).child("Notifications");

            // Add long click listener to items for deletion
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String previousChildName) {
                    String message = dataSnapshot.getValue(String.class);
                    if (message != null) {
                        messages.add(0, message);
                        adapter.notifyDataSetChanged();
                    }
                }

                // Other overridden methods for ChildEventListener
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String previousChildName) {}
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String previousChildName) {}
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }
    }
}

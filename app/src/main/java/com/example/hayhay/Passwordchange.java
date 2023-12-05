package com.example.hayhay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Passwordchange extends AppCompatActivity {
    TextView goback;
    FirebaseAuth fAuth;
    EditText phone1,pass1,pass2;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordchange);
        goback = findViewById(R.id.back);
        pass1 = findViewById(R.id.password);
        pass2 = findViewById(R.id.password1);
        phone1 = findViewById(R.id.oldpassword);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userId = user.getUid();
        String pass12 = pass1.getText().toString().trim();
        String pass22 = pass2.getText().toString().trim();
        DatabaseReference myNodeRef = database.getReference(userId);

        myNodeRef.child("Password").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and whenever the data at this location is updated
                String usernamee = phone1.getText().toString().trim();
                String powerValue = dataSnapshot.getValue(String.class);

                if(!usernamee.matches(powerValue))
                {
                    phone1.setError("Password isn't correct!");
                    return;
                }
                if(!pass12.matches(pass22)){
                    pass2.setError("Password doesn't matched!");
                    return;
                }
                myNodeRef.child("Password").setValue(usernamee);
                Toast.makeText(getApplicationContext(), "YOU SUCCESSFULLY CHANGED YOUR PASSWORD!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Settings.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Passwordchange.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
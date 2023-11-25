package com.example.hayhay;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    EditText email1, password1,username1,phonenumber1,code1,password2;
    TextView login1;
    Button button;
    FirebaseAuth fAuth;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login1 = findViewById(R.id.login);
        email1 = findViewById(R.id.email);
        code1 = findViewById(R.id.code);
        phonenumber1 = findViewById(R.id.phonenumber);
        username1 = findViewById(R.id.username);
        password1 = findViewById(R.id.password);
        password2 = findViewById(R.id.password1);
        fAuth = FirebaseAuth.getInstance();
        button = findViewById(R.id.register1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

    login1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Register.this, MainActivity.class);
        // Start the NextActivity
        startActivity(intent);
    }
    });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codee = code1.getText().toString().trim();
                DatabaseReference myNodeRef = database.getReference(codee);
                myNodeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String email2 = email1.getText().toString().trim();
                            String password12 = password1.getText().toString().trim();
                            String password22 = password2.getText().toString().trim();
                            String phonenumber2 = phonenumber1.getText().toString().trim();
                            String username2 = username1.getText().toString().trim();
                            String emailPattern = "[a-zA-Z0-9._-]+@gmail+\\.+com+";
                            String phonenum = "(0|\\+63)\\d{10}";

                            if(TextUtils.isEmpty(username2)){
                                username1.setError("Username is Required");
                                return;
                            }
                            if(username2.length() < 5) {
                                username1.setError(("Username must atleast 6 Characters"));
                                return;
                            }
                            if(TextUtils.isEmpty(password12)){
                                password1.setError("Password is Required");
                                return;
                            }
                            if(password12.length() < 5) {
                                password1.setError(("Password must atleast 6 Characters"));
                                return;
                            }
                            if(TextUtils.isEmpty(password22)){
                                password2.setError("Confirm Password is Required");
                                return;
                            }
                            if(!password12.equals(password22)) {
                                password2.setError(("Password not matched"));
                                return;
                            }
                            if(TextUtils.isEmpty(email2)){
                                email1.setError("Email is Required");
                                return;
                            }
                            if(TextUtils.isEmpty(phonenumber2)){
                                phonenumber1.setError("Cellphone number is Required");
                                return;
                            }
                            if (!phonenumber2.matches(phonenum)) {
                                phonenumber1.setError("Enter a valid phone number");
                                return;
                            }
                            if(password12.length() < 10 && password12.length() > 12) {
                                password1.setError(("Cellphone number required 11 numbers"));
                                return;
                            }
                            if(!email2.matches(emailPattern)){
                                email1.setError("Enter a valid Email address");
                                return;
                            }
                            if (dataSnapshot.child("Cover").exists()) {
                                String coverValue = dataSnapshot.child("Cover").getValue(String.class);
                                if (coverValue.equals("1")) {
                                    code1.setError("Code is already taken");
                                    return;
                                }
                            }
                            fAuth.createUserWithEmailAndPassword(email2, password12).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            String usernamee = username1.getText().toString().trim();
                                            String passwordd = password1.getText().toString().trim();
                                            String email = email1.getText().toString().trim();
                                            String phone = phonenumber1.getText().toString().trim();
                                            String fan = "0";
                                            String cover = "0";
                                            String device = "0";
                                            String mode = "0";

                                            FirebaseUser user = mAuth.getCurrentUser();
                                            String userId = user.getUid();
                                            DatabaseReference usersRef = database.getReference(userId);
                                            DatabaseReference childNodeRef3 = myNodeRef.child("Fan");
                                            DatabaseReference childNodeRef6 = myNodeRef.child("Cover");
                                            DatabaseReference childNodeRef7 = myNodeRef.child("Device");

                                            DatabaseReference childNodeRef00 = usersRef.child("Username");
                                            DatabaseReference childNodeRef11 = usersRef.child("Password");
                                            DatabaseReference childNodeRef44 = usersRef.child("Email");
                                            DatabaseReference childNodeRef55 = usersRef.child("Phone Number");
                                            DatabaseReference childNodeRef22 = usersRef.child("Code");
                                            DatabaseReference childNodeRef33 = usersRef.child("Fan");
                                            DatabaseReference childNodeRef66 = usersRef.child("Cover");
                                            DatabaseReference childNodeRef77 = usersRef.child("Device");
                                            DatabaseReference childNodeRef88 = usersRef.child("Mode");
                                            databaseReference = FirebaseDatabase.getInstance().getReference(codee);
                                            usersRef.setValue(email);
                                            childNodeRef00.setValue(usernamee);
                                            childNodeRef11.setValue(passwordd);
                                            childNodeRef22.setValue(codee);
                                            childNodeRef33.setValue(fan);
                                            childNodeRef44.setValue(email);
                                            childNodeRef55.setValue(phone);
                                            childNodeRef66.setValue(cover);
                                            childNodeRef77.setValue(device);
                                            childNodeRef88.setValue(mode);

                                            childNodeRef3.setValue(fan);
                                            childNodeRef6.setValue(cover);
                                            childNodeRef7.setValue(device);
                                            Log.d(TAG, "Node exists!");
                                            user = mAuth.getCurrentUser();
                                            if (user != null) {
                                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> verificationTask) {
                                                        if (verificationTask.isSuccessful()) {
                                                            Toast.makeText(Register.this, "Email has been sent to your email address", Toast.LENGTH_SHORT).show();
                                                            // You may want to prompt the user to check their email for verification
                                                        } else {
                                                            Toast.makeText(Register.this, "Oops! Something went wrong", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                                // Proceed with other actions or show relevant messages
                                            }



                                        } else {
                                            Toast.makeText(Register.this, "ERROR!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        if(e instanceof FirebaseAuthUserCollisionException){
                                            email1.setError("Email is already Registered");
                                        }
                                        else{
                                            Toast.makeText(Register.this,"Oops! Something went wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                        } else {
                            // The node does not exist
                            Toast.makeText(Register.this,"CODE DOEST NOT EXIST", Toast.LENGTH_SHORT).show();
                            code1.setError(("CODE DOEST NOT EXIST"));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle errors
                        Log.e(TAG, "Error: " + databaseError.getMessage());
                    }
                });
            }
        });

    }


}
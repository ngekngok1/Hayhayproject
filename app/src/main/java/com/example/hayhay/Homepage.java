package com.example.hayhay;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Homepage extends AppCompatActivity {
    Button fan1,cover1;
    CardView back1, back2, back3, back4, back5;
    TextView name, day1, text, location1,loc11;
    ImageButton notification, settings, feedback,dark;
    FirebaseAuth fAuth;
    GifImageView sun1, sunrain1, cloudy1, cloudynight1, thunderstorm1, cloudyday1, nightrain1;
    TextView devicet, settingst, feedbackt, notificationt ,text33;
    View view;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    RelativeLayout layout;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 101;
    FusedLocationProviderClient fusedLocationClient;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        fan1 = findViewById(R.id.fan);
        text33 = findViewById(R.id.text3);
        cover1 = findViewById(R.id.cover);
        thunderstorm1 = findViewById(R.id.thunderstorm);
        cloudyday1 = findViewById(R.id.cloudyday);
        cloudy1 = findViewById(R.id.cloudy);
        cloudynight1 = findViewById(R.id.cloudynight);
        sunrain1 = findViewById(R.id.sunrain);
        nightrain1 = findViewById(R.id.nightrain);
        devicet = findViewById(R.id.devicee);
        settingst = findViewById(R.id.textsettings);
        feedbackt = findViewById(R.id.textfeedback);
        notificationt = findViewById(R.id.textnotification);
        back1 = findViewById(R.id.deviceback);
        back2 = findViewById(R.id.notifback);
        back3 = findViewById(R.id.settingsback);
        back4 = findViewById(R.id.feedbackback);
        back5 = findViewById(R.id.weatherback);
        notification = findViewById(R.id.notification);
        settings = findViewById(R.id.settings);
        feedback = findViewById(R.id.feedback);
        name = findViewById(R.id.name1);
        day1 = findViewById(R.id.day);
        view = findViewById(R.id.topview);
        dark = findViewById(R.id.dark1);
        int matt = ContextCompat.getColor(this, R.color.first);
        int third = ContextCompat.getColor(this, R.color.third1);
        int sky = ContextCompat.getColor(this, R.color.sky);
        int white = ContextCompat.getColor(this, R.color.white);
        int gray = ContextCompat.getColor(this, R.color.gray);
        layout = findViewById(R.id.layout);
        text = findViewById(R.id.loc);
        loc11 = findViewById(R.id.loc1);
        location1 = findViewById(R.id.location);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        String userId = user.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(userId);

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION);
        } else {
            getLocation();
        }

        databaseReference.child("Username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and whenever the data at this location is updated
                String powerValue = dataSnapshot.getValue(String.class);
                name.setText(" " + powerValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Homepage.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Mode").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and whenever the data at this location is updated
                        String currentValue = dataSnapshot.getValue(String.class);
                        if (currentValue != null) {
                            // Toggle the value between "0" and "1"
                            String newValue = currentValue.equals("0") ? "1" : "0";
                            databaseReference.child("Mode").setValue(newValue);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors
                        Toast.makeText(Homepage.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        fan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Fan").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and whenever the data at this location is updated
                        String currentValue = dataSnapshot.getValue(String.class);
                        if (currentValue != null) {
                            // Toggle the value between "0" and "1"
                            String newValue = currentValue.equals("0") ? "1" : "0";
                            databaseReference.child("Fan").setValue(newValue);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors
                        Toast.makeText(Homepage.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        cover1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Cover").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and whenever the data at this location is updated
                        String currentValue = dataSnapshot.getValue(String.class);
                        if (currentValue != null) {
                            // Toggle the value between "0" and "1"
                            String newValue = currentValue.equals("0") ? "1" : "0";
                            databaseReference.child("Cover").setValue(newValue);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors
                        Toast.makeText(Homepage.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
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
                            devicet.setTextColor(getResources().getColor(R.color.lightgray));
                            text33.setTextColor(getResources().getColor(R.color.lightgray));
                            settingst.setTextColor(getResources().getColor(R.color.lightgray));
                            feedbackt.setTextColor(getResources().getColor(R.color.lightgray));
                            notificationt.setTextColor(getResources().getColor(R.color.lightgray));
                            ViewCompat.setBackgroundTintList(back1, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(back2, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(back3, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(back4, ColorStateList.valueOf(matt));
                            ViewCompat.setBackgroundTintList(back5, ColorStateList.valueOf(gray));
                            dark.setBackgroundColor(getResources().getColor(R.color.first));
                            settings.setBackgroundColor(getResources().getColor(R.color.first));
                            notification.setBackgroundColor(getResources().getColor(R.color.first));
                            feedback.setBackgroundColor(getResources().getColor(R.color.first));
                            view.setBackgroundColor(getResources().getColor(R.color.sec));
                            layout.setBackgroundColor(getResources().getColor(R.color.gray));

                        }
                    });
                } else {
                    // If Power value is not "1", turn off the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            layout.setBackgroundColor(Color.WHITE);
                            day1.setTextColor(getResources().getColor(R.color.white));
                            name.setTextColor(getResources().getColor(R.color.white));
                            devicet.setTextColor(getResources().getColor(R.color.black));
                            text33.setTextColor(getResources().getColor(R.color.black));
                            settingst.setTextColor(getResources().getColor(R.color.black));
                            feedbackt.setTextColor(getResources().getColor(R.color.black));
                            notificationt.setTextColor(getResources().getColor(R.color.black));
                            dark.setBackgroundColor(getResources().getColor(R.color.white));
                            settings.setBackgroundColor(getResources().getColor(R.color.white));
                            notification.setBackgroundColor(getResources().getColor(R.color.white));
                            feedback.setBackgroundColor(getResources().getColor(R.color.white));
                            settings.setBackgroundColor(getResources().getColor(R.color.white));
                            notification.setBackgroundColor(getResources().getColor(R.color.white));
                            feedback.setBackgroundColor(getResources().getColor(R.color.white));
                            ViewCompat.setBackgroundTintList(back1, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(back2, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(back3, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(back4, ColorStateList.valueOf(white));
                            ViewCompat.setBackgroundTintList(back5, ColorStateList.valueOf(white));
                            view.setBackgroundColor(getResources().getColor(R.color.yellow2));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Homepage.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.child("Cover").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String powerValue = dataSnapshot.getValue(String.class);

                if (powerValue != null && powerValue.equals("1")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ViewCompat.setBackgroundTintList(cover1, ColorStateList.valueOf(sky));
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ViewCompat.setBackgroundTintList(cover1, ColorStateList.valueOf(third));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors if needed
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
                            ViewCompat.setBackgroundTintList(fan1, ColorStateList.valueOf(sky));
                            makeNotification();
                        }
                    });
                } else {
                    // If Power value is not "1", turn off the switch
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ViewCompat.setBackgroundTintList(fan1, ColorStateList.valueOf(third));
                        }
                    });
                }
            }

            public void makeNotification() {
                // Check if there are pending notifications
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                boolean isNotificationActive = false;

                if (notificationManager != null) {
                    StatusBarNotification[] notifications = notificationManager.getActiveNotifications();

                    for (StatusBarNotification notification : notifications) {
                        if (notification.getId() == 0 /* Replace 0 with your notification ID */) {
                            // There is already a notification with the same ID pending, don't send a new one
                            isNotificationActive = true;
                            break;
                        }
                    }
                }

                if (!isNotificationActive) {
                    Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
                    String chanelID = "CHANNEL_ID_NOTIFICATION";
                    String data = "Your clothes are now covered, and your fan is now turned on! :)";
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), chanelID);
                    builder.setSmallIcon(R.drawable.img)
                            .setContentTitle("RAIN DETECTED!")
                            .setContentText(data)
                            .setLargeIcon(logoBitmap)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    Intent intent = new Intent(getApplicationContext(), Notification.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("data", "Some value to be passed here");

                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
                    builder.setContentIntent(pendingIntent);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel notificationChannel = notificationManager.getNotificationChannel(chanelID);

                        if (notificationChannel == null) {
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            notificationChannel = new NotificationChannel(chanelID, "Some description", importance);
                            notificationChannel.setLightColor(Color.GREEN);
                            notificationChannel.enableVibration(true);
                            notificationManager.createNotificationChannel(notificationChannel);
                        }
                    }

                    notificationManager.notify(0, builder.build());
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Homepage.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Settings.class);
                // Start the NextActivity
                startActivity(intent);
            }


        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Notification.class);
                // Start the NextActivity
                startActivity(intent);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String UriText = "mailto:" + Uri.encode("hayhayprojectt@gmail.com") + "?subject=" + Uri.encode("Feedback") + "&body="
                        + Uri.encode("");
                Uri uri = Uri.parse(UriText);
                intent.setData(uri);
                startActivity(Intent.createChooser(intent, "Send Feedback to"));
            }

        });
    }

    private void getLocation() {

        try {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            fetchWeather(latitude, longitude);
                            if (location != null) {
                                // Get address based on latitude and longitude
                                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                                try {
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    if (!addresses.isEmpty()) {
                                        String city = addresses.get(0).getLocality();
                                        String country = addresses.get(0).getCountryName();

                                        // Display the location information
                                        String locationText = "" + city + "   \n" + country;
                                        location1.setText(locationText);

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Toast.makeText(Homepage.this,
                                    "Location not available", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void fetchWeather(double latitude, double longitude) {
        // Your OpenWeatherMap API key
        String apiKey = "201a67abeec47d8ef8db5a1b8be8f4cc";
        String url = "https://api.openweathermap.org/data/2.5/weather?lat="
                + latitude + "&lon=" + longitude + "&appid=" + apiKey + "&units=metric";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(Homepage.this,
                            "Failed to fetch weather data. Check your internet connection.",
                            Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {

                    String responseData = response.body().string();
                    WeatherData weatherData = new Gson().fromJson(responseData, WeatherData.class);

                    if (weatherData != null) {
                        double temperatureCelsius = weatherData.getMain().getTemp();
                        String weatherCondition = "";

                        if (weatherData.getWeather() != null && weatherData.getWeather().length > 0) {
                            weatherCondition = weatherData.getWeather()[0].getDescription();
                        }

                        final String finalWeatherCondition = weatherCondition;
                        runOnUiThread(() -> {
                            // Display temperature and weather condition in the TextView
                            Calendar calendar = Calendar.getInstance();
                            int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                            String temperatureText = String.format("\t%.1f°C\n", temperatureCelsius);
                            String weatherConditionText = String.format("\t%s\n", finalWeatherCondition);
                            text.setText(temperatureText);
                            loc11.setText(weatherConditionText);
                            if (finalWeatherCondition.equals("clear sky") || finalWeatherCondition.equals("few clouds") || finalWeatherCondition.equals("broken clouds")) {
                                if (hourOfDay >= 0 && hourOfDay < 4) {
                                    cloudynight1.setVisibility(View.VISIBLE);
                                } else if (hourOfDay >= 6 && hourOfDay < 18) {
                                    cloudyday1.setVisibility(View.VISIBLE);
                                } else {
                                    cloudynight1.setVisibility(View.VISIBLE);
                                }
                            }
                            if (finalWeatherCondition.equals("overcast clouds")) {

                                cloudy1.setVisibility(View.VISIBLE);

                            }
                            if (finalWeatherCondition.equals("thunderstorm with light rain") || finalWeatherCondition.equals("thunderstorm with rain") || finalWeatherCondition.equals("thunderstorm with heavy rain") ||
                                    finalWeatherCondition.equals("light thunderstorm") || finalWeatherCondition.equals("thunderstorm with rain") || finalWeatherCondition.equals("thunderstorm") || finalWeatherCondition.equals("heavy thunderstorm")
                                    || finalWeatherCondition.equals("ragged thunderstorm") || finalWeatherCondition.equals("thunderstorm with light drizzle") || finalWeatherCondition.equals("thunderstorm with drizzle") || finalWeatherCondition.equals("thunderstorm with heavy drizzle")) {
                                thunderstorm1.setVisibility(View.VISIBLE);
                            }
                            if (finalWeatherCondition.equals("light rain") || finalWeatherCondition.equals("moderate rain") || finalWeatherCondition.equals("heavy intensity rain") ||
                                    finalWeatherCondition.equals("very heavy rain") || finalWeatherCondition.equals("extreme rain") || finalWeatherCondition.equals("Freezing rain") || finalWeatherCondition.equals("light intensity shower rain")
                                    || finalWeatherCondition.equals("shower rain") || finalWeatherCondition.equals("heavy intensity shower rain") || finalWeatherCondition.equals("ragged shower rain")) {
                                if (hourOfDay >= 0 && hourOfDay < 4) {
                                    nightrain1.setVisibility(View.VISIBLE);
                                } else if (hourOfDay >= 6 && hourOfDay < 18) {
                                    sunrain1.setVisibility(View.VISIBLE);
                                } else {
                                    nightrain1.setVisibility(View.VISIBLE);
                                }

                            }

                        });
                    }
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(Homepage.this,
                                "Failed to get weather data. Error code: " + response.code(),
                                Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with getting the location
                getLocation();
            } else {
                // Permission denied, show a message or take appropriate action
                Toast.makeText(this, "Location permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }




}



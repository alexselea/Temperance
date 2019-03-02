package com.temperance.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivityAddict extends AppCompatActivity {
    private static final int REQUEST_PHONE_CALL = 1;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView welcome;
    private TextView sobrietyText;
    private TextView helpme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        welcome = (TextView)findViewById(R.id.profileWelcome);
        sobrietyText = (TextView)findViewById(R.id.sobriety);
        user = mAuth.getCurrentUser();
        helpme = (TextView)findViewById(R.id.helpme);



        if (user != null ) {
            String emaill = user.getEmail();
            String uid = user.getUid();
            welcome.setText(emaill);
        }

        welcome.setText("Hello " + welcome.getText());

        sobrietyText.setText("Congratulations! You are 100 days sober");

        helpme.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //get nr de telefon sponsor and call
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0727647655"));
                if (ContextCompat.checkSelfPermission(ProfileActivityAddict.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProfileActivityAddict.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                } else {
                    startActivity(callIntent);
                }

            }
        });

    }

}

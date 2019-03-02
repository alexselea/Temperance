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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivitySponsor extends AppCompatActivity {
    private final static int REQUEST_PHONE_CALL = 1;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView email;
    private TextView statusAddict;
    private TextView callAddict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile_sponsor);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        email = (TextView)findViewById(R.id.profileWelcome);
        statusAddict = (TextView) findViewById(R.id.statuscheck);
        callAddict = (TextView) findViewById(R.id.calladdict);

        if (user != null) {
            String username = user.getEmail();
        }

        callAddict.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0753636760"));
                if (ContextCompat.checkSelfPermission(ProfileActivitySponsor.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ProfileActivitySponsor.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                } else {
                    startActivity(callIntent);
                }
            }
        });
    }
}

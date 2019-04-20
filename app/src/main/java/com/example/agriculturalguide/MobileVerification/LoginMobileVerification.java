// Copyright (C) 2018 INTUZ.

// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
// the following conditions:

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
// ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
// THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

package com.example.agriculturalguide.MobileVerification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.agriculturalguide.LoginActivity;
import com.example.agriculturalguide.R;
import com.example.agriculturalguide.RegisterActivity;
import com.example.agriculturalguide.SensorStatusActivity;
import com.phonenumberui.PhoneNumberActivity;

public class LoginMobileVerification extends AppCompatActivity {
    public static String mobileNumber = "123";
    private Button btnVerify;
    private static final int REQUEST_PHONE_VERIFICATION = 1080;

    SharedPreferences prefs = null;

    Button join_us;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_welcome);


        join_us =(Button) findViewById(R.id.join_us);
        login =(TextView) findViewById(R.id.login);

        join_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(LoginMobileVerification.this, PhoneNumberActivity.class);
                    //Optionally you can add toolbar title
                    intent.putExtra("TITLE", getResources().getString(R.string.app_name));
                    //Optionally you can pass phone number to populate automatically.
                    intent.putExtra("PHONE_NUMBER", "");
                    startActivityForResult(intent, REQUEST_PHONE_VERIFICATION);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginMobileVerification.this, LoginActivity.class);
                startActivity(intent);
                intent.putExtra("Verified_Number",mobileNumber);
            }
        });



        btnVerify =(Button) findViewById(R.id.btnVerify);

        //prefs = getSharedPreferences("com.demo.firebasephoneverificationdemo", MODE_PRIVATE);


        /*btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginMobileVerification.this, PhoneNumberActivity.class);
                //Optionally you can add toolbar title
                intent.putExtra("TITLE", getResources().getString(R.string.app_name));
                //Optionally you can pass phone number to populate automatically.
                intent.putExtra("PHONE_NUMBER", "");
                startActivityForResult(intent, REQUEST_PHONE_VERIFICATION);
            }
        });*/

    }

/*    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
*//*            btnVerify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginMobileVerification.this, PhoneNumberActivity.class);
                    //Optionally you can add toolbar title
                    intent.putExtra("TITLE", getResources().getString(R.string.app_name));
                    //Optionally you can pass phone number to populate automatcially.
                    intent.putExtra("PHONE_NUMBER", "");
                    startActivityForResult(intent, REQUEST_PHONE_VERIFICATION);
                }
            });
            prefs.edit().putBoolean("firstrun", false).commit();*//*
        }
        else{
            *//*Intent intent = new Intent(LoginMobileVerification.this, SensorStatusActivity.class);
            startActivity(intent);*//*
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_PHONE_VERIFICATION:
// If mobile number is verified successfully then you get your phone number to perform further operations.
                if (data != null && data.hasExtra("PHONE_NUMBER") && data.getStringExtra("PHONE_NUMBER") != null) {
                    String phoneNumber = data.getStringExtra("PHONE_NUMBER");
                    mobileNumber = phoneNumber;
                    Toast.makeText(LoginMobileVerification.this,""+mobileNumber,Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(LoginMobileVerification.this, RegisterActivity.class);
                    i.putExtra("Verified_Number",mobileNumber);
                    startActivity(i);
                    finish();
                } else {
                    // If mobile number is not verified successfully You can hendle according to your requirement.
                    Toast.makeText(LoginMobileVerification.this,getString(R.string.mobile_verification_fails), Toast.LENGTH_SHORT);
                }
                break;
        }
    }

   /* public String getMobileNumber(){
        return mobileNumber;
    }*/
}

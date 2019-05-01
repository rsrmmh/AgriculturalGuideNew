package com.example.agriculturalguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriculturalguide.MainDashBoard.DashboardMainActivity;
import com.example.agriculturalguide.MobileVerification.LoginMobileVerification;

public class LoginActivity extends AppCompatActivity {

    TextView back;
    EditText phoneNo;
    String getName;
    Bundle extras;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        back =(TextView) findViewById(R.id.back);
        login=(Button) findViewById(R.id.btn_login);
/*
        if(extras==null) mobileNum="123";
        else extras=getIntent().getExtras();

        mobileNum=extras.getString("Verified_Number");*/

        phoneNo=(EditText) findViewById(R.id.edtPhoneNo);
        //phoneNo.setText(mobileNum);

        final Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        if(bd != null)
        {
            getName = (String) bd.get("Verified_Number");

            phoneNo.setText(getName);
        }else{
            if(LoginMobileVerification.mobileNumber!=null) phoneNo.setText(LoginMobileVerification.mobileNumber);
            else phoneNo.setText("+8801822996661");

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(LoginActivity.this, DashboardMainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}

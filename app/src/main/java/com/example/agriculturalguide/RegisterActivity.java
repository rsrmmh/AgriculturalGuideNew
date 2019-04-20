package com.example.agriculturalguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriculturalguide.MobileVerification.LoginMobileVerification;

public class RegisterActivity extends AppCompatActivity {

    TextView back;
    EditText phoneNo;
    String getName;
    Bundle extras;
    Button signUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        back =(TextView) findViewById(R.id.back);
        phoneNo = (EditText) findViewById(R.id.edtPhoneNo);
        signUp = (Button) findViewById(R.id.btnSignUp);

        final Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        if(bd != null)
        {
            getName = (String) bd.get("Verified_Number");
            phoneNo.setText(getName);
        }


        /*if(extras==null) mobileNum="123";
        else extras=getIntent().getExtras();*/

/*        mobileNum=extras.getString("Verified_Number");

        phoneNo.setText(mobileNum);*/

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = getIntent();
                Bundle bd = intent.getExtras();

                if(bd != null)
                {
                    getName = (String) bd.get("Verified_Number");
                    Intent i =new Intent(RegisterActivity.this,LoginActivity.class);

                    i.putExtra("Verified_Number",getName);
                    Toast.makeText(RegisterActivity.this,"Welcome! Please Sign In ",Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    finish();
                }else{
                    getName=phoneNo.getText().toString();
                    Intent i =new Intent(RegisterActivity.this,LoginActivity.class);

                    i.putExtra("Verified_Number",getName);
                    startActivity(i);
                    Toast.makeText(RegisterActivity.this,"Welcome! Please Sign In ",Toast.LENGTH_SHORT).show();
                    finish();
                }



            }
        });

    }
}

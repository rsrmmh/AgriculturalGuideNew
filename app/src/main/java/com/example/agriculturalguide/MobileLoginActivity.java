package com.example.agriculturalguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.agriculturalguide.LoginReg.SignIn;
import com.google.firebase.auth.FirebaseAuth;

public class MobileLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth auth = FirebaseAuth.getInstance();
/*        if(auth.getCurrentUser()!=null){
            if(FirebaseAuth.getInstance().getCurrentUser().getName().isEmpty()){
                startActivity(new Intent(this, SignInMobile.class)
                .putExtra("phone",FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty())
                );
                finish();
            }
            else{
                startActivityForResult(AuthUI.getInstance().);
            }
        }*/
    }
}

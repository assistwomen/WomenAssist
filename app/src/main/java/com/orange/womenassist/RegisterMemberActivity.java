package com.orange.womenassist;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterMemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_member);
        getSupportActionBar().hide();
    }
}
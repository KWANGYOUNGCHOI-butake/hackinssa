package com.kwang0.hackinssa.main;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kwang0.hackinssa.R

class MainActivity: AppCompatActivity() {

    lateinit var bnv: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnv = findViewById(R.id.main_bnv);
    }
}

package com.ericcode.vtoex.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ericcode.vtoex.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

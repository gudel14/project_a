package com.example.project_a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_)

        Handler().postDelayed({
            val pindah = Intent(this, LoginActivity::class.java)
            startActivity(pindah)
            finish()
        },4000
        )
    }
}
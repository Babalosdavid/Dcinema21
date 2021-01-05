package com.david_422017001.d_cinema_21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.david_422017001.d_cinema_21.onboarding.OnboardingOneActivity

/*
Tidak ada yang spesial disini. hanya mendeklarasikan sebuah variabel
bernama handler dengan Handler sebagai tipe data.
* */

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this@SplashScreenActivity, OnboardingOneActivity::class.java)
            startActivity(intent)
            finish()

        }, 5000)
    }
}
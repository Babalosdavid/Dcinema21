package com.david_422017001.d_cinema_21.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.david_422017001.d_cinema_21.R
import com.david_422017001.d_cinema_21.sign.signin.SignInActivity

class OnboardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)

        val btn_lanjut = findViewById(R.id.btn_lanjut) as Button
        btn_lanjut.setOnClickListener{
            var intent = Intent(this@OnboardingTwoActivity, OnboardingThreeActivity::class.java)
            startActivity(intent)
        }

        val btn_lewati = findViewById(R.id.btn_lewati) as Button
        btn_lewati.setOnClickListener{
            finishAffinity()
            var intent = Intent(this@OnboardingTwoActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
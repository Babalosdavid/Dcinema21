package com.david_422017001.d_cinema_21.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.david_422017001.d_cinema_21.R
import com.david_422017001.d_cinema_21.sign.signin.SignInActivity

class OnboardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        val btn_mulai = findViewById(R.id.btn_mulai) as Button
        btn_mulai.setOnClickListener{
            finishAffinity()
            var intent = Intent(this@OnboardingThreeActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
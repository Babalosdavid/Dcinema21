package com.david_422017001.d_cinema_21.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.david_422017001.d_cinema_21.R
import com.david_422017001.d_cinema_21.sign.signin.SignInActivity
import com.david_422017001.d_cinema_21.utils.Preferences

class OnboardingOneActivity : AppCompatActivity() {
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)

        preference = Preferences(this)

        if(preference.getValues("onboarding").equals("1")) {
            finishAffinity()
            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        val btn_lanjut = findViewById(R.id.btn_lanjut) as Button
        btn_lanjut.setOnClickListener{
            var intent = Intent(this@OnboardingOneActivity, OnboardingTwoActivity::class.java)
            startActivity(intent)
        }

        val btn_lewati = findViewById(R.id.btn_lewati) as Button
        btn_lewati.setOnClickListener{
            preference.SetValues("onboarding", "1")
            finishAffinity()
            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
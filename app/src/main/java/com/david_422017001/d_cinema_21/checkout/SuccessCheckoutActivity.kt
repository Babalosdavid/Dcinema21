package com.david_422017001.d_cinema_21.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.david_422017001.d_cinema_21.R
import com.david_422017001.d_cinema_21.home.HomeActivity
import com.david_422017001.d_cinema_21.home.tiket.TiketActivity
import com.david_422017001.d_cinema_21.sign.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_sign_up_photoscreen.*
import kotlinx.android.synthetic.main.activity_success_checkout.*

class SuccessCheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_checkout)

        btn_home.setOnClickListener{
            finishAffinity()

            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btn_tiket.setOnClickListener {
            var intent = Intent(this, TiketActivity::class.java)
            startActivity(intent)
        }
    }
}
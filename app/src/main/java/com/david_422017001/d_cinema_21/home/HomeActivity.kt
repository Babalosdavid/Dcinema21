package com.david_422017001.d_cinema_21.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.david_422017001.d_cinema_21.R
import com.david_422017001.d_cinema_21.home.dashboard.DashboardFragment
import com.david_422017001.d_cinema_21.home.setting.SettingFragment
import com.david_422017001.d_cinema_21.home.tiket.TiketFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    //val iv_menu1 = findViewById<ImageView>(R.id.iv_menu1)
    //val iv_menu2 = findViewById<ImageView>(R.id.iv_menu2)
   // val iv_menu3 = findViewById<ImageView>(R.id.iv_menu3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome =
            DashboardFragment()
        val fragmentTiket =
            TiketFragment()
        val fragmentSetting =
            SettingFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener {
            setFragment(fragmentHome)
            changeIcon(iv_menu1, R.drawable.home_active)
            changeIcon(iv_menu2, R.drawable.my_ticket_ic)
            changeIcon(iv_menu3, R.drawable.account_ic)
        }

        iv_menu2.setOnClickListener {
            setFragment(fragmentTiket)
            changeIcon(iv_menu1, R.drawable.home_ic)
            changeIcon(iv_menu2, R.drawable.ticket_active)
            changeIcon(iv_menu3, R.drawable.account_ic)
        }

        iv_menu3.setOnClickListener {
            setFragment(fragmentSetting)
            changeIcon(iv_menu1, R.drawable.home_ic)
            changeIcon(iv_menu2, R.drawable.my_ticket_ic)
            changeIcon(iv_menu3, R.drawable.account_active)
        }
    }

    public fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmenTransaction = fragmentManager.beginTransaction()
        fragmenTransaction.replace(R.id.layout_frame, fragment)
        fragmenTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int : Int){
        imageView.setImageResource(int)
    }
}
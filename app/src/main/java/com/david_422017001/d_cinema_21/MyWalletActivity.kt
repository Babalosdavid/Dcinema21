package com.david_422017001.d_cinema_21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.david_422017001.d_cinema_21.utils.Preferences
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_my_wallet.*
import java.text.NumberFormat
import java.util.*

class MyWalletActivity : AppCompatActivity() {
    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("User")

        currency(preferences.getValues("saldo")!!.toDouble(),tv_saldo)
    }

    private fun currency(harga : Double, textview : TextView){
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textview.setText(format.format(harga))
    }
}
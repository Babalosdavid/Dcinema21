package com.david_422017001.d_cinema_21.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.david_422017001.d_cinema_21.R
import com.david_422017001.d_cinema_21.model.Checkout
import com.david_422017001.d_cinema_21.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total:Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for(c in dataList.indices){
            total += dataList[c].harga!!.toInt()
        }

        dataList.add(Checkout("Total harus bayar", total.toString()))

        rv_checkout.layoutManager = LinearLayoutManager(this)
        rv_checkout.adapter = CheckoutAdapter(dataList){

        }

        btn_home.setOnClickListener {
            var intent = Intent(this, SuccessCheckoutActivity::class.java)
            startActivity(intent)
        }

        btn_batal.setOnClickListener {
            var intent = Intent(this@CheckoutActivity, SeatActivity::class.java)
            startActivity(intent)
        }
    }
}
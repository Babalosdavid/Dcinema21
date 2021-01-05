package com.david_422017001.d_cinema_21.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.david_422017001.d_cinema_21.DetailActivity
import com.david_422017001.d_cinema_21.R
import com.david_422017001.d_cinema_21.model.Checkout
import com.david_422017001.d_cinema_21.model.Film
import kotlinx.android.synthetic.main.activity_seat.*

class SeatActivity : AppCompatActivity() {

    var statusC5:Boolean = false
    var statusC6:Boolean = false
    var total:Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)

        val data = intent.getParcelableExtra<Film>("data")
        if (data != null) {
            tv_kursi.text = data.judul
        }

        c5.setOnClickListener {
            if(statusC5){
                c5.setImageResource(R.drawable.ic_empty_seat)
                statusC5 = false
                total -= 1
                beliTiket(total)
            } else {
                c5.setImageResource(R.drawable.ic_your_seat)
                statusC5 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C5", "70000")
                dataList.add(data)
            }
        }

        c6.setOnClickListener {
            if(statusC6){
                c6.setImageResource(R.drawable.ic_empty_seat)
                statusC6 = false
                total -= 1
                beliTiket(total)
            } else {
                c6.setImageResource(R.drawable.ic_your_seat)
                statusC6 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C6", "70000")
                dataList.add(data)
            }
        }

        btn_kembali.setOnClickListener {
            var intent = Intent(this@SeatActivity, DetailActivity::class.java)
            startActivity(intent)
        }

        btn_home.setOnClickListener{
            var intent = Intent(this, CheckoutActivity::class.java).putExtra("data", dataList)
            startActivity(intent)
        }
    }

    private fun beliTiket(total: Int) {
        if(total == 0){
            btn_home.setText("Beli TIket")
            btn_home.visibility = View.INVISIBLE
        } else {
            btn_home.setText("Beli TIket ("+total+")")
            btn_home.visibility = View.VISIBLE
        }
    }
}
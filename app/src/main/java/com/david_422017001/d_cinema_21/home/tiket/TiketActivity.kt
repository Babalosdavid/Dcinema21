package com.david_422017001.d_cinema_21.home.tiket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.david_422017001.d_cinema_21.R
import com.david_422017001.d_cinema_21.model.Checkout
import com.david_422017001.d_cinema_21.model.Film
import kotlinx.android.synthetic.main.activity_tiket.*

class TiketActivity : AppCompatActivity() {
    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        var data = intent.getParcelableExtra<Film>("data")

        if (data != null) {
            tv_title.text = data.judul
        }
        if (data != null) {
            tv_genre.text = data.genre
        }
        if (data != null) {
            tv_rate.text = data.rating
        }

        if (data != null) {
            Glide.with(this)
                .load(data.poster)
                .into(iv_poster_image)
        }

        rc_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C5", ""))
        dataList.add(Checkout("C6", ""))

        rc_checkout.adapter = TiketAdapter(dataList){

        }
    }
}
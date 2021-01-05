package com.david_422017001.d_cinema_21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.david_422017001.d_cinema_21.checkout.SeatActivity
import com.david_422017001.d_cinema_21.home.HomeActivity
import com.david_422017001.d_cinema_21.home.dashboard.PlaysAdapter
import com.david_422017001.d_cinema_21.model.Film
import com.david_422017001.d_cinema_21.model.Plays
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Film>("data")

        if (data != null) {
            mDatabase = FirebaseDatabase.getInstance().getReference("Film")
                .child(data.judul.toString())
                .child("play")
        }

        if (data != null) {
            tv_kursi.text = data.judul
        }
        if (data != null) {
            tv_genre.text = data.genre
        }
        if (data != null) {
            tv_desc.text = data.desc
        }
        if (data != null) {
            tv_rate.text = data.rating
        }

        if (data != null) {
            Glide.with(this)
                .load(data.poster)
                .into(iv_poster)
        }

        rv_who_playing.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btn_pilih_bangku.setOnClickListener {
            val intent = Intent(this@DetailActivity, SeatActivity::class.java).putExtra("data", data)
            startActivity(intent)
        }

        btn_kembali.setOnClickListener {
            var intent = Intent(this@DetailActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+error.message, Toast.LENGTH_LONG)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for(getdataSnapshot in snapshot.children){
                    var Film = getdataSnapshot.getValue(Plays::class.java)
                    dataList.add(Film!!)
                }

                rv_who_playing.adapter = PlaysAdapter(dataList){

                }
            }

        })
    }
}
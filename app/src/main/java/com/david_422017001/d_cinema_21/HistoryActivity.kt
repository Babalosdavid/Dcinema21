package com.david_422017001.d_cinema_21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.david_422017001.d_cinema_21.home.dashboard.ComingSoonAdapter
import com.david_422017001.d_cinema_21.home.tiket.TiketActivity
import com.david_422017001.d_cinema_21.home.tiket.TiketFragment
import com.david_422017001.d_cinema_21.model.Film
import com.david_422017001.d_cinema_21.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_history.rc_tiket
import java.util.ArrayList

class HistoryActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        rc_tiket.layoutManager = LinearLayoutManager(this)
        getData()

        btn_kembali.setOnClickListener {
            var back = Intent(this@HistoryActivity, TiketFragment::class.java)
            startActivity(back)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HistoryActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for(getdataSnapshot in snapshot.children){
                    val film = getdataSnapshot.getValue(Film::class.java)
                    dataList.add(film!!)
                }

                rc_tiket.adapter = ComingSoonAdapter(dataList){
                    var intent = Intent(this@HistoryActivity, TiketActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                tv_total.setText("${dataList.size} Movies")
            }

        })
    }
}
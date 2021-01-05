package com.david_422017001.d_cinema_21.sign.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.david_422017001.d_cinema_21.R
import com.david_422017001.d_cinema_21.home.HomeActivity
import com.david_422017001.d_cinema_21.sign.signup.SignUpActivity
import com.david_422017001.d_cinema_21.utils.Preferences
import com.google.firebase.database.*

class SignInActivity : AppCompatActivity() {
    lateinit var iUsername:String
    lateinit var iPassword:String

    lateinit var mDatabase: DatabaseReference
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        preference.SetValues("onboarding", "1")

        if(preference.getValues("status").equals("1")){
            finishAffinity()

            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        val btn_masuk = findViewById<Button>(R.id.btn_masuk)
        btn_masuk.setOnClickListener{
            val et_username = findViewById<EditText>(R.id.et_username)
            val et_password = findViewById<EditText>(R.id.et_password)

            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if(iUsername.equals("")){
                et_username.error = "Silahkan input username anda!"
                et_username.requestFocus()
            } else if(iPassword.equals("")){
                et_password.error = "Silahkan input password anda!"
                et_password.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }

        val btn_daftar = findViewById<Button>(R.id.btn_daftar)
        btn_daftar.setOnClickListener {
            var goSignUp = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(goSignUp)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if(user == null){
                    Toast.makeText(this@SignInActivity, "User Tidak Ditemukan!",
                        Toast.LENGTH_LONG).show()
                } else {
                    if(user.password.equals(iPassword)) {
                        preference.SetValues("nama", user.nama.toString())
                        preference.SetValues("user", user.username.toString())
                        preference.SetValues("url", user.url.toString())
                        preference.SetValues("email", user.email.toString())
                        preference.SetValues("saldo", user.saldo.toString())
                        preference.SetValues("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password anda salah!",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}
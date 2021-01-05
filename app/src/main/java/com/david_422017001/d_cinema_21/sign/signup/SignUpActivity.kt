package com.david_422017001.d_cinema_21.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.david_422017001.d_cinema_21.R
import com.david_422017001.d_cinema_21.sign.signin.SignInActivity
import com.david_422017001.d_cinema_21.sign.signin.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    lateinit var mDatabaseReference : DatabaseReference
    lateinit var mFirebaseInstance : FirebaseDatabase
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        val btn_lanjutkan = findViewById<Button>(R.id.btn_nanti)
        btn_lanjutkan.setOnClickListener{
            val et_new_username = findViewById<EditText>(R.id.et_new_username)
            val et_new_password = findViewById<EditText>(R.id.et_new_password)
            val et_nama = findViewById<EditText>(R.id.et_nama)
            val et_email = findViewById<EditText>(R.id.et_email)

            sUsername = et_new_username.text.toString()
            sPassword = et_new_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if(sUsername.equals("")){
                et_new_username.error = "Silahkan isi username anda"
                et_new_username.requestFocus()
            } else if(sPassword.equals("")){
                et_new_password.error = "Silahkan isi password anda"
                et_new_password.requestFocus()
            } else if(sNama.equals("")){
                et_nama.error = "Silahkan isi password anda"
                et_nama.requestFocus()
            } else if(sEmail.equals("")){
                et_email.error = "Silahkan isi password anda"
                et_email.requestFocus()
            } else{
                saveUsername(sUsername, sPassword, sNama, sEmail)
            }

        }

        btn_kembali.setOnClickListener {
            var intent = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        var user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if(sUsername != null){
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity,""+databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if(user == null)
                {
                    mDatabaseReference.child(sUsername).setValue(data)

                    var goPhotoScreen = Intent(this@SignUpActivity, SignUpPhotoscreenActivity::class.java).putExtra("nama", data?.nama)
                    startActivity(goPhotoScreen)
                }else{
                    Toast.makeText(this@SignUpActivity,"User sudah digunakan", Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}
package com.example.project_a

import android.content.Context
import com.example.project_a.Retrofit.API
import com.example.project_a.Retrofit.APIRespon
import com.example.project_a.Retrofit.RetrofitClient
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.daftar_activity.*
import kotlinx.android.synthetic.main.login_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences : SharedPreferences
    var isRemembered = false

    //Pemesanan Variabel
    lateinit var myAPI: API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)

        if (isRemembered) {
            val intent = Intent(this, Dashboard_Activity::class.java)
            startActivity(intent)
            finish()
        }


        //Inisialisasi variabel
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(API::class.java)


        //Metode tombol register
        btn_daftar.setOnClickListener {
            startActivity(Intent(this, Daftar_Activity::class.java))
        }

        //metode tombol masuk
        btn_masuk.setOnClickListener {

            if (log_nrp.text.toString().isEmpty()) {
                log_nrp.setError("Masukkan NRP")
                log_nrp.requestFocus()
                return@setOnClickListener
            }
            if (log_pass.text.toString().isEmpty()) {
                log_pass.setError("Masukkan Password")
                log_pass.requestFocus()
                return@setOnClickListener
            }
            if (!checkBox.isChecked){
                checkBox.setError("Belum Dapat Login")
                return@setOnClickListener
            }

            ceklogin (log_nrp.text.toString(), log_pass.text.toString())
            val nama = log_nrp.text.toString()
            val pass = log_pass.text.toString()
            val checked = checkBox.isChecked


            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("NAME", nama)
            editor.putString("PASS", pass)
            editor.putBoolean("CHECKBOX", checked)
            editor.apply()


        }
    }

    private fun ceklogin(nrp: String, pass: String) {
        myAPI.loginUser(nrp, pass)
            .enqueue(object :Callback<APIRespon>{
                override fun onFailure(call: Call<APIRespon>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Periksa Kembali Jaringan/NRP & Password Anda", Toast.LENGTH_SHORT).show()
                    val editor : SharedPreferences.Editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()
                }

                override fun onResponse(call: Call<APIRespon>, response: Response<APIRespon>) {
                    // Toast.makeText(this@LoginActivity, response.body()!!.error_pesan, Toast.LENGTH_SHORT).show()
                    if (response.body()!!.error) {
                        Toast.makeText(this@LoginActivity, response.body()!!.error_pesan, Toast.LENGTH_SHORT).show()
                    } else {
                        intent= Intent(applicationContext, Dashboard_Activity::class.java)
                        startActivity(intent)
                        Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        finish()
                        }
                    }
            })
    }
}
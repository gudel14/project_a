package com.example.project_a

import android.content.Intent
import com.example.project_a.Retrofit.API
import com.example.project_a.Retrofit.RetrofitClient
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.daftar_activity.*

class Daftar_Activity : AppCompatActivity() {

    // pembuatan Variabel
    lateinit var myAPI: API
    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftar_activity)

        // inisialisasi variabel yang dibuat
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(API::class.java)


        //Metode kembali ke login
        regis_btn_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Metode tombol daftar

        regis_btn_daftar.setOnClickListener {

            if (regis_nrp.text.toString().isEmpty()) {
                regis_nrp.setError("Masukkan NRP")
                regis_nrp.requestFocus()
                return@setOnClickListener
            }

            if (regis_nrp.text.toString().length < 7) {
                regis_nrp.setError("NRP anda salah")
                regis_nrp.requestFocus()
                return@setOnClickListener
            }
            if (regis_nrp.text.toString().length > 7) {
                regis_nrp.setError("NRP anda salah")
                regis_nrp.requestFocus()
                return@setOnClickListener
            }
            if (regis_nama.text.toString().isEmpty()) {
                regis_nama.setError("Masukkan Nama")
                regis_nama.requestFocus()
                return@setOnClickListener
            }

            if (regis_pass.text.toString().isEmpty()) {
                regis_pass.setError("Masukkan Password")
                regis_pass.requestFocus()
                return@setOnClickListener
            }
            if (regis_pass.text.toString().length < 8) {
                regis_pass.setError("Password minimal 8 karakter")
                regis_pass.requestFocus()
                return@setOnClickListener
            }

            if (!regis_con_pass.text.toString().equals(regis_pass.text.toString())) {
                regis_con_pass.setError("Password tidak sama")
                regis_con_pass.requestFocus()
                return@setOnClickListener
            }

            //Menjalankan metode untuk simpan data register
            registerData(
                regis_nrp.text.toString(),
                regis_nama.text.toString(),
                regis_pass.text.toString()
            )

        }

    }

    private fun registerData(nrp: String, nama: String, pass: String) {
        compositeDisposable.add(myAPI.registeruser(nrp, nama, pass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { pesan ->
                Toast.makeText(this@Daftar_Activity, pesan, Toast.LENGTH_LONG).show()
            })
        Kosongkan_teks()
    }
    //kosongkan form
    private fun Kosongkan_teks(){
        regis_nrp.setText("")
        regis_nama.setText("")
        regis_pass.setText("")
        regis_con_pass.setText("")
        regis_nrp.requestFocus()
    }


    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}
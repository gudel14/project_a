package com.example.project_a.p2h

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_push_cek.*

class PushCek : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_cek)

        var oldpompa = getIntent().getStringExtra("oldcekpompa")
        var oldshift = getIntent().getStringExtra("oldcekshift")
        var oldnama = getIntent().getStringExtra("oldceknama")
        var oldhm = getIntent().getStringExtra("oldcekhm")
        var oldtanggal = getIntent().getStringExtra("oldcektanggal")




        pushcekPompa.setText(oldpompa)
        pushcekShift.setText(oldshift)
        pushcekNama.setText(oldnama)
        pushcekHM.setText(oldhm)
        pushcekTanggal.setText(oldtanggal)


        buttoncek_push.setOnClickListener {
                        addWp()
//                        resetdata()
        }
        buttoncek_lihat.setOnClickListener {
            startActivity(Intent(this,Show_cek::class.java))
        }
    }

    private fun addWp() {
        val pompa = pushcekPompa?.getText().toString().trim(){ it <= ' ' }
        val shift = pushcekShift?.getText().toString().trim(){ it <= ' ' }
        val nama = pushcekNama?.getText().toString().trim(){ it <= ' ' }
        val hm = pushcekHM?.getText().toString().trim(){ it <= ' ' }
        val tanggal = pushcekTanggal?.getText().toString().trim(){ it <= ' ' }
//        val keterangan = pushTextKeterangan?.getText().toString().trim(){ it <= ' ' }


        lateinit var loading: ProgressDialog

        class Addcek : AsyncTask<Void, Void, String>() {
            override fun onPreExecute() {
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@PushCek, "Menambahkan Ke Database", "Sedang Mengunggah", false, false)
            }

            override fun onPostExecute(s: String) {
                super.onPostExecute(s)
                loading.dismiss()
                Toast.makeText ( this@PushCek, s, Toast.LENGTH_LONG).show()
            }

            override fun doInBackground(vararg v: Void): String {
                val params = HashMap<String, String?>()
                params[RetrofitClient.KEY_CEK_pompa] = pompa
                params[RetrofitClient.KEY_CEK_shift] = shift
                params[RetrofitClient.KEY_CEK_nama] = nama
                params[RetrofitClient.KEY_CEK_hm] = hm
                params[RetrofitClient.KEY_CEK_tanggal] = tanggal
//                params[RetrofitClient.KEY_WP_keterangan] = keterangan

                val rh = RequestHandler()
                return rh.sendPostRequest(RetrofitClient.urladcek, params)
            }
        }

        val aw = Addcek()
        aw.execute()
    }
}
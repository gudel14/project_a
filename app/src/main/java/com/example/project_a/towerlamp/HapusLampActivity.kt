package com.example.project_a.towerlamp

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_hapus_lamp.*
import org.json.JSONException
import org.json.JSONObject

class HapusLampActivity : AppCompatActivity(), View.OnClickListener {

    private var lamp : TextView?=null
    private var shift : TextView?=null
    private var status : TextView?=null
    private var hm : TextView?=null
    private var fuel : TextView?=null
    private var tanggal : TextView?=null
    private var keterangan : TextView?=null

    private var hapus: Button?=null
    private var id:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hapus_lamp)

        val intent=intent

        id= intent.getStringExtra(RetrofitClient.TAG_TL_ID)

        lamp    =findViewById(R.id.hapustl_Lamp)as TextView?
        shift   =findViewById(R.id.hapustl_shift)as TextView?
        status  =findViewById(R.id.hapustl_status)as TextView?
        hm      =findViewById(R.id.hapustl_hm)as TextView?
        fuel    =findViewById(R.id.hapustl_fuel)as TextView?
        tanggal =findViewById(R.id.hapustl_tanggal) as TextView?
//        keterangan =findViewById(R.id.hapus_keterangan)as TextView?

        hapus   = findViewById(R.id.hapustl_btnhapus)as Button
        hapus!!.setOnClickListener(this)
        TampilLamp()

    }

    private fun TampilLamp() {
        class getTampil : AsyncTask<Void, Void, String>() {
            lateinit var loading: ProgressDialog
            override fun onPreExecute() {
                super.onPreExecute()
                loading =
                    ProgressDialog.show(
                        this@HapusLampActivity, "Menampilkan Data", "Sedang Menampilkan", false, false)
            }

            override fun onPostExecute(p0: String) {
                super.onPostExecute(p0)
                loading.dismiss()
                showtl(p0)
            }

            override fun doInBackground(vararg p0: Void?): String {
                val rh = RequestHandler()
                return rh.sendGetRequestParam(RetrofitClient.urltampiltl, id)
            }
        }
        val get = getTampil()
        get.execute()

    }
    private fun showtl(json: String) {
        try {
            val jsonOb= JSONObject(json)
            val result = jsonOb.getJSONArray(RetrofitClient.TL_JSON_ARRAY)
            val jo = result.getJSONObject(0)
            val lampp = jo.getString(RetrofitClient.TAG_TL_lamp)
            val shiftt = jo.getString(RetrofitClient.TAG_TL_shift)
            val statuss = jo.getString(RetrofitClient.TAG_TL_status)
            val hmm = jo.getString(RetrofitClient.TAG_TL_hm)
            val fuell = jo.getString(RetrofitClient.TAG_TL_fuel)
            val tanggall = jo.getString(RetrofitClient.TAG_TL_tanggal)
//            val keterangann = jo.getString(RetrofitClient.TAG_TL_KETERANGAN)


            lamp?.setText(lampp)
            shift?.setText(shiftt)
            status?.setText(statuss)
            hm?.setText(hmm)
            fuel?.setText(fuell)
            tanggal?.setText(tanggall)
//            keterangan?.setText(keterangann)

        }catch (e : JSONException){
            e.printStackTrace()
        }
    }
    fun hapuswp() {
        class hapuss : AsyncTask<Void, Void, String>() {
            lateinit var loading: ProgressDialog
            override fun onPreExecute() {
                super.onPreExecute()
                loading =
                    ProgressDialog.show(this@HapusLampActivity, "Menghapus Data", "Sedang Menghapus", false, false)
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                //               loading.dismiss()
                Toast.makeText(this@HapusLampActivity, result, Toast.LENGTH_LONG).show()
                finishAndRemoveTask()
            }

            override fun doInBackground(vararg params: Void?): String {
                val rh = RequestHandler()
                return rh.sendGetRequestParam(RetrofitClient.urldeletetl, id)
                //catt error : sendGetRequest -> diganti dg sendgetRequestParam (params)
            }
        }

        val hapus = hapuss()
        hapus.execute()
        finish()
    }

    private fun konfirmHapus(){
        val alertDialogBuilder= AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Anda Akan Menghapus Data?")
        alertDialogBuilder.setPositiveButton("Ya") { arg0, arg1 ->
            hapuswp()
            val i = Intent(Intent(this@HapusLampActivity, LihatLampActivity::class.java))
            startActivity(i)
            finish()
        }

        alertDialogBuilder.setNegativeButton("Tidak", object : DialogInterface.OnClickListener{
            override fun onClick(arg0: DialogInterface, arg1:Int){}

        })

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }

    override fun onClick(p0: View?) {
        if (p0=== hapustl_btnhapus) {
            konfirmHapus()
        }
    }
}
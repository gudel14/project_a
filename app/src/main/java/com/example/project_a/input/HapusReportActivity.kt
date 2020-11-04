package com.example.project_a.input

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_hapus_report.*
import org.json.JSONException
import org.json.JSONObject

class HapusReportActivity : AppCompatActivity(), View.OnClickListener {

    private var id_id : EditText?=null
    private var wp : TextView?=null
    private var shift : TextView?=null
    private var status : TextView?=null
    private var hm : TextView?=null
    private var rpm : TextView?=null
    private var fuel : TextView?=null
    private var engine : TextView?=null
    private var preasure : TextView?=null
    private var debit : TextView?=null
    private var elevasi : TextView?=null
    private var tanggal : TextView?=null
    private var keterangan : TextView?=null

    private var edit: Button?=null
    private var hapus: Button?=null

    private var id:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hapus_report)

        val intent=intent

        id=intent.getStringExtra(RetrofitClient.WP_ID)

//        id_id=findViewById(R.id.tampil_id)as EditText
        wp      =findViewById(R.id.hapus_wp)as TextView?
        shift   =findViewById(R.id.hapus_shift)as TextView?
        status  =findViewById(R.id.hapus_status)as TextView?
        hm      =findViewById(R.id.hapus_hm)as TextView?
        rpm     =findViewById(R.id.hapus_rpm)as TextView?
        fuel    =findViewById(R.id.hapus_fuel)as TextView?
        engine  =findViewById(R.id.hapus_engine)as TextView?
        preasure=findViewById(R.id.hapus_preasure)as TextView?
        debit   =findViewById(R.id.hapus_debit)as TextView?
        elevasi =findViewById(R.id.hapus_elevasi)as TextView?
        tanggal =findViewById(R.id.hapus_tanggal) as TextView?
        keterangan =findViewById(R.id.hapus_keterangan)as TextView?

        hapus   =findViewById(R.id.hapus_btnhapus)as Button


        hapus!!.setOnClickListener(this)


        getTampilWp()
    }
    fun getTampilWp (){

        class getTampil: AsyncTask<Void, Void, String>(){
            lateinit var loading: ProgressDialog
            override fun onPreExecute() {
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@HapusReportActivity, "Menampilkan Data", "Sedang Menampilkan", false,false)
            }

            override fun onPostExecute(re: String) {
                super.onPostExecute(re)
                loading.dismiss()
                showwp(re)
            }

            override fun doInBackground(vararg params: Void): String {
                val rh = RequestHandler()
                return rh.sendGetRequestParam(RetrofitClient.urlgetwp,id)
            }
        }
        val get = getTampil()
        get.execute()

    }

    private fun showwp (json:String){
        try {
////            jsonObject = JSONObject(JSON_STRING)
            val jsonObject= JSONObject(json)
            val result = jsonObject.getJSONArray(RetrofitClient.TAG_JSON_ARRAY)
            val jo = result.getJSONObject(0)
////            val idd = jo.getString(RetrofitClient.TAG_ID)
            val wpp = jo.getString(RetrofitClient.TAG_WP)
            val shiftt = jo.getString(RetrofitClient.TAG_SHIFT)
            val statuss = jo.getString(RetrofitClient.TAG_STATUS)
            val hmm = jo.getString(RetrofitClient.TAG_HM)
            val rpmm = jo.getString(RetrofitClient.TAG_RPM)
            val fuell = jo.getString(RetrofitClient.TAG_FUEL)
            val enginee = jo.getString(RetrofitClient.TAG_ENGINE)
            val preasuree = jo.getString(RetrofitClient.TAG_PREASURE)
            val debitt = jo.getString(RetrofitClient.TAG_DEBIT)
            val elevasii = jo.getString(RetrofitClient.TAG_ELEVASI)
            val tanggall = jo.getString(RetrofitClient.TAG_TANGGAL)
            val keterangann = jo.getString(RetrofitClient.TAG_KETERANGAN)

////           id_id?.setText(id)
            wp?.setText(wpp)
            shift?.setText(shiftt)
            status?.setText(statuss)
            hm?.setText(hmm)
            rpm?.setText(rpmm)
            fuel?.setText(fuell)
            engine?.setText(enginee)
            preasure?.setText(preasuree)
            debit?.setText(debitt)
            elevasi?.setText(elevasii)
            tanggal?.setText(tanggall)
            keterangan?.setText(keterangann)

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
                    ProgressDialog.show(this@HapusReportActivity, "Menghapus Data", "Sedang Menghapus", false, false)
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
 //               loading.dismiss()
                Toast.makeText(this@HapusReportActivity, result, Toast.LENGTH_LONG).show()
                finishAndRemoveTask()
            }

            override fun doInBackground(vararg params: Void?): String {
                val rh = RequestHandler()
                return rh.sendGetRequestParam(RetrofitClient.urldeletewp, id)

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
            val i = Intent(Intent(this@HapusReportActivity, ShowReportPompaActivity::class.java))
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
//        if (p0=== tampil_btnedit){
//            updatewp()
//        }
        if (p0=== hapus_btnhapus) {
            konfirmHapus()
        }
    }

}
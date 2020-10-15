package com.example.project_a.input

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_pompa.*
import kotlinx.android.synthetic.main.activity_tampil_wp.*
import kotlinx.android.synthetic.main.list_item.*
import org.jetbrains.anko.AlertDialogBuilder
import org.json.JSONException
import org.json.JSONObject

class TampilWpActivity : AppCompatActivity(), View.OnClickListener {

    private var id_id :EditText?=null
    private var wp :EditText?=null
    private var shift :EditText?=null
    private var status :EditText?=null
    private var rpm :EditText?=null
    private var hm :EditText?=null
    private var fuel :EditText?=null
    private var engine :EditText?=null
    private var preasure :EditText?=null
    private var debit :EditText?=null
    private var elevasi :EditText?=null
    private var tanggal :TextView?=null
    private var keterangan :TextView?=null

    private var edit:Button?=null
    private var hapus:Button?=null

    private var id:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampil_wp)

        val intent=intent

        id=intent.getStringExtra(RetrofitClient.WP_ID)

//        id_id=findViewById(R.id.tampil_id)as EditText
        wp=findViewById(R.id.tampil_wp)as EditText
        shift=findViewById(R.id.tampil_shift)as EditText
        status=findViewById(R.id.tampil_status)as EditText
        rpm=findViewById(R.id.tampil_rpm)as EditText
        hm=findViewById(R.id.tampil_hm)as EditText
        fuel=findViewById(R.id.tampil_fuel)as EditText
        engine=findViewById(R.id.tampil_engine)as EditText
        preasure=findViewById(R.id.tampil_preasure)as EditText
        debit=findViewById(R.id.tampil_debit)as EditText
        elevasi=findViewById(R.id.tampil_elevasi)as EditText
        tanggal=findViewById(R.id.tampil_tanggal)as TextView
        keterangan=findViewById(R.id.tampil_keterangan)as TextView

        edit=findViewById(R.id.tampil_btnedit)as Button
        hapus=findViewById(R.id.tampil_btnhapus)as Button


        edit!!.setOnClickListener(this)
        hapus!!.setOnClickListener(this)

//        id_id!!.setText(id)

        getTampilWp()
    }

    fun getTampilWp (){

        class getTampil: AsyncTask<Void, Void, String>(){
            lateinit var loading: ProgressDialog
            override fun onPreExecute() {
                super.onPreExecute()
                loading=ProgressDialog.show(this@TampilWpActivity, "Memuat Data", "Tunggu", false,false)
            }

            override fun onPostExecute(re: String) {
                super.onPostExecute(re)
                loading.dismiss()
                showwp(re)
////               Toast.makeText(this@TampilWpActivity, Toast.LENGTH_SHORT).show()
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
            val jsonObject=JSONObject(json)
            val result = jsonObject.getJSONArray(RetrofitClient.TAG_JSON_ARRAY)
            val jo = result.getJSONObject(0)
////            val idd = jo.getString(RetrofitClient.TAG_ID)
            val wpp = jo.getString(RetrofitClient.TAG_WP)
            val shiftt = jo.getString(RetrofitClient.TAG_SHIFT)
            val statuss = jo.getString(RetrofitClient.TAG_STATUS)
            val rpmm = jo.getString(RetrofitClient.TAG_RPM)
            val hmm = jo.getString(RetrofitClient.TAG_HM)
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
            rpm?.setText(rpmm)
            hm?.setText(hmm)
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
                    ProgressDialog.show(this@TampilWpActivity, "Menghapus", "Tunggu", false, false)

            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                loading.dismiss()
                Toast.makeText(this@TampilWpActivity, result, Toast.LENGTH_LONG).show()
                finish()
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
            val i = Intent(Intent(this@TampilWpActivity, TampilSemuaWpActivity::class.java))
            startActivity(i)
            finish()
        }

        alertDialogBuilder.setNegativeButton("Tidak", object :DialogInterface.OnClickListener{
                override fun onClick(arg0:DialogInterface, arg1:Int){}
            })

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
    }


    fun updatewp(){

        val update_wp = wp?.getText().toString().trim(){ it <= ' ' }
        val update_shift = shift?.getText().toString().trim(){ it <= ' ' }
        val update_status = status?.getText().toString().trim(){ it <= ' ' }
        val update_rpm = rpm?.getText().toString().trim(){ it <= ' ' }
        val update_hm = hm?.getText().toString().trim(){ it <= ' ' }
        val update_fuel = fuel?.getText().toString().trim(){ it <= ' ' }
        val update_engine = engine?.getText().toString().trim(){ it <= ' ' }
        val update_preasure = preasure?.getText().toString().trim(){ it <= ' ' }
        val update_debit = debit?.getText().toString().trim(){ it <= ' ' }
        val update_elevasi = elevasi?.getText().toString().trim(){ it <= ' ' }
        val update_keterangan = keterangan?.getText().toString().trim(){ it <= ' ' }


        class update : AsyncTask<Void, Void, String>(){
            lateinit var loading: ProgressDialog
            override fun onPreExecute() {
                super.onPreExecute()
                loading =  ProgressDialog.show(this@TampilWpActivity, "Mengubah Data", "Tunggu", false, false)
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                loading.dismiss()
                Toast.makeText(this@TampilWpActivity,result , Toast.LENGTH_LONG).show()
                val i = Intent(Intent(this@TampilWpActivity, TampilSemuaWpActivity::class.java))
                startActivity(i)
                finish()
            }

            override fun doInBackground(vararg p0: Void?): String {
                val params= HashMap<String, String?>()
                params [RetrofitClient.KEY_WP_ID] = id
                params [RetrofitClient.KEY_WP_WP]= update_wp
                params [RetrofitClient.KEY_WP_shift]=update_shift
                params [RetrofitClient.KEY_WP_status]= update_status
                params [RetrofitClient.KEY_WP_rpm]= update_rpm
                params [RetrofitClient.KEY_WP_hm]= update_hm
                params [RetrofitClient.KEY_WP_fuel]= update_fuel
                params [RetrofitClient.KEY_WP_engine]= update_engine
                params [RetrofitClient.KEY_WP_preasure]= update_preasure
                params [RetrofitClient.KEY_WP_debit]= update_debit
                params [RetrofitClient.KEY_WP_elevasi]= update_elevasi
                params [RetrofitClient.KEY_WP_keterangan]= update_keterangan

                val rh = RequestHandler()
                return rh.sendPostRequest(RetrofitClient.urlupdatewp, params)
            }
        }
        val uu = update()
        uu.execute()
    }

    private fun konfirmUpdate(){
        val alertDialogBuilder= AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Data Akan Di Ubah ?")
        alertDialogBuilder.setPositiveButton("Ya") { arg0, arg1 ->
            updatewp()
            val i = Intent(Intent(this@TampilWpActivity, TampilSemuaWpActivity::class.java))
            startActivity(i)
//            finish()
        }

        alertDialogBuilder.setNegativeButton("Tidak", object :DialogInterface.OnClickListener{
            override fun onClick(arg0:DialogInterface, arg1:Int){}
        })

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onClick(p0: View?) {
        if (p0=== tampil_btnedit){
            konfirmUpdate()
        }
        if (p0=== tampil_btnhapus) {
            konfirmHapus()
        }
    }
}




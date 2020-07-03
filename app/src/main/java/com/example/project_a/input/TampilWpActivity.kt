package com.example.project_a.input

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_tampil_wp.*
import org.json.JSONException
import org.json.JSONObject

class TampilWpActivity : AppCompatActivity(), View.OnClickListener {

    private var id_id :EditText?=null
    private var wp :EditText?=null
    private var shift :EditText?=null
    private var status :EditText?=null
    private var edit:Button?=null
    private var id:String?=null
    private var hapus:Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampil_wp)

        val intent=intent
        id=intent.getStringExtra(RetrofitClient.WP_ID)

        id_id=findViewById(R.id.tampil_id)as EditText
        wp=findViewById(R.id.tampil_wp)as EditText
        shift=findViewById(R.id.tampil_shift)as EditText
        status=findViewById(R.id.tampil_status)as EditText
        edit=findViewById(R.id.tampil_btnedit)as Button
        hapus=findViewById(R.id.tampil_btnhapus)as Button


        edit!!.setOnClickListener(this)
        hapus!!.setOnClickListener(this)
        id_id!!.setText(id)
        getTampilWp()




    }

    fun getTampilWp (){

        lateinit var loading: ProgressDialog
        class getTampil: AsyncTask<Void, Void, String>(){

            override fun onPreExecute() {
                super.onPreExecute()
                loading=ProgressDialog.show(this@TampilWpActivity, "menampilkan", "tunggu", false,false)
            }

            override fun onPostExecute(re: String) {
                super.onPostExecute(re)
                loading.dismiss()
                showwp(re)
//               Toast.makeText(this@TampilWpActivity, Toast.LENGTH_SHORT).show()
            }

            override fun doInBackground(vararg params: Void): String {
                val rh = RequestHandler()
                return rh.sendGetRequest(RetrofitClient.urlgetwp,id)
            }
        }
        val get = getTampil()
        get.execute()

    }
    private fun showwp (json:String){
        try {
//            jsonObject = JSONObject(JSON_STRING)
            val jsonObject=JSONObject(json)
            val result = jsonObject.getJSONArray(RetrofitClient.TAG_JSON_ARRAY)
            val jo = result.getJSONObject(0)
            val idd = jo.getString(RetrofitClient.TAG_ID)
            val wpp = jo.getString(RetrofitClient.TAG_WP)
            val shiftt = jo.getString(RetrofitClient.TAG_SHIFT)
            val statuss = jo.getString(RetrofitClient.TAG_STATUS)

//           id_id?.setText(id)
            wp?.setText(wpp)
            shift?.setText(shiftt)
            status?.setText(statuss)

        }catch (e : JSONException){
            e.printStackTrace()
        }
    }

    override fun onClick(p0: View?) {
        if (p0=== tampil_btnedit){

        }
        if (p0=== tampil_btnhapus) {
            hapuswp()
        }

    }
    fun hapuswp() {
        class hapuss : AsyncTask<Void, Void, String>() {

            lateinit var loading: ProgressDialog
            override fun onPreExecute() {
                super.onPreExecute()
                loading =
                    ProgressDialog.show(this@TampilWpActivity, "menghapus", "tunggu", false, false)
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                loading.dismiss()
                Toast.makeText(this@TampilWpActivity, result, Toast.LENGTH_LONG).show()

            }

            override fun doInBackground(vararg p0: Void?): String {
                val rh = RequestHandler()
                return rh.sendGetRequest(RetrofitClient.urldeletewp, id)
            }
        }
        val hapus = hapuss()
        hapus.execute()
    }
    }



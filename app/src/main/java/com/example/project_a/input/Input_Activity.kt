package com.example.project_a.input

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask

open class Input_Activity : AppCompatActivity(), View.OnClickListener {




        private var editwp:EditText?=null
        private var editstatus:EditText?=null
        private var editshift:EditText?=null
        private var btn_save:Button?=null
        private var btn_view:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_activity)



        editwp= findViewById(R.id.input_wp)as EditText
        editstatus= findViewById(R.id.input_status)as EditText
        editshift= findViewById(R.id.input_shift)as EditText
        btn_save = findViewById(R.id.btn_save)as Button
        btn_view = findViewById(R.id.btn_view)as Button

        btn_view!!.setOnClickListener(this)

        btn_save!!.setOnClickListener (this)
    }

    private fun addWp (){
        val waterpump = editwp?.getText().toString().trim(){ it <= ' ' }
        val shift = editshift?.getText().toString().trim(){ it <= ' ' }
        val status = editstatus?.getText().toString().trim(){ it <= ' ' }

        lateinit var loading:ProgressDialog
        class Addwp : AsyncTask<Void, Void, String>(){
            override fun onPreExecute(){
                super.onPreExecute()
                loading=ProgressDialog.show(this@Input_Activity, "menambahkan", "tunggu", false, false)
            }
            override fun onPostExecute (s:String){
                super.onPostExecute(s)
                loading.dismiss()
                Toast.makeText ( this@Input_Activity , s, Toast.LENGTH_SHORT).show()
            }
            override fun doInBackground (vararg v : Void): String{
                val params= HashMap<String, String?>()
                params [RetrofitClient.KEY_WP_WP]=waterpump
                params [RetrofitClient.KEY_WP_shift]=shift
                params [RetrofitClient.KEY_WP_status]=status

                val rh = RequestHandler()
                return rh.sendPostRequest(RetrofitClient.urlad,params)
            }
        }
        val aw = Addwp()
        aw.execute()
    }

    override fun onClick(v: View?) {
        if (v=== btn_save){
            addWp()
        }
        if (v === btn_view){
            startActivity(Intent(this, TampilSemuaWpActivity::class.java))
        }
    }

}
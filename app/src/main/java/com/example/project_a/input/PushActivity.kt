package com.example.project_a.input

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import com.example.project_a.pompa.Pompa
import com.example.project_a.pompa.Pompa2Activity
import com.example.project_a.pompa.database
import kotlinx.android.synthetic.main.activity_pompa.*
import kotlinx.android.synthetic.main.activity_pompa.editTextPompa
import kotlinx.android.synthetic.main.activity_pompa.editTextShift
import kotlinx.android.synthetic.main.activity_pompa.editTextStatus
import kotlinx.android.synthetic.main.activity_push.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class PushActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push)

        var oldpompa = getIntent().getStringExtra("oldpompa")
        var oldshift = getIntent().getStringExtra("oldshift")
        var oldstatus= getIntent().getStringExtra("oldstatus")


        editTextPompa.setText(oldpompa)
        editTextShift.setText(oldshift)
        editTextStatus.setText(oldstatus)


        button_push.setOnClickListener {
            addWp()

            resetdata()
        }

    }



    fun resetdata(){
        editTextShift.text.clear()
        editTextPompa.text.clear()
        editTextStatus.text.clear()
    }
    private fun addWp (){
        val waterpump = editTextPompa?.getText().toString().trim(){ it <= ' ' }
        val shift = editTextShift?.getText().toString().trim(){ it <= ' ' }
        val status = editTextStatus?.getText().toString().trim(){ it <= ' ' }
//        val hm = edithm?.getText().toString().trim(){ it <= ' ' }

        lateinit var loading: ProgressDialog
        class Addwp : AsyncTask<Void, Void, String>(){
            override fun onPreExecute(){
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@PushActivity, "menambahkan", "tunggu", false, false)
            }
            override fun onPostExecute (s:String){
                super.onPostExecute(s)
                loading.dismiss()
                Toast.makeText ( this@PushActivity, s, Toast.LENGTH_LONG).show()
            }
            override fun doInBackground (vararg v : Void): String{
                val params= HashMap<String, String?>()
                params [RetrofitClient.KEY_WP_WP]=waterpump
                params [RetrofitClient.KEY_WP_shift]=shift
                params [RetrofitClient.KEY_WP_status]=status
//                params [RetrofitClient.KEY_WP_hm]=hm


                val rh = RequestHandler()
                return rh.sendPostRequest(RetrofitClient.urlad,params)
            }
        }
        val aw = Addwp()
        aw.execute()
    }

//    override fun onClick(v: View?) {
//        if (v=== button_update){
//            addWp()

}
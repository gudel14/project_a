package com.example.project_a.input

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class PushActivity : AppCompatActivity() {

    var pushTanggal:TextView?=null
    var btnPushTanggal: Button?=null
    var cal= Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push)

        //-------------------------------------------------------
        pushTanggal=findViewById(R.id.pushTextTanggal) as TextView
        btnPushTanggal=findViewById(R.id.push_tanggal) as Button

        //-------------------------------------------------------


        var oldpompa = getIntent().getStringExtra("oldpompa")
        var oldshift = getIntent().getStringExtra("oldshift")
        var oldstatus= getIntent().getStringExtra("oldstatus")
        var oldrpm= getIntent().getStringExtra("oldrpm")
        var oldhm= getIntent().getStringExtra("oldhm")
        var oldfuel= getIntent().getStringExtra("oldfuel")
        var oldengine= getIntent().getStringExtra("oldengine")
        var oldpreasure= getIntent().getStringExtra("oldpreasure")
        var olddebit= getIntent().getStringExtra("olddebit")
        var oldelevasi= getIntent().getStringExtra("oldelevasi")
        var oldtanggal= getIntent().getStringExtra("oldtanggal")

        if (oldpompa.isNullOrBlank()) {
            button_push.isEnabled = false
        } else {
            push_tanggal.isEnabled=false

            pushTextPompa.isEnabled=false
            pushTextShift.isEnabled=false
            pushTextStatus.isEnabled=false
            pushTextRpm.isEnabled=false
            pushTextHm.isEnabled=false
            pushTextFuel.isEnabled=false
            pushTextEngine.isEnabled=false
            pushTextPreasure.isEnabled=false
            pushTextDebit.isEnabled=false
            pushTextElevasi.isEnabled=false

            pushTextPompa.setText(oldpompa)
            pushTextShift.setText(oldshift)
            pushTextStatus.setText(oldstatus)
            pushTextRpm.setText(oldrpm)
            pushTextHm.setText(oldhm)
            pushTextFuel.setText(oldfuel)
            pushTextEngine.setText(oldengine)
            pushTextPreasure.setText(oldpreasure)
            pushTextDebit.setText(olddebit)
            pushTextElevasi.setText(oldelevasi)
            pushTextTanggal.setText(oldtanggal)

            button_push.setOnClickListener {
                addWp()
                resetdata()
            }
            button_push_lihat.setOnClickListener {
                startActivity(Intent(this, ShowReportPompaActivity::class.java))
            }
        }

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                cal.set(Calendar.YEAR, p1)
                cal.set(Calendar.MONTH, p2)
                cal.set(Calendar.DAY_OF_MONTH, p3)
                updateDataInView()
            }
        }

        btnPushTanggal!!.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                DatePickerDialog(this@PushActivity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })
    }
    private fun updateDataInView() {
        val myFormat="yyyy/MM/dd"
//        val myFormat="day month year"
        val sdf= SimpleDateFormat(myFormat, Locale.US)
        pushTanggal!!.text=sdf.format(cal.getTime())
    }


    fun resetdata(){
        pushTextShift.text!!.clear()
        pushTextPompa.text!!.clear()
        pushTextStatus.text!!.clear()
        pushTextRpm.text!!.clear()
        pushTextHm.text!!.clear()
        pushTextFuel.text!!.clear()
        pushTextEngine.text!!.clear()
        pushTextPreasure.text!!.clear()
        pushTextDebit.text!!.clear()
        pushTextElevasi.text!!.clear()
        pushTextTanggal.setText("")
    }
    private fun addWp (){
        val waterpump = pushTextPompa?.getText().toString().trim(){ it <= ' ' }
        val shift = pushTextShift?.getText().toString().trim(){ it <= ' ' }
        val status = pushTextStatus?.getText().toString().trim(){ it <= ' ' }
        val rpm = pushTextRpm?.getText().toString().trim(){ it <= ' ' }
        val hm = pushTextHm?.getText().toString().trim(){ it <= ' ' }
        val fuel = pushTextFuel?.getText().toString().trim(){ it <= ' ' }
        val engine = pushTextEngine?.getText().toString().trim(){ it <= ' ' }
        val preasure = pushTextPreasure?.getText().toString().trim(){ it <= ' ' }
        val debit = pushTextDebit?.getText().toString().trim(){ it <= ' ' }
        val elevasi = pushTextElevasi?.getText().toString().trim(){ it <= ' ' }
        val tanggal = pushTextTanggal?.getText().toString().trim(){ it <= ' ' }


        lateinit var loading: ProgressDialog
        class Addwp : AsyncTask<Void, Void, String>(){
            override fun onPreExecute(){
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@PushActivity, "Menambahkan Ke Database", "Sedang Mengunggah", false, false)
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
                params [RetrofitClient.KEY_WP_rpm]=rpm
                params [RetrofitClient.KEY_WP_hm]=hm
                params [RetrofitClient.KEY_WP_fuel]=fuel
                params [RetrofitClient.KEY_WP_engine]=engine
                params [RetrofitClient.KEY_WP_preasure]=preasure
                params [RetrofitClient.KEY_WP_debit]=debit
                params [RetrofitClient.KEY_WP_elevasi]=elevasi
                params [RetrofitClient.KEY_WP_tanggal]=tanggal

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
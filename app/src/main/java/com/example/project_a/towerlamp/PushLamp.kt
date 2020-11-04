package com.example.project_a.towerlamp

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_push_lamp.*
import java.text.SimpleDateFormat
import java.util.*

class PushLamp : AppCompatActivity() {

    var pushTanggal : TextView? = null
    var btnPushTanggal: Button? = null
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_lamp)

        pushTanggal = findViewById(R.id.pushTextTanggall) as TextView
        btnPushTanggal = findViewById(R.id.push_tanggall) as Button


        var oldlamp     = intent.getStringExtra("oldlamp")
        var oldshift     = intent.getStringExtra("oldshift")
        var oldstatus    = intent.getStringExtra("oldstatus")
        var oldtanggal       = intent.getStringExtra("oldtanggal")
        var oldhm        = intent.getStringExtra("oldhm")
        var oldfuel      = intent.getStringExtra("oldfuel")

        if (oldlamp.isNullOrBlank()) {
//            tlbutton_update.isEnabled = false
        } else {
            textShiftPush.isEnabled= false
            textHmPush.isEnabled= false
            textFuelPush.isEnabled= false
            textStatusPush.isEnabled= false
            pushTextTanggall.isEnabled= false
            textLampPush.isEnabled= false

            textLampPush.setText(oldlamp)
            textShiftPush.setText(oldshift)
            textHmPush.setText(oldhm)
            textFuelPush.setText(oldfuel)
            textStatusPush.setText(oldstatus)
            pushTextTanggall.setText(oldtanggal)

            button_push.setOnClickListener {
                addTl()
//                resetdata()
            }
            button_tl_lihat.setOnClickListener {
                startActivity(Intent(this, LihatLampActivity::class.java))
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

        btnPushTanggal!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                DatePickerDialog(
                    this@PushLamp, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
    }

    private fun updateDataInView() {
        val myFormat = "yyyy/MM/dd"
//        val myFormat="day month year"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val sdff = sdf.format(cal.getTime())
        pushTanggal!!.text = sdff
    }


    private fun addTl(){

        val towerlamp = textLampPush?.getText().toString().trim(){ it <= ' ' }
        val shift = textShiftPush?.getText().toString().trim(){ it <= ' ' }
        val status = textStatusPush?.getText().toString().trim(){ it <= ' ' }
        val hm = textHmPush?.getText().toString().trim(){ it <= ' ' }
        val fuel = textFuelPush?.getText().toString().trim(){ it <= ' ' }
        val tanggal = pushTextTanggall?.getText().toString().trim(){ it <= ' ' }

        lateinit var loading: ProgressDialog

        class Addwp : AsyncTask<Void, Void, String>() {
            override fun onPreExecute() {
                super.onPreExecute()
                loading=
                    ProgressDialog.show(this@PushLamp, "Menambahkan Ke Database", "Sedang Mengunggah", false, false)
            }

            override fun onPostExecute(s: String) {
                super.onPostExecute(s)
                loading.dismiss()
                Toast.makeText ( this@PushLamp, s, Toast.LENGTH_LONG).show()
            }

            override fun doInBackground(vararg v: Void): String {
                val params = HashMap<String, String?>()
                params[RetrofitClient.KEY_TL_WP] = towerlamp
                params[RetrofitClient.KEY_TL_shift] = shift
                params[RetrofitClient.KEY_TL_status] = status
                params[RetrofitClient.KEY_TL_hm] = hm
                params[RetrofitClient.KEY_TL_fuel] = fuel
                params[RetrofitClient.KEY_TL_tanggal] = tanggal

                val rh = RequestHandler()
                return rh.sendPostRequest(RetrofitClient.urladtl, params)
            }
        }

        val aw = Addwp()
        aw.execute()
    }



}
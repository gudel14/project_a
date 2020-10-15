package com.example.project_a.towerlamp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.example.project_a.R
import kotlinx.android.synthetic.main.activity_push_lamp.*
import kotlinx.android.synthetic.main.activity_tower.*
import java.text.SimpleDateFormat
import java.util.*

class PushLamp : AppCompatActivity() {

    var pushTanggal : TextView? = null
    var btnPushTanggal: Button? = null
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_lamp)

        pushTanggal = findViewById(R.id.pushTextTanggal) as TextView
        btnPushTanggal = findViewById(R.id.push_tanggal) as Button


        var oldlamp     = intent.getStringExtra("oldlamp")
        var oldshift     = intent.getStringExtra("oldshift")
        var oldstatus    = intent.getStringExtra("oldstatus")
        var oldtanggal       = intent.getStringExtra("oldtanggal")
        var oldhm        = intent.getStringExtra("oldhm")
        var oldfuel      = intent.getStringExtra("oldfuel")

        if (oldlamp.isNullOrBlank()) {
            tlbutton_update.isEnabled = false
        } else {
            textLampPush.isEnabled= false
            textLampPush.setText(oldlamp)
            textShiftPush.setText(oldshift)
            textHmPush.setText(oldhm)
            textFuelPush.setText(oldfuel)
            textStatusPush.setText(oldstatus)
            pushTextTanggal.setText(oldtanggal)
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
}
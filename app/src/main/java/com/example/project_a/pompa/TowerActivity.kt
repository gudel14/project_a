package com.example.project_a.pompa

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project_a.R
import kotlinx.android.synthetic.main.activity_pompa.*
import kotlinx.android.synthetic.main.activity_tower.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class TowerActivity : AppCompatActivity() {

    var text_Tanggal: TextView?=null
    var tlbtn_tanggal: Button?=null
    var cal= Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tower)

        text_Tanggal=findViewById(R.id.textTanggal) as TextView
        tlbtn_tanggal=findViewById(R.id.tlbutton_tanggal) as Button


        var oldlamp     = intent.getStringExtra("oldlamp")
        var oldshift     = intent.getStringExtra("oldshift")
        var oldstatus    = intent.getStringExtra("oldstatus")
        var oldtanggal       = intent.getStringExtra("oldtanggal")
        var oldhm        = intent.getStringExtra("oldhm")
        var oldfuel      = intent.getStringExtra("oldfuel")

        if (oldlamp.isNullOrBlank()) {
            tlbutton_update.isEnabled = false
        } else {
            tlbutton_simpan.isEnabled = false
            textLamp.isEnabled= false
            textLamp.setText(oldlamp)
            textShift.setText(oldshift)
            textHm.setText(oldhm)
            textFuel.setText(oldfuel)
            textStatus.setText(oldstatus)
            textTanggal.setText(oldtanggal)
        }

        tlbutton_simpan.setOnClickListener {
            adddataTL()
             resetdata() 
        }

        tlbutton_lihat.setOnClickListener {
            startActivity<Tower2Activity>()
        }

        tlbutton_update.setOnClickListener {

            if (textTanggal.text.toString().isEmpty()) {
                textTanggal.setError("Masukkan Tanggal")
                textTanggal.requestFocus()
                return@setOnClickListener
            }
            if (textLamp.text.toString().isEmpty()) {
                editTextPompa.setError("Masukkan TL")
                editTextPompa.requestFocus()
                return@setOnClickListener
            }
            if (textShift.text.toString().isEmpty()) {
                textShift.setError("Masukkan Shift")
                textShift.requestFocus()
                return@setOnClickListener
            }
            if (textStatus.text.toString().isEmpty()) {
                textStatus.setError("Masukkan Status")
                textStatus.requestFocus()
                return@setOnClickListener
            }
            if (textHm.text.toString().isEmpty()) {
                textHm.setError("Masukkan HM")
                textHm.requestFocus()
                return@setOnClickListener
            }
            if (textFuel.text.toString().isEmpty()) {
                textFuel.setError("Masukkan Fuel TL")
                textFuel.requestFocus()
                return@setOnClickListener
            }

            database.use {
                update(
                    Tower.Tabel_Tower,
                    Tower.LAMP to   textLamp.text.toString(),
                    Tower.SHIFT to  textShift.text.toString(),
                    Tower.STATUS to textStatus.text.toString(),
                    Tower.HM to     textHm.text.toString(),
                    Tower.FUEL to   textFuel.text.toString(),
                    Tower.TANGGAL to textTanggal.text.toString()
                )
                    .whereArgs(
                        "${Tower.LAMP}={lamp}",
                        "lamp" to oldlamp!!
                    ).exec()
            }
            resetdata()
        }

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                cal.set(Calendar.YEAR, p1)
                cal.set(Calendar.MONTH, p2)
                cal.set(Calendar.DAY_OF_MONTH, p3)
                updateDataInView()
            }
        }

        tlbtn_tanggal!!.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                DatePickerDialog(this@TowerActivity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })
    }
    private fun updateDataInView() {
        val myFormat = "yyyy/MM/dd"
        val sdf= SimpleDateFormat(myFormat, Locale.US)
        text_Tanggal!!.text=sdf.format(cal.getTime())
    }


    private fun adddataTL() {
        database.use {
            insert(
                Tower.Tabel_Tower,
                Tower.LAMP to textLamp.text.toString(),
                Tower.SHIFT to textShift.text.toString(),
                Tower.STATUS to textStatus.text.toString(),
                Tower.HM to textHm.text.toString(),
                Tower.FUEL to textFuel.text.toString(),
                Tower.TANGGAL to textTanggal!!.text.toString()
            )
            toast("Data Disimpan")
        }
    }

    fun resetdata() {
        textShift.text!!.clear()
        textLamp.text!!.clear()
        textStatus.text!!.clear()
        textHm.text!!.clear()
        textFuel.text!!.clear()
        textTanggal.setText("")
    }
}
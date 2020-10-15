package com.example.project_a.pompa

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import com.example.project_a.input.Input_Activity
import com.example.project_a.input.TampilSemuaWpActivity
import kotlinx.android.synthetic.main.activity_pompa.*
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class PompaActivity : AppCompatActivity() {

    var editTanggal:TextView?=null
    var btn_tanggal:Button?=null
    var cal=Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pompa)
        //-------------------------------------

        editTanggal=findViewById(R.id.editTextTanggal) as TextView
        btn_tanggal=findViewById(R.id.button_tanggal) as Button

        //-------------------------------------

        var oldpompa     = intent.getStringExtra("oldpompa")
        var oldshift     = intent.getStringExtra("oldshift")
        var oldstatus    = intent.getStringExtra("oldstatus")
        var oldrpm       = intent.getStringExtra("oldrpm")
        var oldhm        = intent.getStringExtra("oldhm")
        var oldfuel      = intent.getStringExtra("oldfuel")
        var oldengine    = intent.getStringExtra("oldengine")
        var oldpreasure  = intent.getStringExtra("oldpreasure")
        var olddebit     = intent.getStringExtra("olddebit")
        var oldelevasi   = intent.getStringExtra("oldelevasi")
        var oldtanggal   = intent.getStringExtra("oldtanggal")

        if (oldpompa.isNullOrBlank()) {
            button_update.isEnabled = false
        } else {
            button_simpan.isEnabled = false
            editTextPompa.isEnabled= false
            editTextPompa.setText(oldpompa)
            editTextShift.setText(oldshift)
            editTextStatus.setText(oldstatus)
            editTextRpm.setText(oldrpm)
            editTextHm.setText(oldhm)
            editTextFuel.setText(oldfuel)
            editTextEngine.setText(oldengine)
            editTextPreasure.setText(oldpreasure)
            editTextDebit.setText(olddebit)
            editTextElevasi.setText(oldelevasi)
            editTextTanggal.setText(oldtanggal)
        }

        button_simpan.setOnClickListener {
            if (editTextTanggal.text.toString().isEmpty()) {
                editTextTanggal.setError("Masukkan Tanggal")
                editTextTanggal.requestFocus()
                return@setOnClickListener
            }
            if (editTextPompa.text.toString().isEmpty()) {
                editTextPompa.setError("Masukkan Pompa")
                editTextPompa.requestFocus()
                return@setOnClickListener
            }
            if (editTextShift.text.toString().isEmpty()) {
                editTextShift.setError("Masukkan Shift")
                editTextShift.requestFocus()
                return@setOnClickListener
            }
            if (editTextStatus.text.toString().isEmpty()) {
                editTextStatus.setError("Masukkan Status")
                editTextStatus.requestFocus()
                return@setOnClickListener
            }
            if (editTextRpm.text.toString().isEmpty()) {
                editTextRpm.setError("Masukkan Rpm")
                editTextRpm.requestFocus()
                return@setOnClickListener
            }
            if (editTextHm.text.toString().isEmpty()) {
                editTextHm.setError("Masukkan Rpm")
                editTextHm.requestFocus()
                return@setOnClickListener
            }
            if (editTextFuel.text.toString().isEmpty()) {
                editTextFuel.setError("Masukkan Fuel Rate")
                editTextFuel.requestFocus()
                return@setOnClickListener
            }
            if (editTextEngine.text.toString().isEmpty()) {
                editTextEngine.setError("Masukkan E. Load")
                editTextEngine.requestFocus()
                return@setOnClickListener
            }
            if (editTextPreasure.text.toString().isEmpty()) {
                editTextPreasure.setError("Masukkan P. Gauge")
                editTextPreasure.requestFocus()
                return@setOnClickListener
            }
            if (editTextDebit.text.toString().isEmpty()) {
                editTextDebit.setError("Masukkan Debit")
                editTextDebit.requestFocus()
                return@setOnClickListener
            }
            if (editTextElevasi.text.toString().isEmpty()) {
                editTextElevasi.setError("Masukkan Elevasi Sump")
                editTextElevasi.requestFocus()
                return@setOnClickListener
            }
            addDataPompa()
            resetdata()
        }
        button_lihat.setOnClickListener {
            startActivity<Pompa2Activity>()
        }

        button_update.setOnClickListener {

            if (editTextTanggal.text.toString().isEmpty()) {
                editTextTanggal.setError("Masukkan Tanggal")
                editTextTanggal.requestFocus()
                return@setOnClickListener
            }
            if (editTextPompa.text.toString().isEmpty()) {
                editTextPompa.setError("Masukkan Pompa")
                editTextPompa.requestFocus()
                return@setOnClickListener
            }
            if (editTextShift.text.toString().isEmpty()) {
                editTextShift.setError("Masukkan Shift")
                editTextShift.requestFocus()
                return@setOnClickListener
            }
            if (editTextStatus.text.toString().isEmpty()) {
                editTextStatus.setError("Masukkan Status")
                editTextStatus.requestFocus()
                return@setOnClickListener
            }
            if (editTextRpm.text.toString().isEmpty()) {
                editTextRpm.setError("Masukkan Rpm")
                editTextRpm.requestFocus()
                return@setOnClickListener
            }
            if (editTextHm.text.toString().isEmpty()) {
                editTextHm.setError("Masukkan Rpm")
                editTextHm.requestFocus()
                return@setOnClickListener
            }
            if (editTextFuel.text.toString().isEmpty()) {
                editTextFuel.setError("Masukkan Fuel Rate")
                editTextFuel.requestFocus()
                return@setOnClickListener
            }
            if (editTextEngine.text.toString().isEmpty()) {
                editTextEngine.setError("Masukkan E. Load")
                editTextEngine.requestFocus()
                return@setOnClickListener
            }
            if (editTextPreasure.text.toString().isEmpty()) {
                editTextPreasure.setError("Masukkan P. Gauge")
                editTextPreasure.requestFocus()
                return@setOnClickListener
            }
            if (editTextDebit.text.toString().isEmpty()) {
                editTextDebit.setError("Masukkan Debit")
                editTextDebit.requestFocus()
                return@setOnClickListener
            }
            if (editTextElevasi.text.toString().isEmpty()) {
                editTextElevasi.setError("Masukkan Elevasi Sump")
                editTextElevasi.requestFocus()
                return@setOnClickListener
            }

            database.use {
                update(
                    Pompa.Tabel_Pompa,
                    Pompa.POMPA to editTextPompa.text.toString(),
                    Pompa.SHIFT to editTextShift.text.toString(),
                    Pompa.STATUS to editTextStatus.text.toString(),
                    Pompa.RPM to editTextRpm.text.toString(),
                    Pompa.HM to editTextHm.text.toString(),
                    Pompa.FUEL to editTextFuel.text.toString(),
                    Pompa.ENGINE to editTextEngine.text.toString(),
                    Pompa.PREASURE to editTextPreasure.text.toString(),
                    Pompa.DEBIT to editTextDebit.text.toString(),
                    Pompa.ELEVASI to editTextElevasi.text.toString(),
                    Pompa.TANGGAL to editTextTanggal.text.toString()
                )
                    .whereArgs(
                        "${Pompa.POMPA}={pompa}",
                        "pompa" to oldpompa!!
                    ).exec()
            }
            resetdata()
//            finish()
        }

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                cal.set(Calendar.YEAR, p1)
                cal.set(Calendar.MONTH, p2)
                cal.set(Calendar.DAY_OF_MONTH, p3)
                updateDataInView()
            }
        }

        btn_tanggal!!.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                DatePickerDialog(this@PompaActivity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })
    }
    private fun updateDataInView() {
        val myFormat="yyyy/MM/dd"
        val sdf= SimpleDateFormat(myFormat, Locale.US)
        editTanggal!!.text=sdf.format(cal.getTime())
    }


    private fun addDataPompa() {
        database.use {
            insert(
                Pompa.Tabel_Pompa,
                Pompa.POMPA to editTextPompa.text.toString(),
                Pompa.SHIFT to editTextShift.text.toString(),
                Pompa.STATUS to editTextStatus.text.toString(),
                Pompa.RPM to editTextRpm.text.toString(),
                Pompa.HM to editTextHm.text.toString(),
                Pompa.FUEL to editTextFuel.text.toString(),
                Pompa.ENGINE to editTextEngine.text.toString(),
                Pompa.PREASURE to editTextPreasure.text.toString(),
                Pompa.DEBIT to editTextDebit.text.toString(),
                Pompa.ELEVASI to editTextElevasi.text.toString(),
                Pompa.TANGGAL to editTextTanggal.text.toString()
            )
            toast("Data Disimpan")
        }
    }

    fun resetdata() {
        editTextShift.text!!.clear()
        editTextPompa.text!!.clear()
        editTextStatus.text!!.clear()
        editTextRpm.text!!.clear()
        editTextHm.text!!.clear()
        editTextFuel.text!!.clear()
        editTextEngine.text!!.clear()
        editTextPreasure.text!!.clear()
        editTextDebit.text!!.clear()
        editTextElevasi.text!!.clear()
        editTextTanggal.setText("")
    }

}


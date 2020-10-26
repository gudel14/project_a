package com.example.project_a.p2h

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.example.project_a.R
import com.example.project_a.pompa.*
import kotlinx.android.synthetic.main.activity_cek_harian.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class CekHarianActivity : AppCompatActivity() {

    var cektext_Tanggal: TextView? = null
    var cekbtn_tanggal: Button? = null
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cek_harian)

        cektext_Tanggal = findViewById(R.id.cektextTanggal) as TextView
        cekbtn_tanggal = findViewById(R.id.cekbtn_tanggal) as Button


        cekbtn_kirim.setOnClickListener {
            addCek()
//            resetdata()
        }
//
        cekbtn_lihat.setOnClickListener {
            startActivity<CekCekActivity>()
        }
//
//
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                cal.set(Calendar.YEAR, p1)
                cal.set(Calendar.MONTH, p2)
                cal.set(Calendar.DAY_OF_MONTH, p3)
                updateDataInView()
            }
        }

        cekbtn_tanggal!!.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                DatePickerDialog(this@CekHarianActivity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })
    }



    private fun updateDataInView() {
        val myFormat="dd MMM yy"
        val sdf= SimpleDateFormat(myFormat, Locale.US)
        cektext_Tanggal!!.text=sdf.format(cal.getTime())
    }


    private fun addCek() {
        database.use {
            insert(
                Cek.Tabel_Cek,
                Cek.POMPA to cektextPompa.text.toString(),
                Cek.SHIFT to cektextShift.text.toString(),
                Cek.NAMA to cektextNama.text.toString(),
                Cek.HM to cektextHm.text.toString(),
                Cek.TANGGAL to cektextTanggal!!.text.toString()
            )
            toast("Data Disimpan")
        }
    }
}
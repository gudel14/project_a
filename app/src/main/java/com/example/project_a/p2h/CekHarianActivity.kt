package com.example.project_a.p2h

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project_a.R
import com.example.project_a.pompa.Cek
import com.example.project_a.pompa.database
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
        val myFormat="yyyy/MM/dd"
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
                Cek.TANGGAL to cektextTanggal!!.text.toString(),
                Cek.SPINER1  to spiner1.selectedItem.toString(),
                Cek.SPINER2  to spiner2.selectedItem.toString(),
                Cek.SPINER3  to spiner3.selectedItem.toString(),
                Cek.SPINER4  to spiner4.selectedItem.toString(),
                Cek.SPINER5  to spiner5.selectedItem.toString(),
                Cek.SPINER6  to spiner6.selectedItem.toString(),
                Cek.SPINER7  to spiner7.selectedItem.toString(),
                Cek.SPINER8  to spiner8.selectedItem.toString(),
                Cek.SPINER9  to spiner9.selectedItem.toString(),
                Cek.SPINER10 to spiner10.selectedItem.toString(),
                Cek.SPINER11 to spiner11.selectedItem.toString(),
                Cek.SPINER12 to spiner12.selectedItem.toString(),
                Cek.SPINER13 to spiner13.selectedItem.toString(),
                Cek.SPINER14 to spiner14.selectedItem.toString(),
                Cek.SPINER15 to spiner15.selectedItem.toString(),
                Cek.SPINER16 to spiner16.selectedItem.toString(),
                Cek.SPINER17 to spiner17.selectedItem.toString(),
                Cek.SPINER18 to spiner18.selectedItem.toString(),
                Cek.SPINER19 to spiner19.selectedItem.toString(),
                Cek.SPINER20 to spiner20.selectedItem.toString(),
                Cek.SPINER21 to spiner21.selectedItem.toString(),
                Cek.SPINER22 to spiner22.selectedItem.toString(),
                Cek.SPINER23 to spiner23.selectedItem.toString(),
                Cek.SPINER24 to spiner24.selectedItem.toString(),
                Cek.SPINER25 to spiner25.selectedItem.toString(),
                Cek.SPINER26 to spiner26.selectedItem.toString(),
                Cek.SPINER27 to spiner27.selectedItem.toString(),
                Cek.SPINER28 to spiner28.selectedItem.toString(),
                Cek.SPINER29 to spiner29.selectedItem.toString(),
                Cek.SPINER30 to spiner30.selectedItem.toString(),
                Cek.SPINER31 to spiner31.selectedItem.toString(),
                Cek.SPINER32 to spiner32.selectedItem.toString()
            )
            toast("Data Disimpan")
        }
    }
}
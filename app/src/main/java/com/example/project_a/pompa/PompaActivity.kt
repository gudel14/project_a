package com.example.project_a.pompa

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.project_a.R
import com.example.project_a.RequestHandler
import com.example.project_a.Retrofit.RetrofitClient
import com.example.project_a.input.Input_Activity
import com.example.project_a.input.TampilSemuaWpActivity
import kotlinx.android.synthetic.main.activity_pompa.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class PompaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pompa)

        var oldpompa = intent.getStringExtra("oldpompa")
        var oldshift = intent.getStringExtra("oldshift")
        var oldstatus = intent.getStringExtra("oldstatus")
        var oldhm = intent.getStringExtra("oldhm")

        if (oldpompa.isNullOrBlank()) {
            button_update.isEnabled = false
        } else {
            button_simpan.isEnabled = false
            editTextPompa.setText(oldpompa)
            editTextShift.setText(oldshift)
            editTextStatus.setText(oldstatus)
            editTextHm.setText(oldhm)

    }
        button_simpan.setOnClickListener {
            addDataPompa()
            resetdata()

        }
        button_lihat.setOnClickListener {
            startActivity<Pompa2Activity>()
        }

        button_update.setOnClickListener {
            database.use {
                update(
                    Pompa.Tabel_Pompa,
                    Pompa.POMPA to editTextPompa.text.toString(),
                    Pompa.SHIFT to editTextShift.text.toString(),
                    Pompa.STATUS to editTextStatus.text.toString(),
                    Pompa.HM to editTextStatus.text.toString()
                )
                    .whereArgs(
                        "${Pompa.POMPA}={pompa}",
                        "pompa" to oldpompa!!
                    ).exec()
            }
            resetdata()
        }

    }

    private fun addDataPompa() {
        database.use {
            insert(
                Pompa.Tabel_Pompa,
                Pompa.POMPA to editTextPompa.text.toString(),
                Pompa.SHIFT to editTextShift.text.toString(),
                Pompa.STATUS to editTextStatus.text.toString(),
                Pompa.HM to editTextHm.text.toString()
            )
            toast("Data Disimpan")
        }
    }

    fun resetdata() {
        editTextShift.text.clear()
        editTextPompa.text.clear()
        editTextStatus.text.clear()
        editTextHm.text.clear()
    }


}


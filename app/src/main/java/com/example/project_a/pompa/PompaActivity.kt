package com.example.project_a.pompa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project_a.R
import kotlinx.android.synthetic.main.activity_pompa.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class PompaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pompa)

        var oldpompa =intent.getStringExtra("oldpompa")
        var oldshift =intent.getStringExtra("oldshift")
        var oldstatus =intent.getStringExtra("oldstatus")

        if (oldpompa.isNullOrBlank()){
            button_update.isEnabled = false
        }else{
            button_simpan.isEnabled = false
            editTextPompa.setText(oldpompa)
            editTextShift.setText(oldshift)
            editTextStatus.setText(oldstatus)

        }
        button_simpan.setOnClickListener {
            addDataPompa ()
            resetdata()

        }
        button_lihat.setOnClickListener {
            startActivity<Pompa2Activity>()
        }

        button_update.setOnClickListener {
            database.use {
                update(Pompa.Tabel_Pompa,
                Pompa.POMPA to editTextPompa.text.toString(),
                Pompa.SHIFT to editTextShift.text.toString(),
                Pompa.STATUS to editTextStatus.text.toString())
                    .whereArgs("${Pompa.POMPA}={pompa}",
                    "pompa" to oldpompa!!
                    ).exec()
            }
            resetdata()
        }

    }

    private fun addDataPompa() {
        database.use {
            insert( Pompa.Tabel_Pompa,
            Pompa.POMPA to editTextPompa.text.toString(),
            Pompa.SHIFT to editTextShift.text.toString(),
            Pompa.STATUS to editTextStatus.text.toString()
            )
            toast("Data Disimpan")
        }
    }

    fun resetdata(){
        editTextPompa.text.clear()
        editTextShift.text.clear()
        editTextStatus.text.clear()
    }
}
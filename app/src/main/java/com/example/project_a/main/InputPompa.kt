package com.example.project_a.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project_a.R
import kotlinx.android.synthetic.main.fragment_input_wp.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast

class InputPompa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_input_wp)
        main_btnsimpan.setOnClickListener {
            addDataPompa()
        }
        main_btnlihat.setOnClickListener {
            startActivity(Intent(this, UpdatePompa::class.java))

        }
        main_btnupdate.setOnClickListener {
            startActivity(Intent(this, UpdatePompa::class.java))
            toast("Data diupdate")

        }
    }

    private fun addDataPompa() {
        database.use {
            insert(Pompa.Tabel_Pompa,
                Pompa.Pompa to inputwp_pompa.text.toString(),
                Pompa.Shift to inputwp_shift.text.toString(),
                Pompa.Status to inputwp_status.text.toString()
            )
            toast("Data berhasil tersimpan")
        }
    }
}
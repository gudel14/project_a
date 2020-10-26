package com.example.project_a.p2h

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_a.R
import com.example.project_a.pompa.Cek
import com.example.project_a.pompa.CekAdapter
import com.example.project_a.pompa.database
import kotlinx.android.synthetic.main.activity_cek2.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class CekCekActivity : AppCompatActivity() {

    private lateinit var adaptert: CekAdapter
    private var cek = ArrayList<Cek>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cek_cek)

        adaptert = CekAdapter(this, cek)

        recycless.adapter = adaptert

        getData()
        recycless.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        database.use {
            cek.clear()
            var result = select(Cek.Tabel_Cek)
            var dataCek = result.parseList(classParser<Cek>())
            cek.addAll(dataCek)
            adaptert.notifyDataSetChanged()
        }



    }
}
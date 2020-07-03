package com.example.project_a.pompa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_a.R
import kotlinx.android.synthetic.main.activity_pompa2.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class Pompa2Activity : AppCompatActivity() {

    private lateinit var adapter: RVAdapter
    private var pompa = ArrayList<Pompa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pompa2)

        adapter = RVAdapter(this, pompa)

        recycle.adapter = adapter

        getData()
        recycle.layoutManager = LinearLayoutManager(this)

        kembali.setOnClickListener{
            startActivity(Intent(this, PompaActivity::class.java))
        }

    }

    private fun getData() {
        database.use {
            pompa.clear()
            var result = select(Pompa.Tabel_Pompa)
            var dataPompa = result.parseList(classParser<Pompa>())
            pompa.addAll(dataPompa)
            adapter.notifyDataSetChanged()
        }
    }
}

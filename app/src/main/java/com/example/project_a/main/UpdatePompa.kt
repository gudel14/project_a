package com.example.project_a.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_a.R
import kotlinx.android.synthetic.main.fragment_update_wp.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class UpdatePompa : AppCompatActivity() {

    private lateinit var adapter: RVAdapter
    private var pompa = ArrayList<Pompa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_update_wp)

        adapter = RVAdapter(this, pompa)
        recycleview.adapter = adapter

        getData()
        recycleview.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        database.use {
            pompa.clear()
            var result = select(Pompa.Tabel_Pompa)
            var dataSantri = result.parseList(classParser<Pompa>())
            pompa.addAll(dataSantri)
            adapter.notifyDataSetChanged()
        }
    }
}
package com.example.project_a.pompa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_a.R
import kotlinx.android.synthetic.main.activity_pompa2.*
import kotlinx.android.synthetic.main.activity_tower2.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class Tower2Activity : AppCompatActivity() {

    private lateinit var adaptert: TowerAdapter
    private var tower = ArrayList<Tower>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tower2)

        adaptert = TowerAdapter(this, tower)

        recycles.adapter = adaptert

        getData()
        recycles.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        database.use {
            tower.clear()
            var result = select(Tower.Tabel_Tower)
            var dataTower = result.parseList(classParser<Tower>())
            tower.addAll(dataTower)
            adaptert.notifyDataSetChanged()
        }
    }
}
package com.example.project_a

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.project_a.input.Input_Activity
import com.example.project_a.input.ReportPompaActivity
import com.example.project_a.input.TampilSemuaWpActivity
import com.example.project_a.pompa.PompaActivity
import com.example.project_a.pompa.TowerActivity
import kotlinx.android.synthetic.main.dashboard_activity.*

class Dashboard_Activity : AppCompatActivity() {

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)


        card_report_wp.setOnClickListener {
            startActivity(Intent(this, TampilSemuaWpActivity::class.java))
        }

        card_wp.setOnClickListener {
            startActivity(Intent(this, PompaActivity::class.java))
        }

        card_tl.setOnClickListener {
            startActivity(Intent(this, TowerActivity::class.java))
        }

//        card_tentang.setOnClickListener {
//            startActivity(Intent(this, Input_Activity::class.java))
//        }

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val name = preferences.getString("NAME", " ")
        username2.text = name

        card_keluar.setOnClickListener {

            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "Logout Berhasil", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
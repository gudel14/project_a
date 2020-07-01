package com.example.project_a.upload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.project_a.Fragments.Cloud_Fragment
import com.example.project_a.Fragments.Input_Fragment
import com.example.project_a.Fragments.Update_Fragment
import com.example.project_a.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.upload_activity.*

class Upload_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upload_activity)

    // default beranda yg di jalankan
        val fragment=Input_Fragment()
        defaultFragment(fragment)

        nav_wp.setOnNavigationItemReselectedListener(pilihitem)
    }
     //default fragment
    private fun defaultFragment (fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_wp, fragment)
        transaction.commit()
    }
    //metode memilih menu
    private val pilihitem = BottomNavigationView.OnNavigationItemReselectedListener {menuItem ->
        when (menuItem.itemId){
            R.id.nav_wpinput -> {
                val fragment = Input_Fragment()
                defaultFragment(fragment)
                return@OnNavigationItemReselectedListener
            }
            R.id.nav_wpupdate -> {
                val fragment = Update_Fragment()
                defaultFragment(fragment)
                return@OnNavigationItemReselectedListener
            }
            R.id.nav_wpcloud -> {
                val fragment = Cloud_Fragment()
                defaultFragment(fragment)
                return@OnNavigationItemReselectedListener
            }
        }
        false
    }

}
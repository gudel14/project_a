package com.example.project_a.nav_pompa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.project_a.R
import com.example.project_a.fragmen.Blank2Fragment
import com.example.project_a.fragmen.Blank3Fragment
import com.example.project_a.fragmen.BlankFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        val fragment = BlankFragment()
        defaultFragment(fragment)

        nav.setOnNavigationItemSelectedListener(Nav)



    }

    //default fragment
    private fun defaultFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }

    //metode untuk memilih navigasi
    private val Nav = BottomNavigationView.OnNavigationItemSelectedListener { menuNav ->
        when (menuNav.itemId){
            R.id.nav_wpinput -> {
                val fragment = BlankFragment()
                defaultFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_wpupdate -> {
                val fragment = Blank2Fragment()
                defaultFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_wpcloud -> {
                val fragment = Blank3Fragment()
                defaultFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
package com.example.lomboktravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_admin.*

class AdminActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        setSupportActionBar(toolbar)

        val navController =  Navigation.findNavController(this, R.id.fragmentContainer)
        NavigationUI.setupWithNavController(nav_view,navController)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerId)

        val togle = ActionBarDrawerToggle(this, drawerId, toolbar, R.string.open, R.string.close)
        drawerId.addDrawerListener(togle)
        togle.syncState()

    }


    override fun onBackPressed() {
        if (drawerId.isDrawerOpen(GravityCompat.START)){
            drawerId.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }

    }


}
//        btnLotim.setOnClickListener(this)

//        val listFragment = ListFragment()
//        val fragment = supportFragmentManager.findFragmentByTag(ListFragment::class.java.simpleName)
//        supportFragmentManager.beginTransaction()
//            .add(R.id.layout, listFragment, ListFragment::class.java.simpleName)
//            .addToBackStack(null)
//            .commit()
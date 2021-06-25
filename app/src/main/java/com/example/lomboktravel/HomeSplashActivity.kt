package com.example.lomboktravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_splash.*

class HomeSplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    private lateinit var animation : Animation
    private lateinit var animTop : Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_splash)


        auth = FirebaseAuth.getInstance()

        val stb = AnimationUtils.loadAnimation(this, R.anim.stb)
        animation= AnimationUtils.loadAnimation(this,R.anim.animation)
        animTop = AnimationUtils.loadAnimation(this,R.anim.anim_top)
        btnSplash.startAnimation(animation)
        imgSplash.startAnimation(animTop)
        tvSplash.startAnimation(stb)
        imgSplash2.startAnimation(stb)


        btnSplash.setOnClickListener {
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }

    }
    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Intent(this@HomeSplashActivity, HomeActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}
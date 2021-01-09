package com.lucasdias.marvelcomics.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.lucasdias.base.presentation.BaseActivity
import com.lucasdias.marvelcomics.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()

        toolbarSetup()
        startNextViewWithDelay()
    }

    private fun toolbarSetup() {
        supportActionBar?.hide()
    }

    private fun startNextViewWithDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            BaseActivity.launch(this, R.navigation.nav_comic)
        }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME = 3000L
    }
}

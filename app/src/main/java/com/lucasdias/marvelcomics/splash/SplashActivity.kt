package com.lucasdias.marvelcomics.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
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

    // TODO: Implement navigation to first Activity of the application
    private fun startNextViewWithDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
        }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME = 3000L
    }
}

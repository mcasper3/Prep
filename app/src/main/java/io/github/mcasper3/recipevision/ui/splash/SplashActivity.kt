package io.github.mcasper3.recipevision.ui.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.mcasper3.recipevision.ui.recipes.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = MainActivity.createIntent(this)
        startActivity(intent)
    }
}

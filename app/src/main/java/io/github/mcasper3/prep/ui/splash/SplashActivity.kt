package io.github.mcasper3.prep.ui.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.mcasper3.prep.ui.recipes.list.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = MainActivity.createIntent(this)
        startActivity(intent)
    }
}

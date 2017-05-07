package io.github.mcasper3.prep.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.mcasper3.prep.recipes.list.RecipeListActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(RecipeListActivity.createIntent(this))
        finish()
    }
}

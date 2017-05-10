package io.github.mcasper3.prep.ui.recipes.camera

import android.content.Context
import android.content.Intent
import android.os.Bundle

import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.ui.base.BaseActivity

class CameraActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, CameraActivity::class.java)
        }
    }
}

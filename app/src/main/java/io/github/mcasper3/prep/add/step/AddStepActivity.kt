package io.github.mcasper3.prep.add.step

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.recycler.PrepRecyclerActivity

class AddStepActivity : PrepRecyclerActivity<AddStepPresenter, AddStepView>(), AddStepView {

    private var stepNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_recycler_with_top_layout)

        stepNumber = intent.getIntExtra(STEP_NUMBER, 0)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // TODO confirmation dialog
                onBackPressed()
            }
            R.id.add -> addStep()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addStep() {
        // TODO presenter stuff
    }

    companion object {
        const val STEP_NUMBER = "arg_stepNumber"

        fun createIntent(context: Context, stepNumber: Int) = Intent(context, AddStepActivity::class.java).apply {
            this.putExtra(STEP_NUMBER, stepNumber)
        }
    }
}

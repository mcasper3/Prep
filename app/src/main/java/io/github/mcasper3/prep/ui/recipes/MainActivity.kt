package io.github.mcasper3.prep.ui.recipes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import butterknife.bindView
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.ui.base.BaseActivity
import javax.inject.Inject
import android.animation.Animator
import android.os.Handler
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator

class MainActivity : BaseActivity(), RecipeView {
    val mAddRecipeButton: FloatingActionButton by bindView(R.id.add_recipe)
    val mRecipesList: RecyclerView by bindView(R.id.recipe_list)
    val mToolbar: Toolbar by bindView(R.id.toolbar)
    val mOption1: View by bindView(R.id.menu_option_1)
    val mOption2: View by bindView(R.id.menu_option_2)
    val mOption3: View by bindView(R.id.menu_option_3)
    val mOption1Text: View by bindView(R.id.menu_option_1_text)
    val mOption2Text: View by bindView(R.id.menu_option_2_text)
    val mOption3Text: View by bindView(R.id.menu_option_3_text)
    val mMenu: View by bindView(R.id.fab_menu)
    val mFabMenuBackground: View by bindView(R.id.menu_background)

    @Inject lateinit var mRecipeAdapter: RecipeAdapter
    @Inject lateinit var mPresenter: RecipePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent().inject(this)

        mPresenter.attachView(this)

        mToolbar.setTitle(R.string.title_activity_main)

        mRecipesList.adapter = mRecipeAdapter
        mRecipesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecipesList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        mPresenter.getRecipes()

        setUpFloatingActionMenu()

        mAddRecipeButton.setOnClickListener {
            val expanded = mFabMenuBackground.visibility == View.VISIBLE

            mAddRecipeButton.animate()
                    .rotation(if (expanded) 0f else 135f)
                    .setDuration(300)
                    .setInterpolator(OvershootInterpolator())
                    .start()

            animateFabMenu(expanded, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mPresenter.detachView()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRecipes(recipes: Array<Recipe>) {
        mRecipeAdapter.setRecipes(recipes)
    }

    // Fab menu junk
    private fun setUpFloatingActionMenu() {
        // Initially, the views are shown in the expanded position. By calling the animate method
        // after the view has been created, we ensure the expansion animation happens as expected
        val handler = Handler()
        handler.postDelayed({ animateFabMenu(true, true) }, 300)
    }

    // Package level access so that Java doesn't create an extra accessor method for an anonymous class
    // See https://realm.io/news/360andev-jake-wharton-java-hidden-costs-android/ for an in-depth explanation
    fun animateFabMenu(expanded: Boolean, initialCollapse: Boolean) {
        if (expanded) {
            if (!initialCollapse) {
                hideFabMenuBackground()
            }
            animateOption3(true)
            animateOption2(true)
            animateOption1(true)
        } else {
            showFabMenuBackground()
            animateOption1(false)
            animateOption2(false)
            animateOption3(false)
            mMenu.visibility = View.VISIBLE
        }
    }

    private fun animateOption1(expanded: Boolean) {
        val yAdjustment = mMenu.height * 0.75f

        if (expanded) {
            mOption1.animate()
                    .setDuration(300)
                    .setStartDelay(200)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .translationYBy(yAdjustment)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}

                        override fun onAnimationEnd(animation: Animator) {
                            mOption1.visibility = View.INVISIBLE
                            mMenu.visibility = View.GONE
                        }

                        override fun onAnimationCancel(animation: Animator) {}

                        override fun onAnimationRepeat(animation: Animator) {

                        }
                    })
                    .start()
        } else {
            mOption1.visibility = View.VISIBLE
            mOption1.animate()
                    .setDuration(200)
                    .setStartDelay(0)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .translationYBy(-yAdjustment)
                    .withLayer()
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}

                        override fun onAnimationEnd(animation: Animator) {}

                        override fun onAnimationCancel(animation: Animator) {}

                        override fun onAnimationRepeat(animation: Animator) {}
                    })
                    .start()
        }
    }

    private fun animateOption2(expanded: Boolean) {
        val yAdjustment = mMenu.height * 0.5f

        if (expanded) {
            mOption2.animate()
                    .setDuration(300)
                    .setStartDelay(100)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .translationYBy(yAdjustment)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}

                        override fun onAnimationEnd(animation: Animator) {
                            mOption2.visibility = View.INVISIBLE
                        }

                        override fun onAnimationCancel(animation: Animator) {}

                        override fun onAnimationRepeat(animation: Animator) {}
                    })
                    .start()
        } else {
            mOption2.visibility = View.VISIBLE
            mOption2.animate()
                    .setDuration(200)
                    .setStartDelay(100)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .translationYBy(-yAdjustment)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}

                        override fun onAnimationEnd(animation: Animator) {}

                        override fun onAnimationCancel(animation: Animator) {}

                        override fun onAnimationRepeat(animation: Animator) {}
                    })
                    .start()
        }
    }

    private fun animateOption3(expanded: Boolean) {
        val yAdjustment = mMenu.height * 0.25f

        if (expanded) {
            mOption3.animate()
                    .setDuration(300)
                    .setStartDelay(0)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .translationYBy(yAdjustment)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {
                            mOption1Text.visibility = View.INVISIBLE
                            mOption2Text.visibility = View.INVISIBLE
                            mOption3Text.visibility = View.INVISIBLE
                        }

                        override fun onAnimationEnd(animation: Animator) {
                            mOption3.visibility = View.INVISIBLE
                        }

                        override fun onAnimationCancel(animation: Animator) {}

                        override fun onAnimationRepeat(animation: Animator) {}
                    })
                    .start()
        } else {
            mOption3.visibility = View.VISIBLE
            mOption3.animate()
                    .setDuration(200)
                    .setStartDelay(200)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .translationYBy(-yAdjustment)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}

                        override fun onAnimationEnd(animation: Animator) {
                            mOption1Text.visibility = View.VISIBLE
                            mOption2Text.visibility = View.VISIBLE
                            mOption3Text.visibility = View.VISIBLE
                        }

                        override fun onAnimationCancel(animation: Animator) {}

                        override fun onAnimationRepeat(animation: Animator) {}
                    })
                    .start()
        }
    }

    private fun hideFabMenuBackground() {
        val animation = AlphaAnimation(1f, 0f)
        animation.interpolator = AccelerateInterpolator()
        animation.duration = 300
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                mFabMenuBackground.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        mFabMenuBackground.startAnimation(animation)
    }

    private fun showFabMenuBackground() {
        mFabMenuBackground.visibility = View.VISIBLE
        val animation = AlphaAnimation(0f, 1f)
        animation.interpolator = DecelerateInterpolator()
        animation.duration = 300
        mFabMenuBackground.startAnimation(animation)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}

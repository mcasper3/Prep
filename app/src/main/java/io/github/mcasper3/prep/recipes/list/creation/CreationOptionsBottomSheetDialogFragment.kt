package io.github.mcasper3.prep.recipes.list.creation

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.bindView
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.injection.scope.ActivityScope
import io.github.mcasper3.prep.recipes.camera.CameraActivity
import javax.inject.Inject

@ActivityScope
class CreationOptionsBottomSheetDialogFragment @Inject constructor()
    : BottomSheetDialogFragment(), HasSupportFragmentInjector, CreationOptionsView {

    @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var presenter: CreationOptionsPresenter

    private val writeRecipeOption: View by bindView(R.id.option_write_recipe)
    private val takePhotoOption: View by bindView(R.id.option_take_photo)
    private val choosePhotoOption: View by bindView(R.id.option_choose_photo)
    private val addLinkOption: View by bindView(R.id.option_add_link)

    override fun onAttach(activity: Context) {
        AndroidSupportInjection.inject(this)
        presenter.attachView(this)
        super.onAttach(activity)
    }

    override fun onDetach() {
        presenter.detachView()
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_create_options, null)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        writeRecipeOption.setOnClickListener { presenter.onWriteRecipeClicked() }
        takePhotoOption.setOnClickListener { presenter.onTakePhotoClicked() }
        choosePhotoOption.setOnClickListener { presenter.onChoosePhotoClicked() }
        addLinkOption.setOnClickListener { presenter.onAddLinkClicked() }
    }

    override fun supportFragmentInjector() = childFragmentInjector

    override fun goToWriteRecipe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToTakePhoto() {
        startActivity(CameraActivity.createIntent(context))
    }

    override fun goToChoosePhoto() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToAddLink() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
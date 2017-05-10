package io.github.mcasper3.prep.injection.component

import dagger.Component
import io.github.mcasper3.prep.injection.module.ActivityModule
import io.github.mcasper3.prep.injection.scope.ConfigPersistent

@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}
package com.joancolmenerodev.lidlcoupons.di

import android.app.Application
import com.joancolmenerodev.lidlcoupons.App
import com.joancolmenerodev.lidlcoupons.di.modules.AppFeaturesModule
import com.joancolmenerodev.persistence.di.PersistenceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        PersistenceModule::class,
        AppFeaturesModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
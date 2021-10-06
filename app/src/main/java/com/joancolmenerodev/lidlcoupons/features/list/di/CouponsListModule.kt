package com.joancolmenerodev.lidlcoupons.features.list.di

import com.joancolmenerodev.lidlcoupons.features.list.CouponsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CouponsListModule {

    @ContributesAndroidInjector(modules = [CouponListRepositoryDependenciesModule::class])
    abstract fun bindCouponsActivity(): CouponsActivity
}
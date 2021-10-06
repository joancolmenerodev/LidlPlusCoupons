package com.joancolmenerodev.lidlcoupons.features.detail.di

import com.joancolmenerodev.lidlcoupons.features.detail.CouponDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CouponsDetailModule {

    @ContributesAndroidInjector(modules = [CouponDetailRepositoryDependenciesModule::class])
    abstract fun bindCouponDetailActivity(): CouponDetailActivity
}
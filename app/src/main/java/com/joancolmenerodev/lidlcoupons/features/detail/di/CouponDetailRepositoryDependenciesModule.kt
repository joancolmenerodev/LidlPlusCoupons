package com.joancolmenerodev.lidlcoupons.features.detail.di

import com.joancolmenerodev.lidlcoupons.features.detail.data.CouponDetailRepositoryImpl
import com.joancolmenerodev.lidlcoupons.features.detail.domain.CouponDetailRepository
import com.joancolmenerodev.lidlcoupons.features.detail.presentation.CouponDetailContract
import com.joancolmenerodev.lidlcoupons.features.detail.presentation.CouponDetailPresenter
import com.joancolmenerodev.lidlcoupons.features.list.data.CouponItemRepositoryImpl
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponItemRepository
import dagger.Binds
import dagger.Module

@Module
abstract class CouponDetailRepositoryDependenciesModule {

    @Binds
    abstract fun bindCouponDetailRepository(repository: CouponDetailRepositoryImpl): CouponDetailRepository

    @Binds
    abstract fun bindCouponItemRepository(repository: CouponItemRepositoryImpl): CouponItemRepository

    @Binds
    abstract fun bindCouponDetailPresenter(presenter: CouponDetailPresenter): CouponDetailContract.Presenter

}
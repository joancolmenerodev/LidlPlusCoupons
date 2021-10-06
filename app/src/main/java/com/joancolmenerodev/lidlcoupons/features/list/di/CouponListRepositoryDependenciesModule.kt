package com.joancolmenerodev.lidlcoupons.features.list.di

import com.joancolmenerodev.lidlcoupons.features.list.adapter.CouponItemContract
import com.joancolmenerodev.lidlcoupons.features.list.adapter.CouponItemPresenter
import com.joancolmenerodev.lidlcoupons.features.list.data.CouponItemRepositoryImpl
import com.joancolmenerodev.lidlcoupons.features.list.data.CouponsListRepositoryImpl
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponItemRepository
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponsListRepository
import com.joancolmenerodev.lidlcoupons.features.list.presentation.CouponsListContract
import com.joancolmenerodev.lidlcoupons.features.list.presentation.CouponsListPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class CouponListRepositoryDependenciesModule {

    @Binds
    abstract fun bindCouponListRepository(repository: CouponsListRepositoryImpl): CouponsListRepository

    @Binds
    abstract fun bindCouponItemRepository(repository: CouponItemRepositoryImpl): CouponItemRepository

    @Binds
    abstract fun bindCouponListPresenter(presenter: CouponsListPresenter): CouponsListContract.Presenter

    @Binds
    abstract fun bindCouponItemPresenter(presenter: CouponItemPresenter): CouponItemContract.Presenter

}
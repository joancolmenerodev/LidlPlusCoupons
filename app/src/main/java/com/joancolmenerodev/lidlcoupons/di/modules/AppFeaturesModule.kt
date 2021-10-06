package com.joancolmenerodev.lidlcoupons.di.modules

import com.joancolmenerodev.lidlcoupons.features.detail.di.CouponsDetailModule
import com.joancolmenerodev.lidlcoupons.features.list.di.CouponsListModule
import dagger.Module

@Module(includes = [CouponsListModule::class, CouponsDetailModule::class])
object AppFeaturesModule
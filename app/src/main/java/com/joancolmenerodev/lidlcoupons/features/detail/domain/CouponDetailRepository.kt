package com.joancolmenerodev.lidlcoupons.features.detail.domain

import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain

interface CouponDetailRepository {

    suspend fun getCouponDetail(userId: Int, couponId: Int): CouponDomain
}
package com.joancolmenerodev.lidlcoupons.features.list.domain.repository

interface CouponItemRepository {

    suspend fun setSelected(userId: Int, couponId: Int): Boolean
    suspend fun removeSelected(userId: Int, couponId: Int): Boolean
}
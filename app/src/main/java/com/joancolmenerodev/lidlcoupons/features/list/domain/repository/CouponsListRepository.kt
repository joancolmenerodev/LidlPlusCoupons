package com.joancolmenerodev.lidlcoupons.features.list.domain.repository

import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import kotlinx.coroutines.flow.Flow

interface CouponsListRepository {
    fun getCouponsByUser(userId: Int): Flow<List<CouponDomain>>
}
package com.joancolmenerodev.lidlcoupons.features.detail.data

import com.joancolmenerodev.lidlcoupons.features.detail.domain.CouponDetailExceptions
import com.joancolmenerodev.lidlcoupons.features.detail.domain.CouponDetailRepository
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.toDomain
import com.joancolmenerodev.persistence.dao.CouponDao
import javax.inject.Inject

class CouponDetailRepositoryImpl @Inject constructor(private val couponDao: CouponDao) :
    CouponDetailRepository {
    override suspend fun getCouponDetail(userId: Int, couponId: Int): CouponDomain {
        return try {
            val result = couponDao.findCouponByUserAndCoupon(userId, couponId)
            result.toDomain()
        } catch (exception: Exception) {
            throw CouponDetailExceptions.CouponNotFound
        }
    }

}
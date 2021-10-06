package com.joancolmenerodev.lidlcoupons.features.list.data

import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponsListExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.map
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponsListRepository
import com.joancolmenerodev.persistence.dao.CouponDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CouponsListRepositoryImpl @Inject constructor(private val couponDao: CouponDao) :
    CouponsListRepository {

    override fun getCouponsByUser(userId: Int): Flow<List<CouponDomain>> {
        return try {
            couponDao.findAllCouponByUser(userId).map {
                if (it.isEmpty()) throw CouponsListExceptions.CouponsListEmpty
                else it.map()
            }
        } catch (exception: Exception) {
            throw when (exception) {
                is CouponsListExceptions.CouponsListEmpty -> exception
                else -> CouponsListExceptions.ErrorLoadingList
            }
        }
    }
}
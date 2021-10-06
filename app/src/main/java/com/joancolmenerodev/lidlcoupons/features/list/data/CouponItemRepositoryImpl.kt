package com.joancolmenerodev.lidlcoupons.features.list.data

import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponItemExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponItemRepository
import com.joancolmenerodev.persistence.dao.SelectedDao
import com.joancolmenerodev.persistence.entities.Selected
import javax.inject.Inject

class CouponItemRepositoryImpl @Inject constructor(private val selectedDao: SelectedDao) :
    CouponItemRepository {

    override suspend fun setSelected(userId: Int, couponId: Int): Boolean {
        return try {
            val result = selectedDao.insert(
                Selected(DEFAULT_PRIMARY_KEY, userId, couponId)
            )
            if (result <= NO_ROWS_AFFECTED) throw CouponItemExceptions.CouponSetSelectedException
            else true
        } catch (exception: Exception) {
            throw when (exception) {
                is CouponItemExceptions.CouponSetSelectedException -> exception
                else -> CouponItemExceptions.CouponItemKnownException
            }
        }
    }

    override suspend fun removeSelected(userId: Int, couponId: Int): Boolean {
        return try {
            val result = selectedDao.removeSelected(userId, couponId)
            if (result == NO_ROWS_DELETED) throw CouponItemExceptions.CouponUnSelectException
            else false
        } catch (exception: Exception) {
            throw when (exception) {
                is CouponItemExceptions.CouponUnSelectException -> exception
                else -> CouponItemExceptions.CouponItemKnownException
            }
        }
    }

    companion object {
        private const val NO_ROWS_AFFECTED = 0L
        private const val NO_ROWS_DELETED = 0
        private const val DEFAULT_PRIMARY_KEY = 0
    }
}
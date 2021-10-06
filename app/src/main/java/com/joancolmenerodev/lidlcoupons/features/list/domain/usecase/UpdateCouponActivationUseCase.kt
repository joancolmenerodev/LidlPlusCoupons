package com.joancolmenerodev.lidlcoupons.features.list.domain.usecase

import com.joancolmenerodev.lidlcoupons.base.domain.BaseUseCase
import com.joancolmenerodev.lidlcoupons.base.domain.Either
import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponItemExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponItemRepository
import javax.inject.Inject

class UpdateCouponActivationUseCase @Inject constructor(private val couponItemRepository: CouponItemRepository) :
    BaseUseCase() {

    suspend fun selectCoupon(couponId: Int): Either<CouponItemExceptions, Boolean> {
        return toEither { couponItemRepository.setSelected(DEFAULT_USER_ID, couponId) }
    }

    suspend fun removeSelection(couponId: Int): Either<CouponItemExceptions, Boolean> {
        return toEither { couponItemRepository.removeSelected(DEFAULT_USER_ID, couponId) }
    }

    companion object {
        private const val DEFAULT_USER_ID = 1
    }
}
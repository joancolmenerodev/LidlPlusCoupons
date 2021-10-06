package com.joancolmenerodev.lidlcoupons.features.detail.domain.usecase

import com.joancolmenerodev.lidlcoupons.base.domain.BaseUseCase
import com.joancolmenerodev.lidlcoupons.base.domain.Either
import com.joancolmenerodev.lidlcoupons.features.detail.domain.CouponDetailExceptions
import com.joancolmenerodev.lidlcoupons.features.detail.domain.CouponDetailRepository
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import javax.inject.Inject

class GetCouponByIdUseCase @Inject constructor(private val couponDetailRepository: CouponDetailRepository) :
    BaseUseCase() {

    suspend fun execute(couponId: Int): Either<CouponDetailExceptions, CouponDomain> = toEither {
        couponDetailRepository.getCouponDetail(
            FAKE_USER_ID, couponId
        )
    }

    companion object {
        private const val FAKE_USER_ID = 1
    }
}
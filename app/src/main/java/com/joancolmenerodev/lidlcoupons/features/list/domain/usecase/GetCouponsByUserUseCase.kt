package com.joancolmenerodev.lidlcoupons.features.list.domain.usecase

import com.joancolmenerodev.lidlcoupons.base.domain.BaseUseCase
import com.joancolmenerodev.lidlcoupons.base.domain.Either
import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponsListExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.lidlcoupons.features.list.domain.repository.CouponsListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCouponsByUserUseCase @Inject constructor(private val couponsListRepository: CouponsListRepository) :
    BaseUseCase() {

    fun execute(): Flow<Either<CouponsListExceptions, List<CouponDomain>>> =
        couponsListRepository.getCouponsByUser(
            USER_ID
        ).toFlowEither()

    /*
        This is the user_id fake because it will always be the same for this demo
     */
    companion object {
        private const val USER_ID = 1
    }
}
package com.joancolmenerodev.lidlcoupons.features.list.adapter

import com.joancolmenerodev.lidlcoupons.base.presentation.AbstractPresenter
import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponItemExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.usecase.UpdateCouponActivationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CouponItemPresenter @Inject constructor(private val useCase: UpdateCouponActivationUseCase) :
    AbstractPresenter<CouponItemContract.View>(),
    CouponItemContract.Presenter {

    override fun onCouponClicked(couponId: Int, activated: Boolean) {
        launch {
            withContext(Dispatchers.IO) {
                if (activated) {
                    useCase.removeSelection(couponId)
                } else {
                    useCase.selectCoupon(couponId)
                }
            }.fold(::updateFailure) {}
        }
    }

    private fun updateFailure(couponItemExceptions: CouponItemExceptions) {
        when (couponItemExceptions) {
            is CouponItemExceptions.CouponUnSelectException -> {
                view?.showUnselectError()
            }
            is CouponItemExceptions.CouponSetSelectedException -> {
                view?.showSelectError()
            }
            is CouponItemExceptions.CouponItemKnownException -> {
                view?.showUnknownError()
            }
        }
    }
}
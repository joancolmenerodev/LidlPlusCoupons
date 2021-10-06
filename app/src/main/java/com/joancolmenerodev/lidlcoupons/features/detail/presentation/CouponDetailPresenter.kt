package com.joancolmenerodev.lidlcoupons.features.detail.presentation

import com.joancolmenerodev.lidlcoupons.base.presentation.AbstractPresenter
import com.joancolmenerodev.lidlcoupons.features.detail.domain.CouponDetailExceptions
import com.joancolmenerodev.lidlcoupons.features.detail.domain.usecase.GetCouponByIdUseCase
import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponItemExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.usecase.UpdateCouponActivationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CouponDetailPresenter @Inject constructor(
    private val getCouponByIdUseCase: GetCouponByIdUseCase,
    private val updateCouponActivationUseCase: UpdateCouponActivationUseCase
) :
    AbstractPresenter<CouponDetailContract.View>(),
    CouponDetailContract.Presenter {

    private lateinit var couponDetail: CouponDetailView

    override fun onViewReady(view: CouponDetailContract.View, couponId: Int) {
        super.onViewReady(view)
        launch {
            withContext(Dispatchers.IO) {
                getCouponByIdUseCase.execute(couponId)
            }.fold({ onDetailCouponFailure(it) }, { displayCoupon(it.mapToCouponDetailView()) })
        }
    }

    override fun updateCouponActivation(couponId: Int, isSelected: Boolean) {
        launch {
            withContext(Dispatchers.IO) {
                if (isSelected) updateCouponActivationUseCase.removeSelection(couponId) else updateCouponActivationUseCase.selectCoupon(
                    couponId
                )
            }.fold({
                updateCouponFailures(it)
            }, {
                updateCouponSucceed(it)
            })
        }
    }

    override fun showAvailabilityAndConditions() {
        view?.navigateToAvailabilityAndConditions(couponDetail.availabilityAndCondition)
    }

    private fun onDetailCouponFailure(couponDetailExceptions: CouponDetailExceptions) {
        when (couponDetailExceptions) {
            is CouponDetailExceptions.CouponNotFound -> view?.displayCouponNotFound()
        }
    }

    private fun updateCouponFailures(couponItemExceptions: CouponItemExceptions) {
        when (couponItemExceptions) {
            is CouponItemExceptions.CouponUnSelectException -> view?.displayErrorCouponSelection()
            is CouponItemExceptions.CouponSetSelectedException -> view?.displayErrorCouponSelection()
            else -> view?.displayUnknownError()
        }
    }

    private fun updateCouponSucceed(isActivated: Boolean) {
        view?.updateSelectedCoupon(isActivated)
    }

    private fun displayCoupon(couponDetailView: CouponDetailView) {
        this.couponDetail = couponDetailView
        view?.updateToolbar("${couponDetailView.couponDiscount} ${couponDetailView.couponDiscountName}")
        view?.displayBrandProductImage(couponDetailView.productBrandImage)
        view?.displayProductImage(couponDetailView.productImage)
        view?.displayProductDescription(couponDetailView.productDescription)
        view?.displayCouponDiscount(couponDetailView.couponDiscount)
        view?.displayCouponDiscountName(couponDetailView.couponDiscountName)
        view?.displayCouponSelected(couponDetailView.isSelected)
        view?.displayProductBrandName(couponDetailView.productBrandName)
        view?.displayProductName(couponDetailView.productName)
        view?.displayTimeToExpire(couponDetailView.couponStatus)
        view?.displayCouponCondition(couponDetailView.couponCondition)
        view?.displayCouponConditionDescription(couponDetailView.couponConditionDescription)
        view?.displayProductId(couponDetailView.productId)

    }
}
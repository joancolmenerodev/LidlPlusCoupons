package com.joancolmenerodev.lidlcoupons.features.detail.presentation

import com.joancolmenerodev.lidlcoupons.base.presentation.BasePresenter
import com.joancolmenerodev.lidlcoupons.base.presentation.PresenterView
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponStatus

interface CouponDetailContract {

    interface View : PresenterView {
        fun updateToolbar(couponDiscount: String)
        fun displayCoupon(coupon: CouponDetailView)
        fun displayBrandProductImage(productBrandImage: String)
        fun displayProductImage(productImage: String)
        fun displayCouponDiscount(couponDiscount: String)
        fun displayCouponDiscountName(couponDiscountName: String)
        fun displayCouponSelected(selected: Boolean)
        fun displayProductBrandName(productBrandName: String)
        fun displayProductName(productName: String)
        fun displayTimeToExpire(couponStatus: CouponStatus)
        fun displayCouponCondition(couponCondition: String)
        fun displayCouponConditionDescription(couponConditionDescription: String)
        fun displayProductId(productId: Int)
        fun updateSelectedCoupon(selected: Boolean)
        fun displayErrorCouponSelection()
        fun displayUnknownError()
        fun navigateToAvailabilityAndConditions(availabilityAndConditions: String)
        fun displayCouponNotFound()
        fun displayProductDescription(productDescription: String)

    }

    interface Presenter : BasePresenter<View> {

        fun onViewReady(view: View, couponId: Int)
        fun updateCouponActivation(couponId: Int, isSelected: Boolean)
        fun showAvailabilityAndConditions()
    }
}
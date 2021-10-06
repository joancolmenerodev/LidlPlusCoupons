package com.joancolmenerodev.lidlcoupons.features.list.adapter

import com.joancolmenerodev.lidlcoupons.base.presentation.BasePresenter
import com.joancolmenerodev.lidlcoupons.base.presentation.PresenterView

interface CouponItemContract {
    interface View : PresenterView {
        /**
         * Based on the success of room request, check or uncheck cell
         */
        fun showSelectError()
        fun showUnselectError()
        fun showUnknownError()
    }

    interface Presenter : BasePresenter<View> {
        /**
         * Trigger room request to update item and then update view.
         */
        fun onCouponClicked(couponId: Int, activated: Boolean)
    }
}
package com.joancolmenerodev.lidlcoupons.features.list.presentation

import com.joancolmenerodev.lidlcoupons.base.presentation.BasePresenter
import com.joancolmenerodev.lidlcoupons.base.presentation.PresenterView
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponListView

interface CouponsListContract {

    interface View : PresenterView {

        fun fillList(list: List<CouponListView>)
        fun showEmptyLayout()
        fun showErrorLayout()
        fun showProgressBar(show: Boolean)
        fun showOneOrMoreActiveCoupons(count: Int)
        fun showNoActiveCoupons(count: Int)
        fun displayLockedCoupon(days: String)
        fun navigateToDetailCoupon(couponId: Int)
    }

    interface Presenter : BasePresenter<View> {
        fun showCouponLocked(days: String)
        fun showCouponDetail(couponId: Int)
    }
}
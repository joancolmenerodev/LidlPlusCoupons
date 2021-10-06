package com.joancolmenerodev.lidlcoupons.features.list.presentation.comparator

import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponListView
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponStatus

/*
    Class to device the order of the coupons, in this case I've choose that the Locked ones are on the top then following we have the ones that finish today and then following we have the coupons that needs more time to finish
 */
class CouponListComparator {

    fun getComparator() = compareBy<CouponListView>({
        when (it.status) {
            is CouponStatus.Locked -> FIRST_POSITION
            is CouponStatus.FinishToday -> SECOND_POSITION
            is CouponStatus.DaysToExpire -> THIRD_POSITION
        }
    }, { it.couponDiscount })

    companion object {
        private const val FIRST_POSITION = 0
        private const val SECOND_POSITION = 1
        private const val THIRD_POSITION = 2
    }
}
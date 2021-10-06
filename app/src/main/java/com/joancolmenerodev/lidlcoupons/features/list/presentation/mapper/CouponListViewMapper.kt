package com.joancolmenerodev.lidlcoupons.features.list.presentation.mapper

import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponListView
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponStatus


/*
This mapper would do the map to know the Coupon status like if is Unlock, InProgress, Finish Today or Finish in 5 hours, but as it is a fake one and the data
comes from Room the fastest way is to modify it here

CouponStatus.Blocked --> When startDate > Date().time
CouponStatus.DaysToExpire --> Getting the days diff between Date().time and endDate
CouponStatus.FinishToday --> When the result of the diff between Date().time and endDate is 0

The way to do is, as it will be only 8 elements I've defined a way to split different CouponStatus so we can see different layouts for each coupon
 */
fun List<CouponDomain>.mapToCouponListView(): List<CouponListView> {

    val couponListView = arrayListOf<CouponListView>()
    this.forEach { couponListDomain ->
        couponListView.add(couponListDomain.mapToCouponListView())
    }
    return couponListView
}

fun CouponDomain.mapToCouponListView(): CouponListView {
    return CouponListView(
        id = this.id,
        name = this.name,
        image = this.productImage,
        productBrand = this.productBrandName,
        productBrandImage = this.productBrandImage,
        couponDiscount = this.discount,
        couponDiscountDescription = this.discountName,
        status = this.getCouponStatus(this.id),
        isActivated = isSelected
    )
}

/*
    On this demo that everything is faked we will show only:
    2 coupons blocked
    4 coupons that finish today
    2 coupons with different days to expire
 */
fun CouponDomain.getCouponStatus(couponId: Int): CouponStatus {
    return when (couponId) {
        1 -> CouponStatus.Locked(1)
        2, 7, 3, 8 -> CouponStatus.FinishToday
        4 -> CouponStatus.DaysToExpire(4)
        5 -> CouponStatus.DaysToExpire(8)
        6 -> CouponStatus.Locked(2)
        else -> CouponStatus.Locked(3)
    }
}
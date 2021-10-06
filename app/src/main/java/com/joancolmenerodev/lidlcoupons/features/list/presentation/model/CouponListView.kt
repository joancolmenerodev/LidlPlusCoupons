package com.joancolmenerodev.lidlcoupons.features.list.presentation.model

data class CouponListView(
    val id: Int,
    val name: String,
    val image: String,
    val productBrand: String,
    val productBrandImage: String,
    val couponDiscount: String,
    val couponDiscountDescription: String,
    val status: CouponStatus,
    var isActivated: Boolean
)

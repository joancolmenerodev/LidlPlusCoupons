package com.joancolmenerodev.lidlcoupons.features.detail.presentation

import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.lidlcoupons.features.list.presentation.mapper.getCouponStatus
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponStatus

data class CouponDetailView(
    val productBrandImage: String,
    val productImage: String,
    val couponDiscount: String,
    val couponDiscountName: String,
    val isSelected: Boolean,
    val productBrandName: String,
    val productName: String,
    val productDescription: String,
    val couponStatus: CouponStatus,
    val couponCondition: String,
    val couponConditionDescription: String,
    val productId: Int,
    val availabilityAndCondition: String
)

fun CouponDomain.mapToCouponDetailView() = CouponDetailView(
    this.productBrandImage,
    this.productImage,
    this.discount,
    this.discountName,
    this.isSelected,
    this.productBrandName,
    this.productName,
    this.productDescription,
    this.getCouponStatus(id),
    this.condition,
    this.conditionDescription,
    this.productId,
    this.availabilityAndConditions
)
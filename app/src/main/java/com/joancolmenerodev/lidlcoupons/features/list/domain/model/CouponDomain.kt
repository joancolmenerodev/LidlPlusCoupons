package com.joancolmenerodev.lidlcoupons.features.list.domain.model

import com.joancolmenerodev.persistence.entities.CouponsByUser

data class CouponDomain(
    val id: Int,
    val name: String,
    val description: String,
    val discount: String,
    val discountName: String,
    val startDate: Long,
    val endDate: Long,
    val availabilityAndConditions: String,
    val condition: String,
    val conditionDescription: String,
    val productId: Int,
    val productName: String,
    val productDescription: String,
    val productImage: String,
    val productBrandName: String,
    val productBrandImage: String,
    val isSelected: Boolean
)

fun List<CouponsByUser>.map(): List<CouponDomain> = this.map { it.toDomain() }
fun CouponsByUser.toDomain(): CouponDomain {
    return CouponDomain(
        id = this.coupon.id,
        name = this.coupon.name,
        description = this.coupon.description,
        discount = this.coupon.discount,
        discountName = this.coupon.discountName,
        startDate = this.coupon.startDate,
        endDate = this.coupon.endDate,
        availabilityAndConditions = this.coupon.availabilityAndConditions,
        condition = this.coupon.condition,
        conditionDescription = this.coupon.conditionDescription,
        productId = this.product.id,
        productName = this.product.name,
        productDescription = this.product.description,
        productImage = this.product.image,
        productBrandName = this.product.brandName,
        productBrandImage = this.product.brandImage,
        isSelected = this.selected
    )
}
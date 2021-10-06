package com.joancolmenerodev.persistence.entities

import androidx.room.Embedded

/**
 * A class that encapsulates a coupon with the Product inside and has a flag to check if from that user is favourite or not
 */
data class CouponsByUser(
    @Embedded
    val coupon: Coupon,
    @Embedded
    val product: Product,
    val selected: Boolean
)
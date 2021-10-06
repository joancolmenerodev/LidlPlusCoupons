package com.joancolmenerodev.lidlcoupons.features.detail.domain

sealed class CouponDetailExceptions : Exception() {
    object CouponNotFound : CouponDetailExceptions()
}
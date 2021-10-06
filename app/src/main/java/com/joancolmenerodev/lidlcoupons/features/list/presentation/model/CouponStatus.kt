package com.joancolmenerodev.lidlcoupons.features.list.presentation.model

sealed class CouponStatus {
    object FinishToday : CouponStatus()
    data class DaysToExpire(val days: Long) : CouponStatus()
    data class Locked(val days: Long) : CouponStatus()
}
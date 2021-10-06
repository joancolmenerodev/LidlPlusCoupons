package com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions

import java.lang.Exception

sealed class CouponItemExceptions : Exception() {
    object CouponSetSelectedException : CouponItemExceptions()
    object CouponUnSelectException : CouponItemExceptions()
    object CouponItemKnownException : CouponItemExceptions()
}
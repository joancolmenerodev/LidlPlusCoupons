package com.joancolmenerodev.lidlcoupons.features.list.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import com.joancolmenerodev.lidlcoupons.R
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponStatus

fun getCouponStatusColor(couponStatus: CouponStatus, context: Context): Int {
    return when (couponStatus) {
        is CouponStatus.Locked -> ContextCompat.getColor(context, R.color.dark_gray)
        is CouponStatus.DaysToExpire -> ContextCompat.getColor(context, R.color.dark_gray)
        is CouponStatus.FinishToday -> ContextCompat.getColor(context, R.color.dark_red)
    }
}

fun getCouponStatusText(couponStatus: CouponStatus, context: Context): String {
    return when (couponStatus) {
        is CouponStatus.Locked -> context.getString(
            R.string.coupon_unlocks_in,
            couponStatus.days.toString()
        )
        is CouponStatus.DaysToExpire -> context.getString(
            R.string.days_to_finish,
            couponStatus.days.toString()
        )
        is CouponStatus.FinishToday -> context.getString(R.string.coupon_finish_today)
    }
}
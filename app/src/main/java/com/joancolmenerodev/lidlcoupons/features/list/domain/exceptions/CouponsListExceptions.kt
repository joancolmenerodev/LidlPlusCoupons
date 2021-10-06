package com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions

sealed class CouponsListExceptions : Exception() {
    object CouponsListEmpty : CouponsListExceptions()
    object ErrorLoadingList : CouponsListExceptions()
}
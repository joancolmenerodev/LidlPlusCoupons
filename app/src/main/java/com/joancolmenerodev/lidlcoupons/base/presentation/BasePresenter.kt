package com.joancolmenerodev.lidlcoupons.base.presentation

interface BasePresenter<T> {
    fun onViewReady(view: T)
    fun onViewDestroyed()
}
package com.joancolmenerodev.lidlcoupons.base.presentation

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class AbstractPresenter<T : PresenterView> :
    BasePresenter<T> {

    private val job = Job()

    var view: T? = null

    override fun onViewDestroyed() {
        this.view = null
        this.job.cancel()
    }

    override fun onViewReady(view: T) {
        this.view = view
    }

    protected fun launch(
        context: CoroutineContext = Dispatchers.Main,
        block: suspend CoroutineScope.(T) -> Unit
    ): Job = view?.let {
        CoroutineScope(context + job + CoroutineExceptionHandler { _, error -> throw error })
            .launch { block(it) }

    } ?: throw NotImplementedError("View not attached correctly")

}
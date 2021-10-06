package com.joancolmenerodev.lidlcoupons.features.list.presentation

import com.joancolmenerodev.lidlcoupons.base.presentation.AbstractPresenter
import com.joancolmenerodev.lidlcoupons.features.list.domain.exceptions.CouponsListExceptions
import com.joancolmenerodev.lidlcoupons.features.list.domain.model.CouponDomain
import com.joancolmenerodev.lidlcoupons.features.list.domain.usecase.GetCouponsByUserUseCase
import com.joancolmenerodev.lidlcoupons.features.list.presentation.comparator.CouponListComparator
import com.joancolmenerodev.lidlcoupons.features.list.presentation.mapper.mapToCouponListView
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CouponsListPresenter @Inject constructor(private val useCase: GetCouponsByUserUseCase) :
    AbstractPresenter<CouponsListContract.View>(),
    CouponsListContract.Presenter {

    override fun onViewReady(view: CouponsListContract.View) {
        super.onViewReady(view)
        view.showProgressBar(true)
        launch {
            useCase.execute().collect { it.fold(::handleError, ::handleList) }
        }
        view.showProgressBar(false)
    }

    override fun showCouponLocked(days: String) {
        view?.displayLockedCoupon(days)
    }

    override fun showCouponDetail(couponId: Int) {
        view?.navigateToDetailCoupon(couponId)
    }

    private fun handleList(list: List<CouponDomain>) {
        val listMapped = list.mapToCouponListView()
        val listSorted = listMapped.sortedWith(CouponListComparator().getComparator())
        view?.fillList(listSorted)
        val activeCoupons = list.count { it.isSelected }
        if (activeCoupons == NO_ACTIVE_COUPONS) {
            view?.showNoActiveCoupons(count = activeCoupons)
        } else {
            view?.showOneOrMoreActiveCoupons(count = activeCoupons)
        }
    }

    private fun handleError(couponsListExceptions: CouponsListExceptions) {
        when (couponsListExceptions) {
            is CouponsListExceptions.CouponsListEmpty -> {
                view?.showEmptyLayout()
            }
            is CouponsListExceptions.ErrorLoadingList -> {
                view?.showErrorLayout()
            }
        }
    }

    companion object {
        private const val NO_ACTIVE_COUPONS = 0
    }


}
package com.joancolmenerodev.lidlcoupons.features.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joancolmenerodev.lidlcoupons.R
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponListView

class CouponsListAdapter(
    private val presenter: CouponItemContract.Presenter,
    private val listener: (event: ListEvent) -> Unit
) :
    RecyclerView.Adapter<CouponsListViewHolder>() {

    private var couponListView = ArrayList<CouponListView>()

    fun addItems(couponsList: List<CouponListView>) {
        val diffResult = DiffUtil.calculateDiff(
            CouponsListDiffUtilCallback(this.couponListView, couponsList)
        )
        couponListView.clear()
        couponListView.addAll(couponsList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CouponsListViewHolder {
        return CouponsListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.coupon_item, parent, false)
        )
    }

    override fun getItemCount() = couponListView.size

    override fun onBindViewHolder(holder: CouponsListViewHolder, position: Int) {
        holder.bind(couponListView[position], listener, presenter)
    }
}

sealed class ListEvent {
    class OpenCouponDetail(val couponId: Int) : ListEvent()
    class ShowLockedCoupon(val days: String) : ListEvent()
}



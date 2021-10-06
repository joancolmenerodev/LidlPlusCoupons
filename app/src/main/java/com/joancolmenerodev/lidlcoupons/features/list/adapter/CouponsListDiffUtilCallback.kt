package com.joancolmenerodev.lidlcoupons.features.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponListView

class CouponsListDiffUtilCallback(
    private val oldList: List<CouponListView>,
    private val newList: List<CouponListView>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isActivated == newList[newItemPosition].isActivated
    }
}
package com.joancolmenerodev.lidlcoupons.features.list.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.joancolmenerodev.lidlcoupons.R
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponListView
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponStatus

/*
    With more time I'd create a delegate pattern with the different types of view so onBindViewHolder I could check the viewType and depend of it I create one ViewHolder or other so there's no logic on this ViewHolder
 */
class CouponsListViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView),
    CouponItemContract.View {

    private val context: Context by lazy { itemView.context }

    private val couponContainer: ConstraintLayout =
        itemView.findViewById(R.id.item_coupon_container)
    private val couponChecked: ImageView =
        itemView.findViewById(R.id.item_coupon_checked)
    private val couponButtonActivate: AppCompatButton =
        itemView.findViewById(R.id.item_coupon_activate)
    private val couponDiscount: AppCompatTextView =
        itemView.findViewById(R.id.item_coupon_discount)
    private val couponDiscountDescription: AppCompatTextView =
        itemView.findViewById(R.id.item_coupon_discount_description)
    private val couponName: AppCompatTextView =
        itemView.findViewById(R.id.item_coupon_coupon_name)
    private val couponStatus: AppCompatTextView =
        itemView.findViewById(R.id.item_coupon_coupon_status)
    private val couponBrandImage: ImageView =
        itemView.findViewById(R.id.iv_product_brand)
    private val couponImage: ImageView =
        itemView.findViewById(R.id.item_coupon_product_image)
    private val couponLockedImage: ImageView =
        itemView.findViewById(R.id.item_coupon_locked_image)

    fun bind(
        coupon: CouponListView,
        listener: (event: ListEvent) -> Unit,
        presenter: CouponItemContract.Presenter
    ) {
        presenter.onViewReady(this)

        if (coupon.status is CouponStatus.Locked) {
            couponButtonActivate.visibility = View.GONE
            couponLockedImage.visibility = View.VISIBLE
        } else {
            couponButtonActivate.visibility = View.VISIBLE
            couponLockedImage.visibility = View.GONE
        }

        if (coupon.isActivated) {
            couponChecked.visibility = View.VISIBLE
            couponButtonActivate.isSelected = true
            couponButtonActivate.text = itemView.context.getString(R.string.coupon_activated_text)

        } else {
            couponChecked.visibility = View.GONE
            couponButtonActivate.isSelected = false
            couponButtonActivate.text = itemView.context.getString(R.string.coupon_activate_text)
        }
        couponDiscount.text = coupon.couponDiscount

        couponDiscountDescription.text = coupon.couponDiscountDescription

        couponName.text = coupon.name

        couponStatus.text = getCouponStatusText(coupon.status, context)
        couponStatus.setTextColor(getCouponStatusColor(coupon.status, context))

        loadImage(couponImage, coupon.image)
        loadImage(couponBrandImage, coupon.productBrandImage)

        couponButtonActivate.setOnClickListener {
            presenter.onCouponClicked(
                coupon.id,
                coupon.isActivated
            )
        }

        couponContainer.setOnClickListener {
            if (coupon.status is CouponStatus.Locked) {
                listener.invoke(ListEvent.ShowLockedCoupon(coupon.status.days.toString()))
            } else {
                listener.invoke(ListEvent.OpenCouponDetail(coupon.id))
            }
        }
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    override fun showSelectError() {
        Toast.makeText(itemView.context, "Error when selecting this coupon", Toast.LENGTH_SHORT)
            .show()
    }

    override fun showUnselectError() {
        Toast.makeText(itemView.context, "Error when un-selecting this coupon", Toast.LENGTH_SHORT)
            .show()
    }

    override fun showUnknownError() {
        Toast.makeText(itemView.context, "Unknown error", Toast.LENGTH_SHORT).show()
    }

}

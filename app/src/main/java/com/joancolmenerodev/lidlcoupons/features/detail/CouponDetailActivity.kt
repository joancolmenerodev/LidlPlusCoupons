package com.joancolmenerodev.lidlcoupons.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.joancolmenerodev.lidlcoupons.R
import com.joancolmenerodev.lidlcoupons.features.conditions.AvailabilityAndConditionsActivity
import com.joancolmenerodev.lidlcoupons.features.detail.presentation.CouponDetailContract
import com.joancolmenerodev.lidlcoupons.features.detail.presentation.CouponDetailView
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponStatus
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_coupon_detail.*
import javax.inject.Inject


class CouponDetailActivity : AppCompatActivity(), CouponDetailContract.View {

    @Inject
    lateinit var presenter: CouponDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        presenter.onViewReady(this, getCouponIdFromIntent())
        setContentView(R.layout.activity_coupon_detail)
        setupToolbar()

        btn_coupon_detail.setOnClickListener {
            presenter.updateCouponActivation(getCouponIdFromIntent(), btn_coupon_detail.isSelected)
        }

        tv_detail_availability_and_conditions.setOnClickListener {
            presenter.showAvailabilityAndConditions()
        }

    }

    override fun updateToolbar(couponDiscount: String) {
        toolbar.title = couponDiscount
    }

    override fun displayCoupon(coupon: CouponDetailView) {
        Toast.makeText(this, coupon.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun displayBrandProductImage(productBrandImage: String) {
        loadImage(coupon_detail_brand_product, productBrandImage)
    }

    override fun displayProductImage(productImage: String) {
        loadImage(iv_coupon_detail_image, productImage)
    }

    override fun displayCouponDiscount(couponDiscount: String) {
        tv_coupon_discount.text = couponDiscount
    }

    override fun displayCouponDiscountName(couponDiscountName: String) {
        tv_coupon_discount_name.text = couponDiscountName
    }

    override fun displayCouponSelected(selected: Boolean) {
        changeButtonStatus(selected)
        changeTextStatus(selected)
    }

    override fun displayProductBrandName(productBrandName: String) {
        tv_product_brand_name.text = productBrandName
    }

    override fun displayProductName(productName: String) {
        tv_coupon_detail_name.text = productName
    }

    override fun displayTimeToExpire(couponStatus: CouponStatus) {
        when (couponStatus) {
            is CouponStatus.FinishToday -> {
                tv_coupon_days_to_end.text = getString(R.string.coupon_finish_today)
                tv_coupon_days_to_end.setTextColor(ContextCompat.getColor(this, R.color.dark_red))
            }
            is CouponStatus.DaysToExpire -> {
                tv_coupon_days_to_end.text =
                    getString(R.string.days_to_finish, couponStatus.days.toString())
            }
        }
    }

    override fun displayCouponCondition(couponCondition: String) {
        tv_coupon_condition.text = couponCondition
    }

    override fun displayCouponConditionDescription(couponConditionDescription: String) {
        tv_coupon_condition_description.text = couponConditionDescription
    }

    override fun displayProductId(productId: Int) {
        tv_coupon_code_product.text = productId.toString()
    }

    override fun updateSelectedCoupon(selected: Boolean) {
        changeButtonStatus(selected)
        changeTextStatus(selected)
    }

    override fun displayUnknownError() {
        Toast.makeText(this, R.string.coupon_update_unknown_error, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun navigateToAvailabilityAndConditions(availabilityAndConditions: String) {
        startActivity(
            AvailabilityAndConditionsActivity.getAvailabilityAndConditionsIntent(
                this,
                availabilityAndConditions
            )
        )
    }

    override fun displayProductDescription(productDescription: String) {
        tv_coupon_detail_description.text = productDescription
    }

    override fun displayErrorCouponSelection() {
        Toast.makeText(this, R.string.coupon_update_error, Toast.LENGTH_SHORT).show()
    }

    override fun displayCouponNotFound() {
        Toast.makeText(this, R.string.coupon_detail_not_found, Toast.LENGTH_SHORT).show()
        finish()
    }


    private fun getCouponIdFromIntent() = intent.getIntExtra(KEY_COUPON_ID, DEFAULT_INTENT_VALUE)

    private fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun inject() {
        AndroidInjection.inject(this)
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)

    }

    private fun changeButtonStatus(selected: Boolean) {
        if (btn_coupon_detail.isSelected) {
            btn_coupon_detail.isSelected = selected
        } else {
            btn_coupon_detail.isSelected = selected
        }
        btn_coupon_detail.text =
            if (selected) this.getString(R.string.coupon_activated_text) else this.getString(R.string.coupon_activate_text)
    }

    private fun changeTextStatus(selected: Boolean) {
        if (selected) {
            tv_coupon_detail_status.text =
                this.getString(R.string.detail_status_activated_coupon_text)
            tv_coupon_detail_status.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        } else {
            tv_coupon_detail_status.text = this.getString(
                R.string.detail_status_desactivated_coupon_text
            )
            tv_coupon_detail_status.setTextColor(
                ContextCompat.getColor(this, android.R.color.tab_indicator_text)
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {

        private const val KEY_COUPON_ID = "coupon_id"
        private const val DEFAULT_INTENT_VALUE = -1

        fun getCouponDetailIntent(context: Context, couponId: Int): Intent =
            Intent(context, CouponDetailActivity::class.java)
                .apply {
                    putExtra(KEY_COUPON_ID, couponId)
                }
    }
}
package com.joancolmenerodev.lidlcoupons.features.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.joancolmenerodev.lidlcoupons.R
import com.joancolmenerodev.lidlcoupons.features.detail.CouponDetailActivity
import com.joancolmenerodev.lidlcoupons.features.list.adapter.CouponItemContract
import com.joancolmenerodev.lidlcoupons.features.list.adapter.CouponsListAdapter
import com.joancolmenerodev.lidlcoupons.features.list.adapter.ListEvent
import com.joancolmenerodev.lidlcoupons.features.list.presentation.CouponsListContract
import com.joancolmenerodev.lidlcoupons.features.list.presentation.dialog.LockedCouponDialog
import com.joancolmenerodev.lidlcoupons.features.list.presentation.model.CouponListView
import com.joancolmenerodev.persistence.dao.CouponDao
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_coupons.*
import javax.inject.Inject
import kotlin.math.abs


class CouponsActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener,
    CouponsListContract.View {

    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CouponsListAdapter

    @Inject
    lateinit var couponDao: CouponDao

    @Inject
    lateinit var presenter: CouponsListContract.Presenter

    @Inject
    lateinit var couponItemPresenter: CouponItemContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupons)
        setSupportActionBar(findViewById(R.id.toolbar))

        initViews()
        initRecyclerView()

        presenter.onViewReady(this)

    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = null
        adapter = CouponsListAdapter(couponItemPresenter) {
            when (it) {
                is ListEvent.ShowLockedCoupon -> presenter.showCouponLocked(it.days)
                is ListEvent.OpenCouponDetail -> presenter.showCouponDetail(it.couponId)
            }
        }
        recyclerView.adapter = adapter
    }

    private fun initViews() {
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        appBarLayout = findViewById(R.id.appBarCoupons)
        progressBar = findViewById(R.id.pb_coupon_list)

        recyclerView = findViewById(R.id.rv_coupons)


        appBarLayout.addOnOffsetChangedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        layoutInflater
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_scan_code -> {
                Toast.makeText(
                    this,
                    getString(R.string.to_be_implemented_camera),
                    Toast.LENGTH_SHORT
                )
                    .show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val maxScroll = appBarLayout.totalScrollRange
        val percentage = abs(verticalOffset).toFloat() / maxScroll.toFloat()
        handleToolbarTitleVisibility(percentage)
    }

    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage == PERCENTAGE_TO_CHANGE_TOOLBAR_TEXT) {
            collapsingToolbarLayout.title = activeCouponsTextView.text.toString()
            activeCouponsTextView.visibility = View.GONE
        } else {
            collapsingToolbarLayout.title = getString(R.string.your_coupons)
            activeCouponsTextView.visibility = View.VISIBLE
        }
    }

    private fun inject() {
        AndroidInjection.inject(this)
    }

    companion object {
        private const val PERCENTAGE_TO_CHANGE_TOOLBAR_TEXT = 1.0f

    }

    override fun fillList(list: List<CouponListView>) {
        //Here should put View.Gone the rest of the layouts (empty and error)
        adapter.addItems(list)
    }

    override fun showEmptyLayout() {
        //Here should show the empty screen layout and setting to View.GONE the rest
        Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorLayout() {
        //Here should show the error layout and setting to View.GONE the rest
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showNoActiveCoupons(count: Int) {
        activeCouponsTextView.setTextColor(ContextCompat.getColor(this, R.color.dark_red))
        activeCouponsTextView.text = getString(R.string.active_coupons_toolbar_text, count)
    }

    override fun showOneOrMoreActiveCoupons(count: Int) {
        activeCouponsTextView.setTextColor(ContextCompat.getColor(this, R.color.dark_green))
        activeCouponsTextView.text = getString(R.string.active_coupons_toolbar_text, count)
    }

    override fun displayLockedCoupon(days: String) {
        LockedCouponDialog(this, daysToUnlock = days) {
            it.dismiss()
        }.show()

    }

    override fun navigateToDetailCoupon(couponId: Int) {
        startActivity(CouponDetailActivity.getCouponDetailIntent(this, couponId))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}
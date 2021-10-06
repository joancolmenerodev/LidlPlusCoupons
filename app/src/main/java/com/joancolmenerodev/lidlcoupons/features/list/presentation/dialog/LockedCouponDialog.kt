package com.joancolmenerodev.lidlcoupons.features.list.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import com.joancolmenerodev.lidlcoupons.R
import kotlinx.android.synthetic.main.locked_coupon_dialog.*
import java.text.SimpleDateFormat
import java.util.*

class LockedCouponDialog(
    context: Context,
    private var daysToUnlock: String,
    private val onClosePressed: (dialog: DialogInterface) -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.locked_coupon_dialog)

        tv_dialog_coupon_locked.text = context.getString(
            R.string.coupon_unlocks_in,
            addDaysToCurrentDate(daysToUnlock)
        )

        btn_dialog_coupon_locked.setOnClickListener {
            onClosePressed.invoke(this)
        }
    }

    /**
     * This logic wouldn't be here, I'd implement like a presenter to do this logic
     * Also I'd provide the Date, Calendar, to make it easier for testing purposes like below in the comment and same for Calendar
     * It's just because the daysToUnlock are fakeds so I get the day of today and I add these days to show the desired timestamp
     */

    /*
    internal interface DateTime {
        val date: Date?
    }

    internal class DateTimeImpl : DateTime {
        override val date: Date?
            get() = Date()
    }
     */
    private fun addDaysToCurrentDate(daysToUnlock: String): String {
        val dateFormat = SimpleDateFormat(simpleDateFormatPattern)
        val c: Calendar = Calendar.getInstance()
        c.time = Date()
        c.add(Calendar.DATE, daysToUnlock.toInt())
        val currentDatePlusDaysToUnlock = c.time
        return dateFormat.format(currentDatePlusDaysToUnlock)
    }


    companion object {
        private const val simpleDateFormatPattern = "dd/MM/yy"
    }
}
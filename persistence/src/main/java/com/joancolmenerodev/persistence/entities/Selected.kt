package com.joancolmenerodev.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "TB_SELECTED",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["user_id"],
        childColumns = ["selected_user_id"]
    ), ForeignKey(
        entity = Coupon::class,
        parentColumns = ["coupon_id"],
        childColumns = ["selected_coupon_id"]
    )]
)
data class Selected(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "selected_id")
    val selectedId: Int,

    @ColumnInfo(name = "selected_user_id")
    val id: Int,

    @ColumnInfo(name = "selected_coupon_id")
    val couponId: Int
)

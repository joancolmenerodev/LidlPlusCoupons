package com.joancolmenerodev.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "TB_COUPON",
    foreignKeys = [ForeignKey(
        entity = Product::class,
        parentColumns = ["product_id"],
        childColumns = ["coupon_product_id"]
    )]
)
data class Coupon(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "coupon_id")
    val id: Int,

    @ColumnInfo(name = "coupon_product_id")
    val coupon_product_id: Int,

    @ColumnInfo(name = "coupon_name")
    val name: String,

    @ColumnInfo(name = "coupon_description")
    val description: String,

    @ColumnInfo(name = "coupon_discount")
    val discount: String,

    @ColumnInfo(name = "coupon_discount_name")
    val discountName: String,

    @ColumnInfo(name = "coupon_start_date")
    val startDate: Long,

    @ColumnInfo(name = "coupon_end_date")
    val endDate: Long,

    @ColumnInfo(name = "coupon_availability_and_conditions")
    val availabilityAndConditions: String,

    @ColumnInfo(name = "coupon_condition")
    val condition: String,

    @ColumnInfo(name = "coupon_condition_description")
    val conditionDescription: String

)
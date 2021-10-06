package com.joancolmenerodev.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "TB_PRODUCT"
)
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    val id: Int,

    @ColumnInfo(name = "product_name")
    val name: String,

    @ColumnInfo(name = "product_description")
    val description: String,

    @ColumnInfo(name = "product_image")
    val image: String,

    @ColumnInfo(name = "product_brand")
    val brandName: String,

    @ColumnInfo(name = "product_brand_image")
    val brandImage: String,

    @ColumnInfo(name = "product_price")
    val price: String
)
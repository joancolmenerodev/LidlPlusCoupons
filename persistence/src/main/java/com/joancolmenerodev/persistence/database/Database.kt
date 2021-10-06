package com.joancolmenerodev.persistence.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.joancolmenerodev.persistence.dao.CouponDao
import com.joancolmenerodev.persistence.dao.ProductDao
import com.joancolmenerodev.persistence.dao.SelectedDao
import com.joancolmenerodev.persistence.dao.UserDao
import com.joancolmenerodev.persistence.entities.Coupon
import com.joancolmenerodev.persistence.entities.Product
import com.joancolmenerodev.persistence.entities.Selected
import com.joancolmenerodev.persistence.entities.User


@Database(
    entities = [User::class, Product::class, Coupon::class, Selected::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun selectedDao(): SelectedDao
    abstract fun userDao(): UserDao
    abstract fun couponDao(): CouponDao

}
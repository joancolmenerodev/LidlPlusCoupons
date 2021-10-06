package com.joancolmenerodev.persistence.base

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.joancolmenerodev.persistence.dao.CouponDao
import com.joancolmenerodev.persistence.dao.ProductDao
import com.joancolmenerodev.persistence.dao.SelectedDao
import com.joancolmenerodev.persistence.dao.UserDao
import com.joancolmenerodev.persistence.database.Database
import org.junit.After
import org.junit.Before
import java.io.IOException

abstract class BasePersistenceTest {

    lateinit var db: Database

    val couponDao: CouponDao by lazy { db.couponDao() }
    val userDao: UserDao by lazy { db.userDao() }
    val selectedDao: SelectedDao by lazy { db.selectedDao() }
    val productDao: ProductDao by lazy { db.productDao() }

    @Before
    fun initializeDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .build()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }
}
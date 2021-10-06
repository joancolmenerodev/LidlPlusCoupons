package com.joancolmenerodev.persistence.di

import android.app.Application
import androidx.room.Room
import com.joancolmenerodev.persistence.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PersistenceModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            DATABASE_NAME
        ).createFromAsset(DATABASE_FILE_PATH).build()
    }

    @Provides
    fun provideCouponDao(database: Database) = database.couponDao()

    @Provides
    fun provideProductDao(database: Database) = database.productDao()

    @Provides
    fun provideSelectedDao(database: Database) = database.selectedDao()

    @Provides
    fun provideUserDao(database: Database) = database.userDao()

    private const val DATABASE_NAME = "lidl_db"
    private const val DATABASE_FILE_PATH = "database/lidl.db"
}
package me.kevincampos.cache.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import me.kevincampos.cache.dao.ExchangeDao
import me.kevincampos.cache.model.ExchangeEntity

@Database(entities = [ExchangeEntity::class], version = 1)
abstract class ExchangeListDatabase : RoomDatabase() {

    abstract fun exchangeDao(): ExchangeDao

    companion object {

        private var INSTANCE: ExchangeListDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): ExchangeListDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ExchangeListDatabase::class.java,
                            "exchange_list.db"
                        ).build()
                    }
                    return INSTANCE as ExchangeListDatabase
                }
            }
            return INSTANCE as ExchangeListDatabase
        }
    }

}
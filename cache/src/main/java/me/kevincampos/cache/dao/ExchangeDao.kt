package me.kevincampos.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import me.kevincampos.cache.database.ExchangeConstants.TABLE_NAME
import me.kevincampos.cache.model.ExchangeEntity

@Dao
abstract class ExchangeDao {

    @Query("SELECT * FROM $TABLE_NAME")
    @JvmSuppressWildcards
    abstract fun getExchanges(): List<ExchangeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertExchanges(exchanges: List<ExchangeEntity>)

}
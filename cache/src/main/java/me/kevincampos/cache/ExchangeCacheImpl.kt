package me.kevincampos.cache

import android.util.Log
import me.kevincampos.data.ExchangeCache

class ExchangeCacheImpl : ExchangeCache {

    override suspend fun use() {
        Log.e("TAG", "Using ExchangeCacheImpl")
    }

}
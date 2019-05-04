package me.kevincampos.remote

import me.kevincampos.data.ExchangeRemote

class ExchangeRemoteImpl : ExchangeRemote {

    override suspend fun use() {
        println("Using ExchangeRemoteImpl")
    }

}
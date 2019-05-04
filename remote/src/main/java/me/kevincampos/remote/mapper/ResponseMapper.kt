package me.kevincampos.remote.mapper

interface ResponseMapper<in Response, out Domain> {

    fun mapToDomain(response: Response): Domain

}
package com.ronal.dinamakarya.api

import com.ronal.dinamakarya.model.token.NomorResponse
import com.ronal.dinamakarya.model.token.TokenResponse
import com.ronal.dinamakarya.utils.Url.NOMOR
import com.ronal.dinamakarya.utils.Url.TOKEN
import retrofit2.Call
import retrofit2.http.*

interface BriApi {

    @POST(TOKEN)
    @FormUrlEncoded
    fun token(
        @Field("client_id") id : String,
        @Field("client_secret") secret : String
    ):Call<TokenResponse>

    @GET(NOMOR)
    fun nomor(
        @Header("BRI-Signature") signature: String,
        @Header("BRI-TIMESTAMP") timestamp: String
    ): Call<NomorResponse>

}
package com.ronal.dinamakarya.api

import android.content.Context
import com.ronal.dinamakarya.utils.TokenInterceptor
import com.ronal.dinamakarya.utils.Url.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    private lateinit var api: BriApi

    fun getApiService(context: Context):BriApi {
        if (!::api.isInitialized){
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okClient(context))
                .build()

            api = retrofit.create(BriApi::class.java)
        }

        return api
    }

    private fun okClient(context: Context) : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(context))
            .build()
    }

//    val INSTANCE: BriApi by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        retrofit.create(BriApi::class.java)
//    }
}
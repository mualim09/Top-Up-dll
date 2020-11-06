package com.ronal.dinamakarya.utils

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        sessionManager.ambilToken().let{
            requestBuilder.addHeader("Authorization","Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }

}
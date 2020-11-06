package com.ronal.dinamakarya.utils

import android.content.Context
import android.content.SharedPreferences
import com.ronal.dinamakarya.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)

    companion object{
        const val ACCESS_TOKEN = "access_token"
    }

    fun saveToken(token: String){
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN,token)
        editor.apply()
    }

    fun ambilToken(): String? {
        return prefs.getString(ACCESS_TOKEN, null )
    }

}
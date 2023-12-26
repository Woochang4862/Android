package com.example.usw_random_chat.data.local

import android.content.Context
import android.content.SharedPreferences
import java.util.prefs.Preferences


class TokenSharedPreference (context: Context)  {
    private val prefs : SharedPreferences =
        context.getSharedPreferences("pref_name", Context.MODE_PRIVATE)

    fun getToken(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setToken(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }
}   
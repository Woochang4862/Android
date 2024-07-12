package com.example.usw_random_chat.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.prefs.Preferences
import javax.inject.Inject
import javax.inject.Singleton


class TokenSharedPreference @Inject constructor(@ApplicationContext context: Context)  {
    private val prefs : SharedPreferences =
        context.getSharedPreferences("tokenRepo", Context.MODE_PRIVATE)
    fun getToken(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setToken(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }
    fun getUUID(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setUUID(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getAccount(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setAccount(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getID(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setID(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }
}
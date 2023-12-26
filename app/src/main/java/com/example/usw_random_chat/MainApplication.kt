package com.example.usw_random_chat

import android.app.Application
import com.example.usw_random_chat.data.local.TokenSharedPreference
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApplication : Application() {
    companion object {
        lateinit var prefs: TokenSharedPreference
    }

    override fun onCreate() {
        super.onCreate()
        prefs = TokenSharedPreference(applicationContext)
    }
}
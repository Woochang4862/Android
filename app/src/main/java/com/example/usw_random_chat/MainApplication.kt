package com.example.usw_random_chat

import android.app.Application
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.data.local.TokenSharedPreference
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        prefs = TokenSharedPreference(applicationContext)
    }

    companion object {
        lateinit var prefs: TokenSharedPreference
    }
}
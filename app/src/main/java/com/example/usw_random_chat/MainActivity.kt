package com.example.usw_random_chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.Screen.FeedbackShow
import com.example.usw_random_chat.Screen.IdSearch
import com.example.usw_random_chat.Screen.LoadingScreen
import com.example.usw_random_chat.Screen.MatchingScreen
import com.example.usw_random_chat.Screen.Navigation
import com.example.usw_random_chat.Screen.PolicyScreen
import com.example.usw_random_chat.Screen.PwSearchScreen
import com.example.usw_random_chat.Screen.SignInScreen
import com.example.usw_random_chat.ui.theme.USW_Random_ChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            USW_Random_ChatTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                IdSearch(navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    USW_Random_ChatTheme {
        Greeting("Android")
    }
}
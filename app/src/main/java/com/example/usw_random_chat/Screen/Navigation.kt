package com.example.usw_random_chat.Screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.LoadingScreen.route
    )
    {
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(navController)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen()
        }
        composable(route = Screen.SignUpDoneScreen.route) {
            SignUpDoneScreen()
        }
        composable(route = Screen.PwChangeScreen.route) {
            PwChangeScreen()
        }
        composable(route = Screen.PwSearchScreen.route) {
            PwSearchScreen()
        }
        composable(route = Screen.IdSearchScreen.route) {
            IdSearch()
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(route = Screen.FeedBackScreen.route) {
            FeedbackShow()
        }
        composable(route = Screen.PolicyScreen.route) {
            PolicyScreen()
        }
        composable(route = Screen.EditProfileScreen.route) {
            EditProfileScreen()
        }
        composable(route = Screen.LoadingScreen.route) {
            LoadingScreen(navController)
        }
        composable(route = Screen.ChatScreen.route) {
            ChattingScreen()
        }
        composable(route = Screen.MainPageScreen.route) {
            MainScreen()
        }
        composable(route = Screen.MatchingScreen.route) {
            MainScreen()
        }



    }
}
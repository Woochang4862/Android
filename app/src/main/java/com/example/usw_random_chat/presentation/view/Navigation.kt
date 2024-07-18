package com.example.usw_random_chat.presentation.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.presentation.ViewModel.ChatViewModel
import com.example.usw_random_chat.presentation.ViewModel.ProfileViewModel
import com.example.usw_random_chat.presentation.ViewModel.SignInViewModel
import com.example.usw_random_chat.presentation.ViewModel.SignUpViewModel
import com.example.usw_random_chat.presentation.ViewModel.UserModifyViewModel

@Composable
fun Navigation () {
    val navController = rememberNavController()
    val signupViewModel = hiltViewModel<SignUpViewModel>()
    val signinViewModel = hiltViewModel<SignInViewModel>()
    val chatViewModel = hiltViewModel<ChatViewModel>()
    val userModifyViewModel = hiltViewModel<UserModifyViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screen.LoadingScreen.route
    )
    {
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(signinViewModel,navController)
        }
        composable(route = Screen.EmailAuthScreen.route) {
            EmailAuthScreen(signupViewModel,navController)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(signupViewModel,navController)
        }
        composable(route = Screen.PwChangeScreen.route) {
            PwChangeScreen(userModifyViewModel,navController)
        }
        composable(route = Screen.PwSearchScreen.route) {
            PwSearchScreen(userModifyViewModel)
        }
        composable(route = Screen.IdSearchScreen.route) {
            IdSearch(userModifyViewModel,navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            //ProfileScreen(viewModel,navController)
        }
        composable(route = Screen.FeedBackScreen.route) {
            FeedbackShow(navController)
        }
        composable(route = Screen.PolicyScreen.route) {
            PolicyScreen(navController)
        }
        composable(route = Screen.EditProfileScreen.route) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            EditProfileScreen(navController, viewModel)
        }
        composable(route = Screen.LoadingScreen.route) {
            LoadingScreen(navController,signinViewModel)
        }
        composable(route = Screen.ChatScreen.route) {
            ChattingScreen(navController,chatViewModel)
        }
        composable(route = Screen.MainPageScreen.route) {
            MainScreen(navController,chatViewModel)
        }
        composable(route = Screen.MatchingScreen.route) {
            MatchingScreen(navController = navController,chatViewModel)
        }

    }
}
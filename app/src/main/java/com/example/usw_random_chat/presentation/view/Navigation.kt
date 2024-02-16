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
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.IdSearchScreen.route
    )
    {
        composable(route = Screen.SignInScreen.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            SignInScreen(viewModel,navController)
        }
        composable(route = Screen.EmailAuthScreen.route) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            EmailAuthScreen(viewModel,navController)
        }
        composable(route = Screen.SignUpScreen.route) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(viewModel,navController)
        }
        composable(route = Screen.SignUpDoneScreen.route) {
            SignUpDoneScreen(navController)
        }
        composable(route = Screen.PwChangeScreen.route) {
            val viewModel = hiltViewModel<UserModifyViewModel>()
            PwChangeScreen(viewModel,navController)
        }
        composable(route = Screen.PwSearchScreen.route) {
            val viewModel = hiltViewModel<UserModifyViewModel>()
            PwSearchScreen(viewModel)
        }
        composable(route = Screen.IdSearchScreen.route) {
            val viewModel = hiltViewModel<UserModifyViewModel>()
            IdSearch(viewModel,navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            ProfileScreen(viewModel,navController)
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
            val viewModel = hiltViewModel<SignInViewModel>()
            LoadingScreen(navController)
        }
        composable(route = Screen.ChatScreen.route) {
            val viewModel = hiltViewModel<ChatViewModel>()
            ChattingScreen(viewModel)
        }
        composable(route = Screen.MainPageScreen.route) {
            MainScreen(navController)
        }
        composable(route = Screen.MatchingScreen.route) {
            MatchingScreen(navController = navController)
        }



    }
}
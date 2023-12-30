package com.example.usw_random_chat.presentation.view

sealed class Screen(val route: String){
    object SignInScreen: Screen("SignIn")
    object SignUpScreen: Screen("SignUp")
    object EmailAuthScreen: Screen("EmailAuth")
    object SignUpDoneScreen: Screen("SignUp_Done")
    object LoadingScreen: Screen("Loading")
    object IdSearchScreen: Screen("IdSearch")
    object PwSearchScreen: Screen("PwSearch")
    object PwChangeScreen: Screen("PwChange")
    object ProfileScreen: Screen("Profile")
    object PolicyScreen: Screen("Policy")
    object FeedBackScreen: Screen("Feedback")
    object EditProfileScreen: Screen("EditProfile")
    object ChatScreen: Screen("Chat")
    object MainPageScreen: Screen("MainPage")
    object MatchingScreen: Screen("Matching")

}
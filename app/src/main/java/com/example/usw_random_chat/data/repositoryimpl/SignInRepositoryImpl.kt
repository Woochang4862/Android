package com.example.usw_random_chat.data.repositoryimpl

import androidx.navigation.NavController
import com.example.usw_random_chat.data.api.SignInApiService
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.data.repository.SignInRepository
import com.example.usw_random_chat.presentation.view.Screen
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInApiService: SignInApiService,
    private val tokenSharedPreference: TokenSharedPreference,
    //private val navController: NavController
) : SignInRepository {

    override suspend fun signIn(param: UserDTO) : Int {
        val response = signInApiService.registerSignIn(param)

        if (response.isSuccessful) {
            val accessToken = response.body()?.accessToken
            val refreshToken = response.body()?.refreshToken
            tokenSharedPreference.setToken("accessToken","token : $accessToken")
            tokenSharedPreference.setToken("refreshToken","token : $refreshToken")
            //navController.navigate(Screen.MainPageScreen.route){
               // navController.popBackStack()
            //}
            return response.code()
        } else {
            throw Exception("Fail!!")
        }
    }
}
package com.example.usw_random_chat.data.repositoryimpl

import android.util.Log
import androidx.navigation.NavController
import com.example.usw_random_chat.data.api.SignInApiService
import com.example.usw_random_chat.data.dto.Token
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.local.TokenSharedPreference
import com.example.usw_random_chat.data.repository.SignInRepository
import com.example.usw_random_chat.presentation.ViewModel.SignUpViewModel
import com.example.usw_random_chat.presentation.view.Screen
import kotlinx.coroutines.delay
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInApiService: SignInApiService,
    private val tokenSharedPreference: TokenSharedPreference,
    private val navController: NavController
) : SignInRepository {

    override suspend fun signIn(param: UserDTO) : Int {
        val response = signInApiService.registerSignIn(param)

        return if (response.isSuccessful) {
            //val accessToken = response.body()?.token?.accessToken
            //val refreshToken = response.body()?.token?.refreshToken
            //tokenSharedPreference.setToken("accessToken","$accessToken")
            //tokenSharedPreference.setToken("refreshToken","$refreshToken")
            //navController.navigate(Screen.MainPageScreen.route)
            response.code()
        } else {
            Log.d("로그인 실패",response.body().toString())
            response.code()
        }
    }

    override suspend fun autoSignIn(token: Token){
        val response = signInApiService.autoSignIn()

        if (response.isSuccessful){
            navController.navigate(Screen.MainPageScreen.route){
                navController.popBackStack()
            }
        }
        else{
            navController.navigate(Screen.SignInScreen.route){
                navController.popBackStack()
            }
        }
    }
}
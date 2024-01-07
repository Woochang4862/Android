package com.example.usw_random_chat.data.repositoryimpl

import android.util.Log
import androidx.navigation.NavController
import com.example.usw_random_chat.data.api.SignUpApiService
import com.example.usw_random_chat.data.dto.UserDTO
import com.example.usw_random_chat.data.repository.SignUpRepository
import com.example.usw_random_chat.di.ApiModule
import com.example.usw_random_chat.presentation.view.Screen
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpApiService: SignUpApiService,
    private val navController: NavController
)  : SignUpRepository {


    override suspend fun signup(param: UserDTO): UserDTO {
        val response = signUpApiService.registerSignUp(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }

    override suspend fun idDoubleCheck(param: UserDTO) : UserDTO{
        val response = signUpApiService.registerIdDoubleCheck(param)

        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception("Fail!!")
        }
    }

    override suspend fun authEmail(param: UserDTO): UserDTO {
        val response = signUpApiService.registerAuthEmail(param)

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Fail!!")
        }
    }

    override suspend fun checkAuthEmail(param: UserDTO): UserDTO {
        val response = signUpApiService.registerCheckAuthEmail(param)

        if (response.isSuccessful) {
            navController.navigate(Screen.SignUpScreen.route){
                navController.popBackStack()
            }
            return response.body()!!
        } else {
            throw Exception("Fail!!")
        }
    }
}
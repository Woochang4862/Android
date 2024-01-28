package com.example.usw_random_chat.presentation.ViewModel

import androidx.lifecycle.ViewModel
import com.example.usw_random_chat.data.repositoryimpl.ChatRepositoryImpl
import javax.inject.Inject

class ChatViewModel @Inject constructor( private val chatRepositoryImpl: ChatRepositoryImpl) : ViewModel() {

}
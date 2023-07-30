package com.example.usw_random_chat.Screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(){

}

@Composable
fun getNickName(nickname : MutableState<String>){
    Column(Modifier.padding(3.dp)) {
        Row() {
            Text(text = "닉네임", fontSize = 16.sp)
            Text(
                text = "(필수)",
                color = Color.Red,
                fontSize = 10.sp,
                modifier = Modifier.padding(start = 3.dp, top = 5.dp)
            )
        }
        TextField(
            value = nickname.value,
            onValueChange = {nicknameValue -> nickname.value = nicknameValue},
            placeholder = { Text(text = "#NICKNAME")},
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, textColor = Color.Gray),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
        )
        Text(text = "* 닉네임은 8자 이내로 작성해 주세요", color = Color(0xFFFF6565))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){

}
@Preview(showBackground = true)
@Composable
fun getNickNamePreview(){
    val qwe = remember {
        mutableStateOf("#NICKNAME")
    }
    getNickName(qwe)
}
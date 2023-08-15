package com.example.usw_random_chat.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.usw_random_chat.R

@Composable
fun button(text : String, enable : Boolean, content : Color, back : Color, modifier: Modifier){
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = back,
            contentColor = content
        ),
        enabled = enable,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            lineHeight = 20.sp,
            fontWeight = FontWeight(600),
            textAlign = TextAlign.Center,
        )
    }
}
package com.example.usw_random_chat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.usw_random_chat.R

@Composable
fun button(text: String, enable: Boolean, content: Color, back: Color, modifier: Modifier) {
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

@Composable
fun copyRightByFlag(modifier: Modifier) {
    Text(
        text = "@copyright by Flag",
        fontSize = 12.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        fontWeight = FontWeight(400),
        color = Color(0xFF767676),
        textAlign = TextAlign.Center,
        letterSpacing = 0.3.sp,
        modifier = modifier
    )
}

@Composable
fun tittleWithBackArrow(text: String, modifier: Modifier) {
    Row(
        Modifier, horizontalArrangement = Arrangement.Center
    )
    {
        Spacer(Modifier.width(35.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack, contentDescription = "",
                Modifier
                    .height(36.dp)
                    .width(36.dp)
            )
        }
        Text(
            text = buildAnnotatedString {
                append(text)
            },
            fontSize = 18.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight(600),
            color = Color(0xFF111111),
            textAlign = TextAlign.Center,
            modifier = modifier
        )
    }
}
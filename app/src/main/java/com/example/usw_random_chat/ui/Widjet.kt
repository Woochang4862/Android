package com.example.usw_random_chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.usw_random_chat.R

@Composable
fun button(text: String, enable: Boolean, content: Color, back: Color, modifier: Modifier, onPress : () -> Unit) {
    Button(
        onClick = onPress ,
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

@Composable
fun portalEmail(textFieldValue: String, onValueChange: (String) -> Unit) {
    TextField(
        value = textFieldValue,
        onValueChange = { newValue -> onValueChange(newValue) },
        placeholder = {
            Text(
                "포털 이메일 입력", style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF989898)
                )
            )
        },
        trailingIcon = {
            Text(
                "@ suwon.ac.kr    ", style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )
        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        modifier = Modifier
            .width(326.dp)
            .heightIn(min = 46.dp)
            .padding(start = 3.dp)
    )
}

@Composable
fun idSearchBtn(textFieldIdValue: String, onValueChange: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            //.fillMaxWidth()
            .height(55.dp)
            .width(350.dp)
            .padding(start = 30.dp, top = 3.dp)
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TextField(
            value = textFieldIdValue,
            onValueChange = { idValue -> onValueChange(idValue) },

            placeholder = { Text(text = "아이디 입력 (4~16자)", color = Color.Gray) },

            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent, // 포커스되었을 때의 밑줄 색상
                unfocusedIndicatorColor = Color.Transparent, // 포커스가 해제되었을 때의 밑줄 색상
                disabledIndicatorColor = Color.Transparent // 비활성화되었을 때의 밑줄 색상
            ),
            // shape 속성 주석 처리
            // shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .weight(1f)
                .width(280.dp)
            //.padding(end = 8.dp)
        )
        Button(
            onClick = { },
            modifier = Modifier
                .padding(end = 5.dp)
                .align(Alignment.CenterVertically)
                .width(100.dp)
                .height(40.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFF2D64D8)
            ),
        ) {
            Text(text = "중복 확인")
        }
    }
}

@Composable
fun MatchingAnimationText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 40.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.kcc_chassam)),
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
        )
    )
}

@Composable
fun sendImg(id: Int) {
    Image(
        painter = painterResource(id = R.drawable.send),
        contentDescription = "",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(42.dp)
            .height(42.dp)
            .background(Color(0xFFF8F8F8))
    )
}

@Composable
fun skdjebfe(confirmText: String, dissmissText: String) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            Text(text = confirmText, textAlign = TextAlign.Start)
        },
        dismissButton = {
            Text(text = dissmissText)
        },
        title = { Text(text = "알림")},
        text = { Text(text = "신고하시겠습니까?")},
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun skdjebfePreview() {
    skdjebfe("신고하기", "닫기")
}
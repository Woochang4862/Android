package com.example.usw_random_chat.Screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.usw_random_chat.R


@Composable
fun FeedbackShow(){
    FeedbackText()
    FeedbackExit()
    FeedbackTextField()
    FeedbackSubmitBtn()
}

@Composable
fun FeedbackText(){
    val customFont = Font(R.font.pretendard_regular)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 88.dp
            ),
        contentAlignment = Alignment.TopCenter
    ){
        Text(
            text = "피드백",
            color = Color(0xFF111111),
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(customFont)
            )
        )
    }
}

@Composable
fun FeedbackExit(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 322.dp,
                top = 80.dp
            )
    ){
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(36.dp)
                .height(36.dp)

        ) {
            Canvas(
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawLine(
                    start = Offset(x = canvasWidth, y = 0f),
                    end = Offset(x = 0f, y = canvasHeight),
                    color = Color(0xFF767676),
                    strokeWidth = 1.5F

                )
                drawLine(
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = canvasWidth, y = canvasHeight),
                    color = Color(0xFF767676),
                    strokeWidth = 1.5F
                )
            }
        }
    }
}

@Composable
fun FeedbackTextField(){
    var text by rememberSaveable { mutableStateOf("") }
    val customFont = Font(R.font.pretendard_regular)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 140.dp,
                start = 32.dp
            )
    ){
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = "앱 사용 시 불편한 점 또는 건의 사항이 있다면 \n" +
                            "자유롭게 작성해 주세요 ",
                    color = Color(0xFF989898),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(customFont)
                    )
                )
            },
            modifier = Modifier
                .width(326.dp)
                .height(230.dp)
        )
    }
}

@Composable
fun FeedbackSubmitBtn(){
    val customFont = Font(R.font.kcc_chassam)

    Box(
        modifier = Modifier
            .padding(
                top = 385.dp,
                start = 32.dp
            )
    ){
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .height(56.dp)
                .width(326.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2D64D8))
        ) {
            Text(
                text = "제출하기",
                color = Color(0xFFFFFFFF),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(customFont)
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackShowPreview(){
    FeedbackText()
    FeedbackExit()
    FeedbackTextField()
    FeedbackSubmitBtn()
}

@Preview(showBackground = true)
@Composable
fun FeedbackTextPreview(){
    FeedbackText()
}
@Preview(showBackground = true)
@Composable
fun FeedbackTextFieldPreview(){
    FeedbackTextField()
}@Preview(showBackground = true)
@Composable
fun FeedbackExitPreview(){
    FeedbackExit()
}@Preview(showBackground = true)
@Composable
fun FeedbackSubmitBtnPreview() {
    FeedbackSubmitBtn()
}
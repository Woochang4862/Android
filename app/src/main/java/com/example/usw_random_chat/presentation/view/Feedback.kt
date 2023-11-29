package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R


@Composable
fun FeedbackShow(navController: NavController) {
    val edittext = remember {
        mutableStateOf("")
    }
    FeedbackText()
    FeedbackExit()
    FeedbackTextField(text = edittext)
    FeedbackSubmitBtn()
}

@Composable
fun FeedbackText() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 88.dp
            ),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "피드백",
            color = Color(0xFF111111),
            fontWeight = FontWeight(600),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular))
            ),
            modifier = Modifier
                .weight(0.4f)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun FeedbackExit() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 70.dp
            ),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(0.2f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cancle),
                contentDescription = "image description",
                modifier = Modifier
                    .background(color = Color(0x00FFFFFF))
                    .height(36.dp)
                    .weight(0.3f)
            )
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun FeedbackTextField(text: MutableState<String>) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 140.dp
            ),
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        OutlinedTextField(
            value = text.value,
            onValueChange = { textValue -> text.value = textValue },
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = "앱 사용 시 불편한 점 또는 건의 사항이 있다면 \n" +
                            "자유롭게 작성해 주세요 ",
                    color = Color(0xFF989898),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular))
                    )
                )
            },
            modifier = Modifier
                .height(230.dp)
                .weight(1.1f)
        )
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
fun FeedbackSubmitBtn() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 385.dp,
            ),
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .height(56.dp)
                .weight(1f),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2D64D8))
        ) {
            Text(
                text = "제출하기",
                color = Color(0xFFFFFFFF),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.kcc_chassam))
                ),
            )
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackShowPreview() {
    FeedbackShow(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun FeedbackTextPreview() {
    FeedbackText()
}

@Preview(showBackground = true)
@Composable
fun FeedbackExitPreview() {
    FeedbackExit()
}

@Preview(showBackground = true)
@Composable
fun FeedbackSubmitBtnPreview() {
    FeedbackSubmitBtn()
}
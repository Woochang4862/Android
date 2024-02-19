package com.example.usw_random_chat.presentation.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.ChatViewModel
import com.example.usw_random_chat.ui.TimeText
import com.example.usw_random_chat.ui.TwoButtonDialog
import com.example.usw_random_chat.ui.msg
import com.example.usw_random_chat.ui.sendImg
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChattingScreen(chatViewModel: ChatViewModel = viewModel()) {
    val arr = arrayListOf(
        "awfdwf",
        "가나다라마바사아자차카ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋzzzzzzzzz",
        "waegaefawe",
        "awfdwf",
        "가나다라마바사아자차카ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋzzzzzzzzz",
        "waegaefawe",
        "awfdwf",
        "가나다라마바사아자차카ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋzzzzzzzzz",
        "waegaefawe",
        "awfdwf",
        "가나다라마바사아자차카ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋzzzzzzzzz",
        "waegaefawe",
        "awfdwf",
        "가나다라마바사아자차카ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋzzzzzzzzz",
        "waegaefawe",
        "awfdwf",
        "가나다라마바사아자차카ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋzzzzzzzzz",
        "waegaefawe",
        "awfdwf",
        "가나다라마바사아자차카ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋzzzzzzzzz",
        "waegaefawe"
    )
    val systemUiController = rememberSystemUiController()//상태바 색상변경
    systemUiController.setSystemBarsColor(
        color = Color(0xFF4D76C8)
    )

    if (chatViewModel.profileDialog.value) {
        CustomDialog(name = chatViewModel.userProfile.nickName) {
            chatViewModel.closeProfileDialog()
        }
    }
    if (chatViewModel.reportDialog.value) {
        TwoButtonDialog(
            contentText = "신고하시겠습니까?",
            leftText = "취소",
            rightText = "신고하기",
            leftonPress = { chatViewModel.closeReportDialog() },
            {},
            R.drawable.baseline_error_24
        )
    }
    if (chatViewModel.exitDialog.value) {
        TwoButtonDialog(
            contentText = "대화방을 나가시겠습니까?",
            leftText = "취소",
            rightText = "나가기",
            leftonPress = { chatViewModel.closeExitDialog() },
            {},
            R.drawable.baseline_error_24
        )
    }

    //chatViewModel.connectStomp()
    //chatViewModel.subscribeStomp()

    Scaffold(
        topBar = {
            ChatTopAppBar(chatViewModel.userProfile.nickName,
                { chatViewModel.closeProfileDialog() },
                { chatViewModel.closeReportDialog() },
                { chatViewModel.closeExitDialog() })
        },
        bottomBar = {
            ChatBottomAppBar(chatViewModel.msg,
                { newValue ->
                chatViewModel.updateMSG(newValue) },
                { message ->
                    chatViewModel.sendMSG(message) })
        },
        content = {
            LazyColumn(
                modifier = Modifier.padding(bottom = 58.dp),
                content = {
                    items(arr) {
                        if (it.length > 10) {
                            receiveMsg(text = it)
                        } else {
                            sendMsg(text = it)
                        }
                    }
                },
            )
        },
    )
}

@Composable
fun ChatTopAppBar(
    name: String,
    onPressUser: () -> Unit,
    onPressReport: () -> Unit,
    onPressExit: () -> Unit
) {
    TopAppBar(
        title = {
            IconButton(onClick = { onPressUser }) {
                Row(
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .width(157.dp)
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_img),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(0.dp)
                            .width(32.dp)
                            .height(32.dp),
                        colorFilter = ColorFilter.tint(Color(0xFFABBEFF))
                    )
                    Text(
                        text = name,
                        fontSize = 22.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(Font(R.font.kcc_chassam)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .width(100.dp)
                            .height(24.dp)
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { onPressReport }) {
                Icon(
                    painter = painterResource(id = R.drawable.report),
                    tint = Color(0xFFFFACAC),
                    contentDescription = "",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)

                )
            }
            IconButton(onClick = { onPressExit }) {
                Icon(
                    painter = painterResource(id = R.drawable.exit),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .width(38.dp)
                        .height(38.dp)

                )
            }
        },
        modifier = Modifier
            .height(84.dp),
        backgroundColor = Color(0xFF4D76C8)
    )
}

@Composable
fun ChatBottomAppBar(text: State<String>, onChange: (String) -> Unit, onPress: (String) -> Unit) {
    BottomAppBar(
        modifier = Modifier.height(58.dp),
        backgroundColor = Color.White,
        content = {
            Box(
                Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFFDBDBDB),
                            shape = RoundedCornerShape(size = 25.dp)
                        )
                        .width(342.dp)
                        .background(
                            color = Color(0xFFF8F8F8),
                            shape = RoundedCornerShape(size = 25.dp)
                        ),
                ) {
                    BasicTextField(
                        //커스텀 텍스트 필드를 사용해야해서 BasicTextField 이용
                        modifier = Modifier
                            .width(300.dp)
                            .height(42.dp)
                            .padding(top = 12.dp, start = 17.dp)
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(size = 25.dp)
                            ),
                        value = text.value,
                        onValueChange = { newValue -> onChange(newValue) },
                        decorationBox = { innerTextField ->
                            if (text.value.isEmpty()) {
                                Text(
                                    text = "채팅을 시작해 보세요 . . .",
                                    fontSize = 16.sp,
                                    lineHeight = 5.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF737373),
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(18.dp)
                                )
                            }
                            innerTextField()
                        },
                    )
                    IconButton(
                        onClick = { onPress(text.value) },
                        enabled = text.value.isNotBlank(),
                    ) {
                        if (text.value.isNotBlank()) {
                            sendImg(id = R.drawable.send)
                        } else {
                            sendImg(id = R.drawable.unactive_send)
                        }

                    }
                }
            }
        }
    )
}

@Composable
fun CustomDialog(name: String, onChange: () -> Unit) {
    Dialog(onDismissRequest = { onChange() }) {
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 40.dp,
                    spotColor = Color(0x26000000),
                    ambientColor = Color(0x26000000)
                )
                .width(280.dp)
                .height(326.dp)
                .background(color = Color(0xCCFFFFFF), shape = RoundedCornerShape(size = 25.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = { onChange() },
                modifier = Modifier.align(Alignment.End)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "",
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.profile_img),
                contentDescription = "",
                modifier = Modifier
                    .height(72.dp)
                    .width(72.dp)
            )
            Text(
                text = name,
                fontSize = 24.sp,
                lineHeight = 26.sp,
                fontFamily = FontFamily(Font(R.font.kcc_chassam)),
                fontWeight = FontWeight(400),
                color = Color(0xFF191919),
                modifier = Modifier.padding(top = 18.dp)
            )
            Text(
                text = "#ISTP",
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.kcc_chassam)),
                fontWeight = FontWeight(400),
                color = Color(0xFF767676),
                modifier = Modifier.padding(top = 10.dp)
            )
            TextField(
                value = "자기소개 어쩌고 저쩌고",
                onValueChange = {},
                modifier = Modifier
                    .padding(top = 18.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDBDBDB),
                        shape = RoundedCornerShape(size = 25.dp)
                    )
                    .width(232.dp)
                    .height(78.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(size = 25.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(R.font.kcc_chassam)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF191919),
                )
            )
        }
    }

}

@Composable
fun sendMsg(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        TimeText()
        msg(text = text, color = Color(0xFFD3DFFF))
    }
}

@Composable
fun receiveMsg(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        msg(text = text, color = Color(0xFFFFFFFF))
        TimeText()
    }
}

@Preview(showBackground = true)
@Composable
fun sendMsgPreView() {
    //msg("tghaiuwga", Alignment.Center, Color.White)
    sendMsg(text = "나우밞낭ㄴ홀마ㅕㅈㅁㄹ함ㄹㅈ함한ㅇㅁ")
}

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    CustomDialog("lelelel") {

    }
}

@Preview(showBackground = true)
@Composable
fun ChattingScreenPreview() {
    ChattingScreen()
}
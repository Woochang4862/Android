package com.example.usw_random_chat.presentation.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.presentation.ViewModel.ChatViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChattingScreen(navController: NavController, chatViewModel: ChatViewModel = viewModel()) {
    val systemUiController = rememberSystemUiController()//상태바 색상변경
    systemUiController.setSystemBarsColor(color = Color(0xFF4D76C8))
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var newMessageButtonIsVisible by remember {
        mutableStateOf(false)
    }

    fun LazyListState.getLastIndex() = layoutInfo.visibleItemsInfo.lastOrNull()?.index

    LaunchedEffect(chatViewModel.chatList.size) {
        Log.d(TAG, "ChattingScreen: ${listState.getLastIndex()}")
        if (listState.getLastIndex() == chatViewModel.chatList.size - 1) {
            listState.animateScrollToItem(chatViewModel.chatList.size)
        } else if (listState.getLastIndex() != null) {
            Log.d(TAG, "ChattingScreen: 채팅이 추가되었습니다")
            newMessageButtonIsVisible = true
        }
    }

    LaunchedEffect(Unit) {
        chatViewModel.getYourProfile()
    }

    LaunchedEffect(listState.getLastIndex()) {
        if (listState.getLastIndex() == chatViewModel.chatList.size - 1) {
            newMessageButtonIsVisible = false
        }
    }


    BackHandler {
        chatViewModel.closeExitDialog()
    }


    if (chatViewModel.profileDialog.value) {
        CustomDialog(
            name = chatViewModel.opponentUserProfile.value.nickName,
            mbti = chatViewModel.opponentUserProfile.value.mbti,
            selfIntro = chatViewModel.opponentUserProfile.value.selfIntroduce
        ) {
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
            {
                navController.navigate(Screen.MainPageScreen.route) {
                    navController.popBackStack()
                }
                chatViewModel.exitChattingRoom()
                chatViewModel.closeExitDialog()
            },
            R.drawable.baseline_error_24
        )
    }

    Scaffold(
        topBar = {
            ChatTopAppBar(
                Modifier
                    .height(84.dp),
                chatViewModel.opponentUserProfile.value.nickName,
                {
                    chatViewModel.closeProfileDialog()
                },
                { chatViewModel.closeReportDialog() },
                { chatViewModel.closeExitDialog() })
        },
        bottomBar = {
            ChatBottomAppBar(
                chatViewModel.msg,
                { chatViewModel.updateMSG(it) }
            )
            {
                chatViewModel.sendMSG {
                    coroutineScope.launch {
                        listState.animateScrollToItem(chatViewModel.chatList.size)
                    }
                }
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = 10.dp)
                    .imePadding()
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .padding(bottom = 58.dp),
                    content = {
                        items(chatViewModel.chatList) {
                            if (it.sender == chatViewModel.userProfile.value.nickName) {
                                sendMsg(text = it.contents)
                            } else if (it.sender == "EXIT_MSG") {
                                exitMsg(text = it.contents)
                            } else {
                                receiveMsg(text = it.contents)
                            }
                        }
                    },
                )
                if (newMessageButtonIsVisible) {
                    NewMessageButton(
                        modifier = Modifier
                            .padding(bottom = 64.dp)
                            .align(Alignment.BottomCenter),
                        text = chatViewModel.chatList.last().contents
                    ) {
                        coroutineScope.launch {
                            listState.animateScrollToItem(chatViewModel.chatList.size)
                            newMessageButtonIsVisible = false
                        }
                    }
                }
            }
        },
    )
}

@Composable
fun ChatTopAppBar(
    modifier: Modifier,
    name: String,
    onPressUserProfile: () -> Unit,
    onPressReport: () -> Unit,
    onPressExit: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            IconButton(onClick = { onPressUserProfile() }) {
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
            IconButton(onClick = { onPressReport() }) {
                Icon(
                    painter = painterResource(id = R.drawable.report),
                    tint = Color(0xFFFFACAC),
                    contentDescription = "",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)

                )
            }
            IconButton(onClick = { onPressExit() }) {
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
        backgroundColor = Color(0xFF4D76C8)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatBottomAppBar(
    text: State<String>,
    onChange: (String) -> Unit,
    onPress: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .imePadding()
            .height(59.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.weight(0.5f))
            Row(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDBDBDB),
                        shape = RoundedCornerShape(size = 25.dp)
                    )
                    .weight(15f)
                    .background(
                        color = Color(0xFFF8F8F8),
                        shape = RoundedCornerShape(size = 25.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    //커스텀 텍스트 필드를 사용해야해서 BasicTextField 이용
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(size = 25.dp)
                        ),
                    value = text.value,
                    singleLine = true,
                    onValueChange = { onChange(it) },
                    decorationBox = {
                        TextFieldDefaults.TextFieldDecorationBox(
                            value = text.value,
                            innerTextField = it,
                            enabled = true,
                            singleLine = false,
                            visualTransformation = VisualTransformation.None,
                            interactionSource = MutableInteractionSource(),
                            placeholder = {
                                Text(
                                    text = "채팅을 시작해 보세요 . . .",
                                    fontSize = 14.sp,
                                    lineHeight = 5.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF737373),
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(18.dp)
                                )
                            }
                        )
                    },
                )
                IconButton(
                    onClick = onPress,
                    enabled = text.value.isNotBlank(),
                ) {
                    if (text.value.isNotBlank()) {
                        SendImg(id = R.drawable.send)
                    } else {
                        SendImg(id = R.drawable.unactive_send)
                    }
                }

            }
            Spacer(modifier = Modifier.weight(0.5f))
        }

    }


}


@Composable
fun CustomDialog(name: String, mbti: String, selfIntro: String, onChange: () -> Unit) {
    Dialog(onDismissRequest = { onChange() }) {
        Column(
            modifier = Modifier
//                .shadow(elevation = 40.dp,)
                .width(280.dp)
                .height(326.dp)
                .background(
                    color = Color(0xCCFFFFFF),
                    shape = RoundedCornerShape(size = 25.dp)
                ),
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
                text = "$mbti",
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.kcc_chassam)),
                fontWeight = FontWeight(400),
                color = Color(0xFF767676),
                modifier = Modifier.padding(top = 10.dp)
            )
            Box(
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
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selfIntro,
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    style = TextStyle(
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
        Spacer(modifier = Modifier.width(10.dp))
        TimeText()
        MSG(text = text, color = Color(0xFFD3DFFF))
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
        MSG(text = text, color = Color(0xFFFFFFFF))
        TimeText()
        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun exitMsg(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        MSG(text = text, color = Color(0xFFCFCFCF))
    }
}

@Preview(showBackground = true)
@Composable
fun exitMsgPreView() {
    //msg("tghaiuwga", Alignment.Center, Color.White)
    exitMsg(text = "admin님이 나갔습니다.")
}

@Preview(showBackground = true)
@Composable
fun sendMsgPreView() {
    //msg("tghaiuwga", Alignment.Center, Color.White)
    sendMsg(text = "나우밞낭ㄴ홀마ㅕㅈㅁㄹ함ㄹㅈ함한ㅇaaaaaaaaaaaaaㅁ")
}

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    CustomDialog("lelelel", "#estj", "자기소개어쩌구라라라라라라라라라라ㅏ라라라라라ㅏ라라라라라라라라라라라라라라라라라") {

    }
}

@Preview(showBackground = true)
@Composable
fun Dialog1Preview() {
    val a = remember {
        mutableStateOf("sdaaaa")
    }
    ChatBottomAppBar(text = a, onChange = {}) {

    }
}

@Preview(showBackground = false)
@Composable
fun NewMessageButtonPreview() {
    val text = "new message"
    val color = Color(0x8A5F5F5F)
    NewMessageButton(Modifier, text) {

    }
}

@Composable
fun NewMessageButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    val color = Color(0xB2D3DFFF)
    Box(
        modifier
            .fillMaxWidth()
            .padding(start = 6.dp, end = 6.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .clickable {
                onClick()
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF191919),
                maxLines = 5
            )
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun ChattingScreenPreview() {
    ChattingScreen()
}*/

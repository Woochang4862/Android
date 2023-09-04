package com.example.usw_random_chat.Screen

import android.annotation.SuppressLint
import android.text.style.IconMarginSpan
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.usw_random_chat.R
import com.example.usw_random_chat.ui.copyRightByFlag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Text(text = "!2512341")
                Text(text = "iuvablivcasd")
                Text(text = "!24basfibsdakjfvaisv")
            }
        },
        topBar = {
            MyTopAppBar() {
                scope.launch {
                    scaffoldState.drawerState.open()

                }
            }
        },
        content = { MainContents(navController) },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    )
}

@Composable
fun DrawerScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Text(text = "!2512341")
    Text(text = "iuvablivcasd")
    Text(text = "!24basfibsdakjfvaisv")
}

@Composable
fun MyTopAppBar(onPress: () -> Unit) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.suchat),
                contentDescription = null,
                modifier = Modifier
                    .width(76.dp)
                    .height(20.dp)
            )
        },
        actions = {
            IconButton(onClick = {onPress()}) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black,
                    modifier = Modifier
                        .width(37.dp)
                        .height(30.dp)

                )
            }
        },
        modifier = Modifier
            .height(80.dp),
        backgroundColor = Color.White
    )
}

@Composable
fun MainContents(navController: NavController) {
    TalkBalloon()
    AdBanner()
    MainText()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        MatchingButton(navController)
        subText()
        copyRightByFlag(modifier = Modifier.padding(top = 120.dp))
    }

}

@Composable
fun AdBanner() {
    Text(
        text = "배너광고가 들어올곳",
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .height(55.dp)
    )
}

@Composable
fun MainText() {
    Column {
        Text(
            text = "재미있고 편하게\n" +
                    "마음껏 대화 해보세요",
            fontSize = 28.sp,
            lineHeight = 36.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight(600),
            color = Color(0xFF111111),
            modifier = Modifier
                .padding(top = 123.dp, start = 32.dp)
                .height(72.dp)
        )
        Text(
            text = "마음이 통하는 수원대학교 친구들과\n" +
                    "무엇이든 이야기 해보세요",
            fontSize = 16.sp,
            lineHeight = 22.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight(500),
            color = Color(0xFF767676),
            modifier = Modifier
                .padding(top = 18.dp, start = 32.dp)
                .height(44.dp)
        )
    }
}

@Composable
fun TalkBalloon() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 177.dp, top = 22.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.talkballoon2),
            contentDescription = "image description",
            modifier = Modifier
                .width(331.dp)
                .height(308.dp),
        )
    }
}

@Composable
fun MatchingButton(navController: NavController) {
    Button(
        onClick ={navController.navigate(Screen.MatchingScreen.route)} ,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF2D64D8),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(top = 400.dp)
            .width(334.dp)
            .height(64.dp)
    ) {
        Text(
            text = "매칭 시작하기",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.kcc_chassam)),
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun subText() {
    Text(
        text = "주의!! 욕설 및 상대방에게 불쾌함을 주는 채팅\n" +
                "적발 시 계정 이용이 제한됩니다 ",
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.kcc_chassam)),
        fontWeight = FontWeight(400),
        color = Color(0xFF767676),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 24.dp)

    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}
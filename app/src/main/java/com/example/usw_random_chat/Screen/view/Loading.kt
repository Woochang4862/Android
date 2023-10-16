package com.example.usw_random_chat.Screen.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.usw_random_chat.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoadingScreen(navController: NavController) {

    val visible1 = remember { mutableStateOf(false) }
    val visible2 = remember { mutableStateOf(false) }
    val visible3 = remember { mutableStateOf(false) }

    val message by remember { mutableStateOf("SUCHAT") }
    val alpha: Float by animateFloatAsState(if (visible3.value) 1f else 0.5f)

    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFF6B7AFF)),
        contentAlignment = Alignment.Center

    ) {
        FallingBublle(visible1.value)
        RightBublle(visible3.value,alpha)
        LeftSuChat(visible3.value,message)
    }

    LaunchedEffect(Unit) {
        visible3.value = true
        delay(1000L)
        navController.popBackStack()
        navController.navigate(Screen.SignInScreen.route)
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FallingBublle(visible: Boolean) {

    val value by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            // Start the slide from 40 (pixels) above where the content is supposed to go, to
            // produce a parallax effect
            initialOffsetY = { -500 },
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + scaleIn(
            // Animate scale from 0f to 1f using the top center as the pivot point.
            transformOrigin = TransformOrigin(0.5f, 0f)
        ) + fadeIn(initialAlpha = 0.3f),
        exit = fadeOut(animationSpec = tween(durationMillis = 50))
    ) {
        // Content that needs to appear/disappear goes here:
        //Text("Content to appear/disappear", Modifier.fillMaxWidth().requiredHeight(200.dp)
        Image(
            painter = painterResource(R.drawable.speechbubble), contentDescription = "",
            modifier = Modifier.size(46.dp, 43.dp)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RightBublle(visible: Boolean, alpha: Float) {

    AnimatedVisibility(
        visible = visible,
        // Set the start width to 20 (pixels), 0 by default
        enter = fadeIn() ,
        exit = fadeOut() + slideOutHorizontally(
            targetOffsetX = { -10 }
        )
    ) {
        // Content that needs to appear/disappear goes here:
        //Text("Content to appear/disappear", Modifier.fillMaxWidth().requiredHeight(200.dp)
        Image(
            painter = painterResource(R.drawable.speechbubble), contentDescription = "",
            modifier = Modifier
                .size(26.dp, 23.dp)
                .offset(x = 40.dp)
                .graphicsLayer(alpha = alpha)
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LeftSuChat(visible: Boolean,message : String) {
    AnimatedVisibility(
        visible = visible,
        // Set the start width to 20 (pixels), 0 by default
        enter = fadeIn(),
        exit = fadeOut() + slideOutHorizontally(
            // 종료 위치를 왼쪽으로 이동
            targetOffsetX = { 20 }
        )
    )  {
        // Content that needs to appear/disappear goes here:
        //Text("Content to appear/disappear", Modifier.fillMaxWidth().requiredHeight(200.dp)
        Box(
            modifier = Modifier.offset(x = -15.dp) //animateContentSize().
        ) {
            Text(text = message,
                color= Color(0XFFFFFFFF),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontWeight = FontWeight(400),
                fontFamily = FontFamily(Font(R.font.amberygardenregular))
            )
        }
    }
}
/*@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LeftSuChat(visible: Boolean,message : String) {
    AnimatedVisibility(
        visible = visible,
        // Set the start width to 20 (pixels), 0 by default
        enter = expandHorizontally { 20 },
        exit = shrinkHorizontally(
            // Overwrites the default animation with tween for this shrink animation.
            animationSpec = tween(),
            // Shrink towards the end (i.e. right edge for LTR, left edge for RTL). The default
            // direction for the shrink is towards [Alignment.Start]
            shrinkTowards = Alignment.End,
        ) { fullWidth ->
            // Set the end width for the shrink animation to a quarter of the full width.
            fullWidth / 4
        }
    ) {
        // Content that needs to appear/disappear goes here:
        //Text("Content to appear/disappear", Modifier.fillMaxWidth().requiredHeight(200.dp)
        Box(
            modifier = Modifier.animateContentSize().offset(x = 0.dp)
        ) {
            Text(text = message,
                color= Color(0XFFFFFFFF),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontWeight = FontWeight(400),
                fontFamily = FontFamily(Font(R.font.amberygardenregular))
            )
        }
    }
}*/

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UpCrushBublle(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
            initialAlpha = 0.4f
        ),
        exit = fadeOut(
            // Overwrites the default animation with tween
            animationSpec = tween(durationMillis = 100)
        )
    ) {
        // Content that needs to appear/disappear goes here:
        //Text("Content to appear/disappear", Modifier.fillMaxWidth().requiredHeight(200.dp)
        Image(
            painter = painterResource(R.drawable.crushspeechbubble), contentDescription = "",
            modifier = Modifier.size(59.dp, 39.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    //LoadingScreen()
}

/**
 * 이징 곡선을 사용하여 지정된 [durationMillis] 동안 시작 값과 끝 값 간에 애니메이션을 적용합니다.
 *
 * @param durationMillis 애니메이션 지속 시간 (밀리초)
 * @param delayMillis 애니메이션이 시작되기 전에 대기하는 시간 (밀리초)
 * @param easing 시작과 끝 사이를 보간하는 데 사용되는 이징 곡선
 *
 * @return 주어진 옵션을 사용하는 [TweenSpec]
 *
 * *spring 은 시작 값과 끝 값 사이에 물리학 기반 애니메이션을 적용합니다. 이는 현실 세계의 스프링과 비슷합니다.
 *
 * 인자로는 감쇠비를 나타내는 DampingRatio 와 강성을 나타내는 stiffness, 그리고 가시성 임계값을 나타내는 visibleThreshold 가 있습니다.
 *
 * 감쇠비는 탄성을 의미하고, 강성은 종료 값으로 이동하는 속도, 가시성 임계값은 애니메이션이 대상에 반올림하기에 충분히 시각적으로 가까운 것으로 간주되어야 하는 시기를 의미합니다.*/
/*@Stable
fun <T> tween(
    durationMillis: Int = AnimationConstants.DefaultDurationMillis,
    delayMillis: Int = 0,
    easing: Easing = FastOutSlowInEasing
): TweenSpec<T> = TweenSpec(
    durationMillis = durationMillis,
    delay = delayMillis,
    easing = easing
)*/

/*
    Image(
        painter = painterResource(id = R.drawable.speechbubble),
        contentDescription = null,
        modifier = Modifier.size(46.dp, 43.dp)
    )
    Image(
        painter = painterResource(id = R.drawable.crushspeechbubble),
        contentDescription = null,
        modifier = Modifier.size(59.dp, 36.dp)
    )

    Text(
        text = "SUCHAT",
        color= Color(0XFFFFFF),
        textAlign = TextAlign.Center,
        fontSize = 28.sp,
        fontWeight = FontWeight(400),
        fontFamily = FontFamily(Font(R.font.amberygardenregular)),
    )
}*/

package com.example.usw_random_chat.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.usw_random_chat.R
import java.text.SimpleDateFormat


@Composable
fun CustomText(
    text1: String,
    text2: String,
    text3: String,
    modifier: Modifier
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color(0xFF989898))) {
                append(text1)
            }
            withStyle(style = SpanStyle(color = Color(0xFF2D64D8))) {
                append(text2)
            }
            withStyle(style = SpanStyle(color = Color(0xFF989898))) {
                append(text3)
            }
        },
        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        fontSize = 12.sp,
        color = Color(0xFFDCDCDC),
        modifier = modifier
    )
}


@Composable
fun MadeAccount() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.weight(5.6f))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f)
        ) {
            Spacer(modifier = Modifier.weight(0.4f))
            Divider(
                color = Color(0xFFBFBFBF),
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 9.dp)
            )
            Spacer(modifier = Modifier.weight(0.3f))

            Text(
                text = "계정이 없으신가요?",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .weight(1.3f)
            )
            Spacer(modifier = Modifier.weight(0.3f))

            Divider(
                color = Color(0xFFBFBFBF),
                modifier = Modifier
                    .weight(1.1f)
                    .padding(top = 9.dp)
            )
            Spacer(modifier = Modifier.weight(0.4f))
        }
        Spacer(modifier = Modifier.weight(1.1f))
    }
}

@Composable
fun LoginFindIdAndPassword(navController: NavController) {
    TextButton(
        onClick = { navController.navigate(Screen.IdSearchScreen.route) },
        modifier = Modifier
    ) {
        Text(
            text = "아이디 찾기",
            color = Color(0xFF232323),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular))
            )
        )
    }
    Spacer(modifier = Modifier.width(8.dp))
    Image(
        painter = painterResource(id = R.drawable.rectangle),
        contentDescription = "image description",
        modifier = Modifier
            .width(10.dp)
            .height(32.dp)
            .padding(top = 15.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))
    TextButton(
        onClick = { navController.navigate(Screen.PwSearchScreen.route) },
        modifier = Modifier

    ) {
        Text(
            text = "비밀번호 찾기",
            color = Color(0xFF232323),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular))
            ),
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginTextFieldID(
    text: State<String>,
    text2: String,
    focusState : FocusRequester,
    nextFocusState : FocusRequester,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.weight(11f))
        Row(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            BasicTextField(
                value = text.value,
                onValueChange = onValueChange,
                singleLine = true,
                modifier = Modifier
                    .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                    .focusRequester(focusState)
                    .weight(1f)
                    .height(48.dp)
                    //.wrapContentHeight()
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    ),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    textDecoration = null
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {nextFocusState.requestFocus()}
                ),
                decorationBox = {
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = text.value,
                        innerTextField = it,
                        enabled = true,
                        singleLine = true,
                        interactionSource = MutableInteractionSource(),
                        contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                            bottom = 10.dp,
                        ),
                        visualTransformation = VisualTransformation.None,
                        placeholder = {
                            Text(
                                text = text2,
                                color = Color(0xFF989898),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                                ),
                            )
                        },
                    )
                }
            )
            /*OutlinedTextField(
                value = text.value,
                onValueChange = onValueChange,
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                placeholder = {
                    Text(
                        text = text2,
                        color = Color(0xFF989898),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_regular))
                        ),
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    //.wrapContentHeight()
                    .background(color = Color.White),
                visualTransformation = if (text2 == "PASSWORD") PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = if (text2 == "PASSWORD") KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
            )*/
            Spacer(modifier = Modifier.weight(0.1f))
        }
        Spacer(modifier = Modifier.weight(8.2f))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginTextFieldPW(
    text: State<String>,
    text2: String,
    focusState : FocusRequester,
    onValueChange: (String) -> Unit
) {
    val focusManage = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.weight(3f))
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            BasicTextField(
                value = text.value,
                onValueChange = onValueChange,
                singleLine = true,
                modifier = Modifier
                    .focusRequester(focusState)
                    .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                    .weight(1f)
                    .height(48.dp)
                    //.wrapContentHeight()
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    ),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                ),
                keyboardOptions = if (text2 == "PASSWORD") KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
                keyboardActions = KeyboardActions(
                    onDone = {focusManage.clearFocus()}
                ),
                visualTransformation = if (text2 == "PASSWORD") PasswordVisualTransformation() else VisualTransformation.None,
            ) {
                TextFieldDefaults.TextFieldDecorationBox(
                    value = text.value,
                    innerTextField = it,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = MutableInteractionSource(),
                    contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                        bottom = 5.dp
                    ),
                    placeholder = {
                        Text(
                            text = text2,
                            color = Color(0xFF989898),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.pretendard_regular))
                            ),
                            modifier = Modifier
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }
        Spacer(modifier = Modifier.weight(1.6f))
    }
}


@Composable
fun CustomButton(
    text: String,
    enable: Boolean,
    content: Color,
    back: Color,
    modifier: Modifier,
    onPress: () -> Unit
) {
    Button(
        onClick = onPress,
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
fun CopyRightByFlag(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = "@copyright by Flag",
            fontSize = 12.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight(400),
            color = Color(0xFF767676),
            textAlign = TextAlign.Center,
            letterSpacing = 0.3.sp,
            modifier = modifier,
        )
    }
}

@Composable
fun TittleWithBackArrow(text: String, modifier: Modifier, onBackClick: () -> Unit) {

    Row(
        Modifier, //horizontalArrangement = Arrangement.Center
    )
    {
        Spacer(Modifier.weight(0.12f))
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack, contentDescription = "back",
                Modifier
                    .height(26.dp)
                    .width(26.dp)
                    .weight(0.1f)
            )
        }
        Spacer(Modifier.weight(0.2f))
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
        Spacer(Modifier.weight(0.5f))
    }
}

@Composable
fun PortalEmail(
    text: State<String>,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text.value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                "포털 이메일 입력",
                style = TextStyle(
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
        //.heightIn(min = 46.dp)

    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldSearchBtn(
    graytext: String,
    textFieldIdValue: String,
    onValueChange: (String) -> Unit,
    trigger: Boolean,
    onPress: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(55.dp)
            .width(330.dp)
            .padding(top = 3.dp)
            .border(
                width = 1.dp, color = Color(0xFFBFBFBF),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TextField(
            value = textFieldIdValue,
            onValueChange = { idValue -> onValueChange(idValue) },
            placeholder = { Text(text = graytext, color = Color.Gray, fontSize = 14.sp) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent, // 포커스되었을 때의 밑줄 색상
                unfocusedIndicatorColor = Color.Transparent, // 포커스가 해제되었을 때의 밑줄 색상
                disabledIndicatorColor = Color.Transparent // 비활성화되었을 때의 밑줄 색상
            ),
            modifier = Modifier.weight(0.8f),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )
        Button(
            enabled = !trigger,
            onClick = onPress,
            modifier = Modifier
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
        Spacer(Modifier.weight(0.02f))
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
fun SendImg(id: Int) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(42.dp)
            .height(42.dp)
            .background(Color(0xFFF8F8F8), shape = RoundedCornerShape(25.dp))
    )
}

@Composable
fun TwoButtonDialog(
    contentText: String,
    leftText: String,
    rightText: String,
    leftonPress: () -> Unit,
    rightonPress: () -> Unit,
    image: Int
) {
    Dialog(
        onDismissRequest = leftonPress,
    ) {
        Column(
            modifier = Modifier
                .width(270.dp)
                .height(164.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "image description",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 24.dp)
            )
            Text(
                text = contentText,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight(500),
                color = Color(0xFF767676),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
            ) {

                Spacer(modifier = Modifier.weight(0.1f))
                Button(
                    onClick = leftonPress,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFF1F1F1),
                        contentColor = Color(0xFF767676)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                ) {
                    Text(
                        text = leftText,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                            fontWeight = FontWeight(600),
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(0.1f))
                Button(
                    onClick = rightonPress,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF2D64D8),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                ) {
                    Text(
                        text = rightText,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(0.1f))
            }
        }
    }

}

@Composable
fun MSG(text: String, color: Color) {
    Box(
        Modifier
            .padding(start = 6.dp, end = 6.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFDBDBDB),
                shape = RoundedCornerShape(size = 25.dp)
            )
            .background(
                color = color,
                shape = RoundedCornerShape(size = 25.dp)
            ),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                top = 10.dp,
                start = 17.dp,
                end = 17.dp,
                bottom = 12.dp
            ), // 패딩 추가,
            fontSize = 16.sp,
            lineHeight = 18.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight(400),
            color = Color(0xFF191919),
            maxLines = 5
        )
    }

}

@Composable
fun TimeText() {
    Text(
        text = SimpleDateFormat("HH:mm").format(System.currentTimeMillis()).toString(),
        fontSize = 12.sp,
        lineHeight = 14.sp,
        fontFamily = FontFamily(Font(R.font.kcc_chassam)),
        fontWeight = FontWeight(400),
        color = Color(0xFF767676),
        modifier = Modifier
            .width(32.dp)
            .height(14.dp)
    )
}

@Composable
fun OneButtonDialog(
    contentText: String,
    text: String,
    onPress: () -> Unit,
    image: Int
) {
    Dialog(
        onDismissRequest = onPress,
    ) {
        Column(
            modifier = Modifier
                .width(270.dp)
                .height(164.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "image description",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
            Text(
                text = contentText,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight(500),
                color = Color(0xFF767676),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
            ) {
                Spacer(modifier = Modifier.weight(0.1f))
                Button(
                    onClick = onPress,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF2D64D8),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                ) {
                    Text(
                        text = text,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(0.1f))
            }
        }
    }
}

@Composable
fun DrawerBottom( onPress: () -> Unit ) {
    Column(verticalArrangement = Arrangement.Bottom) {
        Spacer(modifier = Modifier.height(26.dp))
        Image(
            painter = painterResource(id = R.drawable.suchat),
            contentDescription = null,
            modifier = Modifier
                .width(58.dp)
                .height(15.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Copyright 2023. \nFlag inc. all rights reserved.",
            fontSize = 10.sp,
            lineHeight = 12.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            fontWeight = FontWeight(400),
            color = Color(0xFF767676),
            letterSpacing = 0.25.sp,
        )
        Button(
            onClick = { onPress() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(0.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "회원 탈퇴하기",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF767676),
                textAlign = TextAlign.Center,
                letterSpacing = 0.3.sp,
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                contentDescription = ""
            )

        }
    }
}


@Composable
fun DrawerProfile(name: String, mbti: String) {
    Column() {
        Image(
            painter = painterResource(id = R.drawable.profile_img),
            contentDescription = "",
            modifier = Modifier
                .width(49.dp)
                .height(49.dp)
        )

        Text(
            text = name,
            fontSize = 22.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily(Font(R.font.kcc_chassam)),
            fontWeight = FontWeight(400),
            color = Color(0xFF111111),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 10.dp)
        )

        Text(
            text = "# $mbti",
            fontSize = 14.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily(Font(R.font.kcc_chassam)),
            fontWeight = FontWeight(400),
            color = Color(0xFF989898),
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
    }
}


@Composable
fun DrawerMenu(image: Int, menuName: String, onPress: () -> Unit) {
    Button(
        onClick = { onPress() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp),
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            Icon(
                painter = painterResource(id = image),
                tint = Color.Gray,
                contentDescription = "",
                modifier = Modifier
                    .padding(
                        end = 6.dp
                    )
                    .width(25.dp)
                    .height(25.dp)
            )
            Text(
                text = menuName,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF111111),
                textAlign = TextAlign.Center,
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TwoButtonDialogPreview() {
    //TwoButtonDialog("신고하시겠습니까?", "취소", "신고하기", {}, {}, R.drawable.baseline_error_24)
    OneButtonDialog(
        contentText = "아이디 혹은 비밀번호가\n올바르지 않습니다.",
        text = "확인",
        onPress = { /*TODO*/ },
        image = R.drawable.baseline_error_24
    )
}

@Preview(showBackground = true)
@Composable
fun DrawerMenuPreview() {
    DrawerMenu(image = R.drawable.profile_img, menuName = "이용약관") {

    }
}

@Composable
fun RedWarning(warningText: String, modifier: Modifier) {
    Text(
        text = warningText,
        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        fontSize = 12.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFFFF0000),
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun TextFiledTitle(
    title: String,
    redTrueText: String,
    textModifier: Modifier,
    redBool: Boolean,
    redFalseText: String,
    redModifier: Modifier
) {
    Text(
        text = title,
        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
        fontSize = 16.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFF000000),
        textAlign = TextAlign.Left,
        modifier = textModifier
    )
    if (redBool) {
        RedWarning(
            redTrueText,
            modifier = redModifier
        )
    } else {
        RedWarning(
            redFalseText,
            modifier = redModifier
        )
    }
}

@Composable
fun VisibleText(textValue: State<String>, onValueChange: (String) -> Unit, placeholder: String) {
    val passwordVisible = remember { mutableStateOf(false) }

    Row() {
        Spacer(Modifier.weight(0.07f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(55.dp)
                .border(
                    width = 1.dp, color = Color(0xFFBFBFBF),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {

            TextField(
                value = textValue.value,
                onValueChange = { textValue -> onValueChange(textValue) },
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                placeholder = { Text(text = placeholder, color = Color.Gray, fontSize = 14.sp) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent, // 포커스되었을 때의 밑줄 색상
                    unfocusedIndicatorColor = Color.Transparent, // 포커스가 해제되었을 때의 밑줄 색상
                    disabledIndicatorColor = Color.Transparent // 비활성화되었을 때의 밑줄 색상
                ),
                shape = RoundedCornerShape(8.dp),
            )
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(
                    painter = painterResource(id = if (passwordVisible.value) R.drawable.visibility else R.drawable.visibility_off),
                    contentDescription = null // decorative element
                )
            }

        }
        Spacer(Modifier.weight(0.07f))
    }
}

@Preview(showBackground = true)
@Composable
fun VisibleTextPreview() {
    val sad: State<String> = remember {
        mutableStateOf("")
    }
    VisibleText(
        textValue = sad,
        onValueChange = {},
        placeholder = "안녕하세요",
    )
}

@Preview(showBackground = true)
@Composable
fun VisibleTextPreview1() {
    val sad: State<String> = remember {
        mutableStateOf("가나다라마바사자ㅇㄹ")
    }
    //LoginTextFieldID(text = sad, text2 = "ID", onValueChange = {})
}
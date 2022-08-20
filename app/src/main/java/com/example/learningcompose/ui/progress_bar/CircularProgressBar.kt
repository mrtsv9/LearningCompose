package com.example.learningcompose.ui.progress_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun CircularProgressBar(
    percentage: Float,
    fontSize: TextUnit = 36.sp,
    radius: Dp = 56.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 300
) {

    val scope = rememberCoroutineScope()

    var animationPlayed by remember {
        mutableStateOf(false)
    }

    var percentageAsState by remember {
        mutableStateOf(percentage)
    }

    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentageAsState else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay,
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.size(radius * 2),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(radius * 2)) {
                drawArc(
                    color = color,
                    startAngle = -90f,
                    360 * currentPercentage.value,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }
            Text(
                modifier = Modifier.clickable {
                    // this is side effect but idc for now, just practicing for a while
                    scope.launch {
                        if (animationPlayed) {
                            delay(200)
                            animationPlayed = false
                        } else {
                            delay(350)
                            percentageAsState = Random.nextInt(100).toFloat().div(100)
                            animationPlayed = true
                        }
                    }
                },
                text = (currentPercentage.value * 100).toInt().toString(),
                fontSize = fontSize,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }

}
package com.jdkgroup.quiz.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun LoadingQuestion() {
    val colors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.9f)
    )

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val widthPx = with(LocalDensity.current) {
            (maxWidth - 16.dp * 2f).toPx()
        }
        val heightPx = with(LocalDensity.current) {
            (200.dp - 16.dp).toPx()
        }
        val gradientWithPx = 0.3f * heightPx

        val shimmerAnimationSpec = infiniteRepeatable<Float>(
            animation = tween(
                durationMillis = 1000,
                delayMillis = 100,
                easing = LinearEasing
            )
        )

        val infiniteTransition = rememberInfiniteTransition()

        val xPosition = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = widthPx,
            animationSpec = shimmerAnimationSpec
        )

        val yPosition = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = heightPx,
            animationSpec = shimmerAnimationSpec
        )

        val brush = Brush.linearGradient(
            colors = colors,
            start = Offset(
                x = xPosition.value - gradientWithPx,
                y = yPosition.value - gradientWithPx
            ),
            end = Offset(x = xPosition.value, y = yPosition.value)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(8.dp)
                    .border(BorderStroke(1.dp, Color.Black)),
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .background(brush = brush)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) { }
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    //.background(Green)
                    .padding(8.dp),
            ) {
                for (i in 1..4) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, Color.DarkGray),
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(brush = brush)
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                //.background(Color.Yellow)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, Color.DarkGray),
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(brush = brush)
                    )
                }
            }
        }
    }
}

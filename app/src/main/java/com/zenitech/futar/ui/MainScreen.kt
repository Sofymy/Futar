package com.zenitech.futar.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.zenitech.futar.navigation.NavGraph
import com.zenitech.futar.ui.common.HomeHeader
import com.zenitech.futar.ui.common.HomeStatusDisplay
import com.zenitech.futar.ui.theme.BackgroundLightPurple
import com.zenitech.futar.ui.theme.MediumPurple

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val statusBarText = remember {
        mutableStateOf("")
    }
    val isLoggedIn = remember {
        mutableStateOf(false)
    }
    var brightness by remember { mutableFloatStateOf(1f) }

    Box(){
        Column(
            Modifier
                .background(BackgroundLightPurple)
                .fillMaxSize()
        ) {
            HomeHeader(
                isLoggedIn = isLoggedIn.value
            )
            Box(
                Modifier.weight(1f)
            ) {
                DotPatternBackground()
                NavGraph(
                    navController = navController,
                    onStatusBarChange = {
                        statusBarText.value = it
                    },
                    onLoggedInChange = {
                        isLoggedIn.value = it
                    },
                    brightness = brightness,
                    onBrightnessChange = {
                        brightness = it
                    }
                )
            }
            HomeStatusDisplay(
                modifier = Modifier,
                text = statusBarText.value
            )
        }
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 1 - brightness)))

    }
}


@Composable
fun DotPatternBackground() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val dotSize = 3.dp.toPx()
        val dotSpacing = 4.dp.toPx()

        for (y in 0 until canvasHeight.toInt() step dotSpacing.toInt()) {
            val xOffset = if ((y / dotSpacing.toInt()) % 2 == 0) 0 else (dotSpacing / 2).toInt()

            for (x in xOffset until canvasWidth.toInt() step dotSpacing.toInt()) {
                drawCircle(
                    color = MediumPurple.copy(alpha = (canvasHeight - y) / canvasHeight),
                    radius = dotSize / 2,
                    center = Offset(x.toFloat(), y.toFloat())
                )
            }
        }
    }
}

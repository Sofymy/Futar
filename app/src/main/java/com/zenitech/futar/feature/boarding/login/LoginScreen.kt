package com.zenitech.futar.feature.boarding.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.zenitech.futar.feature.boarding.traffic_number.Instruction
import com.zenitech.futar.feature.boarding.traffic_number.NumberInput
import com.zenitech.futar.ui.theme.DarkTicketYellow
import com.zenitech.futar.ui.theme.TicketYellow

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun LoginScreenPreview() {
    LoginScreen {}
}

@Composable
fun LoginScreen(
    onNavigateToTrafficNumberScreen: () -> Unit
) {
    var identifier by remember { mutableStateOf("") }
    val isIdentifierCorrect = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(identifier) {
        if(identifier.length == 5){
            isIdentifierCorrect.value = checkIdentifier(identifier)
        }
    }

    LaunchedEffect(isIdentifierCorrect.value) {
        if(isIdentifierCorrect.value){
            onNavigateToTrafficNumberScreen()
        }
    }

    Column {
        Instruction(text = "Azonosító")
        NumberInput(
            modifier = Modifier.weight(1f),
            number = identifier,
            onNumberChange = {
                    newNumber -> identifier = newNumber
                             },
            isNumberCorrect = isIdentifierCorrect.value,
            maxNumber = 5
        )
    }
}

fun checkIdentifier(
    identifier: String
): Boolean {
    return identifier == "12345"
}


@Composable
fun LoginLock(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val bodyWidth = canvasWidth * 0.6f
        val bodyHeight = canvasHeight * 0.5f


        val handlePath = Path().apply {
            arcTo(
                rect = Rect(
                    Offset((canvasWidth - bodyWidth * 0.6f) / 2, canvasHeight * 0.5f - bodyHeight * .5f),
                    Size(bodyWidth * 0.6f, bodyHeight)
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
        }

        drawPath(
            path = handlePath,
            color = Color.DarkGray,
            style = Stroke(width = 60f)
        )
        drawPath(
            path = handlePath,
            color = Color.Gray,
            style = Stroke(width = 35f)
        )

        drawRoundRect(
            style = Stroke(width = 30f),
            color = DarkTicketYellow,
            topLeft = Offset((canvasWidth - bodyWidth) / 2, canvasHeight * 0.5f),
            size = Size(bodyWidth, bodyHeight),
            cornerRadius = CornerRadius(20f, 20f)
        )

        drawRoundRect(
            color = TicketYellow,
            topLeft = Offset((canvasWidth - bodyWidth) / 2, canvasHeight * 0.5f),
            size = Size(bodyWidth, bodyHeight),
            cornerRadius = CornerRadius(20f, 20f)
        )

    }
}

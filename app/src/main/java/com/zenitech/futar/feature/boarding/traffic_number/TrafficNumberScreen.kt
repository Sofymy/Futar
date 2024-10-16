package com.zenitech.futar.feature.boarding.traffic_number

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardBackspace
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.theme.AlertBackgroundRed
import com.zenitech.futar.ui.theme.AlertRed
import com.zenitech.futar.ui.theme.Purple
import kotlinx.coroutines.Job

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun TrafficNumberScreenPreview() {
    TrafficNumberScreen({}) {}
}

@Composable
fun TrafficNumberScreen(
    onBack: () -> Unit,
    onNavigateToJourney: () -> Unit,
) {
    var trafficNumber by remember { mutableStateOf("") }
    val isTrafficNumberCorrect = remember { mutableStateOf(false) }

    LaunchedEffect(trafficNumber) {
        if (trafficNumber.length == 8) {
            isTrafficNumberCorrect.value = checkTrafficNumber(trafficNumber)
        }
    }

    LaunchedEffect(isTrafficNumberCorrect.value) {
        if (isTrafficNumberCorrect.value) {
            onNavigateToJourney()
        }
    }

    Column {
        Instruction("Forgalmi szám", onBack = onBack)
        NumberInput(
            modifier = Modifier.weight(1f),
            number = trafficNumber,
            onNumberChange = { newNumber -> trafficNumber = newNumber },
            isNumberCorrect = isTrafficNumberCorrect.value,
            maxNumber = 8,
            errorMessage = "Hibás forgalmi szám!"
        )
    }
}

fun checkTrafficNumber(trafficNumber: String): Boolean {
    return trafficNumber == "11111111"
}

@Composable
fun Instruction(
    text: String,
    onBack: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(1.dp, Purple.copy(0.3f), RoundedCornerShape(10.dp))
            .padding(20.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = {
                onBack()
            }
        ){
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, Modifier.size(25.dp))
        }
        Text(
            text = text,
            fontSize = 25.sp,
            color = Purple,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EnteredInput(
    modifier: Modifier = Modifier,
    number: String,
    isNumberCorrect: Boolean,
    maxNumber: Int
) {
    val inputBackground = animateColorAsState(if (isNumberCorrect || number.length < maxNumber) Color.White else AlertBackgroundRed,
        label = ""
    )
    val inputColor = animateColorAsState(if (isNumberCorrect || number.length < maxNumber) Purple else AlertRed,
        label = ""
    )

    Column(
        modifier = modifier
            .background(inputBackground.value, RoundedCornerShape(10.dp))
            .border(1.dp, Purple.copy(0.3f), RoundedCornerShape(10.dp))
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = number.ifEmpty { " " },
            fontSize = 40.sp,
            color = inputColor.value,
        )
    }
}

@Composable
fun NumberInput(
    modifier: Modifier = Modifier,
    number: String,
    onNumberChange: (String) -> Unit,
    isNumberCorrect: Boolean,
    maxNumber: Int,
    errorMessage: String
) {
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxHeight()) {
            InputDisplay(modifier = Modifier.weight(1.2f), number, isNumberCorrect, maxNumber, errorMessage)
            InputButtons(modifier = Modifier.weight(1f), number, onNumberChange, maxNumber)
        }
    }
}

@Composable
fun InputDisplay(
    modifier: Modifier,
    number: String,
    isNumberCorrect: Boolean,
    maxNumber: Int,
    errorMessage: String
) {
    Column(modifier = modifier
        .padding(end = 20.dp)
        .clip(RoundedCornerShape(10.dp))) {
        EnteredInput(modifier = Modifier
            .weight(0.25f)
            .fillMaxWidth()
            .padding(bottom = 16.dp), number, isNumberCorrect, maxNumber)
        Result(modifier = Modifier.weight(0.75f, true), isError = !isNumberCorrect && number.length == maxNumber, errorMessage = errorMessage)
    }
}

@Composable
fun InputButtons(
    modifier: Modifier,
    number: String,
    onNumberChange: (String) -> Unit,
    maxNumber: Int,
) {
    fun handleNumberClick(newNumber: String) {
        if (number.length < maxNumber) {
            onNumberChange(number + newNumber)
        }
    }

    fun handleClearClick() {
        onNumberChange("")
    }

    fun handleBackspaceClick() {
        if (number.isNotEmpty()) {
            onNumberChange(number.dropLast(1))
        }
    }

    Column(modifier = modifier.fillMaxHeight()) {
        listOf("123", "456", "789").forEach { row ->
            NumberButtonRow(row, ::handleNumberClick, modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            NumberButton("C", onClick = ::handleClearClick, modifier = Modifier.weight(1f))
            NumberButton("0", onClick = { handleNumberClick("0") }, modifier = Modifier.weight(1f))
            NumberButton("Back", onClick = ::handleBackspaceClick, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun NumberButtonRow(
    numbers: String,
    onNumberClick: (String) -> Unit,
    modifier: Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        numbers.forEach { number ->
            NumberButton(number.toString(), onClick = { onNumberClick(number.toString()) }, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun Result(
    modifier: Modifier,
    isError: Boolean,
    errorMessage: String
) {
    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AnimatedVisibility(visible = isError) {
            Text(
                text = errorMessage,
                color = AlertRed,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}

@Composable
fun NumberButton(
    number: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = animateColorAsState(if (isPressed) Purple else Color.White, label = "")
    val contentColor = animateColorAsState(targetValue = if (isPressed) Color.White else Purple,
        label = ""
    )

    Box(
        modifier = modifier
            .padding(bottom = 16.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .clickable(interactionSource = interactionSource, indication = null) { onClick() }
            .border(3.dp, Purple, RoundedCornerShape(20.dp))
            .background(color = backgroundColor.value, shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (number == "Back") {
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardBackspace, contentDescription = null, tint = Purple, modifier = Modifier
                .fillMaxSize()
                .padding(20.dp))
        } else {
            Text(text = number, color = contentColor.value, fontSize = 40.sp, textAlign = TextAlign.Center)
        }
    }
}

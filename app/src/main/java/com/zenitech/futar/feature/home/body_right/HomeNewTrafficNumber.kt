package com.zenitech.futar.feature.home.body_right

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardBackspace
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.theme.Purple
import kotlinx.coroutines.delay

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun HomeNewTrafficNumberPreview() {
    HomeNewTrafficNumber({})
}

@Composable
fun HomeNewTrafficNumber(
    onEnterClick: () -> Unit
) {

    var trafficNumber by remember { mutableStateOf(" ") }

    LaunchedEffect(
        trafficNumber
    ) {
        if(trafficNumber.length == 8){
            delay(1000)
            onEnterClick()
        }
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(
                text = "Új forgalmi szám",
                color = Purple,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        }
        HorizontalDivider(
            color = Purple.copy(0.2f),
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Column(
            Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight(.95f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = trafficNumber, color = Purple, fontSize = 40.sp, modifier = Modifier.padding(20.dp))
            HomeInputButtons(modifier = Modifier.fillMaxSize(), trafficNumber,
                onEnterClick = onEnterClick,
                { newNumber -> trafficNumber = newNumber }, 8)
        }
    }
}


@Composable
fun HomeInputButtons(
    modifier: Modifier,
    number: String,
    onEnterClick: () -> Unit,
    onNumberChange: (String) -> Unit,
    maxNumber: Int
) {
    fun handleNumberClick(newNumber: String) {
        if (number.length < maxNumber) {
            onNumberChange(number + newNumber)
        }
    }

    fun handleClearClick() {
        onNumberChange("")
    }


    fun handleEnterClick() {
        onEnterClick()
    }

    fun handleBackspaceClick() {
        if (number.isNotEmpty()) {
            onNumberChange(number.dropLast(1))
        }
    }

    Column(modifier = modifier.fillMaxHeight()) {
        listOf("123", "456", "789").forEach { row ->
            HomeNumberButtonRow(row, ::handleNumberClick, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(10.dp))
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            HomeNumberButton("C", onClick = ::handleClearClick, modifier = Modifier.weight(1f))
            HomeNumberButton("0", onClick = { handleNumberClick("0") }, modifier = Modifier.weight(1f))
            HomeNumberButton("Back", onClick = ::handleBackspaceClick, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun HomeNumberButtonRow(
    numbers: String,
    onNumberClick: (String) -> Unit,
    modifier: Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        numbers.forEach { number ->
            HomeNumberButton(number.toString(), onClick = { onNumberClick(number.toString()) }, modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun HomeNumberButton(
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
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .clickable(interactionSource = interactionSource, indication = null) { onClick() }
            .border(3.dp, Purple, RoundedCornerShape(20.dp))
            .background(color = backgroundColor.value, shape = RoundedCornerShape(20.dp))
        ,
        contentAlignment = Alignment.Center
    ) {
        if (number == "Back") {
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardBackspace, contentDescription = null, tint = Purple, modifier = Modifier
                .fillMaxSize()
                .padding(10.dp))
        } else {
            Text(text = number, color = contentColor.value, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))
        }
    }
}
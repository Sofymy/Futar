package com.zenitech.futar.feature.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.feature.home.HomeDotPatternBackground
import com.zenitech.futar.ui.common.header.HomeHeader
import com.zenitech.futar.ui.common.status_bar.HomeStatusDisplay
import com.zenitech.futar.ui.theme.LightMediumPurple
import com.zenitech.futar.ui.theme.LightPurple
import com.zenitech.futar.ui.theme.MediumPurple
import com.zenitech.futar.ui.theme.Purple

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

@Composable
fun LoginScreen() {
    Column(
        Modifier
            .background(LightPurple)
            .fillMaxSize()
    ) {
        HomeHeader(
            isLoggedIn = false
        )
        Box {
            HomeDotPatternBackground()
            Column {
                LoginInstruction()
                LoginNumberOfShiftInput(
                    modifier = Modifier.weight(1f)
                )
                HomeStatusDisplay(
                    modifier = Modifier,
                    text = "Kérem, adja meg a jármű forgalmi számát!"
                )
            }
        }
    }
}

@Composable
fun LoginNumberOfShiftInput(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(start = 20.dp, top = 0.dp, bottom = 20.dp, end = 20.dp)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Column(
                Modifier.weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .background(Color.White)
                    .border(1.dp, Purple.copy(0.3f), RoundedCornerShape(10.dp))
                    .padding(30.dp)
                    .fillMaxSize()
            ) {

            }
            Column(Modifier.weight(1f)) {
                LoginNumberOfShiftInputNumbers(
                    {}, {}, {}
                )
            }
        }
    }
}

@Composable
fun LoginNumberOfShiftInputNumbers(
    onNumberClick: (String) -> Unit,
    onClearClick: () -> Unit,
    onBackspaceClick: () -> Unit
) {
    Column {
        Row {
            NumberButton(number = "1", onClick = { onNumberClick("1") }, modifier = Modifier.weight(1f))
            NumberButton(number = "2", onClick = { onNumberClick("2") }, modifier = Modifier.weight(1f))
            NumberButton(number = "3", onClick = { onNumberClick("3") }, modifier = Modifier.weight(1f))
        }
        Row {
            NumberButton(number = "4", onClick = { onNumberClick("4") }, modifier = Modifier.weight(1f))
            NumberButton(number = "5", onClick = { onNumberClick("5") }, modifier = Modifier.weight(1f))
            NumberButton(number = "6", onClick = { onNumberClick("6") }, modifier = Modifier.weight(1f))
        }
        Row {
            NumberButton(number = "7", onClick = { onNumberClick("7") }, modifier = Modifier.weight(1f))
            NumberButton(number = "8", onClick = { onNumberClick("8") }, modifier = Modifier.weight(1f))
            NumberButton(number = "9", onClick = { onNumberClick("9") }, modifier = Modifier.weight(1f))
        }
        Row {
            NumberButton(number = "C", onClick = onClearClick, modifier = Modifier.weight(1f))
            NumberButton(number = "0", onClick = { onNumberClick("0") }, modifier = Modifier.weight(1f))
            NumberButton(number = "Back", onClick = onBackspaceClick, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun NumberButton(
    number: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .padding(8.dp)
            .background(
                Color.White,
                RoundedCornerShape(20.dp)
            )
        ,
        border = BorderStroke(3.dp, Purple),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        onClick = {  },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
    ) {
        Text(text = number, color = Purple, modifier = Modifier.padding(vertical = 15.dp), fontSize = 30.sp)
    }
}


@Composable
fun LoginInstruction() {
    Row(
        modifier = Modifier
            .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(1.dp, Purple.copy(0.3f), RoundedCornerShape(10.dp))
            .padding(30.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Jármű forgalmi száma", fontSize = 20.sp, color = Purple, fontWeight = FontWeight.Bold)
    }
}

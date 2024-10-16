package com.zenitech.futar.feature.boarding.traffic_number

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.feature.boarding.welcome.WelcomeButton
import com.zenitech.futar.ui.theme.Purple

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun AutomaticTrafficNumberScreenPreview() {
    AutomaticTrafficNumberScreen({}, {}) {}
}

@Composable
fun AutomaticTrafficNumberScreen(
    onBack: () -> Unit,
    onNavigateToTrafficNumber: () -> Unit,
    onNavigateToJourney: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .weight(1f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Instruction("Forgalmi szám", onBack = onBack)
            Column(
                Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DriverIdentityNumber()
                Spacer(modifier = Modifier.height(40.dp))
                WelcomeButton(text = "Manuális megadás", Modifier, onClick = onNavigateToTrafficNumber)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.End), horizontalArrangement = Arrangement.End) {
                WelcomeButton(text = "Elfogadás", Modifier.padding(end = 20.dp, bottom = 20.dp), onClick = onNavigateToJourney)
            }
        }
    }
}

@Composable
fun DriverIdentityNumber() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Kovács András", color = Purple, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Forgalmi szám: 11111111", color = Purple, fontSize = 30.sp)
    }
}
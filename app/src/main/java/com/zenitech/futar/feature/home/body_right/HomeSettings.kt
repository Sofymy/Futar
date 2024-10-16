package com.zenitech.futar.feature.home.body_right

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.HomeSecondaryButton
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.LightPurple
import com.zenitech.futar.ui.theme.Purple


@Composable
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
fun HomeSettingsPreview(){
    FutarTheme {
        HomeSettings({},{},{},{},{}, {}, true)
    }
}


@Composable
fun HomeSettings(
    onNavigateToHomeDisplay: () -> Unit,
    onNavigateToDataSynchronization: () -> Unit,
    onNavigateToBrightness: () -> Unit,
    onNavigateToVehicleStatus: () -> Unit,
    onNavigateToDeviceStatus: () -> Unit,
    onRazziaChanged: () -> Unit,
    razzia: Boolean
) {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .simpleVerticalScrollbar(state)
            .fillMaxSize(),
        state = state
    ){
        item { Spacer(modifier = Modifier.height(30.dp)) }

        item {
            HomeSettingsButtonRow(
                button1Text = "Adat szinkronizáció",
                button2Text = "Fényerő",
                onClickButton1 = onNavigateToDataSynchronization,
                onClickButton2 = onNavigateToBrightness
            )
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            HomeSettingsButtonRow(
                button1Text = "Jármű állapot",
                button2Text = "Eszköz állapot",
                onClickButton2 = onNavigateToDeviceStatus,
                onClickButton1 = onNavigateToVehicleStatus
            )
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                HomeSettingsRazziaSwitchButton(
                    modifier = Modifier.weight(1f),
                    razzia = razzia,
                    onRazziaChanged = onRazziaChanged
                )
                HomeSecondaryButton(modifier = Modifier.weight(1f), onClick = onNavigateToHomeDisplay) {
                    Text(text = "Kijelzők", color = Purple, fontSize = 20.sp)
                }
            }
        }

        item {
            HomeSettingsVersionInfo()
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
fun HomeSettingsVersionInfo() {
    Column(modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp)) {
        Text(text = "Verzió: 54.3.343", fontSize = 20.sp, color = Purple)
        Text(text = "Nem áll rendelkezésre új adatcsomag", fontSize = 20.sp, color = Purple)
    }
}

@Composable
fun HomeSettingsButtonRow(
    button1Text: String,
    button2Text: String,
    onClickButton2: () -> Unit,
    onClickButton1: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        HomeSecondaryButton(modifier = Modifier.weight(1f), onClick = onClickButton1) {
            Text(button1Text, color = Purple, fontSize = 20.sp)
        }
        HomeSecondaryButton(modifier = Modifier.weight(1f), onClick = onClickButton2) {
            Text(button2Text, color = Purple, fontSize = 20.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSettingsRazziaSwitchButton(
    modifier: Modifier,
    razzia: Boolean,
    onRazziaChanged: () -> Unit
) {
    val buttonColor = animateColorAsState(targetValue = if(razzia) Purple else LightPurple,
        label = ""
    )
    val contentColor = animateColorAsState(targetValue = if(razzia) Color.White else Purple,
        label = ""
    )

    HomeSecondaryButton(
        onClick = {
            onRazziaChanged()
        },
        modifier = modifier,
        containerColor = buttonColor.value
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Text("Razzia", color = contentColor.value, fontSize = 20.sp)
        }
    }
}

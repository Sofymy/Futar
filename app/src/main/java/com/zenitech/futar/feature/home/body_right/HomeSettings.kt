package com.zenitech.futar.feature.home.body_right

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.HomeSecondaryButton
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.Purple


@Composable
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
fun HomeSettingsPreview(){
    FutarTheme {
        HomeSettings()
    }
}

@Composable
fun HomeSettings() {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.simpleVerticalScrollbar(state).fillMaxSize(),
        state = state
    ){
        item {
            HomeSettingsVersionInfo()
        }
        item {
            HorizontalDivider(color = Purple.copy(.2f), modifier = Modifier.padding(vertical = 20.dp))
        }
        item {
            HomeSettingsButtonRow(
                button1Text = "Adat szinkronizáció",
                button2Text = "Fényerő"
            )
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            HomeSettingsButtonRow(
                button1Text = "Jármű állapot",
                button2Text = "Eszköz állapot"
            )
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            HomeSettingsButtonRow(
                button1Text = "Razzia",
                button2Text = "Kijelzők"
            )
        }
        item { Spacer(modifier = Modifier.height(80.dp)) }
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
fun HomeSettingsButtonRow(button1Text: String, button2Text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        HomeSecondaryButton(modifier = Modifier.weight(1f)) {
            Text(button1Text, color = Purple, fontSize = 20.sp)
        }
        HomeSecondaryButton(modifier = Modifier.weight(1f)) {
            Text(button2Text, color = Purple, fontSize = 20.sp)
        }
    }
}

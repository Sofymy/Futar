package com.zenitech.futar.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenitech.futar.feature.home.body_left.HomeJourney
import com.zenitech.futar.feature.home.body_right.HomeActivity
import com.zenitech.futar.feature.home.body_right.HomeMessages
import com.zenitech.futar.feature.home.body_right.HomeMessagesNew
import com.zenitech.futar.feature.home.body_right.HomeSettings
import com.zenitech.futar.feature.home.body_right.HomeStoredSounds
import com.zenitech.futar.ui.HomePrimaryOutlinedButton
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.Purple


@Composable
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
fun HomeScreenPreview(){
    FutarTheme {
        HomeScreen(isRazziaMode = true)
    }
}

@Composable
fun HomeScreen(
    isRazziaMode: Boolean = true
){
    HomeContent(
        isRazziaMode = isRazziaMode
    )
}

@Composable
fun HomeContent(isRazziaMode: Boolean = true) {

    val selectedButton = remember {
        mutableStateOf(HomeButton.UZENETEK)
    }

    HomeBody(
        selectedButton = selectedButton.value,
        onSelectedButtonChanged = {
            selectedButton.value = it
        }
    )
}


enum class HomeButton(val text: String, val isMainButton: Boolean) {
    TEVEKENYSEG("Tevékenység", true),
    TAROLT_HANGOK("Tárolt hangok", true),
    UZENETEK("Üzenetek", true),
    UZENET_KULDESE("Üzenetek", false),
    BEALLITASOK("Beállítások", true),
}


@Composable
fun HomeBody(
    selectedButton: HomeButton,
    onSelectedButtonChanged: (HomeButton) -> Unit
) {
    Box(
        Modifier
            .clip(
                RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
            )
    ) {
        Column {
            HomeButtons(
                selectedButton = selectedButton
            ) {
                 onSelectedButtonChanged(it)
            }
            Row(
                Modifier.padding(top = 10.dp, bottom = 20.dp, end = 30.dp, start = 30.dp)
            ) {
                HomeJourney()
                HomeSelectedButtonContent(
                    selectedButton = selectedButton,
                    onNavigateToCreateMessage = {
                        onSelectedButtonChanged(HomeButton.UZENET_KULDESE)
                    }
                )
            }
        }
    }
}


@Composable
fun HomeButtons(
    selectedButton: HomeButton,
    onSelectButton: (HomeButton) -> Unit,
) {
    Row(Modifier.padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 10.dp)) {
        HomeButton.entries.filter { it.isMainButton }
            .forEach { button ->
                HomePrimaryOutlinedButton(button = button, modifier = Modifier.weight(1f), onSelectButton = onSelectButton, isSelected = button.text == selectedButton.text)
                if(button != HomeButton.entries.last()) Spacer(modifier = Modifier.width(20.dp))
            }
    }
}

@Composable
fun HomeSelectedButtonContent(
    modifier: Modifier = Modifier,
    selectedButton: HomeButton,
    onNavigateToCreateMessage: () -> Unit
) {
    Column {
        Column(
            modifier = modifier
                .padding(start = 15.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(1.dp, Purple.copy(.2f), RoundedCornerShape(30.dp))
                .background(White, RoundedCornerShape(30.dp))
                .fillMaxWidth()
                .weight(1f)
        ) {
            when(selectedButton){
                HomeButton.TEVEKENYSEG -> {
                    HomeActivity()
                }
                HomeButton.UZENETEK -> {
                    HomeMessages(
                        navigateToCreateMessage = onNavigateToCreateMessage
                    )
                }
                HomeButton.TAROLT_HANGOK -> {
                    HomeStoredSounds()
                }
                HomeButton.BEALLITASOK -> {
                    HomeSettings()
                }

                HomeButton.UZENET_KULDESE -> {
                    HomeMessagesNew()
                }
            }
        }
    }
}
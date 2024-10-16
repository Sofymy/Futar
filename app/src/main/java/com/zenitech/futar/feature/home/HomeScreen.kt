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
import com.zenitech.futar.feature.home.body_right.HomeBrightness
import com.zenitech.futar.feature.home.body_right.HomeDataSynchronization
import com.zenitech.futar.feature.home.body_right.HomeDeviceStatus
import com.zenitech.futar.feature.home.body_right.HomeDisplay
import com.zenitech.futar.feature.home.body_right.HomeFuel
import com.zenitech.futar.feature.home.body_right.HomeMessages
import com.zenitech.futar.feature.home.body_right.HomeMessagesNew
import com.zenitech.futar.feature.home.body_right.HomeNewTrafficNumber
import com.zenitech.futar.feature.home.body_right.HomeOtherRoutes
import com.zenitech.futar.feature.home.body_right.HomeRoutesOfJourney
import com.zenitech.futar.feature.home.body_right.HomeSettings
import com.zenitech.futar.feature.home.body_right.HomeStoredSounds
import com.zenitech.futar.feature.home.body_right.HomeVehicleStatus
import com.zenitech.futar.ui.HomePrimaryOutlinedButton
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.Purple


@Composable
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
fun HomeScreenPreview(){
    FutarTheme {
        HomeScreen(
            onBrightnessChange = {  },
            brightness = 1f,
            razzia = true,
            onRazziaChange = { },
            onLogout = { }
        )
    }
}

@Composable
fun HomeScreen(
    onBrightnessChange: (Float) -> Unit,
    brightness: Float,
    razzia: Boolean,
    onRazziaChange: () -> Unit,
    onLogout: () -> Unit
){
    HomeContent(
        onBrightnessChange = onBrightnessChange,
        brightness = brightness,
        onRazziaChange = onRazziaChange,
        razzia = razzia,
        onLogout = onLogout
    )
}

@Composable
fun HomeContent(
    onBrightnessChange: (Float) -> Unit,
    brightness: Float,
    razzia: Boolean,
    onRazziaChange: () -> Unit,
    onLogout: () -> Unit
) {

    val selectedButton = remember {
        mutableStateOf(HomeButton.TEVEKENYSEG)
    }



    HomeBody(
        selectedButton = selectedButton.value,
        onSelectedButtonChanged = {
            selectedButton.value = it
        },
        onBrightnessChange = onBrightnessChange,
        brightness = brightness,
        onRazziaChange = onRazziaChange,
        razzia = razzia,
        onLogout = onLogout
    )
}


enum class HomeButton(val text: String, val isMainButton: Boolean = false) {
    TEVEKENYSEG("Tevékenység", true),
    TAROLT_HANGOK("Tárolt hangok", true),
    UZENETEK("Üzenetek", true),
    BEALLITASOK("Beállítások", true),
    UZENET_KULDESE("Üzenetek"),
    ADAT_SZINKRONIZACIO("Beállítások"),
    FENYERO("Beállítások"),
    JARMU_ALLAPOT("Beállítások"),
    ESZKOZ_ALLAPOT("Beállítások"),
    RAZZIA("Beállítások"),
    KIJELZOK("Beállítások"),
    NAVIGACIO("Tevékenység"),
    VISZONYLAT_UTVONALAI("Tevékenység"),
    EGYEB_UTVONALAK("Tevékenység"),
    UZEMANYAG("Tevékenység"),
    FORGALMI_SZAM("Tevékenység"),
}


@Composable
fun HomeBody(
    selectedButton: HomeButton,
    onSelectedButtonChanged: (HomeButton) -> Unit,
    brightness: Float,
    onBrightnessChange: (Float) -> Unit,
    razzia: Boolean,
    onRazziaChange: () -> Unit,
    onLogout: () -> Unit
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
                    },
                    onNavigateToDataSynchronization = {
                        onSelectedButtonChanged(HomeButton.ADAT_SZINKRONIZACIO)
                    },
                    onNavigateToVehicleStatus = {
                        onSelectedButtonChanged(HomeButton.JARMU_ALLAPOT)
                    },
                    onNavigateToDeviceStatus = {
                        onSelectedButtonChanged(HomeButton.ESZKOZ_ALLAPOT)
                    },
                    onNavigateToBrightness = {
                        onSelectedButtonChanged(HomeButton.FENYERO)
                    },
                    onNavigateToDisplay = {
                        onSelectedButtonChanged(HomeButton.KIJELZOK)
                    },
                    onClickRoutesOfJourney = {
                        onSelectedButtonChanged(HomeButton.VISZONYLAT_UTVONALAI)
                    },
                    onClickNewTrafficNumber = {
                        onSelectedButtonChanged(HomeButton.FORGALMI_SZAM)
                    },
                    onEnterClick = {
                        onSelectedButtonChanged(HomeButton.TEVEKENYSEG)
                    },
                    onClickFuel = {
                        onSelectedButtonChanged(HomeButton.UZEMANYAG)
                    },
                    onClickOtherRoutes = {
                        onSelectedButtonChanged(HomeButton.EGYEB_UTVONALAK)
                    },
                    onBrightnessChange = onBrightnessChange,
                    brightness = brightness,
                    onRazziaChange = onRazziaChange,
                    razzia = razzia,
                    onLogout = onLogout
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
    onNavigateToCreateMessage: () -> Unit,
    onNavigateToDataSynchronization: () -> Unit,
    onNavigateToBrightness: () -> Unit,
    onNavigateToVehicleStatus: () -> Unit,
    onNavigateToDeviceStatus: () -> Unit,
    onNavigateToDisplay: () -> Unit,
    onClickRoutesOfJourney: () -> Unit,
    onClickNewTrafficNumber: () -> Unit,
    onClickFuel: () -> Unit,
    onClickOtherRoutes: () -> Unit,
    onEnterClick: () -> Unit,
    onBrightnessChange: (Float) -> Unit,
    brightness: Float,
    onRazziaChange: () -> Unit,
    razzia: Boolean,
    onLogout: () -> Unit
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
                    HomeActivity(
                        onLogout = onLogout,
                        onClickRoutesOfJourney = onClickRoutesOfJourney,
                        onClickNewTrafficNumber = onClickNewTrafficNumber,
                        onClickFuel = onClickFuel,
                        onClickOtherRoutes = onClickOtherRoutes
                    )
                }
                HomeButton.UZENETEK -> {
                    HomeMessages(
                        onNavigateToCreateMessage = onNavigateToCreateMessage
                    )
                }
                HomeButton.TAROLT_HANGOK -> {
                    HomeStoredSounds()
                }
                HomeButton.BEALLITASOK -> {
                    HomeSettings(
                        onNavigateToDataSynchronization = onNavigateToDataSynchronization,
                        onNavigateToBrightness = onNavigateToBrightness,
                        onNavigateToDeviceStatus = onNavigateToDeviceStatus,
                        onNavigateToVehicleStatus = onNavigateToVehicleStatus,
                        onNavigateToHomeDisplay = onNavigateToDisplay,
                        razzia = razzia,
                        onRazziaChanged = onRazziaChange
                    )
                }

                HomeButton.UZENET_KULDESE -> {
                    HomeMessagesNew()
                }

                HomeButton.ADAT_SZINKRONIZACIO -> {
                    HomeDataSynchronization()
                }
                HomeButton.FENYERO -> {
                    HomeBrightness(
                        onBrightnessChange = onBrightnessChange,
                        brightness = brightness
                    )
                }
                HomeButton.JARMU_ALLAPOT -> {
                    HomeVehicleStatus()
                }
                HomeButton.ESZKOZ_ALLAPOT -> {
                    HomeDeviceStatus()
                }
                HomeButton.RAZZIA -> {

                }
                HomeButton.KIJELZOK -> {
                    HomeDisplay()
                }

                HomeButton.NAVIGACIO -> {

                }
                HomeButton.VISZONYLAT_UTVONALAI -> {
                    HomeRoutesOfJourney()
                }
                HomeButton.EGYEB_UTVONALAK -> {
                    HomeOtherRoutes()
                }
                HomeButton.UZEMANYAG -> {
                    HomeFuel()
                }
                HomeButton.FORGALMI_SZAM -> {
                    HomeNewTrafficNumber(
                        onEnterClick = onEnterClick
                    )
                }
            }
        }
    }
}
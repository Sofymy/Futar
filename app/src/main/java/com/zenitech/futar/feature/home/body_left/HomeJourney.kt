package com.zenitech.futar.feature.home.body_left

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.feature.home.body_right.HomeSettings
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.AlertBackgroundRed
import com.zenitech.futar.ui.theme.AlertRed
import com.zenitech.futar.ui.theme.BusBlue
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.Green
import com.zenitech.futar.ui.theme.LightPurple
import com.zenitech.futar.ui.theme.Purple


@Composable
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
fun HomeJourneyPreview(){
    FutarTheme {
        HomeJourney()
    }
}

@Composable
fun HomeJourney(
    modifier: Modifier = Modifier
) {
    HomeJourneyBox {
        HomeJourneyLazyColumn(modifier)
    }
}

@Composable
fun HomeJourneyBox(content: @Composable () -> Unit) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        content()
    }
}

@Composable
fun HomeJourneyLazyColumn(modifier: Modifier = Modifier) {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = modifier
            .padding(end = 15.dp)
            .clip(RoundedCornerShape(30.dp))
            .simpleVerticalScrollbar(state)
            .clip(RoundedCornerShape(30.dp))
            .border(1.dp, Purple.copy(.2f), RoundedCornerShape(30.dp))
            .background(White, RoundedCornerShape(30.dp))
            .fillMaxWidth(.45f)
            .fillMaxHeight(),
        state = state
    ) {
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item { HomeJourneyStationsVerticalProgressLine() }
        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}


data class Station(
    val name: String,
    val time: String,
    val isArrived: Boolean,
    val isTerminus: Boolean = false,
    val isNext: Boolean = false
)


@Composable
fun HomeJourneyStationsVerticalProgressLine() {
    val stations = listOf(
        Station(name = "Nagytétény, ipartelep", time = "18:41", isArrived = false, isTerminus = true),
        Station(name = "Bányalég utca", time = "18:38", isArrived = false),
        Station(name = "Nagytétényi út", time = "18:32", isArrived = false, isNext = true),
        Station(name = "Növény utca", time = "18:29", isArrived = true),
        Station(name = "Nagytétényi út", time = "18:26", isArrived = true),
        Station(name = "Kossuth Lajos utca", time = "18:23", isArrived = true),
        Station(name = "Leányka utca", time = "18:20", isArrived = true),
        Station(name = "felüljáró", time = "18:17", isArrived = true),
        Station(name = "Kitérő út", time = "18:14", isArrived = true),
        Station(name = "Hunyadi János út", time = "18:11", isArrived = true),
        Station(name = "Budafoki út", time = "18:08", isArrived = true),
        Station(name = "Szent Gellért tér", time = "18:05", isArrived = true),
        Station(name = "Szent Gellért rakpart", time = "18:02", isArrived = true),
        Station(name = "Erzsébet híd", time = "17:59", isArrived = true),
        Station(name = "Szabad sajtó út", time = "17:56", isArrived = true),
        Station(name = "Ferenciek tere", time = "17:53", isArrived = true),
        Station(name = "Kossuth Lajos utca", time = "17:50", isArrived = true),
        Station(name = "Rákóczi út", time = "17:47", isArrived = true),
        Station(name = "Baross tér", time = "17:44", isArrived = true),
        Station(name = "Thököly út", time = "17:41", isArrived = true),
        Station(name = "Bosnyák tér", time = "17:38", isArrived = true),
        Station(name = "Csömöri út", time = "17:35", isArrived = true),
        Station(name = "felüljáró", time = "17:32", isArrived = true),
        Station(name = "Drégelyvár utca", time = "17:29", isArrived = false),
        Station(name = "Nyírpalota út", time = "17:26", isArrived = false),
        Station(name = "Újpalota, Nyírpalota út vá.", time = "17:23", isArrived = false)
    )

    HomeJourneyVerticalProgressLine(stations)
}

@Composable
fun HomeJourneyVerticalProgressLine(
    stations: List<Station>
) {
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Box {
            Column {
                stations.forEach { station ->
                    HomeJourneyStationRow(station)
                }
            }
        }
    }
}

@Composable
fun HomeJourneyStationRow(station: Station) {
    Row(
        modifier = Modifier
            .background(if (station.isNext) LightPurple.copy(.5f) else Color.Transparent)
            .border(if (station.isNext) 1.dp else Dp.Unspecified, Purple.copy(.1f))
            .padding(top = if (station.isNext) 6.dp else 0.dp)
    ) {
        HomeJourneyLine(station)
    }
}

@Composable
fun HomeJourneyLine(station: Station) {
    val stationBoxSize = if (station.isTerminus) 20.dp else 30.dp
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    ) {
        HomeJourneyStationInfo(station, stationBoxSize)
        HomeJourneyDrawLine(station)
    }
}

@Composable
fun HomeJourneyStationInfo(station: Station, stationBoxSize: Dp) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        HomeJourneyStationIcon(station, stationBoxSize)
        Spacer(modifier = Modifier.width(20.dp))
        HomeJourneyStationDetails(station)
    }
}

@Composable
fun HomeJourneyStationIcon(station: Station, stationBoxSize: Dp) {
    if (station.isTerminus) {
        Box(
            Modifier
                .padding(5.dp)
                .border(5.dp, Green.copy(.3f), CircleShape)
                .size(stationBoxSize)
                .padding(5.dp)
                .background(Green, CircleShape)
        )
    } else {
        Box(
            modifier = Modifier
                .border(5.dp, Purple, CircleShape)
                .size(stationBoxSize)
                .background(
                    if (station.isArrived) Purple else White,
                    shape = CircleShape
                )
        )
    }
}

@Composable
fun HomeJourneyStationDetails(station: Station) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = station.time,
                fontSize = 20.sp,
                fontWeight = if (station.isTerminus) FontWeight.Bold else FontWeight.Normal,
                color = Purple
            )
            Spacer(modifier = Modifier.width(30.dp))
            if (station.isNext) {
                Text(
                    text = "+ 3",
                    modifier = Modifier
                        .background(AlertBackgroundRed, RoundedCornerShape(5.dp))
                        .padding(3.dp),
                    color = AlertRed,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = station.name,
            fontSize = 20.sp,
            fontWeight = if (station.isTerminus) FontWeight.Bold else FontWeight.Normal,
            color = Purple
        )
    }
}

@Composable
fun HomeJourneyDrawLine(station: Station) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Canvas(
            modifier = Modifier.height(40.dp)
        ) {
            drawLine(
                color = Purple,
                start = center.copy(y = -34f, x = 30f.dp.toPx() / 2),
                end = center.copy(y = size.height + 65f, x = 30f.dp.toPx() / 2),
                strokeWidth = if (station.isArrived) 30f else 8f,
                pathEffect = if (station.isArrived) PathEffect.cornerPathEffect(0f) else PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        }
        if (station.isNext) {
            Box(
                Modifier
                    .border(1.dp, BusBlue, CircleShape)
                    .background(BusBlue, CircleShape)
                    .padding(4.dp)
            ) {
                Icon(imageVector = Icons.Default.DirectionsBus, contentDescription = null, tint = White)
            }
        }
    }
}

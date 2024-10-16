package com.zenitech.futar.feature.home.body_right

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.BusBlue
import com.zenitech.futar.ui.theme.LightPurple
import com.zenitech.futar.ui.theme.Purple

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun HomeOtherRoutesPreview() {
    HomeOtherRoutes()
}

@Composable
fun HomeOtherRoutes() {
    val state = rememberLazyListState()
    Column {

        LazyColumn(
            modifier = Modifier
                .simpleVerticalScrollbar(state = state)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxSize(),
            state = state
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                ) {
                    Text(text = "Egyéb útvonalak", color = Purple, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                }
                HorizontalDivider(color = Purple.copy(0.2f), modifier = Modifier.padding(vertical = 30.dp))
            }
            items(otherJourneyItems) { journeyItem ->
                HomeRoutesOfJourneyItem(
                    journeyNumber = journeyItem.journeyNumber,
                    fromStation = journeyItem.fromStation,
                    toStation = journeyItem.toStation,
                    other = journeyItem.other,
                    modifier = Modifier
                )
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

val otherJourneyItems = listOf(
    HomeRoutesOfJourneyData("8", "Józsefváros", "Népliget", "Útvonal 2"),
    HomeRoutesOfJourneyData("19", "Városház tér", "Széll Kálmán tér", "Útvonal 3"),
    HomeRoutesOfJourneyData("100E", "Ferihegy", "Kálvin tér", "Útvonal 4"),
    HomeRoutesOfJourneyData("41", "Népliget", "Kelenföld", "Útvonal 5"),
    HomeRoutesOfJourneyData("105", "Pannónia", "Kossuth Lajos", "Útvonal 6"),
    HomeRoutesOfJourneyData("76", "Kőbánya", "Újpalota", "Útvonal 7"),
    HomeRoutesOfJourneyData("34", "Sashalom", "Kispest", "Útvonal 8")
)



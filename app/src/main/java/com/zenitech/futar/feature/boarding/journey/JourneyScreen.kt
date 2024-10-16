package com.zenitech.futar.feature.boarding.journey

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
import com.zenitech.futar.feature.boarding.traffic_number.Instruction
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.BusBlue
import com.zenitech.futar.ui.theme.LightPurple
import com.zenitech.futar.ui.theme.Purple
import kotlinx.coroutines.Job

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun JourneyScreenPreview() {
    JourneyScreen({}) {}
}

@Composable
fun JourneyScreen(
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    val state = rememberLazyListState()
    Column {
        Instruction(text = "Viszonylat", onBack = onBack)

        LazyColumn(
            modifier = Modifier
                .simpleVerticalScrollbar(state = state)
                .padding(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, Purple.copy(.2f), RoundedCornerShape(20.dp))
                .background(White, RoundedCornerShape(20.dp))
                .fillMaxSize(),
            state = state
        ) {
            item { Spacer(modifier = Modifier.height(20.dp)) }
            items(journeyItems) { journeyItem ->
                JourneyItem(
                    time = journeyItem.time,
                    journeyNumber = journeyItem.journeyNumber,
                    fromStation = journeyItem.fromStation,
                    toStation = journeyItem.toStation,
                    other = journeyItem.other,
                    onNavigateToHome = onNavigateToHome
                )
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

data class JourneyData(
    val time: String,
    val journeyNumber: String,
    val fromStation: String,
    val toStation: String,
    val other: String
)

val journeyItems = listOf(
    JourneyData("17:23", "133E", "Újpalota, Nyírpalota út vá.", "Nagytétény, ipartelep", "Útvonal 1"),
    JourneyData("17:34", "133E", "Újpalota, Nyírpalota út vá.", "Nagytétény, ipartelep", "Útvonal 1"),
    JourneyData("17:57", "133E", "Újpalota, Nyírpalota út vá.", "Nagytétény, ipartelep", "Útvonal 2"),
    JourneyData("18:11", "133E", "Újpalota, Nyírpalota út vá.", "Nagytétény, ipartelep", "Útvonal 2"),
    JourneyData("18:28", "133E", "Újpalota, Nyírpalota út vá.", "Nagytétény, ipartelep", "Útvonal 2"),
    JourneyData("18:44", "133E", "Újpalota, Nyírpalota út vá.", "Nagytétény, ipartelep", "Útvonal 2"),
    JourneyData("18:59", "133E", "Újpalota, Nyírpalota út vá.", "Nagytétény, ipartelep", "Útvonal 2"),
    JourneyData("19:10", "133E", "Újpalota, Nyírpalota út vá.", "Nagytétény, ipartelep", "Útvonal 2"),
)

@Composable
fun JourneyItem(
    time: String,
    journeyNumber: String,
    fromStation: String,
    toStation: String,
    other: String,
    onNavigateToHome: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onNavigateToHome()
            }
            .border(1.dp, Purple.copy(.2f), RoundedCornerShape(10.dp))
            .background(LightPurple, RoundedCornerShape(10.dp))
            .padding(vertical = 20.dp, horizontal = 40.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = time, fontSize = 25.sp, color = Purple, fontWeight = FontWeight.Bold)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(BusBlue)
                    .border(3.dp, BusBlue, CircleShape)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DirectionsBus,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .size(30.dp)
                        .animateContentSize()
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = journeyNumber,  fontSize = 25.sp, color = Purple,)
        }
        Column {
            Text(text = fromStation,  fontSize = 25.sp, color = Purple,)
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, tint = Purple)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = toStation,  fontSize = 25.sp, color = Purple,)

            }
        }
        Text(text = other,  fontSize = 25.sp, color = Purple,)
    }
}

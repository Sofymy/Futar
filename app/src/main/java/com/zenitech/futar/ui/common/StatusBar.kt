package com.zenitech.futar.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.theme.AlertRed
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.Green
import com.zenitech.futar.ui.theme.MediumPurple
import com.zenitech.futar.ui.theme.Purple

@Composable
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
fun HomeStatusDisplayPreview(){
    FutarTheme {
        HomeStatusDisplay(
            modifier = Modifier,
            text =  "Text text text",
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeStatusDisplay(
    modifier: Modifier,
    text: String,
) {
    val showConnectionData = remember { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxWidth()
            .border(1.dp, Purple.copy(.2f))
            .background(Purple),
    ) {
        Row(
            Modifier
                .align(Alignment.Center)
                .basicMarquee(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(4) {
                Spacer(modifier = Modifier.width(100.dp))
                Text(
                    text = text,
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.width(100.dp))
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(0.dp))
                .clickable {
                    showConnectionData.value = showConnectionData.value.not()
                }
                .background(Purple, RoundedCornerShape(0.dp))
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            VerticalDivider(Modifier.height(30.dp), color = MediumPurple)
            Spacer(modifier = Modifier.width(20.dp))
            Row {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    HomeSatelliteStatusIcon(isActive = true)
                    AnimatedVisibility(visible = showConnectionData.value) {
                        Row {
                            Spacer(modifier = Modifier.width(10.dp))
                            Text("GPS Aktív", color = White, fontSize = 13.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    HomeDataConnectionStatusIcon(isActive = false)

                    AnimatedVisibility(visible = showConnectionData.value) {
                        Row {
                            Spacer(modifier = Modifier.width(10.dp))
                            Text("Adatkapcsolat nincs", color = White, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun HomeSatelliteStatusIcon(isActive: Boolean) {
    Box(
        modifier = Modifier
            .background(if (isActive) Green else AlertRed, CircleShape)
            .padding(3.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.GpsFixed,
            contentDescription = null,
            tint = White,
        )
    }
}

@Composable
fun HomeDataConnectionStatusIcon(isActive: Boolean) {
    Box(
        modifier = Modifier
            .background(if (isActive) Green else AlertRed, RoundedCornerShape(5.dp))
            .padding(3.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.SignalCellularAlt,
            contentDescription = null,
            tint = White,
        )
    }
}
package com.zenitech.futar.feature.home.body_right

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.theme.Green
import com.zenitech.futar.ui.theme.Purple
import kotlinx.coroutines.delay

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun HomeDataSynchronizationPreview(){
    HomeDataSynchronization()
}

@Composable
fun HomeDataSynchronization(){
    HomeDataSynchronizationContent()
}

@Composable
fun HomeDataSynchronizationContent() {
    var isSyncing by remember { mutableStateOf(true) }
    val syncMessages = listOf(
        "Kapcsolódás a szerverhez...",
        "Adatok szinkronizálása...",
        "Véglegesítés...",
        "Szinkronizálás kész!"
    )
    var currentMessageIndex by remember { mutableIntStateOf(0) }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (isSyncing) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    LaunchedEffect(Unit) {
        syncMessages.forEachIndexed { index, _ ->
            delay(2000L)
            currentMessageIndex = index
            if (index == syncMessages.lastIndex) {
                isSyncing = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isSyncing) {
            Icon(
                imageVector = Icons.Default.Sync,
                contentDescription = null,
                tint = Purple,
                modifier = Modifier
                    .size(60.dp)
                    .rotate(rotationAngle)
            )
        } else {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Green,
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = syncMessages[currentMessageIndex],
            color = Purple,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

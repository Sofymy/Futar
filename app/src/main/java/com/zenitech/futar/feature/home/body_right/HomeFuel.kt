package com.zenitech.futar.feature.home.body_right

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.AlertBackgroundRed
import com.zenitech.futar.ui.theme.AlertRed
import com.zenitech.futar.ui.theme.LightPurple
import com.zenitech.futar.ui.theme.Purple

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun HomeFuelPreview() {
    HomeFuel()
}

@Composable
fun HomeFuel() {
    HomeFuelContent()
}

@Composable
fun HomeFuelContent() {
    val state = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .simpleVerticalScrollbar(state),
        state = state
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {
                Text(text = "Üzemanyag", color = Purple, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            }
            HorizontalDivider(color = Purple.copy(0.2f), modifier = Modifier.padding(vertical = 20.dp))
        }

        item {
            Text(text = "Üzemanyag állapot", color = Purple, fontSize = 20.sp, modifier = Modifier.padding(20.dp))
        }

        item {
            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                HomeFuelStatusItem(field = "Tank 1", fuelLevel = 80f, modifier = Modifier.weight(1f))
                HomeFuelStatusItem(field = "Tank 2", fuelLevel = 60f, modifier = Modifier.weight(1f))
                HomeFuelStatusItem(field = "Tank 3", fuelLevel = 30f, modifier = Modifier.weight(1f))
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Text(text = "Üzemanyag információ", color = Purple, fontSize = 20.sp, modifier = Modifier.padding(20.dp))
        }

        items(listOf(
            "Tankolás szükséges",
            "Tankolt mennyiség: 50L",
            "Utolsó tankolás: 2024.10.10."
        )) { fuelText ->
            HomeFuelItem(fuelText = fuelText)
        }
    }
}

@Composable
fun HomeFuelItem(
    fuelText: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
            .background(Purple.copy(0.04f))
            .border(1.dp, Purple.copy(0.3f), RoundedCornerShape(10.dp))
            .padding(30.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = fuelText, fontSize = 20.sp, color = Purple)
    }
}

@Composable
fun HomeFuelStatusItem(
    field: String,
    fuelLevel: Float,
    modifier: Modifier
) {
    val animatedFuelLevel by animateFloatAsState(targetValue = fuelLevel, label = "")

    Row(
        modifier
            .padding(vertical = 5.dp)
            .border(1.dp, if (fuelLevel > 40) Purple.copy(.3f) else AlertRed, RoundedCornerShape(10.dp))
            .background(
                if (fuelLevel > 40) Purple.copy(0.04f) else AlertBackgroundRed, RoundedCornerShape(10.dp)
            )
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = field, color = if (fuelLevel > 40) Purple else AlertRed, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "${animatedFuelLevel.toInt()}%", fontSize = 20.sp, color = if (fuelLevel > 40) Purple else AlertRed)
        if (fuelLevel > 40) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Purple)
        } else {
            Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = AlertRed)
        }
    }
}

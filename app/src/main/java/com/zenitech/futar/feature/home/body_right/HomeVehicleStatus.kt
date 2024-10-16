package com.zenitech.futar.feature.home.body_right

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.LightPurple
import com.zenitech.futar.ui.theme.Purple

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun HomeVehicleStatusPreview(){
    HomeVehicleStatus()
}

@Composable
fun HomeVehicleStatus(){
    HomeVehicleStatusContent()
}

@Composable
fun HomeVehicleStatusContent() {
    val state = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .simpleVerticalScrollbar(state)
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
                Text(text = "Jármű állapot", color = Purple, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            }
            HorizontalDivider(color = Purple.copy(0.2f), modifier = Modifier.padding(vertical = 30.dp))
        }

        items(listOf(
            "Jármű azonosító" to "028",
            "Ajtók állapota" to "Nyitva",
            "Kilométeróra állás" to "2028,2",
            "Konfiguráció" to "314",
            "Műholdak száma" to "4"
        )) { (field, value) ->
            HomeVehicleStatusItem(
                field = field,
                value = value
            )
        }
    }

}

@Composable
fun HomeVehicleStatusItem(
    field: String,
    value: String
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .border(1.dp, Purple.copy(.3f), RoundedCornerShape(10.dp))
            .background(Purple.copy(0.04f), RoundedCornerShape(10.dp))
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = field, color = Purple, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = value, color = Purple, fontSize = 20.sp)
    }

}

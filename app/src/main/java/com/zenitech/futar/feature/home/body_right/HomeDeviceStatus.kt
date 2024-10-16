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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun HomeDeviceStatusPreview(){
    HomeDeviceStatus()
}

@Composable
fun HomeDeviceStatus(){
    HomeDeviceStatusContent()
}

@Composable
fun HomeDeviceStatusContent() {
    val status = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .simpleVerticalScrollbar(status)
            .fillMaxSize()
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {
                Text(text = "Eszköz állapot", color = Purple, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            }
            HorizontalDivider(color = Purple.copy(0.2f), modifier = Modifier.padding(vertical = 20.dp))
        }

        items(listOf(
            "IBIS-Bus" to true,
            "Útjeladó" to true,
            "Hangrendszer" to false,
            "GPS" to true,
            "Jegykezelő 1" to true,
            "Jegykezelő 2" to true,
            "Jegykezelő 3" to true,
            "Jegykezelő 4" to true
        )) { (field, isOk) ->
            HomeDeviceStatusItem(
                field = field,
                isOk = isOk
            )
        }
    }

}

@Composable
fun HomeDeviceStatusItem(
    field: String,
    isOk: Boolean
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .border(1.dp, if (isOk) Purple.copy(.3f) else AlertRed, RoundedCornerShape(10.dp))
            .background(
                if (isOk) Purple.copy(0.04f) else AlertBackgroundRed, RoundedCornerShape(10.dp)
            )
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = field, color = if(isOk)Purple else AlertRed, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        if(isOk){
            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Purple)
        }
        else Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = AlertRed)
    }

}
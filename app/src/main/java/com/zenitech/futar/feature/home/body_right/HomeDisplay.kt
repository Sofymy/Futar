package com.zenitech.futar.feature.home.body_right

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
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
fun HomeDisplayPreview(){
    HomeDisplay()
}

@Composable
fun HomeDisplay(){
    HomeDisplayContent()
}

@Composable
fun HomeDisplayContent() {
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
                Text(text = "Kijelző", color = Purple, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            }
            HorizontalDivider(color = Purple.copy(0.2f), modifier = Modifier.padding(vertical = 20.dp))
        }

        item {
            Text(text = "Kijelző állapot", color = Purple, fontSize = 20.sp, modifier = Modifier.padding(20.dp))
        }
        item{
            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                HomeDisplayStatusItem(field = "Kijelző 1", isOk = true, modifier = Modifier.weight(1f))
                HomeDisplayStatusItem(field = "Kijelző 2", isOk = true, modifier = Modifier.weight(1f))
                HomeDisplayStatusItem(field = "Kijelző 3", isOk = false, modifier = Modifier.weight(1f))
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Text(text = "Kijelző feliratok", color = Purple, fontSize = 20.sp, modifier = Modifier.padding(20.dp))
        }

        items(listOf(
            "Baleset",
            "Várakozás",
            "Felszállás első ajtón",
            "Megálló kimaradás",
            "Maszkviselés"
        )) { displayText ->
            HomeDisplayItem(
                displayText = displayText
            )
        }
    }

}

@Composable
fun HomeDisplayItem(
    displayText: String
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = displayText, fontSize = 20.sp, color = Purple)
    }

}

@Composable
fun HomeDisplayStatusItem(
    field: String,
    isOk: Boolean,
    modifier: Modifier
) {
    Row(
        modifier
            .padding(vertical = 5.dp)
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
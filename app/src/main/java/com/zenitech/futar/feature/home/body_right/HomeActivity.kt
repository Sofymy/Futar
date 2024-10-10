package com.zenitech.futar.feature.home.body_right

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.HomeSecondaryButton
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.Purple


@Composable
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
fun HomeActivityPreview(){
    FutarTheme {
        HomeActivity()
    }
}

@Composable
fun HomeActivity() {
    HomeActivityContent()
}

@Composable
fun HomeActivityContent() {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .simpleVerticalScrollbar(state)
    ) {
        item { Spacer(Modifier.height(30.dp)) }
        item { HomeActivityRouteOptions() }
        item { Spacer(Modifier.height(30.dp)) }
        item { HomeActivityFuelAndTrafficOptions() }
        item { Spacer(Modifier.height(30.dp)) }
        item { HomeActivityNavigationAndLogout() }
    }
}

@Composable
fun HomeActivityRouteOptions() {
    HomeActivityButtonRow(
        firstButtonText = "Viszonylat útvonalai",
        secondButtonText = "Egyéb útvonalak"
    )
}

@Composable
fun HomeActivityFuelAndTrafficOptions() {
    HomeActivityButtonRow(
        firstButtonText = "Üzemanyag",
        secondButtonText = "Forgalmi szám"
    )
}

@Composable
fun HomeActivityNavigationAndLogout() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        HomeActivityPrimaryButton(text = "Navigáció", modifier = Modifier.weight(1f))
        HomeActivityLogoutButton(modifier = Modifier.weight(1f))
    }
}

@Composable
fun HomeActivityButtonRow(firstButtonText: String, secondButtonText: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        HomeActivityPrimaryButton(text = firstButtonText, modifier = Modifier.weight(1f))
        HomeActivityPrimaryButton(text = secondButtonText, modifier = Modifier.weight(1f))
    }
}

@Composable
fun HomeActivityPrimaryButton(
    text: String,
    modifier: Modifier = Modifier
) {
    HomeSecondaryButton(
        modifier = modifier
    ) {
        Text(text, color = Purple, fontSize = 20.sp)
    }
}

@Composable
fun HomeActivityLogoutButton(
    modifier: Modifier = Modifier
) {
    HomeSecondaryButton(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kijelentkezés", color = Purple, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(20.dp))
            Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = null, tint = Purple)
        }
    }
}


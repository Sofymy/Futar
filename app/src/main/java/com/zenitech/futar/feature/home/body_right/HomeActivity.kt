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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        HomeActivity({},{},{}, {}) {}
    }
}

@Composable
fun HomeActivity(
    onLogout: () -> Unit,
    onClickRoutesOfJourney: () -> Unit,
    onClickNewTrafficNumber: () -> Unit,
    onClickOtherRoutes: () -> Unit,
    onClickFuel: () -> Unit
) {
    HomeActivityContent(
        onLogout = onLogout,
        onClickRoutesOfJourney = onClickRoutesOfJourney,
        onClickNewTrafficNumber = onClickNewTrafficNumber,
        onClickOtherRoutes = onClickOtherRoutes,
        onClickFuel = onClickFuel
    )
}

@Composable
fun HomeActivityContent(
    onLogout: () -> Unit,
    onClickRoutesOfJourney: () -> Unit,
    onClickNewTrafficNumber: () -> Unit,
    onClickOtherRoutes: () -> Unit,
    onClickFuel: () -> Unit
) {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .simpleVerticalScrollbar(state)
    ) {
        item { Spacer(Modifier.height(30.dp)) }
        item { HomeActivityRouteOptions(
            onClickRoutesOfJourney = onClickRoutesOfJourney,
            onClickOtherRoutes = onClickOtherRoutes
        ) }
        item { Spacer(Modifier.height(30.dp)) }
        item { HomeActivityFuelAndTrafficOptions(
            onClickNewTrafficNumber = onClickNewTrafficNumber,
            onClickFuel = onClickFuel
        ) }
        item { Spacer(Modifier.height(30.dp)) }
        item { HomeActivityNavigationAndLogout(onLogout = onLogout) }
    }
}

@Composable
fun HomeActivityRouteOptions(
    onClickRoutesOfJourney: () -> Unit,
    onClickOtherRoutes: () -> Unit
) {
    HomeActivityButtonRow(
        firstButtonText = "Viszonylat útvonalai",
        secondButtonText = "Egyéb útvonalak",
        onClickFirstButton = onClickRoutesOfJourney,
        onClickSecondButton = onClickOtherRoutes
    )
}

@Composable
fun HomeActivityFuelAndTrafficOptions(
    onClickNewTrafficNumber: () -> Unit,
    onClickFuel: () -> Unit
) {
    HomeActivityButtonRow(
        firstButtonText = "Üzemanyag",
        secondButtonText = "Forgalmi szám",
        onClickFirstButton = onClickFuel,
        onClickSecondButton = onClickNewTrafficNumber
    )
}

@Composable
fun HomeActivityNavigationAndLogout(
    onLogout: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        HomeActivityPrimaryButton(text = "Navigáció", modifier = Modifier.weight(1f), {})
        HomeActivityLogoutButton(modifier = Modifier.weight(1f), onLogout = onLogout)
    }
}

@Composable
fun HomeActivityButtonRow(
    firstButtonText: String,
    onClickFirstButton: () -> Unit,
    secondButtonText: String,
    onClickSecondButton: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        HomeActivityPrimaryButton(text = firstButtonText, modifier = Modifier.weight(1f), onClickFirstButton)
        HomeActivityPrimaryButton(text = secondButtonText, modifier = Modifier.weight(1f), onClickSecondButton)
    }
}

@Composable
fun HomeActivityPrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    HomeSecondaryButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text, color = Purple, fontSize = 20.sp)
    }
}

@Composable
fun HomeActivityLogoutButton(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit
) {
    HomeSecondaryButton(
        onClick = onLogout,
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


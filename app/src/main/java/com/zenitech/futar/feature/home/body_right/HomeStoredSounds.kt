package com.zenitech.futar.feature.home.body_right

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeDown
import androidx.compose.material.icons.automirrored.filled.VolumeMute
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.Purple
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
fun HomeStoredSoundsPreview(){
    FutarTheme {
        HomeStoredSounds()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeStoredSounds() {
    val pagerState = rememberPagerState(pageCount = { 3 }, initialPage = 0)
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = selectedTabIndex.value,
        containerColor = White,
        divider = {
            HorizontalDivider(color = Purple.copy(.3f))
        },
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    .height(4.dp)
                    .background(color = Purple, CircleShape)
            )
        }
    ) {
        HomeStoredSoundsItems.entries.forEachIndexed { index, currentTab ->
            Tab(
                selected = selectedTabIndex.value == index,
                selectedContentColor = Purple,
                unselectedContentColor = Color.Gray,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(currentTab.ordinal)
                    }
                },
                text = { Text(text = currentTab.name, fontSize = 20.sp, modifier = Modifier.padding(10.dp)) },
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        when (page) {
            0 -> HomeStoredSoundsInformation()
            1 -> HomeStoredSoundsError()
            2 -> HomeStoredSoundsAccident()
        }
    }
}

@Composable
fun HomeStoredSoundsInformation() {
    val state = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = Modifier.simpleVerticalScrollbar(state)
    ) {
        item {
            HomeStoredSoundsItem(text = "Ajtók záródnak")
        }
        item {
            HomeStoredSoundsItem(text = "Felszállás az első ajtón")
        }
        item {
            HomeStoredSoundsItem(text = "Jegyellenőrzés")
        }
        item {
            HomeStoredSoundsItem(text = "Rövid várakozás")
        }
        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
fun HomeStoredSoundsError() {
    Column {
        HomeStoredSoundsItem(text = "Kérem, vigyázzanak, ajtók záródnak!")
        HomeStoredSoundsItem(text = "Felszállás az első ajtón")
    }
}

@Composable
fun HomeStoredSoundsAccident() {
    Column {
        HomeStoredSoundsItem(text = "Kérem, vigyázzanak, ajtók záródnak!")
        HomeStoredSoundsItem(text = "Felszállás az első ajtón")
    }
}

enum class HomeStoredSoundsItems {
    Információ,
    Hiba,
    Baleset
}

@Composable
fun HomeStoredSoundsItem(text: String) {
    val isSoundButtonPressed = remember { mutableStateOf(false) }
    val voiceLevel = remember { mutableIntStateOf(0) }

    LaunchedEffect(isSoundButtonPressed.value) {
        if (isSoundButtonPressed.value) {
            repeat(10) {
                voiceLevel.intValue = (voiceLevel.intValue + 1) % 3
                delay(500)
            }
            delay(1000)
            isSoundButtonPressed.value = false
            voiceLevel.intValue = 0
        }
    }

    Column(
        modifier = Modifier
            .padding(20.dp, top = 30.dp, end = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                if (!isSoundButtonPressed.value) isSoundButtonPressed.value = true
            }
            .background(Purple.copy(.04f))
            .border(1.dp, Purple.copy(.3f), RoundedCornerShape(10.dp))
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSoundButtonPressed.value)
                HomeStoredSoundsVolumeIcon(volumeLevel = voiceLevel.intValue)
            else Icon(
                imageVector = Icons.AutoMirrored.Filled.VolumeMute,
                contentDescription = null,
                tint = Purple,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(60.dp)
            )
            Text(text = text, fontSize = 20.sp, color = Purple, modifier = Modifier.padding(start = 20.dp))
        }
    }
}

@Composable
fun HomeStoredSoundsVolumeIcon(volumeLevel: Int) {
    val icon = when (volumeLevel) {
        0 -> Icons.AutoMirrored.Filled.VolumeMute
        1 -> Icons.AutoMirrored.Filled.VolumeDown
        else -> Icons.AutoMirrored.Filled.VolumeUp
    }

    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = Purple,
        modifier = Modifier
            .padding(start = 20.dp)
            .size(60.dp)
    )
}


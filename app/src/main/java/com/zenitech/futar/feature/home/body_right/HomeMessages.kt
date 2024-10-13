@file:OptIn(ExperimentalFoundationApi::class)

package com.zenitech.futar.feature.home.body_right

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.AlertBackgroundRed
import com.zenitech.futar.ui.theme.AlertRed
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.Purple
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
@Preview(name = "tablet", device = "spec:width=1280dp,height=800dp,dpi=480")
fun HomeMessagesPreview(){
    FutarTheme {
        HomeMessages {

        }
    }
}

enum class MessageItems { Bejövő, Kimenő }
enum class MessagePriority { LOW, NORMAL, HIGH }

@Composable
fun HomeMessages(navigateToCreateMessage: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { 2 }, initialPage = 0)
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    val scope = rememberCoroutineScope()

    HomeMessagesTabRow(pagerState, selectedTabIndex.value) { index ->
        scope.launch { pagerState.animateScrollToPage(index) }
    }

    Box(contentAlignment = Alignment.BottomEnd) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { page ->
            when (page) {
                0 -> HomeMessagesIncoming()
                1 -> HomeMessagesOutgoing()
            }
        }
        HomeMessagesFloatingActionButton(onClick = navigateToCreateMessage)
    }
}

@Composable
fun HomeMessagesTabRow(
    pagerState: PagerState,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = White,
        divider = { HorizontalDivider(color = Purple.copy(.3f)) },
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    .height(4.dp)
                    .background(color = Purple, CircleShape)
            )
        }
    ) {
        MessageItems.entries.forEachIndexed { index, currentTab ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Purple,
                unselectedContentColor = Gray,
                onClick = { onTabSelected(index) },
                text = {
                    Row {
                        Text(
                            text = currentTab.name,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun HomeMessagesFloatingActionButton(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 0.dp),
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
        containerColor = Purple,
        contentColor = White,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Create, contentDescription = null)
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Üzenet küldése", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun HomeMessagesOutgoing() {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .simpleVerticalScrollbar(state)
            .fillMaxSize(),
        state = state
    ) {
        item { HomeMessagesOutgoingItem("Sanyi", "Indulok!", MessagePriority.LOW) }
        item { HomeMessagesOutgoingItem("Központ", "Nem!", MessagePriority.LOW) }
        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
fun HomeMessagesOutgoingItem(sender: String, text: String, priority: MessagePriority) {
    HomeMessageItem(
        sender = sender,
        text = text,
        priority = priority,
        timestamp = getCurrentTimestamp()
    )
}

@Composable
fun HomeMessagesIncoming() {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .simpleVerticalScrollbar(state)
            .fillMaxSize(),
        state = state
    ) {
        item { HomeMessagesIncomingItem("Központ", "Kérlek, indulj a 7-es járaton 08:30-kor a Központi pályaudvarra (B-235).", MessagePriority.HIGH, false) }
        item { HomeMessagesIncomingItem("Sanyi", "Nem!", MessagePriority.LOW, false) }
        item { HomeMessagesIncomingItem("Béla", "Elindultam", MessagePriority.LOW, true) }
        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
fun HomeMessagesIncomingItem(sender: String, text: String, priority: MessagePriority, isRead: Boolean) {
    HomeMessageItem(
        sender = sender,
        text = text,
        priority = priority,
        isRead = isRead,
        timestamp = getCurrentTimestamp()
    )
}

@Composable
fun HomeMessagesNew() {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .simpleVerticalScrollbar(state)
            .fillMaxSize(),
        state = state
    ) {
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Üzenet küldése", color = Purple, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            }
        }
        item {
            HorizontalDivider(color = Purple.copy(.2f), modifier = Modifier.padding(vertical = 20.dp))
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item { HomeMessagesNewItem(text = "Igen") }
        item { HomeMessagesNewItem(text = "Nem") }
        item { HomeMessagesNewItem(text = "Hívás kérés") }
        item { HomeMessagesNewItem(text = "Utas lemaradás") }
    }
}

@Composable
fun HomeMessagesNewItem(text: String) {
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
        Text(text = text, fontSize = 20.sp, color = Purple)
    }
}

@Composable
fun HomeMessageItem(
    sender: String,
    text: String,
    priority: MessagePriority,
    isRead: Boolean = true,
    timestamp: String
) {
    val animatedRotation by rememberInfiniteTransition().animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val backgroundColor = getBackgroundColor(priority)
    val textColor = getTextColor(priority)
    val fontWeight = if (isRead) FontWeight.Normal else FontWeight.Bold

    Row(
        modifier = Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
            .background(backgroundColor)
            .border(1.dp, Purple.copy(0.3f), RoundedCornerShape(10.dp))
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeMessageItemContent(sender, text, textColor, fontWeight, modifier = Modifier.weight(1f))
        HomeMessageItemTimestamp(timestamp, modifier = Modifier.padding(start = 8.dp))
        HomeMessageItemIcons(priority, animatedRotation, isRead)
    }
}

@Composable
fun HomeMessageItemContent(
    sender: String,
    text: String,
    textColor: Color,
    fontWeight: FontWeight,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = sender,
            fontSize = 20.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = text,
            fontSize = 20.sp,
            color = textColor,
            fontWeight = fontWeight,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun HomeMessageItemTimestamp(timestamp: String, modifier: Modifier) {
    Text(
        text = timestamp,
        fontSize = 16.sp,
        color = Gray,
        modifier = modifier.padding(10.dp)
    )
}

@Composable
fun HomeMessageItemIcons(priority: MessagePriority, animatedRotation: Float, isRead: Boolean) {
    if (!isRead) {
        Icon(
            imageVector = Icons.Default.Circle,
            contentDescription = null,
            tint = if (priority == MessagePriority.HIGH) AlertRed else Purple,
            modifier = Modifier
                .padding(end = 20.dp)
                .graphicsLayer { rotationZ = animatedRotation }
                .size(15.dp)
        )
    }
}

private fun getBackgroundColor(priority: MessagePriority): Color {
    return when (priority) {
        MessagePriority.LOW, MessagePriority.NORMAL -> Purple.copy(0.04f)
        MessagePriority.HIGH -> AlertBackgroundRed
    }
}

private fun getTextColor(priority: MessagePriority): Color {
    return when (priority) {
        MessagePriority.LOW, MessagePriority.NORMAL -> Purple
        MessagePriority.HIGH -> AlertRed
    }
}

private fun getCurrentTimestamp(): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date())
}

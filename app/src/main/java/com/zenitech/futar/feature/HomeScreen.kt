@file:OptIn(ExperimentalFoundationApi::class)

package com.zenitech.futar.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.VolumeDown
import androidx.compose.material.icons.automirrored.filled.VolumeMute
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.filled.SevereCold
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.simpleVerticalScrollbar
import com.zenitech.futar.ui.theme.AlertBackgroundRed
import com.zenitech.futar.ui.theme.AlertRed
import com.zenitech.futar.ui.theme.BusBlue
import com.zenitech.futar.ui.theme.BusDarkBlue
import com.zenitech.futar.ui.theme.DarkTicketYellow
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.Green
import com.zenitech.futar.ui.theme.LightGrey
import com.zenitech.futar.ui.theme.LightPurple
import com.zenitech.futar.ui.theme.MediumPurple
import com.zenitech.futar.ui.theme.Purple
import com.zenitech.futar.ui.theme.TicketYellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Composable
fun SatelliteStatusIcon(isActive: Boolean) {
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
fun DataConnectionStatusIcon(isActive: Boolean) {
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

@Composable
fun StatusDisplay(
    modifier: Modifier,
    lastSelectedButton: HomeButton,
    onLastSelectedButtonClicked: () -> Unit
) {
    val showConnectionData = remember {
        mutableStateOf(false)
    }

    Box(
        modifier
            .fillMaxWidth()
            .border(1.dp, Purple.copy(.2f))
            .background(Purple)
        ,
    ) {
        Row(
            Modifier
                .align(Alignment.Center)
                .basicMarquee(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(4){
                Spacer(modifier = Modifier.width(100.dp))
                Text(text = "Új üzenet érkezett!", fontSize = 20.sp, color = White, fontWeight = FontWeight.Bold, modifier = Modifier)
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SatelliteStatusIcon(isActive = true)
                    AnimatedVisibility(visible = showConnectionData.value) {
                        Row {
                            Spacer(modifier = Modifier.width(10.dp))
                            Text("GPS Aktív", color = White, fontSize = 13.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DataConnectionStatusIcon(isActive = false)

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
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
fun HomeScreenPreview(){
    FutarTheme {
        HomeScreen()
    }
}

@Composable
fun HomeScreen(){
    HomeContent()
}

@Composable
fun HomeDotPatternBackground() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val dotSize = 3.dp.toPx()
        val dotSpacing = 4.dp.toPx()

        for (y in 0 until canvasHeight.toInt() step dotSpacing.toInt()) {
            for (x in 0 until canvasWidth.toInt() step dotSpacing.toInt()) {
                drawCircle(
                    color = MediumPurple.copy(alpha = (canvasHeight - y) / canvasHeight),
                    radius = dotSize / 2,
                    center = Offset(x.toFloat(), y.toFloat())
                )
            }
        }
    }
}


@Composable
fun HomeContent() {

    val selectedButton = remember {
        mutableStateOf(HomeButton.UZENETEK)
    }

    val lastSelectedButton = remember {
        mutableStateOf(HomeButton.UZENETEK)
    }


    Column(
        modifier = Modifier
            .background(Purple)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            HomeHeader()
            HomeBody(
                selectedButton = selectedButton.value,
                onSelectedButtonChanged = {
                    lastSelectedButton.value = selectedButton.value
                    selectedButton.value = it
                }
            )
        }
        Column(
            modifier = Modifier
                .background(LightGrey)
        ) {
            StatusDisplay(
                modifier = Modifier,
                lastSelectedButton = lastSelectedButton.value,
                onLastSelectedButtonClicked = {
                    selectedButton.value = lastSelectedButton.value
                }
            )
        }
    }
}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier
            .background(Purple)
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .height(IntrinsicSize.Max)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start,
        ) {
            HomeNumberAndTerminalOfBus()
            Spacer(modifier = Modifier.width(30.dp))
            HomeNumberOfShift()
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            HomeHandrail(modifier = Modifier.fillMaxHeight())
            HomeDelayOrHurry(modifier = Modifier.fillMaxHeight())
            Spacer(modifier = Modifier.width(60.dp))
            HomeDateAndTime()
        }
    }
}



@Composable
fun HomeDelayOrHurry(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 20.dp)
            .aspectRatio(1f)
            .background(
                AlertRed,
                RoundedCornerShape(20.dp)
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "+3", modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 10.dp), color = AlertBackgroundRed, fontWeight = FontWeight.Bold, fontSize = 40.sp)

    }
}

@Composable
fun HomeNextStation() {
    Column(
        modifier = Modifier.padding(top = 30.dp)
    ) {
        Text(text = "Következik: Nagytétényi út", fontSize = 20.sp, color = White)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = LightPurple)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "3 perc késés", modifier = Modifier
                .background(
                    AlertBackgroundRed,
                    RoundedCornerShape(5.dp)
                )
                .padding(horizontal = 5.dp, vertical = 3.dp), color = AlertRed, fontWeight = FontWeight.Bold)
        }
    }
}

enum class HomeButton(val text: String, val isMainButton: Boolean) {
    TEVEKENYSEG("Tevékenység", true),
    UZENETEK("Üzenetek", true),
    UZENET_KULDESE("Üzenetek", false),
    TAROLT_HANGOK("Tárolt hangok", true),
    BEALLITASOK("Beállítások", true),
}


@Composable
fun HomeBody(
    selectedButton: HomeButton,
    onSelectedButtonChanged: (HomeButton) -> Unit
) {


    Box(
        Modifier
            .background(White.copy(0.96f), RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp))
            .clip(
                RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
            )
    ) {
        HomeDotPatternBackground()
        Column {
            HomeButtons(
                selectedButton = selectedButton
            ) {
                 onSelectedButtonChanged(it)
            }
            Row(
                Modifier.padding(top = 10.dp, bottom = 20.dp, end = 30.dp, start = 30.dp)
            ) {
                HomeJourney()
                HomeSelectedButtonContent(
                    selectedButton = selectedButton,
                    onNavigateToCreateMessage = {
                        onSelectedButtonChanged(HomeButton.UZENET_KULDESE)
                    }
                )
            }
        }
    }
}

@Composable
fun HomeButtons(
    selectedButton: HomeButton,
    onSelectButton: (HomeButton) -> Unit,
) {
    Row(Modifier.padding(start = 30.dp, end = 30.dp, top = 15.dp, bottom = 10.dp)) {
        HomeButton.entries.filter { it.isMainButton }
            .forEach { button ->
                HomePrimaryOutlinedButton(button = button, modifier = Modifier.weight(1f), onSelectButton = onSelectButton, isSelected = button.text == selectedButton.text)
                if(button != HomeButton.entries.last()) Spacer(modifier = Modifier.width(20.dp))
            }
    }
}


@Composable
fun HomeHandrail(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(end = 20.dp, top = 20.dp)
            .aspectRatio(1f)
            .background(White.copy(.2f), RoundedCornerShape(20.dp))
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .width(23.dp)
                .background(BusDarkBlue, RoundedCornerShape(5.dp)),
        )
        Box(
            Modifier
                .padding(start = 8.dp)
                .fillMaxHeight()
                .width(20.dp)
                .background(BusBlue, RoundedCornerShape(2.dp)),
        )
        Row {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Box(
                    Modifier
                        .height(60.dp)
                        .width(40.dp)
                        .background(DarkTicketYellow, RoundedCornerShape(5.dp))
                )
                Box(
                    Modifier
                        .padding(start = 8.dp)
                        .height(60.dp)
                        .width(37.dp)
                        .background(TicketYellow, RoundedCornerShape(3.dp))
                )
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Box(
                        Modifier
                            .height(15.dp)
                            .width(27.dp)
                            .background(DarkGray, RoundedCornerShape(2.dp)),
                    )
                    Box(
                        Modifier
                            .height(5.dp)
                            .width(20.dp)
                            .background(White, RoundedCornerShape(1.dp)),
                    )
                }
            }
        }
        Icon(Icons.Rounded.Close, contentDescription = null, tint = AlertRed, modifier = Modifier
            .size(100.dp)
            .offset(x = 4.dp))
    }
}


@Composable
fun HomePrimaryOutlinedButton(
    button: HomeButton,
    modifier: Modifier = Modifier,
    onSelectButton: (HomeButton) -> Unit,
    isSelected: Boolean
) {
    val buttonContainerColor = animateColorAsState(targetValue = if (!isSelected) White else Purple,
        label = ""
    )
    val buttonBorderColor = animateColorAsState(targetValue = if (isSelected) Purple else Purple,
        label = ""
    )
    val buttonContentColor = animateColorAsState(targetValue = if(!isSelected) Purple else White,
        label = ""
    )
    
    Row(
        modifier = modifier,
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    buttonContainerColor.value,
                    RoundedCornerShape(20.dp)
                ),
            border = BorderStroke(3.dp, buttonBorderColor.value),
            elevation = ButtonDefaults.buttonElevation(0.dp),
            onClick = { onSelectButton(button) },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
        ) {
            Text(button.text, modifier = Modifier.padding(10.dp, vertical = 15.dp), fontSize = 20.sp, color = buttonContentColor.value)
            if(button == HomeButton.UZENETEK)
                Box(
                    Modifier
                        .border(1.dp, AlertRed, CircleShape)
                        .size(40.dp)
                        .background(AlertRed, CircleShape),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "1", color = AlertBackgroundRed, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
        }
    }
}


@Composable
fun HomeNumberAndTerminalOfBus(
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .padding(30.dp, end = 0.dp, top = 30.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(LightPurple, LightPurple),
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(BusBlue)
                    .border(3.dp, BusBlue, CircleShape)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DirectionsBus,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .size(30.dp)
                        .animateContentSize()
                )
            }
            Spacer(modifier = Modifier.width(15.dp))

            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "133E",
                    color = Purple,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Row {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, tint = Purple)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Nagytétény, ipartelep",
                        color = Purple,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}


@Composable
fun HomeJourney(
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()
    
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyColumn(
            modifier = modifier
                .padding(end = 15.dp)
                .clip(RoundedCornerShape(30.dp))
                .simpleVerticalScrollbar(state)
                .clip(RoundedCornerShape(30.dp))
                .border(1.dp, Purple.copy(.2f), RoundedCornerShape(30.dp))
                .background(White, RoundedCornerShape(30.dp))
                .fillMaxWidth(.45f)
                .fillMaxHeight(),
            state = state
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                PreviewVerticalProgressLine()
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun HomeSelectedButtonContent(
    modifier: Modifier = Modifier,
    selectedButton: HomeButton,
    onNavigateToCreateMessage: () -> Unit
) {
    Column {
        Column(
            modifier = modifier
                .padding(start = 15.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(1.dp, Purple.copy(.2f), RoundedCornerShape(30.dp))
                .background(White, RoundedCornerShape(30.dp))
                .fillMaxWidth()
                .weight(1f)
        ) {
            when(selectedButton){
                HomeButton.TEVEKENYSEG -> {
                    HomeActivity()
                }
                HomeButton.UZENETEK -> {
                    HomeMessages(
                        navigateToCreateMessage = onNavigateToCreateMessage
                    )
                }
                HomeButton.TAROLT_HANGOK -> {
                    HomeStoredSounds()
                }
                HomeButton.BEALLITASOK -> {
                    HomeSettings()
                }

                HomeButton.UZENET_KULDESE -> {
                    HomeNewMessage()
                }
            }
        }
    }
}


enum class MessageItems{
    Bejövő,
    Kimenő
}

@Composable
fun HomeMessages(
    navigateToCreateMessage: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 2 }, initialPage = 0)
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
        MessageItems.entries.forEachIndexed { index, currentTab ->
            Tab(
                selected = selectedTabIndex.value == index,
                selectedContentColor = Purple,
                unselectedContentColor = Color.Gray,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(currentTab.ordinal)
                    }
                },
                text = {
                    Row {
                        Text(
                            text = currentTab.name,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    } },
            )
        }
    }

    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> HomeMessagesIncoming()
                1 -> HomeMessagesOutgoing()
            }
        }
        ExtendedFloatingActionButton(
            shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 0.dp),
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            containerColor = Purple,
            contentColor = White,
            onClick = { navigateToCreateMessage() }) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Create, contentDescription = null)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Üzenet küldése", fontSize = 20.sp, modifier = Modifier.padding(0.dp), fontWeight = FontWeight.Bold)
            }
        }
    }
}

enum class MessagePriority { LOW, NORMAL, HIGH }

@Composable
fun HomeMessagesOutgoing() {
    val state = rememberLazyListState()
    LazyColumn(
        Modifier
            .simpleVerticalScrollbar(state)
            .fillMaxSize(),
        state = state
    ) {
        item {
            HomeMessageItem(
                sender = "Sanyi",
                text = "Indulok!",
                priority = MessagePriority.LOW,
                timestamp = getCurrentTimestamp()
            )
        }
        item {
            HomeMessageItem(
                sender = "Központ",
                text = "Nem!",
                priority = MessagePriority.LOW,
                timestamp = getCurrentTimestamp()
            )
        }
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun HomeMessagesIncoming() {
    val state = rememberLazyListState()
    LazyColumn(
        Modifier
            .simpleVerticalScrollbar(state)
            .fillMaxSize(),
        state = state
    ) {
        item {
            HomeMessageItem(
                sender = "Központ",
                text = "Kérlek, indulj a 7-es járaton 08:30-kor a Központi pályaudvarra (B-235).",
                priority = MessagePriority.HIGH,
                isRead = false,
                timestamp = getCurrentTimestamp()
            )
        }
        item {
            HomeMessageItem(
                sender = "Sanyi",
                text = "Nem!",
                priority = MessagePriority.LOW,
                isRead = false,
                timestamp = getCurrentTimestamp()
            )
        }
        item {
            HomeMessageItem(
                sender = "Béla",
                text = "Elindultam",
                priority = MessagePriority.LOW,
                isRead = true,
                timestamp = getCurrentTimestamp()
            )
        }
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun HomeNewMessage() {
    val state = rememberLazyListState()
    LazyColumn(
        Modifier
            .simpleVerticalScrollbar(state)
            .fillMaxSize(),
        state = state
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            HomeNewMessageItem(text = "Igen")
        }
        item {
            HomeNewMessageItem(text = "Nem")
        }
        item {
            HomeNewMessageItem(text = "Hívás kérés")
        }
        item {
            HomeNewMessageItem(text = "Utas lemaradás")
        }
    }
}

@Composable
fun HomeNewMessageItem(text: String) {
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
    priority: MessagePriority,
    text: String,
    isRead: Boolean = true,
    timestamp: String
) {
    val animatedRotation by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val backgroundColor = getBackgroundColor(priority)
    val textColor = getTextColor(priority)
    val fontWeight = if (isRead) FontWeight.Normal else FontWeight.Bold

    Row(
        modifier = Modifier
            .padding(20.dp, top = 30.dp, end = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { }
            .background(backgroundColor)
            .border(1.dp, Purple.copy(0.3f), RoundedCornerShape(10.dp))
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeMessageItemContent(sender, text, textColor, fontWeight, modifier = Modifier.weight(1f))
        HomeMessageItemTimestamp(timestamp, modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically))
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
            modifier = Modifier.padding(top = 10.dp, start = 10.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            fontSize = 20.sp,
            color = textColor,
            modifier = Modifier.padding(10.dp),
            fontWeight = fontWeight
        )
    }
}

@Composable
fun HomeMessageItemTimestamp(
    timestamp: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = timestamp,
            fontSize = 16.sp,
            color = Gray,
        )
    }
}

@Composable
fun HomeMessageItemIcons(priority: MessagePriority, animatedRotation: Float, isRead: Boolean, modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "")
    val animatedOffsetY by transition.animateFloat(
        initialValue = -20f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = EaseInBounce),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Row(modifier = modifier){
        if (!isRead) {
            Icon(
                imageVector = Icons.Default.Circle,
                contentDescription = null,
                tint = if(priority == MessagePriority.HIGH) AlertRed else Purple,
                modifier = Modifier
                    .graphicsLayer {
                        translationX = animatedOffsetY
                    }
                    .padding(20.dp)
                    .graphicsLayer { rotationZ = animatedRotation }
                    .size(15.dp)
            )
        }
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeStoredSounds() {
    val pagerState = rememberPagerState(pageCount = { 3 }, initialPage = 0)
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = selectedTabIndex.value,
        //modifier = Modifier.fillMaxWidth(),
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
        StoredSoundItems.entries.forEachIndexed { index, currentTab ->
            Tab(
                selected = selectedTabIndex.value == index,
                selectedContentColor = Purple,
                unselectedContentColor = Color.Gray,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(currentTab.ordinal)
                    }
                },
                text = { Text(text = currentTab.name, fontSize = 20.sp, modifier = Modifier.padding(10.dp) )},
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
    ) { page ->
        when (page) {
            0 -> HomeInformationStoredSounds()
            1 -> HomeErrorStoredSounds()
            2 -> HomeAccidentStoredSounds()
        }
    }
}

@Composable
fun HomeSettings() {
    LazyColumn(
        Modifier
    ) {
        item {
            Column(
                modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp)
            ) {
                Text(text = "Verzió: 54.3.343", fontSize = 20.sp, color = Purple)
                Text(text = "Nem áll rendelkezése új adatcsomag", fontSize = 20.sp, color = Purple)
            }
        }
        item {
            HorizontalDivider(color = Purple.copy(.2f), modifier = Modifier.padding(vertical = 20.dp))
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Adat szinkronizáció", color = Purple, fontSize = 20.sp)
                }
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Fényerő", color = Purple, fontSize = 20.sp)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Jármű állapot", color = Purple, fontSize = 20.sp)
                }
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Eszköz állapot", color = Purple, fontSize = 20.sp)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Razzia", color = Purple, fontSize = 20.sp)
                }
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Kijelzők", color = Purple, fontSize = 20.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeActivity() {
    LazyColumn(
        Modifier
    ) {
        item { 
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Viszonylat útvonalai", color = Purple, fontSize = 20.sp)
                }
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Egyéb útvonalak", color = Purple, fontSize = 20.sp)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Üzemanyag", color = Purple, fontSize = 20.sp)
                }
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Forgalmi szám", color = Purple, fontSize = 20.sp)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
                    Text("Navigáció", color = Purple, fontSize = 20.sp)
                }
                HomeSecondaryButton(modifier = Modifier.weight(1f)) {
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
        }
    }
}


@Composable
fun HomeSecondaryButton(
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit
) {
    Button(
        onClick = { },
        modifier = modifier.border(1.dp, Purple.copy(.2f), RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = LightPurple)
    ) {
        Box(modifier = Modifier.padding(20.dp)) {
            text()
        }
    }
}


@Composable
fun HomeInformationStoredSounds() {
    val state = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = Modifier.simpleVerticalScrollbar(state)
    ) {
        item {
            HomeStoredSoundItem(text = "Ajtók záródnak")
        }
        item {
            HomeStoredSoundItem(text = "Felszállás az első ajtón")
        }
        item {
            HomeStoredSoundItem(text = "Jegyellenőrzés")
        }
        item {
            HomeStoredSoundItem(text = "Rövid várakozás")
        }
        item {
            HomeStoredSoundItem(text = "Ajtók záródnak")
        }
        item {
            HomeStoredSoundItem(text = "Felszállás az első ajtón")
        }
        item {
            HomeStoredSoundItem(text = "Jegyellenőrzés")
        }
        item {
            HomeStoredSoundItem(text = "Rövid várakozás")
        }
    }
}

@Composable
fun HomeErrorStoredSounds() {
    Column {
        HomeStoredSoundItem(text = "Kérem, vigyázzanak, ajtók záródnak!")
        HomeStoredSoundItem(text = "Felszállás az első ajtón")
    }
}

@Composable
fun HomeAccidentStoredSounds() {
    Column {
        HomeStoredSoundItem(text = "Kérem, vigyázzanak, ajtók záródnak!")
        HomeStoredSoundItem(text = "Felszállás az első ajtón")
    }
}

enum class StoredSoundItems{
    Információ,
    Hiba,
    Baleset
}

@Composable
fun HomeStoredSoundItem(text: String) {
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
                AnimatedVolumeIcon(volumeLevel = voiceLevel.intValue)
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
fun AnimatedVolumeIcon(volumeLevel: Int) {
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


@Composable
fun TrackSlider(
    value: Float,
    onValueChange: (newValue: Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    songDuration: Float
) {
    Slider(
        modifier = Modifier.padding(horizontal = 30.dp),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        onValueChangeFinished = {

            onValueChangeFinished()

        },
        valueRange = 0f..songDuration,
        colors = SliderDefaults.colors(
            thumbColor = Purple,
            activeTrackColor = Purple,
            inactiveTrackColor = Purple.copy(.2f),
        )
    )
}

@Composable
fun HomeNumberOfShift(
    modifier: Modifier = Modifier
) {
    Column(
        modifier.padding(start = 0.dp, top = 30.dp)
    ) {
        Text(
            text = "Forgalmi szám: 19980828",
            color = White,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Icon(imageVector = Icons.Default.SevereCold, contentDescription = null, tint = LightPurple)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "17°C", fontSize = 20.sp, color = White)
        }
    }
}


@Composable
fun HomeDateAndTime(
    modifier: Modifier = Modifier
) {
    val currentTime = remember { mutableStateOf(LocalTime.now()) }
    val currentDate = LocalDate.now()

    LaunchedEffect(Unit) {
        while (true) {
            currentTime.value = LocalTime.now()
            delay(1000)
        }
    }

    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val dateFormatter = DateTimeFormatter.ofPattern("yy.MM.dd")

    Box(
        modifier = modifier
            .padding(top = 30.dp, end = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = currentTime.value.format(timeFormatter),
                fontSize = 50.sp,
                color = White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = currentDate.format(dateFormatter),
                fontSize = 30.sp,
                color = White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun VerticalProgressLine(
    modifier: Modifier = Modifier,
    stations: List<Station>
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Box {
            Column {
                stations.forEachIndexed { _, item ->
                    Row(
                        modifier = Modifier
                            .background(if (item.isNext) LightPurple.copy(.5f) else Color.Transparent)
                            .border(if (item.isNext) 1.dp else Dp.Unspecified, Purple.copy(.1f))
                            .padding(top = if (item.isNext) 6.dp else 0.dp)
                    ) {
                        HomeLine(item)
                    }
                }
            }
        }
    }
}

data class Station(
    val name: String,
    val time: String,
    val isArrived: Boolean,
    val isTerminus: Boolean = false,
    val isNext: Boolean = false
)

@Composable
fun PreviewVerticalProgressLine() {

    val stations = listOf(
        Station(name = "Nagytétény, ipartelep", time = "18:41", isArrived = false, isTerminus = true),
        Station(name = "Bányalég utca", time = "18:38", isArrived = false),
        Station(name = "Nagytétényi út", time = "18:32", isArrived = false, isNext = true),
        Station(name = "Növény utca", time = "18:29", isArrived = true),
        Station(name = "Nagytétényi út", time = "18:26", isArrived = true),
        Station(name = "Kossuth Lajos utca", time = "18:23", isArrived = true),
        Station(name = "Leányka utca", time = "18:20", isArrived = true),
        Station(name = "felüljáró", time = "18:17", isArrived = true),
        Station(name = "Kitérő út", time = "18:14", isArrived = true),
        Station(name = "Hunyadi János út", time = "18:11", isArrived = true),
        Station(name = "Budafoki út", time = "18:08", isArrived = true),
        Station(name = "Szent Gellért tér", time = "18:05", isArrived = true),
        Station(name = "Szent Gellért rakpart", time = "18:02", isArrived = true),
        Station(name = "Erzsébet híd", time = "17:59", isArrived = true),
        Station(name = "Szabad sajtó út", time = "17:56", isArrived = true),
        Station(name = "Ferenciek tere", time = "17:53", isArrived = true),
        Station(name = "Kossuth Lajos utca", time = "17:50", isArrived = true),
        Station(name = "Rákóczi út", time = "17:47", isArrived = true),
        Station(name = "Baross tér", time = "17:44", isArrived = true),
        Station(name = "Thököly út", time = "17:41", isArrived = true),
        Station(name = "Bosnyák tér", time = "17:38", isArrived = true),
        Station(name = "Csömöri út", time = "17:35", isArrived = true),
        Station(name = "felüljáró", time = "17:32", isArrived = true),
        Station(name = "Drégelyvár utca", time = "17:29", isArrived = false),
        Station(name = "Nyírpalota út", time = "17:26", isArrived = false),
        Station(name = "Újpalota, Nyírpalota út vá.", time = "17:23", isArrived = false, isNext = true)
    )

    VerticalProgressLine(stations = stations)
}

@Composable
fun HomeLine(
    station: Station
) {
    val stationBoxSize = if(station.isTerminus) 20.dp else 30.dp

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            if(station.isTerminus) {
                Box(
                    Modifier
                        .padding(5.dp)
                        .border(5.dp, Green.copy(.3f), CircleShape)
                        .size(stationBoxSize)
                        .padding(5.dp)
                        .background(Green, CircleShape))
            } else {
                Box(
                    modifier = Modifier
                        .border(5.dp, Purple, CircleShape)
                        .size(stationBoxSize)
                        .background(
                            if (station.isArrived) Purple else Color.White,
                            shape = CircleShape
                        )
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = station.time, fontSize = 20.sp, fontWeight = if(station.isTerminus) FontWeight.Bold else FontWeight.Normal, color = Purple)
                    Spacer(modifier = Modifier.width(30.dp))
                    if(station.isNext)
                    Text(text = "+ 3", modifier = Modifier
                        .background(
                            AlertBackgroundRed,
                            RoundedCornerShape(5.dp)
                        )
                        .padding(3.dp), color = AlertRed, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = station.name, fontSize = 20.sp, fontWeight = if(station.isTerminus) FontWeight.Bold else FontWeight.Normal, color = Purple)
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth().padding(start = 0.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Canvas(
                modifier = Modifier
                    .height(40.dp)
            ) {
                drawLine(
                    color = Purple,
                    start = center.copy(y = -40f, x = 30f.dp.toPx()/2),
                    end = center.copy(y = size.height + 65f, x = 30f.dp.toPx()/2),
                    strokeWidth = if(station.isArrived) 30f else 8f,
                    pathEffect = if(station.isArrived) PathEffect.cornerPathEffect(0f) else PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }
            if(station.isNext){
                Box(
                    Modifier
                        .border(1.dp, BusBlue, CircleShape)
                        .background(BusBlue, CircleShape)
                        .padding(4.dp)
                ) {
                    Icon(imageVector = Icons.Default.DirectionsBus, contentDescription = null, tint = White)
                }
            }
        }

    }
}
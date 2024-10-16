package com.zenitech.futar.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Hexagon
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SevereCold
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import com.zenitech.futar.ui.theme.*
import kotlinx.coroutines.delay
import java.lang.Float.max
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
fun HomeHeaderPreview(){
    FutarTheme {
        HomeHeader(isRazziaMode = true, isLoggedIn = true)
    }
}

@Composable
fun HomeHeader(
    isRazziaMode: Boolean = true,
    isStopPressed: Boolean = true,
    isLoggedIn: Boolean
) {
    Row(
        modifier = Modifier
            .background(Purple)
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if(isLoggedIn) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
            ) {
                HomeHeaderNumberAndTerminalOfBus(modifier = Modifier.fillMaxHeight())
                Spacer(modifier = Modifier.width(30.dp))
                HomeHeaderNumberOfShift(modifier = Modifier.fillMaxHeight())
            }
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            if(isLoggedIn){
                AnimatedVisibility(visible = isStopPressed) {
                    HomeStopPressed(modifier = Modifier.fillMaxHeight())
                }
                AnimatedVisibility(visible = isRazziaMode) {
                    HomeHeaderHandrail(modifier = Modifier.fillMaxHeight())
                }
                HomeHeaderDelayOrHurry(modifier = Modifier.fillMaxHeight())
                Spacer(modifier = Modifier.width(20.dp))
            }
            HomeHeaderDateAndTime(modifier = Modifier.fillMaxHeight())
        }
    }
}

@Composable
fun HomeStopPressed(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 20.dp, end = 20.dp)
            .aspectRatio(1f)
            .background(
                White.copy(.2f),
                RoundedCornerShape(20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .rotate(20f)
                .padding(10.dp)
                .drawWithCache {
                    val roundedPolygonRed = RoundedPolygon(
                        numVertices = 8,
                        radius = size.minDimension / 2.5f,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val roundedPolygonWhite = RoundedPolygon(
                        numVertices = 8,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val roundedPolygonPathWhite = roundedPolygonWhite
                        .toPath()
                        .asComposePath()
                    val roundedPolygonPathRed = roundedPolygonRed
                        .toPath()
                        .asComposePath()

                    onDrawBehind {
                        drawPath(roundedPolygonPathWhite, color = Black.copy(.1f))
                        drawPath(roundedPolygonPathRed, color = AlertRed)
                    }
                }
                .fillMaxSize(),
        )
        Text(
            text = "STOP",
            modifier = Modifier,
            color = White,
            fontWeight = FontWeight.Black,
            fontSize = 20.sp
        )
    }
}

fun RoundedPolygon.getBounds() = calculateBounds().let { Rect(it[0], it[1], it[2], it[3]) }
class RoundedPolygonShape(
    private val polygon: RoundedPolygon,
    private var matrix: Matrix = Matrix()
) : Shape {
    private var path = Path()
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        path.rewind()
        path = polygon.toPath().asComposePath()
        matrix.reset()
        val bounds = polygon.getBounds()
        val maxDimension = max(bounds.width, bounds.height)
        matrix.scale(size.width / maxDimension, size.height / maxDimension)
        matrix.translate(-bounds.left, -bounds.top)

        path.transform(matrix)
        return Outline.Generic(path)
    }
}

@Composable
fun HomeHeaderNumberAndTerminalOfBus(
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
            .padding(20.dp),
        contentAlignment = Alignment.Center
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
                    fontSize = 28.sp,
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
fun HomeHeaderNumberOfShift(
    modifier: Modifier = Modifier
) {
    Column(
        modifier.padding(start = 0.dp, top = 30.dp)
    ) {
        Text(
            text = "Felhasználó: 12345",
            color = White,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Forgalmi szám: 11111111",
            color = White,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Icon(imageVector = Icons.Default.SevereCold, contentDescription = null, tint = LightPurple)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "17°C", fontSize = 20.sp, color = White)
        }
    }
}

@Composable
fun HomeHeaderHandrail(modifier: Modifier) {
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
            .fillMaxSize()
            .offset(x = 4.dp)
        )
    }
}

@Composable
fun HomeHeaderDelayOrHurry(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 20.dp)
            .aspectRatio(1f)
            .background(
                White.copy(.2f),
                RoundedCornerShape(20.dp)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "+3",
            modifier = Modifier
                .background(AlertRed, CircleShape)
                .padding(15.dp),
            color = AlertBackgroundRed,
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp
        )
    }
}

@Composable
fun HomeHeaderDateAndTime(
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

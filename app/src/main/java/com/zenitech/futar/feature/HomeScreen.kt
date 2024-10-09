package com.zenitech.futar.feature

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.R
import com.zenitech.futar.ui.theme.BusBlue
import com.zenitech.futar.ui.theme.BusDarkBlue
import com.zenitech.futar.ui.theme.FutarTheme
import com.zenitech.futar.ui.theme.Green
import com.zenitech.futar.ui.theme.Purple
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

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
fun HomeContent() {
    Row(
        Modifier
            .fillMaxSize()
            .background(Purple)
    ) {
        Column(
            Modifier
                .weight(1f)
        ) {
            Row(
                Modifier
            ) {
                HomeNumberOfShift(
                    modifier = Modifier
                )
                HomeNumberAndTerminalOfBus(
                    modifier = Modifier.weight(1f)
                )
                HomeDateAndTime()
            }
            Row(
                Modifier
                    .padding(15.dp)
            ) {
                HomeJourney()
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(end = 30.dp)
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(35.dp)
                    .background(BusDarkBlue),
            )
            Box(
                Modifier
                    .padding(start = 8.dp)
                    .fillMaxHeight()
                    .width(30.dp)
                    .background(BusBlue),
            )
            Row {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Box(
                        Modifier
                            .height(80.dp)
                            .width(50.dp)
                            .background(Color.Yellow)
                    )
                    Box(
                        Modifier
                            .height(30.dp)
                            .width(30.dp)
                            .background(Color.Green),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "STOP", fontSize = 8.sp, color = Purple.copy(.4f))
                    }
                }
            }
        }
    }
}


@Composable
fun HomeNumberAndTerminalOfBus(
    modifier: Modifier
) {
    Column(
        modifier.padding(30.dp)
    ) {
        Text(
            text = "133E Nagytétény, ipartelep",
            color = Color.White,
            fontSize = 30.sp
        )
    }
}


@Composable
fun HomeJourney(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyColumn(
            modifier = modifier
                .padding(15.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(1.dp, Purple.copy(.2f), RoundedCornerShape(30.dp))
                .background(Color.White, RoundedCornerShape(30.dp))
                .fillMaxWidth(.5f)
                .fillMaxHeight()
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
fun HomeSettings(
    modifier: Modifier = Modifier
) {
    Column {
        Column(
            modifier = modifier
                .padding(15.dp)
                .background(Color.White, RoundedCornerShape(15.dp))
                .fillMaxWidth()
                .fillMaxHeight(.3f)
                .drawWithContent {
                    drawContent()
                    //drawNeonStroke(radius = 30.dp)
                }
                .padding(20.dp)
        ) {
        }
        Column(
            modifier = modifier
                .padding(15.dp)
                .background(Color.White.copy(.8f), RoundedCornerShape(15.dp))
                .fillMaxSize()
                .padding(20.dp)
        ) {
        }
    }
}

@Composable
fun HomeNumberOfShift(
    modifier: Modifier = Modifier
) {
    Column(
        modifier.padding(30.dp)
    ) {
        Text(
            text = "Forgalmi szám: 19980828",
            color = Color.White,
            fontSize = 30.sp
        )
    }
}

@Composable
fun YellowGreenButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green,
            contentColor = Color.White
        ),
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color.Yellow,
                shape = RoundedCornerShape(8.dp) // You can adjust corner radius as needed
            )
    ) {
        Text(text = text)
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier
) {
    Button(onClick = { /*TODO*/ },
        elevation = ButtonDefaults.buttonElevation(0.dp),
        modifier = modifier
            .padding(end = 30.dp, top = 30.dp)
        ,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Purple
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text("Test", modifier.padding(10.dp), textAlign = TextAlign.Center)
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
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = currentTime.value.format(timeFormatter),
                fontSize = 60.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = currentDate.format(dateFormatter),
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}




// SOURCE: https://stackoverflow.com/questions/74253100/neonglowing-button-in-jetpack-compose
fun ContentDrawScope.drawNeonStroke(radius: Dp) {
    this.drawIntoCanvas {
        val paint =
            Paint().apply {
                style = PaintingStyle.Stroke
                strokeWidth = 10f
            }

        val frameworkPaint =
            paint.asFrameworkPaint()

        val color = Purple

        this.drawIntoCanvas {
            frameworkPaint.color = color.copy(alpha = 0f).toArgb()
            frameworkPaint.setShadowLayer(
                radius.toPx(), 0f, 0f, color.copy(alpha = 1f).toArgb()
            )
            it.drawRoundRect(
                left = 0f,
                right = size.width,
                bottom = size.height,
                top = 0f,
                radiusY = radius.toPx(),
                radiusX = radius.toPx(),
                paint = paint
            )
            drawRoundRect(
                color = Purple,
                size = size,
                cornerRadius = CornerRadius(radius.toPx(), radius.toPx()),
                style = Stroke(width = 1.dp.toPx())
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
            .padding(horizontal = 100.dp, vertical = 50.dp)
            .fillMaxHeight()
    ) {
        stations.forEachIndexed { _, item ->
            HomeLine(item)
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
    Column(
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if(station.isTerminus) {
                Box(
                    Modifier
                        .padding(5.dp)
                        .border(5.dp, Green.copy(.3f), CircleShape)
                        .size(20.dp)
                        .padding(5.dp)
                        .background(Green, CircleShape))
            } else {
                Box(
                    modifier = Modifier
                        .border(5.dp, Purple, CircleShape)
                        .size(30.dp)
                        .background(
                            if (station.isArrived) Purple else Color.Transparent,
                            shape = CircleShape
                        )
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = station.time, fontSize = 20.sp, fontWeight = if(station.isTerminus) FontWeight.Bold else FontWeight.Normal, color = Purple)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = station.name, fontSize = 20.sp, fontWeight = if(station.isTerminus) FontWeight.Bold else FontWeight.Normal, color = Purple)
            }
        }

        Box() {
            Canvas(
                modifier = Modifier
                    .height(80.dp)
            ) {
                drawLine(
                    color = Purple,
                    start = center.copy(y = -40f, x = 45f),
                    end = center.copy(y = size.height + 40f, x = 45f),
                    strokeWidth = if(station.isArrived) 30f else 10f,
                    pathEffect = if(station.isArrived) PathEffect.cornerPathEffect(0f) else PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
            }
            if(station.isNext){
                Box(
                    Modifier
                        .offset(x = (-10).dp, y = 8.dp)
                        .background(Color.LightGray, CircleShape)
                        .padding(10.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_bus), contentDescription = null, colorFilter = ColorFilter.tint(
                        Purple), contentScale = ContentScale.Fit, modifier = Modifier.size(30.dp))

                }
            }
        }

    }
}


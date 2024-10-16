package com.zenitech.futar.feature.boarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zenitech.futar.feature.boarding.journey.JourneyScreen
import com.zenitech.futar.feature.boarding.login.LoginScreen
import com.zenitech.futar.feature.boarding.traffic_number.AutomaticTrafficNumberScreen
import com.zenitech.futar.feature.boarding.traffic_number.TrafficNumberScreen
import com.zenitech.futar.feature.boarding.welcome.WelcomeScreen
import kotlinx.coroutines.launch

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun BoardingScreenPreview(){
    BoardingScreen(onStatusBarChange = {}) {

    }
}

@Composable
fun BoardingScreen(
    onStatusBarChange: (String) -> Unit,
    onNavigateToHome: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 5 }, initialPage = 0)
    val scope = rememberCoroutineScope()


    Box(contentAlignment = Alignment.Center) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> WelcomeScreen {
                    scope.launch {
                        pagerState.animateScrollToPage(1)
                        onStatusBarChange("Kérem, adja meg az azonosítóját!")
                    }
                }
                1 -> LoginScreen(
                    onBack = {
                        scope.launch {
                            pagerState.animateScrollToPage(0)
                            onStatusBarChange("Kérem, adja meg az azonosítóját!")
                        }
                    }
                ) {
                    scope.launch {
                        pagerState.animateScrollToPage(2)
                        onStatusBarChange("Kérem, ellenőrizze az azonosítójához rendelt forgalmi számot!")
                    }
                }
                2 -> AutomaticTrafficNumberScreen(
                    onBack = {
                        scope.launch {
                            pagerState.animateScrollToPage(1)
                            onStatusBarChange("Kérem, adja meg az azonosítóját!")
                        }
                    },
                    onNavigateToTrafficNumber = {
                        scope.launch {
                            pagerState.animateScrollToPage(3)
                            onStatusBarChange("Kérem, adja meg a forgalmi számot!")
                        }
                    }
                ) {
                    scope.launch {
                        pagerState.animateScrollToPage(4)
                        onStatusBarChange("Kérem, válasszon ki egy menetet!")
                    }
                }
                3 -> TrafficNumberScreen(
                    onBack = {
                        scope.launch {
                            pagerState.animateScrollToPage(2)
                            onStatusBarChange("Kérem, ellenőrizze az azonosítójához rendelt forgalmi számot!")
                        }
                    }
                ) {
                    scope.launch {
                        pagerState.animateScrollToPage(4)
                        onStatusBarChange("Kérem, válasszon ki egy menetet!")
                    }
                }
                4 -> JourneyScreen(
                    onBack = {
                        scope.launch {
                            pagerState.animateScrollToPage(2)
                            onStatusBarChange("Kérem, ellenőrizze az azonosítójához rendelt forgalmi számot!")
                        }
                    },
                    onNavigateToHome = onNavigateToHome
                )
            }
        }
    }
}
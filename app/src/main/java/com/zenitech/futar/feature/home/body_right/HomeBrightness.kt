package com.zenitech.futar.feature.home.body_right

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.ui.HomeSecondaryButton
import com.zenitech.futar.ui.theme.BusBlue
import com.zenitech.futar.ui.theme.BusDarkBlue
import com.zenitech.futar.ui.theme.Purple

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun HomeBrightnessPreview(){
    HomeBrightness({  }, 1f)
}

@Composable
fun HomeBrightness(onBrightnessChange: (Float) -> Unit, brightness: Float) {
    HomeBrightnessContent(
        onBrightnessChange = onBrightnessChange,
        brightness = brightness
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBrightnessContent(
    onBrightnessChange: (Float) -> Unit,
    brightness: Float
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(text = "Fényerő beállítása", color = Purple, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            }
            HorizontalDivider(color = Purple.copy(0.2f), modifier = Modifier.padding(vertical = 40.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                val gradientBrush = Brush.horizontalGradient(
                    colors = listOf(BusBlue, Color.White)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .border(1.dp, Purple.copy(0.3f), CircleShape)
                        .background(gradientBrush, CircleShape)
                        .align(Alignment.Center)
                )

                Slider(
                    thumb = {
                        SliderDefaults.Thumb(
                            interactionSource = remember { MutableInteractionSource() },
                            colors = SliderDefaults.colors(thumbColor = BusDarkBlue),
                            thumbSize = DpSize(width = 60.dp, height = 60.dp)
                        )
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = BusDarkBlue,
                        activeTrackColor = Color.Transparent,
                        inactiveTrackColor = Color.Transparent
                    ),
                    value = brightness,
                    onValueChange = { onBrightnessChange(it) },
                    valueRange = 0.4f..1f,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                HomeSecondaryButton(onClick = { onBrightnessChange(0.4f) }) {
                    Text(text = "Dark", color = Purple)
                }

                HomeSecondaryButton(onClick = { onBrightnessChange(1f) }) {
                    Text(text = "Light", color = Purple)
                }
            }
        }
    }
}


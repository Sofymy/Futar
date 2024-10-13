package com.zenitech.futar.feature.boarding.welcome

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.R
import com.zenitech.futar.ui.theme.Purple

@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen {}
}

@Composable
fun WelcomeScreen(
    onNavigateToLoginScreen: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.bkk_futar
                ),
                contentDescription = null,
            )
        }
        Column(
            Modifier
                .padding(40.dp)
                .width(IntrinsicSize.Max)
                .weight(1f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WelcomeButton(text = "Járművezetői mód", Modifier.fillMaxWidth(), onClick = onNavigateToLoginScreen)
            Spacer(modifier = Modifier.height(50.dp))
            WelcomeButton(text = "Karbantartás", Modifier.fillMaxWidth(), onClick = {})
        }
    }
}

@Composable
fun WelcomeButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = animateColorAsState(targetValue = if (isPressed) Purple else Color.White, label = "")
    val contentColor = animateColorAsState(targetValue = if (isPressed) Color.White else Purple, label = "")

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
            .border(3.dp, Purple, RoundedCornerShape(10.dp))
            .background(
                color = backgroundColor.value,
                RoundedCornerShape(10.dp)
            )
            .padding(30.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = contentColor.value, fontSize = 30.sp)
    }

}

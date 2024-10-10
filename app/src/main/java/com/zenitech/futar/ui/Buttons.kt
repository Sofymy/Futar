package com.zenitech.futar.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenitech.futar.feature.home.HomeButton
import com.zenitech.futar.ui.theme.AlertBackgroundRed
import com.zenitech.futar.ui.theme.AlertRed
import com.zenitech.futar.ui.theme.LightPurple
import com.zenitech.futar.ui.theme.Purple

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
                    Text(text = "2", color = AlertBackgroundRed, fontWeight = FontWeight.Bold, fontSize = 20.sp)
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

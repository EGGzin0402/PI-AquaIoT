package com.example.aquaiot.presentation.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquaiot.R

@Composable
fun Navbar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    @DrawableRes navIcon: Int
) {
    Box(
        modifier = Modifier
            .offset(
                y = (-80).dp
            )
            .fillMaxWidth()
            .requiredHeight(height = 175.dp)
            .clip(shape = RoundedCornerShape(25.dp))
            .background(color = Color(0xffd9d9d9))
    )
    Image(
        painter = painterResource(id = R.drawable.minilogo),
        contentDescription = "minilogo",
        modifier = Modifier
            .offset(y = (20).dp)
            .requiredSize(size = 90.dp)
    )
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .offset(
                x = (160).dp,
                y = 40.dp
            )
            .requiredWidth(50.dp)
            .requiredHeight(50.dp)
    ) {
        Icon(
            painter = painterResource(id = navIcon),
            tint = Color(0xff1c1b1f),
            contentDescription = "profile_icon",
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
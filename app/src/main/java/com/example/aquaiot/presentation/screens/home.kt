package com.example.aquaiot.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquaiot.R

@Composable
fun Frame19(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xff1d1d1b))
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.5.dp,
                    y = (-71).dp)
                .requiredWidth(width = 893.dp)
                .requiredHeight(height = 245.dp)
                .clip(shape = RoundedCornerShape(50.dp))
                .background(color = Color(0xffd9d9d9)))
        Image(
            painter = painterResource(id = R.drawable.minilogo),
            contentDescription = "minilogo",
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(y =(-200).dp)
                .requiredSize(size = 512.dp))
        Text(
            text = "Bem-vindo, Sr. Antônio",
            color = Color.White,
            style = TextStyle(
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 59.dp,
                    y = 249.dp)
                .requiredWidth(width = 976.dp)
                .requiredHeight(height = 77.dp))
        IconButton(
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 929.dp,
                    y = 265.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.grid_view),
                contentDescription = "grid_view",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = "Esses são os seus dispositivos",
            color = Color.White,
            style = TextStyle(
                fontSize = 48.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 59.dp,
                    y = 326.dp))
    }
}

@Preview(widthDp = 1080, heightDp = 1920)
@Composable
private fun Frame19Preview() {
    Frame19(Modifier)
}

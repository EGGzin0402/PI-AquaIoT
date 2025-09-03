package com.example.aquaiot.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquaiot.R
import com.example.aquaiot.domain.model.Device
import com.example.aquaiot.presentation.sign_in.UserData
import com.example.aquaiot.presentation.ui.components.Navbar
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Home(
    modifier: Modifier = Modifier,
    userData: UserData?,
    navbarClick: () -> Unit = { }
) {
    val devicesState = remember { mutableStateOf<List<Device>>(emptyList()) }

    Log.d("HomeScreen", "User ID: ${userData?.userId}")

    LaunchedEffect(Unit) {
         FirebaseFirestore.getInstance()
            .collection("Users")
            .document(userData?.userId ?: "")
            .collection("dispositivos")
            .addSnapshotListener { snapshots, _ ->
                if (snapshots != null) {
                    val devices = snapshots.map { doc ->
                        Device(
                            name = doc.id,
                            nextTime = doc.getString("time") ?: "",
                            status = doc.getBoolean("status") ?: false,
                            temperature = doc.getDouble("temperatura") ?: 0,
                            ph = doc.getDouble("ph") ?: 0
                        )
                    }
                    devicesState.value = devices
                }
            }
    }

    Log.d("HomeScreen", "Devices: ${devicesState.value}")

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xff1d1d1b))
    ) {
        Navbar(
            onClick = navbarClick,
            navIcon = R.drawable.profile_icon
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .offset(
                    y = 100.dp
                )
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Column {
                Text(
                    text = "Bem-vindo, ${userData?.username}",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Esses são os seus dispositivos",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.grid_view),
                    tint = Color(0xffd9d9d9),
                    contentDescription = "grid_view",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

        Row(
            modifier = Modifier
                .offset(y = 170.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(devicesState.value.size) { index ->
                    val device = devicesState.value[index]
                    DispositiveButton(
                        name = device.name,
                        nextTime = device.nextTime,
                        status = device.status,
                        temperature = device.temperature,
                        ph = device.ph
                    )
                }
            }
        }
    }

}

@Composable
fun DispositiveButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { /*TODO*/ },
    name: String,
    nextTime: String,
    status: Boolean,
    temperature: Number,
    ph: Number,
) {
    val screenWidth = 1080
    val mid = screenWidth / 2
    Text(
        text = name,
        color = Color.White,
        style = TextStyle(
            fontSize = 20.sp
        ),
        modifier = Modifier
            .offset(x = 30.dp)
    )
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .requiredHeight(130.dp)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.White)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.linearGradient(
                        0f to Color(0xff737373),
                        0.25f to Color(0xffd9d9d9),
                        0.5f to Color(0xffefefef),
                        0.75f to Color(0xffd9d9d9),
                        1f to Color(0xff737373),
                        start = Offset(mid.toFloat(), 0f),
                        end = Offset(mid.toFloat(), 400f)
                    )
                )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.minilogo),
                    contentDescription = "minilogo",
                    modifier = Modifier
                        .requiredSize(size = 80.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.fork_spoon),
                                contentDescription = "device_thermostat",
                                colorFilter = ColorFilter.tint(Color(0xff1c1b1f)),
                                modifier = Modifier
                                    .requiredSize(size = 34.dp)
                            )
                            Text(
                                text = nextTime,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff1d1d1b),
                                style = TextStyle(
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .requiredHeight(height = 24.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.spo2),
                                contentDescription = "device_thermostat",
                                colorFilter = ColorFilter.tint(Color(0xff1c1b1f)),
                                modifier = Modifier
                                    .offset(
                                        x = (-15).dp
                                    )
                                    .requiredSize(size = 34.dp)
                            )
                            Text(
                                text = if (status) "ON" else "OFF",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff1d1d1b),
                                style = TextStyle(
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier
                            )
                        }
                    }
                    VerticalDivider(
                        color = Color.Black,
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .height(250.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "$temperature °C",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff1d1d1b),
                                style = TextStyle(
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier
                            )
                            Image(
                                painter = painterResource(id = R.drawable.device_thermostat),
                                contentDescription = "device_thermostat",
                                colorFilter = ColorFilter.tint(Color(0xff1c1b1f)),
                                modifier = Modifier
                                    .requiredSize(size = 34.dp)
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .requiredHeight(height = 24.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "$ph pH",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff1d1d1b),
                                style = TextStyle(
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier
                            )
                            Image(
                                painter = painterResource(id = R.drawable.bubble_chart),
                                contentDescription = "device_thermostat",
                                colorFilter = ColorFilter.tint(Color(0xff1c1b1f)),
                                modifier = Modifier
                                    .requiredSize(size = 34.dp)
                            )
                        }
                    }
                }
            }

        }
    }
}

@Preview(widthDp = 1080, heightDp = 1920)
@Composable
private fun HomePreview() {
    Home(
        userData = UserData(
            userId = "1",
            username = "Usuário de Teste",
            profilePictureUrl = null
        )
    )
}

@Preview(widthDp = 1080, heightDp = 1920)
@Composable
private fun ButtonPreview() {
    DispositiveButton(
        name = "AWF-393",
        nextTime = "12:00",
        status = false,
        temperature = 26.5,
        ph = 7
    )
}
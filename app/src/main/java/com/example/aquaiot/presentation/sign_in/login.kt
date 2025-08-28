import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquaiot.R
import com.example.aquaiot.presentation.sign_in.SignInState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(
    modifier: Modifier = Modifier,
    onComplete: () -> Unit = { },
    onGoogleSign: () -> Unit = { }
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xff1d1d1b))
    ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "image 1",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 262.dp,
                            y = 50.dp
                        )
                        .requiredWidth(width = 556.dp)
                        .requiredHeight(height = 555.dp))
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(
                    x = (-400).dp,
                    y = 200.dp
                )
                .requiredSize(size = 760.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xffafafaf))
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(
                    x = 200.dp,
                    y = 550.dp
                )
                .requiredSize(size = 902.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xffc8c8c8))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 540.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Cadastre-se",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 96.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .requiredWidth(width = 550.dp)
                    .requiredHeight(height = 100.dp)
            )
            Spacer(modifier = Modifier.requiredHeight(height = 100.dp))
            Text(
                text = "Email:",
                color = Color.White,
                style = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 200.dp)
                    .requiredHeight(height = 77.dp)

            )
            TextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor =  Color(0xffd9d9d9),
                    focusedContainerColor = Color(0xffd9d9d9)
                ),
                modifier = Modifier
                    .requiredWidth(width = 726.dp)
                    .requiredHeight(height = 125.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
            )
            Spacer(modifier = Modifier.requiredHeight(height = 50.dp))
            Text(
                text = "Senha:",
                color = Color.White,
                style = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 200.dp)
                    .requiredHeight(height = 77.dp)

            )
            TextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xffd9d9d9)
                ),
                modifier = Modifier
                    .requiredWidth(width = 726.dp)
                    .requiredHeight(height = 125.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
            )
            Spacer(modifier = Modifier.requiredHeight(height = 50.dp))
            Text(
                text = "Confirmar Senha:",
                color = Color.White,
                style = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 200.dp)
                    .requiredHeight(height = 77.dp)

            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 178.dp)
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xffd9d9d9)
                    ),
                    modifier = Modifier
                        .requiredWidth(width = 726.dp)
                        .requiredHeight(height = 125.dp)
                        .clip(shape = RoundedCornerShape(100.dp))
                )
                Spacer(modifier = Modifier.requiredWidth(width = 20.dp))
                Button(
                    onClick = onComplete,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffd9d9d9)),
                    modifier = Modifier
                        .requiredSize(size = 125.dp)
//                        .offset(x = 100.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_forward),
                        contentDescription = "arrow foward",
                        tint = Color.Black,
                        modifier = Modifier
                            .requiredSize(size = 60.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.requiredHeight(height = 100.dp))

            Text(
                text = "Ou",
                color = Color.White,
                style = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .requiredWidth(width = 70.dp)
                    .requiredHeight(height = 77.dp)
            )
            Spacer(modifier = Modifier.requiredHeight(height = 100.dp))
            Button(
                onClick = onGoogleSign,
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffd9d9d9)),
                modifier = Modifier
                    .requiredWidth(width = 726.dp)
                    .requiredHeight(height = 125.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.googlelogo),
                        contentDescription = "image 2",
                        modifier = Modifier
                            .requiredSize(size = 92.dp))
                    Text(
                        text = "Cadastre-se com Google",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                    )
                }
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    modifier: Modifier = Modifier,
    state: SignInState,
    onComplete: () -> Unit = { },
    onSignInClick: () -> Unit,
) {

    val context = LocalContext.current
    LaunchedEffect(state.signInError) {
        state.signInError?.let { errorMessage ->
            // Show a toast message
            Toast
                .makeText(context, errorMessage, Toast.LENGTH_LONG)
                .show()
        }
    }

    var email by remember{ mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xff1d1d1b)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "image 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .requiredWidth(width = 200.dp)
                .requiredHeight(height = 200.dp))
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(
                    x = (-400).dp,
                    y = 200.dp
                )
                .requiredSize(size = 760.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xffafafaf))
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(
                    x = 200.dp,
                    y = 550.dp
                )
                .requiredSize(size = 902.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xffc8c8c8))
        )
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp)
                .fillMaxWidth()
                .offset(y = 140.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Entre",
                textAlign = TextAlign.Center,
                color = Color.White,
                style = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.requiredHeight(height = 50.dp))
            Text(
                text = "Email:",
                color = Color.White,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()

            )
            Spacer(modifier = Modifier.requiredHeight(height = 10.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor =  Color(0xffd9d9d9),
                    focusedContainerColor = Color(0xffd9d9d9)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 50.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
            )
            Spacer(modifier = Modifier.requiredHeight(height = 50.dp))
            Text(
                text = "Senha:",
                color = Color.White,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()

            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
//                    .padding(start = 178.dp)
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xffd9d9d9)
                    ),
                    modifier = Modifier
                        .requiredHeight(height = 50.dp)
                        .clip(shape = RoundedCornerShape(100.dp))
                )
                Button(
                    onClick = onComplete,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffd9d9d9)),
                    modifier = Modifier
                        .requiredSize(size = 50.dp)
//                        .offset(x = 100.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_forward),
                        contentDescription = "arrow foward",
                        tint = Color.Black,
                        modifier = Modifier
                            .requiredSize(size = 25.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.requiredHeight(height = 50.dp))

            Text(
                text = "Ou",
                color = Color.White,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.requiredHeight(height = 100.dp))
            Button(
                onClick = onSignInClick,
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffd9d9d9)),
                modifier = Modifier
                    .requiredWidth(width = 726.dp)
                    .requiredHeight(height = 125.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.googlelogo),
                        contentDescription = "image 2",
                        modifier = Modifier
                            .requiredSize(size = 92.dp))
                    Text(
                        text = "Entre com Google",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .requiredWidth(width = 440.dp)
                            .requiredHeight(height = 60.dp)
                    )
                }
            }


        }
    }
}

@Preview(widthDp = 1080, heightDp = 1920)
@Composable
private fun Frame1Preview() {
    Cadastro(Modifier)
}

@Preview(widthDp = 1080, heightDp = 1920)
@Composable
private fun LoginPreview() {
}
@Preview
@Composable
fun ButtonPreview() {

    Button(
        onClick = { },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffd9d9d9)),
        modifier = Modifier
            .requiredSize(size = 125.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_forward),
            contentDescription = "arrow foward",
            tint = Color.Black,
            modifier = Modifier
                .requiredSize(size = 60.dp)
        )
    }

}
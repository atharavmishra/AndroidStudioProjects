package android.myapplication.jetpaccomposetextfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.myapplication.jetpaccomposetextfield.ui.theme.JetpacComposeTextFieldTheme
import android.myapplication.jetpaccomposetextfield.ui.theme.Shapes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset.Companion.Unspecified
import androidx.compose.ui.geometry.Size.Companion.Unspecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnitType.Companion.Unspecified
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpacComposeTextFieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Googlebutton()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Googlebutton(text: String = "Sign Up With Google", loadingtext : String = "Signing Up.....") {
    
    var clicked by remember {
        mutableStateOf(false)
    }
    var passwordvisibility by remember {
        mutableStateOf(true)
    }
    val icon = if(passwordvisibility){
        painterResource(id = R.drawable.ic_baseline_visibility_24)
    }
    else{
        painterResource(id = R.drawable.ic_baseline_visibility_off_24)
    }
Surface(
    onClick = {clicked =! clicked},
    shape = Shapes.medium,
    border = BorderStroke(width = 1.dp, color = Color.LightGray),
    color = MaterialTheme.colors.surface

){Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center)
{Row(modifier= Modifier
    .padding(start = 12.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
    .animateContentSize(
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
    )
   ) { Icon(painter = painterResource(id = R.drawable.ic_google_logo),
    contentDescription = "Google Logo", tint = Color.Unspecified
)
    Spacer(modifier = Modifier.width(8.dp))
    Text(if(clicked) loadingtext else text)
    if(clicked){
        Spacer(modifier = Modifier.width(8.dp))
        CircularProgressIndicator(modifier = Modifier
            .height(16.dp)
            .width(16.dp),
            strokeWidth = 1.dp,
            color = MaterialTheme.colors.primary)
    }
}
    var password by remember {
        mutableStateOf("")
    }
    OutlinedTextField(value = password, onValueChange = {password=it},
    placeholder = { Text(text = "Password")}, label = {Text(text = "Password")},
        trailingIcon = { IconButton(onClick = { passwordvisibility = !passwordvisibility }) {
            Icon(painter = icon,
                contentDescription = "Visible" )
        }}, visualTransformation = if(passwordvisibility) VisualTransformation.None else PasswordVisualTransformation())

}
}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpacComposeTextFieldTheme {
        Googlebutton()
    }
}
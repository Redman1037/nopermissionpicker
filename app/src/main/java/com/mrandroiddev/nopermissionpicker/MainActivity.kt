package com.mrandroiddev.nopermissionpicker

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.mrandroiddev.nopermissionpicker.ui.theme.NoPermissionPickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoPermissionPickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val activity = LocalContext.current as Activity

    val uriText = remember {
        mutableStateOf("Empty")
    }

    val pickImage = remember {
        mutableStateOf<Boolean?>(null)
    }

    if (pickImage.value==true) {
        NoPermissionPicker.pickImage(activity, noPermissionPickerType = NoPermissionPickerType.CAMERA_AND_GALLERY) {
            uriText.value = it.toString()
            pickImage.value = null
        }
    }

    Spacer(modifier = Modifier.height(40.dp))

    Button(modifier= Modifier
        .height(60.dp)
        .fillMaxWidth()
        .padding(horizontal = 40.dp),onClick = {

        pickImage.value = true

    }) {
        Text(modifier=Modifier.height(30.dp),text = "Pick Image")
    }

    Text(
        text = "Hello ${uriText.value}!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoPermissionPickerTheme {
        Greeting("Android")
    }
}
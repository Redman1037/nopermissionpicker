package com.mrandroiddev.nopermissionpicker

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
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
                    PickerResult()
                }
            }
        }
    }
}

@Composable
fun PickerResult(modifier: Modifier = Modifier) {

    val activity = LocalContext.current as Activity

    val uri = remember {
        mutableStateOf<Uri?>(null)
    }

    val pickImage = remember {
        mutableStateOf<Boolean?>(null)
    }

    if (pickImage.value == true) {
        NoPermissionPicker.pickImage(
            activity,
            noPermissionPickerType = NoPermissionPickerType.CAMERA_AND_GALLERY
        ) { uriResult ->
            if (uriResult != null) {
                uri.value = uriResult
            }
            pickImage.value = null
        }
    }

    Column {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Uri->  ${uri.value.toString()}",
            modifier = modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            painter = rememberAsyncImagePainter(model = uri.value), contentDescription = null
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 40.dp), onClick = {

            pickImage.value = true

        }) {
            Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Pick Image")
        }

        Spacer(modifier = Modifier.height(30.dp))
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoPermissionPickerTheme {
        PickerResult()
    }
}
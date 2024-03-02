package com.example.eventexplorerapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventexplorerapp.R
import com.example.eventexplorerapp.ui.theme.AppTheme

@Composable
fun FlashScreen(
    modifier : Modifier = Modifier,
    navController: NavController
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.flash_page_image),
            contentDescription = "Flash Image",
            modifier = Modifier
                .size(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = stringResource(id = R.string.app_moto),
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 40.dp)
        )
        Spacer(modifier = Modifier.height(90.dp))
        Button(
            onClick = {
                    navController.navigate("login")
            },
            shape = RectangleShape,
            modifier = Modifier
                .size(width = 300.dp, height = 60.dp)
                .clip(MaterialTheme.shapes.small),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),

        ) {
            Text(
                text = "Get Started",
                style = MaterialTheme.typography.displayMedium,
            )
            
        }
    }


}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun FlashScreenPreview(){
//    AppTheme {
//        FlashScreen(onClick = {})
//    }
//}
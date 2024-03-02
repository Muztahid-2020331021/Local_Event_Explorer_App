package com.example.eventexplorerapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.eventexplorerapp.R
import com.example.eventexplorerapp.ui.theme.AppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.displayMedium,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(R.drawable.hand_phone),
            contentDescription = "Flash Image",
            modifier = Modifier
                .size(300.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))


        TextField(
            value = email,
            onValueChange = {email = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(text = "Enter your email")
            },
            label = {
                Text(text = "Email")
            },
            modifier = Modifier
                .width(325.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = {password = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(text = "Enter password")
            },
            label = {
                Text(text = "Password")
            },
            modifier = Modifier
                .width(325.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))


        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "Forgot Password",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                maxLines = 1,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate("EventScreen")
            },
            shape = RectangleShape,
            modifier = Modifier
                .size(width = 300.dp, height = 60.dp)
                .clip(MaterialTheme.shapes.small),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),

            ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.displayMedium,
            )

        }
        Spacer(modifier = Modifier.height(20.dp))

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Don't have an account?",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                maxLines = 1,
                fontSize = 15.sp
            )

            TextButton(onClick = {
                navController.navigate("signup")
            }) {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    fontSize = 15.sp
                )
            }
        }


    }


}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginScreenPreview(){
//    AppTheme {
//        LoginScreen()
//    }
//}
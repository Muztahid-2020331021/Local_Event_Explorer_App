package com.example.eventexplorerapp.ui

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventexplorerapp.R
import com.example.eventexplorerapp.data.signedIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val auth = FirebaseAuth.getInstance()
    val currentUser by rememberUpdatedState(newValue = auth.currentUser)

    if (currentUser != null) {
        // User is already authenticated, navigate to EventScreen
        navController.navigate("HomeScreen")
        return
    }
    val context = LocalContext.current

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
            contentDescription = "Hand Image",
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
                if(email.isEmpty() || password.isEmpty())
                {
                    showToast(context, "Email or password cannot be empty")
                    return@Button
                }
                authenticateUser(email, password, navController, context)
                signedIn = true

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

private fun authenticateUser(email: String, password: String, navController: NavController, context: Context) {
    val auth = FirebaseAuth.getInstance()
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Authentication successful, navigate to next screen
                navController.navigate("HomeScreen")
            } else {
                // Authentication failed, handle error
                val message = if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    "Invalid email or password. Please try again."
                } else {
                    "Authentication failed. Please try again later."
                }
                showToast(context, message)

            }
        }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginScreenPreview(){
//    AppTheme {
//        LoginScreen()
//    }
//}
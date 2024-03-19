package com.example.eventexplorerapp.ui

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventexplorerapp.ui.theme.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

@Composable
fun RegisterScreen(
    modifier : Modifier = Modifier,
    navController: NavController

    ){

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
            text = "Welcome Onboard!",
            style = MaterialTheme.typography.displayMedium,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Let's help you create your new account",
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            maxLines = 1,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(70.dp))
        TextField(
            value = fullName,
            onValueChange = {fullName = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(text = "Enter your full name")
            },
            label = {
                Text(text = "Name")
            },
            modifier = Modifier
                .width(325.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
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

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = confirmPassword,
            onValueChange = {confirmPassword = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            placeholder = {
                Text(text = "Re-enter your password")
            },
            label = {
                Text(text = "Confirm Password")
            },
            modifier = Modifier
                .width(325.dp)
        )


        Spacer(modifier = Modifier.height(80.dp))

        Button(
            onClick = {
                if (fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                    registerUser(email, password, navController, context)
                }
                else
                {
                    showToast(context, "Logical Error")
                }
            },
            shape = RectangleShape,
            modifier = Modifier
                .size(width = 300.dp, height = 60.dp)
                .clip(MaterialTheme.shapes.small),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),

            ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.displayMedium,
            )

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                maxLines = 1,
                fontSize = 15.sp
            )

            TextButton(onClick = {
                navController.navigate("login")
            }) {
                Text(
                    text = "Sign In",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    fontSize = 15.sp
                )
            }
        }
    }


}


private fun registerUser(email: String, password: String, navController: NavController, context: Context) {
    val auth = FirebaseAuth.getInstance()
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast(context, "Registration successful")
                navController.navigate("login")
                // Navigate to the next screen if registration is successful
                // navController.navigate("next_screen")
            } else {
                val message = when (task.exception) {
                    is FirebaseAuthWeakPasswordException -> "Password is too weak"
                    is FirebaseAuthEmailException -> "Invalid email address"
                    is FirebaseAuthUserCollisionException -> "The email is already in use by another account"
                    else -> "Registration failed. Please try again later."
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
//fun RegisterScreenPreview(){
//    AppTheme {
//        RegisterScreen{}
//    }
//}
package com.example.eventexplorerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventexplorerapp.ui.FlashScreen
import com.example.eventexplorerapp.ui.HomeScreen
import com.example.eventexplorerapp.ui.LoginScreen
import com.example.eventexplorerapp.ui.RegisterScreen
import com.example.eventexplorerapp.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavHost(navController = navController, startDestination = "flash" ){
                        composable("flash"){ FlashScreen(navController = navController) }
                        composable("login"){ LoginScreen( navController = navController)}
                        composable("signup"){ RegisterScreen(navController = navController)}
                        composable("EventScreen"){ EventScreen()}
                    }
//                    EventScreen()
                }
            }
        }
    }
}





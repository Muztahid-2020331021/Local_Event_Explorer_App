package com.example.eventexplorerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventexplorerapp.data.signedIn
import com.example.eventexplorerapp.ui.AddNewEvent
import com.example.eventexplorerapp.ui.AfterClickOnEvent
import com.example.eventexplorerapp.ui.CategoryScreen
import com.example.eventexplorerapp.ui.FlashScreen
import com.example.eventexplorerapp.ui.HomeScreen
import com.example.eventexplorerapp.ui.LoginScreen
import com.example.eventexplorerapp.ui.MoreInfo
import com.example.eventexplorerapp.ui.MyEvents
import com.example.eventexplorerapp.ui.ProfileScreen
import com.example.eventexplorerapp.ui.RealEventScreen
import com.example.eventexplorerapp.ui.RegisterScreen
import com.example.eventexplorerapp.ui.ScheduleConfirmation
import com.example.eventexplorerapp.ui.ScheduledEvents
import com.example.eventexplorerapp.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    GetCurrentLocationAndAddress()
                    NavigatorFunction()
                }
            }
        }
    }
}


@Composable
fun NavigatorFunction(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = getStartDestination(signedIn)) {

        composable("flash"){ FlashScreen(navController = navController) }
        composable("login"){ LoginScreen( navController = navController)}
        composable("signup"){ RegisterScreen(navController = navController) }
        composable("HomeScreen") { HomeScreen(navController = navController) }
        composable("AfterClickOnEvent"){ AfterClickOnEvent(navController = navController) }
        composable("MoreInfoScreen"){MoreInfo(navController = navController) }
        composable("ProfileScreen"){ProfileScreen(navController = navController) }
        composable("CategoryScreen"){CategoryScreen(navController = navController) }
        composable("ScheduledEvents"){ ScheduledEvents(navController = navController) }
        composable("ScheduledConfirmation"){ ScheduleConfirmation(navController = navController) }
        composable("RealEvent"){ RealEventScreen(navController = navController) }
        composable("MyEvents"){ MyEvents(navController = navController) }
        composable("EventAddingScreen"){ AddNewEvent(navController = navController) }
    }
}

private fun getStartDestination(isLoggedIn: Boolean): String {
    return if (isLoggedIn) {
        "HomeScreen"
    } else {
        "login"
    }
}





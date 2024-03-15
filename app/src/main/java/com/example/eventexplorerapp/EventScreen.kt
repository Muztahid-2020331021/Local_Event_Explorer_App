package com.example.eventexplorerapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventexplorerapp.ui.AddNewEvent
import com.example.eventexplorerapp.ui.AfterClickOnEvent
import com.example.eventexplorerapp.ui.CategoryScreen
import com.example.eventexplorerapp.ui.HomeScreen
import com.example.eventexplorerapp.ui.MoreInfo
import com.example.eventexplorerapp.ui.MyEvents
import com.example.eventexplorerapp.ui.ProfileScreen
import com.example.eventexplorerapp.ui.RealEventScreen
import com.example.eventexplorerapp.ui.ScheduleConfirmation
import com.example.eventexplorerapp.ui.ScheduledEvents
import com.example.eventexplorerapp.ui.theme.AppTheme
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.Locale


data class BottomNavigationItems(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)



@Composable
fun funOfItems(): List<BottomNavigationItems> {
    return listOf(
        BottomNavigationItems(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItems(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        ),
        BottomNavigationItems(
            title = "Events",
            selectedIcon = ImageVector.vectorResource(R.drawable.baseline_assignment_24),
            unselectedIcon = ImageVector.vectorResource(R.drawable.outline_assignment_24)
        ),
        BottomNavigationItems(
            title = "Profile",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle
        ),
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EventScreen() {
    val items = funOfItems()
    val navController = rememberNavController()
    Scaffold (
        topBar = {
            MyTopBar()
        },
        bottomBar = {
            BottomBar(items = items, navController = navController)
        }
    ){
        val itt = it
        NavHost(navController = navController, startDestination = "HomeScreen") {
            composable("HomeScreen") {
                HomeScreen(
                    contentPadding = itt,
                    navController = navController
                )
            }
            composable("AfterClickOnEvent"){
                AfterClickOnEvent(
                    contentPadding= itt,
                    navController = navController
                )
            }
            composable("MoreInfoScreen"){
                MoreInfo(contentPadding = itt,navController = navController)
            }
            composable("ProfileScreen"){
                ProfileScreen(contentPadding = itt,navController = navController)
            }
            composable("CategoryScreen"){
                CategoryScreen(contentPadding = itt,navController = navController)
            }
            composable("ScheduledEvents"){
                ScheduledEvents(contentPadding = itt, navController = navController)
            }
            composable("ScheduledConfirmation"){
                ScheduleConfirmation(contentPadding = itt,navController = navController)
            }
            composable("RealEvent"){
                RealEventScreen(contentPadding = itt, navController = navController)
            }
            composable("MyEvents"){
                MyEvents(contentPadding = itt,navController = navController)
            }
            composable("EventAddingScreen"){
                AddNewEvent(contentPadding = itt,navController = navController)
            }
        }

    }

}


@Composable
fun BottomBar(items:List<BottomNavigationItems>,navController: NavController){
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar (
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        tonalElevation = 10.dp,
        modifier = Modifier.height(80.dp)
    ){
        items.forEachIndexed{
            index,item ->

            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    when(index){
                        0 -> navController.navigate("HomeScreen")
                        1 -> navController.navigate("CategoryScreen")
                        2 -> navController.navigate("ScheduledEvents")
                        3 -> navController.navigate("ProfileScreen")
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}

var currentLocation: String = "Akhalia, Sylhet,\nBangladesh"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,

) {
    TopAppBar(
        title = {
            Row{
                Text(
                    text = currentLocation,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
                Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Arrow down")
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    )
}



@Composable
fun GetCurrentLocationAndAddress() {
    val context = LocalContext.current

    if (checkLocationPermissions(context)) {
        requestLocationUpdates(context)
    } else {
        // Request permissions if not granted
        RequestPermission()
    }
}

private fun checkLocationPermissions(context: Context): Boolean {
    return (ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED)
}

@SuppressLint("MissingPermission")
private fun requestLocationUpdates(context: Context) {
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
        location?.let {
            getAddressFromLocation(context, it)
        }
    }.addOnFailureListener { exception ->
        exception.printStackTrace()
        Toast.makeText(context, "Failed to get location", Toast.LENGTH_SHORT).show()
    }
}

private val coroutineScope = CoroutineScope(Dispatchers.Main)

private fun getAddressFromLocation(context: Context, location: Location) {
    coroutineScope.launch {
        withContext(Dispatchers.IO) {
            val geocoder = Geocoder(context, Locale.getDefault())
            try {
                val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                if (addresses != null) {
                    if (addresses.isNotEmpty()) {
                        val cityName = addresses[0].locality ?: ""
                        val areaName = addresses[0].subLocality ?: ""
                        val countryName = addresses[0].countryName ?: ""
                        val locationName = "$areaName, $cityName, $countryName"
                        currentLocation = locationName
                       // Log.e("Location",locationName)
                        // Display location name or do something else with it
                     //   Toast.makeText(context, "Current Location: $locationName", Toast.LENGTH_SHORT).show()
                    } else {
                       // Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
               // Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
@Composable
fun RequestPermission() {
    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val locationPermissionGranted = permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseLocationPermissionGranted = permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        if (locationPermissionGranted && coarseLocationPermissionGranted) {
            // Permissions granted, fetch location and address
            requestLocationUpdates(context as ComponentActivity)
        } else {
            // Permissions not granted, show a message or handle accordingly
           // Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    val permissionsToRequest = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    requestPermissionLauncher.launch(permissionsToRequest)
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfilePreview(){
    AppTheme {
        EventScreen()
    }
}












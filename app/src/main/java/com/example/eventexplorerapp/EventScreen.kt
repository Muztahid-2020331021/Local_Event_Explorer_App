package com.example.eventexplorerapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    currentLocation: String = "Akhalia, Sylhet,\nBangladesh",
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfilePreview(){
    AppTheme {
        EventScreen()
    }
}












package com.example.eventexplorerapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eventexplorerapp.BottomBar
import com.example.eventexplorerapp.MyTopBar
import com.example.eventexplorerapp.data.EventList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController
){

    Scaffold (
        topBar = {
            MyTopBar()
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ){
            val upSpace = it.calculateTopPadding()
            Spacer(modifier = Modifier.height(upSpace))

            Text(
                text = "Suggested",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                val events = EventList.eventsSuggested
                items(events){
                    CommonEventUi(
                        event = it,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(190.dp)
                            .width(350.dp),
                        navController=navController
                    )
                }
            }

            Text(
                text = "Events Nearby",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(bottom = it.calculateBottomPadding())
            ){
                val events = EventList.eventsNearby
                items(events) {

                    CommonEventUi(
                        event = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .height(190.dp),
                        navController=navController
                    )
                }
            }
        }
    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview(){

}
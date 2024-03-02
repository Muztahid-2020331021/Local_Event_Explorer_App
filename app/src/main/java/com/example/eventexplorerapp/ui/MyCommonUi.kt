package com.example.eventexplorerapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventexplorerapp.data.Event
import com.example.eventexplorerapp.data.EventList
import com.example.eventexplorerapp.data.selectedEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCommonUi(
    event: Event,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .height(190.dp),
    navController: NavController
){
    Card(
        onClick = {
            selectedEvent = event.id
            navController.navigate("AfterClickOnEvent")
        },
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Box{
            Image(
                painter = painterResource(id = event.category.imageId),
                contentDescription = event.title,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = event.title,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .width(200.dp)
                )
                Text(
                    text = "${event.date} at ${event.time}",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(
                    onClick = {
                        EventList.myEventList.remove(event)
                        navController.navigate("MyEvents")
                    },
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription ="delete",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
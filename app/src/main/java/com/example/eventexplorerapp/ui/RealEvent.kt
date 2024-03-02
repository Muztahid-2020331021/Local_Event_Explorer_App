package com.example.eventexplorerapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventexplorerapp.EventScreen
import com.example.eventexplorerapp.data.EventList
import com.example.eventexplorerapp.data.eventCategory
import com.example.eventexplorerapp.ui.theme.AppTheme

@Composable
fun RealEventScreen(
    contentPadding: PaddingValues,
    navController: NavController
){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ){
        val upSpace = contentPadding.calculateTopPadding()
        Spacer(modifier = Modifier.height(upSpace))
        Text(
            text = eventCategory.name,
            fontSize = 23.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        val events = EventList.eventsNearby
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            contentPadding = PaddingValues(bottom = contentPadding.calculateBottomPadding())
        ){
            items(events){event ->
                if(eventCategory == event.category){
                    CommonEventUi(event = event, navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventAppScreenPreview(){
    AppTheme {
        EventScreen()
    }
}
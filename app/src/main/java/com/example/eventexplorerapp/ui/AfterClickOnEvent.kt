package com.example.eventexplorerapp.ui

import android.widget.CalendarView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.eventexplorerapp.data.EventList
import com.example.eventexplorerapp.data.selectedEvent

@Composable
fun AfterClickOnEvent(
    contentPadding: PaddingValues,
    navController : NavController
){
    val event = EventList.eventsNearby[selectedEvent]

    Column (
        modifier = Modifier
            .padding(20.dp)
    ){
        val upSpace = contentPadding.calculateTopPadding()
        Spacer(modifier = Modifier.height(upSpace))
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            ShowCard(event = event)

            Button(
                onClick = {
                          navController.navigate("MoreInfoScreen")
                },
                modifier = Modifier
                    .size(height = 60.dp, width = 250.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "More Information",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 15.sp
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "More Info",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Text(
                text = "Calendar",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            var date by remember { mutableStateOf("") }
            AndroidView(
                factory = { CalendarView(it) },
                update = {
                    it.setOnDateChangeListener { _, year, month, day ->
                        date = "$day - ${month + 1} - $year"
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckPreview(){

}
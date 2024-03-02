package com.example.eventexplorerapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventexplorerapp.R
import com.example.eventexplorerapp.data.EventList
import com.example.eventexplorerapp.data.selectedEvent

@Composable
fun MoreInfo(
    contentPadding: PaddingValues,
    navController: NavController
){
    val event = EventList.eventsNearby[selectedEvent]
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val upSpace = contentPadding.calculateTopPadding()
        Spacer(modifier = Modifier.height(upSpace))
        Text(
            text = "Event Details",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        SubInfo(
            icon = ImageVector.vectorResource(id = R.drawable.outline_format_list_bulleted_24),
            text = event.description,
            alignment = Alignment.Top
        )
        SubInfo(
            icon = Icons.Outlined.LocationOn,
            text = event.location,
            alignment = Alignment.CenterVertically
        )
        SubInfo(
            icon = ImageVector.vectorResource(id = R.drawable.outline_calendar_month_24),
            text = "Date: ${event.date}",
            alignment = Alignment.CenterVertically
        )
        SubInfo(
            icon = ImageVector.vectorResource(id = R.drawable.baseline_access_time_filled_24),
            text = "Time: ${event.time}",
            alignment = Alignment.CenterVertically
        )
        SubInfo(
            icon =ImageVector.vectorResource(id = R.drawable.baseline_phone_24),
            text = "Contact Number: ${event.phoneNumber}",
            alignment = Alignment.CenterVertically
        )
        Text(
            text = "Organized by,\n${event.organizer}",
            textAlign = TextAlign.Start,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .size(height = 60.dp, width = 250.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
        ) {
            Text(
                text = "Find in map",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                if(!EventList.scheduledEvents.contains(event)){
                    EventList.scheduledEvents.add(event)
                }
                navController.navigate("ScheduledConfirmation")

            },
            modifier = Modifier
                .size(height = 60.dp, width = 250.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
        ) {
            Text(
                text = "Add to Schedule",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                fontSize = 20.sp
            )
        }
        val bottomSpace = contentPadding.calculateBottomPadding()
        Spacer(modifier = Modifier.height(bottomSpace))
    }
}

@Composable
fun SubInfo(
    icon: ImageVector,
    text: String,
    alignment: Alignment.Vertical
){
    Row (
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = alignment
    ){
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
        )
        Text(
            text = text,
            textAlign = TextAlign.Start,
            fontSize = 20.sp
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MoreInfoPreview(){
//    MoreInfo()
}
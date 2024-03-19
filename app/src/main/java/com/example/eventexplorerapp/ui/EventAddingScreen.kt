package com.example.eventexplorerapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventexplorerapp.BottomBar
import com.example.eventexplorerapp.MyTopBar
import com.example.eventexplorerapp.data.Event
import com.example.eventexplorerapp.data.EventList

@Composable
fun AddNewEvent(
    navController: NavController
){

    Scaffold (
        topBar = {
            MyTopBar()
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ){contentPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .safeDrawingPadding()
                .verticalScroll(rememberScrollState())
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            var title by remember{ mutableStateOf("") }
            var time by remember{ mutableStateOf("") }
            var date by remember{ mutableStateOf("") }
            var description by remember{ mutableStateOf("") }
            var latitude by remember { mutableStateOf("") }
            var longitude by remember { mutableStateOf("") }
            var idd by remember{ mutableStateOf("") }
            var organizer by remember{ mutableStateOf("") }
            var phoneNumber by remember{ mutableStateOf("") }

            val upSpace = contentPadding.calculateTopPadding()
            Spacer(modifier = Modifier.height(upSpace))

            Text(
                text = "Create New Event",
                style = MaterialTheme.typography.displayMedium,
                fontSize = 20.sp
            )

            TextField(
                value = title,
                onValueChange = {title = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(text = "Enter title of your Event")
                },
                label = {
                    Text(text = "Title")
                },
                modifier = Modifier
                    .width(325.dp)
            )
            TextField(
                value = time,
                onValueChange = {time = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(text = "Enter time")
                },
                label = {
                    Text(text = "Time")
                },
                modifier = Modifier
                    .width(325.dp)
            )
            TextField(
                value = date,
                onValueChange = {date = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(text = "Enter date")
                },
                label = {
                    Text(text = "Date")
                },
                modifier = Modifier
                    .width(325.dp)
            )
            TextField(
                value = description,
                onValueChange = {description = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(text = "Enter description")
                },
                label = {
                    Text(text = "Description")
                },
                modifier = Modifier
                    .width(325.dp)
            )
            TextField(
                value = latitude,
                onValueChange = {latitude = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(text = "Enter latitude")
                },
                label = {
                    Text(text = "Latitude")
                },
                modifier = Modifier
                    .width(325.dp)
            )
            TextField(
                value = longitude,
                onValueChange = {longitude = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(text = "Enter longitude")
                },
                label = {
                    Text(text = "Longitude")
                },
                modifier = Modifier
                    .width(325.dp)
            )

            TextField(
                value = idd,
                onValueChange = {idd = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(text = "Enter ID")
                },
                label = {
                    Text(text = "ID")
                },
                modifier = Modifier
                    .width(325.dp)
            )
            TextField(
                value = organizer,
                onValueChange = {organizer = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(text = "Enter your identity")
                },
                label = {
                    Text(text = "Organizer")
                },
                modifier = Modifier
                    .width(325.dp)
            )
            TextField(
                value = phoneNumber,
                onValueChange = {phoneNumber = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(text = "Enter your phone number")
                },
                label = {
                    Text(text = "Phone Number")
                },
                modifier = Modifier
                    .width(325.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    EventList.myEventList.add(
                        Event(
                            title = title,
                            time = time,
                            date = date,
                            latitude = latitude.toDouble(),
                            longitude = longitude.toDouble(),
                            description = description,
                            id = idd.toInt(),
                            organizer = organizer,
                            phoneNumber = phoneNumber
                        )
                    )
                    navController.navigate("MyEvents")
                },
                shape = RectangleShape,
                modifier = Modifier
                    .size(width = 300.dp, height = 60.dp)
                    .clip(MaterialTheme.shapes.small),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),

                ) {
                Text(
                    text = "Create",
                    style = MaterialTheme.typography.displayMedium,
                )

            }
            val bottomSpace = contentPadding.calculateBottomPadding()
            Spacer(modifier = Modifier.height(bottomSpace))
        }
    }


}




//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun sss(){
//    AppTheme {
//        AddNewEvent()
//    }
//
//}
package com.example.eventexplorerapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eventexplorerapp.BottomBar
import com.example.eventexplorerapp.EventViewModel
import com.example.eventexplorerapp.MyTopBar


@Composable
fun ScheduledEvents(
    navController: NavController
){
    val viewModel = viewModel<EventViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val events by viewModel.events.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

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
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            val upSpace = contentPadding.calculateTopPadding()
            Spacer(modifier = Modifier.height(upSpace))
            Text(
                text = "Scheduled Events",
                fontSize = 23.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Search for already scheduled events"
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "ScheduleSearch"
                    )
                }
            )
            if(isSearching){
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            else{
                var check = false
                events.forEach {
                    check = it.doesMatchSearchQuery(searchText)
                }
                if(check){
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(bottom = contentPadding.calculateBottomPadding())
                    ){
                        items(events){event ->
                            ScheduleCommonUi(event = event,  navController = navController)
                        }
                    }
                }
                else{
                    var text = ""
                    text = if(searchText == ""){
                        "\'Your event is empty\'"
                    } else{
                        "\'No match found\'"
                    }
                    Text(
                        text = text
                    )
                }

            }

        }
    }


}

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eventexplorerapp.EventViewModel

@Composable
fun CategoryScreen(
    contentPadding: PaddingValues,
    navController: NavController
){
    val viewModel = viewModel<EventViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val upSpace = contentPadding.calculateTopPadding()
        Spacer(modifier = Modifier.height(upSpace))
        OutlinedTextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Search by category"
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "typeSearch"
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
            categories.forEach {
                check = it.doesMatchSearchQuery(searchText)
            }
            if(check){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = contentPadding.calculateBottomPadding())
                ){
                    items(categories){event ->
                        CommonCategoryUi(event = event,navController = navController)
                    }
                }
            }
            else{
                Text(
                    text = "\'No Match Found\'"
                )
            }
        }

    }
}
